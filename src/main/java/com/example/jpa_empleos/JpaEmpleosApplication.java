package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasJPARepository categoriasJPARepo;

    public JpaEmpleosApplication(CategoriasJPARepository categoriasJPARepo) {
        this.categoriasJPARepo = categoriasJPARepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarTodosOrdenados();
    }

    /**
     * Metodo findAll [Ordenados por un campo] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosOrdenados() {
        List<Categoria> categorias = categoriasJPARepo.findAll(Sort.by("nombre").descending());
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }

    private void borrarTodasEnBloque() {
        categoriasJPARepo.deleteAllInBatch();
    }

    private void buscarTodasJPA() {
        List<Categoria> categorias = categoriasJPARepo.findAll();
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }
}