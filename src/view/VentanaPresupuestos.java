package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPresupuestos extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_OK = new Color(41, 128, 185);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JTextField txtInversiones, txtMarketing, txtInfraestructura;
	private JButton btnGuardar, btnCancelar;

	public VentanaPresupuestos(double presInv, double presMkt, double presInfra) {
		setTitle("Presupuestos de Areas");
		setSize(400, 360);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("PRESUPUESTOS DE AREAS");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 20, 340, 26);
		getContentPane().add(lblTitulo);

		JLabel lblSub = new JLabel("Monto asignado a cada area financiera");
		lblSub.setForeground(MUTED);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setBounds(30, 48, 340, 16);
		getContentPane().add(lblSub);

		JSeparator sep = new JSeparator();
		sep.setBounds(40, 72, 320, 1);
		sep.setForeground(new Color(40, 40, 60));
		getContentPane().add(sep);

		crearLabel("Inversiones:", 88);
		txtInversiones = crearField(String.valueOf(presInv), 108);

		crearLabel("Marketing:", 158);
		txtMarketing = crearField(String.valueOf(presMkt), 178);

		crearLabel("Infraestructur:", 228);
		txtInfraestructura = crearField(String.valueOf(presInfra), 248);

		btnGuardar = crearBoton("GUARDAR", C_OK, 40, 300, 148);
		btnCancelar = crearBoton("CANCELAR", C_BACK, 200, 300, 148);
		getContentPane().add(btnGuardar);
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
		btn.setBounds(x, y, ancho, 38);
		btn.setBackground(bg);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("SansSerif", Font.BOLD, 12));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	public double getPresupuestoInversiones() {
		return parseDouble(txtInversiones.getText());
	}

	public double getPresupuestoMarketing() {
		return parseDouble(txtMarketing.getText());
	}

	public double getPresupuestoInfraestructura() {
		return parseDouble(txtInfraestructura.getText());
	}

	private double parseDouble(String s) {
		try {
			return Double.parseDouble(s.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public void addBtnGuardarListener(ActionListener l) {
		btnGuardar.addActionListener(l);
	}

	public void addBtnCancelarListener(ActionListener l) {
		btnCancelar.addActionListener(l);
	}
}
