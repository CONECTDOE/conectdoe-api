package com.conectdoe.api.repository;

import com.conectdoe.api.domain.Ong;

import java.util.List;

public interface OngRepository {
    Ong salvar(Ong ong);
    List<Ong> listarTodos();
    Ong buscarPorId(String ongId);
    Ong buscarPorNome(String nome);
    void deletar(String ongId);

}
