package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase de acceso a datos (DAO) para la entidad Cuenta. Proporciona metodos
 * para realizar operaciones CRUD sobre la tabla Cuenta en la base de datos
 * relacional. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class CuentaDAO {

	private final Connection conn;

	/**
	 * Constructor que recibe la conexion activa a la base de datos. * @param conn
	 * Objeto Connection a utilizar para las sentencias SQL.
	 */
	public CuentaDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Inserta un nuevo registro de cuenta en la base de datos. * @param cuenta El
	 * objeto Cuenta con los datos a persistir.
	 * 
	 * @throws SQLException Si ocurre un error durante la ejecucion del INSERT.
	 */
	public void guardar(Cuenta cuenta) throws SQLException {
		String sql = "INSERT INTO Cuenta(id, numeroCuenta, propositoCuenta, saldo, gastoNeto) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, cuenta.getId());
			ps.setString(2, cuenta.getNumeroCuenta());
			ps.setString(3, cuenta.getPropositoCuenta());
			ps.setDouble(4, cuenta.getSaldo());
			ps.setDouble(5, cuenta.getGastoNeto());
			ps.executeUpdate();
		}
	}

	/**
	 * Actualiza los valores de una cuenta existente basandose en su identificador.
	 * * @param cuenta El objeto Cuenta con la informacion actualizada.
	 * 
	 * @throws SQLException Si no se encuentra el ID o falla la operacion UPDATE.
	 */
	public void actualizar(Cuenta cuenta) throws SQLException {
		String sql = "UPDATE Cuenta SET numeroCuenta = ?, propositoCuenta = ?, saldo = ?, gastoNeto = ? "
				+ "WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, cuenta.getNumeroCuenta());
			ps.setString(2, cuenta.getPropositoCuenta());
			ps.setDouble(3, cuenta.getSaldo());
			ps.setDouble(4, cuenta.getGastoNeto());
			ps.setString(5, cuenta.getId());
			int filas = ps.executeUpdate();
			if (filas == 0) {
				throw new SQLException("No se encontro la cuenta con id: " + cuenta.getId());
			}
		}
	}

	/**
	 * Recupera el primer registro de cuenta encontrado en la base de datos.
	 * * @return Un objeto Cuenta con los datos recuperados, o null si la tabla esta
	 * vacia.
	 * 
	 * @throws SQLException Si ocurre un error durante la consulta SELECT.
	 */
	public Cuenta obtenerCuentaPrincipal() throws SQLException {
		String sql = "SELECT * FROM Cuenta LIMIT 1";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

			if (!rs.next()) {
				return null;
			}

			Cuenta c = new Cuenta();
			c.setId(rs.getString("id"));
			c.setNumeroCuenta(rs.getString("numeroCuenta"));
			c.setPropositoCuenta(rs.getString("propositoCuenta"));
			c.setSaldo(rs.getDouble("saldo"));
			c.setGastoNeto(rs.getDouble("gastoNeto"));
			return c;
		}
	}

	/**
	 * Elimina todos los registros de la tabla Cuenta. * @throws SQLException Si
	 * ocurre un error durante la ejecucion de DELETE.
	 */
	public void eliminarCuentaPrincipal() throws SQLException {
		String sql = "DELETE FROM Cuenta";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.executeUpdate();
		}
	}
}