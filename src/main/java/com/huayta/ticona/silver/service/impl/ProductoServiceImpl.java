package com.huayta.ticona.silver.service.impl;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayta.ticona.silver.modelo.Producto;
import com.huayta.ticona.silver.repository.ProductoRepository;
import com.huayta.ticona.silver.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repository;
	
	@Override
	public List<Producto> getAll() {		
		return (List<Producto>)repository.findAll();
	}

	@Override
	public Producto getObjectById(int id) {
		Optional<Producto> producto=repository.findById(id);
		if(producto.isEmpty()) {
			throw new NoSuchElementException("El producto con ID "+id+" no existe en la base");
		}
		return producto.get();
	}

	@Override
	public Producto save(Producto producto) {
		return repository.save(producto);
	}

	@Override
	public void deleteObjectById(int id) {
		Optional<Producto> producto=repository.findById(id);
		if(producto.isEmpty()) {
			throw new NoSuchElementException("El producto con ID "+id+" no existe en la base");
		}else {
			repository.deleteById(id);
		}
		
	}

}
