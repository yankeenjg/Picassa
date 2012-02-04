package model;
import java.util.TreeMap;
import java.util.regex.Matcher;

public abstract class VariableExpression extends Expression {
	
	public abstract String getCommandName();
	public abstract Expression getExpression();
	public abstract RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime);
	
	public boolean isThisType(String myInput, int myCurrentPosition) {
		Matcher variableMatcher = VARIABLE_REGEX.matcher(myInput.substring(myCurrentPosition));
    	if (variableMatcher.lookingAt()) {
    		Matcher variableMatcher2 = VARIABLE_REGEX.matcher(myInput);
        	variableMatcher2.find(myCurrentPosition);
        	myCurrentPosition = variableMatcher2.end();
        	return isThisKindOfExpression(myInput.substring(variableMatcher2.start(), variableMatcher2.end()));
    	}
    	return false;
	}
	
	public Expression parseExpression(String myInput, Parser parser) {
		setCurrentPosition(myInput, parser, VARIABLE_REGEX);
		return getExpression();
	}
	
	public boolean isThisKindOfExpression(String command) {
		return command.contains(getCommandName());
	}
	

}
