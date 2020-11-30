package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MinioFile;

/**
 * minio文件Service接口
 * 
 * @author ruoyi
 * @date 2020-11-28
 */
public interface IMinioFileService 
{
    /**
     * 查询minio文件
     * 
     * @param fileId minio文件ID
     * @return minio文件
     */
    public MinioFile selectMinioFileById(Long fileId);

    /**
     * 查询minio文件列表
     * 
     * @param minioFile minio文件
     * @return minio文件集合
     */
    public List<MinioFile> selectMinioFileList(MinioFile minioFile);

    /**
     * 新增minio文件
     * 
     * @param minioFile minio文件
     * @return 结果
     */
    public int insertMinioFile(MinioFile minioFile);

    /**
     * 修改minio文件
     * 
     * @param minioFile minio文件
     * @return 结果
     */
    public int updateMinioFile(MinioFile minioFile);

    /**
     * 批量删除minio文件
     * 
     * @param fileIds 需要删除的minio文件ID
     * @return 结果
     */
    public int deleteMinioFileByIds(Long[] fileIds);

    /**
     * 删除minio文件信息
     * 
     * @param fileId minio文件ID
     * @return 结果
     */
    public int deleteMinioFileById(Long fileId);
    /**
     * 新增minio文件并返回文件id
     *
     * @param minioFile minio文件
     * @return 结果
     */
    public Long insertMinioFileAndReturnId(MinioFile minioFile);
}
