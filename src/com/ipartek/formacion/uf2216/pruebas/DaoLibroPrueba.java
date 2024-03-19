package com.ipartek.formacion.uf2216.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.uf2216.dal.DalException;
import com.ipartek.formacion.uf2216.dal.DaoLibro;
import com.ipartek.formacion.uf2216.dal.FabricaDaoTipos;
import com.ipartek.formacion.uf2216.pojos.Libro;

public class DaoLibroPrueba {
	public static void main(String[] args) {
		try {
			DaoLibro dao = new FabricaDaoTipos().getDaoLibro(); // DaoProductoArrayList.getInstancia();
			
			for(var p: dao.obtenerTodos()) {
				System.out.println(p);
			}
			
			dao.insertar(new Libro(null, "Nuevo1", null, new BigDecimal("111"), 5, true));
			dao.insertar(new Libro(null, "Nuevo2", null, new BigDecimal("111"), 5, true));
			dao.insertar(new Libro(null, "Nuevo3", null, new BigDecimal("111"), 5, true));
			dao.insertar(new Libro(null, "Nuevo4", null, new BigDecimal("111"), 5, true));
			dao.modificar(new Libro(1L, "Modificado", null, new BigDecimal("1234"), 7, false));
			dao.borrar(3L);
			
			for(var p: dao.obtenerTodos()) {
				System.out.println(p);
			}
			
			System.out.println(dao.obtenerPorId(2L));
			System.out.println(dao.buscarPorTitulo("Modificado"));
		} catch (DalException e) {
			System.out.println("Ha habido un error en el acceso a datos");
			e.printStackTrace(System.err);
		} catch(Exception e) {
			System.out.println("Se ha detectado un error no esperado");
			e.printStackTrace(System.err);
		}
		
		
	}
}
