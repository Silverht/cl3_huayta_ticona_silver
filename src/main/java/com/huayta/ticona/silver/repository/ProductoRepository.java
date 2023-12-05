package com.huayta.ticona.silver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.huayta.ticona.silver.modelo.Producto;

@Component
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
