package com.duoc.gamerheaven.controller;

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

import com.duoc.gamerheaven.model.Videojuego;
import com.duoc.gamerheaven.service.VideojuegoService;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    @GetMapping
    public ResponseEntity<List<Videojuego>> findAll() {
        return ResponseEntity.ok(videojuegoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> findById(@PathVariable int id) {
        return videojuegoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Videojuego> findByTitulo(@PathVariable String titulo) {
        return videojuegoService.findByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/plataforma/{plataforma}")
    public ResponseEntity<List<Videojuego>> findByPlataforma(@PathVariable String plataforma) {
        return ResponseEntity.ok(videojuegoService.findAllByPlataforma(plataforma));
    }

    @PostMapping
    public ResponseEntity<Videojuego> save(@RequestBody Videojuego videojuego) {
        Videojuego created = videojuegoService.create(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> update(@PathVariable int id, @RequestBody Videojuego videojuego) {
        return videojuegoService.update(id, videojuego)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return videojuegoService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}