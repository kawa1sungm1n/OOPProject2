import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;


public class MainFrame extends JFrame implements ActionListener {
	
    private final int FRAME_WIDTH = 480;
    private final int FRAME_HEIGHT = 420;
    private final int FRAME_X = 700;
    private final int FRAME_Y = 150; 
	
    private JLabel lblTitle;
    private JLabel lblQuestion;
    private JLabel lblHint;
	private JTextField firstNum;
	private JTextField secondNum;
	private JTextField thirdNum;
	private JTextField resultArea;
	private TextFieldValidator firstValidator;
	private TextFieldValidator secondValidator;
	private TextFieldValidator thirdValidator;	
	private JButton btnOk;
	private JButton btnReset;
	private int [] fermi = new int[3];
	private Random rand = new Random();
	
	public MainFrame() {
		
		for (int i = 0; i < 3; i++) {
			int num = rand.nextInt(10);
			for (int j = 0; j < i; j++) {
				if (num == fermi[j]) {
					num = rand.nextInt(10);
					j = -1;
				}
			}
			fermi[i] = num;
		}
		
        this.setTitle("Concert Ticket Calculator");						// set Frame's name
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setLocation(FRAME_X,FRAME_Y);
        this.setResizable(false);										// user cannot change sizes
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        getContentPane().setLayout(null);
		
		lblTitle = new JLabel("Fermi Guessing Game");
		lblTitle.setFont(new Font("Gulim", Font.BOLD, 12));
		lblTitle.setBounds(12, 21, 159, 15);
		getContentPane().add(lblTitle);
		
		lblQuestion = new JLabel("Enter your three guesses (0-9):");
		lblQuestion.setBounds(12, 46, 184, 15);
		getContentPane().add(lblQuestion);
		
		firstNum = new JTextField();
		firstNum.setBounds(12, 71, 60, 21);
		getContentPane().add(firstNum);
		firstNum.setColumns(10);
		
		secondNum = new JTextField();
		secondNum.setColumns(10);
		secondNum.setBounds(12, 102, 60, 21);
		getContentPane().add(secondNum);
		
		thirdNum = new JTextField();
		thirdNum.setColumns(10);
		thirdNum.setBounds(12, 133, 60, 21);
		getContentPane().add(thirdNum);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(12, 164, 60, 37);
		btnOk.addActionListener(this);
		getContentPane().add(btnOk);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(12, 318, 105, 42);
		btnReset.addActionListener(this);
		getContentPane().add(btnReset);
		
		lblHint = new JLabel("Hints");
		lblHint.setBounds(231, 46, 57, 15);
		getContentPane().add(lblHint);
		
		resultArea = new JTextField();
		resultArea.setBounds(231, 71, 219, 289);
		resultArea.setEnabled(false);					// 맞는지 체크하기 ! ! !
		getContentPane().add(resultArea);
		resultArea.setColumns(10);
		
		firstValidator = new TextFieldValidator(firstNum);
		secondValidator = new TextFieldValidator(secondNum);
		thirdValidator = new TextFieldValidator(thirdNum);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnReset) {
			firstNum.setText("");
			secondNum.setText("");
			thirdNum.setText("");
			resultArea.setText("");
			firstValidator.reset();
			secondValidator.reset();
			thirdValidator.reset();
		}
		
		if (event.getSource() == btnOk) {
			
		}
		
	}
	

	public static void main(String[] args) {
		MainFrame mainframe;
		mainframe = new MainFrame();
		mainframe.setVisible(true);
	}

}
