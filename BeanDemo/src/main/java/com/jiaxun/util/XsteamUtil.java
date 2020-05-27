package com.jiaxun.util;

import com.thoughtworks.xstream.XStream;

public class XsteamUtil {
    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{clazz});
        xStream.processAnnotations(clazz);
        xStream.autodetectAnnotations(true);
        xmlObject= xStream.fromXML(xml);
        return xmlObject;
    }
}