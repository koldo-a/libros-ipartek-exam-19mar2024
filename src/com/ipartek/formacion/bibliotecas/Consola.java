package com.ipartek.formacion.bibliotecas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
	private static final Scanner sc = new Scanner(System.in);

	public static final boolean OPCIONAL = true;
	public static final boolean OBLIGATORIO = false;

	public static void pfl(String formato, Object... datos) {
		System.out.printf(formato + "\n", datos);
	}
	
	public static void pl(Object o) {
		System.out.println(o);
	}
	
	public static void pl() {
		System.out.println();
	}
	
	public static void p(Object o) {
		System.out.print(o);
	}
	
	public static String leerString(String mensaje, boolean esOpcional) {
		String texto;
		boolean repetir = true;

		do {
			p(mensaje + ": ");
			texto = sc.nextLine().trim();

			if (!esOpcional && texto.length() == 0) {
				pl("Este dato es obligatorio");
			} else {
				repetir = false;
			}
		} while (repetir);

		return texto.length() > 0 ? texto : null;
	}

	public static String leerString(String mensaje) {
		return leerString(mensaje, OBLIGATORIO);
	}

	public static Long leerLong(String mensaje) {
		return leerLong(mensaje, OBLIGATORIO);
	}

	public static Long leerLong(String mensaje, boolean opcional) {
		boolean hayError = true;
		long l = 0;

		do {
			try {
				String dato = leerString(mensaje, opcional);

				if (dato == null) {
					return null;
				}

				l = Long.parseLong(dato);
				hayError = false;
			} catch (NumberFormatException e) {
				pl("El número debe ser un entero entre " + Long.MIN_VALUE + " y " + Long.MAX_VALUE);
			}
		} while (hayError);

		return l;
	}

	public static Integer leerInt(String mensaje) {
		return leerInt(mensaje, OBLIGATORIO);
	}

	public static Integer leerInt(String mensaje, boolean opcional) {
		return leerInt(mensaje, opcional, null, null);
	}

	public static Integer leerInt(String mensaje, boolean opcional, Integer minimo, Integer maximo) {
		boolean hayError = true;
		int i = 0;

		if (minimo == null) {
			minimo = Integer.MIN_VALUE;
		}

		if (maximo == null) {
			maximo = Integer.MAX_VALUE;
		}

		do {
			try {
				String dato = leerString(mensaje, opcional);

				if (dato == null) {
					return null;
				}

				i = Integer.parseInt(dato);

				if (i < minimo || i > maximo) {
					pl("El valor está fuera de límites");
				} else {
					hayError = false;
				}
			} catch (NumberFormatException e) {
				System.out
						.println("El número debe ser un entero entre " + Integer.MIN_VALUE + " y " + Integer.MAX_VALUE);
			}
		} while (hayError);

		return i;
	}

	public static BigDecimal leerBigDecimal(String mensaje) {
		return leerBigDecimal(mensaje, true, null, null);
	}

	public static BigDecimal leerBigDecimal(String mensaje, boolean opcional) {
		return leerBigDecimal(mensaje, opcional, null, null);
	}
	
	public static BigDecimal leerBigDecimal(String mensaje, boolean opcional, BigDecimal minimo, BigDecimal maximo) {
		boolean hayError = true;
		BigDecimal bd = BigDecimal.ZERO;

		do {
			try {
				String dato = leerString(mensaje, opcional);

				if (dato == null) {
					return null;
				}

				bd = new BigDecimal(dato);

				if ((minimo != null && bd.compareTo(minimo) < 0) || (maximo != null && bd.compareTo(maximo) > 0)) {
					pl("El valor está fuera de límites");
				} else {
					hayError = false;
				}
			} catch (NumberFormatException e) {
				pl("El número debe ser un decimal");
			}
		} while (hayError);

		return bd;
	}
	
	public static Boolean leerBoolean(String mensaje) {
		String texto = leerString(mensaje + " [SI|NO]");
		
		Boolean b = "si".equalsIgnoreCase(texto);
		
		return b;
	}
	
	public static LocalDate leerFecha(String mensaje) {
		return leerFecha(mensaje, OBLIGATORIO);
	}

	public static LocalDate leerFecha(String mensaje, boolean opcional) {
		return leerFecha(mensaje, opcional, null, null);
	}

	public static LocalDate leerFecha(String mensaje, boolean opcional, LocalDate minima, LocalDate maxima) {
		boolean hayError = true;
		LocalDate fecha = null;

		if (minima == null) {
			minima = LocalDate.MIN;
		}

		if (maxima == null) {
			maxima = LocalDate.MAX;
		}

		do {
			try {
				String dato = leerString(mensaje + " [AAAA-MM-DD] ", opcional);

				if (dato == null) {
					return null;
				}

				fecha = LocalDate.parse(dato);

				if (fecha.isBefore(minima) || fecha.isAfter(maxima)) {
					pl("Fecha fuera de límites");
				} else {
					hayError = false;
				}
			} catch (DateTimeParseException e) {
				pl("La fecha debe ser válida");
			}
		} while (hayError);

		return fecha;
	}
	
	public static String leerDni(String mensaje) {
		return leerDni(mensaje, OBLIGATORIO);
	}
	
	public static String leerDni(String mensaje, boolean opcional) {
		String texto;
		boolean repetir = true;

		do {
			texto = leerString(mensaje, opcional);
			
			if (texto != null && !Dni.validarDni(texto)) {
				pl("El DNI no es conforme a la validación");
			} else {
				repetir = false;
			}
		} while (repetir);

		return texto == null ? null : texto.toUpperCase();
	}
}
