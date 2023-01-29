package br.com.ada.crud.model.estado.dao;

import br.com.ada.crud.model.estado.Estado;

import java.util.List;
import java.util.UUID;

public interface SistemaDAO_Estado {

    void cadastrarEstado(Estado estado) throws DAOException;

    List<Estado> listarEstado() throws DAOException;

    Estado buscarEstado(UUID id) throws DAOException;

    void atualizarEstado(UUID id, Estado estado) throws DAOException;

    Estado apagarEstado(UUID id) throws DAOException;

}
