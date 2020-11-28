package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication extends SpringBootServletInitializer
{
    public static void main(String[] args) throws UnknownHostException
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext applicationContext=SpringApplication.run(RuoYiApplication.class);
        Environment env= applicationContext.getEnvironment();
        String ip= InetAddress.getLocalHost().getHostAddress();
        String port=env.getProperty("server.port");
        String path= env.getProperty("server.servlet.context-path");

        System.out.println(
                "\n----------------------------------------------\n\t"+
                "系统启动成功！\n\t"+
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t"+
                "\n----------------------------------------------\n\t"
                );


    }
}
