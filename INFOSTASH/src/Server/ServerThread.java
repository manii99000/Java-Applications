package Server;

public class ServerThread extends Thread{
    ReceiverServer rs;
    /** Creates a new instance of ServerThread */
   public void run() {
        System.out.println("in run");
        ReceiverServer.serverstatus=true;
        rs=new ReceiverServer();
    }
    
}
