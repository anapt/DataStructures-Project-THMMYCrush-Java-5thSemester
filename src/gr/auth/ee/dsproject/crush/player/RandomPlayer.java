package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;
import java.util.ArrayList;

/**
 * <p>
 * Developed by:
 * </p>
 * 
 * </p>
 * Anastasia Pachni Tsitiridou <br>
 * XXXX || aipachni@ece.auth.gr <br>
 * t: XXX XXX XXXX
 * </p>
 * 
 * <p>
 * Konstantinos Flevarakis <br>
 * XXXX || flevarks@ece.auth.gr <br>
 * t: XXX XXX XXXX
 * </p>
 * 
 * @author Pachni Tsitiridou A
 *
 */

public class RandomPlayer implements AbstractPlayer
{
  private int id;
  private String name;
  private int score;
  
  /**
   * Constructor
   * @param pid			used to initialize variable id
   */
  public RandomPlayer(Integer pid){
	  id = pid;
  }
  /**
   * player's id
   * @return 			variable id 
   */
  public int getId(){
	  return id;
  }
  /**
   * 
   * @return			variable name
   */
  public String getName(){
	  return name;
  }
  
  /**
   * 
   * @return			variable score
   */
  public int getScore(){
	  return score;
  }
  
  /**
   * 
   * @param newValue	sets variable id
   */
  public void setId(int newValue){
	  id = newValue;
  }
  /**
   * 
   * @param newValue	sets variable name
   */
  public void setName(String newValue){
	  name = newValue;
  }
  
  /**
   * 
   * @param newValue	set variable score
   */
  public void setScore(int newValue){
	  score = newValue;
  }

  /**
   * Randomly chooses a move and returns the previous and future coordinates of the tile.
   * @param availableMoves 
   * @param board
   * @return 			1x4 array which contains the previous x,y coordinates of the
   * 					tile and the new x,y coordinates of the tile. In that order.
   */
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {
	  // next[] is the return value of getNextMove
	  int next[] = new int[4];
	  int min = 0;
	  int max= availableMoves.size();
	
	  // randomly picking an element from the availableMoves ArrayList
	  int random = min + (int)(Math.random() * (max - min) );
	  
	  
	  int[] former = {0,0,0};
	  
	  /* 
	   * calling getRandomMove from class CrushUtilities package crush.
	   * passing the randomly chosen index element
	   * this class returns an array with contains the x y coordinates and the
	   * direction the chosen tile should move.
	   */
	  
	  former = CrushUtilities.getRandomMove(availableMoves, random);
	  
	  /*
	   * using a while loop to check if the randomly chosen move is 
	   * within bounds of the board
	   */
	  boolean clear = true;
	  while (clear == true){
		  clear = false;
		  if (former[0]==0 && former[2]==CrushUtilities.LEFT){
			  clear = true;
			  former = CrushUtilities.getRandomMove(availableMoves, random);
		  }
		  if (former[0]==CrushUtilities.NUMBER_OF_COLUMNS-1 && former[2]==CrushUtilities.RIGHT){
			  clear = true;
			  former = CrushUtilities.getRandomMove(availableMoves, random);
		  }
		  if (former[0]==CrushUtilities.NUMBER_OF_ROWS-1 && former[2]==CrushUtilities.UP){
			  clear = true;
			  former = CrushUtilities.getRandomMove(availableMoves, random);
		  }
		  if (former[0]==0 && former[2]==CrushUtilities.DOWN){
			  clear = true;
			  former = CrushUtilities.getRandomMove(availableMoves, random);
		  }
		  
	  }
	  
	  
	  /* 
	   * the first 2 elements of the array are the current coordinates of the tile.
	   * passing their value to the 2 first elements of array "next"
	   * the "next" array must contain the current and future coordinates of the tile
	   * [currentX , currentY, nextX, nextY]
	   */
	  
	  next[0]=former[0];
	  next[1]=former[1];

	  /*
	   * calculating the future coordinates of the tile based on the 3rd element of
	   * former[] which shows the direction the tile should move
	   * either x or y changes 
	   */
	 
	  switch (former[2]) {
	  	case CrushUtilities.LEFT:
		  next[2]=former[0]-1;
		  next[3]=former[1];
		  break;
	  	case CrushUtilities.RIGHT:
		  next[2]=former[0]+1;
		  next[2]=former[1];
		  break;
	  	case CrushUtilities.UP:
		  next[3]=former[1]+1;
		  next[2]=former[0];
		  break;
	  	case CrushUtilities.DOWN:
		  next[3]=former[1]-1;
		  next[2]=former[0];
		  break;
	  }

	  
	  return next;


  }

}
