package model;

import java.util.ArrayList;

import parenExpressions.AbsExpression;
import parenExpressions.AtanExpression;
import parenExpressions.AverageExpression;
import parenExpressions.CeilingExpression;
import parenExpressions.ClampExpression;
import parenExpressions.ColorExpression;
import parenExpressions.ConditionalExpression;
import parenExpressions.CosExpression;
import parenExpressions.DivExpression;
import parenExpressions.ExpExpression;
import parenExpressions.FloorExpression;
import parenExpressions.LogExpression;
import parenExpressions.MaxExpression;
import parenExpressions.MinExpression;
import parenExpressions.ModExpression;
import parenExpressions.MultExpression;
import parenExpressions.NegExpression;
import parenExpressions.PerlinBWExpression;
import parenExpressions.PerlinColorExpression;
import parenExpressions.PlusExpression;
import parenExpressions.ProductExpression;
import parenExpressions.RandomExpression;
import parenExpressions.RgbToYCrCbExpression;
import parenExpressions.SinExpression;
import parenExpressions.SubExpression;
import parenExpressions.SumExpression;
import parenExpressions.TanExpression;
import parenExpressions.WrapExpression;
import parenExpressions.YCrCbtoRGBExpression;
import variableExpressions.TimeVarExpression;
import variableExpressions.XVarExpression;
import variableExpressions.YVarExpression;


/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser 
 * is used 
 *   http://en.wikipedia.org/wiki/Recursive_descent_parser
 *   
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{

    private ArrayList<ExpressionFactory> ExpressionTypes = new ArrayList<ExpressionFactory>();
    // state of the parser
    private int myCurrentPosition;
    private String myInput;
    
    public int getCurrentPosition() {
    	return myCurrentPosition;
    }
    
    public void setPosition(int currentPosition) {
    	myCurrentPosition = currentPosition;
    }
    
    private void makeExpressionList() {
    	//LetExpression/LetVariable come first so as to override X,Y,T if necessary.
    	ExpressionTypes.add(XVarExpression.getFactory());
    	ExpressionTypes.add(YVarExpression.getFactory());
    	ExpressionTypes.add(TimeVarExpression.getFactory());
    	ExpressionTypes.add(LetExpression.getFactory());
    	ExpressionTypes.add(LetVariable.getFactory());
    	ExpressionTypes.add(ConstExpression.getFactory());
    	ExpressionTypes.add(PlusExpression.getFactory());
    	ExpressionTypes.add(SubExpression.getFactory());
    	ExpressionTypes.add(DivExpression.getFactory());
    	ExpressionTypes.add(MultExpression.getFactory());
    	ExpressionTypes.add(ModExpression.getFactory());
    	ExpressionTypes.add(ExpExpression.getFactory());
    	ExpressionTypes.add(NegExpression.getFactory());
    	ExpressionTypes.add(ColorExpression.getFactory());
    	ExpressionTypes.add(RandomExpression.getFactory());
    	ExpressionTypes.add(FloorExpression.getFactory());
    	ExpressionTypes.add(CeilingExpression.getFactory());
    	ExpressionTypes.add(AbsExpression.getFactory());
    	ExpressionTypes.add(ClampExpression.getFactory());
    	ExpressionTypes.add(WrapExpression.getFactory());
    	ExpressionTypes.add(SinExpression.getFactory());
    	ExpressionTypes.add(CosExpression.getFactory());
    	ExpressionTypes.add(TanExpression.getFactory());
    	ExpressionTypes.add(AtanExpression.getFactory());
    	ExpressionTypes.add(LogExpression.getFactory());
    	ExpressionTypes.add(RgbToYCrCbExpression.getFactory());
    	ExpressionTypes.add(YCrCbtoRGBExpression.getFactory());
    	ExpressionTypes.add(PerlinColorExpression.getFactory());
    	ExpressionTypes.add(PerlinBWExpression.getFactory());
    	ExpressionTypes.add(SumExpression.getFactory());
    	ExpressionTypes.add(ProductExpression.getFactory());
    	ExpressionTypes.add(AverageExpression.getFactory());
    	ExpressionTypes.add(MaxExpression.getFactory());
    	ExpressionTypes.add(MinExpression.getFactory());
    	ExpressionTypes.add(ConditionalExpression.getFactory());
    }
    
    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression (String input, Parser parser) {
        myInput = input;
        myCurrentPosition = 0;
        makeExpressionList();
        Expression result = parseExpression(parser);
        skipWhiteSpace();
        if (notAtEndOfString()) {
            throw new ParserException("Unexpected characters at end of the string: " + myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }


    private Expression getExpressionType () {
    	skipWhiteSpace();
    	for (ExpressionFactory expression : ExpressionTypes) {
    		if (expression.isThisType(myInput, myCurrentPosition)) {
    			return expression.getExpression();
    		}
    	}
    	throw new ParserException("Invalid input");
    }
    	
    public Expression parseExpression (Parser parser) {
    	return getExpressionType().parseExpression(myInput, parser);
    }

    public void skipWhiteSpace() {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter())) {
            myCurrentPosition++;
        }
    }

    public char currentCharacter() {
        return myInput.charAt(myCurrentPosition);
    }

    private boolean notAtEndOfString() {
        return myCurrentPosition < myInput.length();
    }
}
