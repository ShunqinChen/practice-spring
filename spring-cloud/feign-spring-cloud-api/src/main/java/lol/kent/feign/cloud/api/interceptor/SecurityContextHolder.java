package lol.kent.feign.cloud.api.interceptor;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.NamedThreadLocal;

/**
 * 标题、简要说明. <br> 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月26日 10:33
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version x.x.x
 */
public class SecurityContextHolder {


    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_CONTEXTS = new NamedThreadLocal<>(
            "Security Context");

    public static Map<String, Object> currentContextMap() {

        Map<String, Object> context = THREAD_LOCAL_CONTEXTS.get();

        if (context == null) {
            context = initContextMap();
        }

        return context;
    }

    protected static Map<String, Object> initContextMap() {
        Map<String, Object> context = new HashMap<>(16);
        THREAD_LOCAL_CONTEXTS.set(context);
        return context;
    }

    protected static boolean has(String key) {
        return currentContextMap().containsKey(key);
    }

    public static <T> T get(String key, Class<T> valueClass) {
        Map<String, Object> currentContextMap = currentContextMap();
        if (currentContextMap.containsKey(key)) {
            Object o = currentContextMap.get(key);
            if (valueClass.isInstance(o)) {
                return valueClass.cast(o);
            }
        }
        return null;
    }

    public static <T> void set(String key, T value) {
        currentContextMap().put(key, value);
    }

    protected static void delete(String key) {
        Map<String, Object> currentContextMap = currentContextMap();
        if (currentContextMap.containsKey(key)) {
            currentContextMap.remove(key);
        }
    }

    public static void removeCurrentContextMap() {
        THREAD_LOCAL_CONTEXTS.remove();
    }

}