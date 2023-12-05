package com.huayta.ticona.silver.service;

import java.util.List;

import com.huayta.ticona.silver.modelo.Producto;

public interface ProductoService {
	public List<Producto>getAll();
	public Producto getObjectById(int id);
	public Producto save(Producto producto);
	public void deleteObjectById(int id);
}
