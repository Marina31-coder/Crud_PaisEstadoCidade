package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.sistema.ControllerException;
import br.com.ada.crud.controller.sistema.SistemaController;
import br.com.ada.crud.controller.sistema.SistemaValidacaoException;
import br.com.ada.crud.model.pais.Pais;
import br.com.ada.crud.model.pais.dao.DAOException;
import br.com.ada.crud.model.pais.dao.SistemaDAO;

import java.util.List;
import java.util.UUID;

public class SistemaArmazenamentoDefinitivoController implements SistemaController {

    private SistemaDAO sistemaDAO;

    public SistemaArmazenamentoDefinitivoController(
            SistemaDAO sistemaDAO
    ) {
        this.sistemaDAO = sistemaDAO;
    }


    @Override
    public void cadastrarPais(Pais pais) throws ControllerException {
        try {
            if (pais.getNomePais() == null) {
                throw new SistemaValidacaoException("Pessoa deve ter o nome preenchido");
            }
            pais.setId(UUID.randomUUID());
            sistemaDAO.cadastrar(pais);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao cadastrar", ex);
        }
    }

    @Override
    public Pais lerPais(UUID id) throws ControllerException {
        try {
            return sistemaDAO.buscar(id);
        } catch (DAOException e) {
            throw new ControllerException("Falha ao ler", e);
        }
    }

    @Override
    public List<Pais> listarPais() throws ControllerException {
        try {
            return sistemaDAO.listar();
        } catch (DAOException e) {
            throw new ControllerException("Falha ao ler", e);
        }
    }

    @Override
    public void updatePais(UUID id, Pais pais) throws ControllerException {
        try {
            sistemaDAO.atualizar(id, pais);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao atualizar", ex);
        }
    }

    @Override
    public Pais deletePais(UUID id) throws ControllerException {
        try {
            return sistemaDAO.apagar(id);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao apagar", ex);
        }
    }

}
