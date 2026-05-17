package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaResumen extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CARD = new Color(28, 28, 42);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color ORANGE = new Color(211, 84, 0);
	private static final Color C_BACK = new Color(44, 44, 60);
	private static final Color C_POS = new Color(39, 174, 96);
	private static final Color C_NEG = new Color(192, 57, 43);

	private JButton btnVolver;

	public VentanaResumen(String[] nombresAreas, double[][] matrizResumen, double[] impactos, double balanceTotal) {
		setTitle("Resumen Financiero");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		int y = 20;

		JLabel lblTitulo = new JLabel("RESUMEN FINANCIERO");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(40, y, 440, 32);
		getContentPane().add(lblTitulo);
		y += 50;

		agregarLabel("AREA", ORANGE, 40, y, 140, true);
		agregarLabel("PRESUPUESTO", ORANGE, 190, y, 120, true);
		agregarLabel("DESVIACION", ORANGE, 320, y, 120, true);
		y += 22;

		JSeparator sep1 = new JSeparator();
		sep1.setBounds(40, y, 420, 2);
		sep1.setForeground(BORDER);
		getContentPane().add(sep1);
		y += 6;

		for (int i = 0; i < nombresAreas.length; i++) {
			double desviacion = matrizResumen[i][1];
			Color colorDesv = desviacion >= 0 ? C_POS : C_NEG;

			JPanel fila = new JPanel(null);
			fila.setBounds(40, y, 420, 30);
			fila.setBackground(i % 2 == 0 ? CARD : BG);
			getContentPane().add(fila);

			JLabel lNom = new JLabel(nombresAreas[i]);
			JLabel lPres = new JLabel(String.format("$ %,.2f", matrizResumen[i][0]));
			JLabel lDesv = new JLabel(String.format("$ %,.2f", desviacion));

			lNom.setForeground(Color.WHITE);
			lPres.setForeground(Color.WHITE);
			lDesv.setForeground(colorDesv);

			Font fila_font = new Font("SansSerif", Font.PLAIN, 13);
			lNom.setFont(fila_font);
			lPres.setFont(fila_font);
			lDesv.setFont(fila_font);

			lNom.setBounds(10, 6, 145, 18);
			lPres.setBounds(150, 6, 130, 18);
			lDesv.setBounds(280, 6, 130, 18);

			fila.add(lNom);
			fila.add(lPres);
			fila.add(lDesv);

			y += 32;
		}

		y += 12;
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(40, y, 420, 2);
		sep2.setForeground(BORDER);
		getContentPane().add(sep2);
		y += 16;

		JLabel lblSubGastos = new JLabel("GASTOS REALES POR AREA");
		lblSubGastos.setForeground(ORANGE);
		lblSubGastos.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSubGastos.setBounds(40, y, 300, 20);
		getContentPane().add(lblSubGastos);
		y += 30;

		for (int i = 0; i < nombresAreas.length; i++) {
			agregarLabel(nombresAreas[i] + ":", MUTED, 40, y, 160, false);
			agregarLabel(String.format("$ %,.2f", impactos[i]), Color.WHITE, 210, y, 180, false);
			y += 26;
		}

		y += 10;
		JSeparator sep3 = new JSeparator();
		sep3.setBounds(40, y, 420, 2);
		sep3.setForeground(BORDER);
		getContentPane().add(sep3);
		y += 14;

		agregarLabel("BALANCE NETO TOTAL", MUTED, 40, y, 250, true);
		y += 26;

		Color colorBalance = balanceTotal >= 0 ? C_POS : C_NEG;
		JLabel lblBalMonto = new JLabel(String.format("$ %,.2f", balanceTotal));
		lblBalMonto.setForeground(colorBalance);
		lblBalMonto.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblBalMonto.setBounds(40, y, 350, 40);
		getContentPane().add(lblBalMonto);
		y += 56;

		btnVolver = new JButton("VOLVER AL MENU");
		btnVolver.setBounds(150, y, 200, 40);
		btnVolver.setBackground(C_BACK);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnVolver);
		y += 60;

		setSize(520, y += 50);
	}

	private void agregarLabel(String texto, Color color, int x, int y, int w, boolean bold) {
		JLabel lbl = new JLabel(texto);
		lbl.setForeground(color);
		lbl.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, 13));
		lbl.setBounds(x, y, w, 20);
		getContentPane().add(lbl);
	}

	public void addBtnVolverListener(ActionListener l) {
		btnVolver.addActionListener(l);
	}
}
