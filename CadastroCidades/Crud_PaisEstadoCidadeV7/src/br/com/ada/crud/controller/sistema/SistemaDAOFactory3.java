package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.impl.CidadeArquivoBinario;
import br.com.ada.crud.controller.arquivo.impl.CidadeArquivoXml;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.model.cidade.dao.PersistenciaTipo;
import br.com.ada.crud.model.cidade.dao.SistemaDAO_Cidade;
import br.com.ada.crud.model.cidade.dao.impl.SistemaArquivoDAOCidade;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SistemaDAOFactory3 {

    private static final SistemaDAOFactory3 INSTANCE = new SistemaDAOFactory3();
    private static final String PERSISTENCIA_TIPO = "sistema.persistencia.tipo";

    private PersistenciaTipo tipo;

    private SistemaDAOFactory3() {
    }

    public static SistemaDAOFactory3 getInstance() {
        return INSTANCE;
    }

    public SistemaDAO_Cidade create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        EscritorArquivo<Cidade> escritor = null;
        LeitorArquivo<Cidade> leitor = null;

        if (tipo == PersistenciaTipo.BINARIA) {
            escritor = new CidadeArquivoBinario();
            leitor = new CidadeArquivoBinario();
        } else if (tipo == PersistenciaTipo.XML) {
            escritor = new CidadeArquivoXml();
            leitor = new CidadeArquivoXml();
        }
        return new SistemaArquivoDAOCidade(
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
