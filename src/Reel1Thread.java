
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Reel1Thread implements Runnable { // implement the runnable interface
    
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

    static  boolean isNotStopped = false;
    JButton reel1;
    
    protected ImageIcon img1; // Image icon 
    static String img ; // static string variable
    
    
    public Reel1Thread(JButton btn  ){ // reel1 constructor paasing the reel1 button
        this.reel1 = btn;
    }

    Reel1Thread() { // constructor
        
    }
  
    
    @Override
    public void run() { // run method

        isNotStopped = true; 
        
            
                            try { // try-block
                            
                            while (isNotStopped){    
                                
                                try { // try-block
                                    
                                Random generator=new Random(); // Random  object - generator
                                Thread.sleep(100); //causes the currently executing thread to sleep for 100 miliseconds thread running - waitin statr after 100 milisecinds returns runnable state 
                                Images arr[]=Images.values();

                           
                         // ImageIcon img = new ImageIcon(this.getClass().getResource("/Images/"+arr[generator.nextInt(6)].toString()+".png"));
                        //  System.out.println("REEL 1"+this.getClass().getResource("/Images/")+arr[generator.nextInt(6)].toString()+".png");
                          
                        img1 = new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\"+arr[generator.nextInt(6)].toString()+".png");
                      //  System.out.println("REEL 1"+img1);
                        
                        img = img1.toString();
                  
                   
                    
                        int index = img.lastIndexOf("\\");
                        String fileName = img.substring(index + 1);
                        fileName = fileName.split(".png")[0];
                        System.out.println(fileName);  
                       
                         Symbol s = new Symbol(); // object from symbol class
                         s.setImage(img1, reel1); // set the image passing the image variable  and reel1 btn
                          
                      
                                                       
                                } catch (Exception ex) { // catch- exceptions
                                    ex.printStackTrace();
                                }
                            }
                            
                        
                                   
                        } catch (Exception ex) { // catch- exceptions
                            ex.printStackTrace();
                        }
            
        
    } // end of run()
    
    
}  // end of reeel1thread class
