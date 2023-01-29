package br.com.ada.crud.controller.arquivo.exception;

public class ArquivoEscritaException extends RuntimeException {

    public ArquivoEscritaException(String message, Exception cause) {
        super(message, cause);
    }

}
