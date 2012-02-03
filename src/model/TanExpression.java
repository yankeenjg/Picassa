package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class TanExpression extends ParenExpression {
	
	private Expression myOperand1;

	public TanExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("tan"));
	}
	
	public static RGBColor tangent (RGBColor operand1) {
		return new RGBColor(Math.tan(operand1.getRed()), Math.abs(operand1.getGreen()), Math.abs(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return tangent(myOperand1.evaluate(evalX, evalY, letVariables));
	}
	
	private TanExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new TanExpression());
	}

	public String getCommandName() {
		return "tan";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new TanExpression(operands);
	}
}