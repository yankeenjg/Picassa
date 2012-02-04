
package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class RgbToYCrCbExpression extends ParenExpression {

	private Expression myOperand1;

	public RgbToYCrCbExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 1;
	}
	
    public static RGBColor RgbToYCrCb (RGBColor operand1) {
        return new RGBColor(operand1.getRed() *  0.2989 + operand1.getGreen() *  0.5866 + operand1.getBlue() *  0.1145,
                   operand1.getRed() * -0.1687 + operand1.getGreen() * -0.3312 + operand1.getBlue() *  0.5,
                   operand1.getRed() *  0.5000 + operand1.getGreen() * -0.4183 + operand1.getBlue() * -0.0816);
    }
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return RgbToYCrCb(myOperand1.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private RgbToYCrCbExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new RgbToYCrCbExpression());
	}

	public String getCommandName() {
		return "rgbToYCrCb";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new RgbToYCrCbExpression(operands);
	}
}