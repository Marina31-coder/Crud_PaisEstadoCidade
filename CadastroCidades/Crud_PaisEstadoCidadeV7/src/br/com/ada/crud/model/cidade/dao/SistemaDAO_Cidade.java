package br.com.ada.crud.model.cidade.dao;

import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.UUID;

public interface SistemaDAO_Cidade {

    void cadastrarCidade(Cidade cidade) throws DAOException;

    List<Cidade> listarCidade() throws DAOException;

    Cidade buscarCidade(UUID id) throws DAOException;

    void atualizarCidade(UUID id, Cidade cidade) throws DAOException;

    Cidade apagarCidade(UUID id) throws DAOException;

}
