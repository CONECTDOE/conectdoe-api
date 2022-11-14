package com.conectdoe.api.service;


import com.conectdoe.api.domain.Ong;

import java.util.List;

public interface OngService {
    Ong adicionar(Ong ong);
    Ong buscarPorId(String ongId);
    Ong buscarPorNome(String nome);
    List<Ong> buscarTodos();
    void deletar(String ongId);

}
