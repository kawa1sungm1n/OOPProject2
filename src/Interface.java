import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Interface extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public Interface() {
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Fermi Guessing Game");
		lblTitle.setBounds(12, 21, 159, 15);
		getContentPane().add(lblTitle);
		
		JLabel lbl = new JLabel("Enter your three guesses (0-9):");
		lbl.setBounds(12, 46, 184, 15);
		getContentPane().add(lbl);
		
		textField = new JTextField();
		textField.setBounds(12, 71, 60, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 102, 60, 21);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 133, 60, 21);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(12, 164, 60, 37);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(12, 318, 105, 42);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Hints");
		lblNewLabel.setBounds(231, 46, 57, 15);
		getContentPane().add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(231, 71, 219, 289);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

	}
}
