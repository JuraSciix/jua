package jua.vm.interpreter;

import jua.util.Preconditions;
import jua.vm.runtime.JuaTypes;
import jua.vm.runtime.heap.ArrayHeap;
import jua.vm.runtime.heap.IteratorHeap;
import jua.vm.runtime.heap.MapHeap;
import jua.vm.runtime.heap.StringHeap;

import static jua.vm.runtime.JuaTypes.*;

public final class Address {

    public static Address[] allocateMemory(int offset, int count) {
        // Check parameters:
        Preconditions.ensureTrue(offset >= 0, "offset is negative");
        Preconditions.ensureTrue(count >= 0, "count is negative");
        Preconditions.ensureTrue((offset + count) < Short.MAX_VALUE, ""); // todo: Come up with a message

        // Allocate the memory:
        Address[] memory = new Address[count];

        // Initialize the allocated memory:
        for (int i = offset; i < count; i++) {
            memory[i] = new Address();
        }

        return memory;
    }

    public static Address valueOf(long l) {
        Address address = new Address();
        address.setLong(l);
        return address;
    }

    public static Address valueOf(double d) {
        Address address = new Address();
        address.setDouble(d);
        return address;
    }

    public static Address valueOf(boolean b) {
        Address address = new Address();
        address.setBoolean(b);
        return address;
    }

    public static Address valueOf(StringHeap s) {
        Address address = new Address();
        address.setString(s);
        return address;
    }

    public static Address valueOf(ArrayHeap a) {
        Address address = new Address();
        address.setArray(a);
        return address;
    }

    public static Address valueOf(MapHeap t) {
        Address address = new Address();
        address.setMap(t);
        return address;
    }

    public static Address valueOf(IteratorHeap e) {
        Address address = new Address();
        address.setIterator(e);
        return address;
    }

    public int type = U;

    public long l;

    public double d;

    public Object a;

    /* The implicit constructor here */

    public int type() {
        return type;
    }

    public String typename() {
        return JuaTypes.name(type);
    }

    public long longVal() {
        return l;
    }

    public double doubleVal() {
        return d;
    }

    public boolean booleanVal() {
        return l != 0L;
    }

    public StringHeap stringVal() {
        return (StringHeap) a;
    }

    public ArrayHeap arrayVal() {
        return (ArrayHeap) a;
    }

    public MapHeap mapVal() {
        return (MapHeap) a;
    }

    public IteratorHeap iteratorVal() {
        return (IteratorHeap) a;
    }

    public void setLong(long _l) {
        type = L;
        l = _l;
    }

    public void setDouble(double _d) {
        type = D;
        d = _d;
    }

    public void setBoolean(boolean b) {
        type = B;
        l = (b ? 1L : 0L);
    }

    public void setString(StringHeap s) {
        type = S;
        a = s;
    }

    public void setArray(ArrayHeap _a) {
        type = A;
        a = _a;
    }

    public void setMap(MapHeap t) {
        type = T;
        a = t;
    }

    public void setIterator(IteratorHeap e) {
        type = E;
        a = e;
    }

    public void setNull() {
        type = N;
    }

    public void set(Address another) {
        type = another.type;
        if (another.isType(S)) {
            a = another.stringVal().cloneHeap();
        } else {
            l = another.l;
            d = another.d;
            a = another.a;
        }
    }

    public void clone(Address another) {
        type = another.type;

        switch (type) {
            case L: // long
            case B: // boolean
                l = another.l;
                break;
            case D: // double
                d = another.d;
                break;
            case S: // string
                a = another.stringVal().cloneHeap();
                break;
            case A: // array
                a = another.arrayVal().cloneHeap();
                break;
            case T: // map
                a = another.mapVal().cloneHeap();
                break;
            case E: // iterator
                a = another.iteratorVal().cloneHeap();
                break;
            case N: // null
                break;
        }
    }

    public void reset() {
        type = U;
        l = 0L;
        d = 0.0D;
        a = null;
    }

    public boolean isType(int _type) {
        return type == _type;
    }

    public boolean isSame(Address another) {
//        if (another == this) return true;
        switch (pair(type, another.type)) {
            case LL: // long == long
            case BB: // boolean == boolean
                return l == another.l;
            case LD: // long == double
                return l == another.d;
            case DL: // double == long
                return d == another.l;
            case DD: // double == double
                return d == another.d;
            case SS: // string == string
                return stringVal().isSame(another.stringVal());
            case AA: // array == array
                return arrayVal().isSame(another.arrayVal());
            case TT: // map == map
                return mapVal().isSame(another.mapVal());
            case EE: // iterator == iterator
                return iteratorVal().isSame(another.iteratorVal());
            case NN: // null == null
                return true;
            default:
                return false;
        }
    }

    public Address copy() {
        Address result = new Address();
        result.clone(this);
        return result;
    }

    @Override
    public int hashCode() {
        switch (type) {
            case L: // long
                return Long.hashCode(l);
            case D: // double
                return Double.hashCode(d);
            case B: // boolean
                return Boolean.hashCode(d != 0L);
            case S: // string
                return stringVal().hashCode();
            case A: // array
                return arrayVal().hashCode();
            case T: // map
                return mapVal().hashCode();
            case E: // iterator
                return iteratorVal().hashCode();
            case N: // null
            default:
                return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isSame((Address) o);
    }
}
