import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GmailController {

    private final GmailService gmailService;

    public GmailController(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @GetMapping("/emails")
    public List<Email> getEmails() throws Exception {
        // Get the user ID.
        String userId = "me";

        // Create a query to get the last 200 emails.
        String q = "label:inbox";

        // Get the list of messages.
        ListMessagesResponse response = gmailService.getMessages(userId, q);

        // Create a list of emails.
        List<Email> emails = new ArrayList<>();

        // Iterate over the messages and add them to the list.
        for (Message message : response.getMessages()) {
            Email email = new Email();
            email.setSender(message.getPayload().getHeaders().get(0).getValue());
            email.setSubject(message.getPayload().getHeaders().get(1).getValue());
            emails.add(email);
        }

        // Return the list of emails.
        return emails;
    }
}
