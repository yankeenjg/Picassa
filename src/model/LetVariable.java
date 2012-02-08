package model;
import java.util.TreeMap;
import java.util.regex.Matcher;

public class LetVariable extends Expression {

	private String myStringName;
	
	public LetVariable(String stringName) {
		myStringName = stringName;
	}
	
	public String getVariableName(String myInput, Parser parser) {
		Matcher letMatcher = LET_VARIABLE_REGEX.matcher(myInput);
		letMatcher.find(parser.getCurrentPosition());
		return letMatcher.group();
	}
	
	public Expression parseExpression(String myInput, Parser parser) {
		myStringName = getVariableName(myInput, parser);
		setCurrentPosition(myInput, parser, LET_VARIABLE_REGEX);
		return new LetVariable(myStringName);
	}

	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		if (letVariables.containsKey(myStringName)) {
			return (letVariables.get(myStringName)).evaluate(evalX, evalY, letVariables, currentTime);
		}
		throw new ParserException("Unexpected input " + myStringName);
	}

	public boolean isThisType(String myInput, int myCurrentPosition) {
		Matcher letVariableMatcher = LET_VARIABLE_REGEX.matcher(myInput.substring(myCurrentPosition));
		return letVariableMatcher.lookingAt();
	}
	
	private LetVariable() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new LetVariable());
	}
}


