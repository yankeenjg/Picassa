package model;

import java.util.TreeMap;

public class YVarExpression extends VariableExpression {
	
	public YVarExpression() {}

	public boolean isThisKindOfExpression(String commandName) {
		return commandName.contains("y");
	}

	public static RGBColor variable(double evaly) {
		return new RGBColor(evaly);
	}

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		return variable(evalY);
	}
	
	public Expression parseExpression(String myInput, Parser parser) {
		setCurrentPosition(myInput, parser, VARIABLE_REGEX);
		return new YVarExpression();
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new YVarExpression());
	}
	

}
