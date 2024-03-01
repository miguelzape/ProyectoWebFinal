package proyectoFinal.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Propiedades {
	
	private static Map<String,String> parametros = new HashMap<String,String>();
	
	
	public void cargarProperties(){
		try {
			ClassLoader classLoader = Propiedades.class.getClassLoader();
			InputStream archivo = classLoader.getResourceAsStream("parametros.properties");
			Properties p = new Properties();
		
			p.load(archivo);
		
			for(String nombre: p.stringPropertyNames()) {
				String valor = (String)p.get(nombre);
				parametros.put(nombre.toLowerCase(),valor);
			}
			
		} catch (Exception e) {
			System.err.println("no existe el archivo de properties. " + e);
			System.exit(1);
		}
	}
	
	public String leerProper(String key) {
		return parametros.get(key.toLowerCase());
	}
	
}
