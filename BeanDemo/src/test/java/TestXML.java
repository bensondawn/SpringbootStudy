import com.jiaxun.BeanDemo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeanDemo.class)
public class TestXML {

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
}