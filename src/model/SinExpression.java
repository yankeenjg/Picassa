package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class SinExpression extends ParenExpression {
	
	private Expression myOperand1;

	public SinExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("sin"));
	}
	
	public static RGBColor sine (RGBColor operand1) {
		return new RGBColor(Math.sin(operand1.getRed()), Math.sin(operand1.getGreen()), Math.sin(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return sine(myOperand1.evaluate(evalX, evalY, letVariables));
	}
	
	private SinExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new SinExpression());
	}

	public String getCommandName() {
		return "sin";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new SinExpression(operands);
	}
}