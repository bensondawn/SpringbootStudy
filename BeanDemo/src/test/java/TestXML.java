
import com.alibaba.fastjson.JSON;
import com.jiaxun.BeanDemo;
import com.jiaxun.Utils.BeanMapUtils;
import com.jiaxun.Utils.MD5Utils;
import com.jiaxun.model.Student;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeanDemo.class)
public class TestXML {

    String[] bufPID = {"YuCheng/20200719/p_1283675681925652480_1283682376433287168_20200719115245.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283680893398695936_20200719115507.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681628144287744_20200719114935.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681671605665792_20200719114950.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681807845048320_20200719115906.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283682180110499840_20200719115338.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681034209869824_20200719115418.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681034209869824_20200719115808.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681714484035584_20200719115130.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681572854972416_20200719115035.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681484543901696_20200719115139.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283682615567335424_20200719115138.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681807845048320_20200719115906.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681807845048320_20200719115954.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283682180110499840_20200719115338.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283682180110499840_20200719115948.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283680893398695936_20200719115507.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283680893398695936_20200719115623.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681318407520256_20200719115316.jpg",
            "YuCheng/20200719/p_1283675681925652480_1283681852250144768_20200719115316.jpg"};

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        //MD5加密后bytes长度16转换成32位16进制字符串
        for (int i = 0; i < bytes.length; i++) {
            /**
             * 在32位的电脑中数字都是以32格式存放的，如果是一个byte(8位)类型的数字，
             * 他的高24位里面都是随机数字，低8位才是实际的数据。
             * java.lang.Integer.toHexString() 方法的参数是int(32位)类型.
             * 如果输入一个byte(8位)类型的数字，这个方法会把这个数字的高24为也看作有效位，
             * 这就必然导致错误，使用& 0XFF操作，可以把高24位置0以避免这样错误.
             *
             * 0xFF = 1111 1111　 低8位为1，高位都为0
             * 故 &0xFF 可将数字的高位都置为0，低8位不变
             *
             * */
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    @Test
    public void xmlTest() {
        String xmlStr = "<?xml version='1.0' encoding='UTF-8'?><root><RESPONSE_CODE>200</RESPONSE_CODE><RESPONSE_MSG>影像统计成功,没有该批次的影像信息</RESPONSE_MSG></root>";
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element elementTemplate = document.getRootElement();
        Element node = (Element) elementTemplate.selectSingleNode("//RESPONSE_CODE");
        if (node == null) {
            System.out.println("1111111111111");
        } else {
            System.out.println("2222222222222");
        }
    }

    @Test
    public void HashCodeTest(){
        String picPath = "LiaoChengBeiYiChang/20191105/p_1152085324000411648_1152092201153224704_20191101172236.jpg";
        int picCode = picPath.hashCode() & Integer.MAX_VALUE;
        int n = picCode % 6;
        System.out.println("!!!!!!!!" + picCode + "**************" + n);
        String lastStr = picPath.substring(picPath.indexOf(".") + 1);
        System.out.println(lastStr);
    }

    @Test
    public void HashCodeTest1(){
        String str = "oper01";
        try {
            //MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
            //信息摘要是安全的单向哈希函数，它接收 任意大小的数据，并输出固定长度的哈希值。
            //MessageDigest 对象开始被初始化。
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            //通过使用 update 方法处理数据
            mDigest.update(str.getBytes());
            //调用 digest 方法之一完成哈希计算同时将Byte数组转换成16进制
            System.out.println(bytesToHexString(mDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String encrypt(String plaintText) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        char[] charArrays = plaintText.toCharArray();
        byte[] byteArrays = new byte[charArrays.length];

        for (int i = 0; i < charArrays.length; i++) {
            byteArrays[i] = (byte) charArrays[i];
        }
        byte[] md5Bytes = md5.digest(byteArrays);
        System.out.println(md5Bytes.length);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    @Test
    // 将字符串转成hash值
    public void toHash() throws NoSuchAlgorithmException {
        String key = "oper01";
        int arraySize = 11113; // 数组大小一般取质数
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { // 从字符串的左边开始计算
            int letterValue = key.charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            // 就代表a的值，同理b=2；
            hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
        }
        System.out.println(hashCode);

        int keyCode = key.hashCode() & Integer.MAX_VALUE;
        System.out.println(keyCode % arraySize);

        for (String pid : bufPID){
            BigInteger bigInteger = MD5Utils.MD5_32bit3(pid);
            System.out.println(pid);
            System.out.println(bigInteger.toString(16).toUpperCase());
            System.out.println(bigInteger.toString(10).toUpperCase());

            BigInteger a = new BigInteger("3");
            System.out.println(bigInteger.mod(a).intValue());
//            System.out.println(bigInteger.remainder(a).intValue());
        }
    }

    @Test
    public void xml2json() throws JSONException {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<contacts version=\"0\" xmlns=\"urn:ietf:params:xml:ns:contacts\">\n" +
                "  <contactsList contactsID=\"2\" contactsName=\"济南电务段\">\n" +
                "    <dept deptid=\"0\" deptName=\"济南电务段\" pos=\"58048\">\n" +
                "      <dept deptid=\"3\" deptName=\"1376699209079341056\" superiorDept=\"0\" pos=\"0\">\n" +
                "        <user userID=\"132\">\n" +
                "          <deptID>3</deptID>\n" +
                "          <pos>0</pos>\n" +
                "          <channelid>24000000001327002001</channelid>\n" +
                "          <channelName>101A</channelName>\n" +
                "          <callNum>24000000001327002001</callNum>\n" +
                "          <cooperateNum/>\n" +
                "          <ptzLevel>254</ptzLevel>\n" +
                "          <talkLevel>254</talkLevel>\n" +
                "          <restart>0</restart>\n" +
                "          <modify>0</modify>\n" +
                "          <preset>0</preset>\n" +
                "          <status>offline</status>\n" +
                "        </user>\n" +
                "      </dept>\n" +
                "      <dept deptid=\"4\" deptName=\"1376725556606550016\" superiorDept=\"0\" pos=\"1\">\n" +
                "        <user userID=\"359\">\n" +
                "          <deptID>4</deptID>\n" +
                "          <pos>0</pos>\n" +
                "          <channelid>24000000001327008152</channelid>\n" +
                "          <channelName>10J1</channelName>\n" +
                "          <callNum>24000000001327008152</callNum>\n" +
                "          <cooperateNum/>\n" +
                "          <ptzLevel>254</ptzLevel>\n" +
                "          <talkLevel>254</talkLevel>\n" +
                "          <restart>0</restart>\n" +
                "          <modify>0</modify>\n" +
                "          <preset>0</preset>\n" +
                "          <status>online</status>\n" +
                "        </user>\n" +
                "      </dept>\n" +
                "    </dept>\n" +
                "  </contactsList>\n" +
                "</contacts>";

//        Object xmlObject = XsteamUtil.toBean(Contacts.class, xml);
//        System.out.println(JSON.toJSONString(xmlObject));

        XMLSerializer xmlSerializer = new XMLSerializer();
        net.sf.json.JSON json = xmlSerializer.read(xml);
        String jsonStr = json.toString().replace("@","");
        org.json.JSONObject jsonObject = new JSONObject(jsonStr);
        String jsonPrettyPrintString = jsonObject.toString(4);
        // 输出格式化后的json
        System.out.println(jsonPrettyPrintString);

//        Document document;
//        try {
//            document = DocumentHelper.parseText(xmlStr);
//            org.json.JSONObject xmlJSONObj = org.json.XML.toJSONObject(document.asXML());
//            //设置缩进
//            String jsonPrettyPrintString = xmlJSONObj.toString(4);
//            //输出格式化后的json
//            System.out.println(jsonPrettyPrintString);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void setTest(){
        Set<String> sb = new HashSet<>();
        sb.add("aaa");
        sb.add("bbb");
        sb.add("ccc");
        if (sb.contains("ccc")){
            System.out.println("1111111111111");
        }

        Student student = new Student();
        student.setName("tinghe");
        student.setAge(11);
        student.setText("hello world");
        student.setUserId("10000");

        try {
            Map<String,Object> stuMap = BeanMapUtils.bean2map(student);
            System.out.println(JSON.toJSONString(stuMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stuStr = "{\n" +
                "    \"id\":\"001\",\n" +
                "    \"name\":\"张三哈\",\n" +
                "    \"sex\":\"男\",\n" +
                "    \"age\":15,\n" +
                "    \"score\":99,\n" +
                "    \"role\":\"班长\"\n" +
                "}";

        Student student1 = JSON.parseObject(stuStr, Student.class);

        System.out.println(student1.getName() + ":" + student1.getAge());
    }
}