package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el area especifica de inversiones de la organizacion. Se
 * encarga de registrar, actualizar y calcular el rendimiento de las
 * inversiones. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class AreaDeInversiones extends Area<Inversion> implements InterfazAreaInversion {

	private double ganancias;

	/**
	 * Obtiene el acumulado de ganancias generadas por las inversiones del area.
	 * 
	 * @return El valor total de las ganancias.
	 */
	public double getGanancias() {
		return ganancias;
	}

	/**
	 * Constructor para inicializar el Area de Inversiones.
	 * 
	 * @param presupuesto Monto asignado para realizar inversiones.
	 * @param elementos   Lista inicial de inversiones.
	 * @param cuenta      Cuenta contable asociada para registrar movimientos.
	 */
	public AreaDeInversiones(double presupuesto, ListaElementos<Inversion> elementos, Cuenta cuenta) {
		super(presupuesto, elementos, cuenta);
		ganancias = 0.0;
	}

	/**
	 * Busca una inversion dentro de la lista de elementos por su identificador.
	 * 
	 * @param id El identificador unico de la inversion.
	 * @return La inversion encontrada o null si no existe.
	 */
	private Inversion buscarInversion(String id) {
		for (int i = 0; i < elementos.size(); i++) {
			if (elementos.get(i).getId().equalsIgnoreCase(id))
				return elementos.get(i);
		}
		return null;
	}

	/**
	 * Valida que los datos de una inversion sean consistentes con las reglas de
	 * negocio.
	 * 
	 * @param gananciaNeta              Monto de la ganancia esperada.
	 * @param montoInversion            Cantidad de dinero invertida.
	 * @param fechaEstimadaRecuperacion Fecha proyectada para recuperar el capital.
	 * @param cantAniosRetrasados       Numero de periodos de retraso.
	 * @throws IllegalArgumentException Si los valores son negativos o la fecha es
	 *                                  anterior a la actual.
	 */
	public void validarInversion(double gananciaNeta, double montoInversion, LocalDateTime fechaEstimadaRecuperacion,
			int cantAniosRetrasados) {
		if (fechaEstimadaRecuperacion.isBefore(LocalDateTime.now()) || cantAniosRetrasados < 0 || montoInversion < 0)
			throw new IllegalArgumentException("La cantidad de anios estimada de recuperacion,"
					+ " la cantidad de anios atrasados, y el monto de la inversion, deben ser mayores a 0");
	}

	/**
	 * Registra una nueva inversion en el sistema y descuenta el monto de la cuenta.
	 * 
	 * @param inversion El objeto inversion a registrar.
	 * @throws ElementoInvalidoException Si la inversion es nula o ya esta
	 *                                   registrada.
	 */
	@Override
	public void registrarElemento(Inversion inversion) throws ElementoInvalidoException {
		if (inversion == null)
			throw new ElementoInvalidoException("La inversion no puede ser nula");
		Inversion inversionEncontrada = buscarInversion(inversion.getId());
		if (inversionEncontrada != null)
			throw new ElementoInvalidoException("Esa inversion ya ha sido registrada anteriormente");
		elementos.add(inversion);
		cuenta.registrarMovimiento("Registro de inversion " + inversion.getId(), -inversion.getMontoInversion(),
				LocalDateTime.now(), this);
	}

	/**
	 * Elimina una inversion de la lista del area.
	 * 
	 * @param inversion El objeto a eliminar.
	 * @throws ElementoInvalidoException Si la inversion no se encuentra en la
	 *                                   lista.
	 */
	@Override
	public void eliminarElemento(Inversion inversion) throws ElementoInvalidoException {
		if (!elementos.remove(inversion)) {
			throw new ElementoInvalidoException("Esa inversion no existe en el area");
		}
	}

	/**
	 * Actualiza los datos de una inversion y ajusta los movimientos en la cuenta
	 * contable.
	 * 
	 * @param invActualizada La inversion con los nuevos datos.
	 * @throws ElementoInvalidoException Si el ID de la inversion no existe.
	 */
	@Override
	public void actualizarElemento(Inversion invActualizada) throws ElementoInvalidoException {
		for (int i = 0; i < elementos.size(); i++) {
			Inversion invAnterior = elementos.get(i);
			if (invAnterior.getId().equals(invActualizada.getId())) {

				double diferenciaMonto = invActualizada.getMontoInversion() - invAnterior.getMontoInversion();
				if (diferenciaMonto != 0) {
					cuenta.registrarMovimiento("Ajuste monto inversion " + invActualizada.getId(), -diferenciaMonto,
							LocalDateTime.now(), this);
				}
				double diferenciaGanancia = invActualizada.getGananciaNeta() - invAnterior.getGananciaNeta();
				if (diferenciaGanancia != 0) {
					cuenta.registrarMovimiento("Ajuste ganancia inversion " + invActualizada.getId(),
							diferenciaGanancia, LocalDateTime.now(), this);
				}
				elementos.set(i, invActualizada);
				return;
			}
		}
		throw new ElementoInvalidoException("Inversion con id: " + invActualizada.getId() + " no encontrada");
	}

	/**
	 * Busca una inversion por su identificador.
	 * 
	 * @param id Identificador de la inversion.
	 * @return El objeto Inversion o null si no se halla.
	 */
	@Override
	public Inversion buscarElemento(String id) {
		for (Inversion inv : elementos) {
			if (inv.getId().equalsIgnoreCase(id))
				return inv;
		}
		return null;
	}

	/**
	 * Calcula el balance total sumando las ganancias netas de todas las
	 * inversiones.
	 */
	@Override
	public void calcularBalanceDeArea() {
		ganancias = 0;
		for (int i = 0; i < elementos.size(); i++) {
			ganancias += elementos.get(i).getGananciaNeta();
		}
	}

	/**
	 * Determina los anios de diferencia entre la fecha estimada de recuperacion y
	 * la actual.
	 * 
	 * @param id Identificador de la inversion.
	 * @throws IllegalArgumentException Si la inversion no existe.
	 */
	public void calcularAniosAtrasados(String id) {
		Inversion inversion = buscarInversion(id);
		if (inversion == null)
			throw new IllegalArgumentException("Esa inversion no ha sido registrada anteriormente");
		inversion.setCantAniosRetrasados(
				inversion.getFechaEstimadaRecuperacion().getYear() - LocalDateTime.now().getYear());
	}

	/**
	 * Calcula la ganancia final de una inversion aplicando intereses si hay
	 * retrasos.
	 * 
	 * @param id Identificador de la inversion.
	 * @throws IllegalArgumentException Si la inversion no existe.
	 */
	@Override
	public void calcularGananciaInversion(String id) {
		double ganancia = 0.0;
		Inversion inversion = buscarInversion(id);
		if (inversion == null)
			throw new IllegalArgumentException("Esa inversion no ha sido registrada anteriormente");
		calcularAniosAtrasados(id);
		double interes = 1 + (inversion.getPorcentajeInteres() / 100);
		ganancia = (inversion.getCantAniosRetrasados() == 0) ? inversion.getGananciaNeta()
				: inversion.getGananciaNeta() * interes;
		inversion.setGananciaNeta(ganancia);
		cuenta.registrarMovimiento("Ganancia de inversion " + inversion.getId(), inversion.getGananciaNeta(),
				LocalDateTime.now(), this);
	}

	/**
	 * Calcula el total de capital invertido actualmente en el area.
	 * 
	 * @return El monto total de las inversiones (gastos reales).
	 */
	@Override
	public double getGastosReales() {
		double totalInvertido = 0;
		for (Inversion inv : elementos) {
			totalInvertido += inv.getMontoInversion();
		}
		return totalInvertido;
	}
}