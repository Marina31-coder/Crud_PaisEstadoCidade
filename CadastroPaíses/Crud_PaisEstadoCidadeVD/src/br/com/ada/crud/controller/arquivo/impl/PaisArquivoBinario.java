package br.com.ada.crud.controller.arquivo.impl;

import br.com.ada.crud.controller.arquivo.AbstractBinarioArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.pais.Pais;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaisArquivoBinario extends AbstractBinarioArquivo<Pais> implements EscritorArquivo<Pais>, LeitorArquivo<Pais> {

    private static final String EXTENSAO = ".dat";
    private String diretorio = "database/binario/paises";

    @Override
    public void escrever(Pais pais, String arquivo) throws IOException {
        File file = new File(diretorio, arquivo + EXTENSAO);
        escrever(pais, file);
    }

    @Override
    public Pais apagar(String arquivo) throws IOException, ClassNotFoundException {
        Pais pais = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return pais;
    }

    public List<Pais> ler() throws IOException, ClassNotFoundException {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(EXTENSAO);

        List<Pais> paises = new ArrayList<>();
        File diretorio = new File(this.diretorio);

        for (File arquivo : diretorio.listFiles(filter)) {
            Pais pais = ler(arquivo);
            paises.add(pais);
        }
        return paises;
    }

    @Override
    public Pais ler(String arquivo) throws IOException, ClassNotFoundException {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        Pais pais = ler(arquivoASerLido);
        return pais;
    }

}
