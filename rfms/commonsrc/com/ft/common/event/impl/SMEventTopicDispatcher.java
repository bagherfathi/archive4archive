package com.ft.common.event.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate102;
import org.springframework.jms.core.MessageCreator;

import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventDispatcher;

/**
 * 
 * 对于JMS 的Topic进行事件的接收
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 * 
 */
public class SMEventTopicDispatcher implements SMEventDispatcher {
	private JmsTemplate102 jmsTemplate;

	public void dispatcher(final SMEvent smEvent) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(smEvent);
			}
		});

	}

	public void setJmsTemplate(JmsTemplate102 jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
