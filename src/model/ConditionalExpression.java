package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class ConditionalExpression extends ParenExpression{
	
	private Expression myOperand1;
	private Expression myOperand2;
	private Expression myOperand3;
	
	public ConditionalExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
		myOperand3 = operandList.get(2);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 3;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("if"));
	}
	
	public static RGBColor conditional(RGBColor operand1, RGBColor operand2, RGBColor operand3) {
		double colorValue = (operand1.getRed() + operand1.getGreen() + operand1.getBlue())/3.0;
		if (colorValue > 0) {
			return operand2;
		}
		return operand3;
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return conditional(myOperand1.evaluate(evalX, evalY, letVariables), myOperand2.evaluate(evalX, evalY, letVariables), myOperand3.evaluate(evalX, evalY, letVariables));
	}
	
	private ConditionalExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new ConditionalExpression());
	}

	public String getCommandName() {
		return "if";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new ConditionalExpression(operands);
	}
}
