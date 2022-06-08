package jua.vm.runtime.code;

import jua.util.Preconditions;

public final class LineNumberTable {

    private final short[] codePoints;

    private final int[] lineNumbers;

    public LineNumberTable(short[] codePoints, int[] lineNumbers) {
        Preconditions.ensureNotNull(codePoints, "code points");
        Preconditions.ensureNotNull(lineNumbers, "line numbers");
        Preconditions.ensureTrue(codePoints.length == lineNumbers.length, "difference array lengths");
        this.codePoints = codePoints;
        this.lineNumbers = lineNumbers;
    }

    public int lineNumberOf(int codePoint) {
        short[] _codePoints = codePoints;

        int f = 0;                  // from
        int t = _codePoints.length; // to
        int c = (t >> 1);           // center

        while ((t - f) > 1) {
            int cp = _codePoints[c] & 0xffff;
            if (codePoint >= cp) {
                f = c;
            } else {
                t = c;
            }
            c = (t + f) >> 1;
        }

        return lineNumbers[c];
    }
}
