package com.eip.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.eip.domain.TimeSheet;
import com.eip.domain.UserDetail;
import com.eip.repository.TimeSheetRepository;
import com.eip.repository.UserDetailRepository;
import com.eip.service.TimeSheetHistoryService;
import com.eip.service.TimeSheetService;
import com.eip.service.UserDetailService;
import com.eip.serviceHelper.TimeSheetServiceHelper;

import utils.Constants;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {

	private static final Logger logger = LoggerFactory.getLogger(TimeSheetServiceImpl.class);

	@Autowired
	TimeSheetRepository timeSheetRepo;

	@Autowired
	UserDetailRepository userDetailRepo;

	@Autowired
	TimeSheetHistoryService historyService;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	TimeSheetServiceHelper helper;

	@Autowired
	UserDetailService userService;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public TimeSheet save(TimeSheet timesheet) {
		logger.info("request to save from TimeSheet {}", timesheet);
		return timeSheetRepo.save(timesheet);
	}

	@Override
	public List<TimeSheet> saveAll(List<TimeSheet> timesheet) {
		logger.info("request to save from TimeSheet {}", timesheet);
		List<TimeSheet> timeSheets = null;

		try {
			timeSheets = timeSheetRepo.saveAll(timesheet);
		} catch (Exception e) {

		}
		logger.error("an exception is thrown", timesheet);
		return timeSheets;
	}

	@Override
	public List<TimeSheet> findAll() {
		logger.info("request to fetchAll from TimeSheet {}");
		return timeSheetRepo.findAll();
	}

	/*
	 * @Override public List<TimeSheet> findByApprovalStatus(String ApprovalStatus)
	 * { Query query = new Query();
	 * query.addCriteria(Criteria.where(Constants.SAVE_TIME).gte(fromDate).lte(
	 * toDate).and(Constants.EMPLOYEE_ID).is(employeeId)); return
	 * mongoTemplate.find(query, TimeSheet.class);
	 * logger.info("request to fetchAll from TimeSheet {}"); return
	 * timeSheetRepo.findAll(); }
	 */

	@Override
	public Optional<TimeSheet> findById(String id) {
		logger.info("request to fetchAll from TimeSheet {}", id);
		return timeSheetRepo.findById(id);
	}

	@Override
	public void update(TimeSheet timesheet) {
		logger.info("request to  from update TimeSheet {}", timesheet);
		timeSheetRepo.save(timesheet);
	}

	@Override
	public List<TimeSheet> updateAll(List<TimeSheet> timesheet) {
		logger.info("request to  from updateAll TimeSheet {}", timesheet);
		return timeSheetRepo.saveAll(timesheet);

	}

	@Override
	public void deleteAll() {
		logger.debug("request to deleteall from TimeSheet {}");
		timeSheetRepo.deleteAll();
	}

	@Override
	public void deleteById(String id) {
		logger.debug("request to deleteall from TimeSheet {}", id);
		timeSheetRepo.deleteById(id);
	}

	@Override
	public List<TimeSheet> findByDateBetween(LocalDate fromDate, LocalDate toDate, String employeeId) {
		logger.debug("request to fetchAll from TimeSheet {}", fromDate, "To", toDate);
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.SAVE_TIME).gte(fromDate).lte(toDate).and(Constants.EMPLOYEE_ID)
				.is(employeeId));
		return mongoTemplate.find(query, TimeSheet.class);
	}

	@Override
	public List<TimeSheet> findByUserLogin(String userLogin) {
		logger.debug("request to fetchAll from TimeSheet {}", userLogin);
		return timeSheetRepo.findByEmployeeId(userLogin);
	}

	@Override
	public ByteArrayInputStream generateExcel(LocalDate fromDate, LocalDate toDate, String email) {

		List<TimeSheet> timesheets = findByDateBetween(fromDate, toDate, email);
		UserDetail detail = userService.findByEmail(email);
		ByteArrayInputStream out = null;
		/*
		 * try { out = helper.timeSheetsToExcel(timesheets, detail); } catch
		 * (IOException | ParseException e) { e.printStackTrace(); }
		 */
		return out;
	}

	@Async
	@Override
	public String sendReportsToEmail(LocalDate fromDate, LocalDate toDate, String id, String email, String user,
			String firstName, String lastName) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		Optional<TimeSheet> timesheet = timeSheetRepo.findById(id);
		UserDetail detail = userService.findByEmail(email);
		ByteArrayInputStream in = null;
		try {
			in = helper.timeSheetsToExcel(timesheet.get(), detail);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
			DataSource timesheetExcel = new ByteArrayDataSource(in, Constants.APPLICATION_VND_MS_EXCEL);
			Month month = toDate.getMonth();
			int Year = toDate.getYear();
			String year = Integer.toString(Year);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			String fromdate = fromDate.format(format);
			String todate = toDate.format(format);
			message.addAttachment(firstName + " Timesheet_" + month.toString().toLowerCase() + "_" + year,
					timesheetExcel);
			message.setTo(Constants.EMAIL_TO);
			message.setSubject("Timesheet - " + month.toString().toLowerCase() + " _ " + year);
			String text = "Hi,<br/><br/>" + "PFA timesheet for the month of " + month.toString().toLowerCase() + "  "
					+ fromdate + " to " + todate + "<br/>"

//                + " Please find the attachment" + "<br/><br/>"
					+ " Thanks & Regards" + "<br/>" + firstName + " " + lastName;
			message.setText(text, true);
			javaMailSender.send(mimeMessage);
			logger.info("Sent email to User : {}");
			return Constants.EMAIL_COULD_NOT_BE_SENT;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.warn("Email could not be sent to user '{}'", e);
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			} else {
				logger.warn("Email could not be sent to user '{}': {}", e.getMessage());
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			}
		}
	}

	@Override
	public List<TimeSheet> findByApprovalStatus(String approveStatus) {
		return timeSheetRepo.findByApproveStatusIn(approveStatus);
	}

	@Override
	public List<UserDetail> findNotSubmittedEmployeeList() {
		List<TimeSheet> timeSheetList = new ArrayList<>();
		List<UserDetail> userdetails = new ArrayList<>();

		List<TimeSheet> tsp = timeSheetRepo.findAll();
		List<UserDetail> ud = userDetailRepo.findAll();
		userdetails.addAll(ud);

		timeSheetList.addAll(tsp);

		for (UserDetail userdetail : userdetails) {

			if (userdetail.getEmpId().equals("ADMIN-1")) {
				ud.remove(userdetail);
			}
			for (TimeSheet tap : timeSheetList) {
				if (tap.getApproveStatus() != null) {
					if (tap.getSubmitDate() == null
							|| tap.getSubmitDate().getMonthValue() == LocalDate.now().getMonthValue()) {
						if (!tap.getApproveStatus().equals("Rejected")) {
							if (userdetail.getEmail().equals(tap.getEmailId())) {

								ud.remove(userdetail);

							}

						}
					}
				}
			}

		}
		return ud;

	}

	@Override
	public List<TimeSheet> updateEmployeeListByTimesheetStatus(String status,
			List<TimeSheet> userTimeSheetEmployeeStatusList) {
		userTimeSheetEmployeeStatusList.stream().forEach(userDetailsLeave -> userDetailsLeave.setApproveStatus(status));
		return timeSheetRepo.saveAll(userTimeSheetEmployeeStatusList);
	}

	@Async
	@Override
	public String requestUnfreezeTimeSheet(LocalDate fromDate, LocalDate toDate, String id, String email, String user) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		UserDetail userDetail = userService.findByEmail(email);
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
			Month month = toDate.getMonth();
			message.setTo(Constants.EMAIL_TO);
			message.setSubject("Unfreeze/RePlan Timesheet for " + month);
			String text = "<html><body>" + "Hi,<br/><br/>" + "My Employee id is " + userDetail.getEmpId() + "<br/>"
					+ "I want to replan my timesheet for the month of " + toDate.getMonth() + "<br/>"
					+ "Kindly enable the timesheet." + "<br/><br/>" + "Thanks & Regards" + "<br/>"
					+ userDetail.getFirstName() + "<br/>" + "</body></html>";
			message.setText(text, true);
			javaMailSender.send(mimeMessage);
			logger.info("Sent email to User : {}");
			return Constants.EMAIL_COULD_NOT_BE_SENT;
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.warn("Email could not be sent to user '{}'", e);
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			} else {
				logger.warn("Email could not be sent to user '{}': {}", e.getMessage());
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			}
		}
	}

	@Async
	@Override
	public String sendMailToEmployeeForSubmitTimesheet(List<UserDetail> timeSheets) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
			List<String> emailList = new ArrayList<>();
			timeSheets.stream().forEach(timeSheet -> {
				emailList.add(timeSheet.getEmail());
			});
			String[] strings = new String[emailList.size()];

			for (int i = 0; i < emailList.size(); i++) {
				strings[i] = emailList.get(i);
			}

			message.setTo(strings);
			message.setSubject("Reminder for filling to Current Month Timesheet");
			String text = "<html><body>" + "Hi Team,<br/><br/>"
					+ "Please find time to submit the timesheet at the earliest." + "<br/>"
					+ "Any concerns, send email HR Team.<br/><br/>" + "Thanks & Regards" + "<br/>" + "HR Team" + "<br/>"
					+ "</body></html>";
			message.setText(text, true);
			javaMailSender.send(mimeMessage);
			logger.info("Sent email to User : {}");
			return Constants.EMAIL_SENT;
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.warn("Email could not be sent to user '{}'", e);
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			} else {
				logger.warn("Email could not be sent to user '{}': {}", e.getMessage());
				return Constants.EMAIL_COULD_NOT_BE_SENT;
			}
		}
	}

	@Override
	public ByteArrayInputStream allDataGenerateExcel(List<TimeSheet> timeSheetList) {

		ByteArrayInputStream out = null;
		try {
			out = helper.wholeDataTimeSheetsToExcel(timeSheetList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}

	@Override
	public Long timesheetStatusCount(String status) {

		/*
		 * Aggregation agg = newAggregation( match(Criteria.where("_id").exists(true)),
		 * group("approveStatus").count().as("count"),
		 * project("count").and("approveStatus").previousOperation(),
		 * sort(Sort.Direction.DESC, "approveStatus") );
		 * 
		 * //Convert the aggregation result into a List
		 * AggregationResults<TimeSheetStatusCount> groupResults =
		 * mongoTemplate.aggregate(agg, TimeSheet.class, TimeSheetStatusCount.class);
		 * List<TimeSheetStatusCount> result = groupResults.getMappedResults();
		 */
		return timeSheetRepo.countByApproveStatus(status);
	}

	@Override
	public Long countByApproveStatusAndSubmitDate(String status, String submitDate) {
		long count = 0;
		List<TimeSheet> timeSheetData = timeSheetRepo.findByApproveStatusIn(status);
		for (TimeSheet t : timeSheetData) {
			LocalDate now = t.getSubmitDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
			String formatDateTime = now.format(formatter);
			if ((formatDateTime).equals(submitDate)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<TimeSheet> findByEmailIdAndApproveStatus(String emailId, String approveStatus) {
		return timeSheetRepo.findByEmailIdAndApproveStatus(emailId, approveStatus);
	}
}
