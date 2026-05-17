package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que gestiona los activos y costos relacionados con la infraestructura.
 * Permite el control de gastos, registro de nuevos activos y mantenimiento de
 * la informacion de infraestructura de la empresa. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class AreaInfraestructura extends Area<Infraestructura> {

	private double gastosTotales;

	/**
	 * Obtiene el total de gastos acumulados en el area de infraestructura.
	 * 
	 * @return El valor de los gastos totales.
	 */
	public double getGastosTotales() {
		return gastosTotales;
	}

	/**
	 * Constructor para inicializar el Area de Infraestructura.
	 * 
	 * @param presupuesto Monto maximo asignado para infraestructura.
	 * @param elementos   Lista inicial de activos de infraestructura.
	 * @param cuenta      Cuenta contable para el registro de movimientos
	 *                    financieros.
	 */
	public AreaInfraestructura(double presupuesto, ListaElementos<Infraestructura> elementos, Cuenta cuenta) {
		super(presupuesto, elementos, cuenta);
		gastosTotales = 0.0;
	}

	/**
	 * Busca un activo de infraestructura especifico por su identificador.
	 * 
	 * @param id Identificador unico del activo.
	 * @return El objeto Infraestructura hallado o null.
	 */
	private Infraestructura buscarInfraestructura(String id) {
		for (int i = 0; i < elementos.size(); i++) {
			if (elementos.get(i).getId().equalsIgnoreCase(id))
				return elementos.get(i);
		}
		return null;
	}

	/**
	 * Registra un nuevo elemento de infraestructura y descuenta su costo de la
	 * cuenta.
	 * 
	 * @param infraestructura El activo a registrar.
	 * @throws ElementoInvalidoException Si el objeto es nulo o ya esta registrado.
	 */
	@Override
	public void registrarElemento(Infraestructura infraestructura) throws ElementoInvalidoException {
		if (infraestructura == null)
			throw new ElementoInvalidoException("La infraestructura no puede ser nula");

		if (buscarInfraestructura(infraestructura.getId()) != null)
			throw new ElementoInvalidoException("Ya existe esa infraestructura");

		elementos.add(infraestructura);
		cuenta.registrarMovimiento("Registro de infraestructura " + infraestructura.getId(),
				-infraestructura.getCosto(), LocalDateTime.now(), this);
	}

	/**
	 * Elimina un activo de infraestructura de la lista del area.
	 * 
	 * @param infraestructura El activo a remover.
	 * @throws ElementoInvalidoException Si el activo no pertenece al area.
	 */
	@Override
	public void eliminarElemento(Infraestructura infraestructura) throws ElementoInvalidoException {
		if (!elementos.remove(infraestructura)) {
			throw new ElementoInvalidoException("Esa infraestructura no existe en el area");
		}
	}

	/**
	 * Actualiza los datos de una infraestructura y ajusta los saldos en la cuenta.
	 * 
	 * @param infActualizada El objeto con la informacion actualizada.
	 * @throws ElementoInvalidoException Si el ID no coincide con ningun registro.
	 */
	@Override
	public void actualizarElemento(Infraestructura infActualizada) throws ElementoInvalidoException {
		for (int i = 0; i < elementos.size(); i++) {
			Infraestructura infAnterior = elementos.get(i);
			if (infAnterior.getId().equals(infActualizada.getId())) {

				double diferenciaMonto = infActualizada.getCosto() - infAnterior.getCosto();
				if (diferenciaMonto != 0) {
					cuenta.registrarMovimiento("Ajuste monto infraestructura " + infActualizada.getId(),
							-diferenciaMonto, LocalDateTime.now(), this);
				}
				elementos.set(i, infActualizada);
				return;
			}
		}
		throw new ElementoInvalidoException("Infraestructura con id: " + infActualizada.getId() + " no encontrada");
	}

	/**
	 * Busca un elemento dentro del area mediante su identificador unico.
	 * 
	 * @param id Identificador de la infraestructura.
	 * @return El objeto Infraestructura encontrado o null.
	 */
	@Override
	public Infraestructura buscarElemento(String id) {
		for (Infraestructura inf : elementos) {
			if (inf.getId().equalsIgnoreCase(id))
				return inf;
		}
		return null;
	}

	/**
	 * Calcula el balance sumando los costos de todos los elementos registrados.
	 */
	@Override
	public void calcularBalanceDeArea() {
		gastosTotales = 0;

		for (int i = 0; i < elementos.size(); i++) {
		    gastosTotales += elementos.get(i).getCosto();
		}
	}

	/**
	 * Obtiene el monto real gastado en infraestructura.
	 * 
	 * @return Suma de los costos de todos los activos.
	 */
	@Override
	public double getGastosReales() {
		double totalInfra = 0;
		for (Infraestructura inf : elementos) {
			totalInfra += inf.getCosto();
		}
		return totalInfra;
	}

}