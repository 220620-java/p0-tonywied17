package tony.bank.data.structures;

public interface List<T> {
	
	public void add(T t);
	
	public void add(T t, int index);
	
	public void addAll(T... t);
	
	public T get(int index);
	
	public int indexOf(T t);
	
	public boolean contains(T t);
	
	public T remove(T t);
	
	public T remove(int index);
	
	public int size();
}

