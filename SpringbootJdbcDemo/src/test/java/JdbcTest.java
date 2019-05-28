import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootConfig.class)
public class JdbcTest {

    @Autowired
    @Qualifier("ctaJdbcTemplate")
    private JdbcTemplate ctaJdbcTemplate;

    @Autowired
    @Qualifier("commonConfigJdbcTemplate")
    private JdbcTemplate commonConfigJdbcTemplate;

    @Autowired
    @Qualifier("gbiapJdbcTemplate")
    private JdbcTemplate gbiapJdbcTemplate;

    @Test
    public void test1() {
        String sql = "select COUNT(*) from " + "mt_C3TimeOut_AlarmInfo";
        int rowCount = ctaJdbcTemplate.queryForObject(sql,Integer.class);

        System.out.println("rowCount = " + rowCount);
    }
    @Test
    public void test2() {
        String sql = "select COUNT(*) from " + "mtAbisDeviceTable";
        int rowCount = commonConfigJdbcTemplate.queryForObject(sql,Integer.class);

        System.out.println("rowCount = " + rowCount);
    }

    @Test
    public void test3() {

        String sql = "select GetData from " + "mt_C3TimeOut_AlarmInfo" + " where AffairID = " + 372;
        String str = ctaJdbcTemplate.queryForObject(sql, String.class);
        System.out.println(str);
        JSONObject jsonPrePublicMsg = JSONObject.parseObject(str);
        System.out.println(JSON.toJSONString(jsonPrePublicMsg.get("0")));
        System.out.println(JSON.toJSONString(jsonPrePublicMsg.get("1")));
        System.out.println(JSON.toJSONString(jsonPrePublicMsg.get("2")));
        System.out.println(JSON.toJSONString(jsonPrePublicMsg.get("3")));

        if (jsonPrePublicMsg.get("0").equals(1)) {
            System.out.println("111111111111111111111111");
        }

        if (jsonPrePublicMsg.get("1").equals(1)) {
            System.out.println("222222222222222222222222");
        }

    }

    @Test
    public void test4(){
        String strSql = "select COUNT(*) from mt_FaultStatisticsInfo";
        int rowCount = gbiapJdbcTemplate.queryForObject(strSql,Integer.class);

        System.out.println("rowCount = " + rowCount);
    }

}