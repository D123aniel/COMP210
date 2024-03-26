package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO (Task 2A): enqueue
    public void enqueue(V value) {
        Patient newPatient = new Patient(value);
        _heap.add(newPatient);
        int lastIndex = _heap.size()-1;
        int parentIndex = (lastIndex-1)/2;
        while(parentIndex >= 0 && _heap.get(lastIndex).compareTo(_heap.get(parentIndex)) > 0) {
            Prioritized<V,P> temp = _heap.get(lastIndex);
            _heap.set(lastIndex, _heap.get(parentIndex));
            _heap.set(parentIndex, temp);
            lastIndex = parentIndex;
            parentIndex = (lastIndex-1)/2;
        }
    }

    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient newPatient = new Patient(value, priority);
        _heap.add(newPatient);
        int lastIndex = _heap.size()-1;
        int parentIndex = (lastIndex-1)/2;
        while(parentIndex >= 0 && _heap.get(lastIndex).compareTo(_heap.get(parentIndex)) > 0) {
            Prioritized<V,P> temp = _heap.get(lastIndex);
            _heap.set(lastIndex, _heap.get(parentIndex));
            _heap.set(parentIndex, temp);
            lastIndex = parentIndex;
            parentIndex = (lastIndex-1)/2;
        }
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
         return null;
    }

    // TODO (Task 2A): getMax
    @Override
    public V getMax() {
    	 return null;
    }

    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

}
