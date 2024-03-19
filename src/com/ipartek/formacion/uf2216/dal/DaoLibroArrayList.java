package com.ipartek.formacion.uf2216.dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import com.ipartek.formacion.uf2216.pojos.Libro;

class DaoLibroArrayList implements DaoLibro {

	private ArrayList<Libro> libros = new ArrayList<>();

	// SINGLETON
	private DaoLibroArrayList() {
        libros.add(new Libro(1L, "Cien años de soledad", 9780307547L, new BigDecimal("20.99"), 500, false));
        libros.add(new Libro(2L, "El principito", 9788817484L, new BigDecimal("15.50"), 150, false));
        libros.add(new Libro(3L, "1984", 9780745135L, new BigDecimal("18.75"), 320, true));
        libros.add(new Libro(4L, "Orgullo y prejuicio", 9780147518L, new BigDecimal("12.25"), 400, false));
        libros.add(new Libro(5L, "Don Quijote de la Mancha", 9788742143L, new BigDecimal("25.99"), 800, true));

	}

	private static DaoLibroArrayList instancia;

	public static DaoLibroArrayList getInstancia() {
		if(instancia == null) {
			instancia = new DaoLibroArrayList();
		}
		
		return instancia;
	}
	// FIN SINGLETON

	@Override
	public Iterable<Libro> obtenerTodos() {
		return libros;
	}

	@Override
	public Libro obtenerPorId(Long id) {


		return libros.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Libro insertar(Libro libro) {
		Optional<Long> oIdMaximo = libros.stream().map(p -> p.getId()).max((id1, id2) -> id1.compareTo(id2));
		// Long idMaximo = oId.orElse(null);
		// Long id = idMaximo == null ? 1L : idMaximo + 1L;
		Long id = oIdMaximo.isEmpty() ? 1L : oIdMaximo.get() + 1L;

		libro.setId(id);

		libros.add(libro);

		return libro;
	}

	@Override
	public Libro modificar(Libro libro) {
		for(int i = 0; i < libros.size(); i++) {
			if(libro.getId() == libros.get(i).getId()) {
				libros.set(i, libro);
			}
		}

		return libro;
	}

	@Override
	public void borrar(Long id) {
		for(int i = 0; i < libros.size(); i++) {
			if(id == libros.get(i).getId()) {
				libros.remove(i);
			}
		}
	}

	@Override
	public Iterable<Libro> buscarPorTitulo(String titulo) {
		return libros.stream().filter(p -> p.getTitulo().contains(titulo)).toList();
	}

}
