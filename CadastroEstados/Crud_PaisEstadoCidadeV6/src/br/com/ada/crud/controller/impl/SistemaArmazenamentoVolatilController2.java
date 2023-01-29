package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.sistema.SistemaController2;
import br.com.ada.crud.controller.exception.SistemaNaoEncontrado;
import br.com.ada.crud.model.estado.Estado;

import java.util.*;

public class SistemaArmazenamentoVolatilController2 implements SistemaController2 {

    private Map<UUID, Estado> estados = new HashMap<>();

    @Override
    public void cadastrarEstado(Estado estado) {
        estado.setIdEstado(UUID.randomUUID());
        estados.put(estado.getIdEstado(), estado);
    }

    @Override
    public Estado lerEstado(UUID id) {
        Estado encontrada = estados.get(id);
        if (encontrada == null) {
            throw new SistemaNaoEncontrado();
        }
        return encontrada;
    }

    @Override
    public List<Estado> listarEstado() {
        return new ArrayList<>(estados.values());
    }

    @Override
    public void updateEstado(UUID id, Estado estado) {
        if (estados.containsKey(id)) {
            estados.put(id, estado);
        } else {
            throw new SistemaNaoEncontrado();
        }
    }

    @Override
    public Estado deleteEstado(UUID id) {
        Estado apagada = estados.remove(id);
        if (apagada == null) {
            throw new SistemaNaoEncontrado();
        }
        return apagada;
    }

}
