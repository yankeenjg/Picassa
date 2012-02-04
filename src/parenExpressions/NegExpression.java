package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class NegExpression extends ParenExpression {
	private Expression myOperand1;

	public NegExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	@Override
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("neg") || commandName.contains("!"));
	}
	
	public static RGBColor negate (RGBColor operand1) {
		return new RGBColor(-operand1.getRed(), -operand1.getGreen(), -operand1.getBlue());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return negate(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private NegExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new NegExpression());
	}

	public String getCommandName() {
		return "neg";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new NegExpression(operands);
	}
}
