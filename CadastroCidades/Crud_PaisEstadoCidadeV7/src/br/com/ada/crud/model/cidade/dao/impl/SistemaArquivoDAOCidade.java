package br.com.ada.crud.model.cidade.dao.impl;

import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.model.cidade.dao.DAOException;
import br.com.ada.crud.model.cidade.dao.SistemaDAO_Cidade;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SistemaArquivoDAOCidade implements SistemaDAO_Cidade {

    private EscritorArquivo<Cidade> escritor;
    private LeitorArquivo<Cidade> leitor;

    public SistemaArquivoDAOCidade(
            EscritorArquivo<Cidade> escritor,
            LeitorArquivo<Cidade> leitor
    ) {
        this.escritor = escritor;
        this.leitor = leitor;
    }

    @Override
    public void cadastrarCidade(Cidade cidade) throws DAOException {
        try {
            escritor.escrever(cidade, cidade.getIdCidade().toString());
        } catch (IOException | ArquivoEscritaException ex) {
            throw new DAOException("Não foi possível persistir essa cidade", ex);
        }
    }

    @Override
    public List<Cidade> listarCidade() throws DAOException {
        return listarCidade(5);
    }

    private List<Cidade> listarCidade(Integer contador) throws DAOException {
        try {
            if (contador == 0) {
                return Collections.emptyList();
            }
            return leitor.ler();
        } catch (IOException ex) {
            System.out.println("Não foi possível ler as lista de cidades");
            ex.printStackTrace();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex1) {
                throw new DAOException("Ocorreu falha na leitura da lista e não foi possível se recuperar", ex);
            }
            return listarCidade(contador - 1);
        } catch (ClassNotFoundException | ArquivoLeituraException ex) {
            throw new DAOException("Não foi possível consultar a lista", ex);
        }
    }

    @Override
    public Cidade buscarCidade(UUID id) throws DAOException {
        try {
            Cidade cidade = leitor.ler(id.toString());
            return cidade;
        } catch (IOException | ArquivoLeituraException | ClassNotFoundException ex) {
            throw new DAOException("Falha na consulta de cidade", ex);
        }
    }

    @Override
    public void atualizarCidade(UUID id, Cidade cidade) throws DAOException {
        try {
            escritor.escrever(cidade, id.toString());
        } catch (IOException | ArquivoEscritaException ex) {
            throw new DAOException("Falha na atualização", ex);
        }
    }

    @Override
    public Cidade apagarCidade(UUID id) throws DAOException {
        try {
            return escritor.apagar(id.toString());
        } catch (IOException | ClassNotFoundException | ArquivoEscritaException ex) {
            throw new DAOException("Falha ao apagar", ex);
        }
    }

}
