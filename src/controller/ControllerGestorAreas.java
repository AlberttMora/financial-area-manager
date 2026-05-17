package controller;

import model.*;
import view.*;
import java.time.LocalDateTime;

public class ControllerGestorAreas {

	private GestorAreas model;
	private GestorAdministrador gestorAdmin;
	private Administrador admin;
	private Ventanas view;

	public ControllerGestorAreas(GestorAreas model, GestorAdministrador gestorAdmin, Administrador admin) {
		this.model = model;
		this.gestorAdmin = gestorAdmin;
		this.admin = admin;
		this.view = new Ventanas();

		registrarEventos();
		cargarDatosIniciales();
		view.getPrincipal().setVisible(true);
	}

	private void registrarEventos() {
		view.getPrincipal().addListenerInsertar(e -> abrirVentanaRegistro());
		view.getPrincipal().addListenerActualizar(e -> view.toggleVentana(view.getPrincipal(), view.getModificar()));
		view.getPrincipal().addListenerEliminar(e -> view.toggleVentana(view.getPrincipal(), view.getEliminar()));
		view.getPrincipal().addListenerImpactos(e -> abrirImpacto());
		view.getPrincipal().addListenerAdmin(e -> view.toggleVentana(view.getPrincipal(), view.getLogin()));
		view.getPrincipal().addListenerResumen(e -> abrirResumen());
		view.getPrincipal().addListenerVerIds(e -> abrirVerIds());
		view.getPrincipal().addListenerLimpiarDatos(e -> limpiarTodoSistema());
		view.getPrincipal().addListenerSalir(e -> System.exit(0));

		view.getModificar().addBtnActualizarListener(e -> modificarElemento());
		view.getModificar().addBtnCancelarListener(e -> view.toggleVentana(view.getModificar(), view.getPrincipal()));

		view.getEliminar().addBtnEliminarListener(e -> eliminarElemento());
		view.getEliminar().addBtnCancelarListener(e -> view.toggleVentana(view.getEliminar(), view.getPrincipal()));

		view.getLogin().addListenerAceptar(e -> autenticar());
		view.getLogin().addListenerVolver(e -> view.toggleVentana(view.getLogin(), view.getPrincipal()));

		view.getConfigCuenta().addListenerCrear(e -> view.toggleVentana(view.getConfigCuenta(), view.getCrearCuenta()));
		view.getConfigCuenta().addListenerActualizar(e -> abrirActualizarCuenta());
		view.getConfigCuenta().addListenerConsultar(e -> abrirConsultarCuenta());
		view.getConfigCuenta().addListenerEliminar(e -> abrirEliminarCuenta());
		view.getConfigCuenta().addListenerVolver(e -> view.toggleVentana(view.getConfigCuenta(), view.getPrincipal()));

		view.getCrearCuenta().addBtnGuardarListener(e -> crearCuenta());
		view.getCrearCuenta()
				.addBtnCancelarListener(e -> view.toggleVentana(view.getCrearCuenta(), view.getConfigCuenta()));

		view.getEliminarCuenta().addBtnSiListener(e -> eliminarCuenta());
		view.getEliminarCuenta()
				.addBtnNoListener(e -> view.toggleVentana(view.getEliminarCuenta(), view.getConfigCuenta()));
		view.getConfigCuenta().addListenerPresupuestos(e -> abrirPresupuestos());
	}

	private void cargarDatosIniciales() {
		try {
			model.cargarDesdeAmbos();
		} catch (Exception e) {
			view.mostrarMensaje("No se pudieron cargar todos los datos iniciales: " + e.getMessage());
		}
	}

	private void abrirImpacto() {
		VentanaImpacto impacto = new VentanaImpacto(model.obtenerNombresAreas(), model.obtenerImpactosEconomicos(),
				model.balanceTotal());
		impacto.addBtnVolverListener(e -> view.toggleVentana(view.getImpacto(), view.getPrincipal()));
		view.setImpacto(impacto);
		view.toggleVentana(view.getPrincipal(), view.getImpacto());
	}

	private void abrirResumen() {
		VentanaResumen resumen = new VentanaResumen(model.obtenerNombresAreas(), model.obtenerMatrizResumenFinanciero(),
				model.obtenerImpactosEconomicos(), model.balanceTotal());
		resumen.addBtnVolverListener(e -> view.toggleVentana(view.getResumen(), view.getPrincipal()));
		view.setResumen(resumen);
		view.toggleVentana(view.getPrincipal(), view.getResumen());
	}

	private void limpiarTodoSistema() {

		if (!view.confirmar(
				"Se borraran TODOS los elementos de inversiones,\nmarketing e infraestructura en archivos, BD y memoria.\n\nEsta accion no se puede deshacer.\n\n¿Continuar?",
				"Confirmar borrado total"))
			return;

		try {
			model.limpiarTodo();
			model.limpiarPersistenciaArchivos();
			model.limpiarPersistenciaBD();
			view.mostrarMensaje("Todos los datos de areas fueron borrados correctamente.");
		} catch (Exception e) {
			view.mostrarMensaje("Error al borrar datos: " + e.getMessage());
		}
	}

	private void abrirVerIds() {
		VentanaVerElementos ver = new VentanaVerElementos(model.getInversiones(), model.getEstrategias(),
				model.getInfraestructuras());
		ver.addBtnVolverListener(e -> view.toggleVentana(view.getVerIds(), view.getPrincipal()));
		view.setVerIds(ver);
		view.toggleVentana(view.getPrincipal(), view.getVerIds());
	}

	private void abrirActualizarCuenta() {
		try {
			Cuenta cuenta = gestorAdmin.consultarCuenta();
			VentanaActualizarCuenta actualizarCuenta = new VentanaActualizarCuenta(cuenta.getNumeroCuenta(),
					cuenta.getPropositoCuenta());
			actualizarCuenta.addBtnActualizarListener(e -> actualizarCuenta());
			actualizarCuenta.addBtnCancelarListener(
					e -> view.toggleVentana(view.getActualizarCuenta(), view.getConfigCuenta()));
			view.setActualizarCuenta(actualizarCuenta);
			view.toggleVentana(view.getConfigCuenta(), view.getActualizarCuenta());
		} catch (IllegalStateException e) {
			view.mostrarMensaje(e.getMessage());
		}
	}

	private void abrirConsultarCuenta() {
		try {
			Cuenta cuenta = gestorAdmin.consultarCuenta();
			VentanaConsultarCuenta consultarCuenta = new VentanaConsultarCuenta(cuenta.getId(),
					cuenta.getNumeroCuenta(), cuenta.getPropositoCuenta(), cuenta.getSaldo());
			consultarCuenta
					.addBtnCerrarListener(e -> view.toggleVentana(view.getConsultarCuenta(), view.getConfigCuenta()));
			view.setConsultarCuenta(consultarCuenta);
			view.toggleVentana(view.getConfigCuenta(), view.getConsultarCuenta());
		} catch (IllegalStateException e) {
			view.mostrarMensaje(e.getMessage());
		}
	}

	private void abrirEliminarCuenta() {
		try {
			gestorAdmin.consultarCuenta();
			view.toggleVentana(view.getConfigCuenta(), view.getEliminarCuenta());
		} catch (IllegalStateException e) {
			view.mostrarMensaje(e.getMessage());
		}
	}

	private void autenticar() {
		if (admin.autenticar(view.getLogin().getUsuario(), view.getLogin().getClave())) {
			view.toggleVentana(view.getLogin(), view.getConfigCuenta());
		} else {
			view.mostrarMensaje("Usuario o contraseña incorrectos");
		}
	}

	private void crearCuenta() {
		try {
			gestorAdmin.crearCuenta(view.getCrearCuenta().getTxtNumero(), view.getCrearCuenta().getTxtProposito(),
					Double.parseDouble(view.getCrearCuenta().getTxtSaldo()));
			view.mostrarMensaje("Cuenta creada correctamente");
			view.toggleVentana(view.getCrearCuenta(), view.getConfigCuenta());
		} catch (NumberFormatException e) {
			view.mostrarMensaje("El saldo debe ser un numero valido");
		} catch (IllegalArgumentException | IllegalStateException e) {
			view.mostrarMensaje("Error al crear cuenta: " + e.getMessage());
		}
	}

	private void actualizarCuenta() {
		try {
			gestorAdmin.modificarCuenta(view.getActualizarCuenta().getTxtNuevoNumero(),
					view.getActualizarCuenta().getTxtNuevoProp());
			view.mostrarMensaje("Cuenta actualizada correctamente");
			view.toggleVentana(view.getActualizarCuenta(), view.getConfigCuenta());
		} catch (IllegalArgumentException | IllegalStateException e) {
			view.mostrarMensaje("Error al actualizar: " + e.getMessage());
		}
	}

	private void eliminarCuenta() {
		try {
			gestorAdmin.eliminarCuenta();
			view.mostrarMensaje("Cuenta eliminada correctamente");
			view.toggleVentana(view.getEliminarCuenta(), view.getConfigCuenta());
		} catch (IllegalStateException e) {
			view.mostrarMensaje("Error al eliminar cuenta: " + e.getMessage());
		}
	}

	private void abrirVentanaRegistro() {
		VentanaRegistrarElemento ventana = new VentanaRegistrarElemento("Inversiones");
		ventana.addBtnGuardarListener(e -> registrarElemento(ventana));
		ventana.addBtnCancelarListener(e -> view.toggleVentana(ventana, view.getPrincipal()));
		view.toggleVentana(view.getPrincipal(), ventana);
	}

	private void registrarElemento(VentanaRegistrarElemento ventana) {
		try {
			String tipoArea = ventana.getTipoArea();
			TipoPersistencia persistencia = TipoPersistencia.values()[ventana.getTipoPersistencia()];

			switch (tipoArea) {
			case "Inversiones": {
				double monto = Double.parseDouble(ventana.getTxtMonto());
				double gananciaNeta = Double.parseDouble(ventana.getTxtGananciaNeta());
				int anios = Integer.parseInt(ventana.getTxtAnios());
				model.agregarInversion(new Inversion(gananciaNeta, monto, LocalDateTime.now().plusYears(anios), 0),
						persistencia);
				break;
			}
			case "Marketing": {
				String nombre = ventana.getTxtNombre();
				double costo = Double.parseDouble(ventana.getTxtMonto());
				double retorno = Double.parseDouble(ventana.getTxtRetorno());
				model.agregarEstrategia(new EstrategiaMarketing(nombre, costo, retorno, LocalDateTime.now(),
						LocalDateTime.now().plusMonths(6)), persistencia);
				break;
			}
			case "Infraestructura": {
				String nombre = ventana.getTxtNombre();
				double costo = Double.parseDouble(ventana.getTxtMonto());
				model.agregarInfraestructura(new Infraestructura(nombre, costo), persistencia);
				break;
			}
			}

			view.mostrarMensaje("Elemento registrado correctamente");
			ventana.limpiarCampos();

		} catch (NumberFormatException e) {
			view.mostrarMensaje("Por favor ingresa valores numericos validos en los campos de monto/años");
		} catch (ElementoInvalidoException e) {
			view.mostrarMensaje("Error al registrar: " + e.getMessage());
		} catch (Exception e) {
			view.mostrarMensaje("Error inesperado: " + e.getMessage());
		}
	}

	private void modificarElemento() {
		try {
			VentanaModificarElemento vm = view.getModificar();
			String id = vm.getTxtIdBusqueda();
			String tipoArea = vm.getTipoArea();
			TipoPersistencia persistencia = TipoPersistencia.valueOf(vm.getTipoPersistencia());

			switch (tipoArea) {
			case "Inversiones": {
				Inversion inv = model.buscarInversion(id);
				if (inv == null) {
					view.mostrarMensaje("No se encontro inversion con ID: " + id);
					return;
				}
				inv.setMontoInversion(Double.parseDouble(vm.getTxtNuevoMonto()));
				inv.setGananciaNeta(Double.parseDouble(vm.getTxtNuevaGananciaNeta()));
				inv.setFechaEstimadaRecuperacion(inv.getFechaEstimadaRecuperacion()
						.plusYears(Long.parseLong(vm.getTxtNuevosAnios()) - inv.getCantAniosRetrasados()));
				model.actualizarInversion(inv, persistencia);
				view.mostrarMensaje("Inversion actualizada correctamente");
				break;
			}
			case "Marketing": {
				EstrategiaMarketing est = model.buscarEstrategia(id);
				if (est == null) {
					view.mostrarMensaje("No se encontro estrategia con ID: " + id);
					return;
				}
				est.setNombre(vm.getTxtNuevoNombre());
				est.setCosto(Double.parseDouble(vm.getTxtNuevoMonto()));
				est.setRetornoGenerado(Double.parseDouble(vm.getTxtNuevoRetorno()));
				model.actualizarEstrategia(est, persistencia);
				view.mostrarMensaje("Estrategia actualizada correctamente");
				break;
			}
			case "Infraestructura": {
				Infraestructura inf = model.buscarInfraestructura(id);
				if (inf == null) {
					view.mostrarMensaje("No se encontro infraestructura con ID: " + id);
					return;
				}
				inf.setNombre(vm.getTxtNuevoNombre());
				inf.setCosto(Double.parseDouble(vm.getTxtNuevoMonto()));
				model.actualizarInfraestructura(inf, persistencia);
				view.mostrarMensaje("Infraestructura actualizada correctamente");
				break;
			}
			default:
				view.mostrarMensaje("Tipo de area no reconocido");
			}

		} catch (NumberFormatException e) {
			view.mostrarMensaje("Los valores numericos no son validos");
		} catch (ElementoInvalidoException e) {
			view.mostrarMensaje("Error al modificar: " + e.getMessage());
		} catch (Exception e) {
			view.mostrarMensaje("Error inesperado: " + e.getMessage());
		}
	}

	private void eliminarElemento() {
		try {
			String id = view.getEliminar().getTxtIdEliminar();
			TipoPersistencia persistencia = TipoPersistencia.valueOf(view.getEliminar().getTipoPersistencia());

			if (model.buscarInversion(id) != null) {
				model.eliminarInversion(id, persistencia);
				view.mostrarMensaje("Inversion eliminada correctamente");
				return;
			}
			if (model.buscarEstrategia(id) != null) {
				model.eliminarEstrategia(id, persistencia);
				view.mostrarMensaje("Estrategia eliminada correctamente");
				return;
			}
			if (model.buscarInfraestructura(id) != null) {
				model.eliminarInfraestructura(id, persistencia);
				view.mostrarMensaje("Infraestructura eliminada correctamente");
				return;
			}

			view.mostrarMensaje("No se encontro ningun elemento con ID: " + id);

		} catch (ElementoInvalidoException e) {
			view.mostrarMensaje("Error al eliminar: " + e.getMessage());
		} catch (Exception e) {
			view.mostrarMensaje("Error inesperado: " + e.getMessage());
		}
	}

	private void abrirPresupuestos() {
		double pInv = model.getPresupuestoInversiones();
		double pMkt = model.getPresupuestoMarketing();
		double pInfra = model.getPresupuestoInfraestructura();
		VentanaPresupuestos vPres = new VentanaPresupuestos(pInv, pMkt, pInfra);

		vPres.addBtnGuardarListener(e -> guardarPresupuestos(vPres));
		vPres.addBtnCancelarListener(e -> view.toggleVentana(view.getPresupuestos(), view.getConfigCuenta()));
		view.setPresupuestos(vPres);
		view.toggleVentana(view.getConfigCuenta(), view.getPresupuestos());
	}

	private void guardarPresupuestos(VentanaPresupuestos v) {
		try {
			double pInv = v.getPresupuestoInversiones();
			double pMkt = v.getPresupuestoMarketing();
			double pInfra = v.getPresupuestoInfraestructura();
			if (pInv < 0 || pMkt < 0 || pInfra < 0) {
				view.mostrarMensaje("Los presupuestos no pueden ser negativos.");
				return;
			}

			model.actualizarPresupuestos(pInv, pMkt, pInfra);

			view.mostrarMensaje("Presupuestos actualizados correctamente.");
			view.toggleVentana(view.getPresupuestos(), view.getConfigCuenta());

		} catch (Exception e) {
			view.mostrarMensaje("Error al guardar presupuestos: " + e.getMessage());
		}
	}
}