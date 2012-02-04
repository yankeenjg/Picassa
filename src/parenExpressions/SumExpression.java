package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;


public class SumExpression extends ParenExpression {

	private ArrayList<Expression> myOperandList;

	public SumExpression(ArrayList<Expression> operandList) {
		myOperandList = operandList;
	}

	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands > 0;
	}

	public static RGBColor sum(ArrayList<RGBColor> operands) {
		double red = 0;
		double green = 0;
		double blue = 0;
		for (RGBColor operand : operands) {
			red += operand.getRed();
			green += operand.getGreen();
			blue += operand.getBlue();
		}
		return new RGBColor(red, green, blue);
	}
		

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		ArrayList<RGBColor> colorOperands = new ArrayList<RGBColor>();
		for (Expression expression : myOperandList) {
			colorOperands.add(expression.evaluate(evalX, evalY, letVariables, currentTime));
		}
		return sum(colorOperands);
	}

	private SumExpression() {}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new SumExpression());
	}

	public void setOperands(ArrayList<Expression> operandList) {
		myOperandList = operandList;
	}

	public String getCommandName() {
		return "sum";
	}

	public Expression getExpression(ArrayList<Expression> operands) {
		return new SumExpression(operands);
	}
}


