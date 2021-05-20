package com.jmc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Jmc
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = {"/save"},
                    method = {RequestMethod.GET},
                    //提交时一定要含的参数
                    params = {"name"})
    public String save() {
        System.out.println("Controller save running...");
        //直接跳转(默认时forward:)
//        return "redirect:/jsp/success.jsp";
        return "success";
    }

    @RequestMapping("/save2")
    public ModelAndView save2() {
        //model: 模型 用于封装数据
        //view: 视图 用于展示数据
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "Jmc");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/save3")
    public ModelAndView save3(ModelAndView modelAndView) {
        modelAndView.addObject("name", "Jmc");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/save4")
    public String save4(Model m) {
        m.addAttribute("name", "Lucy");
        return "success";
    }

    //行不通
    @RequestMapping("/save5")
    public String save5(ModelAndView m) {
        m.addObject("name", "Lucy");
        return "success";
    }

    @RequestMapping("/save6")
    public String save6(HttpServletRequest req) {
        req.setAttribute("name", "Kitty");
        return "success";
    }

    @RequestMapping("/save7")
    public void save7(HttpServletResponse resp) throws IOException {
        resp.getWriter().print("OKSIR!!!");
    }

    @RequestMapping("/save8")
    @ResponseBody
    public String save8() {
        return "<h1>Haha</h1>";
    }

    @RequestMapping("/save9")
    @ResponseBody
    public String save9() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new User("Jmc", "123"));
    }

    @RequestMapping("/save10")
    @ResponseBody
    //要在mvc配置文件中配置
    public User save10() {
        return new User("Jmc", "456");
    }

    @RequestMapping("/save11")
    @ResponseBody
    //要在mvc配置文件中配置
    public List<?> save11() {
        return List.of(new User("Lucy", "666"));
    }




}
