package io.kowsu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SendMessageResult;

@Service
public class SqSService {
	
	@Autowired
	AmazonSQS amazonSQS;
	
	
	public void createQueue(String name) {
		CreateQueueRequest queueRequest = new CreateQueueRequest(); 
		queueRequest.setQueueName(name);
		queueRequest.setSdkRequestTimeout(1000);
		CreateQueueResult createQueueResult = amazonSQS.createQueue(queueRequest);
		System.out.println("Queue creation response " + createQueueResult);
	}
	
	public Map<String, String> getQueueVsDetails() {
		Map<String, String> urls = new LinkedHashMap<>();
		ListQueuesResult queues = amazonSQS.listQueues();
		System.out.println(queues.getQueueUrls());
		queues.getQueueUrls().stream().forEach(a -> {
			urls.put(a.substring(a.lastIndexOf("/")+1), a);
		});
		return urls;
	}
	
	public void sendMessage(String queue, String eventPayload) {
		SendMessageResult sendMessageResult = 
				amazonSQS.sendMessage(getQueueVsDetails().get(queue), eventPayload);
		System.out.println("SQSSendResultResponse.........." + sendMessageResult);
	}

}
