package com.jmc.es.pojo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "jmc")
@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private int age;
    private String gender;
}
