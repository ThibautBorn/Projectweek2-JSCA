package ucll.project.db;

public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message, Throwable exception) {
        super(message, exception);
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable exception) {
        super(exception);
    }


}

