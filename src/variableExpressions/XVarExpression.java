package variableExpressions;

import java.util.TreeMap;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.VariableExpression;

public class XVarExpression extends VariableExpression {
	
	public XVarExpression() {}
	
	public static RGBColor variable(double evalX) {
		return new RGBColor(evalX);
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return variable(evalX);
	}
	
	public Expression getExpression() {
		return new XVarExpression();
	}
	
	public String getCommandName() {
		return "x";
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new XVarExpression());
	}
	
}
