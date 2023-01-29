package br.com.ada.crud.model.pais;

import java.io.Serializable;
import java.util.UUID;

public class Pais implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID id;

    private String nomePais;

    private String siglaPais;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setSiglaPais(String siglaPais) {
        this.siglaPais = siglaPais;
    }


}
