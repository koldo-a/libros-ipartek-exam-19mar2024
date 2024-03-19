package com.ipartek.formacion.uf2216.dal;

import com.ipartek.formacion.uf2216.pojos.Libro;

public interface DaoLibro extends Dao<Libro> {
	Iterable<Libro> buscarPorTitulo(String titulo);
}
