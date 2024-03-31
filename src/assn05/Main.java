package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
//        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        /*
        Part 1 - Write some tests to convince yourself that your code for Task 1 is working
         */
        // Write a simple test for dequeue in SimpleEmergencyRoom
        SimpleEmergencyRoom simpleER = new SimpleEmergencyRoom();
        simpleER.addPatient("Bob1",10);
        simpleER.addPatient("Bob2",20);
        simpleER.addPatient("Bob2",30);
        simpleER.addPatient("Bob4",40);
        simpleER.addPatient("Bob5",50);
        System.out.println("Simple ER size: " + simpleER.size());
        System.out.println("Dequeue: " + simpleER.dequeue().getValue());

    }

    // test Part 2
    public static void testP2(){
       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MaxBinHeapER complexER = new MaxBinHeapER();
        System.out.println("Dequeue, Empty: " + complexER.dequeue());
        complexER.enqueue("Bob1",10);
        //System.out.println("Dequeue, One Element: " + complexER.dequeue());
        complexER.enqueue("Bob2",20);
        complexER.enqueue("Bob3",30);
        complexER.enqueue("Bob4",40);
        complexER.enqueue("Bob5",50);
        complexER.enqueue("Bob6",60);
        complexER.enqueue("Bob7",70);
        complexER.enqueue("Bob8",80);
        complexER.enqueue("Bob9",90);
        System.out.println("Complex ER size: " + complexER.size());
        System.out.println("Dequeue: " + complexER.dequeue());
        System.out.println(complexER.toString());
        complexER.updatePriority("Bob8", 1);
        System.out.println(complexER.toString());
    }

    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()+ ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */
        double[] results = compareRuntimes();
        System.out.println("Simple ER Dequeue Time: " + results[0] + " Average: " + results[1]);
        System.out.println("Complex ER Dequeue Time: " + results[2] + " Average: " + results[3]);

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here
        long startTime = System.nanoTime();
        for(int i = 0; i < 100; i++) {
            simplePQ.dequeue();
        }
        long endTime = System.nanoTime();
        results[0] = (endTime - startTime);
        results[1] = results[0] / 100000;

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here
        startTime = System.nanoTime();
        for(int i = 0; i < 100; i++) {
            binHeap.dequeue();
        }
        endTime = System.nanoTime();
        results[2] = (endTime - startTime);
        results[3] = results[2] / 100000;
        return results;
    }

}
