package com.customer.auth.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@EnableDynamoDBRepositories
  (basePackages = "com.customer.auth.repository")
public class DynamoDBConfig {



    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.eu-central-1.amazonaws.com", "eu-central-1"))
        .build();
        return client;
    }
    
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

}