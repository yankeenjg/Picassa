package model;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;

public abstract class ParenExpression extends Expression { 
	public abstract String getCommandName();
	public abstract boolean isCorrectNumOperands(int numOperands);
	public abstract RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime);
	public abstract Expression getExpression(ArrayList<Expression> operands);
	
	public boolean isThisType(String myInput, int myCurrentPosition) {
		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
		if (expMatcher.lookingAt()) {
			 Matcher expMatcher2 = EXPRESSION_BEGIN_REGEX.matcher(myInput);
		     expMatcher2.find(myCurrentPosition);
			 return isThisKindOfExpression(expMatcher2.group(1));
		}
		return false;
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
		String commandName = getCommandName();
		setCurrentPosition(myInput, parser, EXPRESSION_BEGIN_REGEX);
		ArrayList<Expression> operandList = getOperands(parser);
		CheckCorrectNumOperands(commandName, operandList.size());
		return getExpression(operandList);
	}
	
	private void CheckCorrectNumOperands(String commandName, int numOperands) {
		if (!isCorrectNumOperands(numOperands)) {
			throw new ParserException("Unexpected number of operands for " + commandName);
		}
		return;
	}
	
	public boolean isThisKindOfExpression(String command) {
		return (command.contains(getCommandName()));
	}
}
