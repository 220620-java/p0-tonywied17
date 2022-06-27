package wiedman.tony.bank.ds;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList<T> implements List<T>, Serializable {
	private T[] array;
	private int nextIndex = 0;
	
	public ArrayList() {
		array = (T[]) new Object[2];
	}

	
	public ArrayList(T... objects) {
		array = objects;
	}

	
	@Override
	public void addUser(T obj) {
		if (this.nextIndex >= this.array.length) {
			
			T[] temp = this.array;
			int newLength = (int) Math.floor(this.nextIndex*1.5);
			this.array = (T[]) new Object[newLength];

			for (int i = 0; i<temp.length; i++) {
				this.array[i] = temp[i];
			}
			
		}
		//add it
		this.array[nextIndex] = obj;
		nextIndex++;
	}

	@Override
	public T getUsers(int index) {
		if (index>=0 && index < this.nextIndex) {
			return this.array[index];
		} else {
			throw new IndexOutOfBoundsException(index);
		}
	}

	@Override
	public T deleteUser(int index) {
		// TODO might want to make the array size smaller if enough
		// elements have been deleted
		if (index>=0 && index < this.array.length) {
			T obj = this.array[index];
			// shift everything over
			for (int i=index; i<this.array.length-1; i++) {
				this.array[i] = this.array[i+1];
			}
			// shift the last item over
			this.array[this.array.length-1]=null;
			this.nextIndex--;
			return obj;
		} else {
			throw new IndexOutOfBoundsException(index);
		}
	}

	@Override
	public int indexOf(T obj) {
		for (int i = 0; i<this.nextIndex; i++) {
			if (obj==null && this.array[i]==null) {
				return i;
			}
			if (this.array[i]!=null && this.array[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

	
}
