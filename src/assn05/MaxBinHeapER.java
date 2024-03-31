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
            bubbleDown(0);
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

    public void bubbleUp(int index){
        int parentIndex = (index-1)/2;
        while(parentIndex >= 0 && _heap.get(index).compareTo(_heap.get(parentIndex)) > 0) {
            Prioritized<V,P> temp = _heap.get(index);
            _heap.set(index, _heap.get(parentIndex));
            _heap.set(parentIndex, temp);
            index = parentIndex;
            parentIndex = (index-1)/2;
        }
    }

    public void bubbleDown(int index){
        int leftChildIndex = 2*index + 1;
        int rightChildIndex = 2*index + 2;
        while(leftChildIndex < size()){ //Check if valid children
            System.out.println("bruh");
            System.out.println("leftChildIndex: " + leftChildIndex + " rightChildIndex: " + rightChildIndex + " size: " + size() + " index: " + index);
            
            
            //Issue with right child index right now
            //Only left child
            
            // if(_heap.get(index).compareTo(_heap.get(leftChildIndex)) > 0 && _heap.get(index).compareTo(_heap.get(rightChildIndex)) > 0){ //parent is greater than children
            //     break;
            // }
            if(rightChildIndex >= size() && _heap.get(leftChildIndex).compareTo(_heap.get(index)) > 0){ //left child is greater than parent error here
                Prioritized<V,P> temp = _heap.get(index);
                _heap.set(index, _heap.get(leftChildIndex));
                _heap.set(leftChildIndex, temp);
                index = leftChildIndex;
            }
            //Both children
            else if(rightChildIndex >= size()){
                break;
            }
            else if(leftChildIndex < size() && rightChildIndex < size()){ //Valid children
                if(_heap.get(leftChildIndex).compareTo(_heap.get(rightChildIndex)) > 0){ //left child is greater than right child
                    if(_heap.get(leftChildIndex).compareTo(_heap.get(index)) > 0){ //left child is greater than parent
                        Prioritized<V,P> temp = _heap.get(index);
                        _heap.set(index, _heap.get(leftChildIndex));
                        _heap.set(leftChildIndex, temp);
                        index = leftChildIndex;
                    }
                }
                else if(_heap.get(rightChildIndex).compareTo(_heap.get(leftChildIndex)) > 0){ //right child is greater than left child
                    if (_heap.get(rightChildIndex).compareTo(_heap.get(index)) > 0){ //right child is greater than parent
                        Prioritized<V,P> temp = _heap.get(index);
                        _heap.set(index, _heap.get(rightChildIndex));
                        _heap.set(rightChildIndex, temp);
                        index = rightChildIndex;
                    }
                }
            } else break;
            leftChildIndex = 2*index + 1;
            rightChildIndex = 2*index + 2;
        }
    }

    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
        if (size() == 0){
            return;
        }
        else if (size() == 1){
            if(_heap.get(0).getValue().equals(value)){
                _heap.set(0, new Patient<V, P>(value, newPriority)) ;
            }
        }else{
            int index = 0;
            for(int i = 0; i < size(); i++){
                if(_heap.get(i).getValue().equals(value)){
                    _heap.set(i, new Patient<V, P>(value, newPriority));
                    index = i;
                    break;
                }
            }
            //If the index priority is greater than parent, bubble up
            int parentIndex = (index-1)/2;
            //Priority is correct
            if(_heap.get(index).compareTo(_heap.get(parentIndex)) < 0 && _heap.get(index).compareTo(_heap.get(2*index + 1)) > 0 && _heap.get(index).compareTo(_heap.get(2*index + 2)) > 0){
                return;
            }
            //Priority is greater than parent, bubble up
            else if(_heap.get(index).compareTo(_heap.get(parentIndex)) > 0){
                bubbleUp(index);
            }
            //Priority is less than children, bubble down
            else{
                bubbleDown(index);
            }
            

        }
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for(int i=0;i<initialEntries.length;i++){
            enqueue(initialEntries[i].getValue(), initialEntries[i].getPriority());
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Prioritized<V,P> p : _heap) {
            sb.append(p.getValue() + ":"+ p.getPriority() + " ");
        }
        return sb.toString();
    }
}
