package wiedman.tony.bank.ds;

public interface List<T> {
	
	public void addUser (T obj);
	public T getUsers (int index);
	public T deleteUser (int index);
	public int indexOf (T obj);

	
}
