package br.com.ada.crud;

import br.com.ada.crud.controller.sistema.SistemaController3;
import br.com.ada.crud.controller.sistema.SistemaControllerFactory3;
import br.com.ada.crud.view.CidadeView;

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

        SistemaController3 sistemaController3 = SistemaControllerFactory3
                .getInstance().criar();

        CidadeView view = new CidadeView(
                sistemaController3,
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }

}
