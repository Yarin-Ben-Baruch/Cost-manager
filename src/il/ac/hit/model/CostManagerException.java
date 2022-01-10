package il.ac.hit.model;

/**
 * A class that wraps all the existing problems in the model.
 */
public class CostManagerException extends Exception {

    /**
     * Exception Ctor that contain message.
     * @param message A message-type object that conveys a message to the user
     */
    public CostManagerException(String message) {
        super(message);
    }

    /**
     * Exception Ctor that contain message and cause.
     * @param message A message-type object that conveys a message to the user
     * @param cause The reason why was the program problem
     */
    public CostManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
