package assn04;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element){
		if(element.compareTo(_element)==0){
			return this;
		}
		if(element.compareTo(_element)<0){ //Less, go left
			if(_left.isEmpty()){
				_left = new NonEmptyBST<T>(element);
			}else{
				_left.insert(element);
			}
		}
		else{
			if(_right.isEmpty()){
				_right = new NonEmptyBST<T>(element);
			}else{
				_right.insert(element);
			}
		}
		return this;
	}
	public BST<T> remove_help(T element){
		System.out.println("remove!");
		BST<T> bruh = remove(element);
		printPostOrderTraversal();
		return bruh;
	}
	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if(isEmpty()){
			return this;
		}

		if(element.compareTo(_element)<0){
			_left = _left.remove(element);
		}else if(element.compareTo(_element)>0){
			_right = _right.remove(element);
		}else{
			//Found key
			if(_left.isEmpty()&&_right.isEmpty()){
				return new EmptyBST<T>();
			}
			else if(_left.isEmpty()){
				return _right;
			}else if(_right.isEmpty()){
				return _left;
			}else{
				T successor = _right.findMin();
				_element = successor;
				_right = _right.remove(successor);
			}
		}

		
		return this;
	}
	
	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
		if(isEmpty()){
			return this;
		}
		_left = _left.remove_range(start,end);
		_right = _right.remove_range(start,end);

		System.out.println("Element: "+_element+ ", Compare(start): "+_element.compareTo(start) +", Compare(end): "+_element.compareTo(end));
		if(_element.compareTo(start)>=0 && _element.compareTo(end)<=0){
			//System.out.println("Remove!");
			this.remove_help(_element); //Gets to here but then... doesn't remove?
		}

		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		if(!isEmpty()){
			System.out.print(_element+" ");
			if(!_left.isEmpty()){
				_left.printPreOrderTraversal();
			}
			if(!_right.isEmpty()){
				_right.printPreOrderTraversal();
			}
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if(!_left.isEmpty()){
			_left.printPostOrderTraversal();
		}
		if(!_right.isEmpty()){
			_right.printPostOrderTraversal();
		}
		System.out.print(_element+" ");
	}

	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
