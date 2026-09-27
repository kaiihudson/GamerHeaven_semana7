package com.duoc.gamerheaven.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.gamerheaven.model.Videojuego;
import com.duoc.gamerheaven.repository.VideojuegoRepository;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;
    

    public List<Videojuego> findAll() {
        return videojuegoRepository.findAll();
    }

    public Optional<Videojuego> findById(int id) {
        return videojuegoRepository.findById(id);
    }

    public Optional<Videojuego> findByTitulo(String titulo) {
        return videojuegoRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Videojuego> findAllByPlataforma(String plataforma) {
        return videojuegoRepository.findAllByPlataformaIgnoreCase(plataforma);
    }

    public Videojuego create(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    public Optional<Videojuego> update(int id, Videojuego videojuego) {
        Optional<Videojuego> videojuegoOptional = videojuegoRepository.findById(id);
        if (videojuegoOptional.isPresent()) {
            Videojuego videojuegoUpdate = videojuegoOptional.get();
            videojuegoUpdate.setTitulo(videojuego.getTitulo());
            videojuegoUpdate.setPlataforma(videojuego.getPlataforma());
            videojuegoUpdate.setDisponibilidad(videojuego.isDisponibilidad());
            videojuegoUpdate.setPrecio(videojuego.getPrecio());
            videojuegoRepository.save(videojuegoUpdate);
            return Optional.of(videojuegoUpdate);
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(int id) {
        if (!videojuegoRepository.existsById(id)) {
            return false;
        }
        videojuegoRepository.deleteById(id);
        return true;
    }
}