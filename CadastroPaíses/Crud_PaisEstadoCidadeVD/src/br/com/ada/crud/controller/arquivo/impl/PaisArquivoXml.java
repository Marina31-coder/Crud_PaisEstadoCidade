package br.com.ada.crud.controller.arquivo.impl;

import br.com.ada.crud.controller.arquivo.AbstractXmlArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.crud.model.pais.Pais;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisArquivoXml extends AbstractXmlArquivo implements EscritorArquivo<Pais>, LeitorArquivo<Pais> {

    public static final String EXTENSAO = ".xml";
    private String diretorio = "database/xml/paises";

    @Override
    public void escrever(Pais pais, String arquivo) throws IOException, ArquivoLeituraException {
        try {
            Document document = criarNovoDocumento();
            Element elementPais = document.createElement("pais");
            document.appendChild(elementPais);

            adicionarElemento(document, "id", pais.getId().toString(), elementPais);
            adicionarElemento(document, "nomePais", pais.getNomePais(), elementPais);
            adicionarElemento(document, "siglaPais", pais.getSiglaPais(), elementPais);

            escreverArquivo(diretorio, arquivo + EXTENSAO, document);
        } catch (ParserConfigurationException | TransformerException ex) {
            throw new ArquivoEscritaException("Falha na conversão do xml", ex);
        }
    }

    @Override
    public Pais apagar(String arquivo) throws IOException {
        Pais pais = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return pais;
    }

    @Override
    public Pais ler(String arquivo) throws IOException {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        return ler(arquivoASerLido);
    }

    @Override
    public List<Pais> ler() throws IOException {
        FilenameFilter filter = (dir, nomeDoArquivo) -> nomeDoArquivo.endsWith(EXTENSAO);

        List<Pais> paises = new ArrayList<>();
        File diretorioQueContemOsArquvios = new File(diretorio);
        for (File arquivoASerLido : diretorioQueContemOsArquvios.listFiles(filter)) {
            Pais pais = ler(arquivoASerLido);
            paises.add(pais);
        }
        return paises;
    }

    private Pais ler(File arquivo) throws IOException, ArquivoLeituraException {
        try {
            Document document = parse(arquivo);
            Element elementPais = document.getDocumentElement();

            String id = getValorElemento("id", elementPais);
            String nomePais = getValorElemento("nomePais", elementPais);
            String siglaPais = getValorElemento("siglaPais", elementPais);

            Pais pais = new Pais();
            pais.setId(UUID.fromString(id));
            pais.setNomePais(nomePais);
            pais.setSiglaPais(siglaPais);

            return pais;
        } catch (ParserConfigurationException | SAXException ex) {
            throw new ArquivoLeituraException("Não foi ppossível efetuar a leitura do arquivo", ex);
        }
    }

}
