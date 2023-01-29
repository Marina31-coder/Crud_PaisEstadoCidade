package br.com.ada.crud.model.estado;

import java.io.Serializable;
import java.util.UUID;

public class Estado implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID idEstado;

    private String nomeEstado;

    private String siglaEstado;

    private String siglaPais;


    public UUID getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(UUID idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setSiglaPais(String siglaPais) {
        this.siglaPais = siglaPais;
    }


}
