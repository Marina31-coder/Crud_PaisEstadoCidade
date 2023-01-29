package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.impl.SistemaArmazenamentoDefinitivoController3;
import br.com.ada.crud.controller.impl.SistemaArmazenamentoVolatilController3;
import br.com.ada.crud.model.cidade.dao.SistemaDAO_Cidade;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SistemaControllerFactory3 {

    private static final SistemaControllerFactory3 INSTANCE = new SistemaControllerFactory3();
    private static final String CONTROLLER_TIPO = "sistema.controller.tipo";
    private SistemaArmazenamentoTipo tipo;

    private SistemaControllerFactory3() {
    }

    public static SistemaControllerFactory3 getInstance() {
        return INSTANCE;
    }

    // Factory method
    public SistemaController3 criar() {
        if (tipo == null) {
            carregarTipoArmazenamento();
        }

        SistemaDAOFactory3 daoFactory = SistemaDAOFactory3.getInstance();
        SistemaController3 controller = null;

        if (tipo == SistemaArmazenamentoTipo.VOLATIL) {
            controller = new SistemaArmazenamentoVolatilController3();
        } else if (tipo == SistemaArmazenamentoTipo.DEFINITIVO) {
            SistemaDAO_Cidade sistemaDAOCidade = daoFactory.create();
            controller = new SistemaArmazenamentoDefinitivoController3(sistemaDAOCidade);
        } else {
            throw new RuntimeException("Nenhuma implementação disponível");
        }
        return controller;
    }

    private void carregarTipoArmazenamento() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorDoArquivo = properties.getProperty(CONTROLLER_TIPO);
            tipo = SistemaArmazenamentoTipo.valueOf(valorDoArquivo);
        }catch(IOException ex) {
            throw new RuntimeException("Falha no carregamento do arquivo de propriedaes.",ex);
        }
    }

}
