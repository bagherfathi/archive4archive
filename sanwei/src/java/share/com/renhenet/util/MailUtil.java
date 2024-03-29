package com.renhenet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.apache.commons.lang.StringUtils;

public class MailUtil {

	public static Message[] receiveEmail(String mailHost, String userName,
			String password, String subject) {
		String protocol = "pop3";
		Message[] messages = null;
		Session session = Session.getDefaultInstance(System.getProperties(),
				null);
		Folder getFolder = null;
		try {
			Store store = session.getStore(protocol);
			System.out.println("connecting " + mailHost);
			store.connect(mailHost, userName, password);
			System.out.println("connected successfully " + mailHost);

			getFolder = store.getFolder("INBOX");
			getFolder.open(Folder.READ_ONLY);
			if (!StringUtils.isEmpty(subject)) {
				SearchTerm st = new SubjectTerm(subject);
				messages = getFolder.search(st);
			} else {
				messages = getFolder.getMessages();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (getFolder != null) {
					getFolder.close(false);
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return messages;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String testSeq = format.format(new Date());

		Message[] messages = receiveEmail("mail.hzdtv.com", "liqiang001",
				"109800", testSeq);
		for (Message message : messages) {
			MailParser MailParser = new MailParser(message);
			System.err.println("subject: " + MailParser.getSubject());
			System.err.println("sender: " + MailParser.getFromAddress());
			System.err.println("sender: " + MailParser.isNew());
		}

	}

}
