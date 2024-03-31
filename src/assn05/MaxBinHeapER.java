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
        if(size() == 0 ){
            return null;
        } 
        Prioritized<V,P> root = _heap.get(0);
        if(size() == 1) {
            return _heap.remove(0).getValue();
        }
        else{
            Prioritized<V,P> successor = _heap.get(size()-1);
            _heap.set(0, successor);
            _heap.remove(size()-1);
            int parentIndex = 0;
            int leftChildIndex = 2*parentIndex + 1;
            int rightChildIndex = 2*parentIndex + 2;
            while(leftChildIndex < size()){ //Check if valid children
                leftChildIndex = 2*parentIndex + 1;
                rightChildIndex = 2*parentIndex + 2;
                //Only left child
                if(rightChildIndex >= size() && _heap.get(leftChildIndex).compareTo(_heap.get(parentIndex)) > 0){ //left child is greater than parent
                    Prioritized<V,P> temp = _heap.get(parentIndex);
                    _heap.set(parentIndex, _heap.get(leftChildIndex));
                    _heap.set(leftChildIndex, temp);
                    parentIndex = leftChildIndex;
                }
                //Both children
                else if(leftChildIndex < size() && rightChildIndex < size()){ //Valid children
                    if(_heap.get(leftChildIndex).compareTo(_heap.get(rightChildIndex)) > 0){ //left child is greater than right child
                        if(_heap.get(leftChildIndex).compareTo(_heap.get(parentIndex)) > 0){ //left child is greater than parent
                            Prioritized<V,P> temp = _heap.get(parentIndex);
                            _heap.set(parentIndex, _heap.get(leftChildIndex));
                            _heap.set(leftChildIndex, temp);
                            parentIndex = leftChildIndex;
                        }
                    }
                    else if(_heap.get(rightChildIndex).compareTo(_heap.get(leftChildIndex)) > 0){ //right child is greater than left child
                        if (_heap.get(rightChildIndex).compareTo(_heap.get(parentIndex)) > 0){ //right child is greater than parent
                            Prioritized<V,P> temp = _heap.get(parentIndex);
                            _heap.set(parentIndex, _heap.get(rightChildIndex));
                            _heap.set(rightChildIndex, temp);
                            parentIndex = rightChildIndex;
                        }
                    }
                } else break;
            }
        }
         return root.getValue();
    }

    // TODO (Task 2A): getMax
    //return the largest value, not priority, in the heap without removing it
    @Override
    public V getMax() {
        if(size() == 0){
            return null;
        }
        return _heap.get(0).getValue();
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Prioritized<V,P> p : _heap) {
            sb.append(p.getValue() + " ");
        }
        return sb.toString();
    }
}
