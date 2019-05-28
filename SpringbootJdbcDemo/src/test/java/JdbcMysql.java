import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class JdbcMysql {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://172.16.0.133:3306/common_config_wh";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "gsmrlab";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT ETCS_ID, Kilo FROM mtBailseTable";
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            Vector<Map<String,Long>> bailseVector = new Vector<>();
            Map<String ,Long> map = new HashMap<>();
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                long etesId  = rs.getInt("ETCS_ID");
                long kilo = rs.getInt("Kilo");

                map.put("ETCS_ID",etesId);
                map.put("Kilo",kilo);
                bailseVector.add(map);

                // 输出数据
                System.out.print("ETCS_ID: " + bailseVector.get(count).get("ETCS_ID"));
                System.out.print(", Kilo: " + bailseVector.get(count).get("Kilo"));
                count++;
                System.out.print("count = " + count + "\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
