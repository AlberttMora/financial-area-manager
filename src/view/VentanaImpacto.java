package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaImpacto extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CARD = new Color(28, 28, 42);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_BACK = new Color(44, 44, 60);
	private static final Color[] COLORES_BARRA = { new Color(0, 210, 220), new Color(211, 84, 0),
			new Color(142, 68, 173) };

	private JButton btnVolver;
	private double[] impactos;
	private String[] nombres;

	public VentanaImpacto(String[] nombres, double[] impactos, double balanceTotal) {
		this.nombres = nombres;
		this.impactos = impactos;

		setTitle("Análisis de Impacto Económico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("INDICADORES DE IMPACTO ECONOMICO");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 20, 500, 28);
		getContentPane().add(lblTitulo);

		JSeparator sep = new JSeparator();
		sep.setBounds(50, 55, 460, 2);
		sep.setForeground(BORDER);
		getContentPane().add(sep);

		int y = 70;
		for (int i = 0; i < nombres.length; i++) {

			JPanel fila = new JPanel(null);
			fila.setBounds(50, y, 460, 38);
			fila.setBackground(CARD);
			fila.setBorder(BorderFactory.createLineBorder(BORDER));
			getContentPane().add(fila);

			JLabel lblNombre = new JLabel(nombres[i]);
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
			lblNombre.setBounds(12, 10, 180, 18);
			fila.add(lblNombre);

			JLabel lblMonto = new JLabel(String.format("$ %,.2f", impactos[i]));
			lblMonto.setForeground(COLORES_BARRA[i % COLORES_BARRA.length]);
			lblMonto.setFont(new Font("SansSerif", Font.BOLD, 14));
			lblMonto.setBounds(220, 10, 220, 18);
			fila.add(lblMonto);

			y += 46;
		}

		y += 8;
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(50, y, 460, 2);
		sep2.setForeground(BORDER);
		getContentPane().add(sep2);
		y += 10;

		JLabel lblBalTxt = new JLabel("BALANCE NETO TOTAL");
		lblBalTxt.setForeground(MUTED);
		lblBalTxt.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblBalTxt.setBounds(50, y, 200, 20);
		getContentPane().add(lblBalTxt);

		Color colorBalance = balanceTotal >= 0 ? new Color(39, 174, 96) : new Color(192, 57, 43);
		JLabel lblBalMonto = new JLabel(String.format("$ %,.2f", balanceTotal));
		lblBalMonto.setForeground(colorBalance);
		lblBalMonto.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblBalMonto.setBounds(50, y + 24, 400, 36);
		getContentPane().add(lblBalMonto);
		y += 70;

		JPanel panelGrafico = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dibujarBarras(g);
			}
		};
		panelGrafico.setBounds(50, y, 460, 190);
		panelGrafico.setBackground(CARD);
		panelGrafico.setBorder(BorderFactory.createLineBorder(BORDER));
		getContentPane().add(panelGrafico);
		y += 200;

		btnVolver = new JButton("VOLVER AL MENU");
		btnVolver.setBounds(180, y + 10, 200, 40);
		btnVolver.setBackground(C_BACK);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnVolver);

		setSize(560, y + 80);
	}

	private void dibujarBarras(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double max = 0;
		for (double v : impactos)
			if (Math.abs(v) > max)
				max = Math.abs(v);
		if (max == 0)
			max = 1;

		int panelW = 460;
		int base = 155;
		int barW = 60;
		int gap = (panelW - nombres.length * barW) / (nombres.length + 1);

		int x = gap;
		for (int i = 0; i < impactos.length; i++) {
			int altura = (int) (Math.abs(impactos[i]) / max * 120);

			g2.setColor(COLORES_BARRA[i % COLORES_BARRA.length].darker());
			g2.fillRoundRect(x, base - altura, barW, altura, 6, 6);

			g2.setColor(COLORES_BARRA[i % COLORES_BARRA.length]);
			g2.fillRoundRect(x + 3, base - altura, barW - 6, altura - 3, 6, 6);

			g2.setColor(new Color(180, 180, 210));
			g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
			g2.drawString(nombres[i], x + 4, base + 18);

			x += barW + gap;
		}

		g2.setColor(BORDER);
		g2.drawLine(10, base, 450, base);
	}

	public void addBtnVolverListener(ActionListener l) {
		btnVolver.addActionListener(l);
	}
}
