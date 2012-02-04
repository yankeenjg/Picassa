
package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class YCrCbtoRGBExpression extends ParenExpression {
	
	private Expression myOperand1;

	public YCrCbtoRGBExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
	public static RGBColor yCrCbtoRGB (RGBColor operand1) {
		return new RGBColor(operand1.getRed() + operand1.getBlue() *  1.4022, 
				operand1.getRed() + operand1.getGreen() * -0.3456 + operand1.getBlue() * -0.7145,
				operand1.getRed() + operand1.getGreen() *  1.7710);
	}

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return yCrCbtoRGB(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private YCrCbtoRGBExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new YCrCbtoRGBExpression());
	}

	public String getCommandName() {
		return "yCrCbtoRGB";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new YCrCbtoRGBExpression(operands);
	}
}