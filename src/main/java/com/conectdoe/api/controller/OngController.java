package com.conectdoe.api.controller;

import com.conectdoe.api.domain.Ong;
import com.conectdoe.api.service.OngService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ongs")
public class OngController {

    private final OngService ongService;

    public OngController(OngService ongService){
        this.ongService = ongService;
    }

    @PostMapping
    public ResponseEntity<Ong> adicionar(@Valid @RequestBody Ong ong){
        return ResponseEntity.ok(ongService.adicionar(ong));
    }

    @GetMapping("/id/{ongId}")
    public ResponseEntity<Ong> buscarPorId(@PathVariable String ongId){
        return ResponseEntity.ok(ongService.buscarPorId(ongId));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Ong> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(ongService.buscarPorNome(nome));
    }

    @GetMapping("/")
    public ResponseEntity<List<Ong>> buscarTodas(){
        return ResponseEntity.ok(ongService.buscarTodos());
    }

    @DeleteMapping("/{ongId}")
    public void deletar(@PathVariable String ongId){
        ongService.deletar(ongId);
    }
}
