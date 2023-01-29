package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.model.pais.Pais;

import java.util.List;
import java.util.UUID;

//CRUD - Create, read, update and delete
public interface SistemaController {

    void cadastrarPais(Pais pais) throws ControllerException;

    Pais lerPais(UUID id) throws ControllerException;

    List<Pais> listarPais() throws ControllerException;

    void updatePais(UUID id, Pais pais) throws ControllerException;

    Pais deletePais(UUID id) throws ControllerException;

}
