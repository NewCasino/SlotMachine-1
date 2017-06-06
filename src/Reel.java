
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

public class Reel extends JFrame {

    static Reel mainFrame; // create a static variable from reel 

    HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(); // Hash Map to store game statistics
    private ArrayList<Reel> gameList;  // array list is created to read the saved file data
    int pointKey = 1; // hashmap increment key

    boolean isReel1NotStopped = false; // boolean variable to check if reel1 is stopped
    boolean isReel2NotStopped = false; // boolean variable to check if reel2 is stopped
    boolean isReel3NotStopped = false; // boolean variable to check if reel3 is stopped

    private Thread internalThread1; // internalThread1
    private Thread internalThread2; // internalThread2
    private Thread internalThread3; // internalThread3

    JTextArea creditArea;
    JButton btnspin;
    JTextArea betArea;
    JButton btnbetmax;
    JButton btnbetone;
    JButton reel1;
    JButton reel2;
    JButton reel3;
    JButton btnSave;
    JButton btnadd;
    JButton btnplayGame;
    int totalSpinCount = 0;
    private int betAmount = 0; // variable for betone
    private int maxBet = 0; // variable for maxbet
    private double avg = 0; // variable for avg
    private int initialCredits = 10; // initial credit

    private int coins = 1;
    int total;
    private int totalCoins;
    private int spinCount = 0; // spincount

    protected int totalWins;  // variable to calculate wins
    protected int totalLosses; // variable to calculate loses
    protected int totalNoWins = 0;
    protected int totalNoLosses = 0;

    private int gameNo = 0; // variable to indicate game no

    int initialBetAmount = 0;

    int reel1Clicked = 0; // check when the game is over if all 3 reels are clicked
    int reel2Clicked = 0;
    int reel3Clicked = 0;

    ImageIcon imgIcon = new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + "spin2.png");
    ImageIcon imgbetone = new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + "bet1.jpg");
    //  ImageIcon imgbetmax = new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + "maxbet2.jpg");

    ImageIcon imgplay = new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + "play.jpg");
    String msg = "Reels Did not match!!!!! \n Please Bet A Value Before Spining the Coin"; // msg i lost

    public Reel() { // Reel Constructor

        setTitle("Slot Machine Application");
        setBackground(new Color(179, 236, 255));
        getContentPane().setBackground(new Color(176, 196, 222));

        //   setContentPane(new JLabel(new ImageIcon ("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\"+"ba1.jpg")));
        setLayout(new GridBagLayout()); // layout - gridbag layout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH; // components grow in both dimensions
        c.insets = new Insets(5, 5, 5, 5); // space 

        reel1 = new JButton();
        reel2 = new JButton();
        reel3 = new JButton();
        btnspin = new JButton(imgIcon);
        btnadd = new JButton();
        btnbetone = new JButton(imgbetone);
        btnbetmax = new JButton();
        JButton btnreset = new JButton();
        JButton btnstatistics = new JButton();
        btnplayGame = new JButton(imgplay);
        //   btnmarks = new JButton("");

        btnspin.setEnabled(false);
        btnspin.setDisabledIcon(imgIcon);
        btnbetone.setEnabled(false);
        btnbetone.setDisabledIcon(imgbetone);
        btnbetmax.setEnabled(false);
        btnadd.setEnabled(false);

        Random generator = new Random(); // Rondom object - generator
        Images arr[] = Images.values(); // image array

        reel1.setText(" ");
        reel1.setIcon(new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + arr[generator.nextInt(6)].toString() + ".png"));
        //  reel1.setIcon(new ImageIcon(this.getClass().getResource("/Images/"+arr[generator.nextInt(6)].toString()+".png")));
        reel2.setText(" ");
        reel2.setIcon(new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + arr[generator.nextInt(6)].toString() + ".png"));
        //  reel2.setIcon(new ImageIcon(this.getClass().getResource("/Images/"+arr[generator.nextInt(6)].toString()+".png")));
        reel3.setText(" ");
        //  reel3.setIcon(new ImageIcon(this.getClass().getResource("/Images/"+arr[generator.nextInt(6)].toString()+".png")));

        reel3.setIcon(new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\" + arr[generator.nextInt(6)].toString() + ".png"));

        //  btnspin.setText(" ");
        //  btnspin.setIcon(new ImageIcon("G:\\IIT Notes\\Year 2\\Level 5 Semester 1\\Object Oriented Programming\\Course Work\\"+"spin2.png"));
        c.gridx = 4;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        reel1.setBorder(new LineBorder(Color.black, 5));
        add(reel1, c);

        c.gridx = 5;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        reel2.setBorder(new LineBorder(Color.black, 5));
        add(reel2, c);

        c.gridx = 6;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        reel3.setBorder(new LineBorder(Color.black, 5));
        add(reel3, c);

        c.gridx = 7;
        c.gridy = 7;
        c.gridwidth = 2;
        c.gridheight = 2;
        add(btnspin, c);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        // btnadd.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(btnadd, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        btnbetone.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnbetone.setBackground(Color.GRAY);
        btnbetone.setForeground(Color.red); // text color
        btnbetone.setFont(new Font("Kristen ITC", Font.BOLD, 14));
        add(btnbetone, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        btnbetmax.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnbetmax.setBackground(new Color(192, 192, 192));
        add(btnbetmax, c);

        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.gridheight = 1;
        btnreset.setBorder(new MatteBorder(5, 5, 30, 30, new Color(77, 255, 77)));
        btnreset.setFont(new Font("Kristen ITC", Font.BOLD, 14));
        add(btnreset, c);

        c.gridx = 3;
        c.gridy = 10;
        c.gridwidth = 2;
        c.gridheight = 1;
        btnstatistics.setBackground(new Color(255, 165, 0));
        add(btnstatistics, c);

        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 2;
        c.gridheight = 1;
        btnplayGame.setBackground(Color.WHITE);
        add(btnplayGame, c);

//        c.gridx = 6;
//        c.gridy = 12;
//        c.gridwidth = 1;
//        c.gridheight = 1;
//        btnmarks.setOpaque(false);
//        btnmarks.setContentAreaFilled(false);
//        add(btnmarks, c);
        btnspin.setText("");
        btnadd.setText("Add Coin");
        btnbetone.setText("");
        btnbetmax.setText("Bet Max");
        btnreset.setText("RESET");
        btnstatistics.setText("Statistics");
        btnspin.setSize(5, 5);

        creditArea = new JTextArea("  No of Available Credits      ");    // display credit area
        creditArea.setEditable(false);
        creditArea.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        creditArea.setBackground(new Color(0, 191, 255));

        c.gridx = 8;
        c.gridy = 12;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(creditArea, c);

        betArea = new JTextArea("    Slot Machine Bet Area - 0");   // display bet area
        betArea.setEditable(false);

        betArea.setFont(new Font("Kristen ITC", Font.BOLD, 14));
        betArea.setBackground(new Color(255, 255, 0));

        c.gridx = 8;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(betArea, c);

        btnspin.setBackground(Color.black);
        btnbetone.setBackground(Color.white);
        reel1.setBackground(Color.white);
        reel2.setBackground(Color.white);
        reel3.setBackground(Color.white);
        Random obj = new Random();

        // setInitialCredits();
        //  btnmarks.setEnabled(false);
        reel1.addActionListener(new ActionListener() { // reel1 actiolistner when reel1 is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                Reel1Thread.isNotStopped = false;
                Reel2Thread.isNotStopped = false;
                Reel3Thread.isNotStopped = false;

                btnspin.setEnabled(false);  // diasable the buttons
                btnspin.setDisabledIcon(imgIcon);

                btnbetmax.setEnabled(false);
                btnbetone.setEnabled(false);
                btnbetone.setDisabledIcon(imgbetone);
                btnadd.setEnabled(false);
//              btnmarks.setEnabled(true);

                reel1Clicked++;

                try { // try block
                    Thread.sleep(100); // cause the currently executing  reel1 thread to sleep for a specified time of mili seconds 100)
                    matchImages(); // thread goes from running state - waiting state returns to runnable state after 100 miliseconds
                } catch (Exception ex) { // catch -block exception handling
                    ex.printStackTrace();
                }
            }
        });

        reel2.addActionListener(new ActionListener() { // reel2 actionlistner when reel2 is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                Reel1Thread.isNotStopped = false;
                Reel2Thread.isNotStopped = false;
                Reel3Thread.isNotStopped = false;

                btnspin.setEnabled(false); // disable buttons
                btnspin.setDisabledIcon(imgIcon);

                btnbetmax.setEnabled(false);
                btnbetone.setEnabled(false);
                btnbetone.setDisabledIcon(imgbetone);
                btnadd.setEnabled(false);
//                btnmarks.setEnabled(true);

                reel2Clicked++;

                try { // try - block
                    Thread.sleep(100); // cause the currently executing  reel2 thread to sleep for a specified time of mili seconds (100)
                    matchImages();        // thread goes from running state - waiting state returns to runnable state after 100 miliseconds
                } catch (Exception ex) { // catch-bloack - exception handling
                    ex.printStackTrace();
                }
            }
        });

        reel3.addActionListener(new ActionListener() { // reel3 actionlistner
            @Override
            public void actionPerformed(ActionEvent e) {

                Reel1Thread.isNotStopped = false;
                Reel2Thread.isNotStopped = false;
                Reel3Thread.isNotStopped = false;

                btnspin.setEnabled(false); // disable buttons
                btnspin.setDisabledIcon(imgIcon);

                btnbetmax.setEnabled(false);
                btnbetone.setEnabled(false);
                btnbetone.setDisabledIcon(imgbetone);
                btnadd.setEnabled(false);
//                btnmarks.setEnabled(true);

                reel3Clicked++;

                try {
                    Thread.sleep(100); // cause the currently executing  reel3 thread to sleep for a specified time of mili seconds (100)
                    matchImages(); // thread goes from running state - waiting state returns to runnable state after 100 miliseconds
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

//        btnmarks.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//             //   matchImages();
//
//                //   btnspin.setEnabled(true);
//            }
//        });
        btnreset.addActionListener(new ActionListener() { // when button reset is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                int total = Integer.parseInt(betArea.getText().toString().split("-")[1].trim()) + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
                creditArea.setText("    Total Credits After RESET -  " + total); //  total credits 

                betArea.setText("   Bet Area  - 0 "); // bet area
            }
        });

        btnplayGame.addActionListener(new ActionListener() { // when play game button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                playGame();

                spinCount = 0;
                System.out.println("Spin count new Game" + spinCount);

            }
        });
        

        
                
                
        btnstatistics.addActionListener(new ActionListener() { // when  statistics button is clicked 
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("  Statistics  ");

                JPanel topPanel = new JPanel();
                JPanel btnPanel = new JPanel();

                topPanel.setLayout(new BorderLayout());
                getContentPane().add(topPanel);
                getContentPane().add(btnPanel);

                btnSave = new JButton("save");
                btnSave.setVisible(true);
                frame.add(btnSave);

                String[] columns = new String[]{"GameNo", "No of Wins", "No of Losses", "Average"}; // names of the columns of the table 
               Object[][] info = new Object[][]{{"", "", "", ""}}; // data /// row

               // DefaultTableModel - class 
                DefaultTableModel model = new DefaultTableModel(info, columns); // table was selected to display the statistics
                JTable table = new JTable(model);                               // user is easily able to know the statistics
                frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));     // well organized
                table.setVisible(true);

                model = (DefaultTableModel) table.getModel();
                table.setBorder(new LineBorder(Color.blue, 1));

                table.setBackground(new Color(255, 250, 250));
                frame.add(new JScrollPane(table));

                table.setShowVerticalLines(true);
                table.setGridColor(Color.red);

                int rowNo = 0;
               
                calculateAvg(); // calculate the avg
                
                Vector v = new Vector(); // vector - store the current game details and display in the table

                for (int i = 0; i < model.getRowCount(); i++) {
                v.add(gameNo);
                v.add(totalNoWins);
                v.add(totalNoLosses);
                v.add(avg);
                
           
               storeGameDetails(v); // store game details
                }
               
                    
                model.insertRow(rowNo, v);
                frame.pack();
                frame.setVisible(true);

                frame.setSize(500, 300);

                
                
            }
        });

     
        
        btnspin.addActionListener(new ActionListener() { // when spin button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                spinCount++; // increase the amount of spin times
              //  totalSpinCount = totalSpinCount + spinCount;
                System.out.println("Spin Game" + spinCount);
                initialBetAmount = 0; // set the initial bet amount to 0

                if ((reel1Clicked >= 1) && (reel2Clicked >= 1) && (reel3Clicked >= 1)) { // if all 3 reels are clicked game is over
                    gameOver();
                    btnplayGame.setEnabled(true); // enable the play button to start new game
                } else {
                    btnbetone.setEnabled(false); // disable buttons
                    btnbetone.setDisabledIcon(imgbetone);
                    btnbetmax.setEnabled(false);
                    btnadd.setEnabled(false);
//                btnmarks.setEnabled(false);

                    int count = 0; // initialize bet value - 0
                    int c = Integer.parseInt(betArea.getText().toString().split("-")[1].trim());

                    int creditValue = 0; // initialize credit value - 0
                    int cv = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());

                    if (cv == creditValue) { //  display message if the credits are over you can't play
                        JOptionPane.showMessageDialog(null, "SORRY!!!!! \n Your credits are 0 You cannot play the Game");

                    } else if (c == count) { // display error message if the player did not bet a value
                        JOptionPane.showMessageDialog(null, "You Must BET a Value to Start the game");
                        btnbetone.setEnabled(true);
                        btnbetmax.setEnabled(true);

                    } else {
                        isReel1NotStopped = true;
                        Reel1Thread r1 = new Reel1Thread(reel1); // object reel1thread
                        internalThread1 = new Thread(r1, "Slot Machine"); // create new thread
                        internalThread1.start(); //main thread internally calls the run() to start the newly created thread
                        
                        isReel2NotStopped = true;
                        Reel2Thread r2 = new Reel2Thread(reel2); // object reel2thread
                        internalThread2 = new Thread(r2, "Slot Machine"); // create new thread
                        internalThread2.start(); // main thread internally calls start() to call the newly created thread

                        isReel3NotStopped = true;
                        Reel3Thread r3 = new Reel3Thread(reel3); // object reel3thread
                        internalThread3 = new Thread(r3, "Slot Machine"); // create new thread
                        internalThread3.start(); // main thread internally calls the start() to call the newly created thread

                        //   matchImages();
                    }
                }
            }
//              

//                isNotStopped = true;
//                
        });

        btnbetone.addActionListener(new ActionListener() {// whenthe betone button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                betAmount++; //increase the bet amount
                betOne(); // betone method

                //   btnadd.setEnabled(true); 
                btnspin.setEnabled(true); // enable spin button when player bets amount
//                betAmount++;
//                totalCoins--;
//                
//                betOne();
//                creditArea.setText("Total Credits " + totalCoins);
//                
//                if(totalCoins <= 0){
//                    btnbetone.setEnabled(false);
//            }

            }
        });

        btnbetmax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxBet++; // increase maxbet

                betMax(); // maxbet method
                btnspin.setEnabled(true); // enable spin button when player bets amount

//                if(totalCoins <=3){
//                    btnbetmax.setEnabled(false);
//                }
//               if(totalCoins > 3){
//                   btnbetmax.setEnabled(true);
//               }
            }
        });

        btnadd.addActionListener(new ActionListener() { // when add button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                addCoin(); // addcoin method

            }
        });

        setSize(900, 600); // set the size of the jfame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setInitialCredits() { //set initial credits method
        totalCoins = 10; // initial credits - 10
        creditArea.setText("  Initial Amount of Credits -   " + initialCredits);

    }

    private void betOne() { // betone method
 
        total = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim()); // split from the - 

        if (total >= 1) { // if the total is greater than 1 add the total bet amount to the currently bet amount
            int ba = Integer.parseInt(betArea.getText().toString().split("-")[1].trim());
            ba += 1; // increase the bet area
            betArea.setText("  Amount the Player has  bet -  " + ba + "   ");
            initialBetAmount = ba;

            int ca = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
            ca -= 1; // deduct the credit area when player has bet value
            creditArea.setText("  Total Amount of  Credits -  " + ca + "   ");
//             int betTotal = betAmount + maxBet +initialCredits;
//             betArea.setText("  Amount the Player has  bet -  " + betTotal + "   " );
        } else {
            betArea.setText("  Amount the Player has  bet -  " + betAmount + "   ");
            // initialBetAmount = betAmount;
        }
    }

    private void betMax() { // betmax method
        total = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
        if (total >= 3) { //total is greater than 3 
            int ba = Integer.parseInt(betArea.getText().toString().split("-")[1].trim());
            ba += 3; // incerase the bet area
            betArea.setText("  Amount the Player has  bet -  " + ba + "   ");
            initialBetAmount = ba;

            int ca = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
            ca -= 3; // deduct the credit area when player clicks betmax
            creditArea.setText("  Total Credits -  " + ca + "   ");
        }

//        total  = maxBet + betAmount;
//        betArea.setText(" Max Amount the Player bet -  " + total + "   ");
//        
//        
//        totalCoins = totalCoins - 3;
//        creditArea.setText("Total Credits - " + totalCoins);
    }

    private void addCoin() { // addcoin method

        totalCoins = 1 + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
        creditArea.setText("  Total Number of  -  " + totalCoins + "  ");

        if (totalCoins >= 3) { // if the total credits are > 3
            btnbetmax.setEnabled(true); //enablebetmax
        }
        if (totalCoins >= 1) { // if the total credits are > 1
            btnbetone.setEnabled(true); //enablebetone
        }

    }

    private boolean matchImages() { // method to calculate points by matching the reels

        boolean matched = false; // boolean match - false

        Reel1Thread r1 = new Reel1Thread(); // object1
        String a = r1.img; // r1 image to string variable

        Reel2Thread r2 = new Reel2Thread(); //object2
        String b = r2.im2; // r2 image to string variable

        Reel3Thread r3 = new Reel3Thread(); //object3
        String c = r3.img; // r3 image to string variable
        System.out.println(a + "  1");
        System.out.println(b + "  2");
        System.out.println(c + "  3");

        int index = r1.img.lastIndexOf("\\"); // split from the // to get the image name
        String fileName = r1.img.substring(index + 1);
        fileName = fileName.split(".png")[0];
        System.out.println(Images.valueOf(fileName).getValueOfImage());

        int index2 = r2.im2.lastIndexOf("\\");// split from the // to get the image name
        String fileName2 = r2.im2.substring(index + 1);
        fileName2 = fileName2.split(".png")[0];
        System.out.println(Images.valueOf(fileName2).getValueOfImage());

        int index3 = r3.img.lastIndexOf("\\"); // split from the // to get the image name
        String fileName3 = r3.img.substring(index + 1);
        fileName3 = fileName3.split(".png")[0];
        System.out.println(Images.valueOf(fileName3).getValueOfImage());

        if (a.equalsIgnoreCase(b) && (a.equalsIgnoreCase(c))) {  // if all 3 reels match

            getWins(); // getwins method
            JOptionPane.showMessageDialog(null, "All 3 Reels are Matched"); //  display win msg
            btnbetone.setEnabled(true);
            btnbetmax.setEnabled(true);
            btnadd.setEnabled(true);
            matched = true;
            // calculatePoints();

            // calculate points
            int mark = Images.valueOf(fileName).getValueOfImage(); //get the value of image to a int variable
            int totalMark = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim()); // set to the credit area

            int m = 0;
            if (betAmount > 0) { // if betone is clicked
                m = mark * betAmount; // get thevalue of image * betone amount
            }

            if (maxBet > 0) { // if betmax is clicked
                m = mark * maxBet; // get thevalue of image * betmax amount
            }

            totalMark += m;// total mark - mark
            creditArea.setText(" Total Credits  After Winning Game - " + totalMark); // totalcredits - mark
            System.out.println("1 " + (m * mark));
            matched = true; // reels matched - true

        } else if (a.equalsIgnoreCase(b)) {
            JOptionPane.showMessageDialog(null, "Reels 1 & 2 are matching \n Free Chance to Spin the Reels without Betting"); //if32 reels match 

            btnbetone.setEnabled(false); // disable betone , betmax ,addcoin
            btnbetone.setDisabledIcon(imgbetone);
            btnbetmax.setEnabled(false);
            btnadd.setEnabled(false); // enable spin button
            btnspin.setEnabled(true);
            //  matchedImages();run

            matched = true;
            //   System.out.println(a);
            //  calculatePoints(); 

        } else if (b.equalsIgnoreCase(c)) { // if 2 reels match
            JOptionPane.showMessageDialog(null, "Reels 2 & 3 are Matching \n Free Chance to Spin the Reels without Betting or Adding Coin");

            //  matchedImages();
            btnbetone.setEnabled(false); // disable betone , betmax ,addcoin
            btnbetone.setDisabledIcon(imgbetone);
            btnbetmax.setEnabled(false);
            btnadd.setEnabled(false); // enable spin button
            btnspin.setEnabled(true);

//            int mark = Images.valueOf(fileName2).getValueOfImage();
//            int totalMark = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//
//            int m = 0;
//            if (betAmount > 0) {
//                m = mark * betAmount;
//            }
//
//            if (maxBet > 0) {
//                m = mark * maxBet;
//            }
//
//            totalMark += m;
//            creditArea.setText("Credits - " + totalMark);
//            System.out.println("1 " + (m * mark));
//            matched = true;
            // System.out.println(b);
            // calculatePoints();
        } else if (a.equalsIgnoreCase(c)) {
            JOptionPane.showMessageDialog(null, "Reels 1 & 3 are matching \n Free Chance to Spin the Reels without Betting");

            //   matchedImages();
            btnbetone.setEnabled(false);
            btnbetone.setDisabledIcon(imgbetone);
            btnbetmax.setEnabled(false);
            btnadd.setEnabled(false);
            btnspin.setEnabled(true);

//            int mark = Images.valueOf(fileName).getValueOfImage();
//            int totalMark = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//
//            int m = 0;
//            if (betAmount > 0) {
//                m = mark * betAmount;
//            }
//
//            if (maxBet > 0) {
//                m = mark * maxBet;
//            }
//
//            totalMark += m;
//            creditArea.setText("Credits - " + totalMark);
//            System.out.println("1 " + (m * mark));
//
//            //   calculatePoints();
//            matched = true;

            //   System.out.println("hiiiii"+c);
        } else if ((!a.equalsIgnoreCase(b)) && (!b.equalsIgnoreCase(c)) && (!a.equalsIgnoreCase(c))) { // if 3 reels wont match

            getLosses(); //getlosses method
            // JOptionPane.showMessageDialog(null,msg);
            btnspin.setEnabled(false); // disable spin button
            btnspin.setDisabledIcon(imgIcon);
            btnadd.setEnabled(false);
            btnbetmax.setEnabled(true);
            btnbetone.setEnabled(true);

            JOptionPane.showMessageDialog(null, "You Loose"); // display msg

            if (betAmount > 0) { // bet amount
                betAmount = 0;
            }

            if (maxBet > 0) { // maxbet
                maxBet = 0;
            }

            int getTotalBet = Integer.parseInt(betArea.getText().toString().split("-")[1].trim());
            int getTotalCredits = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
            int creditsAfterLoosing = getTotalCredits - getTotalBet;
            creditArea.setText("Remaining Credits After losing - " + creditsAfterLoosing); // display credits after loosing
            betArea.setText("Bet Area After losing - 0"); // display bet area
            matched = false;

        }

        return matched;

    }
    
    

    public void storeGameDetails(Vector v) { // store game details pass Vector object

        try {
               // Date d = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");
		//String date2 = formatter.format(d);
		//System.out.println(date2);
               // File file = new File(date2+".txt");
                
                
                Date date = new Date() ; // date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ; // date format
                File file = new File(dateFormat.format(date) + ".txt") ; // create file using curent date

		if (!file.exists()) { // create a new fike if the file doen't exist
			file.createNewFile();
		}
		BufferedWriter Bwriter = new BufferedWriter(new FileWriter(file, true)); // buffered writer - writes character to the file
                
                Bwriter.write("Game No , No Of Wins , No of Losses , Average"); // file
                Bwriter.newLine(); // get the new line
		for (int i = 0; i < v.size(); i++) {
                        
			Bwriter.append(v.get(i).toString()); //enter details to the existing line
			Bwriter.write("                           ");

		}
              Bwriter.newLine(); // get new line
		
		Bwriter.flush(); // flush buffered writer
		Bwriter.close(); // close buffered writer
   } catch (Exception e) { // catch - Exceptions
		e.printStackTrace();
	}
	}
    public ArrayList<Reel> readSavedFile() { // read saved file pass the arraylist(Reel object)

        ArrayList<Reel> gameList = new ArrayList<Reel>(); // create a arraylist

        try { // try-block
            Date date = new Date(); // date object from date class
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); // date format
            File file2 = new File(dateFormat.format(date) + ".txt"); // file name using the system date

            FileInputStream in = new FileInputStream(file2); // InputStreamReader stream for reading the line by line
            BufferedReader br = new BufferedReader(new InputStreamReader(in)); // read the text from a character-based input stream

            String line = br.readLine().trim();
            Reel r = null;

            while (line != null) { // while data is there
                if (line.isEmpty()) { // if line is emplty
                    System.out.println("Line is Empty.....");
                    line = br.readLine();
                } else { // if line is not empty
                    String line1 = line;

                }

            }
        } catch (Exception ex) { // catch - exception
            ex.printStackTrace();
        }

        return gameList;
    }

//    private void calculatePoints() {
//
//        Reel1Thread r1 = new Reel1Thread();
//        String a = r1.img;
//
//        Reel2Thread r2 = new Reel2Thread();
//        String b = r2.im2;
//
//        Reel3Thread r3 = new Reel3Thread();
//        String c = r3.img;
//        
//        
//
//        String x = "\\Plum.png";
//       
//       // System.out.println(x);
//       // System.out.println(x.substring(1));
//
//        if (a.equals(x)) {
//
//         //   int points = Images.valueOf(x.substring(2).split(".")[0]).getValueOfImage();//4;   
//            int points = 4;
//            System.out.println(points);
//
//            int plumPoints = points * 3;
//
//            int totalPoints = plumPoints + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//            creditArea.setText("Total Credits - " + totalPoints);
//        }
//        String y = "\\Seven.png";
//
//        if (a.equals(y)) {
//           // int points =  Images.valueOf(y.substring(2).split(".")[0]).getValueOfImage();//7;
//           int points = 7;
//            System.out.println(points);
//
//            int sevenPoints = points * 3;
//
//            int totalPoints = sevenPoints + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//            creditArea.setText("Total Credits - " + totalPoints);
//        }
//
//        String z = "\\Watermelon.png";
//
//        if (a.equals(z)) {
//           // int points = Images.valueOf(z.substring(2).split(".")[0]).getValueOfImage();//5;
//            int points = 5;
//           System.out.println(points);
//
//          
//            int watermelonPoints = points * 3;
//                //int mark = Images.valueOf(a.substring(0)).getValueOfImage();
//                //System.out.println(mark);
//            int totalPoints = watermelonPoints + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//            creditArea.setText("Total Credits - " + totalPoints);
//        }
//
//        String s = "\\Cherry.png";
//
//        if (a.equals(s)) {
//            //int points = Images.valueOf(s.substring(2).split(".")[0]).getValueOfImage();//2;
//            int points = 2;
//            System.out.println(points);
//
//            int cherryPoints = points * 3;
//
//            
//            int totalPoints = cherryPoints + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//            creditArea.setText("Total Credits - " + totalPoints);
//        }
//
//        String t = "\\Bell.png";
//
//        if (a.equals(t)) {
//           // int points = Images.valueOf(t.substring(2).split(".")[0]).getValueOfImage();//6;
//            int points = 6;
//           System.out.println(points);
//
//            int bellPoints = points * 3;
//
//           
//            int totalPoints = bellPoints + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//            creditArea.setText("Total Credits - " + totalPoints);
//        }
//        String l = "\\Lemon.png";
//
//        if (a.equals(l)) {
//          //  int points = Images.valueOf(l.substring(2).split(".")[0]).getValueOfImage();//3;
//           int points =  3;
//          System.out.println(points);
//
//            int lemonPoints = points * 3;
//            
//            int totalPoints = lemonPoints + Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
//            creditArea.setText("Total Credits - " + totalPoints);
//        }
//
//    }
    private void gameOver() { // when all 3 reels are clicked at leased once - game over

        if ((reel1Clicked >= 1) && (reel1Clicked >= 1) && (reel1Clicked >= 1)) {

            JOptionPane.showMessageDialog(null, "GAME OVER!!!!!!!!!!!!!!!!!!!!!!!!! All the Reels have been clicked");

        }
        spinCount--;
    }

    
    public void getWins() { // get wins

        totalWins++; // increment the total wins
        totalNoWins = totalNoWins + totalWins;
        System.out.println(totalNoWins);
        hmap.put(pointKey, totalNoWins); // put to the hmap the wins
        pointKey++; //increment the key
    }

    public void getLosses() { // get losses

        totalLosses++; // increment the total losses
        totalNoLosses = totalNoLosses + totalLosses;
        System.out.println(totalNoLosses);
        hmap.put(pointKey, totalNoLosses); // put to the hmap the losses
        pointKey++; //increment the key
    }

    private void playGame() { // play game method
        setInitialCredits(); //set the initial credits
        gameNo++;
        btnspin.setEnabled(true); // disable button
        btnadd.setEnabled(true);
        btnbetmax.setEnabled(true); 
        btnbetone.setEnabled(true);

        reinitializeforNewGame(); // when new game renitialize the values
    }

    private void reinitializeforNewGame() { // reinitialize values when new game is started

        totalLosses = 0;
        totalNoLosses = 0;
        totalNoWins = 0;
        totalWins = 0;
    }

    public void calculateAvg(){ // calculate avg
        
       int ca = Integer.parseInt(creditArea.getText().toString().split("-")[1].trim());
      //  System.out.println("credit for avg" + ca);
      //System.out.println("spin" + totalSpinCount);
        avg = (double)ca / (double)spinCount; // calculate avg  // CASTING - UPWARD CASTING
        System.out.println(ca);
        System.out.println("Spin Count"+spinCount);
        System.out.println("Aver "+ avg);
        hmap.put(pointKey, totalNoWins); // put to the hmap
        pointKey++; // increment points
    }
    public static void main(String[] args) { // main method

        mainFrame = new Reel(); 
        mainFrame.setVisible(true);

    } // end of main method

} // end of Reel class

