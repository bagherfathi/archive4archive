package com.ft.web.sm.data.enumdata;

import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumEntryDTO;
import com.ft.sm.dto.EnumGroupDTO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 解析系统数据组
 * 
 * @version 1.0
 */
public class EnumDataGroupParserHandler extends DefaultHandler {

    private static final String ENUM_DATA_GROUP_TAG = "enumDataGroup";

    private static final String ENUM_DATA_TAG = "enumData";

    private static final String ENUM_DATA_ENTRY_TAG = "enumDataEntry";

    private EnumGroupDTO enumDataGroup;

    private EnumDTO enumData;

    private EnumEntryDTO enumDataEntry;

    public EnumGroupDTO getEnumDataGroup() {
        return this.enumDataGroup;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        /*
         * if (ENUM_DATA_TAG.equals(qName)) {
         * this.enumDataGroup.addEnumData(enumData); } if
         * (ENUM_DATA_ENTRY_TAG.equals(qName)) {
         * this.enumData.addEnumEntry(enumDataEntry); }
         */
        super.endElement(uri, localName, qName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (ENUM_DATA_GROUP_TAG.equals(qName)) {
            parseEnumDataGroup(attributes);
        }
        if (ENUM_DATA_TAG.equals(qName)) {
            parseEnumData(attributes);
        }
        if (ENUM_DATA_ENTRY_TAG.equals(qName)) {
            parseEnumDataEntry(attributes);
        }

        super.startElement(uri, localName, qName, attributes);
    }

    /**
     * 解析EnumDataGroup
     * 
     * @param attributes
     * @throws SAXException
     */
    private void parseEnumDataGroup(Attributes attributes) throws SAXException {
        enumDataGroup = new EnumGroupDTO();
        String groupName = validate32String("groupName", attributes);
        String desc = validate255String("description", attributes);
        enumDataGroup.setGroupName(groupName);
        enumDataGroup.setDescription(desc);
    }

    /**
     * 解析EnumData
     * 
     * @param attributes
     * @throws SAXException
     */
    private void parseEnumData(Attributes attributes) throws SAXException {
        enumData = new EnumDTO();
        String enumName = validate32String("enumName", attributes);
        String enumCode = validate32String("enumCode", attributes);
        String enumDesc = validate255String("enumDesc", attributes);
        enumData.setEnumName(enumName);
        enumData.setEnumCode(enumCode);
        enumData.setEnumDesc(enumDesc);
    }

    /**
     * 解析EnumDataEntry
     * 
     * @param attributes
     * @throws SAXException
     */
    private void parseEnumDataEntry(Attributes attributes) throws SAXException {
        enumDataEntry = new EnumEntryDTO();
        String label = validate128String("label", attributes);
        String value = validate128String("value", attributes);
        int status = validateStatus(attributes);
        enumDataEntry.setLabel(label);
        enumDataEntry.setValue(value);
        enumDataEntry.setStatus(status);
    }

    /**
     * 验证长度32的字符串
     * 
     * @param qName
     * @param attributes
     * @return
     * @throws SAXException
     */
    private String validate32String(String qName, Attributes attributes)
            throws SAXException {
        String title = attributes.getValue(qName);
        if (null == title || title.length() <= 0) {
            throw new SAXException("Attribute " + qName + " must not be null.");
        }

        if (title.length() > 32) {
            throw new SAXException("Attribute " + qName
                    + " length must less 32.");
        }
        return title;
    }

    /**
     * 验证长度255的字符串
     * 
     * @param qName
     * @param attributes
     * @return
     * @throws SAXException
     */
    private String validate255String(String qName, Attributes attributes)
            throws SAXException {
        String value = attributes.getValue(qName);

        if (value != null && value.length() > 255) {
            throw new SAXException("Attribute " + qName
                    + " length must less 255.");
        }

        if (value == null) {
            value = "";
        }

        return value;
    }

    /**
     * 验证长度128的字符串
     * 
     * @param qName
     * @param attributes
     * @return
     * @throws SAXException
     */
    private String validate128String(String qName, Attributes attributes)
            throws SAXException {
        String title = attributes.getValue(qName);
        if (null == title || title.length() <= 0) {
            throw new SAXException("Attribute " + qName + " must not be null.");
        }

        if (title.length() > 128) {
            throw new SAXException("Attribute " + qName
                    + " length must less 128.");
        }
        return title;
    }

    /**
     * 验证status字段
     * 
     * @param attributes
     * @return
     * @throws SAXException
     */
    private int validateStatus(Attributes attributes) throws SAXException {
        int status = 0;
        String qValue = attributes.getValue("status");
        if (qValue == null || qValue.length() <= 0) {
            throw new SAXException("Attribute status must integer type.");
        }

        if (qValue.length() > 2) {
            throw new SAXException("Attribute status length must less 2.");
        }

        try {
            status = Integer.parseInt(qValue);
        } catch (java.lang.NumberFormatException ex) {
            throw new SAXException("Attribute status must integer type.");
        }
        return status;
    }
}
