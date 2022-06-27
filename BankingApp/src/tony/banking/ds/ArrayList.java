package tony.banking.ds;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

// generics allow you to restrict types and check
// them at compile time, while also still having
// the flexibility of using different types with
// your classes
public class ArrayList<T> implements List<T>, Serializable {
	private T[] array;
	private int nextIndex = 0;
	
	public ArrayList() {
		array = (T[]) new Object[2];
	}
	
	// new ArrayList(myArr);
	// new ArrayList(myObj1, myObj2, myObj3);
	
	// varargs parameter (...) allows you to
	// pass in either an array or a comma-separated
	// list of values
	public ArrayList(T... objects) {
		array = objects;
	}

	@Override
	public void add(T obj) {
		// check if we need more space in the array
		if (this.nextIndex >= this.array.length) {
			// hang on to the old array
			T[] temp = this.array;
			// the new length will be 1.5 * the size of the old one
			int newLength = (int) Math.floor(this.nextIndex*1.5);
			// create the new array
			this.array = (T[]) new Object[newLength];
			// copy everything over from the old array
			for (int i = 0; i<temp.length; i++) {
				this.array[i] = temp[i];
			}
		}
		// add the new object
		this.array[nextIndex] = obj;
		nextIndex++;
	}

	@Override
	public T get(int index) {
		if (index>=0 && index < this.nextIndex) {
			return this.array[index];
		} else {
			throw new IndexOutOfBoundsException(index);
		}
	}

	@Override
	public T delete(int index) {
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
