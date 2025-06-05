package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.EquipoDentalLuisZCH;
import model.dentistaLuisZCH;
import utils.JPAUtil;

import javax.swing.JTextArea;
import java.awt.Font;

public class DlgEquipoDental extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNroEquipo;
	private JLabel lblNombreEquipo;
	private JLabel lblCosto;
	private JLabel lblEstado;
	private JLabel lblFechaAdquisicion;
	private JLabel lblDentista;
	private JTextField txtNroEquipo;
	private JTextField txtNombre;
	private JTextField txtCosto;
	private JComboBox<String> cboEstados;
	private JTextField txtFechaAdquisicion;
	private JComboBox<Object> cboDentistas;
	private JButton btnBuscar;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnListar;
	private JScrollPane scrollPane;
	private JTextArea txtSalida;

	private int tipoOperacion;

	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DlgEquipoDental dialog = new DlgEquipoDental();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public DlgEquipoDental() {
		setResizable(false);
		setTitle("Mantenimiento | Equipo Dental");
		setBounds(100, 100, 810, 604);
		getContentPane().setLayout(null);

		lblNroEquipo = new JLabel("Nro Equipo:");
		lblNroEquipo.setBounds(10, 10, 149, 23);
		getContentPane().add(lblNroEquipo);

		lblNombreEquipo = new JLabel("Nombre :");
		lblNombreEquipo.setBounds(10, 35, 149, 23);
		getContentPane().add(lblNombreEquipo);

		lblDentista = new JLabel("Dentista :");
		lblDentista.setBounds(10, 145, 149, 23);
		getContentPane().add(lblDentista);

		lblEstado = new JLabel("Estado :");
		lblEstado.setBounds(10, 88, 149, 23);
		getContentPane().add(lblEstado);

		txtNroEquipo = new JTextField();
		txtNroEquipo.setBounds(174, 10, 86, 23);
		getContentPane().add(txtNroEquipo);
		txtNroEquipo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(169, 35, 251, 23);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		lblCosto = new JLabel("Costo :");
		lblCosto.setBounds(10, 62, 149, 23);
		getContentPane().add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(174, 62, 86, 23);
		getContentPane().add(txtCosto);

		String[] estados = { "N", "A", "R", "S" };
		cboEstados = new JComboBox<String>();
		cboEstados.setEditable(true);
		cboEstados.setBounds(174, 88, 86, 23);
		getContentPane().add(cboEstados);
		for (String estado : estados) {
			cboEstados.addItem(estado);
		}

		lblFechaAdquisicion = new JLabel("Fecha de adquisición:");
		lblFechaAdquisicion.setBounds(10, 116, 162, 20);
		getContentPane().add(lblFechaAdquisicion);

		txtFechaAdquisicion = new JTextField();
		txtFechaAdquisicion.setBounds(174, 114, 146, 26);
		getContentPane().add(txtFechaAdquisicion);
		txtFechaAdquisicion.setColumns(10);

		cboDentistas = new JComboBox<Object>();
		cboDentistas.setEditable(true);
		cboDentistas.setBounds(174, 143, 251, 26);
		getContentPane().add(cboDentistas);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(324, 10, 101, 23);
		getContentPane().add(btnBuscar);

		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(430, 145, 100, 23);
		getContentPane().add(btnOK);

		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(555, 10, 100, 75);
		getContentPane().add(btnOpciones);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(664, 10, 120, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(664, 36, 120, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(664, 62, 120, 23);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 775, 337);
		getContentPane().add(scrollPane);

		txtSalida = new JTextArea();
		txtSalida.setEditable(false);
		txtSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scrollPane.setViewportView(txtSalida);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(345, 525, 115, 29);
		getContentPane().add(btnListar);

		habilitarEntradas(false);
		habilitarBotones(true);
		cargarDentistas();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnListar) {
			actionPerformedBtnListar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		buscar();
	}

	protected void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
		case ADICIONAR:
			adicionar();
			break;
		case MODIFICAR:
			modificar();
			break;
		case ELIMINAR:
			eliminar();
		}
	}

	protected void actionPerformedBtnOpciones(ActionEvent arg0) {
		limpiar();
	}

	protected void actionPerformedBtnListar(ActionEvent arg0) {
		listar();
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		habilitarEntradas(true);
		habilitarBotones(false);
		txtNroEquipo.setEditable(false);
		txtNombre.requestFocus();
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		habilitarEntradas(false);
		habilitarBotones(false);
		txtNroEquipo.setEditable(true);
		txtNroEquipo.requestFocus();
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipoOperacion = ELIMINAR;
		habilitarEntradas(false);
		habilitarBotones(false);
		txtNroEquipo.setEditable(true);
		txtNroEquipo.requestFocus();
	}

	void cargarDentistas() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			List<dentistaLuisZCH> dentistas = em.createQuery("SELECT d FROM dentistaLuisZCH d", dentistaLuisZCH.class)
					.getResultList();
			cboDentistas.removeAllItems();
			for (dentistaLuisZCH d : dentistas) {
				cboDentistas.addItem(d);
			}
		} catch (Exception e) {
			mensajeError("Error cargando dentistas: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	private void listar() {
	    EntityManager manager = JPAUtil.getEntityManager();
	    String jpql = "select d, e.descripcion from Dentista d join d.especialidad e order by d.idDentista";

	    try {
	        List<Object[]> lista = manager.createQuery(jpql, Object[].class).getResultList();

	        for (Object[] fila : lista) {
	        	dentistaLuisZCH d = (dentistaLuisZCH) fila[0];
	            String especialidad = (String) fila[1];
	            System.out.println("ID: " + d.getIdDentista() + ", Nombre: " + d.getNombreCompleto() + ", Especialidad: " + especialidad);
	        }
	    } finally {
	        manager.close();
	    }
	}



	void adicionar() {
		try {
			String nombre = txtNombre.getText();
			double costoDouble = Double.parseDouble(txtCosto.getText());
			BigDecimal costo = BigDecimal.valueOf(costoDouble);
			String estado = (String) cboEstados.getSelectedItem();
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(txtFechaAdquisicion.getText());
			dentistaLuisZCH dentista = (dentistaLuisZCH) cboDentistas.getSelectedItem();

			EquipoDentalLuisZCH equipo = new EquipoDentalLuisZCH(null, nombre, costo, fecha, estado, dentista);

			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(equipo);
			em.getTransaction().commit();
			em.close();

			mensajeInfo("Equipo dental registrado correctamente.");
			limpiar();

		} catch (Exception e) {
			mensajeError("Error al registrar: " + e.getMessage());
		}
	}

	void buscar() {
	    try {
	        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del equipo dental a buscar"));
	        System.out.println("Buscar equipo con ID: " + id);

	        EntityManager em = JPAUtil.getEntityManager();
	        EquipoDentalLuisZCH equipo = em.find(EquipoDentalLuisZCH.class, id);
	        System.out.println("Equipo encontrado? " + (equipo != null));
	        em.close();

	        if (equipo != null) {
	            txtNroEquipo.setText(equipo.getNroEquipo() != null ? equipo.getNroEquipo().toString() : "");
	            txtNombre.setText(equipo.getNombre());
	            txtCosto.setText(String.valueOf(equipo.getCosto()));
	            cboEstados.setSelectedItem(equipo.getEstado());
	            txtFechaAdquisicion.setText(new SimpleDateFormat("yyyy-MM-dd").format(equipo.getFechaAdquisicion()));
	            cboDentistas.setSelectedItem(equipo.getDentista());
	            habilitarOk();
	        } else {
	            mensajeInfo("Equipo dental no encontrado");
	            limpiar();
	        }
	    } catch (Exception e) {
	        mensajeError("Error al buscar: " + e.getMessage());
	    }
	}

	void modificar() {
		try {
			int id = Integer.parseInt(txtNroEquipo.getText());

			EntityManager em = JPAUtil.getEntityManager();
			EquipoDentalLuisZCH equipo = em.find(EquipoDentalLuisZCH.class, id);

			if (equipo != null) {
				em.getTransaction().begin();

				equipo.setNombre(txtNombre.getText());
				equipo.setCosto(BigDecimal.valueOf(Double.parseDouble(txtCosto.getText())));
				equipo.setEstado((String) cboEstados.getSelectedItem());
				equipo.setFechaAdquisicion(new SimpleDateFormat("yyyy-MM-dd").parse(txtFechaAdquisicion.getText()));
				equipo.setDentista((dentistaLuisZCH) cboDentistas.getSelectedItem());

				em.merge(equipo);
				em.getTransaction().commit();
				mensajeInfo("Equipo dental actualizado correctamente.");
				limpiar();
			} else {
				mensajeInfo("Equipo dental no encontrado");
			}
			em.close();
		} catch (Exception e) {
			mensajeError("Error al modificar: " + e.getMessage());
		}
	}

	void eliminar() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del equipo dental a eliminar"));

			EntityManager em = JPAUtil.getEntityManager();
			EquipoDentalLuisZCH equipo = em.find(EquipoDentalLuisZCH.class, id);

			if (equipo != null) {
				em.getTransaction().begin();
				em.remove(equipo);
				em.getTransaction().commit();
				mensajeInfo("Equipo dental eliminado correctamente.");
				limpiar();
			} else {
				mensajeInfo("Equipo dental no encontrado");
			}
			em.close();
		} catch (Exception e) {
			mensajeError("Error al eliminar: " + e.getMessage());
		}
	}

	void limpiar() {
		txtNroEquipo.setText("");
		txtNombre.setText("");
		txtCosto.setText("");
		txtFechaAdquisicion.setText("");
		cboEstados.setSelectedIndex(0);
		cboDentistas.setSelectedIndex(0);
		habilitarEntradas(false);
		habilitarBotones(true);
		txtSalida.setText("");
	}

	void habilitarEntradas(boolean estado) {
	}

	void habilitarBotones(boolean estado) {
		btnAdicionar.setEnabled(estado);
		btnModificar.setEnabled(estado);
		btnEliminar.setEnabled(estado);
		btnListar.setEnabled(estado);
		btnOpciones.setEnabled(!estado);
	}

	void habilitarOk() {
		btnOK.setEnabled(true);
	}

	void mensajeInfo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}

	void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
