package aws.example.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Send message to Web hook.
	 * @param webhookUrl
	 * @param message
	 */
	public void sendToWebhook(String webhookUrl, String message) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, requestEntity,
				String.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Message sent successfully to webhook.");
		} else {
			System.out.println(
					"Failed to send message to webhook. Status code: " + responseEntity.getStatusCode().value());
		}
	}
}
