package com.eip.service;

import com.eip.domain.ContactAdmin;
import com.eip.domain.UserDetailsLeave;
import io.github.jhipster.config.JHipsterProperties;

import org.apache.commons.math3.analysis.function.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import utils.Constants;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;
    
  


    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public Boolean sendLeaveRequestEmail(UserDetailsLeave userDetailsLeave, Boolean isApplyLeave) {
  
    	String Days;
		if (userDetailsLeave.getTotalDays() > 1) {
			Days = "Days";
		} else {
			Days = "Day";
		}
		System.out.println("sendLeaveRequestEmail");
		StringBuilder text = new StringBuilder();
		text.append("<html><body>" + "Hi," + "<br/><br/>");
		if (userDetailsLeave != null && Constants.WFH.equalsIgnoreCase(userDetailsLeave.getLeaveType())) {
			if (isApplyLeave) {
				text.append("Pls find my WFH application for approval.. <br/><br/>");
			} else {
				text.append("This is a update to cancel my planned WFH.. <br/><br/>");
			}
//            if(userDetailsLeave.getFromDate() == userDetailsLeave.getToDate()) {
//            text.append("Request date" + userDetailsLeave.getFromDate() + "<br/><br/>");
//            }else {
			text.append("Request date from: " + userDetailsLeave.getFromDate() + ", to: " + userDetailsLeave.getToDate()
					+ " (" + userDetailsLeave.getTotalDays() + " " + Days + ") " + "<br/><br/>");
			text.append("Reason for Work-From-Home:"
					+ (userDetailsLeave.getReasonForLeave() != null ? userDetailsLeave.getReasonForLeave() : "")
					+ "<br/><br/>");
			text.append("Regards,<br/>");
			text.append("Employee Name:" + userDetailsLeave.getEmpName() + "<br/>");
			text.append("Employee Id:" + userDetailsLeave.getEmployeeId() + "<br/>");
			text.append("</body></html>");
		} else if (userDetailsLeave != null && Constants.WCL.equalsIgnoreCase(userDetailsLeave.getLeaveType())) {
			if (isApplyLeave) {
				text.append("Pls find my WCL application for approval.. <br/><br/>");
			} else {
				text.append("This is a update to cancel my planned WCL.. <br/><br/>");
			}
			text.append("Request date from: " + userDetailsLeave.getFromDate() + " to " + userDetailsLeave.getToDate()
					+ " (" + userDetailsLeave.getTotalDays() + " " + Days + ") " + "<br/><br/>");
			text.append("Reason for Work-From-Client-Location:"
					+ (userDetailsLeave.getReasonForLeave() != null ? userDetailsLeave.getReasonForLeave() : "")
					+ "<br/><br/>");
			text.append("Regards,<br/>");
			text.append("Employee Name:" + userDetailsLeave.getEmpName() + "<br/>");
			text.append("Employee Id:" + userDetailsLeave.getEmployeeId() + "<br/>");
			text.append("</body></html>");
		} else if (userDetailsLeave != null && Constants.CO.equalsIgnoreCase(userDetailsLeave.getLeaveType())) {
			if (isApplyLeave) {
				text.append("Pls find my Comp-Off application for approval.. <br/><br/>");
			} else {
				text.append("This is a update to cancel my planned Comp-Off.. <br/><br/>");
			}
			text.append("Request date from: " + userDetailsLeave.getFromDate() + ", to: " + userDetailsLeave.getToDate()
					+ " (" + userDetailsLeave.getTotalDays() + " " + Days + ") " + "<br/><br/>");
			text.append("Reason for Comp-Off:"
					+ (userDetailsLeave.getReasonForLeave() != null ? userDetailsLeave.getReasonForLeave() : "")
					+ "<br/><br/>");
			text.append("Regards,<br/>");
			text.append("Employee Name:" + userDetailsLeave.getEmpName() + "<br/>");
			text.append("Employee Id:" + userDetailsLeave.getEmployeeId() + "<br/>");
			text.append("</body></html>");
		} else {
			if (isApplyLeave) {
				text.append("Pls find my leave application for approval.. <br/><br/>");
			} else {
				text.append("This is a update to cancel my planned leave.. <br/><br/>");
			}
			text.append("Leave type:" + userDetailsLeave.getLeaveType() + "<br/><br/>");
			text.append("From date:" + userDetailsLeave.getFromDate() + "<br/><br/>");
			text.append("To date:" + userDetailsLeave.getToDate() + "<br/><br/>");
			text.append("Total days :" + userDetailsLeave.getTotalDays() + "<br/><br/>");
			text.append("Reason for leave:"
					+ (userDetailsLeave.getReasonForLeave() != null ? userDetailsLeave.getReasonForLeave() : "")
					+ "<br/><br/>");
			text.append("Thanks and Regards,<br/>");
			text.append("Employee Name:" + userDetailsLeave.getEmpName() + "<br/>");
			text.append("Employee Id:" + userDetailsLeave.getEmployeeId() + "<br/>");
			text.append("</body></html>");
		}

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            System.out.println("sendLeaveRequestEmail before email");
            
            
            String repmanager = userDetailsLeave.getReportingManagerEmail();
            String notifymanager = userDetailsLeave.getNotificationTo();
            
            String[]repnot = {"praneet@srsconsultinginc.com", "archanad@srsconsultinginc.com", "maneesh@srsconsultinginc.com"};
            for(int j=0; j< repnot.length; j++) {
            	if(repnot[j].equals(userDetailsLeave.getReportingManagerEmail())) {
            		repmanager = Constants.EMAIL_TO;
            	}else if(repnot[j].equals(userDetailsLeave.getNotificationTo())) {
            		notifymanager = Constants.EMAIL_TO;
            	}
            }
            String[] mails = {repmanager, notifymanager};
            String[] constemp = {Constants.EMAIL_TO, userDetailsLeave.getEmpMail()};
            if((repmanager.equals(Constants.EMAIL_TO) && notifymanager.equals(repmanager)) || (repmanager ==null && notifymanager == null)) {
            	System.out.println("Checking wethers emails are are equal");
            	message.setTo(Constants.EMAIL_TO);
            	message.setCc(userDetailsLeave.getEmpMail());
           }
            else if(repmanager.equals(Constants.EMAIL_TO) && !notifymanager.equals(repmanager)) {
            	message.setTo(mails);
            	message.setCc(userDetailsLeave.getEmpMail());
           }else if(notifymanager.equals(Constants.EMAIL_TO) && !repmanager.equals(Constants.EMAIL_TO)) {
            	message.setTo(mails);
            	message.setCc(userDetailsLeave.getEmpMail());
           }else if(userDetailsLeave.getNotificationTo() == null && repmanager.equals(Constants.EMAIL_TO)) {
            	message.setTo(Constants.EMAIL_TO);
            	message.setCc(userDetailsLeave.getEmpMail());
           }else if(userDetailsLeave.getNotificationTo() == null && !repmanager.equals(Constants.EMAIL_TO)) {
            	message.setCc(constemp);
            	message.setTo(repmanager);
           }else {
            	System.out.println("emails are not equal the second one is null");
            	message.setCc(constemp);
                message.setTo(mails);
           }
           
            
            
//            message.setTo(Reportingmanger);
            System.out.println("sendLeaveRequestEmail after email");
            message.setFrom(jHipsterProperties.getMail().getFrom());
            String leavetype ;
            if(userDetailsLeave.getLeaveType().equals(Constants.SL) || userDetailsLeave.getLeaveType().equals(Constants.CL)) {
            leavetype = "Leave";
            }else {
            leavetype = userDetailsLeave.getLeaveType();
            }
            
                if (isApplyLeave) {
                    message.setSubject(Constants.REQUEST_FOR+ leavetype + " (" + userDetailsLeave.getEmpName()+ " - "+ userDetailsLeave.getTotalDays()+ " " + Days+ ")");
                } else {
                    message.setSubject(Constants.REQUEST_FOR+ leavetype + " (" + userDetailsLeave.getEmpName()+ " - "+ userDetailsLeave.getTotalDays()+ " " + Days+ ")");
                }
            
            message.setText("" + text, true);
            javaMailSender.send(mimeMessage);
            System.out.println("sendLeaveRequestEmail return true before");
            log.info("Sent email to User : {}");
            return true;
        } catch (Exception e) {
            System.out.println("sendLeaveRequestEmail exception : " + e.getMessage());
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", e.getMessage());
            }
            return false;
        }
    }


    @Async
    public void emailForContactSystemAdministrator(ContactAdmin contactAdmin) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            message.setTo(Constants.EMAIL_TO);
           // message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject("User Queries");
            message.setText(contactAdmin.getName() + " has sent a request to application admin", true);
            javaMailSender.send(mimeMessage);
            log.info("Sent email to User : {}",Constants.EMAIL_TO);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
             log.warn("Email could not be sent to user '{}'", Constants.EMAIL_TO, e);
            } else {
              log.warn("Email could not be sent to user '{}': {}", Constants.EMAIL_TO, e.getMessage());
            }
        }
    }

}