public class GreetingThreadTester {

    public static void main(String[] args) {
        GreetingThread t1 = new GreetingThread("Hello, World!");
        GreetingThread t2 = new GreetingThread("Goodbye, World!");

        t1.start();
        t2.start();
    }
}


