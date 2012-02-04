package parenExpressions;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinColorExpression extends ParenExpression {
	
	private Expression myOperand1;
	private Expression myOperand2;

	public PerlinColorExpression(ArrayList<Expression> operandList) {
		myOperand1 = operandList.get(0);
		myOperand2 = operandList.get(1);
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 2;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("perlinColor"));
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return PerlinNoise.colorNoise(myOperand1.evaluate(evalX, evalY, letVariables, currentTime), myOperand2.evaluate(evalX, evalY, letVariables, currentTime));
	}
	
	private PerlinColorExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new PerlinColorExpression());
	}

	public String getCommandName() {
		return "perlinColor";
	}
	
	public Expression getExpression(ArrayList<Expression> operands) {
		return new PerlinColorExpression(operands);
	}
}