package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase encargada de gestionar la conexion con la base de datos. Utiliza el
 * patron Singleton para mantener una unica instancia de la conexion y carga las
 * credenciales desde un archivo de propiedades externo. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class ConnectionDB {

	private static Connection conn = null;

	/**
	 * Lee el archivo de configuracion externo para obtener los parametros de
	 * acceso.
	 * @return Objeto Properties con la configuracion de la base de datos.
	 * 
	 * @throws IOException Si el archivo config.properties no puede ser hallado o
	 *                     leido.
	 */
	private static Properties cargarPropiedades() throws IOException {
		Properties props = new Properties();
		try (InputStream input = ConnectionDB.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null)
				throw new IOException("No se encontro config.properties");
			props.load(input);
		}
		return props;
	}

	/**
	 * Establece y retorna la conexion activa a la base de datos. Si la conexion no
	 * existe o esta cerrada, intenta crear una nueva. * @return Objeto Connection
	 * activo.
	 * 
	 * @throws SQLException Si ocurre un error al intentar conectar con el servidor
	 *                      o al leer las propiedades de configuracion.
	 */
	public static Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			try {
				Properties props = cargarPropiedades();
				conn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.usuario"),
						props.getProperty("db.clave"));
			} catch (IOException e) {
				throw new SQLException("Error cargando configuracion de BD: " + e.getMessage());
			}
		}
		return conn;
	}

	/**
	 * Cierra la conexion actual con la base de datos si esta se encuentra abierta.
	 * * @throws SQLException Si ocurre un error durante el cierre de la conexion.
	 */
	public static void closeConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
}