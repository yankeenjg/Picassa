package model;
import java.util.ArrayList;
import java.util.TreeMap;


public class MinExpression extends ParenExpression {

	private ArrayList<Expression> myOperandList;

	public MinExpression(ArrayList<Expression> operandList) {
		myOperandList = operandList;
	}

	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands > 0;
	}

	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("min"));
	}

	public static RGBColor min(ArrayList<RGBColor> operands) {
		double red = operands.get(0).getRed();
		double green = operands.get(0).getGreen();
		double blue = operands.get(0).getBlue();
		operands.remove(0);
		for (RGBColor operand : operands) {
			red = Math.min(red, operand.getRed());
			green = Math.min(green, operand.getGreen());
			blue = Math.min(blue, operand.getBlue());
		}
		return new RGBColor(red, green, blue);
	}
		

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		ArrayList<RGBColor> colorOperands = new ArrayList<RGBColor>();
		for (Expression expression : myOperandList) {
			colorOperands.add(expression.evaluate(evalX, evalY, letVariables));
		}
		return min(colorOperands);
	}

	private MinExpression() {}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new MinExpression());
	}

	public String getCommandName() {
		return "min";
	}

	public Expression getExpression(ArrayList<Expression> operands) {
		return new MinExpression(operands);
	}
}