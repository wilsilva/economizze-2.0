package br.com.williamsilva.economizze.exception;

/**
 * Created by williamsilva on 19/11/2016.
 */

public class ErroPersistenciaException extends RuntimeException {

    public ErroPersistenciaException() {
    }

    public ErroPersistenciaException(String message) {
        super(message);
    }

    public ErroPersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroPersistenciaException(Throwable cause) {
        super(cause);
    }
}
