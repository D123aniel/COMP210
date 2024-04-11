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
        _left = null;
        _right = null;
        _height = 0;
        _size = 1;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        AVLTree<T> newRoot = this._right;
        AVLTree<T> tempNode = newRoot._left;
        newRoot._left = this;
        this._right = tempNode;
        int left = this._left == null ? 0 : this._left.height();
        int right = this._right == null ? 0 : this._right.height();
        this._height = Math.max(left, right) + 1;
        int rightleft = _right._left == null ? 0 : _right._left.height();
        int rightright = _right._right == null ? 0 : _right._right.height();
        _right._height = Math.max(rightleft, rightright) + 1;
        return this;
    }
    
    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
        AVLTree<T> newRoot = this._left;
        AVLTree<T> tempNode = newRoot._right;
        newRoot._right = this;
        this._left = tempNode;
        int left = this._left == null ? 0 : this._left.height();
        int right = this._right == null ? 0 : this._right.height();
        this._height = Math.max(left, right) + 1;
        int leftleft = _left._left == null ? 0 : _left._left.height();
        int leftright = _left._right == null ? 0 : _left._right.height();
        _left._height = Math.max(leftleft, leftright) + 1;
        return this;
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
        System.out.println("Hi!");
        if(isEmpty()){
            System.out.println("Empty!");
            return new AVLTree<T>(element);
        }

        if(element.compareTo(_value) < 0){
            if(_left == null){
                _left = new AVLTree<T>(element);
            }else{
                _left = (AVLTree<T>) _left.insert(element);
            }  
        }else if(element.compareTo(_value) > 0){
            if(_right == null){
                _right = new AVLTree<T>(element);
            }else{
                _right = (AVLTree<T>) _right.insert(element);
            }
        }else return this;
        
        if(_left == null && _right == null){
            _height = 0;
        }else if(_left == null){
            _height = _right.height() + 1;
        }else if(_right == null){
            _height = _left.height() + 1;
        }else{
            _height = Math.max(_left.height(), _right.height()) + 1;
        }
        _size++;
        
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
    	// TODO
        
        return null;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO

        return null;
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO

        return null;
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        
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

