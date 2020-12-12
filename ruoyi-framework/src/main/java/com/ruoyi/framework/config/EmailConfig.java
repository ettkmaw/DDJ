package com.ruoyi.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource(value = { "classpath:email.properties" })
public class EmailConfig
{
    @Value(value = "${email.host}")
    public static String mailHost;
    @Value(value = "${email.port}")
    public static String mailPort;

    /**
     *邮件发送用户
     */
    @Value(value = "${email.fromEmail}")
    public static String  fromEmail;
    /**
     * 邮件发送用户授权密码
     */
    @Value(value = "${email.fromEmailPwd}")
    public static String fromEmailPwd;
    @Value("${email.host}")
    public  void setMailHost(String mailHost) {
        EmailConfig.mailHost = mailHost;
    }
    @Value("${email.port}")
    public  void setMailPort(String mailPort) {
        EmailConfig.mailPort = mailPort;
    }

    @Value("${email.fromEmail}")
    public  void setFromEmail(String fromEmail) {
        EmailConfig.fromEmail = fromEmail;
    }
    @Value("${email.fromEmailPwd}")
    public  void setFromEmailPwd(String fromEmailPwd) {
        EmailConfig.fromEmailPwd = fromEmailPwd;
    }

    public static String getMailHost() {
        return mailHost;
    }



    public static String getMailPort() {
        return mailPort;
    }



    public static String getFromEmail() {
        return fromEmail;
    }

    public static String getFromEmailPwd() {
        return fromEmailPwd;
    }


}
