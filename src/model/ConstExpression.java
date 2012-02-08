package model;
import java.util.TreeMap;
import java.util.regex.Matcher;

public class ConstExpression extends Expression {
	
	private RGBColor myOperand1;
	
	public ConstExpression(RGBColor operand1) {
		myOperand1 = operand1;
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		return myOperand1;
	}
	
	public boolean isThisType(String myInput, int myCurrentPosition) {
		 Matcher doubleMatcher = DOUBLE_REGEX.matcher(myInput.substring(myCurrentPosition));
	     return doubleMatcher.lookingAt();
	}
	
	public Expression parseExpression(String myInput, Parser parser) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(myInput);
		doubleMatcher.find(parser.getCurrentPosition());
		String numberMatch = myInput.substring(doubleMatcher.start(), doubleMatcher.end());
		parser.setPosition(doubleMatcher.end());
		double grayValue = Double.parseDouble(numberMatch);
		return new ConstExpression(new RGBColor(grayValue));
	}
	
	public ConstExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new ConstExpression());
	}
}
