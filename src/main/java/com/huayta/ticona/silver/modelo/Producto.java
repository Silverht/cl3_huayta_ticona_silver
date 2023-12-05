package com.huayta.ticona.silver.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_producto;
		
	@Size(max = 100, message = "La descripción debe tener máximo 100 caracteres")
	@NotNull(message = "La descripcion no puede ser nulo")
	@NotBlank(message = "La descripcion no puede estar vacía")
	@Column(length = 100)
	private String descripcion;
	
	@NotNull(message = "El campo precio no puede ser nulo")
	@DecimalMin("0.0")
	private double precio;
	
	@NotNull(message = "El campo stock no puede ser nulo")
    @Min(value = 0,message = "El campo stock no puede ser negativo")
	private Integer stock;
	
	@NotNull(message = "El campo estado no puede ser nulo")
	@Min(0)
    @Max(1)
	private Integer estado;	
	
	@NotNull(message = "La categoria no puede ser puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
}
