package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import java.util.Date;

public class SysEmail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 邮件类型 */
    @Excel(name = "邮件类型")
    private Integer emailType;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 发送者邮箱 */
    @Email
    @Excel(name = "发送者邮箱")
    private String fromEmail;


    /** 接收者邮箱 */
    @Email
    @Excel(name = "接收者邮箱")
    private String toEmail;

    /** 主题 */
    @Excel(name = "主题")
    private String subject;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 是否发送 */
    @Excel(name = "是否发送")
    private Integer sendFlag;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 发送类型 0立即 1定时 */
    @Excel(name = "发送类型 0立即 1定时")
    private String sendType;

    /** 定时时间 */
    @Excel(name = "定时时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;

    /** 抄送用户 */
    @Excel(name = "抄送用户")
    private String copyTo;

    /** 附件 */
    @Excel(name = "附件")
    private String attachFiles;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setEmailType(Integer emailType)
    {
        this.emailType = emailType;
    }

    public Integer getEmailType()
    {
        return emailType;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setFromEmail(String fromEmail)
    {
        this.fromEmail = fromEmail;
    }

    public String getFromEmail()
    {
        return fromEmail;
    }


    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getSubject()
    {
        return subject;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setSendFlag(Integer sendFlag)
    {
        this.sendFlag = sendFlag;
    }

    public Integer getSendFlag()
    {
        return sendFlag;
    }
    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime()
    {
        return sendTime;
    }
    public void setSendType(String sendType)
    {
        this.sendType = sendType;
    }

    public String getSendType()
    {
        return sendType;
    }
    public void setPlanTime(Date planTime)
    {
        this.planTime = planTime;
    }

    public Date getPlanTime()
    {
        return planTime;
    }
    public void setCopyTo(String copyTo)
    {
        this.copyTo = copyTo;
    }

    public String getCopyTo()
    {
        return copyTo;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(String attachFiles) {
        this.attachFiles = attachFiles;
    }

    @Override
    public String toString() {
        return "SysEmail{" +
                "id='" + id + '\'' +
                ", emailType=" + emailType +
                ", userId='" + userId + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sendFlag=" + sendFlag +
                ", sendTime=" + sendTime +
                ", sendType='" + sendType + '\'' +
                ", planTime=" + planTime +
                ", copyTo='" + copyTo + '\'' +
                ", attachFiles='" + attachFiles + '\'' +
                '}';
    }
}
