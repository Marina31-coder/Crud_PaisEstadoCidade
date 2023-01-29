package br.com.ada.crud.model.estado.dao;

public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception cause) {
        super(message, cause);
    }
}
