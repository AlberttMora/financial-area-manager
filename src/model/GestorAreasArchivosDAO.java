package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la persistencia de datos en archivos de texto plano.
 * Implementa operaciones de lectura, escritura y actualizacion para las
 * entidades de Inversion, Marketing e Infraestructura. * @author Albertt Mora
 * 
 * @version 1.0
 */
public class GestorAreasArchivosDAO {

	private final String FILE_INV = "inversiones.txt";
	private final String FILE_MARKETING = "marketing.txt";
	private final String FILE_INFRA = "infraestructuras.txt";

	/**
	 * Agrega un nuevo registro de inversion al final del archivo correspondiente.
	 * * @param inv Objeto Inversion a persistir.
	 * 
	 * @throws IOException Si ocurre un error de escritura en el disco.
	 */
	public void agregarInversion(Inversion inv) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_INV, true));
				PrintWriter p = new PrintWriter(bw)) {
			p.println(inv.getId() + ";" + inv.getGananciaNeta() + ";" + inv.getMontoInversion() + ";"
					+ inv.getFechaEstimadaRecuperacion() + ";" + inv.getCantAniosRetrasados() + ";"
					+ inv.getPorcentajeInteres());
		}
	}

	/**
	 * Elimina una inversion del archivo basandose en su identificador unico.
	 * * @param id Identificador de la inversion a remover.
	 * 
	 * @throws IOException Si falla la lectura o escritura del archivo temporal.
	 */
	public void eliminarInversion(String id) throws IOException {
		List<String> lineas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_INV))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.split(";")[0].equalsIgnoreCase(id)) {
					lineas.add(linea);
				}
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_INV, false))) {
			for (String l : lineas)
				pw.println(l);
		}
	}

	/**
	 * Recupera todos los registros de inversiones almacenados en el archivo.
	 * * @return Lista de objetos Inversion reconstruidos.
	 * 
	 * @throws IOException Si el archivo no puede ser procesado.
	 */
	public ListaElementos<Inversion> listarInversiones() throws IOException {
		ListaElementos<Inversion> lista = new ListaElementos<>();
		File f = new File(FILE_INV);
		if (!f.exists()) {
			return lista;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				Inversion inv = new Inversion(Double.parseDouble(partes[1]), Double.parseDouble(partes[2]),
						LocalDateTime.parse(partes[3]), Integer.parseInt(partes[4]));
				inv.setId(partes[0]);
				inv.setPorcentajeInteres(Double.parseDouble(partes[5]));
				lista.add(inv);
			}
		}
		return lista;
	}

	/**
	 * Almacena una nueva estrategia de marketing en el archivo de texto. * @param
	 * est Estrategia a guardar.
	 * 
	 * @throws IOException Si hay problemas con el acceso al archivo.
	 */
	public void agregarEstrategia(EstrategiaMarketing est) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_MARKETING, true));
				PrintWriter p = new PrintWriter(bw)) {
			p.println(est.getId() + ";" + est.getNombre() + ";" + est.getCosto() + ";" + est.getRetornoGenerado() + ";"
					+ est.getFechaInicio() + ";" + est.getFechaFin());
		}
	}

	/**
	 * Elimina una estrategia de marketing del archivo segun su ID. * @param id
	 * Identificador de la estrategia.
	 * 
	 * @throws IOException Error al manipular el archivo.
	 */
	public void eliminarEstrategia(String id) throws IOException {
		List<String> lineas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_MARKETING))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.split(";")[0].equalsIgnoreCase(id)) {
					lineas.add(linea);
				}
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_MARKETING, false))) {
			for (String l : lineas)
				pw.println(l);
		}
	}

	/**
	 * Lista todas las estrategias de marketing registradas en el almacenamiento
	 * persistente. * @return Lista de estrategias reconstruidas.
	 * 
	 * @throws IOException Error de lectura.
	 */
	public ListaElementos<EstrategiaMarketing> listarEstrategias() throws IOException {
		ListaElementos<EstrategiaMarketing> lista = new ListaElementos<>();
		File f = new File(FILE_MARKETING);
		if (!f.exists()) {
			return lista;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				EstrategiaMarketing est = new EstrategiaMarketing(partes[1], Double.parseDouble(partes[2]),
						Double.parseDouble(partes[3]), LocalDateTime.parse(partes[4]), LocalDateTime.parse(partes[5]));
				est.setId(partes[0]);
				lista.add(est);
			}
		}
		return lista;
	}

	/**
	 * Agrega un activo de infraestructura al registro en disco. * @param inf Objeto
	 * Infraestructura a guardar.
	 * 
	 * @throws IOException Error de escritura.
	 */
	public void agregarInfraestructura(Infraestructura inf) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_INFRA, true));
				PrintWriter p = new PrintWriter(bw)) {
			p.println(inf.getId() + ";" + inf.getNombre() + ";" + inf.getCosto());
		}
	}

	/**
	 * Remueve una infraestructura especifica del archivo de texto. * @param id ID
	 * de la infraestructura.
	 * 
	 * @throws IOException Error de acceso.
	 */
	public void eliminarInfraestructura(String id) throws IOException {
		ListaElementos<String> lineas = new ListaElementos<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_INFRA))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.split(";")[0].equalsIgnoreCase(id)) {
					lineas.add(linea);
				}
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_INFRA, false))) {
			for (String l : lineas)
				pw.println(l);
		}
	}

	/**
	 * Recupera la lista completa de infraestructura desde el archivo. * @return
	 * Lista de elementos de infraestructura.
	 * 
	 * @throws IOException Error de lectura.
	 */
	public ListaElementos<Infraestructura> listarInfraestructura() throws IOException {
		ListaElementos<Infraestructura> lista = new ListaElementos<>();
		File f = new File(FILE_INFRA);
		if (!f.exists()) {
			return lista;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				Infraestructura inf = new Infraestructura(partes[0], partes[1], Double.parseDouble(partes[2]));
				lista.add(inf);
			}
		}
		return lista;
	}

	/**
	 * Sobrescribe la informacion de una inversion existente en el archivo. * @param
	 * inv Objeto con los datos actualizados.
	 * 
	 * @throws IOException Error al actualizar el archivo.
	 */
	public void actualizar(Inversion inv) throws IOException {
		List<String> lineas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_INV))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase(inv.getId())) {
					linea = inv.getId() + ";" + inv.getGananciaNeta() + ";" + inv.getMontoInversion() + ";"
							+ inv.getFechaEstimadaRecuperacion() + ";" + inv.getCantAniosRetrasados() + ";"
							+ inv.getPorcentajeInteres();
				}
				lineas.add(linea);
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_INV, false))) {
			for (String l : lineas)
				pw.println(l);
		}
	}

	/**
	 * Actualiza los datos de una estrategia de marketing en el archivo. * @param
	 * est Estrategia actualizada.
	 * 
	 * @throws IOException Error de escritura.
	 */
	public void actualizar(EstrategiaMarketing est) throws IOException {
		List<String> lineas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_MARKETING))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase(est.getId())) {
					linea = est.getId() + ";" + est.getNombre() + ";" + est.getCosto() + ";" + est.getRetornoGenerado()
							+ ";" + est.getFechaInicio() + ";" + est.getFechaFin();
				}
				lineas.add(linea);
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_MARKETING, false))) {
			for (String l : lineas)
				pw.println(l);
		}
	}

	/**
	 * Modifica los datos de una infraestructura en el almacenamiento persistente.
	 * 
	 * @param inf Infraestructura actualizada.
	 * @throws IOException Error al manipular el archivo.
	 */
	public void actualizar(Infraestructura inf) throws IOException {
		List<String> lineas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_INFRA))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase(inf.getId())) {
					linea = inf.getId() + ";" + inf.getNombre() + ";" + inf.getCosto();
				}
				lineas.add(linea);
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_INFRA, false))) {
			for (String l : lineas)
				pw.println(l);
		}
	}

	/**
	 * Borra el contenido de todos los archivos de persistencia utilizados por el
	 * sistema.
	 * 
	 * @throws IOException Error al vaciar los archivos.
	 */
	public void limpiarTodo() throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_INV, false))) {
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_MARKETING, false))) {
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_INFRA, false))) {
		}
	}

	private final String FILE_CONFIG = "config_presupuestos.txt";

	/**
	 * Guarda los presupuestos actuales en un archivo de configuración.
	 */
	public void guardarPresupuestos(double pInv, double pMkt, double pInfra) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_CONFIG, false))) {
			pw.println(pInv + ";" + pMkt + ";" + pInfra);
		}
	}

	/**
	 * Carga los presupuestos desde el archivo. Si no existe, retorna valores en 0.
	 */
	public double[] cargarPresupuestos() throws IOException {
		File f = new File(FILE_CONFIG);
		if (!f.exists())
			return new double[] { 0, 0, 0 };

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String linea = br.readLine();
			if (linea != null) {
				String[] p = linea.split(";");
				return new double[] { Double.parseDouble(p[0]), Double.parseDouble(p[1]), Double.parseDouble(p[2]) };
			}
		}
		return new double[] { 0, 0, 0 };
	}
}