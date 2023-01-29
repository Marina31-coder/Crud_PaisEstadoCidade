package br.com.ada.crud.controller.arquivo;

import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;

import java.io.IOException;
import java.util.List;

public interface LeitorArquivo<T> {

    T ler(String arquivo) throws IOException, ClassNotFoundException, ArquivoLeituraException;

    List<T> ler() throws IOException, ClassNotFoundException, ArquivoLeituraException;

}
