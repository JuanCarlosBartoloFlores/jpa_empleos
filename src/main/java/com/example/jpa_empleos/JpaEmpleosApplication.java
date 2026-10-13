package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.models.Usuario;
import com.example.jpa_empleos.repository.PerfilesRepository;
import com.example.jpa_empleos.repository.UsuarioRepository;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final VacantesRepository vacantesRepo;
    private final PerfilesRepository perfilesRepo;
    private final UsuarioRepository usuarioRepo;

    public JpaEmpleosApplication(VacantesRepository vacantesRepo,
                                 PerfilesRepository perfilesRepo,
                                 UsuarioRepository usuarioRepo) {
        this.vacantesRepo = vacantesRepo;
        this.perfilesRepo = perfilesRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarUsuario();
    }

    /**
     * Método para buscar un usuario y sus perfiles asociados
     */
    public void buscarUsuario() {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(1);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Perfiles asignados");
            for (Perfil perfil : usuario.getPerfiles()) {
                System.out.println(perfil.getPerfil());
            }
        }
    }

    /**
     * Crear usuario con 2 perfiles ADMINISTRADOR=2, USUARIO=3
     */
    private void crearUsuarioConPerfiles() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Juan Carlos");
        nuevoUsuario.setEmail("Juan@gmail.com");
        nuevoUsuario.setUsername("juanF");
        nuevoUsuario.setPassword("12345");
        nuevoUsuario.setEstatus(1);
        nuevoUsuario.setFechaRegistro(LocalDate.now());

        Perfil perfil1 = new Perfil();
        perfil1.setId(2);

        Perfil perfil2 = new Perfil();
        perfil2.setId(3);

        Set<Perfil> perfilesUsuario = new HashSet<>();
        perfilesUsuario.add(perfil1);
        perfilesUsuario.add(perfil2);
        nuevoUsuario.setPerfiles(perfilesUsuario);

        usuarioRepo.save(nuevoUsuario);
    }

    /**
     * Método para crear los perfiles
     */
    private void crearPerfiles() {
        perfilesRepo.saveAll(obtenerPerfiles());
    }

    /**
     * Método que regresa una lista de Perfiles que se tienen en la aplicación de empleos
     */
    private List<Perfil> obtenerPerfiles() {
        List<Perfil> perfiles = new LinkedList<>();
        Perfil perfil1 = new Perfil();
        perfil1.setPerfil("SUPERVISOR");

        Perfil perfil2 = new Perfil();
        perfil2.setPerfil("ADMINISTRADOR");

        Perfil perfil3 = new Perfil();
        perfil3.setPerfil("USUARIO");

        perfiles.add(perfil1);
        perfiles.add(perfil2);
        perfiles.add(perfil3);
        return perfiles;
    }
}