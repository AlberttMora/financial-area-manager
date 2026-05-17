package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class VentanaModificarElemento extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_MOD = new Color(41, 128, 185);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JLabel lblId, lblNombre, lblMonto, lblGananciaNeta, lblRetorno, lblAnios;
	private JTextField txtIdBusqueda, txtNuevoNombre, txtNuevoMonto, txtNuevaGananciaNeta, txtNuevoRetorno,
			txtNuevosAnios;

	private JComboBox<String> comboArea, comboPersistencia;
	private JButton btnActualizar, btnCancelar;
	private String tipoArea;

	public VentanaModificarElemento(String tipoArea) {
		this.tipoArea = tipoArea;
		setTitle("Modificar elemento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("MODIFICAR ELEMENTO");
		lblTitulo.setForeground(CYAN);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(30, 20, 340, 28);
		getContentPane().add(lblTitulo);

		crearLabelEstatico("Selecciona el area financiera:", 60);
		comboArea = new JComboBox<>(new String[] { "Inversiones", "Marketing", "Infraestructura" });
		comboArea.setBounds(40, 84, 320, 32);
		estilizarCombo(comboArea);
		comboArea.setSelectedItem(tipoArea);
		getContentPane().add(comboArea);

		comboArea.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				this.tipoArea = comboArea.getSelectedItem().toString();
				actualizarCampos();
			}
		});

		JLabel lblIdHint = new JLabel("ID del elemento  (ej: INV-xxxx, MARK-xxxx, INF-xxxx)");
		lblIdHint.setForeground(MUTED);
		lblIdHint.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblIdHint.setBounds(40, 125, 320, 16);
		getContentPane().add(lblIdHint);

		crearLabelEstatico("ID a modificar:", 143);
		txtIdBusqueda = crearField(167);

		actualizarCampos();

		setLocationRelativeTo(null);
	}

	private void actualizarCampos() {
		for (JLabel lbl : new JLabel[] { lblNombre, lblMonto, lblGananciaNeta, lblRetorno, lblAnios })
			if (lbl != null)
				getContentPane().remove(lbl);
		for (JTextField tf : new JTextField[] { txtNuevoNombre, txtNuevoMonto, txtNuevaGananciaNeta, txtNuevoRetorno,
				txtNuevosAnios })
			if (tf != null)
				getContentPane().remove(tf);
		if (comboPersistencia != null)
			getContentPane().remove(comboPersistencia);
		if (btnActualizar != null)
			getContentPane().remove(btnActualizar);
		if (btnCancelar != null)
			getContentPane().remove(btnCancelar);

		lblNombre = lblMonto = lblGananciaNeta = lblRetorno = lblAnios = null;
		txtNuevoNombre = txtNuevoMonto = txtNuevaGananciaNeta = txtNuevoRetorno = txtNuevosAnios = null;

		int y = 230;

		switch (tipoArea) {
		case "Inversiones":
			lblMonto = crearLabel("Nuevo monto de inversion:", y);
			txtNuevoMonto = crearField(y + 24);
			y += 70;

			lblGananciaNeta = crearLabel("Nueva ganancia neta:", y);
			txtNuevaGananciaNeta = crearField(y + 24);
			y += 70;

			lblAnios = crearLabel("Nuevos anos estimados de recuperacion:", y);
			txtNuevosAnios = crearField(y + 24);
			y += 70;
			break;

		case "Marketing":
			lblNombre = crearLabel("Nuevo nombre de la estrategia:", y);
			txtNuevoNombre = crearField(y + 24);
			y += 70;

			lblMonto = crearLabel("Nuevo costo:", y);
			txtNuevoMonto = crearField(y + 24);
			y += 70;

			lblRetorno = crearLabel("Nuevo retorno generado:", y);
			txtNuevoRetorno = crearField(y + 24);
			y += 70;
			break;

		case "Infraestructura":
			lblNombre = crearLabel("Nuevo nombre / descripcion:", y);
			txtNuevoNombre = crearField(y + 24);
			y += 70;

			lblMonto = crearLabel("Nuevo costo:", y);
			txtNuevoMonto = crearField(y + 24);
			y += 70;
			break;
		}

		crearLabelEstatico("Actualizar en:", y);
		comboPersistencia = new JComboBox<>(new String[] { "ARCHIVO", "BD", "AMBOS" });
		comboPersistencia.setBounds(40, y + 22, 320, 32);
		estilizarCombo(comboPersistencia);
		getContentPane().add(comboPersistencia);
		y += 66;

		btnActualizar = crearBoton("ACTUALIZAR", C_MOD, 40, y, 148);
		btnCancelar = crearBoton("CANCELAR", C_BACK, 200, y, 148);
		getContentPane().add(btnActualizar);
		getContentPane().add(btnCancelar);
		y += 60;

		setSize(400, y + 10);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	private JLabel crearLabel(String texto, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setForeground(MUTED);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setBounds(40, y, 320, 18);
		getContentPane().add(lbl);
		return lbl;
	}

	private void crearLabelEstatico(String texto, int y) {
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

	public String getTipoArea() {
		return tipoArea;
	}

	public String getTxtIdBusqueda() {
		return txtIdBusqueda != null ? txtIdBusqueda.getText().trim() : "";
	}

	public String getTxtNuevoNombre() {
		return txtNuevoNombre != null ? txtNuevoNombre.getText().trim() : "";
	}

	public String getTxtNuevoMonto() {
		return txtNuevoMonto != null ? txtNuevoMonto.getText().trim() : "0";
	}

	public String getTxtNuevaGananciaNeta() {
		return txtNuevaGananciaNeta != null ? txtNuevaGananciaNeta.getText().trim() : "0";
	}

	public String getTxtNuevoRetorno() {
		return txtNuevoRetorno != null ? txtNuevoRetorno.getText().trim() : "0";
	}

	public String getTxtNuevosAnios() {
		return txtNuevosAnios != null ? txtNuevosAnios.getText().trim() : "1";
	}

	public String getTipoPersistencia() {
		return comboPersistencia != null ? (String) comboPersistencia.getSelectedItem() : "ARCHIVO";
	}

	public void limpiarCampos() {
		if (txtIdBusqueda != null)
			txtIdBusqueda.setText("");
		if (txtNuevoNombre != null)
			txtNuevoNombre.setText("");
		if (txtNuevoMonto != null)
			txtNuevoMonto.setText("");
		if (txtNuevaGananciaNeta != null)
			txtNuevaGananciaNeta.setText("");
		if (txtNuevoRetorno != null)
			txtNuevoRetorno.setText("");
		if (txtNuevosAnios != null)
			txtNuevosAnios.setText("");
	}

	public void addBtnActualizarListener(ActionListener l) {
		btnActualizar.addActionListener(l);
	}

	public void addBtnCancelarListener(ActionListener l) {
		btnCancelar.addActionListener(l);
	}
}
