package com.conectdoe.api.service.impl;

import com.conectdoe.api.domain.Ong;
import com.conectdoe.api.exception.DuplicatedNameExcpetion;
import com.conectdoe.api.exception.ResourceNotFoundException;
import com.conectdoe.api.repository.OngRepository;
import com.conectdoe.api.service.OngService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OngServiceImpl implements OngService {

    private final OngRepository ongRepository;

    public OngServiceImpl(OngRepository ongRepository){
        this.ongRepository = ongRepository;
    }

    @Override
    public Ong adicionar(Ong ong) {
        if(ongRepository.buscarPorNome(ong.getNome()) != null) {
            throw new DuplicatedNameExcpetion("Já existe uma ONG com o nome de - " + ong.getNome() +
                    " - no Banco de Dados.");
        }
        return ongRepository.salvar(ong);

    }

    @Override
    public Ong buscarPorId(String ongId) {
        var ong = ongRepository.buscarPorId(ongId);
        if(ong != null) {
            return ong;
        } else {
            throw new ResourceNotFoundException("A ONG de id  - " + ongId + " - não foi encontrada!");
        }

    }

    @Override
    public Ong buscarPorNome(String nome) {
        var ong = ongRepository.buscarPorNome(nome);
        if(ong != null){
            return ong;
        } else {
            throw new ResourceNotFoundException("A ONG - " + nome + " - não foi encontrada!");
        }
    }

    @Override
    public List<Ong> buscarTodos() {
        return ongRepository.listarTodos();
    }

    @Override
    public void deletar(String ongId) {
        ongRepository.deletar(ongId);
    }

}
