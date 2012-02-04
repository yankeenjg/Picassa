package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class ExpExpression extends ParenExpression {
	
	private Expression myOperand1;
	private Expression myOperand2;

	public ExpExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 2;
	}
	
	@Override
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("exp") || commandName.contains("^"));
	}
	
	public static RGBColor exponentiate(RGBColor operand1, RGBColor operand2) {
		return new RGBColor (Math.pow(operand1.getRed(), operand2.getRed()), Math.pow(operand1.getGreen(), operand2.getGreen()),
				Math.pow(operand1.getBlue(), operand2.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return exponentiate(myOperand1.evaluate(evalX, evalY, letVariables, currentTime), myOperand2.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private ExpExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new ExpExpression());
	}

	public String getCommandName() {
		return "exp";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new ExpExpression(operands);
	}
}
