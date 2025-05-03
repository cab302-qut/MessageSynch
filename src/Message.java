public class Message implements Runnable {

    private String message;

    private int pauseTime;

    private volatile boolean stopFlag;

    public Message() {
        super();
    }

    public Message(String message, int pauseTime) {
        this();
        this.message = message;
        this.pauseTime = pauseTime;
    }

    public void run() {
        stopFlag = false;
        try {
            while (!stopFlag) {
                displayMessage(this);
                Thread.currentThread().sleep(pauseTime);
            }
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

    }

    public static void main(String[] args) {
        try {
            Message m1 = new Message("Artificial", 500);
            Message m2 = new Message("Intelligence", 500);
            Thread m1Thread = new Thread(m1);
            Thread m2Thread = new Thread(m2);
            m1Thread.start();
            m2Thread.start();

            Thread.currentThread().sleep(5000);
            m1.finish();
            m2.finish();
            m1Thread.join();
            m2Thread.join();
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        } finally {
            System.out.println();
        }
    }

    public void finish() {
        stopFlag = true;
        return;
    }
/*(
    public static void displayMessage(Message jm) throws InterruptedException {
        for (int i = 0; i < jm.message.length(); i++) {
            System.out.print(jm.message.charAt(i));
            Thread.currentThread().sleep(100);
        }
        System.out.println();
    }
*/
     public static synchronized void displayMessage(Message jm) throws InterruptedException {
      for (int i = 0; i < jm.message.length(); i++) {
         System.out.print(jm.message.charAt(i));
         Thread.currentThread().sleep(200);
      }
      System.out.println();
   }

 /*  private final static Object sharedLock = new Object();

  public static void displayMessage(Message jm) throws InterruptedException {
         synchronized (sharedLock) {
         for (int i = 0; i < jm.message.length(); i++) {
            System.out.print(jm.message.charAt(i));
            Thread.currentThread().sleep(50);
         }
         System.out.println();
      }
   }
*/
}
