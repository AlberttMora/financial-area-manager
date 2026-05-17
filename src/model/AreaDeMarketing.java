package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que gestiona las actividades y estrategias del area de marketing.
 * Permite el control de costos, el registro de estrategias y el calculo de
 * retornos aplicando los impuestos correspondientes. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class AreaDeMarketing extends Area<EstrategiaMarketing> {

	private double gananciasTotales;
	private static final double IMPUESTO = 13.0;

	/**
	 * Obtiene el acumulado de las ganancias generadas por las estrategias.
	 * 
	 * @return El total de ganancias netas.
	 */
	public double getGananciasTotales() {
		return gananciasTotales;
	}

	/**
	 * Constructor para inicializar el Area de Marketing.
	 * 
	 * @param presupuesto Monto asignado para actividades de marketing.
	 * @param elementos   Lista inicial de estrategias de marketing.
	 * @param cuenta      Cuenta contable vinculada para movimientos financieros.
	 */
	public AreaDeMarketing(double presupuesto, ListaElementos<EstrategiaMarketing> elementos, Cuenta cuenta) {
		super(presupuesto, elementos, cuenta);
		gananciasTotales = 0.0;
	}

	/**
	 * Busca una estrategia especifica dentro del area por su identificador.
	 * 
	 * @param id Identificador unico de la estrategia.
	 * @return La estrategia encontrada o null si no existe.
	 */
	private EstrategiaMarketing buscarEstrategia(String id) {
		for (int i = 0; i < elementos.size(); i++) {
			if (elementos.get(i).getId().equalsIgnoreCase(id))
				return elementos.get(i);
		}
		return null;
	}

	/**
	 * Registra una nueva estrategia de marketing y deduce su costo de la cuenta.
	 * 
	 * @param estrategia El objeto de estrategia a registrar.
	 * @throws ElementoInvalidoException Si la estrategia es nula o ya existe.
	 */
	@Override
	public void registrarElemento(EstrategiaMarketing estrategia) throws ElementoInvalidoException {
		if (estrategia == null)
			throw new ElementoInvalidoException("La estrategia no puede ser nula");
		if (buscarEstrategia(estrategia.getId()) != null)
			throw new ElementoInvalidoException("Esa estrategia ya fue registrada");
		elementos.add(estrategia);
		cuenta.registrarMovimiento("Registro de estrategia de marketing " + estrategia.getId(), -estrategia.getCosto(),
				LocalDateTime.now(), this);
	}

	/**
	 * Elimina una estrategia de marketing de la lista actual.
	 * 
	 * @param estrategia La estrategia a remover.
	 * @throws ElementoInvalidoException Si la estrategia no se encuentra en el
	 *                                   area.
	 */
	@Override
	public void eliminarElemento(EstrategiaMarketing estrategia) throws ElementoInvalidoException {
		if (!elementos.remove(estrategia)) {
			throw new ElementoInvalidoException("Esa estrategia de marketing no existe en el area");
		}
	}

	/**
	 * Actualiza los datos de una estrategia y ajusta los saldos en la cuenta
	 * contable.
	 * 
	 * @param estActualizada Estrategia con la informacion nueva.
	 * @throws ElementoInvalidoException Si no se encuentra el ID de la estrategia.
	 */
	@Override
	public void actualizarElemento(EstrategiaMarketing estActualizada) throws ElementoInvalidoException {
		for (int i = 0; i < elementos.size(); i++) {
			EstrategiaMarketing estAnterior = elementos.get(i);
			if (estAnterior.getId().equals(estActualizada.getId())) {

				double diferenciaMonto = estActualizada.getCosto() - estAnterior.getCosto();
				if (diferenciaMonto != 0) {
					cuenta.registrarMovimiento("Ajuste monto estrategia de marketing " + estActualizada.getId(),
							-diferenciaMonto, LocalDateTime.now(), this);
				}
				double diferenciaGanancia = estActualizada.getRetornoGenerado() - estAnterior.getRetornoGenerado();
				if (diferenciaGanancia != 0) {
					cuenta.registrarMovimiento("Ajuste ganancia inversion " + estActualizada.getId(),
							diferenciaGanancia, LocalDateTime.now(), this);
				}
				elementos.set(i, estActualizada);
				return;
			}
		}
		throw new ElementoInvalidoException("Estrategia con id: " + estActualizada.getId() + " no encontrada");
	}

	/**
	 * Busca un elemento por su identificador unico.
	 * 
	 * @param id Identificador de la estrategia buscada.
	 * @return La EstrategiaMarketing correspondiente o null.
	 */
	@Override
	public EstrategiaMarketing buscarElemento(String id) {
		for (EstrategiaMarketing est : elementos) {
			if (est.getId().equalsIgnoreCase(id))
				return est;
		}
		return null;
	}

	/**
	 * Calcula el balance del area procesando el retorno de cada estrategia,
	 * deduciendo el porcentaje de impuesto y registrando los movimientos.
	 */
	@Override
	public void calcularBalanceDeArea() {
		gananciasTotales = 0;

		for (EstrategiaMarketing estrategia : elementos) {

			double retorno = estrategia.calcularRetorno();
			retorno = retorno - (retorno * (IMPUESTO / 100));

			gananciasTotales += retorno;

			cuenta.registrarMovimiento("Retorno de estrategia marketing " + estrategia.getId(), retorno,
					LocalDateTime.now(), this);
		}
	}

	/**
	 * Calcula el costo total real incurrido por todas las estrategias de marketing.
	 * 
	 * @return Suma de los costos de cada estrategia.
	 */
	@Override
	public double getGastosReales() {
		double totalCostoMkt = 0;
		for (EstrategiaMarketing est : elementos) {
			totalCostoMkt += est.getCosto();
		}
		return totalCostoMkt;
	}

}