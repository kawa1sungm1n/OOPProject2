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
    private JTextField [] nums = {new JTextField(), new JTextField(), new JTextField()};
	private JTextArea resultArea;
	private TextFieldValidator[] validators = new TextFieldValidator[3];
	private JButton btnOk;
	private JButton btnReset;
	private int [] fermi = new int[3]; // correct answer
	private Random rand = new Random();
	private int counts = 0;
	
	public MainFrame() {
		
		// randomly create the answer
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
		lblTitle.setFont(new Font("Courier", Font.BOLD, 13));
		lblTitle.setBounds(12, 21, 159, 15);
		getContentPane().add(lblTitle);
		
		lblQuestion = new JLabel("Enter your three guesses (0-9):");
		lblQuestion.setFont(new Font ("Courier", Font.PLAIN, 12));
		lblQuestion.setBounds(12, 46, 184, 15);
		getContentPane().add(lblQuestion);
		
		for (int i = 0; i < 3; i++) {
			getContentPane().add(nums[i]);
			nums[i].setColumns(10);
		}
		
		nums[0].setBounds(12, 71, 60, 21);		
		nums[1].setBounds(12, 102, 60, 21);
		nums[2].setBounds(12, 133, 60, 21);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(12, 164, 60, 32);
		btnOk.addActionListener(this);
		getContentPane().add(btnOk);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(12, 318, 75, 42);
		btnReset.addActionListener(this);
		getContentPane().add(btnReset);
		
		lblHint = new JLabel("Hints:");
		lblHint.setFont(new Font ("Courier", Font.PLAIN, 12));
		lblHint.setBounds(231, 46, 57, 15);
		getContentPane().add(lblHint);
		
		resultArea = new JTextArea();
		resultArea.setBounds(231, 71, 219, 289);
		resultArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		resultArea.setFont(new Font("Courier", Font.PLAIN, 12));
		resultArea.setForeground(Color.BLACK);
		resultArea.setEditable(false);		// 수정
		resultArea.setColumns(15);
		getContentPane().add(resultArea);
		
		for (int i = 0; i < 3; i++) {
			validators[i] =  new TextFieldValidator(nums[i]);
			validators[i].setRegExp("^[0-9]$");
		}
		
//		firstValidator = new TextFieldValidator(firstNum);
//		secondValidator = new TextFieldValidator(secondNum);
//		thirdValidator = new TextFieldValidator(thirdNum);
//		
//		firstValidator.setRegExp("^[0-9]$");
//		secondValidator.setRegExp("^[0-9]$"); 
//		thirdValidator.setRegExp("^[0-9]$"); 
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnReset) {
			for (int i = 0; i < 3; i++) {
				nums[i].setText("");
				validators[i].reset();		
			}
			resultArea.setText("");
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
			btnOk.setEnabled(true);
		}
		if (event.getSource() == btnOk) {
			counts++;
			boolean checkValidator = true;
			for (int i = 0; i < 3; i++) {
				if(!validators[i].check())
					checkValidator = false;  
			}
			if (!(checkValidator)) {
				return;
			}
			String guesses = "";
			for (int i = 0;i < 3; i++) {
				guesses += nums[i].getText() + " ";
			}
			guesses += ": ";
			
			String [] hint = new String[3];
			for (int i = 0; i < 3; i++) {
				boolean found = false;
				int n = Integer.parseInt(nums[i].getText());
				for (int j = 0; j < 3; j++) {
					if (n == fermi[j]) {
						found = true;
						if (i == j) {
							hint[i] = "Fermi";
						}
						else {
							hint[i] = "Pico";
						}
					}
				}
				if(!found) hint[i] = "Nano";
			}
			boolean gameOver = true;
			for(int i = 0; i < 3; i++) {
				guesses += hint[i] + " ";
				if (hint[i] != "Fermi") gameOver = false;
			}
			
			resultArea.setText(resultArea.getText() + guesses+ "\n");
		
			if (gameOver) { 
				resultArea.append("Congratulations! Guesses: " + counts); // 이 부분 하나 남음
			}
		}		
	}
	

	public static void main(String[] args) {
		MainFrame mainframe;
		mainframe = new MainFrame();
		mainframe.setVisible(true);
	}

}
