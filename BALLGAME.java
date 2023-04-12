import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;

     
   
public class BALLGAME extends Application
{
  
   
   boolean isVisible = true;
   private int Circles = 0;
   Label l = new Label();
   // basic stage
   public void start(Stage stage)
   {
      
      BorderPane bp = new BorderPane();// Adding border pane
      GridPane gp = new GridPane();// adding grid panes 
      
      bp.setAlignment(l,Pos.CENTER);
      bp.setTop(l);
      
      
      gp.setHgap(10);// Setting gaps between the border panes 
      gp.setVgap(10);
      gp.setAlignment(Pos.CENTER);// Setting pos to center
      
     
      
      for(int i=0;i<4;i++)// FOr loop to make the board gamepanes 
    {
      for(int j=0;j<4;j++)
       {
        GamePane Game = new GamePane();
        Board[i][j] = Game;
        gp.add(Board[i][j],i,j);
        
        if( i == 0 & j == 2){
        Board[i][j].setBallVis(false);// Setting the ball in the 0,2 position not visiblw
        
        }
              }
    }
        
        isMoveable(Board);// Calling the is movable method so that it updates after every move and draws it 
        for(int i=0;i<4;i++){
     for(int j=0;j<4;j++){
      Board[i][j].draw();
      
     }
    
    }

    
       
       bp.setCenter(gp);// Setting the gridpane to the center of the borderpane 
       
       
      
      Scene scene = new Scene(bp, 600, 600); // creating a scene
      stage.setScene(scene); // setting our scene to the scene from above
      stage.setTitle("Ball Game"); // our title is Ball Game
      stage.show(); // shows our stage
         
   }
      
      GamePane[][] Board = new GamePane[4][4];// Board is a 4x4 playing field 
      
       int BallCount = 0;// variable for the ballcount
       int Moves = 0;// variabelf or the moves
      
      
      public class GamePane extends GridPane
    {
    private  Button Left = new Button();// Variables for buttons 
    private Button Right = new Button();
    private Button Bottom = new Button();
    private Button Top = new Button();
   
    boolean LeftButtonVis = false;// Boolean variables for visibility for the buttons and the balls
    boolean TopButtonVis = false;
    boolean RightButtonVis = false;
    boolean BottomButtonVis = false;
    boolean CircleVis = true;
   
    
    
    GridPane GridPane = new GridPane();// New gridpane 
    Canvas canvas = new Canvas(80,80);// Canvas is 80x80
    GraphicsContext gc = canvas.getGraphicsContext2D();// adding gc to the canvas 
    
    public GamePane(){
      super();
     
     Left.setPrefSize(20,80);// Setting the button's sizes
     Top.setPrefSize(80,20);

     Right.setPrefSize(20,80);
         
     
     Bottom.setPrefSize(80,20);
     
       Top.setOnAction(new ButtonListener());// Setting on action each button 
       Right.setOnAction(new ButtonListener());
       Left.setOnAction(new ButtonListener());
       Bottom.setOnAction(new ButtonListener());

     GridPane.add(Top,1,0);// Setting the buttons to their specific location onto the gridpanes 
     GridPane.add(Left,0,1);
     GridPane.add(Right,2,1);
     GridPane.add(Bottom,1,2);
     GridPane.add(canvas,1,1);
     
     gc.setFill(Color.BLACK);// setting the ball to color black 
     gc.fillOval(0,0,80,80);// Filling the oval in the canvas 
     
     
    
    
    getChildren().add(GridPane);// Adding gridpane to canvas 
    
   
   }
     public Button buttonSelected(char name){// Button method
    if(name == 't'){// if top equals t then retunr top Applies to rest of buttons 
    return Top;
    }
    if(name == 'l'){
    return Left;
    }
    if(name == 'b'){
    return Bottom;
    }
    if(name == 'r'){
    return Right;
    }
   return Right;
    }
    public void draw()
   
    {
         
         
         if(CircleVis){
         gc.fillOval(0,0,80,80);
         }
         else{
         gc.clearRect(0,0,80,80);
         }
     if(TopButtonVis){//if top button is selected set it to visible
      Top.setVisible(true);
     }
      else{
      Top.setVisible(false);// set to false 

      }
     if(LeftButtonVis){
      Left.setVisible(true);// if left is selected then left is set to visible
     }
      else{
      Left.setVisible(false);// set to false 

      }
     if(RightButtonVis){ // if right is selected then set it to true 
      Right.setVisible(true);
     }
      else{
      Right.setVisible(false);// sets to false if not 

      }
       if(BottomButtonVis){
      Bottom.setVisible(true); //if bottom is selected then set it to true 
     }
      else{
      Bottom.setVisible(false);// sets to false if not 

      }
            
   
   if(BallCount == 1 & Moves == 0){// if ball count and moves = zero then it says you win 
   l.setText("YOU WIN!!!!");

    }
    else if(Moves == 0){// if moves = 0 then you lost 
     l.setText("YOU LOST!!!");

    }
    else 
     l.setText("Balls Left: " +BallCount+                "   Possible Moves: "+Moves);// else it keeps track of balls and possible moves 
   }
 
  public boolean setBallVis(boolean CircleVis)// constructors for ball visibility and the buttons
  {
  this.CircleVis = CircleVis;
  return CircleVis;
  }
    //start 
    public boolean setTopButtonVis(boolean TopButtonVis)
    {
    this.TopButtonVis = TopButtonVis;
    return TopButtonVis;
    }
     
     public boolean setRightButtonVis(boolean RightButtonVis)
    {
    this.RightButtonVis = RightButtonVis;
    return RightButtonVis;
    }
    public boolean setLeftButtonVis(boolean LeftButtonVis)
    {
    this.LeftButtonVis = LeftButtonVis;
    return LeftButtonVis;
    }
   public boolean setBottomButtonVis(boolean BottomButtonVis)
    {
    this.BottomButtonVis = BottomButtonVis;
    return BottomButtonVis;
    }
    
   public boolean getBallVis()
   {
   return CircleVis;
   
   }
 //end 
 }
  
  public void isMoveable(GamePane[][] Board){
  BallCount = 0;
  Moves = 0;
  for(int i=0;i<4;i++){
   for(int j=0;j<4;j++)
   {
    if( Board[i][j].getBallVis() == true){
    BallCount++;
    }
    //Checks up
    if(j-2 > -1){// parameters for columns and checks spaces to move the ball to the space below that ball
     if(Board[i][j-1].getBallVis() == true && Board[i][j-2].getBallVis() == false && Board[i][j].getBallVis() == true)
     {
      Board[i][j].setBottomButtonVis(true);
      Moves++;// increases moves
      }else{
      Board[i][j].setBottomButtonVis(false);// Keeps button invisible until the paramters are met 
      }
     
    }
    //Checks Bottom 
       if(j+2 < 4){
     if(Board[i][j+1].getBallVis() == true && Board[i][j+2].getBallVis() == false && Board[i][j].getBallVis() == true)
     {
      Board[i][j].setTopButtonVis(true);
      Moves++;
      }else{
      Board[i][j].setTopButtonVis(false);// Keeps button invisible until the paramters are met 
      }
     
    }
    // Checks balls to the left  
    if(i-2 > -1){
     if(Board[i-1][j].getBallVis() == true && Board[i-2][j].getBallVis() == false && Board[i][j].getBallVis() == true)
     {
      Board[i][j].setRightButtonVis(true);
      Moves++;
      }else{
      Board[i][j].setRightButtonVis(false);// Keeps button invisible until the paramters are met 
      }
     
    }
    // checks balls to the right
    if(i+2 < 4){
     if(Board[i+1][j].getBallVis() == true && Board[i+2][j].getBallVis() == false && Board[i][j].getBallVis() == true)
     {
      Board[i][j].setLeftButtonVis(true);
      Moves++;// Counts Moves 
      }else{
      Board[i][j].setLeftButtonVis(false);// Keeps button invisible until the paramters are met 
      }
     
    }

  
  
  
   }
  
  }
  
  
  }
  
  public class ButtonListener implements EventHandler<ActionEvent>  
   {
      public void handle(ActionEvent e) 
      {
      for(int i=0;i<4;i++){
       for(int j=0;j<4;j++){
      if(e.getSource()== Board[i][j].buttonSelected('t')){
      Click(i,j,'t');// Getting the top button was clicked and sending a signal to the isMovable method to give it functionality for that specific button 
     }
     if(e.getSource()== Board[i][j].buttonSelected('b')){
      Click(i,j,'b');// same thing for bottom button
     }
     if(e.getSource()== Board[i][j].buttonSelected('l')){
      Click(i,j,'l');// same thing for left button 
     }
     if(e.getSource()== Board[i][j].buttonSelected('r')){
      
      Click(i,j,'r');// Same for right button 
     }

   
   }
  
  }

       
   }
}
  
  
  public void Click(int x, int y, char name){
   if(name == 't'){// if top button was clicked it checks the visibility of the ball below it and then sets the ball visible to true 2 spaces down 
   Board[x][y].setBallVis(false);
   Board[x][y+1].setBallVis(false);
   Board[x][y+2].setBallVis(true);
   } 
   if(name == 'b'){// sets ball 2 spaces up to visible and does the same thing above but for bottom button 
   Board[x][y].setBallVis(false);
   Board[x][y-1].setBallVis(false);
   Board[x][y-2].setBallVis(true);
   } 
    if(name == 'l'){ // sets ball 2 spaces right to visible and does the same thing above but for the left button 
   Board[x][y].setBallVis(false);
   Board[x+1][y].setBallVis(false);
   Board[x+2][y].setBallVis(true);
   } 
    if(name == 'r'){//sets ball 2 spaces left to visible and does the same thing above but for the right button
   Board[x][y].setBallVis(false);
   Board[x-1][y].setBallVis(false);
   Board[x-2][y].setBallVis(true);
   } 

  isMoveable(Board);// Re draws the board and calls the is movable method 
  for(int i=0;i<4;i++){
  for(int j=0;j<4;j++){
   Board[i][j].draw();
     }

   }
  
    }
     
       
   public static void main(String[] args)
   {
      launch(args); // launches the code
    }
   }


