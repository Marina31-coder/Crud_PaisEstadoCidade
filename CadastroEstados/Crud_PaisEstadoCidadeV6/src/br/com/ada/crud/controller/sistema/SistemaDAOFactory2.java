package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.impl.EstadoArquivoBinario;
import br.com.ada.crud.controller.arquivo.impl.EstadoArquivoXml;
import br.com.ada.crud.model.estado.Estado;
import br.com.ada.crud.model.estado.dao.PersistenciaTipo;
import br.com.ada.crud.model.estado.dao.SistemaDAO_Estado;
import br.com.ada.crud.model.estado.dao.impl.SistemaArquivoDAOEstado;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SistemaDAOFactory2 {

    private static final SistemaDAOFactory2 INSTANCE = new SistemaDAOFactory2();
    private static final String PERSISTENCIA_TIPO = "sistema.persistencia.tipo";

    private PersistenciaTipo tipo;

    private SistemaDAOFactory2() {
    }

    public static SistemaDAOFactory2 getInstance() {
        return INSTANCE;
    }

    public SistemaDAO_Estado create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        EscritorArquivo<Estado> escritor = null;
        LeitorArquivo<Estado> leitor = null;

        if (tipo == PersistenciaTipo.BINARIA) {
            escritor = new EstadoArquivoBinario();
            leitor = new EstadoArquivoBinario();
        } else if (tipo == PersistenciaTipo.XML) {
            escritor = new EstadoArquivoXml();
            leitor = new EstadoArquivoXml();
        }
        return new SistemaArquivoDAOEstado(
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
