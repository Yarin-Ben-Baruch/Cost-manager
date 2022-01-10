package il.ac.hit.model;

/**
 * A class that wraps the messages for the user.
 */
public class Message {

    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getText() {
        return content;
    }

}
