package br.com.ada.crud.controller.arquivo.exception;

public class ArquivoLeituraException extends RuntimeException{

    public ArquivoLeituraException(String message, Exception cause) {
        super(message, cause);
    }

}
