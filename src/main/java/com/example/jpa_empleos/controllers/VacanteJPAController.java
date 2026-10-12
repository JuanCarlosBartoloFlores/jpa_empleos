package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa-vacante")
public class VacanteJPAController {

    @Autowired
    private VacantesRepository vacantesRepo;

    // buscarVacantes: consultar todas las vacantes
    @GetMapping
    public List<Vacante> buscarVacantes() {
        return vacantesRepo.findAll();
    }

    // guardarVacante: registrar una nueva vacante desde un JSON
    @PostMapping
    public Vacante guardarVacante(@RequestBody Vacante vacante) {
        vacante.setId(null); // fuerza un INSERT
        return vacantesRepo.save(vacante);
    }
}