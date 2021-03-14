import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class TextFieldValidator {
	
	private String regExp = "\\w";
	private Color errorColor = Color.RED;
	private JTextField myTarget;

	
	//
	public TextFieldValidator(JTextField myTarget, Color myErrorColor) {
		this.myTarget = myTarget;
		this.errorColor = myErrorColor;
	}
	
	public TextFieldValidator(JTextField myTarget) {
		this.myTarget = myTarget;
	}
	
	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}
	
	public void setErrorColor(Color errorColor) {
		this.errorColor = errorColor;
	}
	
	public boolean check() {
		if (myTarget.getText().matches(regExp)) {
			 return true;
		} else {
			myTarget.setBorder(BorderFactory.createLineBorder(errorColor, 1));
			return false;
		}
	}
	
	public void reset() {
		myTarget.setBorder(BorderFactory.createEmptyBorder());
		return;
	}
}
