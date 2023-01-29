package br.com.ada.crud.view;

import br.com.ada.crud.controller.exception.SistemaNaoEncontrado;
import br.com.ada.crud.controller.sistema.ControllerException;
import br.com.ada.crud.controller.sistema.SistemaController2;
import br.com.ada.crud.controller.sistema.SistemaValidacaoException;
import br.com.ada.crud.model.estado.Estado;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class EstadoView {

    private SistemaController2 controller;
    private Scanner scanner;

    public EstadoView(
            SistemaController2 controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrarEstado() {
        Estado estado = new Estado();

        System.out.println("\nInforme o nome do Estado:");
        String nomeEstado = scanner.nextLine();
        estado.setNomeEstado(nomeEstado);

        System.out.println("\nInforme a sigla do Estado:");
        String siglaEstado = scanner.nextLine();
        estado.setSiglaEstado(siglaEstado);

        System.out.println("\nInforme a sigla do País ao qual essa Estado pertence:");
        String siglaPais = scanner.nextLine();
        estado.setSiglaPais(siglaPais);

        try {
            controller.cadastrarEstado(estado);
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void listarEstado(UUID id) {
        try {
            Estado estado = controller.lerEstado(id);
            exibirEstado(estado);
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void atualizarEstado() {
        listarEstado();
        System.out.println("\nInforme o número do Estado que deseja atualizar:");
        Integer numeroEstado = scanner.nextInt();
        scanner.nextLine();
        try {
            Estado estado = controller.listarEstado().get(numeroEstado - 1);
            atualizarEstado(estado);
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void atualizarEstado(Estado estado) {
        exibirEstado(estado);

        System.out.println("\nInforme o novo nome do Estado:");
        String nomeEstado = scanner.nextLine();
        estado.setNomeEstado(nomeEstado);

        System.out.println("\nInforme a nova sigla do Estado:");
        String siglaEstado = scanner.nextLine();
        estado.setSiglaEstado(siglaEstado);

        System.out.println("\nInforme a nova sigla do País ao qual este Estado pertence:");
        String siglaPais = scanner.nextLine();
        estado.setSiglaPais(siglaPais);

        try {
            controller.updateEstado(estado.getIdEstado(), estado);
        } catch (SistemaNaoEncontrado ex) {
            System.out.println("\nEstado informado não existe nos registros. Tente novamente.");
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void apagarEstado() {
        listarEstado();
        System.out.println("\nInforme o número do Estado que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        try {
            Estado estado = controller.listarEstado().get(numero - 1);
            apagarEstado(estado.getIdEstado());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void apagarEstado(UUID id) {
        try {
            Estado estado = controller.deleteEstado(id);
            System.out.println("\nO Estado apagado foi:");
            exibirEstado(estado);
        } catch (SistemaNaoEncontrado ex) {
            System.out.println("\nEstado não encontrado. Não foi possível apagar o registro. Tente novamente!");
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void listarEstado() {
        try {
            List<Estado> estados = controller.listarEstado();
            System.out.println("| Número | Nome do Estado        | Sigla do Estado       |" +
                    "Sigla do País ao qual o Estado pertence       |");
            for (int index = 0; index < estados.size(); index++) {
                System.out.print("| " + (index + 1) + "      ");
                exibirEstado(estados.get(index));
            }
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void exibirEstado(Estado estado) {
        System.out.print("| ");
        System.out.print(estado.getNomeEstado());
        System.out.print("    | ");
        System.out.print(estado.getSiglaEstado());
        System.out.print("    |  ");
        System.out.print(estado.getSiglaPais());
        System.out.print("    |  ");
        System.out.println("");
    }

    public void exibirOpcoes() {
        System.out.println("");
        System.out.println("Infome a opção desejada:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Sair");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                cadastrarEstado();
                break;
            case 2:
                listarEstado();
                break;
            case 3:
                atualizarEstado();
                break;
            case 4:
                apagarEstado();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida!");
        }
        exibirOpcoes();
    }
}
