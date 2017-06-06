
import javax.swing.ImageIcon;
import javax.swing.JButton;


public interface ISymbol {  // isymbol interface
    
    public abstract void setImage(ImageIcon img , JButton btn); // setimage
    public abstract ImageIcon getImage(); // getimage
    public abstract void setValue(int val); // set value
    public abstract int getValue(); // get value
    
} // end of isymbol
