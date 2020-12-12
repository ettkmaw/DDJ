package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PreUpdate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MinioFileMapper;
import com.ruoyi.system.domain.MinioFile;
import com.ruoyi.system.service.IMinioFileService;

/**
 * minio文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-28
 */
@Service
public class MinioFileServiceImpl implements IMinioFileService 
{
    @Autowired
    private MinioFileMapper minioFileMapper;

    /**
     * 查询minio文件
     * 
     * @param fileId minio文件ID
     * @return minio文件
     */
    @Override
    public MinioFile selectMinioFileById(Long fileId)
    {
        return minioFileMapper.selectMinioFileById(fileId);
    }

    /**
     * 查询minio文件列表
     * 
     * @param minioFile minio文件
     * @return minio文件
     */
    @Override
    public List<MinioFile> selectMinioFileList(MinioFile minioFile)
    {
        return minioFileMapper.selectMinioFileList(minioFile);
    }

    /**
     * 新增minio文件
     * 
     * @param minioFile minio文件
     * @return 结果
     */
    @Override
    public int insertMinioFile(MinioFile minioFile)
    {
        BeanUtils.copyProperties(PreUpdate.preInsert(),minioFile);
        return minioFileMapper.insertMinioFile(minioFile);
    }

    /**
     * 修改minio文件
     * 
     * @param minioFile minio文件
     * @return 结果
     */
    @Override
    public int updateMinioFile(MinioFile minioFile)
    {
        minioFile.setUpdateTime(DateUtils.getNowDate());
        return minioFileMapper.updateMinioFile(minioFile);
    }

    /**
     * 批量删除minio文件
     * 
     * @param fileIds 需要删除的minio文件ID
     * @return 结果
     */
    @Override
    public int deleteMinioFileByIds(Long[] fileIds)
    {
        return minioFileMapper.deleteMinioFileByIds(fileIds);
    }

    /**
     * 删除minio文件信息
     * 
     * @param fileId minio文件ID
     * @return 结果
     */
    @Override
    public int deleteMinioFileById(Long fileId)
    {
        return minioFileMapper.deleteMinioFileById(fileId);
    }

    @Override
    public Long insertMinioFileAndReturnId(MinioFile minioFile) {
        return minioFileMapper.insertMinioFileAndReturnId(minioFile);
    }
}
