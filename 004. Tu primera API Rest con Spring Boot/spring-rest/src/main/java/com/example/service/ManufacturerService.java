package com.example.service;

import com.example.model.Manufacturer;

import java.util.List;
import java.util.Optional;

/*
CRUD:
Create
Retrieve / Read
Update
Delete
 */
public interface ManufacturerService {

    // RETRIEVE
    List<Manufacturer> findAll();
    List<Manufacturer> findAllByYear(Integer year);
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> findByName(String name);

    // CREATE / UPDATE
    Manufacturer save(Manufacturer manufacturer);

    // DELETE
    void deleteById(Long id);
    void deleteAll();

    // Más lógica de negocio:
    // 1. Coches fabricados
    // 2. Beneficios obtenidos
    // 3. .....
}
