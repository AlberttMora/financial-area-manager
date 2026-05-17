package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class VentanaRegistrarElemento extends JFrame {

	private static final Color BG = new Color(18, 18, 28);
	private static final Color CYAN = new Color(0, 210, 220);
	private static final Color MUTED = new Color(120, 120, 150);
	private static final Color FIELD = new Color(36, 36, 54);
	private static final Color BORDER = new Color(60, 60, 90);
	private static final Color C_OK = new Color(39, 174, 96);
	private static final Color C_BACK = new Color(44, 44, 60);

	private JLabel lblNombre, lblMonto, lblGananciaNeta, lblRetorno, lblAnios;
	private JTextField txtNombre, txtMonto, txtGananciaNeta, txtRetorno, txtAnios, txtPresupuesto;
	private JComboBox<String> comboArea, comboPersistencia;
	private JButton btnGuardar, btnCancelar;
	private String tipoArea;

	public VentanaRegistrarElemento(String tipoArea) {
		this.tipoArea = tipoArea;
		setTitle("Registrar elemento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BG);

		JLabel lblTitulo = new JLabel("REGISTRAR ELEMENTO");
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

		actualizarCampos();

		crearLabelEstatico("Guardar en:", 320);
		comboPersistencia = new JComboBox<>(new String[] { "ARCHIVO", "BD", "AMBOS" });
		comboPersistencia.setBounds(40, 344, 320, 32);
		estilizarCombo(comboPersistencia);
		getContentPane().add(comboPersistencia);

		btnGuardar = crearBoton("REGISTRAR", C_OK, 40, 390, 148);
		btnCancelar = crearBoton("CANCELAR", C_BACK, 200, 390, 148);
		getContentPane().add(btnGuardar);
		getContentPane().add(btnCancelar);

		setSize(400, 500);
		setLocationRelativeTo(null);
	}

	private void actualizarCampos() {
		if (lblNombre != null)
			getContentPane().remove(lblNombre);
		if (lblMonto != null)
			getContentPane().remove(lblMonto);
		if (lblGananciaNeta != null)
			getContentPane().remove(lblGananciaNeta);
		if (lblRetorno != null)
			getContentPane().remove(lblRetorno);
		if (lblAnios != null)
			getContentPane().remove(lblAnios);

		if (txtNombre != null)
			getContentPane().remove(txtNombre);
		if (txtMonto != null)
			getContentPane().remove(txtMonto);
		if (txtGananciaNeta != null)
			getContentPane().remove(txtGananciaNeta);
		if (txtRetorno != null)
			getContentPane().remove(txtRetorno);
		if (txtAnios != null)
			getContentPane().remove(txtAnios);

		int y = 140;

		switch (tipoArea) {
		case "Inversiones":
			lblMonto = crearLabel("Monto de inversion:", y);
			txtMonto = crearField(y + 24);
			y += 70;

			lblGananciaNeta = crearLabel("Ganancia neta esperada:", y);
			txtGananciaNeta = crearField(y + 24);
			y += 70;

			lblAnios = crearLabel("Anios estimados de recuperacion:", y);
			txtAnios = crearField(y + 24);

			break;

		case "Marketing":
			lblNombre = crearLabel("Nombre de la Estrategia:", y);
			txtNombre = crearField(y + 24);
			y += 70;

			lblMonto = crearLabel("Costo:", y);
			txtMonto = crearField(y + 24);
			y += 70;

			lblRetorno = crearLabel("Retorno Generado Estimado:", y);
			txtRetorno = crearField(y + 24);

			break;

		case "Infraestructura":
			lblNombre = crearLabel("Nombre / Descripcion:", y);
			txtNombre = crearField(y + 24);
			y += 70;

			lblMonto = crearLabel("Costo:", y);
			txtMonto = crearField(y + 24);
			break;
		}

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

	public String getTxtNombre() {
		return txtNombre != null ? txtNombre.getText().trim() : "";
	}

	public String getTxtMonto() {
		return txtMonto != null ? txtMonto.getText().trim() : "";
	}

	public String getTxtGananciaNeta() {
		return txtGananciaNeta != null ? txtGananciaNeta.getText().trim() : "0";
	}

	public String getTxtRetorno() {
		return txtRetorno != null ? txtRetorno.getText().trim() : "0";
	}

	public String getTxtAnios() {
		return txtAnios != null ? txtAnios.getText().trim() : "1";
	}

	public int getTipoPersistencia() {
		return comboPersistencia.getSelectedIndex();
	}

	public String getAreaSeleccionada() {
		return comboArea.getSelectedItem() != null ? comboArea.getSelectedItem().toString() : tipoArea;
	}

	public void addBtnGuardarListener(ActionListener l) {
		btnGuardar.addActionListener(l);
	}

	public void addBtnCancelarListener(ActionListener l) {
		btnCancelar.addActionListener(l);
	}

	public void limpiarCampos() {
		if (txtNombre != null)
			txtNombre.setText("");
		if (txtMonto != null)
			txtMonto.setText("");
		if (txtGananciaNeta != null)
			txtGananciaNeta.setText("");
		if (txtRetorno != null)
			txtRetorno.setText("");
		if (txtAnios != null)
			txtAnios.setText("");
	}
}