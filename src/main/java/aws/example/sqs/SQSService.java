package aws.example.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Service
public class SQSService {

	@Autowired
	private AmazonSQS amazonSQS;

	/**
	 * Receive Messages From Queue.
	 * @param queueUrl
	 * @return Result of the ReceiveMessage operation returned by the service.
	 */
	public ReceiveMessageResult receiveMessagesFromQueue(String queueUrl) {
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withQueueUrl(queueUrl)
				.withMaxNumberOfMessages(3);

		return amazonSQS.receiveMessage(receiveMessageRequest);
	}

	/**
	 * Send Message To Queue.
	 * @param queueUrl
	 * @param messageBody
	 */
	public void sendMessageToQueue(String queueUrl, String messageBody) {
		SendMessageRequest send_msg_request = new SendMessageRequest().withQueueUrl(queueUrl)
				.withMessageBody(messageBody);
		amazonSQS.sendMessage(send_msg_request);
	}
}
