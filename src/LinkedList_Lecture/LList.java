package LinkedList_Lecture;

public class LList implements LListInt{
    Node head;
    Node tail;

    LList(int value){
        head = new Node(value);
        tail = head;
    }

    public Node insertNode(int value){
        Node newNode = new Node(value);
        tail.nextNode = newNode;
        tail = newNode;
        return newNode;
    }

    public Node getHead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }

    public boolean deleteNode(int value){
        return false;
    }
    public int search(Node node, int n, int i){
        return 0;

    }
    public Node search(Node node, int n){
        if(node.value == n){
            return node;
        }
        else if(node.nextNode == null){
            return null;
        }
        else{
            return(search(node.nextNode,n));
        }
    }

}
