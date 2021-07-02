package com.jiaxun.Utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class XsteamUtil {
    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xStream = new XStream();
        // 尽量限制所需的最低权限 这条语句解决该问题
        xStream.addPermission(AnyTypePermission.ANY);
        // 对象设置默认安全防护
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{clazz});
        xStream.processAnnotations(clazz);
        xStream.autodetectAnnotations(true);
        xmlObject= xStream.fromXML(xml);
        return xmlObject;
    }
}
