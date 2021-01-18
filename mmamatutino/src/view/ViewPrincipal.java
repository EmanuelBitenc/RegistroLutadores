package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class ViewPrincipal extends JFrame implements ActionListener{
	private JMenuBar barMenu;
	private JMenu menuCadastro, menuConsulta, menuAjuda;
	private JMenuItem menuLutador, menuListagemLutador, menuSobre;
		
	public ViewPrincipal() {
		super("MMA");
		setSize(500,500);
		setLocationRelativeTo(null); // centralizar a janela
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("image/imagem.jpg").getImage());
		setExtendedState(MAXIMIZED_BOTH);
		initComponents();
		setVisible(true);
	}

	public void initComponents() {
		barMenu = new JMenuBar();
		menuCadastro = new JMenu("Cadastro");
		menuConsulta = new JMenu("Consulta");
		menuAjuda = new JMenu("Ajuda");
		menuLutador = new JMenuItem("Manter Lutador");
		menuLutador.addActionListener(this);
		menuListagemLutador = new JMenuItem("Listagem de Lutador");
		menuListagemLutador.addActionListener(this);
		menuSobre = new JMenuItem("Sobre");
		menuSobre.addActionListener(this);
		barMenu.add(menuCadastro);
		barMenu.add(menuConsulta);
		barMenu.add(menuAjuda);
		menuCadastro.add(menuLutador);
		menuConsulta.add(menuListagemLutador);
		menuAjuda.add(menuSobre);
		setJMenuBar(barMenu);
	}
	
	
	public static void main(String[] args) {
        new ViewPrincipal();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(menuLutador == e.getSource()) {
			new ViewLutador();
		}else if(menuSobre==e.getSource()) {
			new ViewSobre();
		}else if(menuListagemLutador==e.getSource()) {
			try {
				new ViewListagemLutador();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
