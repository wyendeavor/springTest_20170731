package com.wy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: jdk自带的JAXB xml处理工具类
 *
 * @author wangyuan
 * Date: Created at 2019-01-29 09:48
 */
public class JaxbXmlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JaxbXmlUtil.class);

    private static ConcurrentHashMap<String, JAXBContext> JAXBCONTEXT_CONCURRENTHASHMAP = new ConcurrentHashMap<String, JAXBContext>();

    /**
     * 将特定的xml字符串转换成对应的Class对象
     *
     * @param clazz     class类型的对象
     * @param objXmlStr 需要转换的xml字符串
     * @param <T>       转换后的目标对象类型
     * @return clazz类的实例对象，如果发生异常，则返回null
     */
    public static <T> T parseObjectFromXmlStr(Class<T> clazz, String objXmlStr) {
        JAXBContext jaxbContext = getJaxbContextByClassName(clazz);

        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T xmlObj = (T) unmarshaller.unmarshal(new StringReader(objXmlStr));
            return xmlObj;
        } catch (Exception ex) {
            LOGGER.info("#JaxbXmlUtil.parseObjectFromXmlStr exception, objXmlStr:", objXmlStr,
                    ", exception:", ex);
        }
        return null;
    }


    /**
     * 根据class对象获取JAXBContext
     *
     * @param clazz class类对象
     * @return JAXBContext
     */
    private static JAXBContext getJaxbContextByClassName(Class clazz) {
        String fullQualifiedName = clazz.getName();

        JAXBContext jaxbContext = JAXBCONTEXT_CONCURRENTHASHMAP.get(fullQualifiedName);

        if (jaxbContext != null) {
            return jaxbContext;
        }

        try {
            jaxbContext = JAXBContext.newInstance(clazz);
            JAXBCONTEXT_CONCURRENTHASHMAP.put(fullQualifiedName, jaxbContext);
        } catch (Exception ex) {
            LOGGER.error("#JAXBContext.newInstance error, className:" + clazz.getName() + ", exception:", ex);
        }

        return jaxbContext;
    }


}