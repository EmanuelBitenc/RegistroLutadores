package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewSobre extends JFrame {
	private JLabel lblImage;

	public ViewSobre() {
		super("Sobre");
		setSize(300,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("image/imagem.jpg").getImage());
		initComponents();
		setVisible(true);
	}
	
	public void initComponents() {
		lblImage = new JLabel(new ImageIcon("image/imagem.jpg"));
		add(lblImage);
	}

}
