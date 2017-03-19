package br.com.williamsilva.economizze.exception;

/**
 * Created by williamsilva on 11/14/16.
 */

public class SaldoInsuficienteException extends RuntimeException {


    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
