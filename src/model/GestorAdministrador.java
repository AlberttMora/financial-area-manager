package model;

import java.sql.SQLException;

/**
 * Clase encargada de la logica de negocio para la gestion de la cuenta
 * principal. Actua como un intermediario entre la capa de presentacion y el
 * acceso a datos (DAO), asegurando que se cumplan las reglas de validacion
 * antes de persistir cambios. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class GestorAdministrador {

	private Cuenta cuentaPrincipal;
	private final CuentaDAO dao;

	/**
	 * Constructor que inicializa el gestor y recupera la cuenta existente desde la
	 * BD. * @param dao Objeto de acceso a datos para la entidad Cuenta.
	 */
	public GestorAdministrador(CuentaDAO dao) {
		this.dao = dao;
		try {
			this.cuentaPrincipal = dao.obtenerCuentaPrincipal();
		} catch (SQLException e) {
			this.cuentaPrincipal = null;
		}
	}

	/**
	 * Crea una nueva cuenta principal en el sistema siempre que no exista una
	 * previa. * @param numeroCuenta El numero identificador de la cuenta.
	 * 
	 * @param proposito    La finalidad u objetivo de la cuenta.
	 * @param saldoInicial El monto de dinero con el que inicia la cuenta.
	 * @throws IllegalStateException    Si ya existe una cuenta registrada o falla
	 *                                  la BD.
	 * @throws IllegalArgumentException Si el numero es vacio o el saldo es
	 *                                  negativo.
	 */
	public void crearCuenta(String numeroCuenta, String proposito, double saldoInicial) {
		if (cuentaPrincipal != null) {
			throw new IllegalStateException("Ya existe una cuenta creada");
		}
		if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
			throw new IllegalArgumentException("Numero de cuenta invalido");
		}
		if (saldoInicial < 0) {
			throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
		}
		cuentaPrincipal = new Cuenta(numeroCuenta, proposito, saldoInicial);
		try {
			dao.guardar(cuentaPrincipal);
		} catch (SQLException e) {
			cuentaPrincipal = null;
			throw new IllegalStateException("Error al guardar la cuenta en base de datos");
		}
	}

	/**
	 * Retorna la instancia de la cuenta principal gestionada. * @return El objeto
	 * Cuenta activo.
	 * 
	 * @throws IllegalStateException Si no se ha creado ninguna cuenta aun.
	 */
	public Cuenta consultarCuenta() {
		if (cuentaPrincipal == null) {
			throw new IllegalStateException("No existe una cuenta creada");
		}
		return cuentaPrincipal;
	}

	/**
	 * Actualiza la informacion de identificacion de la cuenta en memoria y base de
	 * datos. * @param nuevoNumero Nuevo numero para la cuenta.
	 * 
	 * @param nuevoProposito Nueva descripcion del proposito.
	 * @throws IllegalStateException    Si no existe cuenta o falla la conexion a
	 *                                  BD.
	 * @throws IllegalArgumentException Si el nuevo numero es nulo o vacio.
	 */
	public void modificarCuenta(String nuevoNumero, String nuevoProposito) {
		if (cuentaPrincipal == null) {
			throw new IllegalStateException("No existe una cuenta creada");
		}
		if (nuevoNumero == null || nuevoNumero.trim().isEmpty()) {
			throw new IllegalArgumentException("Numero invalido");
		}

		cuentaPrincipal.setNumeroCuenta(nuevoNumero);
		cuentaPrincipal.setPropositoCuenta(nuevoProposito);

		try {
			dao.actualizar(cuentaPrincipal);
		} catch (SQLException e) {
			throw new IllegalStateException("Error al actualizar la cuenta en base de datos");
		}
	}

	/**
	 * Borra definitivamente la cuenta principal tanto del sistema como de la base
	 * de datos. * @throws IllegalStateException Si no hay cuenta para eliminar o
	 * falla la BD.
	 */
	public void eliminarCuenta() {
		if (cuentaPrincipal == null) {
			throw new IllegalStateException("No existe cuenta para eliminar");
		}

		try {
			dao.eliminarCuentaPrincipal();
		} catch (SQLException e) {
			throw new IllegalStateException("Error al eliminar la cuenta en base de datos");
		}

		cuentaPrincipal = null;
	}

	/**
	 * Verifica si la referencia a la cuenta principal esta inicializada. * @return
	 * true si existe una cuenta cargada, false en caso contrario.
	 */
	public boolean existeCuenta() {
		return cuentaPrincipal != null;
	}
}