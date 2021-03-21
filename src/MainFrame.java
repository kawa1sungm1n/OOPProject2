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
	private JTextField resultArea;
	private TextFieldValidator[] validators = new TextFieldValidator[3];;
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
		
		for (int i = 0; i < 3; i++) {
			getContentPane().add(nums[i]);
			nums[i].setColumns(10);
		}
		
		nums[0].setBounds(12, 71, 60, 21);		
		nums[1].setBounds(12, 102, 60, 21);
		nums[2].setBounds(12, 133, 60, 21);
		
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
		}
		
		if (event.getSource() == btnOk) {
			boolean checkValidator = true;
			for (int i = 0; i < 3; i++) {
				if(!validators[i].check())
					checkValidator = false;  
			}
			if (!(checkValidator)) {
				return;
			}
			
		}		
	}
	

	public static void main(String[] args) {
		MainFrame mainframe;
		mainframe = new MainFrame();
		mainframe.setVisible(true);
	}

}
