import com.ljshuoda.demo.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication(scanBasePackages = {
        "com.ljshuoda.demo"
})
class BootConfig {

}
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootConfig.class)
public class KafkaDemoTest {

    @Autowired
    private KafkaProducer producer;

    @Test
    public void kafkaSend() throws InterruptedException {
        //发消息
        for (int i = 0; i < 5; i++) {
            producer.send("my kafka information is " + i);
            Thread.sleep(3000);
        }
    }
}
