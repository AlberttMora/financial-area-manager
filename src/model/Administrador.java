package model;

/**
 * Representa a un usuario con privilegios de administrador en el sistema. Esta
 * clase gestiona las credenciales y la verificacion de acceso. * @author
 * Albertt Mora
 * 
 * @version 1.0
 */
public class Administrador {

	private String usuario;
	private String clave;

	/**
	 * Constructor para crear una instancia de Administrador. * @param usuario
	 * Nombre de usuario para el acceso.
	 * 
	 * @param clave Contrasena asociada a la cuenta.
	 */
	public Administrador(String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}

	/**
	 * Valida si las credenciales ingresadas coinciden con las del administrador.
	 * * @param usuario El nombre de usuario a verificar.
	 * 
	 * @param clave La contrasena a verificar.
	 * @return true si ambos parametros coinciden con los atributos de la clase,
	 *         false en caso contrario.
	 */
	public boolean autenticar(String usuario, String clave) {
		return this.usuario.equals(usuario) && this.clave.equals(clave);
	}
}