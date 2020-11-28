package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.utils.MinioUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MinioFile;
import com.ruoyi.system.service.IMinioFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio文件Controller
 * 
 * @author ruoyi
 * @date 2020-11-28
 */
@RestController
@RequestMapping("/system/file")
public class MinioFileController extends BaseController
{
    @Autowired
    private IMinioFileService minioFileService;

    /**
     * 查询minio文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(MinioFile minioFile)
    {
        startPage();
        List<MinioFile> list = minioFileService.selectMinioFileList(minioFile);
        return getDataTable(list);
    }

    /**
     * 导出minio文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:file:export')")
    @Log(title = "minio文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MinioFile minioFile)
    {
        List<MinioFile> list = minioFileService.selectMinioFileList(minioFile);
        ExcelUtil<MinioFile> util = new ExcelUtil<MinioFile>(MinioFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 获取minio文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:file:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return AjaxResult.success(minioFileService.selectMinioFileById(fileId));
    }

    /**
     * 新增minio文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:add')")
    @Log(title = "minio文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MinioFile minioFile)
    {
        return toAjax(minioFileService.insertMinioFile(minioFile));
    }

    /**
     * 修改minio文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "minio文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MinioFile minioFile)
    {
        return toAjax(minioFileService.updateMinioFile(minioFile));
    }

    /**
     * 删除minio文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:remove')")
    @Log(title = "minio文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(minioFileService.deleteMinioFileByIds(fileIds));
    }
    /**
     * 上传文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:upload')")
    @Log(title = "minio文件", businessType = BusinessType.DELETE)
    @PutMapping("/uploadMinioFile")
    public AjaxResult upload(MultipartFile file, @RequestParam("bucketName") String bucketName)
    {
        return MinioUtils.uploadAndSaveToLocalDb(file, bucketName);
    }
}
