package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pelicula;
import com.example.demo.model.Genero;
import com.example.demo.service.GeneroService;
import com.example.demo.service.PeliculaService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/getAllPeliculas")
	public String getAllPelicula(Model model) {
		
		List<Pelicula> listPelicula = peliculaService.getAllPelicula();
		
		model.addAttribute("peliculas", listPelicula);
		
		return "peliculaList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("generos", generoService.getAllGenero());
		
		return "peliculaRegister";
	}
	
	@PostMapping("/register")
	public String createPelicula(@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "director") String director, 
			@RequestParam(name = "fechaestreno") String fechaestreno,
			@RequestParam(name = "idgenero") int idgenero, Model model) {
		
		Pelicula pelicula = new Pelicula();
		pelicula.nombre = nombre;
		pelicula.director = director;
		pelicula.fechaestreno = fechaestreno;

		Genero genero = generoService.getGeneroById(idgenero);
		pelicula.genero = genero;
		
		
		peliculaService.createPelicula(pelicula);
		
		List<Pelicula> listPelicula = peliculaService.getAllPelicula();
		model.addAttribute("peliculas", listPelicula);
				
		return "peliculaList";
	}
	
	@GetMapping("/edit/{idpelicula}")
	public String getPeliculaById(@PathVariable int idpelicula, Model model) {
		Pelicula pelicula = peliculaService.getPeliculaById(idpelicula);		
		
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generoService.getAllGenero());
		
		return "peliculaEdit";
	}
	
	@PostMapping("/edit")
	public String editPelicula(@RequestParam(name = "idpelicula") int idpelicula, 
			@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "director") String director, 
			@RequestParam(name = "fechaestreno") String fechaestreno,
			@RequestParam(name = "idgenero") int idgenero, Model model) {
		
		Pelicula pelicula = peliculaService.getPeliculaById(idpelicula);
		pelicula.nombre = nombre;
		pelicula.director = director;
		pelicula.fechaestreno = fechaestreno;
		
		Genero genero = generoService.getGeneroById(idgenero);
		pelicula.genero = genero;
		
		peliculaService.createPelicula(pelicula);
		
		List<Pelicula> listPelicula = peliculaService.getAllPelicula();
		model.addAttribute("peliculas", listPelicula);
		
		return "peliculaList";
	}
	
	@GetMapping("/delete/{idpelicula}")
	public String deletePelicula(@PathVariable int idpelicula, Model model) {
		peliculaService.deletePelicula(idpelicula);
		
		List<Pelicula> listPelicula = peliculaService.getAllPelicula();
		model.addAttribute("peliculas", listPelicula);
		
		return "peliculaList";
	}
}
