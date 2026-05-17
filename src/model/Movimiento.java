package model;

import java.time.LocalDateTime;

/**
 * Representa un registro financiero individual dentro del sistema. Contiene el
 * detalle de la operacion, el monto transado, la estampa de tiempo y el area
 * organizacional que genero dicho flujo. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class Movimiento {

	private String descripcion;
	private double monto;
	private LocalDateTime fecha;
	private Area area;

	/**
	 * Constructor para inicializar un nuevo movimiento financiero. * @param
	 * descripcion Detalle o concepto del movimiento.
	 * 
	 * @param monto Valor monetario de la operacion (positivo para ingresos,
	 *              negativo para egresos).
	 * @param fecha Fecha y hora en la que se registro el suceso.
	 * @param area  Referencia al objeto Area responsable del movimiento.
	 */
	public Movimiento(String descripcion, double monto, LocalDateTime fecha, Area area) {
		this.descripcion = descripcion;
		this.monto = monto;
		this.fecha = fecha;
		this.area = area;
	}
	  
	/**
	 * Obtiene el concepto o descripcion del movimiento.
	 * 
	 * @return Una cadena con la descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el valor monetario de la transaccion.
	 * 
	 * @return El monto del movimiento.
	 */
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * Obtiene la fecha y hora de registro.
	 * 
	 * @return Objeto LocalDateTime con la estampa de tiempo.
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene el area asociada al movimiento.
	 * 
	 * @return El objeto Area correspondiente.
	 */
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}