package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class PlusExpression extends ParenExpression{
	
	private Expression myOperand1;
	private Expression myOperand2;
	
	public PlusExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 2;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("plus") || commandName.contains("+"));
	}
	
	public static RGBColor add(RGBColor operand1, RGBColor operand2) {
		return new RGBColor(operand1.getRed() + operand2.getRed(), operand1.getGreen() + operand2.getGreen(),
				operand1.getBlue() + operand2.getBlue());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return add(myOperand1.evaluate(evalX, evalY, letVariables), myOperand2.evaluate(evalX, evalY, letVariables));
	}
	
	private PlusExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new PlusExpression());
	}

	public String getCommandName() {
		return "plus";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new PlusExpression(operands);
	}
}


