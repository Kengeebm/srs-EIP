package com.eip.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.eip.domain.UserDetailsLeave;
import com.eip.repository.UserDetailsLeaveRepository;
import com.eip.service.MailService;
import com.eip.service.UserDetailsLeaveService;
import com.eip.serviceHelper.UserDetailsLeaveServiceHelper;

import utils.Constants;

/**
 * The type User details leave service.
 */
@Service
public class UserDetailsLeaveServiceImpl implements UserDetailsLeaveService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsLeaveServiceImpl.class);

	/**
	 * The User details leave repository.
	 */
	@Autowired
	UserDetailsLeaveRepository userDetailsLeaveRepository;

	/**
	 * The Mail service.
	 */
	@Autowired
	MailService mailService;

	@Autowired
	UserDetailsLeaveServiceHelper helper;

	/**
	 * The Mongo template.
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public List<UserDetailsLeave> findAll() {
		logger.info("request to fetchALL from UserDetailsLeave {}");
		return userDetailsLeaveRepository.findAll();
	}

	@Override
	public List<UserDetailsLeave> findByStatus(String status) {
		return userDetailsLeaveRepository.findByStatus(status);
	}

	@Override
	public void save(UserDetailsLeave userDetailsLeave) {
		try {

			logger.info("Sending Leave Request to the HR through mail");
			mailService.sendLeaveRequestEmail(userDetailsLeave, true);
			logger.info("request to save from UserDetailsLeave {}", userDetailsLeave);
			userDetailsLeaveRepository.save(userDetailsLeave);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Optional<UserDetailsLeave> findById(String id) {
		logger.info("request to fetchAll from UserDetailsLeave {}", id);
		return userDetailsLeaveRepository.findById(id);
	}

	@Override
	public List<UserDetailsLeave> findByEmployeeId(String employeeId) {
		logger.info("request to fetchAllByEmployeeId from UserDetailsLeave {}", employeeId);
		return userDetailsLeaveRepository.findByEmployeeId(employeeId);
	}

	@Override
	public Boolean leaveRequestMail(UserDetailsLeave userDetailsLeave, Boolean isApplyLeave) {
		return mailService.sendLeaveRequestEmail(userDetailsLeave, isApplyLeave);
	}

//    @Override
//    public List<UserDetailsLeave> findByLeaveTypeAndStatus(String leaveType, String status) {
//        logger.info("request to fetchAll from findByLeaveType {}", leaveType);
//        return userDetailsLeaveRepository.findByLeaveTypeAndStatus(leaveType, status);
//    }

//    @Override
//    public List<UserDetailsLeave> findBySlAlLeaveAndStatus(String leaveType, String status) {
//        return userDetailsLeaveRepository.findByLeaveTypeNotLikeAndStatus(leaveType, status);
//    }

	@Override
	public List<UserDetailsLeave> updateEmployeeListByLeaveStatus(String leaveStatus,
			List<UserDetailsLeave> userDetailsLeaveList) {
		userDetailsLeaveList.stream().forEach(userDetailsLeave -> userDetailsLeave.setStatus(leaveStatus));
		return userDetailsLeaveRepository.saveAll(userDetailsLeaveList);
	}

	@Override
	public ByteArrayInputStream userDetailLeaveDataToExcel(List<UserDetailsLeave> UserDetailList) {

		ByteArrayInputStream out = null;
		try {
			out = helper.userDetailLeaveDataToExcel(UserDetailList);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return out;
	}

	@Override
	public List<UserDetailsLeave> findBySubmittedList(List<String> statusList) {
		return userDetailsLeaveRepository.findByStatusIn(statusList);
	}

//    @Override
//    public Integer findCountByLeaveTypeAndStatus(String leaveType, List<String> statusList) {
//        return userDetailsLeaveRepository.findByLeaveTypeInAndStatusIn(leaveType, statusList).size();
//    }

	@Override
	public Long LeaveStatusCountByLeaveType(String leaveType, String status) {
		return userDetailsLeaveRepository.countByLeaveTypeAndStatus(leaveType, status);
	}

	@Override
	public Long countByLeaveTypeAndStatusAndSubmitDate(String leaveType, String status, String fromDate) {
		long count = 0;
		List<UserDetailsLeave> leaveData = userDetailsLeaveRepository.findByLeaveTypeAndStatus(leaveType, status);
		for (UserDetailsLeave t : leaveData) {
			if ((t.getFromDate().substring(0, 7)).equals(fromDate)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<UserDetailsLeave> findByEmployeeIdAndStatusIn(String empId, List<String> status) {
		return userDetailsLeaveRepository.findByEmployeeIdAndStatusIn(empId, status);
	}

	@Async
	@Override
	public String sendMailToEmployeeForLeaveApproval(List<UserDetailsLeave> userDetailsLeave) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		List<UserDetailsLeave> leavelist = userDetailsLeave;

		try {

			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

			for (UserDetailsLeave ul : leavelist) {
				String text = null;
				String req;
				String subreq;
				String[] repnot = { "praneet@srsconsultinginc.com", "archanad@srsconsultinginc.com",
						"maneesh@srsconsultinginc.com" };

				String notifymanager = ul.getNotificationTo();
				String repmanager = ul.getReportingManagerEmail();
				for (int j = 0; j < repnot.length; j++) {
					if (repnot[j].equals(ul.getNotificationTo())) {
						notifymanager = Constants.EMAIL_TO;
					} else if (repnot[j].equals(ul.getReportingManagerEmail()))
						;
					repmanager = Constants.EMAIL_TO;
				}
				String[] mails = { repmanager, notifymanager };

				if (repmanager.equals(notifymanager)) {
					message.setTo(ul.getEmpMail());
					message.setCc(Constants.EMAIL_TO);
				} else {
					message.setTo(ul.getEmpMail());
					message.setCc(mails);
				}

				String Days;
				if (ul.getTotalDays() > 1) {
					Days = "Days";
				} else {
					Days = "Day";
				}
				String leavetype = ul.getLeaveType();
				if (ul.getTotalDays() == 1) {
					req = "on: " + ul.getFromDate() + " (" + ul.getTotalDays() + " " + Days + ") ";
					subreq = "for " + ul.getFromDate();

				} else {
					req = " from: " + ul.getFromDate() + ", to: " + ul.getToDate() + " (" + ul.getTotalDays() + " "
							+ Days + ") ";
					subreq = "for " + ul.getFromDate() + " : " + ul.getToDate();
				}
				if (ul.getStatus().equals("Approved")) {
					message.setSubject(leavetype + " is Approved " + ul.getEmpName() + " " + subreq);
					text = "<html><body>" + "Hi " + ul.getEmpName() + "," + "<br/><br/>" + "The Applied " + leavetype
							+ " " + req + "<br/>" + "is Approved" + "<br/><br/>" + "Thanks & Regards" + "<br/>"
							+ "HR Team" + "<br/>" + "</body></html>";
				} else {
					message.setSubject(leavetype + " is Rejected " + ul.getEmpName() + " " + subreq);
					text = "<html><body>" + "Hi " + ul.getEmpName() + "," + "<br/><br/>" + "The Applied " + leavetype
							+ " " + req + "<br/>" + "is rejected " + "<br/><br/>" + "the reason for rejection : "
							+ ul.getReasonForReject() + "<br/>" + "Thanks & Regards" + "<br/>" + "HR Team" + "<br/>"
							+ "</body></html>";
				}

				message.setText(text, true);
				javaMailSender.send(mimeMessage);
				logger.info("Sent email to User : {}");
				System.out.println("Sent email to User : {}");

			}
		} catch (Exception e) {
			System.out.println("Sent email error");
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.warn("Email could not be sent to user '{}'", e);
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			} else {
				logger.warn("Email could not be sent to user '{}': {}", e.getMessage());
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			}
		}
		return Constants.EMAIL_SENT;

	}

	@Override
	public List<UserDetailsLeave> findByLeaveTypeInAndStatusIn(List<String> leaveTypes, List<String> statusList) {
		return userDetailsLeaveRepository.findByLeaveTypeInAndStatusIn(leaveTypes, statusList);
	}

	@Override
	public List<UserDetailsLeave> findByReportingManagerEmailInAndLeaveTypeInAndStatusIn(List<String> reportingMngEmail,
			List<String> leaveTypes, List<String> statusList) {
		return userDetailsLeaveRepository.findByReportingManagerEmailInAndLeaveTypeInAndStatusIn(reportingMngEmail, leaveTypes, statusList);
	}

	@Override
	public List<UserDetailsLeave> findByReportingManagerEmailInAndStatusIn(List<String> reportingMngEmail,
			List<String> statusList) {
		return userDetailsLeaveRepository.findByReportingManagerEmailInAndStatusIn(reportingMngEmail, statusList);
	}
}