package variableExpressions;
import java.util.TreeMap;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.VariableExpression;

public class TimeVarExpression extends VariableExpression {
	
	public TimeVarExpression() {}
	
	public static RGBColor time(double evalX) {
		return new RGBColor(evalX);
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		 return time(currentTime*2 - 1);
	}
	
	public Expression getExpression() {
		return new TimeVarExpression();
	}
	
	public String getCommandName() {
		return "t";
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new TimeVarExpression());
	}
	
}
