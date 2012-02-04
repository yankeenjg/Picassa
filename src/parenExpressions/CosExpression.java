package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class CosExpression extends ParenExpression {
	private Expression myOperand1;

	public CosExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor cosine (RGBColor operand1) {
		return new RGBColor(Math.cos(operand1.getRed()), Math.cos(operand1.getGreen()), Math.cos(operand1.getBlue()));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return cosine(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private CosExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new CosExpression());
	}

	public String getCommandName() {
		return "cos";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new CosExpression(operands);
	}
}