package fr.jhelp.tools;

public class Tools {
    public static void sleep(long timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
