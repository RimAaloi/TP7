public class Talkative implements Runnable {
    private int value;

    public Talkative(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread " + value + ": " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Talkative(i));
            thread.start();
        }
    }
}
