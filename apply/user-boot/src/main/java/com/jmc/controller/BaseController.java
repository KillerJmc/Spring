package com.jmc.controller;

import com.jmc.common.Const;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    /**
     * 跳转到成功界面
     * @param succeedMsg 成功信息
     * @param returnUrl 成功界面中返回的地址
     * @return 失成功界面视图对象
     */
    public ModelAndView succeedView(String succeedMsg, String returnUrl) {
        return new ModelAndView("succeedPage")
                .addObject(Const.SUCCEED_MSG, succeedMsg)
                .addObject(Const.SUCCEED_RETURN_URL, returnUrl);
    }
}
