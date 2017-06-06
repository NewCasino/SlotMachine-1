
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Symbol implements ISymbol{ // class symbol implements isymbol interface

    int value;
    ImageIcon Sym;
    
    @Override
    public void setImage(ImageIcon img , JButton btn) { // set image btn passing imageicon and btn
        btn.setIcon(img);
        this.Sym=img;
    }

    @Override
    public ImageIcon getImage() { // get image method
        return this.Sym;
    }

    @Override
    public void setValue(int val) {
         this.value = val;
    }

    @Override
    public int getValue() {
        return this.value;
    }

   
    
    
}


enum Images { // enum  data type // static & final
    Seven(7), Bell(6), Watermelon(5), Plum(4), Lemon(3), Cherry(2); // imge values

    private int value;

    private Images(int val) { // private constructor - enum
        this.value = val;
    }
int getValueOfImage(){ // get values of images returns an array containing all the values of the enum.
    return value;
}
    
}