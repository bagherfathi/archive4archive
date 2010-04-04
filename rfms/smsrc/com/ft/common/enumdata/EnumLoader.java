package com.ft.common.enumdata;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ft.commons.web.context.InitializationException;
import com.ft.commons.web.context.Loader;

/**
 * 装载系统中枚举类型数据到WEB应用上下文中
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
	 * 初始化WEB应用上下文
	 */
	public void initContext(ServletContext context)
            throws InitializationException {
        // 装载所有枚举类型数据
        logger.info("Begin load enum to context");
        try {
            enumRepository.loadEnumDatas();
        } catch (Exception e) {
            logger.error("Load enum data error",e);
            throw new InitializationException(e);
        }
        
        logger.info("End loaded enum.");

        // 将枚举类型数据仓库设置到WEB应用上下文中，便于页面调用
        context.setAttribute(enumRepository.getName(), enumRepository);
        
    }

	/**
	 * 销毁方法
	 */
	public void destroyContext(ServletContext context) {
		context.removeAttribute(enumRepository.getName());
	}

}
