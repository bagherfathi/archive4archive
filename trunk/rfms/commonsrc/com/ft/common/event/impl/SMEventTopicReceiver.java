package com.ft.common.event.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.NamingException;

import org.springframework.jndi.JndiTemplate;

import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventReceiver;
import com.ft.common.event.SMEventSupport;


public class SMEventTopicReceiver implements SMEventReceiver, MessageListener {
	private SMEventSupport smEventSupport;

	private JndiTemplate jndiTemplate;

	private String factoryName;

	private String topicName;

	private TopicConnection connection;

	private TopicSession session;

	public void init() {

		TopicConnectionFactory factory;
		try {
			factory = (TopicConnectionFactory) jndiTemplate.lookup(factoryName);
			connection = factory.createTopicConnection();
			session = connection.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);      
            Topic dest = (Topic) jndiTemplate.lookup(topicName);
            TopicSubscriber receiver = session.createSubscriber(dest);
            receiver.setMessageListener(this);
            connection.start();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}

	public void setSmEventSupport(SMEventSupport smEventSupport) {
		this.smEventSupport = smEventSupport;
	}

	public void receive(SMEvent event) {
		smEventSupport.fireEvent(event);
	}

	public void onMessage(Message msg) {
		if (msg instanceof ObjectMessage) {
			ObjectMessage message = (ObjectMessage) msg;
			Object obj;
			try {
				obj = message.getObject();
				if (obj instanceof SMEvent) {
					receive((SMEvent) obj);
				}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

    /**
     * @param factoryName the factoryName to set
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * @param topicName the topicName to set
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
