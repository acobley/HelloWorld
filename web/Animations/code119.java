import java.awt.*;
import java.net.*;

public class code119 extends java.applet.Applet implements Runnable{

  Thread clockThread;
  Thread mainThread;

  MediaTracker myTracker;

  Image bgImage;
  Image fgImage;
  URL baseURL;
  boolean Loaded = false;
  int Count=0;

  public void init(){
     baseURL=getDocumentBase();
     bgImage=getImage(baseURL,"bg.gif");
     fgImage=getImage(baseURL,"fg.gif");
  
     myTracker = new MediaTracker(this);
     myTracker.addImage(bgImage,0);
     myTracker.addImage(fgImage,0);
     
   }


 
  public void start(){
     if  (clockThread == null){
          clockThread= new Thread(this);
          clockThread.start();
     }
     if  (mainThread == null){
          mainThread= new Thread(this);
          mainThread.start();
     }
  }
 


public void run(){

  if (Thread.currentThread() == clockThread){
     while(true){
        try{
          Thread.sleep(1000);
        }catch (InterruptedException ignored){
           return;
        }
        Count ++;
        repaint();
     }
   }
   if (Thread.currentThread() == mainThread){
      try {
       myTracker.waitForID(0);
     }catch(Exception et){
         return;
     }      
     clockThread.stop();
     Loaded = true;
     // Now do what you want with the images I.e call a repaint
     repaint();
  }
}

public void paint(Graphics g){
  if (Loaded==false){
     g.drawRect(10,10,200,10);
     g.drawString("Waiting for images, waited for "+Count,20,20);
  }
  if ( (Loaded ==true)){
     g.drawImage(bgImage,0,0,this);
     g.drawImage(fgImage,0,0,this);
  }  
}

   public void update (Graphics g){
      paint(g);
   }



   public void stop(){
      if  (clockThread != null){
          clockThread.stop();
          clockThread= null;
      }
      if  (mainThread != null){
          mainThread.stop();
          mainThread= null;
      }
   }
}
