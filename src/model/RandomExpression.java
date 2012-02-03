package model;
import java.util.ArrayList;
import java.util.TreeMap;

public class RandomExpression extends ParenExpression {
	

	public RandomExpression(ArrayList<Expression> operandList) {
	}
	
	public boolean isCorrectNumOperands(int numOperands) {
		return numOperands == 0;
	}
	
	public boolean isThisKindOfExpression(String commandName) {
		return (commandName.contains("random"));
	}
	
	public static RGBColor random () {
		return new RGBColor(Math.random(), Math.random(), Math.random());
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
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