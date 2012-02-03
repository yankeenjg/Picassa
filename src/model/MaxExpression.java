package model;
import java.util.ArrayList;
import java.util.TreeMap;


public class MaxExpression extends ParenExpression {

	private ArrayList<Expression> myOperandList;

	public MaxExpression(ArrayList<Expression> operandList) {
		myOperandList = operandList;
	}

	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands > 0;
	}

	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("max"));
	}

	public static RGBColor max(ArrayList<RGBColor> operands) {
		double red = operands.get(0).getRed();
		double green = operands.get(0).getGreen();
		double blue = operands.get(0).getBlue();
		operands.remove(0);
		for (RGBColor operand : operands) {
			red = Math.max(red, operand.getRed());
			green = Math.max(green, operand.getGreen());
			blue = Math.max(blue, operand.getBlue());
		}
		return new RGBColor(red, green, blue);
	}
		

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		ArrayList<RGBColor> colorOperands = new ArrayList<RGBColor>();
		for (Expression expression : myOperandList) {
			colorOperands.add(expression.evaluate(evalX, evalY, letVariables));
		}
		return max(colorOperands);
	}

	private MaxExpression() {}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new MaxExpression());
	}

	public String getCommandName() {
		return "max";
	}

	public Expression getExpression(ArrayList<Expression> operands) {
		return new MaxExpression(operands);
	}
}