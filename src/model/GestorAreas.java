package model;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase controladora que gestiona la interaccion entre las diferentes areas del
 * sistema. Coordina las operaciones de registro, eliminacion y consulta,
 * integrando la persistencia en base de datos y archivos.
 * 
 * @author Albertt Mora
 * 
 * @version 1.0
 */
public class GestorAreas {

	private AreaDeInversiones areaInversiones;
	private AreaDeMarketing areaMarketing;
	private AreaInfraestructura areaInfraestructura;
	private Cuenta cuenta;
	private GestorAreasDBDAO daoDB;
	private GestorAreasArchivosDAO daoArchivo;
	private Connection conn;

	/**
	 * Constructor que inicializa las areas y los objetos de acceso a datos.
	 * * @param daoDB Objeto DAO para base de datos.
	 * 
	 * @param daoArchivo Objeto DAO para archivos.
	 * @param conn       Conexion activa a la base de datos.
	 * @param cuenta     Cuenta contable asociada.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public GestorAreas(GestorAreasDBDAO daoDB, GestorAreasArchivosDAO daoArchivo, Connection conn, Cuenta cuenta)
			throws SQLException {
		this.cuenta = cuenta;
		this.areaInversiones = new AreaDeInversiones(0, new ListaElementos<>(), cuenta);
		this.areaMarketing = new AreaDeMarketing(0, new ListaElementos<>(), cuenta);
		this.areaInfraestructura = new AreaInfraestructura(0, new ListaElementos<>(), cuenta);
		this.daoDB = daoDB;
		this.daoArchivo = daoArchivo;
		this.conn = conn;
	}

	/**
	 * Ejecuta una accion con persistencia dual y manejo de transacciones.
	 */
	private void ejecutarConPersistencia(AccionPersistencia accionMemoria, AccionPersistencia accionArchivo,
			AccionPersistencia accionDB) throws SQLException, IOException, ElementoInvalidoException {
		accionMemoria.ejecutar();

		if (accionArchivo != null)
			accionArchivo.ejecutar();
		if (accionDB != null) {
			conn.setAutoCommit(false);
			try {
				accionDB.ejecutar();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		}
	}

	/**
	 * Dirige la ejecucion segun el tipo de persistencia solicitado.
	 */
	private void ejecutarSegunTipo(TipoPersistencia tipo, AccionPersistencia accionMemoria,
			AccionPersistencia accionArchivo, AccionPersistencia accionDB)
			throws SQLException, IOException, ElementoInvalidoException {

		switch (tipo) {
		case ARCHIVO:
			ejecutarConPersistencia(accionMemoria, accionArchivo, null);
			break;
		case BD:
			ejecutarConPersistencia(accionMemoria, null, accionDB);
			break;
		case AMBOS:
			ejecutarConPersistencia(accionMemoria, accionArchivo, accionDB);
			break;
		default:
			throw new IllegalArgumentException("Tipo de persistencia inválido");
		}
	}

	public void agregarInversion(Inversion inv, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		ejecutarSegunTipo(tipo, () -> areaInversiones.registrarElemento(inv), () -> daoArchivo.agregarInversion(inv),
				() -> daoDB.agregarInversion(inv));
	}

	public void eliminarInversion(String id, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		Inversion inv = buscarInversion(id);
		if (inv == null)
			throw new ElementoInvalidoException("No existe la inversión con id " + id);
		ejecutarSegunTipo(tipo, () -> areaInversiones.eliminarElemento(inv), () -> daoArchivo.eliminarInversion(id),
				() -> daoDB.eliminarInversion(id));
	}

	public void actualizarInversion(Inversion inv, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		ejecutarSegunTipo(tipo, () -> areaInversiones.actualizarElemento(inv), () -> daoArchivo.actualizar(inv),
				() -> daoDB.actualizar(inv));
	}

	public void agregarEstrategia(EstrategiaMarketing est, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		ejecutarSegunTipo(tipo, () -> areaMarketing.registrarElemento(est), () -> daoArchivo.agregarEstrategia(est),
				() -> daoDB.agregarEstrategia(est));
	}

	public void eliminarEstrategia(String id, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		EstrategiaMarketing est = buscarEstrategia(id);
		if (est == null)
			throw new ElementoInvalidoException("No existe la estrategia con id " + id);
		ejecutarSegunTipo(tipo, () -> areaMarketing.eliminarElemento(est), () -> daoArchivo.eliminarEstrategia(id),
				() -> daoDB.eliminarEstrategia(id));
	}

	public void actualizarEstrategia(EstrategiaMarketing est, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		ejecutarSegunTipo(tipo, () -> areaMarketing.actualizarElemento(est), () -> daoArchivo.actualizar(est),
				() -> daoDB.actualizar(est));
	}

	public void agregarInfraestructura(Infraestructura inf, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		ejecutarSegunTipo(tipo, () -> areaInfraestructura.registrarElemento(inf),
				() -> daoArchivo.agregarInfraestructura(inf), () -> daoDB.agregarInfraestructura(inf));
	}

	public void eliminarInfraestructura(String id, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		Infraestructura inf = buscarInfraestructura(id);
		if (inf == null)
			throw new ElementoInvalidoException("No existe la infraestructura con id " + id);
		ejecutarSegunTipo(tipo, () -> areaInfraestructura.eliminarElemento(inf), () -> daoArchivo.eliminarInversion(id),
				() -> daoDB.eliminarInfraestructura(id));
	}

	public void actualizarInfraestructura(Infraestructura inf, TipoPersistencia tipo)
			throws SQLException, IOException, ElementoInvalidoException {
		ejecutarSegunTipo(tipo, () -> areaInfraestructura.actualizarElemento(inf), () -> daoArchivo.actualizar(inf),
				() -> daoDB.actualizar(inf));
	}

	/**
	 * Ordena las areas por gasto real utilizando el algoritmo de burbuja. * @return
	 * Arreglo de areas ordenadas.
	 */
	public Area<?>[] obtenerAreasOrdenadasPorGasto() {
		Area<?>[] areas = { areaInversiones, areaMarketing, areaInfraestructura };
		int n = areas.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (areas[j].getGastosReales() < areas[j + 1].getGastosReales()) {
					Area<?> temp = areas[j];
					areas[j] = areas[j + 1];
					areas[j + 1] = temp;
				}
			}
		}
		return areas;
	}

	/**
	 * Genera una matriz con el presupuesto y desviacion de cada area.
	 */
	public double[][] obtenerMatrizResumenFinanciero() {
		double[][] matriz = new double[3][2];
		matriz[0][0] = areaInversiones.getPresupuesto();
		matriz[0][1] = areaInversiones.getDesviacion();
		matriz[1][0] = areaMarketing.getPresupuesto();
		matriz[1][1] = areaMarketing.getDesviacion();
		matriz[2][0] = areaInfraestructura.getPresupuesto();
		matriz[2][1] = areaInfraestructura.getDesviacion();
		return matriz;
	}

	public Inversion buscarInversion(String id) {
		return areaInversiones.buscarElemento(id);
	}

	public EstrategiaMarketing buscarEstrategia(String id) {
		return areaMarketing.buscarElemento(id);
	}

	public Infraestructura buscarInfraestructura(String id) {
		return areaInfraestructura.buscarElemento(id);
	}

	public ListaElementos<Inversion> getInversiones() {
		return areaInversiones.getElementos();
	}

	public ListaElementos<EstrategiaMarketing> getEstrategias() {
		return areaMarketing.getElementos();
	}

	public ListaElementos<Infraestructura> getInfraestructuras() {
		return areaInfraestructura.getElementos();
	}

	public double[] obtenerImpactosEconomicos() {
		return new double[] { areaInversiones.getGastosReales(), areaMarketing.getGastosReales(),
				areaInfraestructura.getGastosReales() };
	}

	public String[] obtenerNombresAreas() {
		return new String[] { "Inversiones", "Marketing", "Infraestructura" };
	}

	/**
	 * Calcula el balance consolidado del sistema.
	 */
	public double balanceTotal() {
		areaInversiones.calcularBalanceDeArea();
		areaMarketing.calcularBalanceDeArea();
		areaInfraestructura.calcularBalanceDeArea();
		return areaInversiones.getGanancias() + areaMarketing.getGananciasTotales()
				- areaInfraestructura.getGastosTotales();
	}

	/**
	 * Actualiza los presupuestos de las tres áreas en memoria y persistencia.
	 * 
	 * @param pInv   Nuevo presupuesto para Inversiones.
	 * 
	 * @param pMkt   Nuevo presupuesto para Marketing.
	 * @param pInfra Nuevo presupuesto para Infraestructura.
	 * @throws SQLException Si hay error en la BD.
	 * @throws IOException  Si hay error en archivos.
	 */
	public void actualizarPresupuestos(double pInv, double pMkt, double pInfra)
			throws SQLException, IOException, ElementoInvalidoException {

		AccionPersistencia memoria = () -> {
			setPresupuestoInversiones(pInv);
			setPresupuestoMarketing(pMkt);
			setPresupuestoInfraestructura(pInfra);
		};

		AccionPersistencia archivo = () -> daoArchivo.guardarPresupuestos(pInv, pMkt, pInfra);
		AccionPersistencia db = () -> daoDB.actualizarPresupuestos(pInv, pMkt, pInfra);
		ejecutarSegunTipo(TipoPersistencia.AMBOS, memoria, archivo, db);
	}

	public void setPresupuestoInversiones(double p) {
		areaInversiones.setPresupuesto(p);
	}

	public void setPresupuestoMarketing(double p) {
		areaMarketing.setPresupuesto(p);
	}

	public void setPresupuestoInfraestructura(double p) {
		areaInfraestructura.setPresupuesto(p);
	}

	public double getPresupuestoInversiones() {
		return areaInversiones.getPresupuesto();
	}

	public double getPresupuestoMarketing() {
		return areaMarketing.getPresupuesto();
	}

	public double getPresupuestoInfraestructura() {
		return areaInfraestructura.getPresupuesto();
	}

	public void cargarDesdeArchivo() throws IOException, ElementoInvalidoException {
		areaInversiones.setElementos(daoArchivo.listarInversiones());
		areaMarketing.setElementos(daoArchivo.listarEstrategias());
		areaInfraestructura.setElementos(daoArchivo.listarInfraestructura());
	}

	public void cargarDesdeBD() throws SQLException, ElementoInvalidoException {
		areaInversiones.setElementos(daoDB.listarInversiones());
		areaMarketing.setElementos(daoDB.listarEstrategias());
		areaInfraestructura.setElementos(daoDB.listarInfraestructura());
	}

	public void cargarDesdeAmbos() throws SQLException, IOException, ElementoInvalidoException {
		ListaElementos<Inversion> invs = new ListaElementos<>();
		ListaElementos<EstrategiaMarketing> ests = new ListaElementos<>();
		ListaElementos<Infraestructura> infs = new ListaElementos<>();

		Set<String> idsInv = new HashSet<>();
		Set<String> idsEst = new HashSet<>();
		Set<String> idsInf = new HashSet<>();

		for (Inversion i : daoArchivo.listarInversiones()) {
			invs.add(i);
			idsInv.add(i.getId());
		}
		for (Inversion i : daoDB.listarInversiones()) {
			if (!idsInv.contains(i.getId())) {
				invs.add(i);
				idsInv.add(i.getId());
			}
		}

		for (EstrategiaMarketing e : daoArchivo.listarEstrategias()) {
			ests.add(e);
			idsEst.add(e.getId());
		}
		for (EstrategiaMarketing e : daoDB.listarEstrategias()) {
			if (!idsEst.contains(e.getId())) {
				ests.add(e);
				idsEst.add(e.getId());
			}
		}

		for (Infraestructura f : daoArchivo.listarInfraestructura()) {
			infs.add(f);
			idsInf.add(f.getId());
		}
		for (Infraestructura f : daoDB.listarInfraestructura()) {
			if (!idsInf.contains(f.getId())) {
				infs.add(f);
				idsInf.add(f.getId());
			}
		}

		double[] p = daoDB.cargarPresupuestos();
		if (p[0] == 0 && p[1] == 0 && p[2] == 0) {
			p = daoArchivo.cargarPresupuestos();
		}

		areaInversiones.setPresupuesto(p[0]);
		areaMarketing.setPresupuesto(p[1]);
		areaInfraestructura.setPresupuesto(p[2]);
		areaInversiones.setElementos(invs);
		areaMarketing.setElementos(ests);
		areaInfraestructura.setElementos(infs);
	}

	public void limpiarTodo() {
		areaInversiones.getElementos().clear();
		areaMarketing.getElementos().clear();
		areaInfraestructura.getElementos().clear();
	}

	public void limpiarPersistenciaArchivos() throws IOException {
		daoArchivo.limpiarTodo();
	}

	public void limpiarPersistenciaBD() throws SQLException {
		daoDB.limpiarTodo();
	}
}