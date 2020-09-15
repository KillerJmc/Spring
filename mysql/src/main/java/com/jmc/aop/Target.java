package com.jmc.aop;

import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetI {
    @Override
    public void save() {
//        int i = 1/0;
        System.out.println("save running...");
    }
}
