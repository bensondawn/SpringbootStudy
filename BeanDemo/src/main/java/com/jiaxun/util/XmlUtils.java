package com.jiaxun.util;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public class XmlUtils {

    /**
     *
     * 判断xml中是否含有一个指定的节点
     * @param str  xml主题内容
     * @param nodeString 指定的节点
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isExistNote(String str,String nodeString ) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        try {
            byte[] byteArray = str.getBytes("UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(byteArrayInputStream);
            List list = doc.selectNodes("//"+nodeString);
            //"//"的意思表示在任意层级下发现nodeString，不加"//"只会在根节点"nodeString"
            if(list.size()!=0){
                return true;
            }else{
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (DocumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExistEle(String str){
        if (StringUtils.isBlank(str)) {
            return false;
        }
        try {
            byte[] byteArray = str.getBytes("UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(byteArrayInputStream);

            Element root = document.getRootElement();
            Element contactsEle = root.element("contacts");
            for(Iterator iteratorHotelGeo = contactsEle.elementIterator("contactsList"); iteratorHotelGeo.hasNext();) {
                Element HotelGeo = (Element) iteratorHotelGeo.next();
                Document d = HotelGeo.getDocument();
                if (d.selectNodes("//*").size() > 0) {
                    System.out.println("存在子节点");
                    return true;
                }
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (DocumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}
