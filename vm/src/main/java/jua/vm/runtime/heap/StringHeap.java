package jua.vm.runtime.heap;

public final class StringHeap implements CharSequence, Comparable<StringHeap> {

    private final StringBuilder buffer;

    private Integer cachedHashCode;

    public StringHeap() {
        buffer = new StringBuilder();
    }

    public StringHeap(int capacity) {
        buffer = new StringBuilder(capacity);
    }

    public StringHeap(CharSequence csq) {
        buffer = new StringBuilder(csq);
    }

    public int length() {
        return buffer.length();
    }

    @Override
    public char charAt(int index) {
        return buffer.charAt(index);
    }

    @Override
    public StringHeap subSequence(int start, int end) {
        return new StringHeap(buffer.subSequence(start, end));
    }

    @Override
    public int compareTo(StringHeap o) {
        if (this == o) return 0;
        StringBuilder buffer1 = buffer;
        StringBuilder buffer2 = o.buffer;
        int len = buffer1.length();
        for (int i = 0; i < len; i++) {
            char ch1 = buffer1.charAt(i);
            char ch2 = buffer2.charAt(i);
            if (ch1 != ch2) return (ch1 - ch2);
        }
        return len - buffer2.length();
    }

    public void trimToSize() {
        buffer.trimToSize();
    }

    public void appendLong(long l) {
        resetCaches();
        buffer.append(l);
    }

    public void appendDouble(double d) {
        resetCaches();
        buffer.append(d);
    }

    public void appendBoolean(boolean b) {
        resetCaches();
        buffer.append(b);
    }

    public void appendString(StringHeap s) {
        resetCaches();
        buffer.append(s.buffer);
    }

    public void appendNull() {
        resetCaches();
        buffer.append((StringBuffer) null);
    }

    private void resetCaches() {
        cachedHashCode = null;
    }

    public StringHeap cloneHeap() {
        return new StringHeap(buffer);
    }

    public boolean isSame(StringHeap another) {
        return (this == another) || buffer.length() == another.buffer.length() && compareTo(another) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isSame((StringHeap) o);
    }

    @Override
    public int hashCode() {
        if (cachedHashCode != null) return cachedHashCode;
        StringBuilder _buffer = buffer;
        int _cachedHashCode = 0;
        for (int i = 0, len = _buffer.length(); i < len; i++) {
            char ch = _buffer.charAt(i);
            _cachedHashCode = (_cachedHashCode << 5) - _cachedHashCode + (int) ch;
        }
        return cachedHashCode = _cachedHashCode;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
