package br.com.ada.crud.controller.arquivo.impl;

import br.com.ada.crud.controller.arquivo.AbstractBinarioArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.cidade.Cidade;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CidadeArquivoBinario extends AbstractBinarioArquivo<Cidade> implements EscritorArquivo<Cidade>, LeitorArquivo<Cidade> {

    private static final String EXTENSAO = ".dat";
    private String diretorio = "database/binario/cidades";

    @Override
    public void escrever(Cidade cidade, String arquivo) throws IOException {
        File file = new File(diretorio, arquivo + EXTENSAO);
        escrever(cidade, file);
    }

    @Override
    public Cidade apagar(String arquivo) throws IOException, ClassNotFoundException {
        Cidade cidade = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return cidade;
    }

    public List<Cidade> ler() throws IOException, ClassNotFoundException {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(EXTENSAO);

        List<Cidade> cidades = new ArrayList<>();
        File diretorio = new File(this.diretorio);

        for (File arquivo : diretorio.listFiles(filter)) {
            Cidade cidade = ler(arquivo);
            cidades.add(cidade);
        }
        return cidades;
    }

    @Override
    public Cidade ler(String arquivo) throws IOException, ClassNotFoundException {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        Cidade cidade = ler(arquivoASerLido);
        return cidade;
    }

}
