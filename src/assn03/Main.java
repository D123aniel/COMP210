package assn03;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(30);
        System.out.println("list = " + list.toString());
        System.out.println("size of list = " + list.size());
        System.out.println("list contains 10?: " + list.contains(10));     // implemented
        System.out.println("list contains 50?: " + list.contains(50));
        System.out.println("set element at index 2 to be 10");
        list.set(2, 10);
        System.out.println("get element at index 2 = " + list.get(2));
        System.out.println("list = " + list.toString());
        System.out.println("Last Index of element 10 in list = " + list.lastIndexOf(10));


        

        // Test task 1 - removeAtIndex
        System.out.println("------TEST 1------");
        System.out.println("List: "+list.toString());
        list.removeAtIndex(1);  // TBD
        System.out.println("Task 1: list after removing element at index 1 = " + list.toString());

        // Test task 2 - isEqual
        System.out.println("------TEST 2------");
        LinkedList list2 = new LinkedList();
        list2.add(10);
        list2.add(10);
        list2.add(30);
        System.out.println("list2 = " + list2.toString());
        System.out.println("list = "+list.toString());
        System.out.println("Task 2: list == list2 ?: " + list.isEqual(list2)); // not yet implemented

        // Test task 3 - Remove Repeat
        System.out.println("------TEST 3------");
        LinkedList l3 = new LinkedList();
        l3.add(1);
        l3.add(2);
        l3.add(2);
        l3.add(3);
        l3.add(1);
        l3.add(3);
        System.out.println("List: " + l3.toString());
        l3.removeRepeats();
        System.out.println("List after removing repeats: " + l3.toString());

        // Test task 4 - Reverse
        System.out.println("------TEST 4------");
        LinkedList l4 = new LinkedList();
        l4.add(1);
        l4.add(3);
        l4.add(5);
        l4.add(7);
        l4.add(9);
        System.out.println("List: "+l4.toString());
        l4.reverse();
        System.out.println("List after reverse: "+l4.toString());

        // Test task 5 - Merge
        System.out.println("------TEST 5------");
        LinkedList list4 = new LinkedList();
        list4.add(1);
        list4.add(3);
        list4.add(5);
        list4.add(7);
        LinkedList list44 = new LinkedList();
        list44.add(2);
        list44.add(4);
        list44.add(6);
        System.out.println("List 1: "+list4.toString() +"\nList 2: "+list44.toString());
        list4.merge(list44);
        System.out.println("New Merged List: "+ list4.toString());
    }
}
