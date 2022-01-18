import java.lang.reflect.Array;

public class DynamicArray<T> {
    Class<T> clazz;
    private T[] items;
    private int size;

    public DynamicArray(Class<T> clazz) {
        this.clazz = clazz;
        this.size = 0;
        this.items = createEmptyArray(0);
    }

    private void checkRange(int i) {
        if (i < 0 || size <= i)
            throw new IndexOutOfBoundsException();
    }

    private T[] createEmptyArray(int capacity) {
        return (T[]) Array.newInstance(this.clazz, capacity);
    }

    public int length() {
        return this.size;
    }

    public T getItem(int i) {
        checkRange(i);
        return this.items[i];
    }

    public void setItem(int i, T item) {
        checkRange(i);
        this.items[i] = item;
    }

    public void addItem(T item) {
        T[] newArr = createEmptyArray(this.items.length + 1);
        for (int i = 0; i < this.items.length; i++) {
            newArr[i] = this.items[i];
        }
        this.items = newArr;
        this.items[this.size] = item;
        this.size++;
    }

    public void printNumericalArray(){
        for (int i=0; i<items.length; i++){
            System.out.println((i + 1) + ". " + items[i]);
        }
    }
}
