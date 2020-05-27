package com.jiaxun.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiaxun.model.Contacts;
import com.jiaxun.model.ContactsList;
import com.jiaxun.service.XsteamUtil;
import com.jiaxun.util.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/live")
public class GetUrlLiveListController {

    Logger logger = LoggerFactory.getLogger(GetUrlLiveListController.class);

    @PostMapping("/list")
    public Object getLiveList(@RequestBody String request) throws Exception {

        JSONObject jsonObject = JSON.parseObject(request);
        String url = jsonObject.get("url").toString();
        logger.info(url);

        StringBuffer result = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url ;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection urlConnection = realUrl.openConnection();
            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
            // 建立实际的连接
            urlConnection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = urlConnection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
                result.append("\r\n");
            }
            logger.info(result.toString());
        } catch (Exception e) {
            logger.info("Send GET Exception:" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
//        ContactsList contactsList = (ContactsList) XmlBuilder.xmlStrToObject(ContactsList.class, result.toString());
//        return contactsList.getDeptFirsts();
        Object xmlObject = null;
        if (XmlUtils.isExistNote(result.toString(),"user")){
            logger.info("11111111111111111111111111111");
            xmlObject = XsteamUtil.toBean(Contacts.class, result.toString());
        }
        return JSONObject.toJSON(xmlObject);
    }
}
