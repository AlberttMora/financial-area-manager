package model;

import java.sql.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos (DAO) que gestiona la persistencia en la base de
 * datos para las distintas areas: Inversiones, Marketing e Infraestructura.
 * Proporciona metodos para realizar operaciones CRUD (Crear, Leer, Actualizar,
 * Borrar). * @author Albertt Mora
 * 
 * @version 1.0
 */
public class GestorAreasDBDAO {

	private Connection conn;

	/**
	 * Constructor que recibe una conexion activa a la base de datos. * @param conn
	 * Objeto Connection para ejecutar las sentencias SQL.
	 */
	public GestorAreasDBDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Inserta un nuevo registro de inversion en la base de datos. * @param inv El
	 * objeto Inversion con los datos a guardar.
	 * 
	 * @throws SQLException Si ocurre un error durante la ejecucion del comando SQL.
	 */
	public void agregarInversion(Inversion inv) throws SQLException {
		String sql = "INSERT INTO Inversiones(id, gananciaNeta, montoInversion, fechaEstimadaRecuperacion, cantAniosRetrasados, porcentajeInteres) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, inv.getId());
			ps.setDouble(2, inv.getGananciaNeta());
			ps.setDouble(3, inv.getMontoInversion());
			ps.setTimestamp(4, Timestamp.valueOf(inv.getFechaEstimadaRecuperacion()));
			ps.setInt(5, inv.getCantAniosRetrasados());
			ps.setDouble(6, inv.getPorcentajeInteres());
			ps.executeUpdate();
		}
	}

	/**
	 * Inserta una nueva estrategia de marketing en la base de datos. * @param est
	 * El objeto EstrategiaMarketing con los datos a guardar.
	 * 
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public void agregarEstrategia(EstrategiaMarketing est) throws SQLException {
		String sql = "INSERT INTO Marketing(id, nombre, costo, retornoGenerado, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, est.getId());
			ps.setString(2, est.getNombre());
			ps.setDouble(3, est.getCosto());
			ps.setDouble(4, est.getRetornoGenerado());
			ps.setTimestamp(5, Timestamp.valueOf(est.getFechaInicio()));
			ps.setTimestamp(6, Timestamp.valueOf(est.getFechaFin()));
			ps.executeUpdate();
		}
	}

	/**
	 * Inserta un nuevo activo de infraestructura en la base de datos. * @param inf
	 * El objeto Infraestructura con los datos a guardar.
	 * 
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public void agregarInfraestructura(Infraestructura inf) throws SQLException {
		String sql = "INSERT INTO Infraestructura(id, nombre, costo) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, inf.getId());
			ps.setString(2, inf.getNombre());
			ps.setDouble(3, inf.getCosto());
			ps.executeUpdate();
		}
	}

	/**
	 * Elimina una inversion de la base de datos segun su ID. * @param id
	 * Identificador unico de la inversion.
	 * 
	 * @throws SQLException Si falla la operacion de borrado.
	 */
	public void eliminarInversion(String id) throws SQLException {
		String sql = "DELETE FROM Inversiones WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, id);
			ps.executeUpdate();
		}
	}

	/**
	 * Elimina una estrategia de marketing de la base de datos segun su ID. * @param
	 * id Identificador unico de la estrategia.
	 * 
	 * @throws SQLException Si falla la operacion de borrado.
	 */
	public void eliminarEstrategia(String id) throws SQLException {
		String sql = "DELETE FROM Marketing WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, id);
			ps.executeUpdate();
		}
	}

	/**
	 * Elimina un registro de infraestructura de la base de datos segun su ID.
	 * * @param id Identificador unico de la infraestructura.
	 * 
	 * @throws SQLException Si falla la operacion de borrado.
	 */
	public void eliminarInfraestructura(String id) throws SQLException {
		String sql = "DELETE FROM Infraestructura WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, id);
			ps.executeUpdate();
		}
	}

	/**
	 * Obtiene la lista completa de inversiones registradas en la base de datos.
	 * * @return Lista de objetos Inversion.
	 * 
	 * @throws SQLException Si ocurre un error al consultar la tabla.
	 */
	public ListaElementos<Inversion> listarInversiones() throws SQLException {
		ListaElementos<Inversion> lista = new ListaElementos<>();
		String sql = "SELECT * FROM Inversiones";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Inversion inv = new Inversion(rs.getDouble("gananciaNeta"), rs.getDouble("montoInversion"),
						rs.getTimestamp("fechaEstimadaRecuperacion").toLocalDateTime(),
						rs.getInt("cantAniosRetrasados"));
				inv.setId(rs.getString("id"));
				inv.setPorcentajeInteres(rs.getDouble("porcentajeInteres"));
				lista.add(inv);
			}
		}
		return lista;
	}

	/**
	 * Obtiene la lista completa de estrategias de marketing registradas. * @return
	 * Lista de objetos EstrategiaMarketing.
	 * 
	 * @throws SQLException Si ocurre un error al consultar la tabla.
	 */
	public ListaElementos<EstrategiaMarketing> listarEstrategias() throws SQLException {
		ListaElementos<EstrategiaMarketing> lista = new ListaElementos<>();
		String sql = "SELECT * FROM Marketing";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				EstrategiaMarketing est = new EstrategiaMarketing(rs.getString("nombre"), rs.getDouble("costo"),
						rs.getDouble("retornoGenerado"), rs.getTimestamp("fechaInicio").toLocalDateTime(),
						rs.getTimestamp("fechaFin").toLocalDateTime());
				est.setId(rs.getString("id"));
				lista.add(est);
			}
		}
		return lista;
	}

	/**
	 * Obtiene la lista completa de activos de infraestructura registrados.
	 * * @return Lista de objetos Infraestructura.
	 * 
	 * @throws SQLException Si ocurre un error al consultar la tabla.
	 */
	public ListaElementos<Infraestructura> listarInfraestructura() throws SQLException {
		ListaElementos<Infraestructura> lista = new ListaElementos<>();
		String sql = "SELECT * FROM Infraestructura";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Infraestructura inf = new Infraestructura(rs.getString("nombre"), rs.getDouble("costo"));
				inf.setId(rs.getString("id"));
				lista.add(inf);
			}
		}
		return lista;
	}

	/**
	 * Actualiza los datos de una inversion existente. * @param inv Objeto con los
	 * datos actualizados.
	 * 
	 * @throws SQLException Si no se encuentra el ID o falla la actualizacion.
	 */
	public void actualizar(Inversion inv) throws SQLException {
		String sql = "UPDATE Inversiones SET gananciaNeta = ?, montoInversion = ?, fechaEstimadaRecuperacion = ?, cantAniosRetrasados = ?, porcentajeInteres = ? WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDouble(1, inv.getGananciaNeta());
			ps.setDouble(2, inv.getMontoInversion());
			ps.setTimestamp(3, Timestamp.valueOf(inv.getFechaEstimadaRecuperacion()));
			ps.setInt(4, inv.getCantAniosRetrasados());
			ps.setDouble(5, inv.getPorcentajeInteres());
			ps.setString(6, inv.getId());
			int filas = ps.executeUpdate();
			if (filas == 0) {
				throw new SQLException("No se encontro la inversion con id: " + inv.getId());
			}
		}
	}

	/**
	 * Actualiza los datos de una estrategia de marketing existente. * @param est
	 * Objeto con los datos actualizados.
	 * 
	 * @throws SQLException Si no se encuentra el ID o falla la actualizacion.
	 */
	public void actualizar(EstrategiaMarketing est) throws SQLException {
		String sql = "UPDATE Marketing SET nombre = ?, presupuesto = ?, retornoEsperado = ?, fechaInicio = ?, fechaFin = ? WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, est.getNombre());
			ps.setDouble(2, est.getCosto());
			ps.setDouble(3, est.getRetornoGenerado());
			ps.setTimestamp(4, Timestamp.valueOf(est.getFechaInicio()));
			ps.setTimestamp(5, Timestamp.valueOf(est.getFechaFin()));
			ps.setString(6, est.getId());
			int filas = ps.executeUpdate();
			if (filas == 0) {
				throw new SQLException("No se encontro la estrategia con id: " + est.getId());
			}
		}
	}

	/**
	 * Actualiza los datos de un elemento de infraestructura existente. * @param inf
	 * Objeto con los datos actualizados.
	 * 
	 * @throws SQLException Si no se encuentra el ID o falla la actualizacion.
	 */
	public void actualizar(Infraestructura inf) throws SQLException {
		String sql = "UPDATE Infraestructura SET nombre = ?, costo = ? WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, inf.getNombre());
			ps.setDouble(2, inf.getCosto());
			ps.setString(3, inf.getId());
			int filas = ps.executeUpdate();
			if (filas == 0) {
				throw new SQLException("No se encontro la infraestructura con id: " + inf.getId());
			}
		}
	}

	/**
	 * Elimina todos los registros de todas las tablas gestionadas por este DAO.
	 * 
	 * @throws SQLException Si falla la operacion de limpieza masiva.
	 */
	public void limpiarTodo() throws SQLException {
		try (Statement st = conn.createStatement()) {
			st.executeUpdate("DELETE FROM Inversiones");
			st.executeUpdate("DELETE FROM Marketing");
			st.executeUpdate("DELETE FROM Infraestructura");
		}
	}

	/**
	 * Actualiza los montos de presupuesto en la tabla de configuración.
	 */
	public void actualizarPresupuestos(double pInv, double pMkt, double pInfra) throws SQLException {
		// Intentamos actualizar la fila 1, si no existe (primera vez), se debe
		// insertar.
		String sql = "UPDATE Presupuestos SET inversion = ?, marketing = ?, infraestructura = ? WHERE id = 1";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDouble(1, pInv);
			ps.setDouble(2, pMkt);
			ps.setDouble(3, pInfra);
			int filas = ps.executeUpdate();

			if (filas == 0) { // Si no existía la fila, la creamos
				String insertSql = "INSERT INTO Presupuestos(id, inversion, marketing, infraestructura) VALUES (1, ?, ?, ?)";
				try (PreparedStatement psIns = conn.prepareStatement(insertSql)) {
					psIns.setDouble(1, pInv);
					psIns.setDouble(2, pMkt);
					psIns.setDouble(3, pInfra);
					psIns.executeUpdate();
				}
			}
		}
	}

	/**
	 * Recupera los presupuestos desde la base de datos.
	 */
	public double[] cargarPresupuestos() throws SQLException {
		String sql = "SELECT inversion, marketing, infraestructura FROM Presupuestos WHERE id = 1";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (rs.next()) {
				return new double[] { rs.getDouble("inversion"), rs.getDouble("marketing"),
						rs.getDouble("infraestructura") };
			}
		}
		return new double[] { 0, 0, 0 };
	}
}