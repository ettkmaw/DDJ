package com.example.demo.datasource;

public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> THREAD_LOCAL=new ThreadLocal<String>();


    public static String getDataSource(){
        return THREAD_LOCAL.get();
    }
    public static void setDataSource(String value){
         THREAD_LOCAL.set(value);
    }
    public static void removeDataSource(){
         THREAD_LOCAL.remove();
    }
}
