package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class TanExpression extends ParenExpression {
	
	private Expression myOperand1;

	public TanExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor tangent (RGBColor operand1) {
		return new RGBColor(Math.tan(operand1.getRed()), Math.abs(operand1.getGreen()), Math.abs(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return tangent(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
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