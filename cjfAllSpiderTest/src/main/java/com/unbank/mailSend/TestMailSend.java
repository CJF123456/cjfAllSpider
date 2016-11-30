package com.unbank.mailSend;

public class TestMailSend {

	
	
	 public static void main(String[] args) {  
		 TestMailSend.send_163();  
	    }  
	      
	    // 163邮箱  
	    public static void send_163() {  
	        // 这个类主要是设置邮件  
	        MailSenderInfo mailInfo = new MailSenderInfo();  
	        mailInfo.setMailServerHost("smtp.163.com");  
	        mailInfo.setMailServerPort("25");  
	        mailInfo.setValidate(true);  
	        mailInfo.setUserName("18310068076@163.com"); // 实际发送者  
	        mailInfo.setPassword("cjf.mail");// 您的邮箱密码  
	        mailInfo.setFromAddress("18310068076@163.com"); // 设置发送人邮箱地址  
	        mailInfo.setToAddress("1378022176@qq.com"); // 设置接受者邮箱地址  
	        mailInfo.setSubject("Test邮件发送项目");  
	        mailInfo.setContent("设置邮箱内容<b>h6</b>");  
	        // 这个类主要来发送邮件  
	        SimpleMailSender sms = new SimpleMailSender();  
	        sms.sendTextMail(mailInfo); // 发送文体格式  
	        sms.sendHtmlMail(mailInfo); // 发送html格式  
	    }  
}
