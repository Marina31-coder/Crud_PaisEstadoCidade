package br.com.ada.crud.controller.arquivo.impl;

import br.com.ada.crud.controller.arquivo.AbstractBinarioArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.estado.Estado;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EstadoArquivoBinario extends AbstractBinarioArquivo<Estado> implements EscritorArquivo<Estado>, LeitorArquivo<Estado> {

    private static final String EXTENSAO = ".dat";
    private String diretorio = "database/binario/estados";

    @Override
    public void escrever(Estado estado, String arquivo) throws IOException {
        File file = new File(diretorio, arquivo + EXTENSAO);
        escrever(estado, file);
    }

    @Override
    public Estado apagar(String arquivo) throws IOException, ClassNotFoundException {
        Estado estado = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return estado;
    }

    public List<Estado> ler() throws IOException, ClassNotFoundException {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(EXTENSAO);

        List<Estado> estados = new ArrayList<>();
        File diretorio = new File(this.diretorio);

        for (File arquivo : diretorio.listFiles(filter)) {
            Estado estado = ler(arquivo);
            estados.add(estado);
        }
        return estados;
    }

    @Override
    public Estado ler(String arquivo) throws IOException, ClassNotFoundException {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        Estado estado = ler(arquivoASerLido);
        return estado;
    }

}
