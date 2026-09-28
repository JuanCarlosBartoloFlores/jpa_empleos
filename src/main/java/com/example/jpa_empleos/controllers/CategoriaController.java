package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriasRepository categoriasRepo;

    public CategoriaController(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    // GET /api/categorias -> consultar todas
    @GetMapping
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        categoriasRepo.findAll().forEach(categorias::add);
        return categorias;
    }

    // GET /api/categorias/{id} -> buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        return categoriasRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/categorias -> crear
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        categoria.setId(null);
        Categoria guardada = categoriasRepo.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    // PUT /api/categorias/{id} -> actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Integer id,
                                                @RequestBody Categoria datos) {
        return categoriasRepo.findById(id)
                .map(existente -> {
                    existente.setNombre(datos.getNombre());
                    existente.setDescripcion(datos.getDescripcion());
                    return ResponseEntity.ok(categoriasRepo.save(existente));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/categorias/{id} -> eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!categoriasRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriasRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}