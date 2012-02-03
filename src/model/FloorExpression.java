package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class FloorExpression extends ParenExpression {
	
	private Expression myOperand1;

	public FloorExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("floor"));
	}
	
	public static RGBColor floor (RGBColor operand1) {
		return new RGBColor(Math.round(operand1.getRed() - 0.5), Math.round(operand1.getGreen() - 0.5), Math.round(operand1.getBlue() - 0.5));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return floor(myOperand1.evaluate(evalX, evalY, letVariables));
	}
	
	private FloorExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new FloorExpression());
	}

	public String getCommandName() {
		return "floor";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new FloorExpression(operands);
	}
}