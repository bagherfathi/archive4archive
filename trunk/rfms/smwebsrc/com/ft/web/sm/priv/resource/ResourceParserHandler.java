package com.ft.web.sm.priv.resource;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ft.sm.dto.ResourceDTO;

/**
 * Class comments.
 * 
 * @version 1.0
 */
public class ResourceParserHandler extends DefaultHandler {
    private static final String RESOURCE_TAG = "resource";

    private List resourceList;

    private ResourceDTO resource;

    public List getResourceList() {
        return this.resourceList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (RESOURCE_TAG.equals(qName)) {
            this.resourceList.add(resource);
        }
        super.endElement(uri, localName, qName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        this.resourceList = new ArrayList();
        super.startDocument();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (RESOURCE_TAG.equals(qName)) {
            parseResource(attributes);
        }

        super.startElement(uri, localName, qName, attributes);
    }

    /**
     * Ω‚ŒˆResource
     * 
     * @param attributes
     * @throws SAXException
     */
    private void parseResource(Attributes attributes) throws SAXException {
        resource = new ResourceDTO();
        String title = validateTitle(attributes);
        String resourcePath = validateResourcePath(attributes);
        String url = validate255String("url", attributes);
        String image = validate255String("image", attributes);
        String resourceKey = validate255String("resourceKey", attributes);
        boolean visible = validateBoolean("visible", attributes);
        int type = validateType(attributes);
        int order = validateOrder(attributes);

        resource.setImage(image);
        resource.setResourceKey(resourceKey);
        resource.setTitle(title);
        resource.setResourcePath(resourcePath);
        resource.setUrl(url);
        resource.setType(type);
        resource.setOrder(order);
        resource.setVisible(visible);
    }

    /**
     * @param attributes
     * @return
     */
    private int validateOrder(Attributes attributes) throws SAXException {
        int order = 0;
        String qValue = attributes.getValue("order");
        if (qValue == null || qValue.length() <= 0) {
            throw new SAXException("Attribute order must integer type.");
        }

        try {
            order = Integer.parseInt(qValue);
        } catch (java.lang.NumberFormatException ex) {
            throw new SAXException("Attribute order must integer type.");
        }
        return order;
    }

    /**
     * @param attributes
     * @return
     * @throws SAXException
     */
    private int validateType(Attributes attributes) throws SAXException {
        int type = 0;
        String qValue = attributes.getValue("type");
        if (qValue == null || qValue.length() <= 0) {
            throw new SAXException("Attribute order must be \"1\" or \"2\".");
        }

        try {
            type = Integer.parseInt(qValue);
            if (type != 1 && type != 2) {
                throw new SAXException(
                        "Attribute order must be \"1\" or \"2\".");
            }
        } catch (java.lang.NumberFormatException ex) {
            throw new SAXException("Attribute order must be \"1\" or \"2\".");
        }

        return type;
    }

    /**
     * @param attributes
     * @throws SAXException
     */
    private boolean validateBoolean(String qName, Attributes attributes)
            throws SAXException {
        String qValue = attributes.getValue(qName);
        boolean value = false;

        if (null == qValue || qValue.length() <= 0) {
            throw new SAXException(
                    "Attribute visible must be \"true\" or \"false\".");
        } else {

            if ("true".equalsIgnoreCase(qValue)) {
                value = true;
            } else if ("false".equalsIgnoreCase(qValue)) {
                value = false;
            } else {
                throw new SAXException(
                        "Attribute visible must be \"true\" or \"false\".");
            }
        }

        return value;
    }

    /**
     * @param attributes
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
     * @param attributes
     * @throws SAXException
     */
    private String validateResourcePath(Attributes attributes)
            throws SAXException {
        String resourcePath = attributes.getValue("resourcePath");
        if (null == resourcePath || resourcePath.length() <= 0) {
            throw new SAXException("Attribute resourcePath must not be null.");
        }

        if (resourcePath.length() > 255) {
            throw new SAXException("Attribute title length must less 255.");
        }

        return resourcePath;
    }

    /**
     * @param attributes
     * @return
     * @throws SAXException
     */
    private String validateTitle(Attributes attributes) throws SAXException {
        String title = attributes.getValue("title");
        if (null == title || title.length() <= 0) {
            throw new SAXException("Attribute title must not be null.");
        }

        if (title.length() > 64) {
            throw new SAXException("Attribute title length must less 64.");
        }
        return title;
    }
}
