package model;

import java.util.List;
import java.util.UUID;

/**
 * Clase abstracta que define la estructura base para las diferentes areas del
 * sistema. Gestiona presupuestos, cuentas y una lista de elementos genericos.
 * * @author Albertt Mora
 * 
 * @version 1.0
 * @param <T> Tipo de elementos que gestionara el area.
 */
public abstract class Area<T> {

	protected double presupuesto;
	protected Cuenta cuenta;
	protected ListaElementos<T> elementos;
	protected String id;

	/**
	 * Constructor para inicializar un area con sus componentes basicos. * @param
	 * presupuesto Monto asignado al area.
	 * 
	 * @param elementos Lista inicial de elementos del area.
	 * @param cuenta    Cuenta contable asociada.
	 */
	public Area(double presupuesto, ListaElementos<T> elementos, Cuenta cuenta) {
		this.presupuesto = presupuesto;
		this.elementos = elementos;
		this.cuenta = cuenta;
		this.id = generarId();
	}

	/**
	 * Metodo abstracto para obtener el total de gastos reales ejecutados. * @return
	 * Suma de los gastos reales.
	 */
	public abstract double getGastosReales();

	/**
	 * Calcula la diferencia entre el presupuesto y los gastos reales. * @return
	 * Valor de la desviacion presupuestaria.
	 */
	public double getDesviacion() {
		return this.presupuesto - getGastosReales();
	}

	/**
	 * Calcula el avance de la ejecucion presupuestaria en terminos porcentuales.
	 * * @return Porcentaje de ejecucion. Retorna 0 si el presupuesto es menor o
	 * igual a cero.
	 */
	public double getPorcentajeEjecucion() {
		if (this.presupuesto <= 0)
			return 0;
		return (getGastosReales() / this.presupuesto) * 100;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public ListaElementos<T> getElementos() {
		return elementos;
	}

	public void setElementos(ListaElementos<T> elementos) {
		this.elementos = elementos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Genera un identificador unico para el area utilizando UUID. * @return Cadena
	 * de texto con el prefijo AREA seguido del identificador unico.
	 */
	protected String generarId() {
		return "AREA-" + UUID.randomUUID();
	}

	/**
	 * Registra un nuevo elemento en el area. * @param elemento El objeto a
	 * insertar.
	 * 
	 * @throws ElementoInvalidoException Si el elemento no cumple con las
	 *                                   validaciones.
	 */
	public abstract void registrarElemento(T elemento) throws ElementoInvalidoException;

	/**
	 * Elimina un elemento existente del area. * @param elemento El objeto a
	 * remover.
	 * 
	 * @throws ElementoInvalidoException Si el elemento no puede ser eliminado.
	 */
	public abstract void eliminarElemento(T elemento) throws ElementoInvalidoException;

	/**
	 * Actualiza la informacion de un elemento existente. * @param elemento El
	 * objeto con los datos actualizados.
	 * 
	 * @throws ElementoInvalidoException Si la actualizacion falla por reglas de
	 *                                   negocio.
	 */
	public abstract void actualizarElemento(T elemento) throws ElementoInvalidoException;

	/**
	 * Busca un elemento dentro de la lista mediante su identificador. * @param id
	 * El identificador unico del elemento buscado.
	 * 
	 * @return El elemento encontrado o null si no existe.
	 */
	public abstract T buscarElemento(String id);

	/**
	 * Realiza los calculos financieros para determinar el balance actual del area.
	 */
	public abstract void calcularBalanceDeArea();
}