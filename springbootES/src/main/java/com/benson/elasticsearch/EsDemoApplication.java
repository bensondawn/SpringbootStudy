package com.benson.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * ElasticSearch是一个NoSQL类型的数据库，本身是弱化了对关系的处理，因为像lucene，es，solr这样的全文检索框架对性能要求都是比较高的，
 * 一旦出现join这样的操作，性能会非常差，所以在使用搜索框架时，我们应该避免把搜索引擎当做关系型数据库用。
 * es天生对json数据支持的非常完美，只要是标准的json结构的数据，无论多么复杂，无论是嵌套多少层，都能存储到es里面，进而能够查询和分析，检索。
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.benson.elasticsearch") // 这里basePackages对应你dao所在包
public class EsDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsDemoApplication.class, args);
    }
}
