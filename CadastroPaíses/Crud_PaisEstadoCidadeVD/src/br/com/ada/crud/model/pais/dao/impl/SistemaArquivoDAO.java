package br.com.ada.crud.model.pais.dao.impl;

import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.crud.model.pais.Pais;
import br.com.ada.crud.model.pais.dao.DAOException;
import br.com.ada.crud.model.pais.dao.SistemaDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SistemaArquivoDAO implements SistemaDAO {

    private EscritorArquivo<Pais> escritor;
    private LeitorArquivo<Pais> leitor;

    public SistemaArquivoDAO(
            EscritorArquivo<Pais> escritor,
            LeitorArquivo<Pais> leitor
    ) {
        this.escritor = escritor;
        this.leitor = leitor;
    }

    @Override
    public void cadastrar(Pais pais) throws DAOException {
        try {
            escritor.escrever(pais, pais.getId().toString());
        } catch (IOException | ArquivoEscritaException ex) {
            throw new DAOException("Não foi possível persistir esse país", ex);
        }
    }

    @Override
    public List<Pais> listar() throws DAOException {
        return listar(5);
    }

    private List<Pais> listar(Integer contador) throws DAOException {
        try {
            if (contador == 0) {
                return Collections.emptyList();
            }
            return leitor.ler();
        } catch (IOException ex) {
            System.out.println("Não foi possível ler as lista de países");
            ex.printStackTrace();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex1) {
                throw new DAOException("Ocorreu falha na leitura da lista e não foi possível se recuperar", ex);
            }
            return listar(contador - 1);
        } catch (ClassNotFoundException | ArquivoLeituraException ex) {
            throw new DAOException("Não foi possível consultar a lista", ex);
        }
    }

    @Override
    public Pais buscar(UUID id) throws DAOException {
        try {
            Pais pais = leitor.ler(id.toString());
            return pais;
        } catch (IOException | ArquivoLeituraException | ClassNotFoundException ex) {
            throw new DAOException("Falha na consulta de país", ex);
        }
    }

    @Override
    public void atualizar(UUID id, Pais pais) throws DAOException {
        try {
            escritor.escrever(pais, id.toString());
        } catch (IOException | ArquivoEscritaException ex) {
            throw new DAOException("Falha na atualização", ex);
        }
    }

    @Override
    public Pais apagar(UUID id) throws DAOException {
        try {
            return escritor.apagar(id.toString());
        } catch (IOException | ClassNotFoundException | ArquivoEscritaException ex) {
            throw new DAOException("Falha ao apagar", ex);
        }
    }

}
