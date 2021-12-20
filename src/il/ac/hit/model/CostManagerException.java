package il.ac.hit.model;

public class CostManagerException extends Exception {

    /**
     * Exception C'tor that contain message.
     * @param message
     */
    public CostManagerException(String message) {
        super(message);
    }

    /**
     * Exception C'tor that contain message and cause.
     * @param message
     * @param cause
     */
    public CostManagerException(String message, Throwable cause) {
        super(message, cause);
    }

}
