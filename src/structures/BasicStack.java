package structures;

import interfaces.CustomStackInterface;

//Java class "Stack" uses Vector in the underlying implementation
//Java interface "Deque" is better
public class BasicStack<X> implements CustomStackInterface<X> {
	private X[] data;
	private int stackPointer;
	private int max;

	// this is limited, replace array with ArrayList if max stack size unknown when
	// instantiating
	public BasicStack(int maxStackSize) {
		data = (X[]) new Object[maxStackSize];
		stackPointer = 0;
		max = maxStackSize - 1;
	}

	// main ops
	// insert on top
	// O(1)
	@Override
	public void push(X newItem) {
		if (isFull()) {
			throw new IllegalStateException("Stack's full.");
		}
		data[stackPointer++] = newItem;
	}

	// delete top element
	// O(1)
	@Override
	public X pop() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack's empty.");
		}
		return data[--stackPointer];
	}

	// O (1)
	@Override
	public X peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack's empty.");
		}
		return data[stackPointer - 1];
	}

	// O(n)
	@Override
	public boolean contains(X item) {
		for (int i = 0; i < stackPointer; i++) {
			if (data[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return stackPointer == 0;
	}

	@Override
	public boolean isFull() {
		return stackPointer > max;
	}

	@Override
	public int size() {
		return stackPointer;
	}

	public BasicStack<X> deepCopy() {
		BasicStack<X> newStack = new BasicStack<X>(max + 1);
		newStack.stackPointer = stackPointer;
		for (int i = 0; i < stackPointer; i++) {
			newStack.data[i] = data[i];
		}
		return newStack;
	}
	
	public void printBasicStack() {
		for (int i = 0; i < stackPointer; i++) {
			System.out.print(data[i] + "; ");
		}
	}
	
	// TODO: override equals and hashCode
}
