package br.com.williamsilva.economizze.exception;

/**
 * Created by williamsilva on 19/11/2016.
 */
public class CampoNuloException extends RuntimeException {
    public CampoNuloException() {
    }

    public CampoNuloException(String message) {
        super(message);
    }

    public CampoNuloException(String message, Throwable cause) {
        super(message, cause);
    }

    public CampoNuloException(Throwable cause) {
        super(cause);
    }
}
