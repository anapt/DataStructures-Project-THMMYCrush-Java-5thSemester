package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;
import java.util.ArrayList;

/**
 * * <p>
 * Developed by:
 * </p>
 *
 * <p>
 * Konstantinos Flevarakis <br>
 * XXXX || flevarks@ece.auth.gr <br>
 * t: XXX XXX XXXX
 * </p>
 * </p>
 * Anastasia Pachni Tsitiridou <br>
 * XXXX || aipachni@ece.auth.gr <br>
 * t: XXX XXX XXXX
 * </p>
 *
 * @author Pachni Tsitiridou A
 *
 */

public class HeuristicPlayer implements AbstractPlayer
{

  int score;
  int id;
  String name;

  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    score = 0;
  }

  @Override
  public String getName ()
  {

    return "evaluation";

  }

  @Override
  public int getId ()
  {
    return id;
  }

  @Override
  public void setScore (int score)
  {
    this.score = score;
  }

  @Override
  public int getScore ()
  {
    return score;
  }

  @Override
  public void setId (int id)
  {

    this.id = id;

  }

  @Override
  public void setName (String name)
  {

    this.name = name;

  }

  @Override
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {

    int[] move = availableMoves.get(findBestMoveIndex(availableMoves, board));

    return calculateNextMove(move);

  }

  int[] calculateNextMove (int[] move)
  {

    int[] returnedMove = new int[4];

    if (move[2] == CrushUtilities.UP) {
      // System.out.println("UP");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0];
      returnedMove[3] = move[1] + 1;
    }
    if (move[2] == CrushUtilities.DOWN) {
      // System.out.println("DOWN");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0];
      returnedMove[3] = move[1] - 1;
    }
    if (move[2] == CrushUtilities.LEFT) {
      // System.out.println("LEFT");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0] - 1;
      returnedMove[3] = move[1];
    }
    if (move[2] == CrushUtilities.RIGHT) {
      // System.out.println("RIGHT");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0] + 1;
      returnedMove[3] = move[1];
    }
    return returnedMove;
  }

  /**
   * calls moveEvaluation for each move
   * and finds the move with the highest evaluation
   * than returns the index of that move.
   *
   * @param availableMoves
   * @param board
   * @return
   */
  int findBestMoveIndex (ArrayList<int[]> availableMoves, Board board)
  {
	  double[] evals = new double[availableMoves.size()];
	  double max = 0;
	  int index = 0;
	  for (int i = 0; i < availableMoves.size(); i++){
		  evals[i] = moveEvaluation(availableMoves.get(i), board);
		  if (i==0){
			  max = evals[0];
			  index = 0;
		  }
		  else{
			  if (evals[i] >= max){
				  max = evals[i];
				  index = i;
			  }
		  }
	  }
	  return index;

  }


  /**
   * first stage of evaluation
   * scans the entire board 2 times and finds the horizontal and vertical
   * combinations
   * @param board
   * @return
   */
  int calculateTilesThatWillCrush(Board board){
	  int points = 0;
	  // y is the row index
	  // x is the column index

    // scanning the board for horizontal combinations
    // horizontal combinations look like: (x,y) (x+1,y) (x+2,y)

    // double loop
    for (int y=0; y< CrushUtilities.NUMBER_OF_PLAYABLE_ROWS; y++){
      // NUMBER_OF_COLUMNS = 10, array index goes up to 9
      // worst case scenario combination at [7 8 9]
      for (int x=0; x <= CrushUtilities.NUMBER_OF_COLUMNS - 3; x++){
        int aColor = board.giveTileAt(x,y).getColor();
        int bColor = board.giveTileAt(x+1, y).getColor();
        int cColor = board.giveTileAt(x+2, y).getColor();

        if ((aColor == bColor) && (bColor == cColor)){
          // I have a 3 color combination
          points = points +3;
          System.out.println("3 hor" + points);

          // checking for 4 candies combination
          // first checking boundaries
          // then getting x+3 candy
          // worst case scenario combination at [6 7 8 9]

          if (x <= CrushUtilities.NUMBER_OF_COLUMNS - 4){
            int dColor = board.giveTileAt(x+3,y).getColor();
            if ( bColor == dColor){
              // I have a 4 color combination
              points = points +4;
              System.out.println("4 hor" + points);

              // checking for 5 candies color combination
              // first checking if within boundaries
              // then getting x+4 candy
              // worst case scenario combination at [5 6 7 8 9]

              if (x <= CrushUtilities.NUMBER_OF_COLUMNS -5){
                int eColor = board.giveTileAt(x+4,y).getColor();
                if (eColor == bColor){
                  points = points +5;
                  System.out.println("5 hor" + points);

                }
              }
            }
          }
        }// check for 3 closes here
      }
    } // loop for HORIZONTAL color combinations closes HERE.

    // scanning the board for VERTICAL combinations
    // vertical combinations look like (y,x) (y+1,x) (y+2,x)
    for (int x=0; x< CrushUtilities.NUMBER_OF_COLUMNS; x++){
      for (int y=0; y<= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - 3; y++){
        // worst case scenario combination at [7 8 9]
        int aColor = board.giveTileAt(x,y).getColor();
        int bColor = board.giveTileAt(x,y+1).getColor();
        int cColor = board.giveTileAt(x,y+2).getColor();
        if ((aColor == bColor) && (bColor==cColor)){
          // I have a 3 candy combination
          points = points + 3 ;
          System.out.println("3 ver" + points);

          //checking if there is a 4 candy combination
          // first checking if within boundaries
          // then getting y+3 candy
          // worst case scenario combination at [6 7 8 9]
          if (y <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS -4){
            int dColor = board.giveTileAt(x,y+3).getColor();
            if (dColor == bColor){
              // I have a 4 color combination
              points = points + 4;
              System.out.println("4 ver " + points);

              // checking for 5 candies color combination
              // first checking if within boundaries
              // then getting the candy at y+4
              // worst case scenario combination at [5 6 7 8 9]
              if (y<= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - 5){
                // I am within boundaries
                int eColor = board.giveTileAt(x, y+4).getColor();
                if (eColor == bColor){
                  // I have a 5 color combination
                  points = points +5;
                  System.out.println("5 ver " + points);
                }
              }
            }
          }
        }
      }
    } // loop for VERTICAL combinations closes HERE

    return points;
  }
  /**
   * second stage of evaluation
   * checks how far the tile is from the bottom of the board
   * (where y=0) and returns that as reward
   * @param move
   * @return
   */
  double heightOfMove(int[] move){
	  int[] coords = calculateNextMove(move);
	  // coords now contains the old and new coordinates of the tile
	  // [xOld, yOld, xNew, yNew]
	  return ((10 - coords[3]));

  }
  /**
   * third stage of evaluation
   * checks if move is vertical or horizontal
   * @param move
   * @return
   */
  boolean vertOrHor(int[] move){
	  int[] coords = calculateNextMove(move);
	  if (coords[0] == coords[2]){
		  return true; // if x1 == x2 than move is VERTICAL
	  } else {
		  return false; // else move is HORIZONTAL
	  }
  }


  /**
   * keeps a sum of the number of candies with the same color
   * @param colorCode
   * @param counters
   * @return
   */
  int[] colorCheck(int colorCode, int[] counters){


    switch(colorCode){
    case CrushUtilities.RED:
      counters[0]++;
    case CrushUtilities.GREEN:
      counters[1]++;
    case CrushUtilities.BLUE:
      counters[2]++;
    case CrushUtilities.YELLOW:
      counters[3]++;
    case CrushUtilities.BLACK:
      counters[4]++;
    case CrushUtilities.PURPLE:
      counters[5]++;
    case CrushUtilities.CYAN:
      counters[6]++;
    }

    return counters;
  }


  /**
   * forth stage of evaluation
   *
   * checks a 3x3 array around the tile taking into account all places a tile
   * may be, then calls colorCheck that keeps a sum of the tiles with the same color
   *
   * @param width
   * @param height
   * @param board
   * @return
   */
  int[] sameColorInProximity(int width, int height, Board board){
    int[] counters = {0,0,0,0,0,0,0};
    boolean flagHor = true;
    boolean flagVer = true;
    if ((width-1 >= 0) && (width+1 < 10)){
      // array is well within bounds HORIZONTALLY
      flagHor = false;
    }
    if ((height - 1 >= 0 )&&(height +1 < 10)){
      // array is well within bounds VERTICALLY
      flagVer = false;
    }

    // case 1 -----> REGULAR
    if ( (flagVer == false) && (flagHor == false)){
      for (int y=height-1; y<=height+1; y++){
        for (int x=width-1; x<=width+1; x++){
          int colorCode = board.giveTileAt(x, y).getColor();
          counters = colorCheck(colorCode, counters);
        }
      }
    }

    //case 2 -----> CORNERS
    if ((flagVer == true) && (flagHor == true)){
      // candy is located in one of the four corners

      if ((height == 0) && (width == 0)){
        // candy is in the bottom left corner
        for (int y=height; y<height+3; y++){
          for (int x=width; x<width+3; x++){
            int colorCode = board.giveTileAt(x, y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }


      if ((height == 0) && (width == CrushUtilities.NUMBER_OF_COLUMNS -1)){
        //candy is in the bottom right corner
        System.out.println("bottom right corner");
        for (int y=height; y<height+3; y++){
          for (int x=width; x<width-3; x--){
            int colorCode = board.giveTileAt(x, y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }


      if( ( height == CrushUtilities.NUMBER_OF_PLAYABLE_ROWS -1) && (width ==0)){
        // candy is in the top left corner
        System.out.println("top left corner");
        for (int y=height; y<height-3; y--){
          for (int x=width; x<width+3; x++){
            int colorCode = board.giveTileAt(x, y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }


      if ((height == CrushUtilities.NUMBER_OF_PLAYABLE_ROWS -1) && (width == CrushUtilities.NUMBER_OF_COLUMNS -1)){
        // candy is in the top right corner
        System.out.println("top right corner");
        for (int y=height; y<height-3; y++){
          for (int x=width; x<width-3; x--){
            int colorCode = board.giveTileAt(x, y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }


    }// if statement for 4 corners

    // case 3 ----> BORDERS
    if ((flagVer == false) && (flagHor == true)){
      // candy is located either in width=0 or width=CrushUtilities.NUMBER_OF_COLUMNS-1
      if (width == 0){
        for (int y=height-1; y<=height +1; y++){
          for (int x=width; x<width+3; x++){
            int colorCode=board.giveTileAt(x,y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }
      if (width == CrushUtilities.NUMBER_OF_COLUMNS-1){
        for (int y=height-1; y<=height +1; y++){
          for (int x=width; x<width-3; x--){
            int colorCode=board.giveTileAt(x,y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }
    }
    if ((flagVer == true) && (flagHor== false)){
      // candy is located either in height = 0 or height=CrushUtilities.NUMBER_OF_PLAYABLE_ROWS-1
      if (height==0){
        for (int y=height; y<height+3; y++){
          for (int x=width-1; x<=width+1; x++){
            int colorCode=board.giveTileAt(x,y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }
      if (width == CrushUtilities.NUMBER_OF_PLAYABLE_ROWS-1){
        for (int y=height; y<height -3; y--){
          for (int x=width-1; x<=width+1; x++){
            int colorCode=board.giveTileAt(x,y).getColor();
            counters = colorCheck(colorCode, counters);
          }
        }
      }

    }
    return counters;

  }


  /**
   * EVALUATION
   * evaluates each move using the 4 aforementioned ways
   * @param move
   * @param board
   * @return
   */
  double moveEvaluation (int[] move, Board board)
  {

	  Board aBoard = CrushUtilities.boardAfterFirstMove(board, move);
	  int firstEval = calculateTilesThatWillCrush(aBoard);
	  double secondEval = heightOfMove(move);
	  boolean checkVorH = vertOrHor(move);
	  int thirdEval = 0;
	  if (checkVorH == true){
		  //than move is VERTICAL
		  thirdEval = 2;
	  }else{
		  // move is HORIZONTAL
		  thirdEval = 0;
	  }

	  double fourthEval = 0;
	  int[] coords = calculateNextMove(move);
	  // getting the color of the Tile that moved
	  int [] counters = sameColorInProximity(coords[2], coords[3], aBoard);
	  int tempColor = aBoard.giveTileAt(coords[2],coords[3]).getColor();
	  // checking the Color of the Tile and rewarding it with the same amount
	  // of points as the number of same color tiles in the 3x3 area around it
	  switch(tempColor){
	  case CrushUtilities.RED:
	    fourthEval=counters[0];
	  case CrushUtilities.GREEN:
	    fourthEval=counters[1];
	  case CrushUtilities.BLUE:
	    fourthEval=counters[2];
	  case CrushUtilities.YELLOW:
	    fourthEval=counters[3];
	  case CrushUtilities.BLACK:
	    fourthEval=counters[4];
	  case CrushUtilities.PURPLE:
	    fourthEval=counters[5];
	  case CrushUtilities.CYAN:
	    fourthEval=counters[6];
	  }

	  //calculating final evaluation and returning that value
	  double eval = firstEval + secondEval + thirdEval + (fourthEval/2);
	  return eval;

  }



}
