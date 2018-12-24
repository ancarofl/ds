package solution;

import structures.BasicStack;

public class Solution {
	public static void main(String[] args) {
		//
		/*
		 * if (areParanthesesBalanced("[[][]()")) { System.out.println("Balanced."); }
		 * else { System.out.println("Not balanced."); }
		 */
		//
		System.out.println("Infix: (A+B)*C(D/(J+D)), Postfix: " + infixToPostfix("(A+B)*C(D/(J+D))"));
	}

	public static boolean areParanthesesBalanced(String expression) {
		int len = expression.length();
		if (len == 0) {
			throw new IllegalArgumentException("You need to input an expression to be checked.");
		}
		BasicStack<Character> charStack = new BasicStack<Character>(len);
		for (int i = 0; i < len; i++) {
			char c = expression.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				charStack.push(c);
			} else if (charStack.isEmpty()) {
				return false;
			} else if (c == ')') {
				if (charStack.pop() != '(') {
					return false;
				}
			} else if (c == ']') {
				if (charStack.pop() != '[') {
					return false;
				}
			} else if (c == '}') {
				if (charStack.pop() != '{') {
					return false;
				}
			}
		}
		return charStack.isEmpty();
	}

	public static boolean hasHigherSamePrecedence(Character cs, Character c) {
		if (cs != '+' && cs != '-' && cs != '*' && cs != '/' && cs != '^') {
			return false;
		}
		if (c == cs || c == '+' || c == '-') {
			return true;
		} else if (c == '*' || c == '/') {
			if (cs == '-' || cs == '+') {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public static String infixToPostfix(String expression) {
		int len = expression.length();
		if (len == 0) {
			throw new IllegalArgumentException("You need to input an expression to be checked.");
		}
		String postfixExpression = "";
		BasicStack<Character> postfixStack = new BasicStack<Character>(len);
		// 1. Push "(" onto Stack, and add ")" to the end of X.
		postfixStack.push('(');
		expression += ')';
		len++;
		// 2. Scan X from left to right and repeat Step 3 to 6 for each element of X
		// until the Stack is empty.
		for (int i = 0; i < len; i++) {
			char c = expression.charAt(i);
			// 3. If an operand is encountered, add it to Y.
			if (Character.isLetterOrDigit(c)) {
				postfixExpression += c;
			}
			// 4. If a left parenthesis is encountered, push it onto Stack.
			else if (c == '(') {
				postfixStack.push(c);
			}
			// 5. If an operator is encountered, then:
			// 1. Repeatedly pop from Stack and add to Y each operator (on the top of Stack)
			// which has the same precedence as or higher precedence than operator.
			// 2. Add operator to Stack.
			else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
				while (!postfixStack.isEmpty() && hasHigherSamePrecedence(postfixStack.peek(), c)) {
					postfixExpression += postfixStack.pop();
				}
				postfixStack.push(c);
			}
			// 6. If a right parenthesis is encountered ,then:
			// 1. Repeatedly pop from Stack and add to Y each operator (on the top of Stack)
			// until a left parenthesis is encountered.
			// 2. Remove the left Parenthesis.
			else if (c == ')') {
				while (!postfixStack.isEmpty()) {
					Character temp = postfixStack.peek();
					if (temp == '+' || temp == '-' || temp == '*' || temp == '/' || temp == '^') {
						postfixExpression += postfixStack.pop();
					} else if (temp == '(') {
						postfixStack.pop();
						break;
					}
				}
			}

		}
		return postfixExpression;
	}
}
