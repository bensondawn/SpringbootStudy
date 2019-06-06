package com.benson.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "mybook",type = "library")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    @Id
    private String id;

    // IK分词器有两种分词模式：ik_max_word(将文本做最细粒度的拆分)和ik_smart(将文本做最粗粒度的拆分)模式。
    // @Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String name;

    private String author;
}
