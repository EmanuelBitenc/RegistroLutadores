package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.LutadorDAO;
import model.Lutador;

public class ViewLutador extends JFrame implements WindowListener,
                                ActionListener, FocusListener{

	private JTextField txtNome, txtPeso, txtAltura; // caixa de entrada do tipo texto
	private JLabel lblNome, lblPeso, lblAltura, lblInfo; // um rótulo (texto)
	private JButton btnAdicionar,btnLimpar; // criar botão
	private int id;
	
	public ViewLutador() {
		super("Cadastro de Lutador");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(400,400); // seta o tamanho da janela pixel
		//setLocation(10,10); // seta localização da janela no monitor
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // fechar a janela.
		
		addWindowListener(this); // adicionando a escuta da janela a classe
		
		addElements(null);
		
		setVisible(true); // tornar a janela visivel pois o padrão é invisivel
	}
	
	public ViewLutador(Lutador lutador) {
		super("Cadastro de Lutador");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(400,400); // seta o tamanho da janela pixel
		//setLocation(10,10); // seta localização da janela no monitor
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // fechar a janela.
		
		addWindowListener(this); // adicionando a escuta da janela a classe
		
		addElements(lutador);
		
		setVisible(true); // tornar a janela visivel pois o padrão é invisivel
		
	}
	
	
	public void addElements(Lutador lutador) {
		if (lutador==null) {
		    txtNome = new JTextField(30);
		    txtPeso = new JTextField(30);
		    txtAltura = new JTextField(30);
		    btnAdicionar = new JButton("Adicionar");
		}else{
			txtNome = new JTextField(lutador.getNome(),30);
			txtPeso = new JTextField(""+lutador.getPeso(),30);
			txtAltura = new JTextField(""+lutador.getAltura(),30);
			btnAdicionar = new JButton("Atualizar");
			id = lutador.getId();
		}
		lblNome = new JLabel("Nome do Lutador");
		txtPeso.addFocusListener(this);
		lblPeso = new JLabel("Peso do Lutador");
		lblAltura = new JLabel("Altura do Lutador");
		
		btnAdicionar.addActionListener(this);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				limparDados();				
			}
		});
		
		lblInfo = new JLabel("");
		
		add(lblNome);add(txtNome);
		add(lblPeso);add(txtPeso);
		add(lblAltura);add(txtAltura);
		add(btnAdicionar);add(btnLimpar);
		add(lblInfo);
	}
	
	public void limparDados() {
		txtNome.setText(null);
		txtPeso.setText(null);
		txtAltura.setText(null);
		txtNome.requestFocus(); // posiciona o foco do teclado na caixa Nome
	}
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		JOptionPane.showMessageDialog(null, "Sou um icone na barra de tarefa");
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		JOptionPane.showMessageDialog(null, "Não Sou um icone na barra de tarefa");
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Lutador lutador = new Lutador();
		if (btnAdicionar == e.getSource()) {
			//JOptionPane.showMessageDialog(null, "Rotina em manutenção");
			
			double imc=0, peso=0, altura=0;
			
			peso = Double.parseDouble(txtPeso.getText());
			altura = Double.parseDouble(txtAltura.getText());
			
			imc = peso / Math.pow(altura, 2);
			
			lblInfo.setText("IMC: "+Math.round(imc));
			lutador.setId(id);
			lutador.setNome(txtNome.getText());
			lutador.setPeso(Double.parseDouble(txtPeso.getText()));
			lutador.setAltura(Double.parseDouble(txtAltura.getText()));
			
			try {
				LutadorDAO dao = new LutadorDAO(); // responsável pelo crud
				if (btnAdicionar.getText().equals("Adicionar")) {
				    dao.insert(lutador);
				    JOptionPane.showMessageDialog(null, "Adicionado com sucesso!!!");
				}else {
					dao.update(lutador);
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
					this.dispose();
					new ViewListagemLutador();
				}
				
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null,"Error no driver");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Erro no SQL");
			    e1.printStackTrace();
			} 
			
			
			
			
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		System.out.println("A caixinha do peso ganhou o foco");
		lblInfo.setText("Digite apenas números");
	    txtPeso.setBackground(Color.BLUE);
	    txtPeso.setForeground(Color.WHITE);
	}

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("Peso perdeu o foco");
	    lblInfo.setText("");	
	    txtPeso.setBackground(Color.WHITE);
	    txtPeso.setForeground(Color.BLACK);
	}

}
