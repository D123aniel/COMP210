package assn06;

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

    public AVLTree(T element) {
        _value = element;
        _left = new AVLTree<T>();
        _right = new AVLTree<T>();
        _height = 0;
        _size = 1;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        AVLTree<T> newRoot = _right;
        AVLTree<T> leftNode = newRoot._left;

        int leftSize = leftNode == null ? 0 : leftNode.size();
        int rightSize = newRoot._right == null ? 0 : newRoot._right.size();
        newRoot._size = _size;
        _size = leftSize + rightSize + 1;

        newRoot._left = this;
        _right = leftNode;

        int left = _left == null ? -1 : _left.height();
        int right = _right == null ? -1 : _right.height();
        _height = Math.max(left, right) + 1;

        int rightright = newRoot._right == null ? -1 : newRoot._right.height();
        int rightleft = _height;
        newRoot._height = Math.max(rightleft, rightright) + 1;

        return newRoot;

    }
    
    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
        AVLTree<T> newRoot = _left;
        AVLTree<T> rightNode = newRoot._right;

        int rightSize = rightNode == null ? 0 : rightNode.size();
        int leftSize = newRoot._left == null ? 0 : newRoot._left.size();
        newRoot._size = _size;
        _size = leftSize + rightSize + 1;

        newRoot._right = this;
        _left = rightNode;

        int left = _left == null ? -1 : _left.height();
        int right = _right == null ? -1 : _right.height();
        _height = Math.max(left, right) + 1;

        int leftleft = newRoot._left == null ? -1 : newRoot._left.height();
        int leftright = _height;
        newRoot._height = Math.max(leftleft, leftright) + 1;

        return newRoot;
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
        if(isEmpty()){
            return new AVLTree<T>(element);
        }

        if(element.compareTo(_value) < 0){
            _left = (AVLTree<T>) _left.insert(element);
        }else if(element.compareTo(_value) > 0){
            _right = (AVLTree<T>) _right.insert(element);
        }else return this;
        
        _height = Math.max(_left._height, _right._height) + 1;
        _size = _left.size() + _right.size() + 1;
        
        int balance = balanceFactor();

        //Left Left
        if(balance > 1 && element.compareTo(_left._value) < 0){
            return rotateRight();
        }
        //Right Right
        if(balance < -1 && element.compareTo(_right._value) > 0){
            return rotateLeft();
        }

        //Left Right
        if(balance > 1 && element.compareTo(_left._value) > 0){
            _left = _left.rotateLeft();
            return rotateRight();
        }
        //Right Left
        if(balance < -1 && element.compareTo(_right._value) < 0){
            _right = _right.rotateRight();
            return rotateLeft();
        }
        return this;

    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if(isEmpty()){
            return this;
        }

        if(element.compareTo(_value)<0){
            _left = (AVLTree<T>) _left.remove(element);
        }else if(element.compareTo(_value)>0){
            _right = (AVLTree<T>) _right.remove(element);
        }
        else{
            if(_right.isEmpty()){
                return _left;
            }else if(_left.isEmpty()){
                return _right;
            }

            T successor = _right.findMin();
            _value = successor;
            _right = (AVLTree<T>) _right.remove(successor);
            _size = _left.size() + _right.size() + 1;
            return this;
        }
        _height = Math.max(_left._height, _right._height) + 1;
        _size = _left.size() + _right.size() + 1;

        int balance = balanceFactor();

        //Left Left
        if(balance > 1 && element.compareTo(_left._value) < 0){
            return rotateRight();
        }
        //Right Right
        if(balance < -1 && element.compareTo(_right._value) > 0){
            return rotateLeft();
        }

        //Left Right
        if(balance > 1 && element.compareTo(_left._value) > 0){
            _left = _left.rotateLeft();
            return rotateRight();
        }
        //Right Left
        if(balance < -1 && element.compareTo(_right._value) < 0){
            _right = _right.rotateRight();
            return rotateLeft();
        }

        return this;
    }

    @Override
    public T findMin() {
        T min;

        if(!_left.isEmpty()){
            min = _left.findMin();
        } else return _value;

        return min;
    }

    @Override
    public T findMax() {
        T max;

        if(!_right.isEmpty()){
            max = _right.findMin();
        } else return _value;

        return max;
    }

    @Override
    public boolean contains(T element) {
        if(element.compareTo(_value) == 0){
            return true;
        }

        else if(element.compareTo(_value) < 0){
            if(_left.isEmpty()){
                return false;
            }
            return _left.contains(element);
        }

        else if(element.compareTo(_value) > 0){
            if(_right.isEmpty()){
                return false;
            }
            return _right.contains(element);
        }

        return false;
    }

    public int balanceFactor(){
        if(isEmpty()){
            return 0;
        }
        int left = _left == null ? 0 : _left.height();
        int right = _right == null ? 0 : _right.height();
        return left - right;
    }

    @Override
    public boolean rangeContain(T start, T end) {
        // TODO
        if(isEmpty()){
            return false;
        }
        if(_value.compareTo(start) >= 0 && _value.compareTo(end) <= 0){
            return true;
        }

        if(_left != null && _left.rangeContain(start, end)){
            return true;
        }
        else if(_right != null && _right.rangeContain(start, end)){
            return true;
        }

        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }

}

