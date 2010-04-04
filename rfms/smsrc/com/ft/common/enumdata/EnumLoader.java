package com.ft.common.enumdata;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ft.commons.web.context.InitializationException;
import com.ft.commons.web.context.Loader;

/**
 * װ��ϵͳ��ö���������ݵ�WEBӦ����������
 * 
 * @spring.bean id="enumLoader"
 * 
 * @spring.property name="enumRepository" ref="enumRepository"
 * 
 * @version 1.0
 */
public class EnumLoader implements Loader {
	private static final Log logger = LogFactory.getLog(EnumLoader.class);

	private EnumRepository enumRepository;
	
	public void setEnumRepository(EnumRepository enumRepository) {
		this.enumRepository = enumRepository;
	}

	/**
	 * ��ʼ��WEBӦ��������
	 */
	public void initContext(ServletContext context)
            throws InitializationException {
        // װ������ö����������
        logger.info("Begin load enum to context");
        try {
            enumRepository.loadEnumDatas();
        } catch (Exception e) {
            logger.error("Load enum data error",e);
            throw new InitializationException(e);
        }
        
        logger.info("End loaded enum.");

        // ��ö���������ݲֿ����õ�WEBӦ���������У�����ҳ�����
        context.setAttribute(enumRepository.getName(), enumRepository);
        
    }

	/**
	 * ���ٷ���
	 */
	public void destroyContext(ServletContext context) {
		context.removeAttribute(enumRepository.getName());
	}

}
