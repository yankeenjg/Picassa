package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class FloorExpression extends ParenExpression {
	
	private Expression myOperand1;

	public FloorExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor floor (RGBColor operand1) {
		return new RGBColor(Math.round(operand1.getRed() - 0.5), Math.round(operand1.getGreen() - 0.5), Math.round(operand1.getBlue() - 0.5));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return floor(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private FloorExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new FloorExpression());
	}

	public String getCommandName() {
		return "floor";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new FloorExpression(operands);
	}
}