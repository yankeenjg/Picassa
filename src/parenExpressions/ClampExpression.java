package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class ClampExpression extends ParenExpression {
	private Expression myOperand1;

	public ClampExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor clamp (RGBColor operand1) {
		double red = Math.max(operand1.getRed(), -1);
		double green = Math.max(operand1.getGreen(), -1);
		double blue = Math.max(operand1.getBlue(), -1);
		red = clampIfPositive(operand1.getRed());
		green = clampIfPositive(operand1.getGreen());
		blue = clampIfPositive(operand1.getBlue());
		return new RGBColor(red, green, blue);
	}
	
	public static double clampIfPositive (double operand1) {
		if (operand1 > 0) {
			return (Math.min(operand1, 1));
		}
		return operand1;
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return clamp(myOperand1.evaluate(evalX, evalY, letVariables, currentTime ));
	}
	
	private ClampExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new ClampExpression());
	}
	
	public String getCommandName() {
		return "clamp";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new ClampExpression(operands);
	}
}