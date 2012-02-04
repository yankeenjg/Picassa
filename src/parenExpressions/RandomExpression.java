package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class RandomExpression extends ParenExpression {
	

	public RandomExpression(ArrayList<Expression> operandList) {
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 0;
	}
	
	public static RGBColor random () {
		return new RGBColor(Math.random()*2 - 1, Math.random()*2 - 1, Math.random()*2 - 1);
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return random();
	}
	
	private RandomExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new RandomExpression());
	}

	public String getCommandName() {
		return "random";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new RandomExpression(operands);
	}
}