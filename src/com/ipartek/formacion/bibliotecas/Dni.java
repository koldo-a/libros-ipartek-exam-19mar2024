package com.ipartek.formacion.bibliotecas;

public class Dni {
	private static final String LETRAS_NIE = "XYZ";
	private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

	public static boolean validarDni(String dni) {
		dni = dni.toUpperCase();
		
		if(!dni.matches("^[" + LETRAS_NIE + "\\d]\\d{7}[" + LETRAS_DNI + "]$")) {
			return false;
		}

		char primeraLetra = dni.charAt(0);
		int indiceLetraNIE = LETRAS_NIE.indexOf(primeraLetra);

		String num;

		if (indiceLetraNIE != -1) {
			num = indiceLetraNIE + dni.substring(1, 8);
		} else {
			num = dni.substring(0, 8);
		}

		char letra = dni.charAt(8);

		return LETRAS_DNI.charAt(Integer.parseInt(num) % 23) == letra;
	}
}
