package com.ruoyi.common.utils;

import com.ruoyi.common.utils.uuid.UUID;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
    public static MinioClient createMinioClient() {
        if (minioClient == null) {
            try {
                minioClient = new MinioClient(minioUrl, minioName, minioPassword);
            } catch (InvalidEndpointException e) {
                e.printStackTrace();
            } catch (InvalidPortException e) {
                e.printStackTrace();
            }
        }
        return minioClient;
    }

    /**
     * 创建具有给定区域的新存储桶。
     *
     * @param bucketName
     */
    public static void makeBucket(String bucketName) {
        try {
            minioClient = createMinioClient();
            // Create bucket if it doesn't exist.
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                System.out.println(bucketName + " already exists");
            } else {
                // Create bucket 'my-bucketname'.
                minioClient.makeBucket(bucketName);
                System.out.println(bucketName + " is created successfully");
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出所有桶。
     *
     * @return
     */
    public static List<Bucket> listBuckets() {
        List<Bucket> bucketList = null;
        try {
            minioClient = createMinioClient();
            // List buckets that have read access.
            bucketList = minioClient.listBuckets();
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketList;
    }

    /**
     * 上传文件到文件服务器
     *
     * @param file
     * @param bucketName
     * @return
     */
    public static ObjectStat upload(@NotNull MultipartFile file, String bucketName) {
        String objectName = null;
        if (StringUtils.isEmpty(bucketName)) {
            bucketName = defaultBucketName;
        }
        try {
            minioClient = createMinioClient();
            String fileName = file.getOriginalFilename();
            objectName = fileName + "_" + UUID.fastUUID().toString().substring(0, 6) + System.currentTimeMillis();
            InputStream is = file.getInputStream();
            minioClient.putObject(bucketName, objectName, is, is.available(), "application/octet-stream");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
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
        return setObjectStat(bucketName, objectName);
    }

    /**
     * 获取对象元数据。
     *
     * @param bucketName
     * @param objectName
     * @return
     */
    public static ObjectStat setObjectStat(String bucketName, String objectName) {
        ObjectStat objectStat = null;
        try {
            minioClient = createMinioClient();
            // 获得对象的元数据。
            objectStat = minioClient.statObject(bucketName, objectName);
            System.out.println(objectStat);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectStat;
    }

    /**
     * 删除对象。
     *
     * @param bucketName
     * @param objectName
     */
    public static void removeObject(String bucketName, String objectName) {
        try {
            minioClient = createMinioClient();
            // 从bucketName中删除objectName。
            minioClient.removeObject(bucketName, objectName);
            System.out.println("successfully removed " + bucketName + "/" + objectName);
        } catch (MinioException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个给HTTP GET请求用的presigned URL。浏览器/移动端的客户端可以用这个URL进行下载，即使其所在的存储桶是私有的。
     * 这个presigned URL可以设置一个失效时间，默认值是7天。
     * 一天：60 * 60 * 24
     *
     * @param bucketName 桶名
     * @param objectName 对象名
     * @param expires    链接有效时间
     */
    public static String presignedGetObject(String bucketName, String objectName, Integer expires) {
        String url = null;
        try {
            minioClient = createMinioClient();
            url = minioClient.presignedGetObject(bucketName, objectName, expires);
            System.out.println(url);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 生成一个给HTTP PUT请求用的presigned URL。浏览器/移动端的客户端可以用这个URL进行上传，即使其所在的存储桶是私有的。
     * 这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 桶名
     * @param objectName 对象名
     * @param expires    链接有效时间
     * @return
     */
    public static String presignedPutObject(String bucketName, String objectName, Integer expires) {
        String url = null;
        try {
            minioClient = createMinioClient();
            url = minioClient.presignedPutObject(bucketName, objectName, expires);
            System.out.println(url);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
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
