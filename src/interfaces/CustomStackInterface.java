package interfaces;

public interface CustomStackInterface<X> {
	public void push(X newItem);
	public X pop();
	public X peek();
	public boolean contains(X item);
	public boolean isEmpty();
	public boolean isFull();
	public int size();
}
