package com.utils;
import java.util.*;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleSendMail {
	public SimpleSendMail() {
	}

	public static void main(String[] args) {
		String host = "smtp.qq.com";// 你自己的主机
		String from = "376775994.com";// 要发给谁
		String to = "1193418905@qq.com";// 你的帐号
		String subject = "发送测试";// 主题
		String messageText = "http://ltchina.net";// 正文
		boolean sessionDebug = false;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		//props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		// 系统需要的信息
		Session session = Session.getDefaultInstance(props, null);
		// 一次对话，一个session ，这个session 要货去固定的发送邮件信息
		session.setDebug(sessionDebug);
		try {
			MimeMessage meg = new MimeMessage(session);// 生成消息实例
			meg.setFrom(new InternetAddress(from));// 指定发件人
			InternetAddress[] address = { new InternetAddress(to) };// 生成收件人地址数组
			meg.setRecipients(Message.RecipientType.TO, address);// 指定收件人数组
			meg.setSubject(subject);// 指定主题
			meg.setText(messageText);// 指定正文
			meg.setSentDate(new Date());// 指定发送时间
			meg.saveChanges();// 保存信息
			Transport transport = session.getTransport("smtp");// 产生传输对象
			transport.connect(host, from, to);
			// 连接到你自己的主机
			transport.sendMessage(meg, meg.getAllRecipients());// 开始发送
			System.out.println("send   over");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}