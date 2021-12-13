package il.ac.hit;

public class CostMangerException extends Exception {

    /**
     * Exception C'tor that contain message.
     * @param message
     */
    public CostMangerException(String message) {
        super(message);
    }

    /**
     * Exception C'tor that contain message and cause.
     * @param message
     * @param cause
     */
    public CostMangerException(String message, Throwable cause) {
        super(message, cause);
    }

}
