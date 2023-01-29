package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.sistema.SistemaController;
import br.com.ada.crud.controller.exception.SistemaNaoEncontrado;
import br.com.ada.crud.model.pais.Pais;

import java.util.*;

public class SistemaArmazenamentoVolatilController implements SistemaController {

    private Map<UUID, Pais> pessoas = new HashMap<>();

    @Override
    public void cadastrarPais(Pais pais) {
        pais.setId(UUID.randomUUID());
        pessoas.put(pais.getId(), pais);
    }

    @Override
    public Pais lerPais(UUID id) {
        Pais encontrada = pessoas.get(id);
        if (encontrada == null) {
            throw new SistemaNaoEncontrado();
        }
        return encontrada;
    }

    @Override
    public List<Pais> listarPais() {
        return new ArrayList<>(pessoas.values());
    }

    @Override
    public void updatePais(UUID id, Pais pais) {
        if (pessoas.containsKey(id)) {
            pessoas.put(id, pais);
        } else {
            throw new SistemaNaoEncontrado();
        }
    }

    @Override
    public Pais deletePais(UUID id) {
        Pais apagada = pessoas.remove(id);
        if (apagada == null) {
            throw new SistemaNaoEncontrado();
        }
        return apagada;
    }

}
