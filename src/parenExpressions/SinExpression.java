package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class SinExpression extends ParenExpression {
	
	private Expression myOperand1;

	public SinExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor sine (RGBColor operand1) {
		return new RGBColor(Math.sin(operand1.getRed()), Math.sin(operand1.getGreen()), Math.sin(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return sine(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private SinExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new SinExpression());
	}

	public String getCommandName() {
		return "sin";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new SinExpression(operands);
	}
}