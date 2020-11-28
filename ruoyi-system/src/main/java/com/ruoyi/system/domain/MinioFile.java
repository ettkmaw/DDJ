package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * minio文件对象 minio_file
 * 
 * @author ruoyi
 * @date 2020-11-28
 */
public class MinioFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long fileId;

    /** 桶名 */
    @Excel(name = "桶名")
    private String bucketName;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long size;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setBucketName(String bucketName) 
    {
        this.bucketName = bucketName;
    }

    public String getBucketName() 
    {
        return bucketName;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getSize() 
    {
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("bucketName", getBucketName())
            .append("fileName", getFileName())
            .append("size", getSize())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
