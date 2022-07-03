package tony.bank.data.structure;

public class LinkedList<T> implements List<T> {
private Node<T> head;
	
	public LinkedList() {
		
	}
	
	public LinkedList(T... objs) {
		for (T obj : objs) {
			this.add(obj);
		}
	}

	
	
	@Override
	public void add(T obj) {
		Node<T> newNode = new Node<>(obj);
		if (this.head==null) {
			this.head = newNode;
		} else {
			Node<T> current = this.head;
			while (current.next!=null) {
				current = current.next;
			}
			current.next = newNode;
		}
	}

	
	
	@Override
	public void add(T t, int index) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void addAll(T... t) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public boolean contains(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public T remove(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	@Override
	public int indexOf(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	private class Node<T> {
		T value;
		Node<T> next;
		
		public Node(T value) {
			this.value = value;
		}
		
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
		
		
	}

}
