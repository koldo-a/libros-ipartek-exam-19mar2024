package com.ipartek.formacion.uf2216.dal;

import java.util.Properties;

public class FabricaDaoTipos implements FabricaDao {

	private static final String FABRICADAO_PROPERTIES = "fabricadao.properties";
	
	private final DaoLibro daoLibro;

	public FabricaDaoTipos() {
		this(FABRICADAO_PROPERTIES);
	}
	
	public FabricaDaoTipos(String configuracion) {
		try {
			Properties props = new Properties();
			props.load(FabricaDaoTipos.class.getClassLoader().getResourceAsStream(configuracion));

			String tipo = props.getProperty("tipo");
//			String fichero = props.getProperty("fichero");

			daoLibro = switch (tipo) {
			case "arraylist" -> DaoLibroArrayList.getInstancia();
//			case "treemap" -> DaoLibroTreeMap.getInstancia();
//			case "hashmap" -> DaoLibroHashMap.getInstancia();
//			case "linkedhashmap" -> DaoLibroLinkedHashMap.getInstancia();
//			case "csv" -> new DaoLibroCSV(fichero);
//			case "txt" -> new DaoLibroTXT(fichero);
//			case "objetos" -> new DaoLibroFicheroObjetos(fichero);
			default -> throw new DalException("NO se reconoce el tipo " + tipo);
			};
		} catch (Exception e) {
			throw new DalException("Error al leer la configuración", e);
		} finally {
			
		}

	}

	@Override
	public DaoLibro getDaoLibro() {
		return daoLibro;
	}

}
