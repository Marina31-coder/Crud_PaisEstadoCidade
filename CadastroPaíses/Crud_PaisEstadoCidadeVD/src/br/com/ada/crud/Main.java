package br.com.ada.crud;

import br.com.ada.crud.controller.sistema.SistemaController;
import br.com.ada.crud.controller.sistema.SistemaControllerFactory;
import br.com.ada.crud.view.PaisView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable ex) {
                System.out.println("Exceção não tratada pela aplicação");
                ex.printStackTrace();
            }
        });

        SistemaController sistemaController = SistemaControllerFactory
                .getInstance().criar();

        PaisView view = new PaisView(
                sistemaController,
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }

}
