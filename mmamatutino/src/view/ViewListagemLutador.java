package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import controller.ControllerLutador;
import dao.LutadorDAO;
import model.Lutador;

public class ViewListagemLutador extends JFrame implements ActionListener {
    private JTable tabelaLutador;	
    private JButton btnExcluir, btnAlterar;
    private JPanel panelRodape;
    
	public ViewListagemLutador() throws ClassNotFoundException, SQLException {
		super("Listagem de Lutador");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initComponents();
		
		setVisible(true);
	}
	
	public void initComponents() throws ClassNotFoundException, SQLException {
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		panelRodape = new JPanel();
		
		panelRodape.add(btnExcluir);panelRodape.add(btnAlterar);
		tabelaLutador = new JTable(new LutadorDAO().selectAll());
		add(tabelaLutador);
		add(panelRodape,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ControllerLutador controllerLutador =
		new ControllerLutador();
		if (btnAlterar==e.getSource()) {
		controllerLutador.alterar(
		Integer.parseInt(
		tabelaLutador.getValueAt(
		tabelaLutador.getSelectedRow(), 0).toString()),
		tabelaLutador.getValueAt(
		tabelaLutador.getSelectedRow(),1).toString(),
		Double.parseDouble(
		tabelaLutador.getValueAt(
		tabelaLutador.getSelectedRow(),2).toString()),
		Double.parseDouble(
		tabelaLutador.getValueAt(
		tabelaLutador.getSelectedRow(),3).toString())
		);
		} else if (btnExcluir == e.getSource()) {
		String[] options = { "Sim", "Não" };
		int resposta = JOptionPane.showOptionDialog(
		null, "Deseja Excluir?", "Excluir",
		JOptionPane.DEFAULT_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null, options, options[1]);
		if (resposta == 0) {
		if (controllerLutador.excluir(
		Integer.parseInt(tabelaLutador.getValueAt(
		tabelaLutador.getSelectedRow(), 0).toString()))) {
		JOptionPane.showMessageDialog(null, "Excluído!!");
		try {
		tabelaLutador.setModel(new LutadorDAO().selectAll());
		} catch (ClassNotFoundException | SQLException e1) {
		JOptionPane.showMessageDialog(null, "Não Excluído!!");
		}
		}
		}
		}
	}}
