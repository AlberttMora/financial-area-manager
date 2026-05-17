package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaLoginAdmin extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_ADMIN = new Color(142, 68, 173);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JButton btnEntrar, btnCancelar;

	public VentanaLoginAdmin() {
		setTitle("Configuracion de cuenta");
		setSize(380, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("ADMIN LOGIN");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(40, 68, 300, 26);
		getContentPane().add(lblTitulo);

		JLabel lblSub = new JLabel("Ingresa tus credenciales de administrador");
		lblSub.setForeground(MUTED);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setBounds(40, 96, 300, 18);
		getContentPane().add(lblSub);

		JSeparator sep = new JSeparator();
		sep.setBounds(50, 122, 280, 2);
		sep.setForeground(new Color(40, 40, 60));
		getContentPane().add(sep);

		crearLabel("Usuario:", 136);
		txtUsuario = new JTextField();
		estilizarField(txtUsuario, 158, C_ADMIN);
		getContentPane().add(txtUsuario);

		crearLabel("Contraseña:", 204);
		txtClave = new JPasswordField();
		estilizarField(txtClave, 226, C_ADMIN);
		getContentPane().add(txtClave);

		btnEntrar = new JButton("AUTENTICAR");
		btnEntrar.setBounds(40, 286, 300, 44);
		btnEntrar.setBackground(C_ADMIN);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnEntrar.setFocusPainted(false);
		btnEntrar.setBorderPainted(false);
		btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnEntrar);

		btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(130, 350, 120, 28);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setForeground(MUTED);
		btnCancelar.setBorder(null);
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnCancelar);
	}

	private void crearLabel(String texto, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setForeground(MUTED);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setBounds(40, y, 200, 18);
		getContentPane().add(lbl);
	}

	private void estilizarField(JTextField tf, int y, Color acento) {
		tf.setBounds(40, y, 300, 36);
		tf.setBackground(FIELD);
		tf.setForeground(Color.WHITE);
		tf.setCaretColor(acento);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(acento),
				BorderFactory.createEmptyBorder(2, 8, 2, 8)));
		tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
	}

	public String getUsuario() {
		return txtUsuario.getText().trim();
	}

	public String getClave() {
		return new String(txtClave.getPassword());
	}

	public void addListenerAceptar(ActionListener l) {
		btnEntrar.addActionListener(l);
	}

	public void addListenerVolver(ActionListener l) {
		btnCancelar.addActionListener(l);
	}
}
