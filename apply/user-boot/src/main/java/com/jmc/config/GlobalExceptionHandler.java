package com.jmc.config;

import com.jmc.config.exception.MsgException;
import com.jmc.lang.extend.Strs;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 信息异常
     */
    @ExceptionHandler(MsgException.class)
    public ModelAndView msgException(MsgException e) {
         return new ModelAndView("error/errorPage")
                .addObject("errMsg", e.getMessage());
    }

    /**
     * validation异常
     */
    @ExceptionHandler(BindException.class)
    public ModelAndView bindException(BindException e) {
        return new ModelAndView("error/errorPage")
                // 只获取一条错误信息
                .addObject("errMsg", Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }

    /**
     * 默认异常
     */
    @ExceptionHandler
    public ModelAndView defaultException(Exception e) {
        var errMsg = e.getClass().getSimpleName() + ": " +
                Strs.subExclusive(e.getMessage(), "nested exception is ");
        return new ModelAndView("error/errorPage")
                .addObject("errMsg", errMsg);
    }
}
