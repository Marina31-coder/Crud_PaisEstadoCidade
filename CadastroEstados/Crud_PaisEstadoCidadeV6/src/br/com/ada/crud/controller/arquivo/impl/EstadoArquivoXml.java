package br.com.ada.crud.controller.arquivo.impl;

import br.com.ada.crud.controller.arquivo.AbstractXmlArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.crud.model.estado.Estado;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoArquivoXml extends AbstractXmlArquivo implements EscritorArquivo<Estado>, LeitorArquivo<Estado> {

    public static final String EXTENSAO = ".xml";
    private String diretorio = "database/xml/estados";

    @Override
    public void escrever(Estado estado, String arquivo) throws IOException, ArquivoLeituraException {
        try {
            Document document = criarNovoDocumento();
            Element elementEstado = document.createElement("estado");
            document.appendChild(elementEstado);

            adicionarElemento(document, "id", estado.getIdEstado().toString(), elementEstado);
            adicionarElemento(document, "nomeEstado", estado.getNomeEstado(), elementEstado);
            adicionarElemento(document, "siglaEstado", estado.getSiglaEstado(), elementEstado);
            adicionarElemento(document, "siglaPais", estado.getSiglaPais(), elementEstado);


            escreverArquivo(diretorio, arquivo + EXTENSAO, document);
        } catch (ParserConfigurationException | TransformerException ex) {
            throw new ArquivoEscritaException("Falha na conversão do xml", ex);
        }
    }

    @Override
    public Estado apagar(String arquivo) throws IOException {
        Estado estado = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return estado;
    }

    @Override
    public Estado ler(String arquivo) throws IOException {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        return ler(arquivoASerLido);
    }

    @Override
    public List<Estado> ler() throws IOException {
        FilenameFilter filter = (dir, nomeDoArquivo) -> nomeDoArquivo.endsWith(EXTENSAO);

        List<Estado> paises = new ArrayList<>();
        File diretorioQueContemOsArquvios = new File(diretorio);
        for (File arquivoASerLido : diretorioQueContemOsArquvios.listFiles(filter)) {
            Estado estado = ler(arquivoASerLido);
            paises.add(estado);
        }
        return paises;
    }

    private Estado ler(File arquivo) throws IOException, ArquivoLeituraException {
        try {
            Document document = parse(arquivo);
            Element elementEstado = document.getDocumentElement();

            String id = getValorElemento("id", elementEstado);
            String nomeEstado = getValorElemento("nomeEstado", elementEstado);
            String siglaEstado = getValorElemento("siglaEstado", elementEstado);
            String siglaPais = getValorElemento("siglaPais", elementEstado);

            Estado estado = new Estado();
            estado.setIdEstado(UUID.fromString(id));
            estado.setNomeEstado(nomeEstado);
            estado.setSiglaEstado(siglaEstado);
            estado.setSiglaPais(siglaPais);

            return estado;
        } catch (ParserConfigurationException | SAXException ex) {
            throw new ArquivoLeituraException("Não foi ppossível efetuar a leitura do arquivo", ex);
        }
    }

}
