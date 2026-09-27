package com.duoc.gamerheaven.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.gamerheaven.model.Videojuego;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Integer> {
    Optional<Videojuego> findByTituloIgnoreCase(String titulo);
    List<Videojuego> findAllByPlataformaIgnoreCase(String plataforma);
}
