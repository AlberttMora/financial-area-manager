package model;

import java.io.IOException;
import java.sql.SQLException;

public interface AccionPersistencia {
	public void ejecutar() throws SQLException, IOException, ElementoInvalidoException;
}