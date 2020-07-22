package com.jiaxun.util;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class XmlJsonUtils {

    /**
     *
     * 判断xml中是否含有一个指定的节点
     * @param xmlStr  xml主题内容
     * @param nodeString 指定的节点
     * @return
     */
    public static boolean isExistNote(String xmlStr,String nodeString ) {
        if (StringUtils.isBlank(xmlStr)) {
            return false;
        }
        try {
            byte[] byteArray = xmlStr.getBytes("UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(byteArrayInputStream);

            List list = document.selectNodes(nodeString);
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

    public static boolean isExist(String xmlStr,String nodeString){

        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Element elementTemplate = document.getRootElement();
        Element node = (Element)elementTemplate.selectSingleNode("//deptID");
        if(node == null){
            System.out.println("1111111111111");
        }else {
            System.out.println("2222222222222");
            return true;
        }
        return false;
    }

    /**
     * JSON(数组)字符串转换成XML字符串
     */
    public static String json2xml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        String xml = xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        xml = xml.replace("<o>", "").replace("</o>", "");
        xml = xml.replaceAll("\r\n", "").concat("\r\n");
        return xml;
    }
    /**
     * xml 转 json
     */
    public static String xml2json(String xmlString) {
        JSONObject jsonObject = XML.toJSONObject(xmlString);
        return jsonObject.toString(3);
    }
}
