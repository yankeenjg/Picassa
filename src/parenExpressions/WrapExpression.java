package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class WrapExpression extends ParenExpression {
	
	private Expression myOperand1;

	public WrapExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor wrap (RGBColor operand1) {
		double red = wrapDoubleValue(operand1.getRed());
		double green = wrapDoubleValue(operand1.getGreen());
		double blue = wrapDoubleValue(operand1.getBlue());
		return new RGBColor(red, green, blue);
	}
	
	public static double wrapDoubleValue(double operand1) {
		double value = Math.abs(operand1);
		while (value > 1) {
			value -= 2;
		}
		return Math.copySign(1.0, operand1) * value;
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return wrap(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private WrapExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new WrapExpression());
	}

	public String getCommandName() {
		return "wrap";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new WrapExpression(operands);
	}
}