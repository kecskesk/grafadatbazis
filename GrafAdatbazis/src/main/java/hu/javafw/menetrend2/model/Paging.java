package hu.javafw.menetrend2.model;

/**
 * Helper class for paging attributes.
 *
 * @author kkrisz
 */
public class Paging {
    public int max;
    public int first;

    public Paging() {
    }

    public Paging(int max, int first) {
        this.max = max;
        this.first = first;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }
}
