package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class AbsExpression extends ParenExpression {
	private Expression myOperand1;

	public AbsExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("abs"));
	}
	
	public static RGBColor absolute (RGBColor operand1) {
		return new RGBColor(Math.abs(operand1.getRed()), Math.abs(operand1.getGreen()), Math.abs(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return absolute(myOperand1.evaluate(evalX, evalY, letVariables));
	}
	
	private AbsExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new AbsExpression());
	}

	public String getCommandName() {
		return "abs";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new AbsExpression(operands);
	}
}