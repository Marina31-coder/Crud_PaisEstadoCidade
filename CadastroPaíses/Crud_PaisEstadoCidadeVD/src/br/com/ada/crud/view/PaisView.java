package br.com.ada.crud.view;

import br.com.ada.crud.controller.exception.SistemaNaoEncontrado;
import br.com.ada.crud.controller.sistema.ControllerException;
import br.com.ada.crud.controller.sistema.SistemaController;
import br.com.ada.crud.controller.sistema.SistemaValidacaoException;
import br.com.ada.crud.model.pais.Pais;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaisView {

    private SistemaController controller;
    private Scanner scanner;

    public PaisView(
            SistemaController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrarPais() {
        Pais pais = new Pais();

        System.out.println("\nInforme o nome do País:");
        String nomePais = scanner.nextLine();
        pais.setNomePais(nomePais);

        System.out.println("\nInforme a sigla do País:");
        String siglaPais = scanner.nextLine();
        pais.setSiglaPais(siglaPais);


        try {
            controller.cadastrarPais(pais);
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void listarPais(UUID id) {
        try {
            Pais pais = controller.lerPais(id);
            exibirPais(pais);
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void atualizarPais() {
        listarPais();
        System.out.println("\nInforme o número do País que deseja atualizar:");
        Integer numeroPais = scanner.nextInt();
        scanner.nextLine();
        try {
            Pais pais = controller.listarPais().get(numeroPais - 1);
            atualizarPais(pais);
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void atualizarPais(Pais pais) {
        exibirPais(pais);

        System.out.println("\nInforme o novo nome do País:");
        String nomePais = scanner.nextLine();
        pais.setNomePais(nomePais);

        System.out.println("\nInforme a nova sigla do País:");
        String siglaPais = scanner.nextLine();
        pais.setSiglaPais(siglaPais);

        try {
            controller.updatePais(pais.getId(), pais);
        } catch (SistemaNaoEncontrado ex) {
            System.out.println("\nPaís informado não existe nos registros. Tente novamente.");
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void apagarPais() {
        listarPais();
        System.out.println("\nInforme o número do País que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        try {
            Pais pais = controller.listarPais().get(numero - 1);
            apagarPais(pais.getId());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void apagarPais(UUID id) {
        try {
            Pais pais = controller.deletePais(id);
            System.out.println("\nO país apagado foi:");
            exibirPais(pais);
        } catch (SistemaNaoEncontrado ex) {
            System.out.println("\nPaís não encontrado. Não foi possível apagar o registro. Tente novamente!");
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void listarPais() {
        try {
            List<Pais> pais = controller.listarPais();
            System.out.println("| Número | Nome do País        | Sigla do País       |");
            for (int index = 0; index < pais.size(); index++) {
                System.out.print("| " + (index + 1) + "      ");
                exibirPais(pais.get(index));
            }
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void exibirPais(Pais pais) {
        System.out.print("| ");
        System.out.print(pais.getNomePais());
        System.out.print("    | ");
        System.out.print(pais.getSiglaPais());
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
                cadastrarPais();
                break;
            case 2:
                listarPais();
                break;
            case 3:
                atualizarPais();
                break;
            case 4:
                apagarPais();
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
