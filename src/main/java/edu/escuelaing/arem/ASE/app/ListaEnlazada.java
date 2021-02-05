package edu.escuelaing.arem.ASE.app;

/*
 * Nuestra (LinkedList)
 */
public class ListaEnlazada 
{
	/*
	 * Los nodos de la lista enlazada
	 */
    class Nodo 
    {
    	double info;
        Nodo sig;
    }
 
    private Nodo raiz;
    private int cabeza;
    private int cola;
    
    /*
     * Constructor de la linkedlist
     */
    public ListaEnlazada() {
        raiz=null;
        cabeza = 0;
        cola = 0;
    }
    
    /*
     * Insertamos a nuestra lista el dato y le damos el puntero de su siguiente nodo
     * @param x  Datos que vamos a ingresar en la lista
     */
    public void insertar(double x) 
    {
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.info = x;
        
        if (raiz==null)
        {
            nuevo.sig = null;
            raiz = nuevo;
        }
        else
        {
            nuevo.sig = raiz;
            raiz = nuevo;
            cola += 1;
        }
    }
    
    /*
     * Sacamos los datos de la lista uno por uno
     * @return valor Último valor de la lista
     */
    public double extraer ()
    {
        if (raiz!=null)
        {
            double informacion = raiz.info;
            raiz = raiz.sig;
            cola -= 1;
            return informacion;
        }
        else
        {
            return Integer.MAX_VALUE;
        }
    }
    
    /*
     * Saber donde se encuentra la cabeza de la lista
     * @return cabeza
     */
    public int getCabeza()
    {
    	return cabeza;
    }
    
    /*
     * Saber donde se encuentra la cola de la lista
     * @return cola
     */
    public int getCola()
    {
    	return cola;
    }
    
    public void imprimir() 
    {
        Nodo reco=raiz;
        
        while (reco!=null) {
            System.out.print(reco.info+"-");
            reco=reco.sig;
        }
        System.out.println();
    }
}