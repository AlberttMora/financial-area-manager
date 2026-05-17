package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa una estructura de lista enlazada simple para gestionar
 * el historial de movimientos financieros. Permite el almacenamiento dinamico
 * de objetos de tipo Movimiento mediante nodos. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class ListaMovimientos {
	private NodoMovimiento cabeza;
	private int tamanio;

	/**
	 * Constructor que inicializa una lista vacia, con la cabeza en null y el
	 * contador de tamanio en cero.
	 */
	public ListaMovimientos() {
		this.cabeza = null;
		this.tamanio = 0;
	}

	/**
	 * Inserta un nuevo movimiento al final de la lista enlazada. Si la lista esta
	 * vacia, el nuevo nodo se convierte en la cabeza. * @param movimiento El objeto
	 * Movimiento que se desea registrar.
	 */
	public void insertar(Movimiento movimiento) {
		NodoMovimiento nuevoNodo = new NodoMovimiento(movimiento);
		if (cabeza == null) {
			cabeza = nuevoNodo;
		} else {
			NodoMovimiento temporal = cabeza;
			while (temporal.getSiguiente() != null) {
				temporal = temporal.getSiguiente();
			}
			temporal.setSiguiente(nuevoNodo);
		}
		tamanio++;
	}

	/**
	 * Convierte la estructura de lista enlazada manual a una implementacion de List
	 * de Java (ArrayList) para facilitar su uso en otras capas. * @return Una lista
	 * de tipo ArrayList conteniendo todos los movimientos.
	 */
	public ListaElementos<Movimiento> toArrayList() {
		ListaElementos<Movimiento> lista = new ListaElementos<>();
		NodoMovimiento temporal = cabeza;
		while (temporal != null) {
			lista.add(temporal.getDato());
			temporal = temporal.getSiguiente();
		}
		return lista;
	}

	/**
	 * Obtiene la cantidad total de nodos (movimientos) presentes en la lista.
	 * * @return El valor entero del tamanio actual.
	 */
	public int getTamanio() {
		return tamanio;
	}
}