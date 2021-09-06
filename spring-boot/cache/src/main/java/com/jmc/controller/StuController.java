package com.jmc.controller;

import com.jmc.pojo.Stu;
import com.jmc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StuController {
    private StuService stuService;

    @Autowired
    public void setStuService(StuService stuService) {
        this.stuService = stuService;
    }

    @RequestMapping("/all")
    public List<Stu> getAll() {
        return stuService.getAll();
    }

    @RequestMapping("/get/{id}")
    public Stu getStu(@PathVariable("id") Integer id) {
        return stuService.getById(id);
    }

    @RequestMapping("/update/{id}")
    public Stu updateStu(@PathVariable("id") Integer id) {
        var s = stuService.getById(id);
        s.setName("流弊");
        return stuService.update(s);
    }

    @RequestMapping("/del/{id}")
    public String delStu(@PathVariable("id") Integer id) {
        stuService.deleteById(id);
        return "成功!";
    }
}
