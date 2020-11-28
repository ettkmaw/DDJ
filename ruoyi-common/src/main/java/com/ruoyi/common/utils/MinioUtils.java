package com.ruoyi.common.utils;

import com.ruoyi.common.core.domain.AjaxResult;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioUtils {
    private static String minioUrl;
    private static String minioName;
    private static String minioPassword;
    private static String defaultBucketName;

    private static MinioClient minioClient = null;

    /**
     * 创建minioClient对象
     *
     * @return
     */
    public static MinioClient CreateMinioClient() {
        if (null != minioClient) {
            try {
                minioClient = new MinioClient(minioUrl, minioName, minioPassword);
            } catch (InvalidEndpointException e) {
                e.printStackTrace();
            }catch (InvalidPortException e){
                e.printStackTrace();
            }
        }
        return minioClient;
    }

    /**
     * 上传文件到文件服务器
     * @param file
     * @param bucketName
     * @return
     */
    public static boolean upload(@NotNull MultipartFile file, String bucketName){
        if (StringUtils.isEmpty(bucketName)){
            bucketName=defaultBucketName;
        }
        try {
        minioClient=CreateMinioClient();
        String fileName=file.getOriginalFilename();
        String newFileName= fileName+System.currentTimeMillis();
        InputStream is=file.getInputStream();
        minioClient.putObject(bucketName,newFileName,is,is.available(),"application/octet-stream");
        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 上传文件并保存到本地数据库
     * @return
     */
    public static AjaxResult uploadAndSaveToLocalDb(MultipartFile file, String bucketName){
        if (upload(file, bucketName)){
        return AjaxResult.success();

        }
        return AjaxResult.error();
    }

    public static String getMinioUrl() {
        return minioUrl;
    }

    public static void setMinioUrl(String minioUrl) {
        MinioUtils.minioUrl = minioUrl;
    }

    public static String getMinioName() {
        return minioName;
    }

    public static void setMinioName(String minioName) {
        MinioUtils.minioName = minioName;
    }

    public static String getMinioPassword() {
        return minioPassword;
    }

    public static void setMinioPassword(String minioPassword) {
        MinioUtils.minioPassword = minioPassword;
    }

    public static String getDefaultBucketName() {
        return defaultBucketName;
    }

    public static void setDefaultBucketName(String defaultBucketName) {
        MinioUtils.defaultBucketName = defaultBucketName;
    }

    public static MinioClient getMinioClient() {
        return minioClient;
    }

    public static void setMinioClient(MinioClient minioClient) {
        MinioUtils.minioClient = minioClient;
    }
}
