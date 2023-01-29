package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.UUID;

//CRUD - Create, read, update and delete
public interface SistemaController3 {

    void cadastrarCidade(Cidade cidade) throws ControllerException;

    Cidade lerCidade(UUID id) throws ControllerException;

    List<Cidade> listarCidade() throws ControllerException;

    void updateCidade(UUID id, Cidade cidade) throws ControllerException;

    Cidade deleteCidade(UUID id) throws ControllerException;

}
