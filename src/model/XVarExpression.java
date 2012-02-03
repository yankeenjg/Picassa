package model;

import java.util.TreeMap;

public class XVarExpression extends VariableExpression {
	
	public XVarExpression() {}
	
	public boolean isThisKindOfExpression(String commandName) {
		return commandName.contains("x");
	}
	
	public static RGBColor variable(double evalX) {
		return new RGBColor(evalX);
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables) {
		 return variable(evalX);
	}
	
	public Expression parseExpression(String myInput, Parser parser) {
		setCurrentPosition(myInput, parser, VARIABLE_REGEX);
		return new XVarExpression();
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new XVarExpression());
	}
	
}
