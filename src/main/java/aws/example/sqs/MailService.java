package aws.example.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class MailService {

	@Autowired
	private AmazonSimpleEmailService amazonMail;

	/**
	 * Send mail.
	 * @param to
	 * @param messageBody
	 * @param subject
	 * @param from
	 */
	public void sendMail(String to, String messageBody, String subject, String from) {

		SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(to))
				.withMessage(new Message()
						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(messageBody)))
						.withSubject(new Content().withCharset("UTF-8").withData(subject)))
				.withSource(from);

		try {
			amazonMail.sendEmail(request);
			System.out.println("Email sent successfully!");
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
		}
	}
}
