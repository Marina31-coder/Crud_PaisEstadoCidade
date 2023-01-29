package br.com.ada.crud.model.cidade;

import java.io.Serializable;
import java.util.UUID;

public class Cidade implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID idCidade;

    private String nomeCidade;

    private String siglaEstado;

    public UUID getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(UUID idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
}
