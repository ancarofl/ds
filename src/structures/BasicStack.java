package structures;

//Java class "Stack" uses Vector in the underlying implementation
//Java interface "Deque" is better
public class BasicStack<X> {
	private X[] data;
	private int stackPointer;
	private int max;

	//this is limited, replace array with ArrayList if max stack size unknown when instantiating
	public BasicStack(int maxStackSize) {
		data = (X[]) new Object[maxStackSize];
		stackPointer = 0;
		max = maxStackSize - 1;
	}

	// insert on top
	// O(1)
	public void push(X newItem) {
		if (stackPointer > max) {
			throw new IllegalStateException("Stack's full.");
		}
		data[stackPointer++] = newItem;
	}

	// delete/access top element
	// O(1)
	public X pop() {
		if (stackPointer == 0) {
			throw new IllegalStateException("Stack's empty.");
		}
		return data[--stackPointer];
	}

	// O(n)
	public boolean contains(X item) {
		for (int i = 0; i < stackPointer; i++) {
			if (data[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return stackPointer;
	}
}
