import java.awt.*;
import java.net.*;

public class code113 extends java.applet.Applet implements Runnable{

  Thread myThread1;
 
  int x_location;
  int y_location;
  int AppletWidth;
  Image Background;
  Image Foreground;

  public void init(){
     URL baseURL=null;
     baseURL = getCodeBase();
     Background = getImage(baseURL,"Pictures/Background.gif");
     Foreground = getImage(baseURL,"Pictures/ghost.gif");
     x_location = (-1)*Foreground.getWidth(this);
     y_location = (int)this.size().height/2;
     AppletWidth=this.size().width;
  }

  public void start(){
     if  (myThread1 == null){
          myThread1= new Thread(this);
          myThread1.start();
     }
  }
 

public void run(){

  if (Thread.currentThread() == myThread1){
     while(true){
        try{
          Thread.sleep(100);
        }catch (Exception ignored){
           return;
        }

        x_location=x_location +2;
        if (x_location > AppletWidth)
           x_location =(-1)*Foreground.getWidth(this);

        repaint();
     }
   }
}
   
   
   public void paint(Graphics g){
      g.drawImage(Background,0,0,this);
      g.drawImage(Foreground,x_location,y_location,this);
   }



public void stop(){
     if  (myThread1 != null){
          myThread1.stop();
          myThread1= null;
     }
 }
}
