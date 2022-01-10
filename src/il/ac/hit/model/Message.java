package il.ac.hit.model;

/**
 * A class that wraps the messages for the user.
 */
public class Message {
    private String content;

    /**
     * A constructor that receives a string and saves it for message transmission.
     * @param content string message.
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * basic getter.
     * @return String(message).
     */
    public String getText() {
        return content;
    }
}
