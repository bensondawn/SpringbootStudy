package com.ljshuoda.Service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源相关注入规则定义
 * 多个数据库的时候，用哪个数据库需要加注解@Primary！
 */
@Configuration
public class DataBaseConfig {
    /**
     * Cta 数据源
     *
     * @return 数据源实例
     */
    @Bean
    @ConfigurationProperties(prefix = "lj.datasource.cta")
    public DataSource ctaDataSource() {
        return new DruidDataSource();
    }

    /**
     * CommonConfig 数据源
     *
     * @return 数据源实例
     */
    @Bean
    @ConfigurationProperties(prefix = "lj.datasource.common-config")
    public DataSource commonConfigDataSource() {
        return new DruidDataSource();
    }

    /**
     * CtaJdbcTemplate 的注入规则
     *
     * @param ctaDataSource 注入的数据源
     * @return JdbcTemplate实例
     */
    @Bean
    public JdbcTemplate ctaJdbcTemplate(@Qualifier("ctaDataSource") DataSource ctaDataSource) {
        return new JdbcTemplate(ctaDataSource);
    }

    /**
     * CommonConfigJdbcTemplate 的注入规则
     *
     * @param commonConfigDataSource 注入的数据源
     * @return JdbcTemplate实例
     */
    @Bean
    public JdbcTemplate commonConfigJdbcTemplate(@Qualifier("commonConfigDataSource") DataSource commonConfigDataSource) {
        return new JdbcTemplate(commonConfigDataSource);
    }

    /**
     * GBIAP 数据源
     * prefix需要跟yaml的配置一致！
     * @return 数据源实例
     */
    @Bean
    @ConfigurationProperties(prefix = "lj.datasource.gbiap")
    public DataSource gbiapDataSource() {
        return new DruidDataSource();
    }

    /**
     * GBIAP CommonConfig 数据源
     *
     * @return 数据源实例
     */
    @Bean
    @ConfigurationProperties(prefix = "lj.datasource.gbiap-common-config")
    public DataSource gbiapCommonConfigDataSource() {
        return new DruidDataSource();
    }

    /**
     * GbiapJdbcTemplate 的注入规则
     *
     * @param gbiapDataSource 注入的数据源
     * @return JdbcTemplate实例
     */
    @Bean
    public JdbcTemplate gbiapJdbcTemplate(@Qualifier("gbiapDataSource") DataSource gbiapDataSource) {
        return new JdbcTemplate(gbiapDataSource);
    }

    /**
     * GbiapCommonConfigJdbcTemplate 的注入规则
     *
     * @param gbiapCommonConfigDataSource 注入的数据源
     * @return JdbcTemplate实例
     */
    @Bean
    public JdbcTemplate gbiapCommonConfigJdbcTemplate(@Qualifier("gbiapCommonConfigDataSource") DataSource gbiapCommonConfigDataSource) {
        return new JdbcTemplate(gbiapCommonConfigDataSource);
    }

}
