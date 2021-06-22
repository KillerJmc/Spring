package com.jmc.controller;

import com.jmc.domain.Stu;
import com.jmc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("stuController")
public class StuController {
    private StuService stuService;

    @Autowired
    public void setStuService(StuService stuService) {
        this.stuService = stuService;
    }

    @RequestMapping(value = "/addStu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String addStu(Stu s) {
        System.out.println("StuController.addStu: " + s);
        stuService.save(s);
        return "添加成功";
    }

    @RequestMapping(value = "/showStus", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showStus() {
        System.out.println("StuController.showStus");
        var sb = new StringBuilder();
        stuService.getAll().forEach(s -> sb.append(s).append("<br>"));
        return sb.toString();
    }
}
