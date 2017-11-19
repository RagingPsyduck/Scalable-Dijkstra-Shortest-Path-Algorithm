import java.util.*;

public class Thread implements Runnable{
    private int ticket = 5;

    public void run(){
        for (int i=0;i<10;i++)
        {
            if(ticket > 0){
                System.out.println("ticket = " + ticket--);
            }
        }
    }

}