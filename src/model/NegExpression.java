package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class NegExpression extends ParenExpression {
	private Expression myOperand1;

	public NegExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("neg") || commandName.contains("!"));
	}
	
	public static RGBColor negate (RGBColor operand1) {
		return new RGBColor(-operand1.getRed(), -operand1.getGreen(), -operand1.getBlue());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return negate(myOperand1.evaluate(evalX, evalY, letVariables));
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
