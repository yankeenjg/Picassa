package model;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public abstract class Expression
{
  
    protected static final Pattern DOUBLE_REGEX = Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
    protected static final Pattern EXPRESSION_BEGIN_REGEX = Pattern.compile("\\(([a-z,A-Z,!,-,*,+,%,/,^]+)");
    protected static final Pattern VARIABLE_REGEX = Pattern.compile("[xyt]");
    protected static final Pattern LET_REGEX = Pattern.compile("\\((let)");
    protected static final Pattern LET_VARIABLE_REGEX = Pattern.compile("[a-z,A-Z]+");
    
    public abstract Expression parseExpression(String myInput, Parser parser);
    public abstract RGBColor evaluate(double evalX, double evalY, TreeMap<String, Expression> letVariables, double currentTime);
    public abstract boolean isThisType(String myInput, int myCurrentPosition);

    public void setCurrentPosition(String myInput, Parser parser, Pattern pattern) {
		Matcher expMatcher = pattern.matcher(myInput);
	    expMatcher.find(parser.getCurrentPosition());
	    parser.setPosition(expMatcher.end());
	}
    
    /**
     * @return string representation of expression
     */
    public String toString (String commandName, int myValue, Expression Operand1, Expression Operand2)
    {
        StringBuffer result = new StringBuffer();
        if (commandName == null) {
            result.append(myValue); 
        }
        else {
            result.append("(");
            result.append(" " + commandName + " ");
            result.append(Operand1.toString());  
            result.append(Operand2.toString());
            result.append(")");
        }
        return result.toString();
    }
}
