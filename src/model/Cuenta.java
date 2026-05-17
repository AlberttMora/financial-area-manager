package model;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Representa la cuenta contable de la organizacion. Gestiona el saldo
 * disponible, el calculo del gasto neto y el historial de movimientos
 * financieros asociados a diferentes areas. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class Cuenta {

	private String numeroCuenta, propositoCuenta, id;
	private double saldo;
	private double gastoNeto;
	private ListaMovimientos movimientos;

	/**
	 * Constructor por defecto que inicializa la lista de movimientos.
	 */
	public Cuenta() {
		this.movimientos = new ListaMovimientos();
	}

	/**
	 * Constructor con parametros para crear una cuenta con valores iniciales.
	 * * @param numeroCuenta Identificador numerico de la cuenta bancaria.
	 * 
	 * @param propositoCuenta Descripcion de la finalidad de la cuenta.
	 * @param saldo           Monto inicial disponible.
	 */
	public Cuenta(String numeroCuenta, String propositoCuenta, double saldo) {
		this.numeroCuenta = numeroCuenta;
		this.propositoCuenta = propositoCuenta;
		this.id = generarId();
		this.saldo = saldo;
		this.gastoNeto = 0;
		this.movimientos = new ListaMovimientos();
	}

	/**
	 * Genera un identificador unico universal (UUID) para la cuenta. * @return Una
	 * cadena de texto con el ID unico.
	 */
	public String generarId() {
		return String.valueOf(UUID.randomUUID());
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getPropositoCuenta() {
		return propositoCuenta;
	}

	public void setPropositoCuenta(String propositoCuenta) {
		this.propositoCuenta = propositoCuenta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getGastoNeto() {
		return gastoNeto;
	}

	public void setGastoNeto(double gastoNeto) {
		this.gastoNeto = gastoNeto;
	}

	/**
	 * Obtiene la lista de movimientos transformandola a un ArrayList. * @return
	 * Lista de objetos Movimiento.
	 */
	public ListaElementos<Movimiento> getMovimientos() {
		return movimientos.toArrayList();
	}

	public void setMovimientos(ListaMovimientos movimientos) {
		this.movimientos = movimientos;
	}

	/**
	 * Registra un flujo financiero en la cuenta, actualizando el saldo y el gasto
	 * neto. Si el monto es positivo se considera un ingreso; si es negativo, un
	 * egreso. * @param descripcion Detalle del motivo del movimiento.
	 * 
	 * @param monto Valor monetario del movimiento (positivo o negativo).
	 * @param fecha Fecha y hora en que se realiza la operacion.
	 * @param area  Area responsable que origina el movimiento.
	 * @throws IllegalArgumentException Si la descripcion esta vacia o solo contiene
	 *                                  espacios.
	 */
	public void registrarMovimiento(String descripcion, double monto, LocalDateTime fecha, Area area) {
		if (descripcion.trim().isEmpty())
			throw new IllegalArgumentException("Descripcion del movimiento vacio");

		if (monto > 0) {
			saldo += monto;
			gastoNeto -= monto;
			movimientos.insertar(new Movimiento(descripcion, monto, fecha, area));
		} else {
			saldo += monto;
			gastoNeto += Math.abs(monto);
			movimientos.insertar(new Movimiento(descripcion, monto, fecha, area));
		}
	}

}