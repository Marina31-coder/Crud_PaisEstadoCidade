package br.com.ada.crud.model.pais.dao;

import br.com.ada.crud.model.pais.Pais;

import java.util.List;
import java.util.UUID;

public interface SistemaDAO {

    void cadastrar(Pais pais) throws DAOException;

    List<Pais> listar() throws DAOException;

    Pais buscar(UUID id) throws DAOException;

    void atualizar(UUID id, Pais pais) throws DAOException;

    Pais apagar(UUID id) throws DAOException;

}
