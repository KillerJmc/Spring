package com.jmc.json.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.json.domain.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JacksonTest {
    @Test
    public void java2Json_01() throws JsonProcessingException {
        Person p = new Person("张三", 13, "男");
        ObjectMapper mapper = new ObjectMapper();
        //{"name":"张三","age":13,"gender":"男"}
        System.out.println(mapper.writeValueAsString(p));
        //writeValue也方法可用
    }

    @Test
    public void java2Json_02() throws JsonProcessingException {
        Person p = new Person("张三", 13, "男", new Date());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(p));
    }

    @Test
    public void java2Json_03() throws JsonProcessingException {
        Person p = new Person("张三", 13, "男", new Date());
        Person p1 = new Person("李四", 23, "男", new Date());
        Person p2 = new Person("王五", 33, "未知", new Date());
        var l = List.of(p, p1, p2);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(l));
    }

    @Test
    public void java2Json_04() throws JsonProcessingException {
        var m = Map.of("name", "张三",
                "age", 13,
                "gender", "男");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(m));
    }

    @Test
    public void json2Java() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"name\":\"张三\",\"age\":13,\"gender\":\"男\"}";
        Person p = mapper.readValue(json, Person.class);
        System.out.println(p);
    }
}
