package model;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class LetExpression extends Expression {
	private Expression myValue;
	private String myVariableName;
	private Expression myOperand1;
	
	
	public LetExpression(String variableName, Expression value, ArrayList<Expression> operandList) {
		myVariableName = variableName;
		myValue = value;
		myOperand1 = operandList.get(0);
	}
	
	public boolean isThisType(String myInput, int myCurrentPosition) {
		Matcher letMatcher = LET_REGEX.matcher(myInput.substring(myCurrentPosition));
		return letMatcher.lookingAt();
	}
	
	public String getVariableName(String myInput, Parser parser) {
		Matcher letMatcher = LET_VARIABLE_REGEX.matcher(myInput);
		letMatcher.find(parser.getCurrentPosition());
		return letMatcher.group();
	}
	
	public Expression getValue(Parser parser) {
		return parser.parseExpression(parser);
	}
	
	public ArrayList<Expression> getOperands(Parser parser) {
		ArrayList<Expression> operandList = new ArrayList<Expression>();
		while (parser.currentCharacter() != ')') {
			operandList.add(parser.parseExpression(parser));
			parser.skipWhiteSpace();
		}
		int currentPosition = parser.getCurrentPosition() + 1;
		parser.setPosition(currentPosition);
		return operandList;
	} 
	
	public Expression parseExpression(String myInput, Parser parser) {
		setCurrentPosition(myInput, parser, LET_REGEX);
		String variableName = getVariableName(myInput, parser);
		setCurrentPosition(myInput, parser, LET_VARIABLE_REGEX);
		Expression value = getValue(parser);
		ArrayList<Expression> operandList = getOperands(parser);
		if (operandList.size() > 1) {
			throw new ParserException("Unexpected number of operands in let statement");
		}
		return new LetExpression(variableName, value, operandList);
	}
	
	public RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime) {
		letVariables.put(myVariableName, myValue);
		RGBColor result = myOperand1.evaluate(evalX, evalY, letVariables, currentTime);
		letVariables.remove(myVariableName);
		return result;		
	}
	
	private LetExpression() {}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new LetExpression());
	}
}	

	
	
	
	





