package com.shop.amazonS3.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class S3Config {
    @Value("${amazons3.region}")
    private String region;

    @Value("${amazons3.api.access_key_id}")
    private String awsId;

    @Value("${amazons3.api.secret_access_key}")
    private String awsKey;

    @Bean
    public AmazonS3 s3Client() {
        log.info("Creating Amazon S3 client...");
        final BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        log.info("Amazon S3 client created successfully.");
        return s3Client;
    }
}
