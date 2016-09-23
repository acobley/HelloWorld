import java.awt.*;
import java.net.*;

public class code117 extends java.applet.Applet implements Runnable{

  Thread myThread1;
 
  int x_location;
  int y_location;
  int AppletWidth;
  Image Background;
  Image Foreground;
 int lastx=0;
  int lasty=0;
  int newx;
  int newy;
  boolean first=true;
	boolean loaded=false;
  MediaTracker myTracker;

  public void init(){
     URL baseURL=null;
     baseURL = getCodeBase();
     Background = getImage(baseURL,"Pictures/Background.gif");
     Foreground = getImage(baseURL,"Pictures/ghost.gif");
     x_location = (-1)*Foreground.getWidth(this);
     y_location = (int)this.size().height/2;
     AppletWidth=this.size().width;
      myTracker = new MediaTracker(this);
  myTracker.addImage(Background,0);
  myTracker.addImage(Foreground,0);
  System.out.println("Waiting");
  try {
     myTracker.waitForID(0);
  }catch(Exception et){
      return;
  }
	loaded=true;
  System.out.println("Image Loaded");
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
   
public void paint (Graphics g){
 if (loaded==true){
	  //Draw the background at the old postion
	  if (first != true){
		g.clipRect(0,0,this.size().width,this.size().height);
     		g.clipRect(lastx, lasty, Foreground.getWidth(this), Foreground.getHeight(this));
	     	g.drawImage(Background,0,0,this);
	  }else{
		System.out.println("Drawing Background");
	     g.drawImage(Background,0,0,this);
	     first =false;
	  }
	  //Draw the Foreground at the new position
	  newx=x_location;
	  newy=y_location;
	  g.clipRect(0,0,this.size().width, this.size().height);
	  g.clipRect(newx, newy, Foreground.getWidth(this), Foreground.getHeight(this));
	  g.drawImage(Foreground,newx,newy,this);
	  lastx=newx;
	  lasty=newy;
	}
  
}


public void update (Graphics g){
   paint(g);
}



public void stop(){
     if  (myThread1 != null){
          myThread1.stop();
          myThread1= null;
     }
 }
}
