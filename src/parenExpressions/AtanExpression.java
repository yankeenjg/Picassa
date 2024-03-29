package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;


public class AtanExpression extends ParenExpression {
	private Expression myOperand1;

	public AtanExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor atan (RGBColor operand1) {
		return new RGBColor(Math.atan(operand1.getRed()), Math.atan(operand1.getGreen()), Math.atan(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return atan(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private AtanExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new AtanExpression());
	}

	public String getCommandName() {
		return "atan";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new DivExpression(operands);
	}
}
