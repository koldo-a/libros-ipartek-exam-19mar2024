package com.ipartek.formacion.uf2216.dal;

// CRUD: Create, Read, Update, Delete
// DAO: Data Access Object

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	T obtenerPorId(Long id);
	
	T insertar(T objeto);
	T modificar(T objeto);
	void borrar(Long id);
}
