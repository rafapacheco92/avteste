package com.example.fazenda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fazenda.entities.Fazenda;
import com.example.fazenda.services.FazendaService;


@RestController
@RequestMapping("/fazendas")
public class FazendaController {
    @Autowired
    private FazendaService service;

    @PostMapping
    public ResponseEntity<Fazenda> create(@RequestBody Fazenda fazenda) throws Exception{
        Fazenda usuarioCriado = service.create(fazenda);
        return new ResponseEntity<Fazenda>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Fazenda> put(@RequestBody Fazenda fazenda) throws Exception{
        Fazenda usuarioAtualizada = service.update(fazenda);
        return ResponseEntity.ok(usuarioAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Fazenda>> getList(){
        List<Fazenda> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fazenda> getRead(@PathVariable Long id){
        Fazenda usuarioEncontrada = service.read(id);
        return ResponseEntity.ok(usuarioEncontrada);
    }
}
