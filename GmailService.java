import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class GmailService {

    private static final String APPLICATION_NAME = "Gmail Service";

    private Gmail gmail;

    public GmailService(GoogleCredential credential) {
        this.gmail = new Gmail(new NetHttpTransport(), new JacksonFactory(), credential);
    }

    public ListMessagesResponse getMessages(String userId, String q) throws Exception {
        return gmail.users().messages().list(userId).setQ(q).execute();
    }

    public Message getMessage(String userId, String id) throws Exception {
        return gmail.users().messages().get(userId, id).execute();
    }
}
