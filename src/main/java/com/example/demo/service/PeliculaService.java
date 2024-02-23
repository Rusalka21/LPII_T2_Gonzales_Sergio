package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.PeliculaRepository;

import com.example.demo.model.Pelicula;

@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	public List<Pelicula> getAllPelicula() {
		return peliculaRepository.findAll();
	}
	
	public Pelicula createPelicula(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}
	
	public void deletePelicula(int id) {
		peliculaRepository.deleteById(id);
	}
	
	public Pelicula getPeliculaById(int id) {
		return peliculaRepository.findById(id).orElse(null);
	}

}
