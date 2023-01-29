package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.impl.PaisArquivoBinario;
import br.com.ada.crud.controller.arquivo.impl.PaisArquivoXml;
import br.com.ada.crud.model.pais.Pais;
import br.com.ada.crud.model.pais.dao.PersistenciaTipo;
import br.com.ada.crud.model.pais.dao.SistemaDAO;
import br.com.ada.crud.model.pais.dao.impl.SistemaArquivoDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SistemaDAOFactory {

    private static final SistemaDAOFactory INSTANCE = new SistemaDAOFactory();
    private static final String PERSISTENCIA_TIPO = "pais.persistencia.tipo";

    private PersistenciaTipo tipo;

    private SistemaDAOFactory() {
    }

    public static SistemaDAOFactory getInstance() {
        return INSTANCE;
    }

    public SistemaDAO create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        EscritorArquivo<Pais> escritor = null;
        LeitorArquivo<Pais> leitor = null;

        if (tipo == PersistenciaTipo.BINARIA) {
            escritor = new PaisArquivoBinario();
            leitor = new PaisArquivoBinario();
        } else if (tipo == PersistenciaTipo.XML) {
            escritor = new PaisArquivoXml();
            leitor = new PaisArquivoXml();
        }
        return new SistemaArquivoDAO(
                escritor,
                leitor
        );
    }

    private void carregarTipoPersistencia() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipo.valueOf(valorNoArquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }

}
