package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.sistema.SistemaController3;
import br.com.ada.crud.controller.exception.SistemaNaoEncontrado;
import br.com.ada.crud.model.cidade.Cidade;

import java.util.*;

public class SistemaArmazenamentoVolatilController3 implements SistemaController3 {

    private Map<UUID, Cidade> cidades = new HashMap<>();

    @Override
    public void cadastrarCidade(Cidade cidade) {
        cidade.setIdCidade(UUID.randomUUID());
        cidades.put(cidade.getIdCidade(), cidade);
    }

    @Override
    public Cidade lerCidade(UUID id) {
        Cidade encontrada = cidades.get(id);
        if (encontrada == null) {
            throw new SistemaNaoEncontrado();
        }
        return encontrada;
    }

    @Override
    public List<Cidade> listarCidade() {
        return new ArrayList<>(cidades.values());
    }

    @Override
    public void updateCidade(UUID id, Cidade cidade) {
        if (cidades.containsKey(id)) {
            cidades.put(id, cidade);
        } else {
            throw new SistemaNaoEncontrado();
        }
    }

    @Override
    public Cidade deleteCidade(UUID id) {
        Cidade apagada = cidades.remove(id);
        if (apagada == null) {
            throw new SistemaNaoEncontrado();
        }
        return apagada;
    }

}
