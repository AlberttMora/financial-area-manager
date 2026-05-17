package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaConfigCuenta extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color C_CREATE = new Color(39, 174, 96);
	private static final Color C_EDIT = new Color(41, 128, 185);
	private static final Color C_VIEW = new Color(52, 152, 219);
	private static final Color C_DEL = new Color(192, 57, 43);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JButton btnCrear, btnActualizar, btnEliminar, btnConsultar, btnPresupuestos, btnVolver;

	public VentanaConfigCuenta() {
		setTitle("Configuracion de Cuenta");
		setSize(420, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("AJUSTES DE CUENTA");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(40, 24, 340, 30);
		getContentPane().add(lblTitulo);

		JLabel lblSub = new JLabel("Administracion de cuenta del gestor");
		lblSub.setForeground(MUTED);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setBounds(40, 56, 340, 18);
		getContentPane().add(lblSub);

		JSeparator sep = new JSeparator();
		sep.setBounds(50, 82, 320, 2);
		sep.setForeground(new Color(40, 40, 60));
		getContentPane().add(sep);

		btnCrear = crearBoton("CREAR CUENTA", C_CREATE, 100);
		btnActualizar = crearBoton("EDITAR DATOS", C_EDIT, 165);
		btnConsultar = crearBoton("VER INFORMACION", C_VIEW, 230);
		btnEliminar = crearBoton("ELIMINAR CUENTA", C_DEL, 295);
		btnPresupuestos = crearBoton("AJUSTAR PRESUPUESTOS", new Color(52, 73, 94), 360);

		btnVolver = new JButton("Regresar");
		btnVolver.setBounds(145, 438, 130, 30);
		btnVolver.setBackground(C_BACK);
		btnVolver.setForeground(MUTED);
		btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnVolver);
	}

	private JButton crearBoton(String texto, Color color, int y) {
		JButton btn = new JButton(texto);
		btn.setBounds(50, y, 320, 48);
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

	public void addListenerCrear(ActionListener l) {
		btnCrear.addActionListener(l);
	}

	public void addListenerActualizar(ActionListener l) {
		btnActualizar.addActionListener(l);
	}

	public void addListenerEliminar(ActionListener l) {
		btnEliminar.addActionListener(l);
	}

	public void addListenerConsultar(ActionListener l) {
		btnConsultar.addActionListener(l);
	}

	public void addListenerPresupuestos(ActionListener l) {
		btnPresupuestos.addActionListener(l);
	}

	public void addListenerVolver(ActionListener l) {
		btnVolver.addActionListener(l);
	}
}
