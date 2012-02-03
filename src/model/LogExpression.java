package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class LogExpression extends ParenExpression {
	
	private Expression myOperand1;

	public LogExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("log"));
	}
	
	public static RGBColor logE(RGBColor operand1) {
		return new RGBColor(Math.log(operand1.getRed()), Math.log(operand1.getGreen()), Math.log(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return logE(myOperand1.evaluate(evalX, evalY, letVariables));
	}
	
	private LogExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new LogExpression());
	}

	public String getCommandName() {
		return "log";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new LogExpression(operands);
	}
}