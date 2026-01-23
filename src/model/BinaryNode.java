package model;

/**
 *
 * @author MikelMZ : Miguel Armas
 */
public class BinaryNode {
    private int index;
    private int value;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode(int index, int value, BinaryNode left, BinaryNode right) {
        this.index = index;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(int index, int value) {
        this.index = index;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Nodo{" + "id=" + index + ", valor=" + value + '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
