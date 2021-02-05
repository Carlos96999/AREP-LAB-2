package edu.escuelaing.arem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
/*
 * Clase principal que ejecuta todo el programa 
 * @author Carlos
 *
 */
public class App 
{	
	/*
	 * Procedemos a sacar la desviacion estandar con la formula depejando alfa
	 * @param datos Una lista enlazada con los datos de la tabla
	 * @param datosCopia Una lista enlazada con los datos de la tabla como copia
	 * @return desviacion Resultado de la operacion realizada
	 */
	public static String[] sacarDesviacion(ListaEnlazada datos, ListaEnlazada datosCopia) 
	{
		DesviacionEstandar desviacion = new DesviacionEstandar();
		desviacion.datos(datos, datosCopia);
		DecimalFormat df = new DecimalFormat("#.00");
		String[] respuesta = new String[2];
		respuesta[0] = df.format(desviacion.getDesviacion());
		respuesta[1] = df.format(desviacion.getMedia());
		return respuesta;
	}
	
	public static String sacarMedia(ListaEnlazada datos, ListaEnlazada datosCopia) 
	{
		DesviacionEstandar desviacion = new DesviacionEstandar();
		desviacion.datos(datos, datosCopia);
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(desviacion.getMedia());
	}
	
	/*
	 * Crear la lista enlazada con los datos que se encuentran en el archivo
	 * @param ruta Ruta donde se encuentra el archivo con los datos (Por defecto "archivo.txt")
	 * @return datos lista enlazada con los datos de la tabla (archivo de texto)
	 */
	public static ListaEnlazada leerArchivo(String ruta)
	{
		LecturaArchivo archivo = new LecturaArchivo();
		ListaEnlazada datos = archivo.leerArchivo(ruta);
		return datos;
	}
	
	/*
	 * Este metodo sera el encargado de iniciar el proceso, al abrir en el navegador el puerto 4567 (Puerto de Spark).
	 * @param args - Iniciara el Proceso.
	 */
    public static void main( String[] args ){
        port(getPort());
        get("/", (request, response) -> inicio(request, response));
        get("/respuesta", (request, response) -> resultados(request, response));
        
    }
    
    /*
     * Este metodo se encarga de devolver si el puerto registrado esta disponible o no.
     * @return retorna el numero del puerto.
     */
    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
	
    public static String inicio(Request request, Response response)
    {
    	String pageContent
    	
    	= "<!DOCTYPE html>"
                + "<html>"
    				+ "<head>"
    					+ "<title>AREP Laboratorio 2 Desviacion estandar</title>"
    				+ "</head>"
    				+ "<body bgcolor=#f4b24d>"
    				+ "<center>"
    					+ "<div style=\"padding:20px; width:100%; background:#f4b24d; color:white; font-size:300%; position:absolute ; top:0px ; left:0px\">"
    						+ "Desviacion Estandar "
    					+ "</div>"
    					+ "<form action=\"/respuesta\">"
    						+ "<div style=\"padding:45px; width:93.8%; background:#f4b24d; color:white; font-size:150%; position:absolute ; top:100px ; left:0px\">"
    							+ "<div>"
    								+ "Digite los numeros que desea agregar a la lista enlazada para proceder con la operacion"
    								+ " <br>"
    								+ "Estos datos deben estar separados por comas ',' "
    							+ "</div>"
    						+ "</div>"
    						+ "<div style=\"padding:200px; width:83.8%; background:#f4b24d; color:white; font-size:150%; position:absolute ; top:200px ; left:0px\">"
    						+ "</div>"
    						+ "  <br>"
    						+ "<input style=\"width:74% ; font-size:65% ; position:absolute ; padding: 10px 10px; margin: 8px 0; top:200px ; text-align:center; left:12%\" type=\"text\" name=\"numeros\">"
    						+ "  <br>"
    						+ "<input style=\"width: 80%; background-color: #FF0000 ; color: white ; background-color: #f6e91f; padding: 14px 20px; margin: 8px 0; border: none; border-radius: 4px; cursor: pointer; font-size:150% ; position:absolute ; top:250px ; text-align:center; left:10%\" type=\"submit\" value=\"Calcular Media y Desviacion Estandar\">"
    					+ "</form>"
    				+ "</center>"
    				+ "</body>"
                + "</html>";
    	
    	return pageContent;
    
    }

	public static String resultados(Request request, Response response) {
	
		double numero;
		ListaEnlazada datos = new ListaEnlazada();
		ListaEnlazada datosCopia = new ListaEnlazada();
		String numeros = request.queryParams("numeros");
		String [] result = numeros.split(",");
	
		for (String valor : result) 
		{
			numero = Double.parseDouble(valor);
			datos.insertar(numero);
			datosCopia.insertar(numero);
		}
	

		String[] desviacionEstandar = sacarDesviacion(datos, datosCopia);
		String pageResponse
	
		= "<!DOCTYPE html>"
				+ "<html>"
					+ "<head>"
						+ "<title>AREP Laboratorio 2 Desviacion estandar </title>"
					+ "</head>"
					+ "<body bgcolor=#f4b24d>"
						+ "<center>"
							+ "<div style=\"padding:30px; width:96.9%; background:#f4b24d; color:white; font-size:300%; position:absolute ; top:0px ; left:0px\">"
								+ "Datos"
								+ "</div>"
							+ "<div style=\"padding:30px; width:96.9%; background:#f6e91f; color:white; font-size:150%; position:absolute ; top:100px ; left:0px\">"
								+ "Los numeros dentro de la lista enlazada son: " + numeros 
							+ "</div>"
							+ "<div style=\"padding:30px; width:96.9%; background:#f6e91f; color:white; font-size:150%; position:absolute ; top:200px ; left:0px\">"
								+ "La Media del Conjunto de Numeros es: "
								+ desviacionEstandar[1]
							+ "</div>"
							+ "<div style=\"padding:30px; width:96.9%; background:#f6e91f; color:white; font-size:150%; position:absolute ; top:300px ; left:0px\">"
								+ "La Desviacion Estandar es: "
								+ desviacionEstandar[0]
								+ "<br><br>"
								+ "<form>"
									+ "<input type=\"button\" value=\"Volver\" onclick=\"history.back()\">"
								+ "</form>"
							+ "</div>"
						+ "</center>"
					+ "</body>"
				+ "</html>";
	
		return pageResponse;
	}

}