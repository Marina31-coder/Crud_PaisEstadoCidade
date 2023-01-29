package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.sistema.ControllerException;
import br.com.ada.crud.controller.sistema.SistemaController3;
import br.com.ada.crud.controller.sistema.SistemaValidacaoException;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.model.cidade.dao.DAOException;
import br.com.ada.crud.model.cidade.dao.SistemaDAO_Cidade;

import java.util.List;
import java.util.UUID;

public class SistemaArmazenamentoDefinitivoController3 implements SistemaController3 {

    private SistemaDAO_Cidade sistemaDAOCidade;

    public SistemaArmazenamentoDefinitivoController3(
            SistemaDAO_Cidade sistemaDAOCidade
    ) {
        this.sistemaDAOCidade = sistemaDAOCidade;
    }


    @Override
    public void cadastrarCidade(Cidade cidade) throws ControllerException {
        try {
            if (cidade.getNomeCidade() == null) {
                throw new SistemaValidacaoException("Cidade deve ter o nome preenchido");
            }
            cidade.setIdCidade(UUID.randomUUID());
            sistemaDAOCidade.cadastrarCidade(cidade);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao cadastrar cidade", ex);
        }
    }

    @Override
    public Cidade lerCidade(UUID id) throws ControllerException {
        try {
            return sistemaDAOCidade.buscarCidade(id);
        } catch (DAOException e) {
            throw new ControllerException("Falha ao ler", e);
        }
    }

    @Override
    public List<Cidade> listarCidade() throws ControllerException {
        try {
            return sistemaDAOCidade.listarCidade();
        } catch (DAOException e) {
            throw new ControllerException("Falha ao ler", e);
        }
    }

    @Override
    public void updateCidade(UUID id, Cidade cidade) throws ControllerException {
        try {
            sistemaDAOCidade.atualizarCidade(id, cidade);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao atualizar", ex);
        }
    }

    @Override
    public Cidade deleteCidade(UUID id) throws ControllerException {
        try {
            return sistemaDAOCidade.apagarCidade(id);
        } catch (DAOException ex) {
            throw new ControllerException("Falha ao apagar", ex);
        }
    }

}
