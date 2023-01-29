package br.com.ada.crud.controller.arquivo.impl;

import br.com.ada.crud.controller.arquivo.AbstractXmlArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.crud.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.crud.model.cidade.Cidade;
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

public class CidadeArquivoXml extends AbstractXmlArquivo implements EscritorArquivo<Cidade>, LeitorArquivo<Cidade> {

    public static final String EXTENSAO = ".xml";
    private String diretorio = "database/xml/cidades";

    @Override
    public void escrever(Cidade cidade, String arquivo) throws IOException, ArquivoLeituraException {
        try {
            Document document = criarNovoDocumento();
            Element elementCidade = document.createElement("cidade");
            document.appendChild(elementCidade);

            adicionarElemento(document, "id", cidade.getIdCidade().toString(), elementCidade);
            adicionarElemento(document, "nomeCidade", cidade.getNomeCidade(), elementCidade);
            adicionarElemento(document, "siglaEstado", cidade.getSiglaEstado(), elementCidade);


            escreverArquivo(diretorio, arquivo + EXTENSAO, document);
        } catch (ParserConfigurationException | TransformerException ex) {
            throw new ArquivoEscritaException("Falha na conversão do xml", ex);
        }
    }

    @Override
    public Cidade apagar(String arquivo) throws IOException {
        Cidade cidade = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return cidade;
    }

    @Override
    public Cidade ler(String arquivo) throws IOException {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        return ler(arquivoASerLido);
    }

    @Override
    public List<Cidade> ler() throws IOException {
        FilenameFilter filter = (dir, nomeDoArquivo) -> nomeDoArquivo.endsWith(EXTENSAO);

        List<Cidade> paises = new ArrayList<>();
        File diretorioQueContemOsArquvios = new File(diretorio);
        for (File arquivoASerLido : diretorioQueContemOsArquvios.listFiles(filter)) {
            Cidade cidade = ler(arquivoASerLido);
            paises.add(cidade);
        }
        return paises;
    }

    private Cidade ler(File arquivo) throws IOException, ArquivoLeituraException {
        try {
            Document document = parse(arquivo);
            Element elementCidade = document.getDocumentElement();

            String id = getValorElemento("id", elementCidade);
            String nomeCidade = getValorElemento("nomeCidade", elementCidade);
            String siglaEstado = getValorElemento("siglaEstado", elementCidade);


            Cidade cidade = new Cidade();
            cidade.setIdCidade(UUID.fromString(id));
            cidade.setNomeCidade(nomeCidade);
            cidade.setSiglaEstado(siglaEstado);


            return cidade;
        } catch (ParserConfigurationException | SAXException ex) {
            throw new ArquivoLeituraException("Não foi ppossível efetuar a leitura do arquivo", ex);
        }
    }

}
