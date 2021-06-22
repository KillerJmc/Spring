package com.jmc.controller;

import com.jmc.common.Const;
import com.jmc.config.exception.MsgException;
import com.jmc.config.valid.Group;
import com.jmc.pojo.Admin;
import com.jmc.pojo.User;
import com.jmc.service.AdminService;
import com.jmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Jmc
 */
@RestController
public class UserController extends BaseController {
    private UserService userService;
    private AdminService adminService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ModelAndView register(@Validated(Group.Insert.class) User u, HttpSession session) {
        userService.insert(u);
        session.setAttribute(Const.LOGGED_IN_SESSION, u);

        return new ModelAndView("redirect:func");
    }

    @PostMapping("/login")
    public ModelAndView login(@Validated(Group.Login.class) User u, HttpSession session,
                              @SessionAttribute(value = Const.LOGGED_IN_SESSION, required = false) Object loggedIn) {
        if (loggedIn != null) {
            throw new MsgException("请勿重复登录");
        }

        Admin a;
        if (userService.getByNameAndPassword(u) != null) {
            session.setAttribute(Const.LOGGED_IN_SESSION, u);
            return new ModelAndView("redirect:func");
        } else if ((a = adminService.getByNameAndPassword(u.getName(), u.getPassword())) != null) {
            session.setAttribute(Const.LOGGED_IN_SESSION, a);
            return new ModelAndView("redirect:func");
        } else {
            throw new MsgException("用户名或密码错误");
        }
    }

    /**
     * 解决无法跳转的问题
     */
    @GetMapping({"/func", "/func.html"})
    public ModelAndView toFunc(@SessionAttribute(value = Const.LOGGED_IN_SESSION) Object loggedIn) {
        if (loggedIn instanceof User u) {
            return new ModelAndView("func")
                    .addObject(Const.LOGGED_IN_MSG, "用户：" + u.getName());
        } else if (loggedIn instanceof Admin a) {
            return new ModelAndView("func")
                    .addObject(Const.LOGGED_IN_MSG, "管理员：" + a.getName());
        } else {
            throw new MsgException("登录人信息获取错误");
        }
    }

    @GetMapping("/viewAllUsers")
    public ModelAndView viewAllUsers(@SessionAttribute(value = Const.LOGGED_IN_SESSION) Object loggedIn) {
        return new ModelAndView("viewAllUsers")
                .addObject(Const.USER_TABLE, userService.getAllUsersAsString(loggedIn instanceof Admin));
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute(Const.LOGGED_IN_SESSION);
        return new ModelAndView("index");
    }

    @GetMapping({"/", "/index.html"})
    public ModelAndView toIndex() {
        return new ModelAndView("index");
    }

    @GetMapping("/modifyUser")
    public ModelAndView modifyUser(@SessionAttribute(value = Const.LOGGED_IN_SESSION) Object loggedIn) {
        return new ModelAndView("modifyUser")
                .addObject(Const.USER_TABLE, userService.getAllUsersAsString(loggedIn instanceof Admin));
    }

    @PostMapping("/deleteById")
    public ModelAndView deleteUser(Integer id,
                                   @SessionAttribute(value = Const.LOGGED_IN_SESSION) Object loggedIn) {
        if (loggedIn instanceof User) {
            throw new MsgException("删除失败！普通用户没有权利删除用户");
        }

        userService.deleteById(id);
        return succeedView("删除成功！", "modifyUser");
    }

    @PostMapping("/deleteByName")
    public ModelAndView deleteUser(String name,
                                   @SessionAttribute(value = Const.LOGGED_IN_SESSION) Object loggedIn) {
        if (loggedIn instanceof User) {
            throw new MsgException("删除失败！普通用户没有权利删除用户");
        }

        userService.deleteByName(name);
        return succeedView("删除成功！", "modifyUser");
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@Validated(Group.Update.class) User u, HttpSession session,
                                   @SessionAttribute(value = Const.LOGGED_IN_SESSION) Object loggedIn) {

        // 如果是用户在操作
        if (loggedIn instanceof User u0) {
            var operatingUser = userService.getByName(u0.getName());
            if (!operatingUser.getId().equals(u.getId())) {
                throw new MsgException("修改失败！普通用户只能修改自己的信息");
            } else {
                session.setAttribute(Const.LOGGED_IN_SESSION, u);
            }
        }

        userService.updateById(u);
        return succeedView("修改成功！", "modifyUser");
    }
}
