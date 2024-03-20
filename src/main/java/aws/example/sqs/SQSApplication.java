package aws.example.sqs;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.amazonaws.services.sqs.model.Message;

@SpringBootApplication
public class SQSApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SQSApplication.class, args);

		String queueUrl = "https://sqs.ap-southeast-1.amazonaws.com/730335398753/training_sqs";

		SQSService sqsService = context.getBean(SQSService.class);
		MailService mailService = context.getBean(MailService.class);
		WebhookService webhookService = context.getBean(WebhookService.class);

		// Send Message To Queue.
		sqsService.sendMessageToQueue(queueUrl, "msg send from app");

		// Receive Messages From Queue.
		List<Message> msgList = sqsService.receiveMessagesFromQueue(queueUrl).getMessages();
		String body = "";

		for (Message message : msgList) {
			body = message.getBody();
			System.out.println(body);
			
			// send body to mail.
			mailService.sendMail("huongdtm@leadsgen.asia", body, "mail send from app", "huongdtm@leadsgen.asia");
			
			// send body to web hook.
			webhookService.sendToWebhook("https://webhook.site/271a2396-53b4-4729-bc92-8ca3df571cec", body);
		}
		context.close();
	}
}
