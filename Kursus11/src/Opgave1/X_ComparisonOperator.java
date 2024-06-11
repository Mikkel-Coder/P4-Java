/*
 * THIS FILE IS NOT A PART OF THE ASSIGNMENT
 * 
 * This enum is used in TrainCLI to convert user input to an operator
 */

package Opgave1;

public enum X_ComparisonOperator {
	GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS("=");
	
	private final String operator;

    X_ComparisonOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
		return operator;
	}
}
