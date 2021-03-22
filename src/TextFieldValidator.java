
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * TextField Validator
 * @author Sungmin Park
 *
 */

public class TextFieldValidator  {

	/** Set Regular Expression of Program */
	private String regExp = "\\w";
	/** Set Error color*/
	private Color errorColor = Color.RED;
	/** Set Target number (input value) */
	private JTextField myTarget;
	
	/**
	 * Set the value of TextFieldValidator
	 * @param myTarget	Set the target number
	 * @param myErrorColor an error occurs, replace the border in the text box with red.
	 */
	public TextFieldValidator(JTextField myTarget, Color myErrorColor) {
		this.myTarget = myTarget;		// get myTarget
		this.errorColor = myErrorColor;	// get errorColor
	}
	
	/**
	 * Set the value of TextFieldValidator #2
	 * @param myTarget Set the target number.
	 */
	public TextFieldValidator(JTextField myTarget) {
		this.myTarget = myTarget;		// get myTarget
	}
	
	/**
	 * Set the value of regExp 
	 * @param regExp for outputting the values you enter
	 */
	public void setRegExp(String regExp) {
		this.regExp = regExp;	// get regExp "\\w"
	}
	
	/** 
	 * Set the value of errorColor
	 * @param errorColor Set border color default to red
	 */
	public void setErrorColor(Color errorColor) {
		this.errorColor = errorColor;	// get errorColor
	}
	
	/**
	 * Set the value of reset function
	 * When user use reset() textField will get default border.
	 */
	public void reset() {
		myTarget.setBorder(new JTextField().getBorder());
		return;
	}
	/**
	 * when user try to check value of text, if collect then return true
	 * when user try to check value of text, if not collect then make border's color red and return false
	 * @return value of the check()
	 */
	public boolean check() {
		if (myTarget.getText().matches(regExp)) {
			reset(); 
			return true;
		} else {	// if not,
			myTarget.setBorder(BorderFactory.createLineBorder(errorColor, 1));	// output error color(red)
			return false;
		}
	}
}
