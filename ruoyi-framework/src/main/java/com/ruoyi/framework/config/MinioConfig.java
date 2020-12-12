package com.ruoyi.framework.config;

import com.ruoyi.common.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value(value = "${minio.url}")
    private String minioUrl;
    @Value(value = "${minio.name}")
    private String minioName;
    @Value(value = "${minio.password}")
    private String minioPass;
    @Value(value = "${minio.bucketName}")
    private String defaultBucketName;

    @Bean
    public void initMinio() {
        if (!minioUrl.startsWith("http")) {
            minioUrl = "http://" + minioUrl;
        }
        if (!minioUrl.endsWith("/")) {
            minioUrl = minioUrl.concat("/");
        }
        MinioUtils.setMinioUrl(minioUrl);
        MinioUtils.setMinioName(minioName);
        MinioUtils.setMinioPassword(minioPass);
        MinioUtils.setDefaultBucketName(defaultBucketName);
    }
}
