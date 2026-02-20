package DAO;

public class ErroDao extends RuntimeException {
    public ErroDao() {
        this("");
    }

    public ErroDao(String message) {
        super("Erro Dao: "+message);
    }

    public ErroDao(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroDao(Throwable cause) {
        super(cause);
    }

    public ErroDao(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
