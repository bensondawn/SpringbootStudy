package io.seata.sample.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/*
该示例是测试使用mysql和mybatis时，如何自行代理数据源和如何切换XA模式。
1、切换seata依赖为手动代理数据源依赖。
2、修改排除Spring的动态数据源类：@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
3、修改application.yml：配置  spring.cloud.alibaba.seata.tx-service-group: fsp_tx_group(分组名称)
4、该方式不支持yml配置seata，所以resource下需要导入file.conf和register.conf，并修改。
5、增加数据源配置类DataSourceConfiguration。
6、注意：
    自动代理数据源不需要file.conf和register.conf两个文件，使用yml配置即可！
    开启自动代理数据源时要把该处的手动代理数据源配置关闭，否则会有冲突！
 */
//@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSource(DataSource druidDataSource){
        // DataSourceProxy for AT mode
        return new DataSourceProxy(druidDataSource);

        // DataSourceProxyXA for XA mode
        // return new DataSourceProxyXA(druidDataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy)throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
}
