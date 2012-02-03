package model;
import java.util.ArrayList;
import java.util.TreeMap;


public class AverageExpression extends ParenExpression {

	private ArrayList<Expression> myOperandList;

	public AverageExpression(ArrayList<Expression> operandList) {
		myOperandList = operandList;
	}

	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands > 0;
	}

	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("average"));
	}

	public static RGBColor average(ArrayList<RGBColor> operands) {
		double red = 0;
		double green = 0;
		double blue = 0;
		for (RGBColor operand : operands) {
			red += operand.getRed();
			green += operand.getGreen();
			blue += operand.getBlue();
		}
		double numOperands = operands.size();
		return new RGBColor(red/numOperands, green/numOperands, blue/numOperands);
	}
		

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		ArrayList<RGBColor> colorOperands = new ArrayList<RGBColor>();
		for (Expression expression : myOperandList) {
			colorOperands.add(expression.evaluate(evalX, evalY, letVariables));
		}
		return average(colorOperands);
	}

	private AverageExpression() {}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new AverageExpression());
	}

	public String getCommandName() {
		return "average";
	}

	public Expression getExpression(ArrayList<Expression> operands) {
		return new AverageExpression(operands);
	}
}