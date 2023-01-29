package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.sistema.ControllerException;
import br.com.ada.crud.controller.sistema.SistemaController2;
import br.com.ada.crud.controller.sistema.SistemaValidacaoException;
import br.com.ada.crud.model.estado.Estado;
import br.com.ada.crud.model.estado.dao.DAOException;
import br.com.ada.crud.model.estado.dao.SistemaDAO_Estado;

import java.util.List;
import java.util.UUID;

public class SistemaArmazenamentoDefinitivoController2 implements SistemaController2 {

    private SistemaDAO_Estado sistemaDAOEstado;

    public SistemaArmazenamentoDefinitivoController2(
            SistemaDAO_Estado sistemaDAOEstado
    ) {
        this.sistemaDAOEstado = sistemaDAOEstado;
    }


    @Override
    public void cadastrarEstado(Estado estado) throws ControllerException {
        try {
            if (estado.getNomeEstado() == null) {
                throw new SistemaValidacaoException("Estado deve ter o nome preenchido");
            }
            estado.setIdEstado(UUID.randomUUID());
            sistemaDAOEstado.cadastrarEstado(estado);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao cadastrar", ex);
        }
    }

    @Override
    public Estado lerEstado(UUID id) throws ControllerException {
        try {
            return sistemaDAOEstado.buscarEstado(id);
        } catch (DAOException e) {
            throw new ControllerException("Falha ao ler", e);
        }
    }

    @Override
    public List<Estado> listarEstado() throws ControllerException {
        try {
            return sistemaDAOEstado.listarEstado();
        } catch (DAOException e) {
            throw new ControllerException("Falha ao ler", e);
        }
    }

    @Override
    public void updateEstado(UUID id, Estado estado) throws ControllerException {
        try {
            sistemaDAOEstado.atualizarEstado(id, estado);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao atualizar", ex);
        }
    }

    @Override
    public Estado deleteEstado(UUID id) throws ControllerException {
        try {
            return sistemaDAOEstado.apagarEstado(id);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao apagar", ex);
        }
    }

}
