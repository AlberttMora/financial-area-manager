package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Ventana de confirmación para eliminar la cuenta.
 */
public class VentanaEliminarCuenta extends JFrame {

	// ── Paleta ────────────────────────────────────────────────────────────────
	private static final Color BG = new Color(18, 18, 28);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color C_DEL = new Color(192, 57, 43);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JButton btnSi, btnNo;

	public VentanaEliminarCuenta() {
		setTitle("Eliminar Cuenta");
		setSize(380, 220);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblMens = new JLabel("¿Eliminar la cuenta definitivamente?");
		lblMens.setForeground(Color.WHITE);
		lblMens.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMens.setHorizontalAlignment(SwingConstants.CENTER);
		lblMens.setBounds(30, 60, 320, 22);
		getContentPane().add(lblMens);

		JLabel lblSub = new JLabel("Esta accion no se puede deshacer.");
		lblSub.setForeground(MUTED);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setBounds(30, 84, 320, 18);
		getContentPane().add(lblSub);

		btnSi = new JButton("Si, ELIMINAR");
		btnSi.setBounds(40, 130, 140, 42);
		btnSi.setBackground(C_DEL);
		btnSi.setForeground(Color.WHITE);
		btnSi.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSi.setFocusPainted(false);
		btnSi.setBorderPainted(false);
		btnSi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnSi);

		btnNo = new JButton("CANCELAR");
		btnNo.setBounds(198, 130, 140, 42);
		btnNo.setBackground(C_BACK);
		btnNo.setForeground(Color.WHITE);
		btnNo.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNo.setFocusPainted(false);
		btnNo.setBorderPainted(false);
		btnNo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnNo);
	}

	public void addBtnSiListener(ActionListener l) {
		btnSi.addActionListener(l);
	}

	public void addBtnNoListener(ActionListener l) {
		btnNo.addActionListener(l);
	}
}
