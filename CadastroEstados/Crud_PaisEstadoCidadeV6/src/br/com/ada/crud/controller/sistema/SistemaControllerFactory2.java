package br.com.ada.crud.controller.sistema;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.impl.SistemaArmazenamentoDefinitivoController2;
import br.com.ada.crud.controller.impl.SistemaArmazenamentoVolatilController2;
import br.com.ada.crud.model.estado.dao.SistemaDAO_Estado;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SistemaControllerFactory2 {

    private static final SistemaControllerFactory2 INSTANCE = new SistemaControllerFactory2();
    private static final String CONTROLLER_TIPO = "sistema.controller.tipo";
    private SistemaArmazenamentoTipo tipo;

    private SistemaControllerFactory2() {
    }

    public static SistemaControllerFactory2 getInstance() {
        return INSTANCE;
    }

    // Factory method
    public SistemaController2 criar() {
        if (tipo == null) {
            carregarTipoArmazenamento();
        }

        SistemaDAOFactory2 daoFactory = SistemaDAOFactory2.getInstance();
        SistemaController2 controller = null;

        if (tipo == SistemaArmazenamentoTipo.VOLATIL) {
            controller = new SistemaArmazenamentoVolatilController2();
        } else if (tipo == SistemaArmazenamentoTipo.DEFINITIVO) {
            SistemaDAO_Estado sistemaDAOEstado = daoFactory.create();
            controller = new SistemaArmazenamentoDefinitivoController2(sistemaDAOEstado);
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
