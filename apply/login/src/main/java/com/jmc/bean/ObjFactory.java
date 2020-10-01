package com.jmc.bean;

import com.jmc.lang.Tries;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

public class ObjFactory {
    public static<T> T get(HttpServletRequest req, Class<T> c) {
        Map<String, String[]> params = req.getParameterMap();
        Method[] ms = c.getDeclaredMethods();
        Object o = Tries.tryReturnsT(() -> c.getConstructor().newInstance());

        for (Method m : ms)
            if (m.getName().startsWith("set"))
                Tries.tryThis(() -> m.invoke(o, params.get(m.getName().substring(3).toLowerCase())[0]));

        return (T) o;
    }
}
