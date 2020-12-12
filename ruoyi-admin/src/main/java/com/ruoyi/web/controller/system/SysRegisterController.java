package com.ruoyi.web.controller.system;

import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.email.MailSenderInfo;
import com.ruoyi.common.utils.email.SimpleMailSender;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.framework.config.EmailConfig;
import com.ruoyi.system.domain.SysEmail;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 账号注册
 * @author loken
 */
@RestController
@Api("账号注册")
public class SysRegisterController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private RedisCache redisCache;
    /**
     * 注册接口
     */
    @ApiOperation(value = "注册",notes = "账号注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUser usr){
        return AjaxResult.success();
    }
    /**
     * 服务器向注册邮箱发送验证码
     */
    @ApiOperation(value = "邮箱验证",notes = "发送注册邮箱验证码")
    @PostMapping("/register/email")
    public AjaxResult sendEmail(@RequestParam("email") String email){
        if (!Pattern.matches("[a-z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+", email)){
            return AjaxResult.error(HttpStatus.BAD_REQUEST,"邮箱格式不正确！");
        }
        String f= userService.checkEmailUnique(new SysUser(){
            @Override
            public void setEmail(String email) {
                super.setEmail(email);
            }
        });
        if (UserConstants.NOT_UNIQUE.equals(f)) {
            return AjaxResult.error("'失败，邮箱账号已存在");
        }
        //生成验证码
        String code= RandomUtil.randomNumbers(6);
        //发送邮件
        MailSenderInfo info=new MailSenderInfo();
        SysEmail e=new SysEmail();
        e.setSendTime(new Date());
        info.setMailServiceHost(EmailConfig.getMailHost());
        info.setMailServicePort(EmailConfig.getMailPort());
        info.setFromAddress(EmailConfig.getFromEmail());
        info.setValidate(true);
        info.setUserName(EmailConfig.getFromEmail());
        info.setPassword(EmailConfig.getFromEmailPwd());
        String[] to={email};
        info.setToAddress(to);
        info.setSubject("某某系统用户注册验证码");
        info.setContent(code);
        SimpleMailSender sender=new SimpleMailSender();
        boolean b=sender.sendHtmlMail(info);
        if(b){
            e.setSendFlag(1);
            e.setSendTime(DateUtils.getNowDate());
            //将验证码缓存到redis中并设置过期时间为60s
            String uuid=UUID.fastUUID().toString();
            String cacheKey= Constants.CAPTCHA_EMAIL_CODE_KEY+uuid;
            redisCache.setCacheObject(cacheKey,code,60, TimeUnit.SECONDS);
            return AjaxResult.success("邮件已发送，请注意查看验证码",uuid);
        }
        return AjaxResult.error(HttpStatus.ERROR,"系统错误，邮件发送失败");
    }
    /**
     * 邮箱验证码验证
     */
    @ApiOperation(value = "邮箱验证",notes = "账号注册邮箱验证码验证")
    @GetMapping("/register/verify/{code}")
    public AjaxResult codeVerify(@PathVariable("code") String code,@RequestParam("uuid") String uuid){
        String cacheCode=redisCache.getCacheObject(Constants.CAPTCHA_CODE_KEY+uuid);
        redisCache.deleteObject(Constants.CAPTCHA_CODE_KEY+uuid);
        if (cacheCode.equals(code)) {
            return AjaxResult.success("验证成功!");
        }
        return AjaxResult.error(HttpStatus.BAD_REQUEST,"验证失败，验证码错误！");
    }
}
