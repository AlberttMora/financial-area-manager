package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ventanas {

	private VentanaPrincipalGestor principal;
	private VentanaRegistrarElemento registrar;
	private VentanaModificarElemento modificar;
	private VentanaEliminarElemento eliminar;
	private VentanaLoginAdmin login;
	private VentanaConfigCuenta configCuenta;
	private VentanaCrearCuenta crearCuenta;
	private VentanaActualizarCuenta actualizarCuenta;
	private VentanaConsultarCuenta consultarCuenta;
	private VentanaEliminarCuenta eliminarCuenta;
	private VentanaImpacto impacto;
	private VentanaResumen resumen;
	private VentanaVerElementos verIds;

	private VentanaPresupuestos presupuestos;

	public Ventanas() {
		principal = new VentanaPrincipalGestor();
		registrar = new VentanaRegistrarElemento("Inversiones");
		modificar = new VentanaModificarElemento("Inversiones");
		eliminar = new VentanaEliminarElemento("Inversiones");
		login = new VentanaLoginAdmin();
		configCuenta = new VentanaConfigCuenta();
		crearCuenta = new VentanaCrearCuenta();
		eliminarCuenta = new VentanaEliminarCuenta();
	}

	public VentanaPrincipalGestor getPrincipal() {
		return principal;
	}

	public VentanaRegistrarElemento getRegistrar() {
		return registrar;
	}

	public VentanaModificarElemento getModificar() {
		return modificar;
	}

	public VentanaEliminarElemento getEliminar() {
		return eliminar;
	}

	public VentanaLoginAdmin getLogin() {
		return login;
	}

	public VentanaConfigCuenta getConfigCuenta() {
		return configCuenta;
	}

	public VentanaCrearCuenta getCrearCuenta() {
		return crearCuenta;
	}

	public VentanaActualizarCuenta getActualizarCuenta() {
		return actualizarCuenta;
	}

	public VentanaConsultarCuenta getConsultarCuenta() {
		return consultarCuenta;
	}

	public VentanaEliminarCuenta getEliminarCuenta() {
		return eliminarCuenta;
	}

	public VentanaImpacto getImpacto() {
		return impacto;
	}

	public VentanaResumen getResumen() {
		return resumen;
	}

	public VentanaVerElementos getVerIds() {
		return verIds;
	}

	public VentanaPresupuestos getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(VentanaPresupuestos v) {
		this.presupuestos = v;
	}

	public void setActualizarCuenta(VentanaActualizarCuenta v) {
		this.actualizarCuenta = v;
	}

	public void setConsultarCuenta(VentanaConsultarCuenta v) {
		this.consultarCuenta = v;
	}

	public void setImpacto(VentanaImpacto v) {
		this.impacto = v;
	}

	public void setResumen(VentanaResumen v) {
		this.resumen = v;
	}

	public void setVerIds(VentanaVerElementos v) {
		this.verIds = v;
	}

	public void toggleVentana(JFrame actual, JFrame siguiente) {
		actual.setVisible(false);
		siguiente.setLocationRelativeTo(null);
		siguiente.setVisible(true);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public boolean confirmar(String mensaje, String titulo) { 
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
}