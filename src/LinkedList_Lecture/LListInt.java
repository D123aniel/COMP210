package LinkedList_Lecture;

public interface LListInt {
    Node getHead();
    Node getTail();
    Node insertNode(int value);
    boolean deleteNode(int value);
    int search(Node node, int n, int i);
    Node search(Node node, int n);
}
