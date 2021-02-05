package edu.escuelaing.arem.ASE.app;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	@Test
	public void casoDePruebaUno()
	{
		App pruebaUno = new App();
		ListaEnlazada datos = pruebaUno.leerArchivo("casoDePruebaUno.txt");
    	ListaEnlazada datosCopia = pruebaUno.leerArchivo("casoDePruebaUno.txt");
    	String[] res = pruebaUno.sacarDesviacion(datos, datosCopia);
    	assertEquals(res[0], "572.03");
	}
	
	@Test
	public void casoDePruebaDos()
	{
		App pruebaDos = new App();
		ListaEnlazada datos = pruebaDos.leerArchivo("casoDePruebaDos.txt");
    	ListaEnlazada datosCopia = pruebaDos.leerArchivo("casoDePruebaDos.txt");
    	String[] res = pruebaDos.sacarDesviacion(datos, datosCopia);
    	assertEquals(res[0], "62.25");
	}
	
	@Test
	public void deberiaHacerSumatoria()
	{
		App pruebaUno = new App();
		ListaEnlazada datos = pruebaUno.leerArchivo("casoDePruebaUno.txt");
    	ListaEnlazada datosCopia = pruebaUno.leerArchivo("casoDePruebaUno.txt");
    	DesviacionEstandar desviacion = new DesviacionEstandar();
    	desviacion.setN(datos.getCola()+1);
    	double res = desviacion.sumatoria(datos);
    	assertEquals(res, 5506, 0.0);
	}
	
	@Test
	public void deberiaHacerSumatoriaConxPromedio()
	{
		App pruebaUno = new App();
		ListaEnlazada datos = pruebaUno.leerArchivo("casoDePruebaDos.txt");
    	ListaEnlazada datosCopia = pruebaUno.leerArchivo("casoDePruebaDos.txt");
    	DesviacionEstandar desviacion = new DesviacionEstandar();
    	desviacion.setN(datos.getCola()+1);
    	double xPromedio = desviacion.sumatoria(datos)/(datos.getCola()+1);
    	double res = desviacion.equisMenosEquisPromedio(datosCopia, xPromedio);
    	assertEquals(res, 2147483647, 0.0);
	}
}
