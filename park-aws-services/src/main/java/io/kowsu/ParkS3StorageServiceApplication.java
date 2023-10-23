package io.kowsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkS3StorageServiceApplication implements CommandLineRunner{

	@Autowired
	S3Service s3Service;
	
	@Autowired
	SnSService snSService;
	
	@Autowired
	SqSService sqSService;
	
	public static void main(String[] args) {
		SpringApplication.run(ParkS3StorageServiceApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
//		s3Service.createBucket("kowsu-images");
//		s3Service.putObject("kowsu-images", "key_721192", new File("C:\\Users\\Ram Kowsu\\Desktop\\vikram.jpg"));
//		for (String string : s3Service.listBuckets()) {
//			System.out.println("Bucket----------" + string);	
//		}
//		s3Service.deleteBucket("kowsu-images");
		
//		snSService.createTopic("kowsu-sns");
//		snSService.createTopic("kowsu-sns-1");
		//snSService.addSubscriptions();
//		snSService.sendMessage("kowsu-sns-1", "Bro, sending messages from SNS to EMAIL");
		
		
		
		//sqSService.createQueue("kowsu-sqs");
		sqSService.sendMessage("kowsu-sqs", "Swamiye saranam Ayyappa");
		sqSService.sendMessage("kowsu-sqs", "Manikanta");
		sqSService.sendMessage("kowsu-sqs", "Hari Hara Sutane");
		
		
		
	}

}
