package util;


import java.util.List;
import java.util.Properties;


import javax.mail.*;

/**
 * Created by vuthaiduong on 1/2/17.
 */
public class FetchingMail {

    public static List<StringBuilder> fetchMailBody(String pop3Host, String storeType, String user,
                             String password) {
        List<StringBuilder> list = null;
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", storeType);
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(pop3Host, user, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                StringBuilder body = getMailBody(message);
                list.add(body);
            }

            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {

        String host = "pop.gmail.com";
        String mailStoreType = "pop3";
        String username =
                "testforever54321@gmail.com";
        String password = "Duong123";

        fetchMailBody(host, mailStoreType, username, password);
    }

    public static StringBuilder getMailBody(Part p) throws Exception {

        StringBuilder result = new StringBuilder();
        if (p instanceof Message)

            if (findMailBySubject((Message) p, "Welcome to helloPay")) {

                if (p.isMimeType("text/plain")) {
                    result.append(p.getContent());
                }

                else if (p.isMimeType("multipart/*")) {

                    Multipart parts = (Multipart) p.getContent();
                    for (int i = 0; i < parts.getCount(); i++) {
                        BodyPart part = parts.getBodyPart(i);
                        if (part.getContent() instanceof String) {
                            result.append(part.getContent());
                        }
                    }
                }
            }
        return result;
    }

    public static boolean findMailBySubject(Message m, String subject) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;

        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }

        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());

        if(m.getSubject().contains(subject)){
            return true;
        } else {
            return false;
        }
    }
}
