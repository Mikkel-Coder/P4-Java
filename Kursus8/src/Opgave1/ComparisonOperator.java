/* THIS FILE IS NOT A PART OF THE ASSIGMENT */

package Opgave1;

public enum ComparisonOperator {
	GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS("=");
	
	private final String operator;

    ComparisonOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
