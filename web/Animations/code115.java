import java.awt.*;


public class code115 extends java.applet.Applet implements Runnable{

  Thread myThread1;
  Thread myThread2;
  int Count;
   String FontList[];

  public void init(){
     //Set up any variables etc
	FontList =Toolkit.getDefaultToolkit().getFontList();
  }
  public void start(){
     if  (myThread1 == null){
          myThread1= new Thread(this);
          myThread1.start();
     }
     if  (myThread2 == null){
          myThread2= new Thread(this);
          myThread2.start();
     }
  }
 

public void run(){

  if (Thread.currentThread() == myThread1){
     while(true){
        try{
          Thread.sleep(1000);
        }catch (Exception ignored){
           return;
        }
        Count ++;
        repaint();
     }
   }
}

public void paint(Graphics g){
Font myFont = new Font(FontList[0],Font.PLAIN,36);
         g.setFont(myFont);
     g.setColor(Color.red);
     g.fillRect(20,20,50,50);
     g.setColor(Color.black);
     g.drawString(""+Count,50,50);
 
}

public void update (Graphics g){
   paint(g);
}


public void stop(){
     if  (myThread1 != null){
          myThread1.stop();
          myThread1= null;
     }
     if  (myThread2 != null){
          myThread2.stop();
          myThread2= null;
     }
 }
}
