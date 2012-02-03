package model;

public class ExpressionFactory {
	Expression myExpression;
	
	public ExpressionFactory(Expression expression) {
		myExpression = expression;
	}
	
	public boolean isThisType(String myInput, int myCurrentPosition) {
		return myExpression.isThisType(myInput, myCurrentPosition);
	}
	
	public Expression getExpression() {
		return myExpression;
	}
}
