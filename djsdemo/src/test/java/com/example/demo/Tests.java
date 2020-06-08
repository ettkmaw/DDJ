package com.example.demo;

import org.antlr.stringtemplate.StringTemplate;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Tests {
    public static void main(String[] args) {
        //时间格式化
        /*Date date=new Date();
        System.out.println(date);
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,1);
        Date newDate=c.getTime();
        System.out.println( new SimpleDateFormat("yyyy-MM-dd").format(newDate));*/
       /* String testStr="i am not null";
        try {
            //断言
            Assert.notNull(testStr, "testStr is null");
            System.out.println(testStr);
        }catch (Exception e){
            e.printStackribute("key","is a key");Trace();
    }*/
       //生成流水号
        StringBuffer buffer=new StringBuffer(String.valueOf(System.currentTimeMillis()));
        int num=randomNum(4);
        buffer.append(num);
        System.out.println(buffer.toString());
    }

    public static int randomNum(Integer length){
        double random=Math.random();
        int num=1;
        if (random<0.1){
            random=random+0.1;
        }
        for (int i = 0; i <length ; i++) {
            num=num*10;
        }
        return (int)(random*num);
    }
}
