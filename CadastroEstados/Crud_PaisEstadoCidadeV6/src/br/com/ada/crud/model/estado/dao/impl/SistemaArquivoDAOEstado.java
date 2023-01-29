package br.com.ada.crud.model.estado.dao.impl;

import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.crud.model.estado.Estado;
import br.com.ada.crud.model.estado.dao.DAOException;
import br.com.ada.crud.model.estado.dao.SistemaDAO_Estado;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SistemaArquivoDAOEstado implements SistemaDAO_Estado {

    private EscritorArquivo<Estado> escritor;
    private LeitorArquivo<Estado> leitor;

    public SistemaArquivoDAOEstado(
            EscritorArquivo<Estado> escritor,
            LeitorArquivo<Estado> leitor
    ) {
        this.escritor = escritor;
        this.leitor = leitor;
    }

    @Override
    public void cadastrarEstado(Estado estado) throws DAOException {
        try {
            escritor.escrever(estado, estado.getIdEstado().toString());
        } catch (IOException | ArquivoEscritaException ex) {
            throw new DAOException("Não foi possível persistir esse país", ex);
        }
    }

    @Override
    public List<Estado> listarEstado() throws DAOException {
        return listarEstado(5);
    }

    private List<Estado> listarEstado(Integer contador) throws DAOException {
        try {
            if (contador == 0) {
                return Collections.emptyList();
            }
            return leitor.ler();
        } catch (IOException ex) {
            System.out.println("Não foi possível ler as lista de estados");
            ex.printStackTrace();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex1) {
                throw new DAOException("Ocorreu falha na leitura da lista e não foi possível se recuperar", ex);
            }
            return listarEstado(contador - 1);
        } catch (ClassNotFoundException | ArquivoLeituraException ex) {
            throw new DAOException("Não foi possível consultar a lista", ex);
        }
    }

    @Override
    public Estado buscarEstado(UUID id) throws DAOException {
        try {
            Estado estado = leitor.ler(id.toString());
            return estado;
        } catch (IOException | ArquivoLeituraException | ClassNotFoundException ex) {
            throw new DAOException("Falha na consulta de estado", ex);
        }
    }

    @Override
    public void atualizarEstado(UUID id, Estado estado) throws DAOException {
        try {
            escritor.escrever(estado, id.toString());
        } catch (IOException | ArquivoEscritaException ex) {
            throw new DAOException("Falha na atualização", ex);
        }
    }

    @Override
    public Estado apagarEstado(UUID id) throws DAOException {
        try {
            return escritor.apagar(id.toString());
        } catch (IOException | ClassNotFoundException | ArquivoEscritaException ex) {
            throw new DAOException("Falha ao apagar", ex);
        }
    }

}
