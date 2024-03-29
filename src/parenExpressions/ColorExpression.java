package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class ColorExpression extends ParenExpression {
	private Expression myOperand1;
	private Expression myOperand2;
	private Expression myOperand3;

	public ColorExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
		myOperand3 = operandList.get(2);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 3;
	}
	
	public static RGBColor color (RGBColor operand1, RGBColor operand2, RGBColor operand3) {
		return new RGBColor(operand1.getRed(), operand2.getGreen(), operand3.getBlue());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return color(myOperand1.evaluate(evalX, evalY, letVariables, currentTime), myOperand2.evaluate(evalX, evalY, letVariables, currentTime), myOperand3.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private ColorExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new ColorExpression());
	}

	public String getCommandName() {
		return "color";
	}

	public Expression getExpression(ArrayList<Expression> operands) {
		return new ColorExpression(operands);
	}
}
