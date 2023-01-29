package br.com.ada.crud.view;

import br.com.ada.crud.controller.exception.SistemaNaoEncontrado;
import br.com.ada.crud.controller.sistema.ControllerException;
import br.com.ada.crud.controller.sistema.SistemaController3;
import br.com.ada.crud.controller.sistema.SistemaValidacaoException;
import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CidadeView {

    private SistemaController3 controller;
    private Scanner scanner;

    public CidadeView(
            SistemaController3 controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrarCidade() {
        Cidade cidade = new Cidade();

        System.out.println("\nInforme o nome da Cidade:");
        String nomeCidade = scanner.nextLine();
        cidade.setNomeCidade(nomeCidade);

        System.out.println("\nInforme a sigla do Estado ao qual a cidade pertence:");
        String siglaEstado = scanner.nextLine();
        cidade.setSiglaEstado(siglaEstado);


        try {
            controller.cadastrarCidade(cidade);
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void listarCidade(UUID id) {
        try {
            Cidade cidade = controller.lerCidade(id);
            exibirCidade(cidade);
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void atualizarCidade() {
        listarCidade();
        System.out.println("\nInforme o número da Cidade que deseja atualizar:");
        Integer numeroCidade = scanner.nextInt();
        scanner.nextLine();
        try {
            Cidade cidade = controller.listarCidade().get(numeroCidade - 1);
            atualizarCidade(cidade);
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void atualizarCidade(Cidade cidade) {
        exibirCidade(cidade);

        System.out.println("\nInforme o novo nome da Cidade:");
        String nomeCidade = scanner.nextLine();
        cidade.setNomeCidade(nomeCidade);

        System.out.println("\nInforme a nova sigla do Estado ao qual a Cidade pertence:");
        String siglaEstado = scanner.nextLine();
        cidade.setSiglaEstado(siglaEstado);


        try {
            controller.updateCidade(cidade.getIdCidade(), cidade);
        } catch (SistemaNaoEncontrado ex) {
            System.out.println("\nCidade informada não existe nos registros. Tente novamente.");
        } catch (SistemaValidacaoException ex) {
            System.out.println(ex.getMessage());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void apagarCidade() {
        listarCidade();
        System.out.println("\nInforme o número do Estado que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        try {
            Cidade cidade = controller.listarCidade().get(numero - 1);
            apagarCidade(cidade.getIdCidade());
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void apagarCidade(UUID id) {
        try {
            Cidade cidade = controller.deleteCidade(id);
            System.out.println("\nA cidade apagada foi:");
            exibirCidade(cidade);
        } catch (SistemaNaoEncontrado ex) {
            System.out.println("\nCidade não encontrada. Não foi possível apagar o registro. Tente novamente!");
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void listarCidade() {
        try {
            List<Cidade> cidades2 = controller.listarCidade();
            System.out.println("| Número | Nome da Cidade        |Sigla do Estado ao qual a Cidade pertence       |");
            for (int index = 0; index < cidades2.size(); index++) {
                System.out.print("| " + (index + 1) + "      ");
                exibirCidade(cidades2.get(index));
            }
        } catch (ControllerException ex) {
            System.out.println("\nOcorreu um erro, tente novamente mais tarde");
            ex.printStackTrace();
        }
    }

    public void exibirCidade(Cidade cidade) {
        System.out.print("| ");
        System.out.print(cidade.getNomeCidade());
        System.out.print("    | ");
        System.out.print(cidade.getSiglaEstado());
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
                cadastrarCidade();
                break;
            case 2:
                listarCidade();
                break;
            case 3:
                atualizarCidade();
                break;
            case 4:
                apagarCidade();
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
