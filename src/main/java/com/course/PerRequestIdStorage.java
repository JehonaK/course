package com.course;

public class PerRequestIdStorage {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setUserId(String userId) {
        threadLocal.set(userId);
    }

    public static String getUserId() {
        return threadLocal.get();
    }

    public static void removeUserId() {
        threadLocal.remove();
    }

}