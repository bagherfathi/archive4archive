package com.ft.common.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Class comments.
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class XmlParser {
	/**
	 * 解析XML内容
	 * 
	 * @param content
	 * @param handler
	 * @throws ParserConfigurationException
	 * @throws Exception
	 */
	public void parse(String content, DefaultHandler handler)
			throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		StringReader reader = new StringReader(content);
		SAXParser saxParser = factory.newSAXParser();
		InputSource input = new InputSource(reader);
		saxParser.parse(input, handler);
	}

	/**
	 * 解析XML流
	 * 
	 * @param inputStream
	 * @param handler
	 * @throws ParserConfigurationException
	 * @throws Exception
	 */
	public void parse(InputStream inputStream, DefaultHandler handler)
			throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		InputSource input = new InputSource(inputStream);
		saxParser.parse(input, handler);
	}
}
