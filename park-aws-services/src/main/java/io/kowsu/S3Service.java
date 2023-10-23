package io.kowsu;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 amazonS3;
	public void createBucket(String bucketName) {
		amazonS3.createBucket(bucketName);
	}
	
	public void putObject(String bucketName, String key, File file) {
		amazonS3.putObject(bucketName, key, file);
	}
	
	public String[] listBuckets() {
		List<String> names = 
				amazonS3.listBuckets().stream
				().map(a -> a.getName()).collect(Collectors.toList());
		return names.toArray(new String[0]);
	}
	
	public void deleteBucket(String name) {
		DeleteBucketRequest bucketRequest = new DeleteBucketRequest(name);
		ObjectListing objects = amazonS3.listObjects(name);
		List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
		objectSummaries.stream().forEach(objectKey -> {
			System.out.println("Deleting object------" + objectKey);
			amazonS3.deleteObject(name, objectKey.getKey());
		});
		amazonS3.deleteBucket(bucketRequest);
	}
}
