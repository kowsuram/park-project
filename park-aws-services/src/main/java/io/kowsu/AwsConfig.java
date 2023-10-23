package io.kowsu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
public class AwsConfig {
	
	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;
	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
	
	@Bean
	AmazonS3 amazonS3Client(AWSStaticCredentialsProvider awsStaticCredentialsProvider) {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(awsStaticCredentialsProvider)
				.withRegion("us-east-1")
				.build();
	}
	
	@Bean
	AmazonSNS amazonSNSClient(AWSStaticCredentialsProvider awsStaticCredentialsProvider) {
		return AmazonSNSClientBuilder.standard().withCredentials(awsStaticCredentialsProvider)
		.withRegion("ap-south-1")
		.build();
	}
	
	@Bean
	AmazonSQS amazonSQS(AWSStaticCredentialsProvider awsStaticCredentialsProvider) {
		return AmazonSQSClientBuilder.standard()
				.withCredentials(awsStaticCredentialsProvider)
				.withRegion("ap-south-1").build();
	}
	
	@Bean
	AWSStaticCredentialsProvider awsStaticCredentialsProvider() {
		return new  AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
	}
	
	
	

}
