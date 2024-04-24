public class Move {
    private int HIndex;
    private int numObjectsRemoved;

    public Move(int heapIndex, int numObjectsRemoved) {
        this.HIndex = heapIndex;
        this.numObjectsRemoved = numObjectsRemoved;
    }

    public int getHeapIndex() {
        return HIndex;
    }

    public void setHeapIndex(int heapIndex) {
        this.HIndex = heapIndex;
    }

    public int getNumObjectsRemoved() {
        return numObjectsRemoved;
    }

    public void setNumObjectsRemoved(int numObjectsRemoved) {
        this.numObjectsRemoved = numObjectsRemoved;
    }
}