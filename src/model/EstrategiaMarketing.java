package model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa una estrategia de marketing individual dentro del sistema.
 * Almacena informacion sobre costos, retornos generados y el periodo de
 * vigencia de la campana, permitiendo calcular la rentabilidad de la misma.
 * * @author Albertt Mora
 * 
 * @version 1.0
 */
public class EstrategiaMarketing {

	private String id;
	private String nombre;
	private double costo;
	private double retornoGenerado;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;

	/**
	 * Constructor para crear una nueva estrategia de marketing con sus datos base.
	 * * @param nombre Nombre descriptivo de la estrategia.
	 * 
	 * @param costo           Inversion inicial requerida.
	 * @param retornoGenerado Ingresos brutos obtenidos por la estrategia.
	 * @param fechaInicio     Fecha y hora de comienzo de la campana.
	 * @param fechaFin        Fecha y hora de finalizacion de la campana.
	 */
	public EstrategiaMarketing(String nombre, double costo, double retornoGenerado, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {

		this.id = generarId();
		this.nombre = nombre;
		this.costo = costo;
		this.retornoGenerado = retornoGenerado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	/**
	 * Genera un identificador unico para la estrategia con el prefijo MARK.
	 * * @return Una cadena de texto con el ID unico generado.
	 */
	private String generarId() {
		return "MARK-" + UUID.randomUUID();
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

	public double getRetornoGenerado() {
		return retornoGenerado;
	}

	public void setRetornoGenerado(double retornoGenerado) {
		this.retornoGenerado = retornoGenerado;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Calcula el beneficio neto de la estrategia restando el costo al retorno
	 * generado. * @return El valor del retorno neto (ROI simplificado).
	 */
	public double calcularRetorno() {
		return retornoGenerado - costo;
	}
}