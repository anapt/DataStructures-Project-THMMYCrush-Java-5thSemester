package gr.auth.ee.dsproject.crush.board;

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

public class Tile
{

  protected int id;
  private int x;
  private int y;
  private int color;
  private boolean mark;

  /**
   * Constructor. Initializing each class variable
   * @param a			initializes id
   * @param b			initializes x
   * @param c			initializes y
   * @param d			initializes color
   * @param e			initializes mark
   */
  public Tile(int a, int b, int c, int d, boolean e){
	  id = a;
	  x = b;
	  y = c;
	  color = d;
	  mark = e;
  }
  
  /**
   * tile's id
   * @return 			variable id
   */
  public int getId(){
	  return id;
  }
  
  /**
   * 
   * @return			variable x
   */
  public int getX(){
	  return x;
  }
  
  /**
   * 
   * @return			variable y
   */
  public int getY(){
	  return y;
  }
  
  /**
   * 
   * @return			variable color
   */
  public int getColor(){
	  return color;
  }
  
  /**
   * 
   * @return			variable mark
   */
  public boolean getMark(){
	  return mark;
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
   * @param newValue	sets variable x
   */
  public void setX(int newValue){
	  x = newValue;
  }
  
  /**
   * 					
   * @param newValue	sets variable y
   */
  public void setY(int newValue){
	  y = newValue;
  }
  
  /**
   * 					
   * @param newValue	sets variable color
   */
  public void setColor(int newValue){
	  color = newValue;
  }
  
  /**
   * 					
   * @param newValue	sets variable mark
   */
  public void setMark(boolean newValue){
	  mark = newValue;
  }
  

}
