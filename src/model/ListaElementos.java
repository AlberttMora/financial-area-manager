package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase generica que sirve como envoltura para una lista de elementos.
 * Proporciona metodos basicos para la manipulacion y consulta de colecciones de
 * cualquier tipo de objeto definido por el usuario. * @author Albertt Mora
 * 
 * @version 1.0
 * @param <T> El tipo de elementos que almacenara la lista.
 */
public class ListaElementos<T> implements Iterable<T> {

	private List<T> elementos;

	/**
	 * Constructor que inicializa la estructura de datos interna como un ArrayList.
	 */
	public ListaElementos() {
		elementos = new ArrayList<>();
	}

	/**
	 * Agrega un nuevo elemento de tipo T al final de la coleccion. * @param
	 * elemento El objeto que se desea almacenar.
	 */
	public void add(T elemento) {
		elementos.add(elemento);
	}

	/**
	 * Elimina la primera ocurrencia del elemento especificado de la coleccion.
	 * 
	 * @param elemento El objeto que se desea eliminar.
	 * @return true si el elemento fue encontrado y eliminado, false en caso contrario.
	 */
	public boolean remove(T elemento) {
	    return elementos.remove(elemento);
	}

	/**
	 * Reemplaza el elemento ubicado en la posicion indicada con el nuevo valor.
	 * 
	 * @param indice   La posicion del elemento a reemplazar (basado en cero).
	 * @param elemento El nuevo objeto que ocupara dicha posicion.
	 * @throws IndexOutOfBoundsException Si el indice esta fuera de rango.
	 */
	public void set(int indice, T elemento) {
	    elementos.set(indice, elemento);
	}
	
	/**
	 * Recupera el elemento ubicado en la posicion especificada.
	 * 
	 * @param indice La posicion del elemento (basado en cero).
	 * @return El objeto de tipo T en dicha posicion.
	 * @throws IndexOutOfBoundsException Si el indice esta fuera de rango.
	 */
	public T get(int indice) {
	    return elementos.get(indice);
	}

	/**
	 * Retorna la cantidad de elementos almacenados actualmente en la lista.
	 * 
	 * @return El numero total de elementos.
	 */
	public int size() {
	    return elementos.size();
	}

	/**
	 * Elimina todos los elementos de la coleccion, dejandola vacia.
	 */
	public void clear() {
	    elementos.clear();
	}

	/**
	 * Retorna un iterador sobre los elementos de la coleccion en orden secuencial.
	 * Permite el uso de la estructura en ciclos for-each.
	 * 
	 * @return Un iterador de tipo T.
	 */
	public java.util.Iterator<T> iterator() {
	    return elementos.iterator();
	}
	
}