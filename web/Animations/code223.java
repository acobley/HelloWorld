import java.awt.*;
import java.net.*;

public class code223 extends java.applet.Applet {
 
  myCanvasType myCanvas;
myCanvasType myCanvas2;
 private URL ImageURL;
  private Image myImage;
  

  public void init(){
     MediaTracker tracker= new MediaTracker(this);
     ImageURL = getDocumentBase();
     myImage = getImage(ImageURL,"ghost.gif");
     tracker.addImage(myImage,0);
     try {
         tracker.waitForID(0);
     }catch (InterruptedException e){
         System.out.println("Caught interrupt Exception"+e.getMessage());
         return;
     }
      myCanvas= new myCanvasType(myImage);
myCanvas2= new myCanvasType(myImage);
      add(myCanvas);
    add(myCanvas2);
     
  }    

}



class myCanvasType extends Canvas {
   private Image myImage;
   Dimension myDimension;

   myCanvasType(Image newImage){
      super();
      myImage=newImage;
      myDimension =new Dimension(myImage.getWidth(this),myImage.getHeight(this));
   }

   public void  paint(Graphics g){
       g.drawImage(myImage,0,0,this);
   }


   public  Dimension minimumSize(){
       return myDimension;
   }


   public  Dimension preferredSize(){
      return myDimension;
  }
}

