package com.benson.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.benson.elasticsearch.model.Employee;
import com.benson.elasticsearch.model.EsPage;
import com.benson.elasticsearch.utils.ElasticsearchUtil;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/es")
public class EsController {

    /**
     * 测试索引
     */
    private String indexName = "megacorp";

    /**
     * 类型
     */
    private String esType = "employee";

    /**
     * 创建索引
     * http://127.0.0.1:8080/es/createIndex
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createIndex")
    public String createIndex(HttpServletRequest request, HttpServletResponse response) {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    /**
     * 插入记录
     *
     * @return
     */
    @RequestMapping("/insertJson")
    public String insertJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "001");
        jsonObject.put("name", "张三");
        jsonObject.put("age", 11);
        jsonObject.put("score", 99);
        jsonObject.put("role", "班长");
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }

    /**
     * 插入记录
     *
     * @return
     */
    @RequestMapping("/insertModel")
    public String insertModel(@RequestBody Employee employee) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(employee);
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }

    /**
     * 删除记录
     * http://127.0.0.1:9000/es/getData?id=2018-04-25%2016:33:44
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, esType, id);
            return "删除id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 更新数据
     * http://127.0.0.1:9000/es/getData?id=2018-04-25%2016:33:44
     * @return
     */
    @RequestMapping("/update")
    public String update(String id) {
        if (StringUtils.isNotBlank(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("name", "王五");
            jsonObject.put("sex", "男");
            jsonObject.put("age",12 );
            ElasticsearchUtil.updateDataById(jsonObject, indexName, esType, id);
            return "id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 获取数据
     * http://127.0.0.1:9000/es/getData?id=2018-04-25%2016:33:44
     * @param id
     * @return
     */
    @RequestMapping("/getData")
    public String getData(String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, esType, id, null);
            return JSONObject.toJSONString(map);
        } else {
            return "id为空";
        }
    }

    /**
     * 查询数据
     * 模糊查询
     *
     * @return
     */
    @RequestMapping("/queryMatchData")
    public String queryMatchData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            //不进行分词搜索
            boolQuery.must(QueryBuilders.matchPhraseQuery("name", "cici"));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("name", "cici"));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.
                searchListData(indexName, esType, boolQuery, 10, "name", null, "name");
        return JSONObject.toJSONString(list);
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     *
     * @return
     */
    @RequestMapping("/queryWildcardData")
    public String queryWildcardData() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name.keyword", "cici");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 正则查询
     *
     * @return
     */
    @RequestMapping("/queryRegexpData")
    public String queryRegexpData() {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("first_name.keyword", "m--[0-9]{1,11}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数字范围数据
     *
     * @return
     */
    @RequestMapping("/queryIntRangeData")
    public String queryIntRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from(24)
                .to(25));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询日期范围数据
     *
     * @return
     */
    @RequestMapping("/queryDateRangeData")
    public String queryDateRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from("20")
                .to("50"));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询分页
     *
     * @param startPage 第几条记录开始
     *                  从0开始
     *                  第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                  第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize  每页大小
     * @return
     */
    @RequestMapping("/queryPage")
    public String queryPage(String startPage, String pageSize) {
        if (StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.rangeQuery("age").from("10").to("20"));
            EsPage list = ElasticsearchUtil.searchDataPage(indexName, esType, Integer.parseInt(startPage), Integer.parseInt(pageSize), boolQuery, null, null, null);
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }
}

