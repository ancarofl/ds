package solution;

import java.util.ArrayList;

import structures.BasicStack;

public class Solution {
	public static void main(String[] args) {
		//
		/*
		 * if (areParanthesesBalanced("[[][]()")) { System.out.println("Balanced."); }
		 * else { System.out.println("Not balanced."); }
		 */
		//
		// System.out.println("Infix: (A+B)*C(D/(J+D)), Postfix: " +
		// infixToPostfix("(A+B)*C(D/(J+D))"));
		//
		hanoi(6);
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

	// TODO: test recursive
	// TODO: optimize this
	// TODO: reformat this
	public static void hanoi(int diskNumber) {
		// a = src, b = aux, c = dst, swap b and c if disk number is even
		BasicStack<Integer> a = new BasicStack<Integer>(diskNumber);
		BasicStack<Integer> b = new BasicStack<Integer>(diskNumber);
		BasicStack<Integer> c = new BasicStack<Integer>(diskNumber);
		for (int i = 1; i <= diskNumber; i++) {
			a.push(i);
		}
		int total = (int) (Math.pow(2, diskNumber) - 1);
		int temp = 0;
		int temp2 = 0;
		for (int i = 1; i <= total; i++) {
			// src -> dst
			if (i % 3 == 1) {
				if (!a.isEmpty()) {
					temp = a.peek();
					if (!c.isEmpty()) {
						temp2 = c.peek();
						if (temp > temp2) {
							System.out.println("Moving " + a.peek() + " to C.");
							c.push(a.pop());
						} else {
							System.out.println("Moving " + c.peek() + " to A.");
							a.push(c.pop());
						}
					} else {
						System.out.println("Moving " + a.peek() + " to C.");
						c.push(a.pop());
					}
				} else {
					System.out.println("Moving " + c.peek() + " to A.");
					a.push(c.pop());
				}
			}
			// src -> aux
			else if (i % 3 == 2) {
				if (!a.isEmpty()) {
					temp = a.peek();
					if (!b.isEmpty()) {
						temp2 = b.peek();
						if (temp > temp2) {
							System.out.println("Moving " + a.peek() + " to B.");
							b.push(a.pop());
						} else {
							System.out.println("Moving " + b.peek() + " to A.");
							a.push(b.pop());
						}
					} else {
						System.out.println("Moving " + a.peek() + " to B.");
						b.push(a.pop());
					}
				} else {
					System.out.println("Moving " + b.peek() + " to A.");
					a.push(b.pop());
				}
			} 
			// aux -> dst
			else if (i % 3 == 0) {
				if (!b.isEmpty()) {
					temp = b.peek();
					if (!c.isEmpty()) {
						temp2 = c.peek();
						if (temp > temp2) {
							System.out.println("Moving " + b.peek() + " to C.");
							c.push(b.pop());
						} else {
							System.out.println("Moving " + c.peek() + " to B.");
							b.push(c.pop());
						}
					} else {
						System.out.println("Moving " + b.peek() + " to C.");
						c.push(b.pop());
					}
				} else {
					System.out.println("Moving " + c.peek() + " to B.");
					b.push(c.pop());
				}
			}
		}
		// TODO: replace ArrayList?
		ArrayList<BasicStack<Integer>> result = new ArrayList<BasicStack<Integer>>(3);
		result.add(a);
		result.add(b);
		result.add(c);
		for (int i = 0; i < 3; i++) {
			System.out.print("Stack " + (int) (i + 1) + ": ");
			result.get(i).printBasicStack();
			System.out.println();
		}
	}
}
