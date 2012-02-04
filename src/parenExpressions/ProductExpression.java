package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;


public class ProductExpression extends ParenExpression {

	private ArrayList<Expression> myOperandList;

	public ProductExpression(ArrayList<Expression> operandList) {
		myOperandList = operandList;
	}

	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands > 0;
	}

	public static RGBColor product(ArrayList<RGBColor> operands) {
		double red = operands.get(0).getRed();
		double green = operands.get(0).getGreen();
		double blue = operands.get(0).getBlue();
		operands.remove(0);
		for (RGBColor operand : operands) {
			red *= operand.getRed();
			green *= operand.getGreen();
			blue *= operand.getBlue();
		}
		return new RGBColor(red, green, blue);
	}
		

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		ArrayList<RGBColor> colorOperands = new ArrayList<RGBColor>();
		for (Expression expression : myOperandList) {
			colorOperands.add(expression.evaluate(evalX, evalY, letVariables, currentTime));
		}
		return product(colorOperands);
	}

	private ProductExpression() {}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new ProductExpression());
	}

	public String getCommandName() {
		return "product";
	}

	public Expression getExpression(ArrayList<Expression> operands) {
		return new ProductExpression(operands);
	}
}

