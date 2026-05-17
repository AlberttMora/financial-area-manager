package model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa una operacion financiera de inversion dentro del sistema. Almacena
 * los montos invertidos, las ganancias proyectadas y las fechas clave para el
 * retorno de capital y el calculo de intereses. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class Inversion {

	private String id;
	private LocalDateTime fechaEstimadaRecuperacion, fechaInversion;
	private double gananciaNeta, montoInversion, porcentajeInteres;
	private int cantAniosRetrasados;

	/**
	 * Constructor para crear una nueva inversion con sus valores base. Genera
	 * automaticamente un identificador unico y establece la fecha actual como fecha
	 * de inicio de la inversion. * @param gananciaNeta Monto de ganancia proyectada
	 * o real.
	 * 
	 * @param montoInversion            Capital inicial invertido.
	 * @param fechaEstimadaRecuperacion Fecha en la que se espera recibir el
	 *                                  retorno.
	 * @param cantAniosRetrasados       Numero de anios de retraso en el retorno.
	 */
	public Inversion(double gananciaNeta, double montoInversion, LocalDateTime fechaEstimadaRecuperacion,
			int cantAniosRetrasados) {
		this.gananciaNeta = gananciaNeta;
		this.montoInversion = montoInversion;
		this.fechaEstimadaRecuperacion = fechaEstimadaRecuperacion;
		this.cantAniosRetrasados = 0;
		this.fechaInversion = LocalDateTime.now();
		this.id = generarId();
		this.porcentajeInteres = 10;
	}

	/**
	 * Genera un identificador unico universal con el prefijo INV. * @return Una
	 * cadena de texto con el ID unico generado.
	 */
	private String generarId() {
		return "INV-" + UUID.randomUUID();
	}

	public double getGananciaNeta() {
		return gananciaNeta;
	}

	public void setGananciaNeta(double gananciaNeta) {
		this.gananciaNeta = gananciaNeta;
	}

	public double getMontoInversion() {
		return montoInversion;
	}

	public void setMontoInversion(double montoInversion) {
		this.montoInversion = montoInversion;
	}

	public LocalDateTime getFechaEstimadaRecuperacion() {
		return fechaEstimadaRecuperacion;
	}

	public void setFechaEstimadaRecuperacion(LocalDateTime fechaEstimadaRecuperacion) {
		this.fechaEstimadaRecuperacion = fechaEstimadaRecuperacion;
	}

	public LocalDateTime getFechaInversion() {
		return fechaInversion;
	}

	public void setFechaInversion(LocalDateTime fechaInversion) {
		this.fechaInversion = fechaInversion;
	}

	public void setCantAniosRetrasados(int cantAniosRetrasados) {
		this.cantAniosRetrasados = cantAniosRetrasados;
	}

	public int getCantAniosRetrasados() {
		return cantAniosRetrasados;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPorcentajeInteres() {
		return porcentajeInteres;
	}

	public void setPorcentajeInteres(double porcentajeInteres) {
		this.porcentajeInteres = porcentajeInteres;
	}

}