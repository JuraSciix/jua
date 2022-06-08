package jua.vm.runtime.code;

import jua.vm.interpreter.Address;
import jua.vm.runtime.heap.StringHeap;

import java.util.ArrayList;
import java.util.List;

public final class ConstantPool {

    public static final class Builder {

        private final List<Address> entries = new ArrayList<>();

        public int addEntry(long l) {
            return putEntry(Address.valueOf(l));
        }

        public int addEntry(double d) {
            return putEntry(Address.valueOf(d));
        }

        public int addEntry(CharSequence csq) {
            StringHeap sh = new StringHeap(csq);
            return putEntry(Address.valueOf(sh));
        }

        public int addEntry(Address entry) {
            return putEntry(entry.copy());
        }

        int putEntry(Address entry) {
            int freeIdx = entries.size();
            entries.add(entry);
            return freeIdx;
        }

        public ConstantPool build() {
            Address[] array = new Address[entries.size()];
            entries.toArray(array);
            return new ConstantPool(array);
        }
    }

    private final Address[] entries;

    ConstantPool(Address[] entries) {
        this.entries = entries;
    }

    public int numEntries() {
        return entries.length;
    }

    public void load(int idx, Address dst) {
        Address src = entries[idx];
        dst.set(src);
    }
}
