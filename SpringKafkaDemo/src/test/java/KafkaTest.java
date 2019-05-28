import com.ljshuoda.demo.KafkaProducer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
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
public class KafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void kafkaSend() throws InterruptedException {
        //发消息
        for (int i = 0; i < 5; i++) {

            String str = "My message count: " + i;
            kafkaProducer.sendMessage(str);
            Thread.sleep(3000);
        }
    }

}