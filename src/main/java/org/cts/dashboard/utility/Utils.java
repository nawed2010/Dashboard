package org.cts.dashboard.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.util.StringUtils;

/*import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;*/

public class Utils {

	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
	
	public static String getCurrentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
	}

	public static String getDisplayDate(Date date) {
		if(date != null) return new SimpleDateFormat(CommonConstants.APPLICATION_DISPLAY_DATE_FORMAT).format(date);
		else return "";
	}

	public static int diffInDays(Date startDt, Date endDt){
		if(startDt == null || endDt == null) return -1;
		return (int) TimeUnit.DAYS.convert((endDt.getTime() - startDt.getTime()), TimeUnit.MILLISECONDS);
	}

/*	public static void flagTaskStatus(Task task) {

		if(task!=null){
			List<TaskStatus> allStatus = new ArrayList<TaskStatus>();
			
			if(task.getAllTaskStatus()!=null && task.getAllTaskStatus().size()>0) allStatus.addAll(task.getAllTaskStatus());
			else if(task.getStatus()!=null) allStatus.add(task.getStatus());
			
			if(!allStatus.isEmpty()){
				Date today = new Date();
				for(TaskStatus status : allStatus){
					int diff = diffInDays(status.getCreatedOnDate(), today);
					//Flag task/task status if 3 or less days away from completion date 
					if(diff >= 0 && diff-task.getDaysToComplete() >= -3 && status.getStatusCode()<=1){ //Flag if status is WIP/Not Started 
						status.setFlagged(true);
						task.setFlagged(true);
					}
				}
			}
		}
		
	}*/

/*	public static void logExceptions(MailException me){
		
		if(me!=null && me instanceof MailSendException){
			Exception[] nestedExArr = ((MailSendException)me).getMessageExceptions();
			if(nestedExArr!=null && nestedExArr.length>0){
				Exception sfe = null;
				for(Exception ne : nestedExArr){
					if(ne instanceof SendFailedException){
						if(((SendFailedException) ne).getNextException() == null){
							sfe = (SendFailedException) ne;
						}else{
							sfe = ((SendFailedException) ne).getNextException();
						}
						if(sfe instanceof SMTPAddressFailedException){
							LOGGER.error("Sending Mail operation was failed to this Email Address: "+ ((SMTPAddressFailedException)sfe).getAddress());
						}else if(sfe instanceof SMTPSendFailedException){
							List<String> unsent = new ArrayList<String>();
							unsent.addAll(Utils.parseAddress(((SMTPSendFailedException)sfe).getInvalidAddresses()));
							unsent.addAll(Utils.parseAddress(((SMTPSendFailedException)sfe).getValidUnsentAddresses()));
							LOGGER.error("Sending Mail operation was failed - mail was not sent to one or more Users: " + unsent);
						}
					}
				}
			}
		}
		
	}*/
	
	/*private static String prepareEmailHead(){
		
		StringBuilder body = new StringBuilder();
		
		body.append("<!doctype html>");
		body.append("<html>");
		body.append("<head>");
		body.append("<meta name='viewport' content='width=device-width'>");
		body.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		body.append("<title>Resource Onboarding Portal Email Notification</title>");
		body.append("<style>");
		body.append("*{font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;font-size: 100%;line-height: 1.6em;margin: 0;padding: 0;}");
		body.append("body{ -webkit-font-smoothing: antialiased;height: 100%;-webkit-text-size-adjust: none;width: 100% !important;}");
		body.append("table.body-wrap{padding: 20px;width: 100%;}");
		body.append("h3 {color: #fff;font-family: 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif;font-weight: 200;line-height: 1.2em;margin: 5px 0;font-size: 25px;}");
		body.append("p, ul, ol {font-size: 14px;font-weight: normal;margin-bottom: 10px;}");
		body.append("ul li, ol li {margin-left: 5px;list-style-position: inside;}");
		body.append(".container{clear: both !important;display: block !important;margin: 0 auto !important;max-width: 900px !important; border-left: 1px solid #bce8f1; border-right: 1px solid #bce8f1;}");
		body.append(".body .container {padding: 20px;}");
		body.append(".header .container {padding: 10px 20px; border: 1px solid #31708f; border-radius: 10px 10px 0 0;}");
		body.append(".footer .container {padding: 10px 20px 0; border-bottom: 1px solid #bce8f1; border-radius: 0 0 10px 10px;}");
		body.append(".content{display: block;margin: 0 auto;max-width: 900px;}");
		body.append(".content .heading{font-weight: bold; text-decoration: underline;}");
		body.append(".content table.fw{width: 100%;}");
		body.append(".content table.box{border-collapse: collapse;min-width: 500px;}");
		body.append(".content table.box th{font-size: 14px;border: 1px solid #fff; padding:5px 15px;}");
		body.append(".content table.box td{font-size: 14px;border: 1px solid #bce8f1; padding:5px 15px;max-width:400px;}");
		body.append("</style>");
		body.append("</head>");
		body.append("<body bgcolor='#f6f6f6'>");
		body.append("<table class='body-wrap' bgcolor='#f6f6f6' cellspacing='0' cellpadding='0'>");
		body.append("<tr class='header'>");
		body.append("<td></td>");
		body.append("<td class='container' bgcolor='#31708f'>");
		body.append("<div class='content'>");
		body.append("<table class='fw'>");
		body.append("<tr>");
		body.append("<td align='center'>");
		body.append("<h3>Resource Onboarding Portal</h3>");
		body.append("</td>");
		body.append("</tr>");
		body.append("</table>");
		body.append("</div>");
		body.append("</td>");
		body.append("<td></td>");
		body.append("</tr>");
		body.append("<tr class='body'>");
		body.append("<td></td>");
		body.append("<td class='container' bgcolor='#FFFFFF'>");
		body.append("<div class='content'>");
		body.append("<table height='250' class='fw'>");
		body.append("<tr>");
		body.append("<td valign='top'>");
		
		return body.toString();
	}
	
	private static String prepareEmailFoot(){
		
		StringBuilder body = new StringBuilder();
		
		body.append("</td>");
		body.append("</tr>");
		body.append("</table>");
		body.append("</div>");
		body.append("</td>");
		body.append("<td></td>");
		body.append("</tr>");
		body.append("<tr class='footer'>");
		body.append("<td></td>");
		body.append("<td class='container' bgcolor='#d9edf7'>");
		body.append("<div class='content'>");
		body.append("<table class='fw'>");
		body.append("<tr>");
		body.append("<td align='center'>");
		body.append("<p>** This is an Auto Generated Mail. Please do not reply to this mail **</p>");
		body.append("</td>");
		body.append("</tr>");
		body.append("</table>");
		body.append("</div>");
		body.append("</td>");
		body.append("<td></td>");
		body.append("</tr>");
		body.append("</table>");
		body.append("</body>");
		body.append("</html>");
		
		return body.toString();
	}
	
	public static String getEmailBodyForLogin(User user){
		StringBuilder body = new StringBuilder();
		
		body.append(prepareEmailHead());
		
		body.append("<p>Hi "+ (StringUtils.isEmpty(user.getName()) ? "User" : user.getName()) +",</p><br/>");
		body.append("<p>You are successfully Logged In to User Portal.</p>");
		body.append("<p>This is just an notification, Please log in and change your password if it was not you.</p>");
		
		body.append(prepareEmailFoot());
		
		return body.toString();
	}
	
	public static String getEmailBodyForUpdateCredentials(User user){
		StringBuilder body = new StringBuilder();
		
		body.append(prepareEmailHead());
		
		body.append("<p>Hi "+ (StringUtils.isEmpty(user.getName()) ? "User" : user.getName()) +",</p><br/>");
		body.append("<p>Your credentials have been updated successfully, log in with following:</p>");
		body.append("<p>Username: "+user.getEmail()+"</p>");
		if(!StringUtils.isEmpty(user.getPassword())){
			body.append("<p>Password: "+user.getPassword()+"</p>");
		}
		body.append(prepareEmailFoot());
		
		return body.toString();
	}
	
	public static String getEmailBodyForForgotCredentials(User user){
		StringBuilder body = new StringBuilder();
		
		body.append(prepareEmailHead());
		
		body.append("<p>Hi "+ (StringUtils.isEmpty(user.getName()) ? "User" : user.getName()) +",</p><br/>");
		body.append("<p>Your credentials have been recovered successfully, log in with following:</p>");
		body.append("<p>Username: "+user.getEmail()+"</p>");
		body.append("<p>Password: "+user.getPassword()+"</p>");
		
		body.append(prepareEmailFoot());
		
		return body.toString();
	}
	
	public static List<String> parseAddress(Address[] a){
		List<String> unsent = new ArrayList<String>();
		if(a != null){
			for(Address a1 : a){
				if(a1 instanceof InternetAddress) {
					unsent.add(((InternetAddress) a1).toString());
				}
			}
		}
		return unsent;
	}

	public static String getEmailBodyForNewTask(User user, List<Task> tasks, boolean offBoard) {
		StringBuilder body = new StringBuilder();

		body.append(prepareEmailHead());

		body.append("<p>Hi "+tasks.get(0).getOwner().getName()+",</p><br/>");
		body.append("<p>New tasks have been assigned to you.</p>");
		body.append("<p class='heading'>TASK DETAILS:</p>");
		body.append("<table cellspacing='0' cellpadding='0' class='box'>");
		body.append("<thead>");
		body.append("<tr>");
		body.append("<th bgcolor='#bce8f1'>Task Description</th>");
		body.append("<th bgcolor='#bce8f1'>Created For</th>");
		body.append("</tr>");
		body.append("</thead>");
		body.append("<tbody>");
		
		for(int i=0; i<tasks.size(); i++){
			body.append("<tr>");
			body.append("<td bgcolor='#f6f6f6' width='350'>"+tasks.get(i).getDescription()+"</td>");
			if(i==0){
				body.append("<td bgcolor='#f6f6f6' width='150' rowspan='"+tasks.size()+"'>"+user.getName()+"</td>");
			}
			body.append("</tr>");
		}
		
		body.append("</tbody>");
		body.append("</table>");
		body.append("<br/>");
		body.append("<p>Please open the Resource Portal and complete all the Pending Tasks.</p>");
		
		body.append(prepareEmailFoot());

		return body.toString();
	}

	public static String getEmailBodyForNewTask(List<User> assignedUsers, Task task, boolean newTask) {
		
		StringBuilder body = new StringBuilder();

		body.append(prepareEmailHead());
		
		body.append("<p>Hi "+task.getOwner().getName()+",</p><br/>");
		if(newTask){
			body.append("<p>Congratulations! you are now the owner of following task.</p><br/>");
		}else{
			body.append("<p>New tasks have been assigned to you.</p><br/>");
		}
		body.append("<p class='heading'>TASK DETAILS:</p>");
		body.append("<table cellspacing='0' cellpadding='0' class='box'>");
		body.append("<thead>");
		body.append("<tr>");
		body.append("<th bgcolor='#bce8f1'>Task Description</th>");
		body.append("<th bgcolor='#bce8f1'>Created For</th>");
		body.append("</tr>");
		body.append("</thead>");
		body.append("<tbody>");
		
		for(int i=0; i<assignedUsers.size(); i++){
			body.append("<tr>");
			if(i==0){
				body.append("<td bgcolor='#f6f6f6' width='350' rowspan='"+assignedUsers.size()+"'>"+task.getDescription()+"</td>");
			}
			body.append("<td width='150' bgcolor='#f6f6f6'>"+assignedUsers.get(i).getName()+"</td>");
			body.append("</tr>");
		}
		
		body.append("</tbody>");
		body.append("</table>");
		body.append("<br/>");
		body.append("<p>Please open the Resource Portal and complete all the Pending Tasks.</p>");
		
		body.append(prepareEmailFoot());
		
		return body.toString();
		
	}
	
	*/
}
