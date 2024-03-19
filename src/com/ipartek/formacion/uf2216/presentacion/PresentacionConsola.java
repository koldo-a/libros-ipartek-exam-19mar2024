package com.ipartek.formacion.uf2216.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.math.BigDecimal;

import com.ipartek.formacion.uf2216.dal.DaoLibro;
import com.ipartek.formacion.uf2216.dal.FabricaDaoTipos;
import com.ipartek.formacion.uf2216.pojos.Libro;

public class PresentacionConsola {
	private static final int SALIR = 0;

	private static final DaoLibro DAO = new FabricaDaoTipos().getDaoLibro();

	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);
		} while (opcion != SALIR);
	}

	private static void mostrarMenu() {
		pl("1. LISTADO");
		pl("2. OBTENER POR ID");
		pl();
		pl("3. INSERTAR");
		pl("4. MODIFICAR");
		pl("5. BORRAR");
		pl();
		pl("0. SALIR");
		pl();
	}

	private static int pedirOpcion() {
		return leerInt("Selecciona una opción", OBLIGATORIO);
	}

	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listado();
		case 2 -> buscar();
		case 3 -> insertar();
		case 4 -> modificar();
		case 5 -> borrar();
		case 0 -> salir();
		default -> pl("Opción no reconocida");
		}
	}

	private static void listado() {
		for (var producto : DAO.obtenerTodos()) {
			mostrarFila(producto);
		}
	}

	private static void buscar() {
		Long id = leerLong("Dime el id", OBLIGATORIO);

		var producto = DAO.obtenerPorId(id);

		mostrarFicha(producto);
	}

//	private static void insertar() {
//		var producto = new Libro(null, leerString("Titulo", OBLIGATORIO), leerLong("Dime el ISBN", OBLIGATORIO), leerBigDecimal("Precio", OBLIGATORIO), leerInt("Número de páginas"),
//				leerBoolean("Es digital?"));
//		
//		DAO.insertar(producto);
//	}
	private static void insertar() {
		String titulo;
		do {
			titulo = leerString("Título", OBLIGATORIO);
			if (titulo.length() < 3 || titulo.length() > 150) {
				pl("El título debe tener al menos 3 letras y como máximo 150 letras.");
			}
		} while (titulo.length() < 3 || titulo.length() > 150);

		Long isbn;
		do {
			isbn = leerLong("Dime el ISBN", OBLIGATORIO);
			if (String.valueOf(isbn).length() != 10) {
				pl("El ISBN debe tener exactamente 10 dígitos.");
			}
		} while (String.valueOf(isbn).length() != 10);

		BigDecimal precio = leerBigDecimal("Precio", OBLIGATORIO);

		int numeroPaginas;
		do {
			numeroPaginas = leerInt("Número de páginas");
			if (numeroPaginas < 1) {
				pl("El número de páginas debe ser como mínimo 1.");
			}
		} while (numeroPaginas < 1);

		Boolean esDigital = leerBoolean("¿Es digital?");

		var libro = new Libro(null, titulo, isbn, precio, numeroPaginas, esDigital);

		mostrarFicha(libro);

		if (confirmar("¿Desea insertar este libro?")) {
			DAO.insertar(libro);
		} else {
			pl("Operación de inserción cancelada.");
		}
	}

	private static boolean confirmar(String mensaje) {
		String respuesta;
		do {
			respuesta = leerString(mensaje + " [S/N]", OBLIGATORIO).toUpperCase();
			if (!respuesta.equals("S") && !respuesta.equals("N")) {
				pl("Por favor, responda con 'S' para sí o 'N' para no.");
			}
		} while (!respuesta.equals("S") && !respuesta.equals("N"));

		return respuesta.equals("S");
	}

	private static void modificar() {
	    Long id = leerLong("ID del libro que desea modificar", OBLIGATORIO);
	    
	    // Obtener el libro por su ID
	    Libro libro = DAO.obtenerPorId(id);
	    
	    // Mostrar la ficha del libro existente
	    mostrarFicha(libro);
	    
	    // Solicitar los nuevos datos del libro
	    String titulo;
	    do {
	        titulo = leerString("Nuevo título", OBLIGATORIO);
	        if (titulo.length() < 3 || titulo.length() > 150) {
	            pl("El título debe tener al menos 3 letras y como máximo 150 letras.");
	        }
	    } while (titulo.length() < 3 || titulo.length() > 150);

	    BigDecimal precio = leerBigDecimal("Nuevo precio", OBLIGATORIO);

	    int numeroPaginas;
	    do {
	        numeroPaginas = leerInt("Nuevo número de páginas");
	        if (numeroPaginas < 1) {
	            pl("El número de páginas debe ser como mínimo 1.");
	        }
	    } while (numeroPaginas < 1);

	    Boolean esDigital = leerBoolean("¿Es digital?");

	    // Actualizar el libro con los nuevos datos
	    libro.setTitulo(titulo);
	    libro.setPrecio(precio);
	    libro.setNumeroPaginas(numeroPaginas);
	    libro.setEsDigital(esDigital);
	    
	    // Mostrar la ficha del libro actualizado
	    mostrarFicha(libro);

	    if (confirmar("¿Desea modificar este libro?")) {
	        DAO.modificar(libro);
	    } else {
	        pl("Operación de modificación cancelada.");
	    }
	}


	private static void borrar() {
		Long id = leerLong("Dime el id", OBLIGATORIO);

		DAO.borrar(id);
	}

	private static void salir() {
		pl("Gracias por usar esta aplicación");
	}

	private static void mostrarFila(Libro libro) {
		pfl("| %03d | %-30s | %-12d | %,10.2f € | %4d | %13s |", libro.getId(), libro.getTitulo(), libro.getIsbn(),
				libro.getPrecio(), libro.getNumeroPaginas(), libro.getEsDigital() ? "DIGITAL" : "PAPEL");
	}

	private static void mostrarFicha(Libro libro) {
		pl();
		pfl("%-2s: %s", "Id", libro.getId());
		pfl("%-7s: %s", "Título", libro.getTitulo());
		pfl("%-4s: %s", "ISBN", libro.getIsbn());
		pfl("%-6s: %,.2f", "Precio", libro.getPrecio());
		pfl("%-10s: %d", "Nº páginas", libro.getNumeroPaginas());
		pfl("%-7s: %s", "Digital", libro.getEsDigital() ? "SÍ" : "NO");
		pl();
	}
}
