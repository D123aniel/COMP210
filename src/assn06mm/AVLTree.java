package assn06mm;

import static java.lang.Math.max;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        AVLTree<T> rightChild = _right;
        AVLTree<T> leftChild = rightChild._left;

        // Update sizes
        int leftSize = leftChild == null ? 0 : leftChild.size();
        int rightRightSize = rightChild._right == null ? 0 : rightChild._right.size();
        rightChild._size = _size;
        _size = leftSize + 1 + rightRightSize;

        // Perform rotation
        rightChild._left = this;
        _right = leftChild;

        // Update heights
        _height = Math.max(_left == null ? -1 : _left._height, _right == null ? -1 : _right._height) + 1;
        rightChild._height = Math.max(rightChild._right == null ? -1 : rightChild._right._height, _height) + 1;

        return rightChild;
    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateRight() {
        AVLTree<T> leftChild = _left;
        AVLTree<T> rightChild = leftChild._right;

        // Update sizes
        int rightSize = rightChild == null ? 0 : rightChild.size();
        int leftLeftSize = leftChild._left == null ? 0 : leftChild._left.size();
        leftChild._size = _size;
        _size = rightSize + 1 + leftLeftSize;

        // Perform rotation
        leftChild._right = this;
        _left = rightChild;

        // Update heights
        _height = Math.max(_left == null ? -1 : _left._height, _right == null ? -1 : _right._height) + 1;
        leftChild._height = Math.max(leftChild._left == null ? -1 : leftChild._left._height, _height) + 1;

        return leftChild;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override

    public SelfBalancingBST<T> insert(T element) {
        if (isEmpty()) {
            _value = element;
            _left = new AVLTree<T>();
            _right = new AVLTree<T>();
            _height = 0;
            _size = 1;
            return this;
        }

        int compareResult = element.compareTo(_value);

        if (compareResult < 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (compareResult > 0) {
            _right = (AVLTree<T>) _right.insert(element);
        }

        _height = max(_left._height, _right._height) + 1;
        _size = _left.size() + _right.size() + 1;

        int balance = getBalanceFactor(this);

        if (balance < -1) {
            if (element.compareTo(_right._value) < 0) {
                _right = _right.rotateRight();
                return rotateLeft();
            } else if (element.compareTo(_right._value) > 0) {
                return rotateLeft();
            }
        } else if (balance > 1) {
            if (element.compareTo(_left._value) > 0) {
                _left = _left.rotateLeft();
                return rotateRight();
            } else if (element.compareTo(_left._value) < 0) {
                return rotateRight();
            }
        }


        return this;
    }

    public int getBalanceFactor(AVLTree<T> tree){
        return tree._left._height - tree._right._height;
    }
    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (isEmpty()) {
            return this;
        }

        int cmp = element.compareTo(_value);

        if (cmp < 0) {
            _left = (AVLTree<T>) _left.remove(element);
        } else if (cmp > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        }
        else {
            if (_left.isEmpty()) {
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            }
            // Node with two children: replace with minimum from right subtree
            T successorValue = _right.findMin();
            _value = successorValue;
            _right = (AVLTree<T>) _right.remove(successorValue);
            _size = _left.size() + _right.size() + 1;
            return this;
        }


        _height = max(_left._height, _right._height) + 1;
        _size = _left.size() + _right.size() + 1;

        int balance = getBalanceFactor(this);
        if (balance < -1) {
            if (getBalanceFactor(_right) <= 0) {
                return rotateLeft();
            }
            if (getBalanceFactor(_right) > 0) {
                _right = _right.rotateRight();
                return rotateLeft();
            }
        } else if (balance > 1) {
            if (getBalanceFactor(_right) >= 0) {
                return rotateRight();
            }
            if (getBalanceFactor(_right) < 0) {
                _left = _left.rotateLeft();
                return rotateRight();
            }
        }

        return this;
    }

    @Override
    public T findMin() {
        T min;
        if(!_left.isEmpty()){
            min = _left.findMin();
        } else {
            return _value;
        }
        return min;
    }

    @Override
    public T findMax() {
        T max;
        if(!_right.isEmpty()){
            max = _right.findMax();
        } else {
            return _value;
        }
        return max;
    }

    @Override
    public boolean contains(T element) {
        boolean value = false;
        if(element.compareTo(_value) > 0){
            if(!_right.isEmpty()) {
                value = _right.contains(element);
            }
        }

        if(element.compareTo(_value) < 0) {
            if (_left.isEmpty()) {
                value = false;
            } else {
                value = _left.contains(element);
            }
        }

        if(element == _value){
            return true;
        }

        return value;
    }

    @Override
    public boolean rangeContain(T start, T end) {
        if (isEmpty()) {
            return false;
        }

        if (_value.compareTo(start) >= 0 && _value.compareTo(end) <= 0) {
            return true;
        }

        boolean leftContains = _left != null && _left.rangeContain(start, end);
        boolean rightContains = _right != null && _right.rangeContain(start, end);

        return leftContains || rightContains;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        return _right;
    }
}
