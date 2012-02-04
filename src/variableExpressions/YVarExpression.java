package variableExpressions;

import java.util.TreeMap;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.VariableExpression;

public class YVarExpression extends VariableExpression {
	
	public YVarExpression() {}

	public static RGBColor variable(double evaly) {
		return new RGBColor(evaly);
	}

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		return variable(evalY);
	}
	
	public Expression getExpression() {
		return new YVarExpression();
	}
	
	public String getCommandName() {
		return "y";
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new YVarExpression());
	}
	

}
