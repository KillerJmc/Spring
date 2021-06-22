package com.jmc.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    /**
     * 默认通过forward方式跳转
     * @return 跳转到的网页
     */
    @RequestMapping("/quick")
    public String hello() {
        System.out.println("UserController.save running...");
        return "success.html";
    }

    /**
     * 通过redirect方式跳转
     * @return 跳转到的网页
     */
    @RequestMapping("/quick2")
    public String hello2() {
        System.out.println("UserController.save running...");
        return "redirect:success.html";
    }

    /**
     * 打印并解决输出乱码问题
     * @return 打印内容
     */
    @RequestMapping(value = "/print", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String printSth() {
        return "兄弟们666";
    }

    @RequestMapping("/user1")
    @ResponseBody
    public void user(String name, int id) {
        // 注意须在web.xml配置listener解决获取post请求数据的乱码问题
        System.out.println("name = " + name);
        System.out.println("id = " + id);
    }

    @RequestMapping("/user2")
    @ResponseBody
    public void user2(@RequestParam(name = "username") String name,
                      @RequestParam(name = "userId", required = false, defaultValue = "123") int id) {
        System.out.println("name = " + name);
        System.out.println("id = " + id);
    }

    /**
     * Restful风格，从地址上，用GET，POST，DELETE等请求实现不同风格
     * @param id 要获取的用户对应的编号
     */
    @RequestMapping(value = "/user3/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void user3(@PathVariable int id) {
        System.out.println("id = " + id);
    }

    @Data
    @AllArgsConstructor
    static class User {
        String name;
        int id;
    }

    /**
     * 返回json格式字符串（注意要导入jackson包并且要写 mvc:annotation-driven）
     * @return json格式字符串
     */
    @RequestMapping("/user4")
    @ResponseBody
    public List<User> user4() {
        return List.of(
                new User("Jmc", 18),
                new User("Lucy", 16),
                new User("Jerry", 40)
        );
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(@RequestHeader(value = "User-Agent", required = false) String userAgent,
                     @CookieValue("JSESSIONID") String jSessionID) {
        System.out.println("userAgent = " + userAgent);
        System.out.println("jSessionID = " + jSessionID);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", produces = "text/plain;charset=UTF-8")
    public String login(String name, String password, HttpSession session) {
        if ("Jmc".equals(name) && "0189".equals(password)) {
            session.setAttribute("u", "Jmc");
            return "/func";
        } else {
            // 抛出异常，配置简单异常映射器解决
            throw new RuntimeException("无此用户！！！");
        }
    }

    /**
     * 出异常时跳转的页面
     */
    @RequestMapping(value = "/error", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String error() {
        return "服务器异常！（运行时异常）";
    }

    /**
     * 前往功能列表，前提是进行上面的登录操作（有FuncInterceptor进行拦截）
     */
    @RequestMapping(value = "/func", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String func(HttpSession session) {
        return "欢迎你，%s! 功能如下。".formatted(session.getAttribute("u"));
    }
}
