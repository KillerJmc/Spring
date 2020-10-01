package com.jmc.web;

import com.jmc.bean.ObjFactory;
import com.jmc.config.SpringConfig;
import com.jmc.domain.User;
import com.jmc.lang.Tries;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private final JdbcTemplate jt;

    public IndexServlet() {
        var app = new AnnotationConfigApplicationContext(SpringConfig.class);
        jt = app.getBean(JdbcTemplate.class);
    }

    public boolean containsUser(User u) {
        Exception e = Tries.tryThrowsE(() ->
            jt.queryForObject("select * from user where username = ? and password = ?",
                new BeanPropertyRowMapper<>(User.class),
                u.getUsername(), u.getPassword()));

        return e == null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User u = ObjFactory.get(req, User.class);

        User u = new User();

//        Tries.tryThis(() -> {
//            BeanUtils.setProperty(u, "username", "Jmc");
//            BeanUtils.setProperty(u, "password", "010809");
//            String username = BeanUtils.getProperty(u, "username");
//            String password = BeanUtils.getProperty(u, "password");
//        });

        Tries.tryThis(() -> BeanUtils.populate(u, req.getParameterMap()));
        System.out.println(u);

        if (containsUser(u)) {
            req.setAttribute("u", u);
            req.getRequestDispatcher("/success")
               .forward(req, resp);
        } else {
            req.getRequestDispatcher("/fail")
               .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
