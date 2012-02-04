package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinBWExpression extends ParenExpression {
	
	private Expression myOperand1;
	private Expression myOperand2;

	public PerlinBWExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 2;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("perlinBW"));
	}

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return PerlinNoise.greyNoise(myOperand1.evaluate(evalX, evalY, letVariables, currentTime), myOperand2.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private PerlinBWExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new PerlinBWExpression());
	}

	public String getCommandName() {
		return "perlinBW";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new PerlinBWExpression(operands);
	}
}