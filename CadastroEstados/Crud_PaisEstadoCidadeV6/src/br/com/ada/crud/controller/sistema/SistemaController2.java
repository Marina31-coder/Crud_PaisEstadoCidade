package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.model.estado.Estado;

import java.util.List;
import java.util.UUID;

//CRUD - Create, read, update and delete
public interface SistemaController2 {

    void cadastrarEstado(Estado estado) throws ControllerException;

    Estado lerEstado(UUID id) throws ControllerException;

    List<Estado> listarEstado() throws ControllerException;

    void updateEstado(UUID id, Estado estado) throws ControllerException;

    Estado deleteEstado(UUID id) throws ControllerException;

}
