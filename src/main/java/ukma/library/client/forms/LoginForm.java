package ukma.library.client.forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame{

	JPanel panel;
	JTextField loginField;
	JPasswordField passwordField;
	JComboBox role;
	JButton ok;
	final String[] roles = {"Бібліотекар", "Читач"};
	
	public LoginForm(){
		
		this.setSize(300, 150);
		this.setLocation(200,200);
		setTitle("Аутентифікація");
		
		panel = new JPanel(new GridLayout(4, 2));
		loginField = new JTextField(10);
		passwordField = new JPasswordField(10);
		role = new JComboBox(roles);
		ok = new JButton("OK");
		
		panel.add(new JLabel("Введіть логін: "));
		panel.add(loginField);
		panel.add(new JLabel("Введіть пароль: "));
		panel.add(passwordField);
		
		panel.add(new JLabel("Виберіть роль: "));
		panel.add(role);
		this.add(panel);
		this.add(ok, BorderLayout.PAGE_END);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JButton getButton(){
		return this.ok;
	}
	public JPasswordField getPasswordField(){
		return this.passwordField;
	}
	public JTextField getLoginField(){
		return this.loginField;
	}
	public JComboBox getRole(){
		return this.role;
	}
	
}
