package com.ipartek.formacion.uf2216.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Libro implements Serializable {
	private static final long serialVersionUID = -6950379321811458669L;

	public static final String NOMBRE_POR_DEFECTO = "LIBRO SIN DEFINIR";
		
	private Long id;
	private String titulo;
	private Long isbn;
	private BigDecimal precio;
	private Integer numeroPaginas;
	private Boolean esDigital;
	
	public Libro() {
		super();
	}
	public Libro(Long id, String titulo, Long isbn, BigDecimal precio, Integer numeroPaginas, Boolean esDigital) {
		setId(id);
		setTitulo(titulo);
		setIsbn(isbn);
		setPrecio(precio);
		setNumeroPaginas(numeroPaginas);
		setEsDigital(esDigital);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		if (id != null && id < 0) {
			throw new RuntimeException("No se admiten valores de id menores que 0");
		}

		this.id = id;
	}
	
	public void setId(String id) {
		if(id == null || id.isBlank()) {
			this.id = null;
			return;
		}
		
		this.id = Long.valueOf(id);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
	    // Verificar si el nombre es válido
	    if (titulo == null || titulo.trim().length() < 3 || titulo.trim().length() > 150) {
	        throw new IllegalArgumentException("El nombre del producto debe tener entre 3 y 150 caracteres.");
	    }

	    this.titulo = titulo.trim();
	}

	
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
	    // Verificar si el ISBN es válido
	    String isbnString = String.valueOf(isbn);
	    if (isbnString.length() == 10) {
	        this.isbn = isbn;
	    } else {
	        // Lanzar una excepción o tomar alguna acción apropiada si el ISBN no es válido
	        throw new IllegalArgumentException("El ISBN debe tener exactamente 10 dígitos.");
	    }
	}

	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	
	public void setNumeroPaginas(Integer numeroPaginas) {
	    // Verificar si el número de páginas es válido
	    if (numeroPaginas != null && numeroPaginas >= 1) {
	        this.numeroPaginas = numeroPaginas;
	    } else {
	        // Lanzar una excepción o tomar alguna acción apropiada si el número de páginas no es válido
	        throw new IllegalArgumentException("El número de páginas debe ser mayor o igual a 1");
	    }
	}
	
	public Boolean getEsDigital() {
		return esDigital;
	}
	
	public void setEsDigital(Boolean esDigital) {
		if(esDigital == null) {
			throw new RuntimeException("Es obligatorio definir si está en digital o no");
		}
		
		this.esDigital = esDigital;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(esDigital, id, isbn, numeroPaginas, precio, titulo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(esDigital, other.esDigital) && Objects.equals(id, other.id)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(numeroPaginas, other.numeroPaginas)
				&& Objects.equals(precio, other.precio) && Objects.equals(titulo, other.titulo);
	}
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + ", precio=" + precio + ", numeroPaginas="
				+ numeroPaginas + ", esDigital=" + esDigital + "]";
	}


}
