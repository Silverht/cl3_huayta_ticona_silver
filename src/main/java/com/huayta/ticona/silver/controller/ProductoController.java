package com.huayta.ticona.silver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huayta.ticona.silver.modelo.Producto;
import com.huayta.ticona.silver.service.ProductoService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping("/read")
	public ResponseEntity<Object>getAllObjects(){
		List<Producto> productos=service.getAll();
		Map<String, Object>response=new HashMap<>();
		response.put("codigo", HttpStatus.OK.value());
		response.put("message", "Se encontró "+productos.size()+" productos");
		response.put("productos", productos);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/edit/{id}")
	public ResponseEntity<Object>getObjectById(@PathVariable int id){
		Producto producto=service.getObjectById(id);
		Map<String, Object>response=new HashMap<>();
		response.put("codigo", HttpStatus.OK.value());
		response.put("message", "Producto encontrado");
		response.put("producto", producto);
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>saveObject(@Valid @RequestBody Producto request){		
		Map<String, Object>response=new HashMap<>();
		int idproducto=request.getId_producto()==null?0:request.getId_producto();
		Producto producto=service.save(request);			
		response.put("codigo", HttpStatus.OK.value());
		response.put("message", idproducto>0?"Se actualizó el producto con éxito!":"Se creó nuevo producto con éxito!");
		response.put("producto", producto);			
		return ResponseEntity.ok(response);			
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Object>deleteObjectById(@PathVariable int id){
		service.deleteObjectById(id);
		Map<String, Object>response=new HashMap<>();
		response.put("codigo", HttpStatus.OK.value());
		response.put("message", "Producto con ID-"+id+" se eliminó con éxito!");
		return ResponseEntity.ok(response);
	}
}
