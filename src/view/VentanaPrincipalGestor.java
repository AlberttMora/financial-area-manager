package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipalGestor extends JFrame {

	private JButton btnInsertar, btnActualizar, btnEliminar, btnImpactos, btnAdmin, btnSalir, btnResumen, btnVerIds,
			btnLimpiarDatos;

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CARD = new Color(28, 28, 42);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color C_INSERT = new Color(39, 174, 96);
	private static final Color C_MODIFY = new Color(41, 128, 185);
	private static final Color C_DELETE = new Color(192, 57, 43);
	private static final Color C_IMPACT = new Color(211, 84, 0);
	private static final Color C_ADMIN = new Color(142, 68, 173);
	private static final Color C_RESUMEN = new Color(52, 73, 94);
	private static final Color C_SALIR = new Color(44, 44, 60);

	public VentanaPrincipalGestor() {
		setTitle("Gestor de Areas");
		setSize(440, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("GESTOR DE AREAS");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(40, 28, 360, 36);
		getContentPane().add(lblTitulo);

		JLabel lblSub = new JLabel("Sistema de administracion financiera");
		lblSub.setForeground(MUTED);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setBounds(40, 66, 360, 18);
		getContentPane().add(lblSub);

		JSeparator sep = new JSeparator();
		sep.setBounds(60, 92, 320, 2);
		sep.setForeground(new Color(40, 40, 60));
		getContentPane().add(sep);

		btnInsertar = crearBoton("REGISTRAR ELEMENTO", C_INSERT, 118);
		btnActualizar = crearBoton("MODIFICAR ELEMENTO", C_MODIFY, 183);
		btnEliminar = crearBoton("ELIMINAR ELEMENTO", C_DELETE, 248);
		btnImpactos = crearBoton("ANALISIS DE IMPACTO", C_IMPACT, 313);
		btnAdmin = crearBoton("AJUSTES DE CUENTA", C_ADMIN, 378);
		btnResumen = crearBoton("VER RESUMEN", C_RESUMEN, 443);
		btnVerIds = crearBoton("VER ELEMENTOS", new Color(52, 73, 94).darker(), 508);

		btnLimpiarDatos = new JButton("Limpiar datos");
		btnLimpiarDatos.setBounds(20, 595, 120, 26);
		btnLimpiarDatos.setBackground(CARD);
		btnLimpiarDatos.setForeground(MUTED);
		btnLimpiarDatos.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnLimpiarDatos.setFocusPainted(false);
		btnLimpiarDatos.setBorderPainted(false);
		btnLimpiarDatos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnLimpiarDatos);

		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(280, 595, 130, 30);
		btnSalir.setBackground(C_SALIR);
		btnSalir.setForeground(MUTED);
		btnSalir.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnSalir);
	}

	private JButton crearBoton(String texto, Color color, int y) {
		JButton btn = new JButton(texto);
		btn.setBounds(60, y, 320, 48);
		btn.setBackground(color);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("SansSerif", Font.BOLD, 13));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setIconTextGap(12);
		getContentPane().add(btn);
		return btn;
	}

	public void addListenerInsertar(ActionListener l) {
		btnInsertar.addActionListener(l);
	}

	public void addListenerActualizar(ActionListener l) {
		btnActualizar.addActionListener(l);
	}

	public void addListenerEliminar(ActionListener l) {
		btnEliminar.addActionListener(l);
	}

	public void addListenerImpactos(ActionListener l) {
		btnImpactos.addActionListener(l);
	}

	public void addListenerAdmin(ActionListener l) {
		btnAdmin.addActionListener(l);
	}

	public void addListenerSalir(ActionListener l) {
		btnSalir.addActionListener(l);
	}

	public void addListenerResumen(ActionListener l) {
		btnResumen.addActionListener(l);
	}

	public void addListenerVerIds(ActionListener l) {
		btnVerIds.addActionListener(l);
	}

	public void addListenerLimpiarDatos(ActionListener l) {
		btnLimpiarDatos.addActionListener(l);
	}
}