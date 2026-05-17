package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaActualizarCuenta extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_MOD = new Color(41, 128, 185);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JTextField txtNuevoNumero, txtNuevoProp;
	private JButton btnActualizar, btnCancelar;

	public VentanaActualizarCuenta(String numActual, String propActual) {
		setTitle("Editar Cuenta");
		setSize(400, 320);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("EDITAR CUENTA");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 20, 340, 28);
		getContentPane().add(lblTitulo);

		JLabel lblSub = new JLabel("Modifica los campos que deseas actualizar");
		lblSub.setForeground(MUTED);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setBounds(30, 50, 340, 16);
		getContentPane().add(lblSub);

		JSeparator sep = new JSeparator();
		sep.setBounds(40, 74, 320, 2);
		sep.setForeground(BORDER);
		getContentPane().add(sep);

		crearLabel("Numero de cuenta:", 90);
		txtNuevoNumero = crearField(numActual, 112);

		crearLabel("Proposito de la cuenta:", 162);
		txtNuevoProp = crearField(propActual, 184);

		btnActualizar = crearBoton("ACTUALIZAR", C_MOD, 40, 248, 148);
		btnCancelar = crearBoton("CANCELAR", C_BACK, 200, 248, 148);
		getContentPane().add(btnActualizar);
		getContentPane().add(btnCancelar);
	}

	private void crearLabel(String texto, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setForeground(MUTED);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setBounds(40, y, 320, 18);
		getContentPane().add(lbl);
	}

	private JTextField crearField(String valorInicial, int y) {
		JTextField tf = new JTextField(valorInicial);
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

	public String getTxtNuevoNumero() {
		return txtNuevoNumero.getText().trim();
	}

	public String getTxtNuevoProp() {
		return txtNuevoProp.getText().trim();
	}

	public void addBtnActualizarListener(ActionListener l) {
		btnActualizar.addActionListener(l);
	}

	public void addBtnCancelarListener(ActionListener l) {
		btnCancelar.addActionListener(l);
	}
}