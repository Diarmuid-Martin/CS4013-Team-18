//just throwing up some stuff to see how things work in github



/**
 * This class generates a random number between 100 and 500 and gets the user to guess what number it is.
 * The methods will generate JFrames with labels and buttons to play the game.
 * There is a start screen with a help button which explains how to play and a start button.
 * The play button brings you to the play screen where you enter your guesses to try find the number.
 * There is a max of 10 guesses and the class tells the user if their guess is too high or too low. 
 * If you guess the number correctly in 10 guesses or less you win!
 * Take more than 10 guesses and you lose.
 * 
 * Diarmuid Martin 19242913
 * 03/04/2020
 */

package guibased.guessing.game;


import java.awt.Color;
import javax.swing.*;  
import java.awt.event.ActionEvent;
import java.util.Random;

    public class BaseGUI   {  
        
    
    private static final int Int2Guess = RandomInt(); //generates a random number
    private static int count=1;
    
    public static void main(String[] args) {  
        
    
    JFrame mainScreen=new JFrame();//creating instance of JFrame  
   
    JButton play=new JButton("Play Game");//creating instance of JButton 
    JButton help=new JButton("Help");
    JLabel Title= new JLabel("The Guessing Game!");
    JButton exit= new JButton("Exit");
       
    play.setBounds(50,70,100,40);//x axis, y axis, width, height  
    Title.setBounds(40,0,150,80);//x axis, y axis, width, height  
    help.setBounds(60,120,80,40);
    exit.setBounds(60,170,80,30);
    
    mainScreen.add(play);//adding button in JFrame  
    mainScreen.add(Title); 
    mainScreen.add(help);
    mainScreen.add(exit);
  
    mainScreen.setSize(215, 260);
    mainScreen.setLayout(null);//using no layout managers  
    mainScreen.setVisible(true);//making the frame visible 
    mainScreen.setLocationRelativeTo(null);
    
        play.addActionListener((ActionEvent e) -> {
            
        mainScreen.setVisible(false);
        
        JFrame playScreen=new JFrame();//creating instance of JFrame  
       
        JTextField Guess = new JTextField();
         Guess.setBounds(50,60, 100,40);
         
        JLabel Instructions= new JLabel("Enter your guess here!");
         Instructions.setBounds(40,10,150,40);
         
        JButton enter= new JButton("Enter");
         enter.setBounds(50,145,100,40);
         
        JLabel result= new JLabel("Waiting your answer...");
         result.setBounds(35, 70, 400, 100);
         
         exit.setBounds(60,200,80,30);
         
        JLabel NumOfGuesses =  new JLabel("This is guess #1");
        NumOfGuesses.setBounds(150, 30, 400, 100);
        
        JLabel PrevGuess =  new JLabel("These are your previous guesses");
        PrevGuess.setBounds(275, 10, 400, 40);
        
        JLabel guess1 =  new JLabel("Guess #1:");
        guess1.setBounds(275, 30, 400, 40);
        
        JLabel guess2 =  new JLabel("Guess #2:");
        guess2.setBounds(275, 50, 400, 40);
        
        JLabel guess3 =  new JLabel("Guess #3:");
        guess3.setBounds(275, 70, 400, 40);
        
        JLabel guess4 =  new JLabel("Guess #4:");
        guess4.setBounds(275, 90, 400, 40);
        
        JLabel guess5 =  new JLabel("Guess #5:");
        guess5.setBounds(275, 110, 400, 40);
        
        JLabel guess6 =  new JLabel("Guess #6:");
        guess6.setBounds(275, 130, 400, 40);
        
        JLabel guess7 =  new JLabel("Guess #7:");
        guess7.setBounds(275, 150, 400, 40);
        
        JLabel guess8 =  new JLabel("Guess #8:");
        guess8.setBounds(275, 170, 400, 40);
        
        JLabel guess9 =  new JLabel("Guess #9:");
        guess9.setBounds(275, 190, 400, 40);
        
        JLabel guess10 =  new JLabel("Guess #10:");
        guess10.setBounds(275, 210, 400, 40);
        
        
        playScreen.add(guess9);
        playScreen.add(guess8);
        playScreen.add(guess7);
        playScreen.add(guess6);
        playScreen.add(guess5);
        playScreen.add(guess4);
        playScreen.add(guess3);
        playScreen.add(guess2);
        playScreen.add(guess1);
        playScreen.add(guess10);
        playScreen.add(PrevGuess);
        playScreen.add(Instructions);
        playScreen.add(exit);
        playScreen.add(result);
        playScreen.add(Guess);
        playScreen.add(NumOfGuesses);
        playScreen.add(enter);
        
        playScreen.setSize(500,290); 
        playScreen.setLayout(null);//using no layout managers  
        playScreen.setVisible(true);//making the frame visible   
        playScreen.setLocationRelativeTo(null);
    enter.addActionListener((ActionEvent a) -> {
      
    
      try {
       String text = Guess.getText();
       int guess = Integer.parseInt(text);
       Guess.setText("");
       
       NumOfGuesses.setText("This is guess #" + (count+1));       
       if(guess < 100 || guess > 500) // insures guess was between 100 and 500
       result.setText("Try between 100 - 500");
       else if (guess!=Int2Guess)
       {
        if(guess<Int2Guess)
        result.setText("Unlucky! You were too low");
        else
         result.setText("Unlucky! You were too high");                   
        }
       
       if(guess == Int2Guess)
       {
       result.setText("Congratulations! You won!");
       result.setForeground(Color.green);
       Guess.setVisible(false);
       enter.setVisible(false);
       Instructions.setVisible(false);
       NumOfGuesses.setVisible(false);
       }
       
       if (count == 10 && guess != Int2Guess)
       {
       result.setText("You ran out of guesses! You lost");  
       result.setForeground(Color.red);
       Guess.setVisible(false);
       enter.setVisible(false);
       Instructions.setVisible(false);
       NumOfGuesses.setVisible(false);
       }
       
       if (count ==1 )
       {
           guess1.setText("Guess #1: " + guess);
       }
       
       if (count ==2 )
       {
           guess2.setText("Guess #2: " + guess);
       }
       
       if (count ==3 )
       {
           guess3.setText("Guess #3: " + guess);
       }
       
       if (count ==4 )
       {
           guess4.setText("Guess #4: " + guess);
       }
       
       if (count ==5 )
       {
         guess5.setText("Guess #5: " + guess);  
       }
              
       if (count ==6 )
       {
          guess6.setText("Guess #6: " + guess); 
       }
       
       if (count ==7 )
       {
           guess7.setText("Guess #7: " + guess);
       }
       
       if (count ==8 )
       {
         guess8.setText("Guess #8: " + guess);  
       }
       
       if (count ==9 )
       {
         guess9.setText("Guess #9: " + guess);  
       }
       
       if (count ==10 )
       {
          guess10.setText("Guess #10: " + guess); 
       }
       count++; 
       
       } catch (NumberFormatException er) {
         result.setText("Your input was invalid");
         Guess.setText("");
         NumOfGuesses.setText("This is guess #" + count); 
        } 
            
        });
        
     });
        
     help.addActionListener((ActionEvent e) -> {
         
        mainScreen.setVisible(false);
        
        JOptionPane.showMessageDialog(null, "This code randomly produces an interger between 100 and 500, it is your job to guess that number in under 10 guesses. Good Luck!");
        JOptionPane.showMessageDialog(null, "How to play: Enter any whole number between 100-500 in numerical form. The game will tell you if your guess was too high or too low.");
        
        mainScreen.setVisible(true);
        });
     
     
     exit.addActionListener((ActionEvent e) -> {
          System.exit(0);
          });
     }
    
    public static int RandomInt()
    {      
        Random randomNumbers = new Random(); // random number generator
        int  face = 100 + randomNumbers.nextInt(400);
        return face;   
    }
    
    }  
    



