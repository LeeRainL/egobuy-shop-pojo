package com.igeekhome.egobuy.util;

/**
 * @author yadonghe
 * @date 2020-03-03 10:41
 */
public class IDUtil {
    public static Long generateId() {
        return System.currentTimeMillis() + (int)(Math.random()*1000);
    }
}
