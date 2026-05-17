package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaConsultarCuenta extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CARD = new Color(28, 28, 42);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JButton btnCerrar;

	public VentanaConsultarCuenta(String id, String num, String prop, double saldo) {
		setTitle("Informacion de Cuenta");
		setSize(400, 360);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("INFORMACION DE CUENTA");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 20, 340, 26);
		getContentPane().add(lblTitulo);

		JSeparator sep = new JSeparator();
		sep.setBounds(40, 54, 320, 2);
		sep.setForeground(BORDER);
		getContentPane().add(sep);

		JPanel card = new JPanel(null);
		card.setBounds(40, 68, 320, 198);
		card.setBackground(CARD);
		card.setBorder(BorderFactory.createLineBorder(BORDER));
		getContentPane().add(card);

		agregarFila(card, "ID", id, Color.WHITE, 14);
		agregarFila(card, "Numero de Cuenta", num, Color.WHITE, 60);
		agregarFila(card, "Proposito", prop, Color.WHITE, 106);
		Color colorSaldo = saldo >= 0 ? new Color(39, 174, 96) : new Color(192, 57, 43);
		agregarFila(card, "Saldo", String.format("$ %,.2f", saldo), colorSaldo, 152);

		btnCerrar = new JButton("CERRAR");
		btnCerrar.setBounds(130, 288, 140, 40);
		btnCerrar.setBackground(C_BACK);
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCerrar.setFocusPainted(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnCerrar);
	}

	private void agregarFila(JPanel parent, String etiqueta, String valor, Color colorValor, int y) {
		JLabel lblKey = new JLabel(etiqueta + ":");
		lblKey.setForeground(MUTED);
		lblKey.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblKey.setBounds(14, y, 110, 18);
		parent.add(lblKey);

		JLabel lblVal = new JLabel(valor);
		lblVal.setForeground(colorValor);
		lblVal.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblVal.setBounds(134, y, 172, 18);
		parent.add(lblVal);
	}

	public void addBtnCerrarListener(ActionListener l) {
		btnCerrar.addActionListener(l);
	}
}
