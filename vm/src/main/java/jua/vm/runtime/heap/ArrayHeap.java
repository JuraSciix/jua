package jua.vm.runtime.heap;

import jua.util.Preconditions;
import jua.vm.interpreter.Address;
import jua.vm.interpreter.InterpreterThread;
import jua.vm.runtime.JuaTypes;

import java.util.Arrays;

public final class ArrayHeap {

    public static void arraycopy(Address[] src, int srcOffset, Address[] dst, int dstOffset, int count) {
        Preconditions.ensureNotNull(src, "source array");
        Preconditions.ensureTrue(srcOffset >= 0 && (srcOffset + count) < src.length, "source offset out of bounds");
        Preconditions.ensureNotNull(dst, "destination array");
        Preconditions.ensureTrue(dstOffset >= 0 && (dstOffset + count) < dst.length, "destination offset out of bounds");
        Preconditions.ensureTrue(count >= 0, "count is negative");

        for (int i = 0; i < count; i++) dst[dstOffset + i].set(src[srcOffset + i]);
    }

    private Address[] elements;

    private int top = 0;

    public ArrayHeap() {
        elements = Address.allocateMemory(0, 1);
    }

    public ArrayHeap(int initialCapacity) {
        Preconditions.ensureTrue(initialCapacity >= 0, "initial capacity is negative");
        elements = Address.allocateMemory(0, initialCapacity);
    }

    public ArrayHeap(Address... elements) {
        Preconditions.ensureNotNull(elements, "elements");
        this.elements = Address.allocateMemory(0, elements.length);
        for (int i = 0; i < elements.length; i++) this.elements[i].set(elements[i]);
    }

    // size
    // isEmpty
    // nonEmpty
    // get
    // set
    // insert
    // remove
    // push
    // pop
    // shift
    // unshift
    // peek
    // peekFirst
    // peekLast
    // subarray
    // cloneHeap

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return (top == 0);
    }

    public boolean nonEmpty() {
        return !isEmpty();
    }

    public Address get(Address index) {
        int real_index = calculateRealIndex(index);

        if (real_index < 0) {
            // Illegal index passed.
            return null;
        }

        return elements[real_index];
    }

    public boolean set(Address index, Address value) {
        int real_index = calculateRealIndex(index);
        Preconditions.ensureNotNull(value, "value address");

        if (real_index < 0) {
            // Illegal index passed.
            return false;
        }

        if (real_index == top) {
            addLast(value);
        } else {
            elements[real_index].set(value);
        }
        return true;
    }

    public boolean insert(Address index, Address value) {
        int real_index = calculateRealIndex(index);

        if (real_index < 0) {
            // Illegal index passed.
            return false;
        }

        insertInternal(real_index, value);
        return true;
    }

    private int calculateRealIndex(Address index) {
        Preconditions.ensureNotNull(index, "index address");
        long real_index;

        if (index.isType(JuaTypes.L)) {
            real_index = index.l;
        } else if (index.isType(JuaTypes.D)) {
            real_index = (long) index.d;
        } else {
            InterpreterThread.currentThread().error("%s cannot be a index of array", index.typename());
            return -1;
        }

        if (real_index < 0L || real_index > (long) top) {
            InterpreterThread.currentThread().error("index %d out of bounds", real_index);
            return -1;
        }

        // real_index <= top <= 2^31, so we can cast real_index to int.
        return (int) real_index;
    }

    public void addLast(Address value) {
        growIfRequired();
        int t = top;
        System.out.println(Arrays.toString(elements));
        elements[t].set(value);
        top = t + 1;
    }

    public boolean pollLast(Address output) {
        if (isEmpty()) {
            InterpreterThread.currentThread().error("cannot pop element from empty array");
            return false;
        }
        int t = top - 1;
        output.set(elements[t]);
        top = t;
        return true;
    }

    public boolean peekLast(Address output) {
        if (isEmpty()) {
            InterpreterThread.currentThread().error("cannot peek last element inempty array");
            return false;
        }
        int t = top - 1;
        output.set(elements[t]);
        return true;
    }

    public void ensureNotEmpty() {
        if (isEmpty()) {
            InterpreterThread.currentThread().error("array is empty");
        }
    }

    public void unshift(Address value) {
        growIfRequired();
        elements[0].set(value);
        top++;
    }

    public boolean shift(Address output) {
        if (isEmpty()) {
            InterpreterThread.currentThread().error("cannot shift element from empty array");
            return false;
        }

        output.set(elements[0]);
        top--;
        arraycopy(elements, 1, elements, 0, top);
        return true;
    }

    private void insertInternal(int index, Address value) {
        growIfRequired();
        arraycopy(elements, index, elements, index + 1, top - index);
        elements[index].set(value);
        top++;
    }

    private void growIfRequired() {
        if (top >= elements.length) {
            Address[] doubled = Address.allocateMemory(top, top);
            System.arraycopy(elements, 0, doubled, 0, top + 1);
            elements = doubled;
        }
    }

    public ArrayHeap cloneHeap() {
        return new ArrayHeap(elements);
    }

    public Address[] toArray() {
        return Arrays.copyOf(elements, top);
    }

    public Address[] toArray(Address[] a) {
        System.arraycopy(elements, 0, a, 0, Math.min(top, a.length));
        return a;
    }

    public boolean isSame(ArrayHeap another) {
        return Arrays.equals(elements, another.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isSame((ArrayHeap) o);
    }
}
