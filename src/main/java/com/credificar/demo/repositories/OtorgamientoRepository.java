package com.credificar.demo.repositories;

import com.credificar.demo.models.entities.Otorgamiento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * El repositorio JPA de otorgamientos almacena la información de los otorgamientos en la base de datos MySQL.
 * Las configuraciones de la base de datos se agregan al archivo application.properties.
 * El Repositorio JPA ejecutará el comando de guardar para guardar los datos de los empleados en la base de datos.
 */
public interface OtorgamientoRepository extends JpaRepository<Otorgamiento, Long> {
}
