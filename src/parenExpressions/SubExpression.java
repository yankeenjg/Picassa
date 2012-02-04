package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class SubExpression extends ParenExpression {

	private Expression myOperand1;
	private Expression myOperand2;
	
	public SubExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 2;
	}
	
	@Override
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("sub") || commandName.contains("-"));
	}
	
	public static RGBColor subtract(RGBColor operand1, RGBColor operand2) {
		return new RGBColor(operand1.getRed() - operand2.getRed(), operand1.getGreen() - operand2.getGreen(),
				operand1.getBlue() - operand2.getBlue());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return subtract(myOperand1.evaluate(evalX, evalY, letVariables, currentTime), myOperand2.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private SubExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new SubExpression());
	}

	public String getCommandName() {
		return "sub";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new SubExpression(operands);
	}
}


