package edu.escuelaing.arem.ASE.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Clase que nos permite leer los datos del archivo
 */
public class LecturaArchivo 
{	
	/*
	 * Constructor
	 */
    public LecturaArchivo(){}
    
    /*
     * Leemos el archivo para obtener los datos
     * @param archivo ubicación del archivo
     * @return datos Lista enlazada con los datos de la tabla (archivo de texto)
     */
    public ListaEnlazada leerArchivo(String archivo) 
    {
    	ListaEnlazada datos = new ListaEnlazada();
        try 
        {
            Scanner input = new Scanner(new File(archivo));
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                datos.insertar(Double.parseDouble(line));
            }
            input.close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return datos;
    }
}