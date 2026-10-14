package com.example.jpa_empleos;

import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final VacantesRepository vacantesRepo;

    public JpaEmpleosApplication(VacantesRepository vacantesRepo) {
        this.vacantesRepo = vacantesRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarVacantesPorDestacadoEstatus();
    }

    /**
     * Query Method: Buscar Vacantes por Destacado y Estatus Ordenado por Id Desc
     */
    private void buscarVacantesPorDestacadoEstatus() {
        List<Vacante> vacantesAprobadasYDestacadas = vacantesRepo
                .findByDestacadoAndEstatusOrderByIdDesc(1, EstatusVacante.Aprobada);
        System.out.println("Registros encontrados: " +
                vacantesAprobadasYDestacadas.size());

        for (Vacante vacante : vacantesAprobadasYDestacadas) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - " +
                    vacante.getEstatus() + ", destacada: " + vacante.getDestacado());
        }
    }

    /**
     * Query Method: Buscar Vacantes por Estatus
     */
    private void buscarVacantesPorEstatus() {
        List<Vacante> vacantesAprobadas = vacantesRepo
                .findByEstatus(EstatusVacante.Aprobada);
        System.out.println("Registros encontrados: " + vacantesAprobadas.size());

        for (Vacante vacante : vacantesAprobadas) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - " +
                    vacante.getEstatus());
        }
    }
}