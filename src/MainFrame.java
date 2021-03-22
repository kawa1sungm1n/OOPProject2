
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * OOP project 2
 * Fermi Guessing Game
 * @author Sungmin Park
 */


/** MainFrame and Function for Fermi Guessing Game */
public class MainFrame extends JFrame implements ActionListener {
	/** Set Frame's width */
    private final int FRAME_WIDTH = 480;
    /** Set Frame's height */
    private final int FRAME_HEIGHT = 420;
    /** Set Frame's X side */
    private final int FRAME_X = 700;
    /** Set Frame's Y side */
    private final int FRAME_Y = 150; 
	
    /** Title section of this frame */
    private JLabel lblTitle;
    /** Question section of this frame */
    private JLabel lblQuestion;
    /** "Hint:" setcion of this frame */
    private JLabel lblHint;
    /** Define three input windows that user will enter */
    private JTextField [] nums = {new JTextField(), new JTextField(), new JTextField()};
    /** Results area where input values to output */
	private JTextArea resultArea;
	/** Set array of Validators*/
	private TextFieldValidator[] validators = new TextFieldValidator[3];
	/** Button for passing to output value */
	private JButton btnOk;
	/** Button for reset everything */
	private JButton btnReset;
	/** Set fermi array (for correct answer)*/
	private int [] fermi = new int[3]; // correct answer
	/** set random function */
	private Random rand = new Random();
	/** Function for counts how many time user try to guesses*/
	private int counts = 0;
	
	public MainFrame() {
		
		/**
		 * randomly create the answer
		 */
		for (int i = 0; i < 3; i++) {
			/** set first answer numbers between 0 to 10 */
			int num = rand.nextInt(10);
			/** set next answer numbers between 0 to 10 (but not duplicate with first one) */
			for (int j = 0; j < i; j++) {		
				if (num == fermi[j]) {			
					num = rand.nextInt(10);
					j = -1;
				}
			}
			/** set Fermi number on num */
			fermi[i] = num;	
		}
		/** Set Frame's name */
        this.setTitle("Fermi Guessing Game");
        /** Set Frame's sizes */
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		/** Set Frame's Location */
		this.setLocation(FRAME_X,FRAME_Y);
		/** User cannot changes frame's sizes*/
        this.setResizable(false);						
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        getContentPane().setLayout(null);
        
		/** Set Frame's title on top*/ 
		lblTitle = new JLabel("Fermi Guessing Game");
		lblTitle.setFont(new Font("Courier", Font.BOLD, 13));
		lblTitle.setBounds(12, 21, 159, 15);
		getContentPane().add(lblTitle);
		
		/** Set Program's questions area under the title*/
		lblQuestion = new JLabel("Enter your three guesses (0-9):");
		lblQuestion.setFont(new Font ("Courier", Font.PLAIN, 12));
		lblQuestion.setBounds(12, 46, 184, 15);
		getContentPane().add(lblQuestion);
		
		/** Set three input areas under the question's area */
		for (int i = 0; i < 3; i++) {
			getContentPane().add(nums[i]);
			nums[i].setColumns(10);
		}
		
		/** Set exact locations of input areas */
		nums[0].setBounds(12, 71, 60, 21);		
		nums[1].setBounds(12, 102, 60, 21);
		nums[2].setBounds(12, 133, 60, 21);
		
		/** Set Button "Ok" under the input areas */
		btnOk = new JButton("Ok");
		btnOk.setBounds(12, 164, 60, 32);
		btnOk.addActionListener(this);
		getContentPane().add(btnOk);
		
		/** Set Button "Reset" very under of Ok button */
		btnReset = new JButton("Reset");
		btnReset.setBounds(12, 318, 75, 42);
		btnReset.addActionListener(this);
		getContentPane().add(btnReset);
		
		/** Set label of "Hints: " on top right side*/
		lblHint = new JLabel("Hints:");
		lblHint.setFont(new Font ("Courier", Font.PLAIN, 12));
		lblHint.setBounds(231, 46, 57, 15);
		getContentPane().add(lblHint);
		
		/** Set Result Area under the Hints label
		 * set Border's color
		 * Set Font properties
		 * Set Font color
		 * User cannot type anything on this area
		 */
		resultArea = new JTextArea();
		resultArea.setBounds(231, 71, 219, 289);
		resultArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));	// set Border's color
		resultArea.setFont(new Font("Courier", Font.PLAIN, 12));	// set Font properties
		resultArea.setForeground(Color.BLACK);		// Set Font color
		resultArea.setEditable(false);		// User cannot type anything on this area
		resultArea.setColumns(15);
		getContentPane().add(resultArea);
		
		/** 
		 * Apply regular expression on each array nums[]
		 */
		for (int i = 0; i < 3; i++) {
			validators[i] =  new TextFieldValidator(nums[i]);
			validators[i].setRegExp("^[0-9]$");
		}

		
		//	--------------------------------------------------------- Other solution of num's regExp (really basic one)
		//	firstValidator = new TextFieldValidator(firstNum);
		//	secondValidator = new TextFieldValidator(secondNum);
		//	thirdValidator = new TextFieldValidator(thirdNum);
		//		
		//	firstValidator.setRegExp("^[0-9]$");
		//	secondValidator.setRegExp("^[0-9]$"); 
		//	thirdValidator.setRegExp("^[0-9]$"); 
	}
	
	/**
	 *  Functions that occur when the user presses the Reset button or the OK button
	 *  Pressing the reset button resets all the output values, reset the counts of attempts, and returns to the time the program was first run
	 *  When the OK button is pressed, the user-entered value is output to the Result Area and the number of attempts is saved by 1.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnReset) {	// When user click Reset Button
			for (int i = 0; i < 3; i++) {
				nums[i].setText("");	// reset all numbers in the input areas (make space "")
				validators[i].reset();
			}
			resultArea.setText("");		// reset all output values in the result area
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
			btnOk.setEnabled(true);		// set Allows to press the OK button again
			counts = 0;					// reset the user's OK button click counts
		}
		if (event.getSource() == btnOk) { 	// When user click Ok button
			boolean checkValidator = true;		// Create checkValidator variable
			for (int i = 0; i < 3; i++) {
				if(!validators[i].check())
					checkValidator = false;  
			}
			// if the value is not in checkValidator, return the value.
			if (!(checkValidator)) {
				return;		
			}
			String guesses = "";	
			// Outputs the value that the user entered.
			for (int i = 0;i < 3; i++) {
				guesses += nums[i].getText() + " ";		// Adds empty space between the output input values
			}
			guesses += ": ";	// Add a colon to the end of the three input values
			counts++;	// The number of user attempts increases one by one as the game attempted
			// Add Fermi, Pico, or nano to the entered value
			String [] hint = new String[3];
			for (int i = 0; i < 3; i++) {
				boolean found = false;
				int n = Integer.parseInt(nums[i].getText());
				for (int j = 0; j < 3; j++) {
					if (n == fermi[j]) {
						found = true;
						if (i == j) {
							hint[i] = "Fermi";	// Outputs Fermi if the correct answer matches the value "i" entered by the user	
						}
						else {
							hint[i] = "Pico";	// If not, output pico
						}
					}
				}
				if(!found) {
					hint[i] = "Nano";	// If it does not match found and does not match any conditions, output nano.
				}
			}
			// Features to play and end the game.
			boolean gameOver = true;
			for(int i = 0; i < 3; i++) {
				guesses += hint[i] + " ";
				// If get one wrong, cannot end the game
				if (hint[i] != "Fermi") gameOver = false;	
			}
			// Each time a user tries to play a game and enters a number, it outputs the value to the resultArea 
			resultArea.setText(resultArea.getText() + guesses+ "\n");	
			
			// The user finishes the game if all three Fermi are printed correctly 
			if (gameOver) { 
				// Outputs the total number of attempts using the counts previously declared
				resultArea.append("Congratulations! Guesses: " + counts);	
				btnOk.setEnabled(false);	// User cannot use Ok button
			}
		}		
	}
	
	/**
	 * Make MainFrame user can see
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame mainframe;
		mainframe = new MainFrame();
		mainframe.setVisible(true);
	}

}
