package model;

/**
 * Clase que representa un nodo dentro de una estructura de lista enlazada
 * simple. Cada nodo encapsula un objeto de tipo Movimiento y mantiene una
 * referencia al siguiente elemento en la secuencia. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class NodoMovimiento {
	private Movimiento dato;
	private NodoMovimiento siguiente;

	/**
	 * Constructor que inicializa el nodo con un objeto Movimiento. Por defecto, la
	 * referencia al siguiente nodo se establece como null. * @param dato El objeto
	 * Movimiento que sera almacenado en este nodo.
	 */
	public NodoMovimiento(Movimiento dato) {
		this.dato = dato;
		this.siguiente = null;
	}

	/**
	 * Obtiene el dato almacenado en el nodo.
	 * 
	 * @return El objeto Movimiento contenido.
	 */
	public Movimiento getDato() {
		return dato;
	}

	/**
	 * Define o actualiza el dato almacenado en el nodo.
	 * 
	 * @param dato El nuevo objeto Movimiento a almacenar.
	 */
	public void setDato(Movimiento dato) {
		this.dato = dato;
	}

	/**
	 * Obtiene la referencia al siguiente nodo en la lista.
	 * 
	 * @return El objeto NodoMovimiento siguiente, o null si es el final de la
	 *         lista.
	 */
	public NodoMovimiento getSiguiente() {
		return siguiente;
	}

	/**
	 * Establece la referencia al siguiente nodo en la estructura enlazada.
	 * 
	 * @param siguiente El nodo que continuara la secuencia.
	 */
	public void setSiguiente(NodoMovimiento siguiente) {
		this.siguiente = siguiente;
	}
}