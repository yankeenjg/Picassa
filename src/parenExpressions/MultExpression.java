package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class MultExpression extends ParenExpression{
	
	private Expression myOperand1;
	private Expression myOperand2;
	
	public MultExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 2;
	}
	
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("mul") || commandName.contains("*"));
	}
	
	public static RGBColor multiply(RGBColor operand1, RGBColor operand2) {
		return new RGBColor(operand1.getRed() * operand2.getRed(), operand1.getGreen() * operand2.getGreen(),
				operand1.getBlue() * operand2.getBlue());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return multiply(myOperand1.evaluate(evalX, evalY, letVariables, currentTime), myOperand2.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private MultExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new MultExpression());
	}

	public String getCommandName() {
		return "mul";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new MultExpression(operands);
	}
}


