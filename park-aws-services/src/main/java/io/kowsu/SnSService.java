package io.kowsu;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

@Service
public class SnSService {
	
	@Autowired
	AmazonSNS amazonSNSClient;
	
	public void createTopic(String snsTopic) {
		CreateTopicRequest createTopicRequest = new CreateTopicRequest();
		createTopicRequest.setName(snsTopic);
		CreateTopicResult topicResult = amazonSNSClient.createTopic(createTopicRequest);
		System.out.println("Topic2 Result...."+ topicResult);
		
		ListTopicsResult topicsList = amazonSNSClient.listTopics();
		topicsList.getTopics().stream().forEach(System.out::println);
	}
	
	
	public void sendMessage(String topic, String message) {
		PublishRequest request = new PublishRequest();
		request.setMessage(message);
		request.setTopicArn(getTopicNameVsDetails().get(topic));
		PublishResult publish = amazonSNSClient.publish(request);
		System.out.println(publish.getMessageId() + " " + 
		publish.getSdkResponseMetadata() + " " + publish.getSdkHttpMetadata());
	}
	
	private Map<String, String> getTopicNameVsDetails() {
		Map<String, String> topicMap = new LinkedHashMap<>();
		amazonSNSClient.listTopics().getTopics().forEach(a -> {
			topicMap.put(a.getTopicArn().split(":")[5], a.getTopicArn());
		});;
		return topicMap;
	}
	
	
	public void addSubscriptions() {
		Map<String, String> topicNameVsDetails = getTopicNameVsDetails();
		topicNameVsDetails.forEach((topic, arn)-> {
			SubscribeRequest subscribeRequest = new SubscribeRequest();
			subscribeRequest.setEndpoint("ramanjineyulu.kld@gmail.com");
			subscribeRequest.setTopicArn(arn);
			subscribeRequest.setProtocol("email");
			SubscribeResult result = amazonSNSClient.subscribe(subscribeRequest);
			System.out.println("SubscriptionArn--------------" + result.getSubscriptionArn());	
		});
		
	}
	

}
