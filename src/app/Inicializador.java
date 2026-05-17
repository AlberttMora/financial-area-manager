package app;

import model.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Inicializador {

	private GestorAreas gestorAreas;
	private GestorAdministrador gestorAdmin;
	private Administrador admin;

	public Inicializador() throws SQLException {
		Connection conn = ConnectionDB.getConnection();
		GestorAreasDBDAO daoDB = new GestorAreasDBDAO(conn);
		GestorAreasArchivosDAO daoArchivo = new GestorAreasArchivosDAO();
		CuentaDAO cuentaDAO = new CuentaDAO(conn);
		Cuenta cuenta = new Cuenta();

		this.gestorAreas = new GestorAreas(daoDB, daoArchivo, conn, cuenta);
		this.gestorAdmin = new GestorAdministrador(cuentaDAO);
		this.admin = new Administrador("Albertt", "123");
	}

	public GestorAreas getGestorAreas() {
		return gestorAreas;
	}

	public GestorAdministrador getGestorAdmin() {
		return gestorAdmin;
	}

	public Administrador getAdmin() {
		return admin;
	}
}