package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaEliminarElemento extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_DEL = new Color(192, 57, 43);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JTextField txtIdEliminar;
	private JComboBox<String> comboPersistencia;
	private JButton btnEliminar, btnCancelar;
	private String tipoArea;

	public VentanaEliminarElemento(String tipoArea) {
		this.tipoArea = tipoArea;
		setTitle("Eliminar de " + tipoArea);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("ELIMINAR ELEMENTO");
		lblTitulo.setForeground(new Color(192, 57, 43));
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 20, 340, 28);
		getContentPane().add(lblTitulo);

		JLabel lblArea = new JLabel("Area: " + tipoArea);
		lblArea.setForeground(MUTED);
		lblArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblArea.setBounds(30, 50, 340, 18);
		getContentPane().add(lblArea);

		JSeparator sep = new JSeparator();
		sep.setBounds(40, 75, 320, 2);
		sep.setForeground(BORDER);
		getContentPane().add(sep);

		JLabel lblHint = new JLabel("Ingresa el ID del elemento  (ej: INV-xxxx, MARK-xxxx, INF-xxxx)");
		lblHint.setForeground(MUTED);
		lblHint.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblHint.setBounds(40, 88, 320, 16);
		getContentPane().add(lblHint);

		crearLabel("ID del elemento a eliminar:", 106);
		txtIdEliminar = crearField(130);

		crearLabel("Eliminar de:", 182);
		comboPersistencia = new JComboBox<>(new String[] { "ARCHIVO", "BD", "AMBOS" });
		comboPersistencia.setBounds(40, 204, 320, 32);
		estilizarCombo(comboPersistencia);
		getContentPane().add(comboPersistencia);

		btnEliminar = crearBoton("ELIMINAR", C_DEL, 40, 258, 148);
		btnCancelar = crearBoton("CANCELAR", C_BACK, 200, 258, 148);
		getContentPane().add(btnEliminar);
		getContentPane().add(btnCancelar);
	}

	private void crearLabel(String texto, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setForeground(MUTED);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setBounds(40, y, 320, 18);
		getContentPane().add(lbl);
	}

	private JTextField crearField(int y) {
		JTextField tf = new JTextField();
		tf.setBounds(40, y, 320, 32);
		tf.setBackground(FIELD);
		tf.setForeground(Color.WHITE);
		tf.setCaretColor(CYAN);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER),
				BorderFactory.createEmptyBorder(2, 8, 2, 8)));
		tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
		getContentPane().add(tf);
		return tf;
	}

	private void estilizarCombo(JComboBox<String> combo) {
		combo.setBackground(FIELD);
		combo.setForeground(Color.WHITE);
		combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		combo.setBorder(BorderFactory.createLineBorder(BORDER));
	}

	private JButton crearBoton(String texto, Color bg, int x, int y, int ancho) {
		JButton btn = new JButton(texto);
		btn.setBounds(x, y, ancho, 40);
		btn.setBackground(bg);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("SansSerif", Font.BOLD, 12));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	public String getTxtIdEliminar() {
		return txtIdEliminar.getText().trim();
	}

	public String getTipoPersistencia() {
		return (String) comboPersistencia.getSelectedItem();
	}

	public void addBtnEliminarListener(ActionListener l) {
		btnEliminar.addActionListener(l);
	}

	public void addBtnCancelarListener(ActionListener l) {
		btnCancelar.addActionListener(l);
	}
}
