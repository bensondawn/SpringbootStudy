package com.jiaxun.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiaxun.Utils.MD5Utils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.json.JSONException;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/live")
public class GetUrlLiveListController {

    Logger logger = LoggerFactory.getLogger(GetUrlLiveListController.class);

    String [] ipAddress = {"172.16.1.168","172.16.1.163","172.16.1.119"};

    int port = 9000;

    private static final String MACHINE_COUNT = "3";

    @PostMapping("/list")
    public JSONObject getLiveList(@RequestBody String request) {

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
        Document document = null;
        try {
            document = DocumentHelper.parseText(result.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        org.json.JSONObject xmlJsonObject = null;
        try {
            xmlJsonObject = XML.toJSONObject(document.asXML());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObject = JSON.parseObject(xmlJsonObject.toString());
        return jsonObject;
    }

    @PostMapping("/get-alarm-picture-machine")
    public List<StringBuilder> GetAlarmPictureMachine(@RequestBody String[] picPaths){

        List<StringBuilder> pathList = new ArrayList<>();
        for (String picPath : picPaths){
            StringBuilder picAddr = new StringBuilder();
            BigInteger bigInteger = MD5Utils.MD5_32bit3(picPath);
            BigInteger mCount = new BigInteger(MACHINE_COUNT);
            int mNum = bigInteger.mod(mCount).intValue();
            String ip = ipAddress[mNum];
            picAddr.append(ip).append(":").append(port).append("/").append(picPath);
            System.out.println(picAddr);
            pathList.add(picAddr);
        }
        return pathList;
    }

    @GetMapping("/get-photo")
    public void getPhoto(@RequestParam("photoAddr") String photoAddr, HttpServletResponse response){

        photoAddr = photoAddr.replace("\"","");
        try {
            URL url = new URL(photoAddr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());

            byte[] bytes = new byte[1024];
            OutputStream os = response.getOutputStream();
            int len;
            while ((len = bis.read(bytes)) > 0){
                os.write(bytes,0,len);
            }
            bis.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/get-photo1")
    public void getPhoto1(@RequestBody String photoAddr, HttpServletResponse response){

        photoAddr = photoAddr.replace("\"","");
        System.out.println(photoAddr);

        try {
            URL url = new URL(photoAddr);
            String fileType = photoAddr.substring(photoAddr.lastIndexOf("."));
            System.out.println(fileType);

            InputStream is = url.openStream();

            byte[] bytes = new byte[1024];

            OutputStream os = response.getOutputStream();
            int len;
            while ((len = is.read(bytes)) > 0){
                os.write(bytes,0,len);
            }
            is.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
