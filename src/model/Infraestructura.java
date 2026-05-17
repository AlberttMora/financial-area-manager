package model;

import java.util.UUID;

/**
 * Representa un activo fisico o logico dentro del area de infraestructura.
 * Almacena informacion basica sobre la identificacion del recurso y su costo
 * asociado para el control presupuestario. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class Infraestructura {

	private String id;
	private String nombre;
	private double costo;

	/**
	 * Constructor para crear una instancia con un identificador preexistente.
	 * Utilizado principalmente al cargar datos desde persistencia. * @param id
	 * Identificador unico del activo.
	 * 
	 * @param nombre Nombre descriptivo del elemento de infraestructura.
	 * @param costo  Valor monetario o costo de adquisicion.
	 */
	public Infraestructura(String id, String nombre, double costo) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
	}

	/**
	 * Constructor para crear un nuevo activo de infraestructura. Genera
	 * automaticamente un identificador unico con prefijo INF. * @param nombre
	 * Nombre descriptivo del elemento.
	 * 
	 * @param costo Valor monetario del activo.
	 */
	public Infraestructura(String nombre, double costo) {
		this.id = generarId();
		this.nombre = nombre;
		this.costo = costo;
	}

	/**
	 * Genera un identificador unico universal (UUID) con el prefijo INF. * @return
	 * Una cadena de texto con el ID unico generado.
	 */
	private String generarId() {
		return "INF-" + UUID.randomUUID();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

}