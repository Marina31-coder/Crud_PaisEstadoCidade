package br.com.ada.crud;

import br.com.ada.crud.controller.sistema.SistemaController2;
import br.com.ada.crud.controller.sistema.SistemaControllerFactory2;
import br.com.ada.crud.view.EstadoView;

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

        SistemaController2 sistemaController2 = SistemaControllerFactory2
                .getInstance().criar();

        EstadoView view = new EstadoView(
                sistemaController2,
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }

}
