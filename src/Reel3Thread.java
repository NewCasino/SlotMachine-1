
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Reel3Thread implements Runnable{ // implement the runnable interface

     static  boolean isNotStopped = false;
     
     JButton reel3 ;
     static String img;
     ImageIcon img3;
    
   public  Reel3Thread(JButton btn){
        this.reel3 = btn;
    }

    Reel3Thread() {
        
    }
   
 
    
    @Override
    public void run() {
        isNotStopped=true;
        
            
                            try {
                            
                                
                                
                            while (isNotStopped){                               
                                try {
                           Random generator=new Random();
                            Thread.sleep(100);
                           Images arr[]=Images.values();
                           
                         // ImageIcon img = new ImageIcon(this.getClass().getResource("/Images/"+arr[generator.nextInt(6)].toString()+".png"));
                         img3 = new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\"+arr[generator.nextInt(6)].toString()+".png");
                         // System.out.println("REEL 3"+this.getClass().getResource("/Images/")+arr[generator.nextInt(6)].toString()+".png");
                          
                        // System.out.println("REEL 3"+img3);
                         
                         img = img3.toString();
                         
                         
                      // img = img.split("Course Work")[1];
                      // System.out.println(img);
                         
                        int index = img.lastIndexOf("\\");
                        String fileName = img.substring(index + 1);
                        fileName = fileName.split(".png")[0];
                        System.out.println(fileName); 
                         
                          Symbol s = new Symbol();
                          s.setImage(img3, reel3);
                           
                          
                      
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            
                        
                                   
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
            
        
    }
    
    
}

/*
    Threads - series of executed statements
    allows us to do multiple activities in a single process
    extednding the thread class  override run() / implementing the runnable interface implement run()
    inceases the responsiveness if the gui application
    
    start() -> a new thread is created , the code inside the run() executed in the newly cerated thread
    only once , illegalstateexception
    run() -> no new thread is created , the code inside the run() executed in the current thread
    runnable ->when the newly created thread is started
    */