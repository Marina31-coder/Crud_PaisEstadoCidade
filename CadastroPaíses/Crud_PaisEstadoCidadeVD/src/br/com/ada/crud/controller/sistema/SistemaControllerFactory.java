package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.impl.SistemaArmazenamentoDefinitivoController;
import br.com.ada.crud.controller.impl.SistemaArmazenamentoVolatilController;
import br.com.ada.crud.model.pais.dao.SistemaDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SistemaControllerFactory {

    private static final SistemaControllerFactory INSTANCE = new SistemaControllerFactory();
    private static final String CONTROLLER_TIPO = "pais.controller.tipo";
    private SistemaArmazenamentoTipo tipo;

    private SistemaControllerFactory() {
    }

    public static SistemaControllerFactory getInstance() {
        return INSTANCE;
    }

    // Factory method
    public SistemaController criar() {
        if (tipo == null) {
            carregarTipoArmazenamento();
        }

        SistemaDAOFactory daoFactory = SistemaDAOFactory.getInstance();
        SistemaController controller = null;

        if (tipo == SistemaArmazenamentoTipo.VOLATIL) {
            controller = new SistemaArmazenamentoVolatilController();
        } else if (tipo == SistemaArmazenamentoTipo.DEFINITIVO) {
            SistemaDAO sistemaDAO = daoFactory.create();
            controller = new SistemaArmazenamentoDefinitivoController(sistemaDAO);
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
