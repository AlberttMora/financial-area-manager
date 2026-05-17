package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.EstrategiaMarketing;
import model.Infraestructura;
import model.Inversion;
import model.ListaElementos;

public class VentanaVerElementos extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CARD = new Color(28, 28, 42);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JButton btnVolver;

	public VentanaVerElementos(ListaElementos<Inversion> listaElementos, ListaElementos<EstrategiaMarketing> listaElementos2,
			ListaElementos<Infraestructura> listaElementos3) {
		setTitle("IDs y elementos");
		setSize(820, 560);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("IDS Y ELEMENTOS REGISTRADOS");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 18, 760, 28);
		getContentPane().add(lblTitulo);

		JSeparator sep = new JSeparator();
		sep.setBounds(30, 72, 760, 2);
		sep.setForeground(BORDER);
		getContentPane().add(sep);

		JTabbedPane tabs = new JTabbedPane();
		tabs.setBounds(30, 92, 760, 360);
		tabs.setBackground(CARD);
		tabs.setForeground(Color.WHITE);
		tabs.setFont(new Font("SansSerif", Font.BOLD, 12));

		tabs.addTab("Inversiones", crearPanelInversiones(listaElementos));
		tabs.addTab("Marketing", crearPanelMarketing(listaElementos2));
		tabs.addTab("Infraestructura", crearPanelInfra(listaElementos3));
		getContentPane().add(tabs);

		btnVolver = new JButton("VOLVER AL MENU");
		btnVolver.setBounds(310, 468, 200, 40);
		btnVolver.setBackground(C_BACK);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnVolver);
	}

	private JPanel crearPanelInversiones(ListaElementos<Inversion> listaElementos) {
		String[] cols = { "ID", "Ganancia neta", "Monto inversion", "Fecha recup.", "Años retraso", "% Interes" };
		DefaultTableModel model = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Inversion inv : listaElementos) {
			model.addRow(new Object[] { inv.getId(), inv.getGananciaNeta(), inv.getMontoInversion(),
					inv.getFechaEstimadaRecuperacion(), inv.getCantAniosRetrasados(), inv.getPorcentajeInteres() });
		}

		return crearPanelTabla(model);
	}

	private JPanel crearPanelMarketing(ListaElementos<EstrategiaMarketing> listaElementos2) {
		String[] cols = { "ID", "Nombre", "Costo", "Retorno", "Inicio", "Fin" };
		DefaultTableModel model = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (EstrategiaMarketing est : listaElementos2) {
			model.addRow(new Object[] { est.getId(), est.getNombre(), est.getCosto(), est.getRetornoGenerado(),
					est.getFechaInicio(), est.getFechaFin() });
		}

		return crearPanelTabla(model);
	}

	private JPanel crearPanelInfra(ListaElementos<Infraestructura> listaElementos3) {
		String[] cols = { "ID", "Nombre / Descripcion", "Costo" };
		DefaultTableModel model = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Infraestructura inf : listaElementos3) {
			model.addRow(new Object[] { inf.getId(), inf.getNombre(), inf.getCosto() });
		}

		return crearPanelTabla(model);
	}

	private JPanel crearPanelTabla(DefaultTableModel model) {
		JPanel panel = new JPanel(null);
		panel.setBackground(BG);

		JTable table = new JTable(model);
		table.setBackground(FIELD);
		table.setForeground(Color.WHITE);
		table.setGridColor(BORDER);
		table.setRowHeight(26);
		table.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table.setSelectionBackground(new Color(0, 210, 220, 60));
		table.setSelectionForeground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTableHeader header = table.getTableHeader();
		header.setBackground(CARD);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("SansSerif", Font.BOLD, 12));

		for (int i = 0; i < table.getColumnCount(); i++) {
			int w = 160;
			if (i == 0)
				w = 260;
			if (table.getColumnCount() == 3 && i == 1)
				w = 360;
			table.getColumnModel().getColumn(i).setPreferredWidth(w);
		}

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 0, 760, 330);
		sp.getViewport().setBackground(BG);
		sp.setBorder(BorderFactory.createLineBorder(BORDER));
		panel.add(sp);

		return panel;
	}

	public void addBtnVolverListener(ActionListener l) {
		btnVolver.addActionListener(l);
	}
}
