package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.EstatusVacante;
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

    // 1. Varios estatus (In): ?estatus=Eliminada,Creada
    @GetMapping("/estatus-varios")
    public List<Vacante> buscarPorVariosEstatus(@RequestParam EstatusVacante[] estatus) {
        return vacantesRepo.findByEstatusIn(estatus);
    }

    // 2. Rango de salario (Between): ?minimo=7000&maximo=14000
    @GetMapping("/salario")
    public List<Vacante> buscarPorSalario(@RequestParam Double minimo,
                                          @RequestParam Double maximo) {
        return vacantesRepo.findBySalarioBetween(minimo, maximo);
    }

    // 3. Rango de salario ordenado de mayor a menor
    @GetMapping("/salario/desc")
    public List<Vacante> buscarPorSalarioDesc(@RequestParam Double minimo,
                                              @RequestParam Double maximo) {
        return vacantesRepo.findBySalarioBetweenOrderBySalarioDesc(minimo, maximo);
    }

    // 4. Destacadas y con un estatus, ordenadas por id descendente
    @GetMapping("/destacadas")
    public List<Vacante> buscarDestacadas(@RequestParam int destacado,
                                          @RequestParam EstatusVacante estatus) {
        return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(destacado, estatus);
    }

    // 5. Por estatus
    @GetMapping("/estatus")
    public List<Vacante> buscarPorEstatus(@RequestParam EstatusVacante estatus) {
        return vacantesRepo.findByEstatus(estatus);
    }
}