package cs2.game
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.VBox
import scalafx.scene.image.Image

/** contains the control and logic to present a coordinated set of Enemy objects.
 *  For now, this class generates a "grid" of enemy objects centered near the
 *  top of the screen.
 *
 *  @param nRows - number of rows of enemy objects
 *  @param nCols - number of columns of enemy objects
 */
class EnemySwarm(val nRows:Int, val nCols:Int) {
  /** method to display all Enemy objects contained within this EnemySwarm
   *
   *  @param g - the GraphicsContext to draw into
   *  @return none/Unit
   */
  val bullImgPath = getClass().getResource("/images/Bullet.png")
  val bullImg = new Image(bullImgPath.toString)
  val enemyImgPath = getClass().getResource("/images/Enemy.png")
  val enemyImg = new Image(enemyImgPath.toString)
  var swarm = List[Enemy]()
  for(x <- 0 to nRows) {
      for(y <- 0 to nCols) {
        swarm ::= new Enemy(enemyImg,new Vec2((x*100)+100,(y*100)+50),bullImg)
      }
    }
  def display(g:GraphicsContext):Unit = {
    for (m <- swarm) {
      m.display(g)
    }
  }





  /** overridden method of ShootsBullets. Creates a single, new bullet instance
   *  originating from a random enemy in the swarm. (Not a bullet from every
   *  object, just a single from a random enemy)
   *
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
  def shoot():Bullet = { ???
    //val s = new Bullet(img, ) ??? collection of enemies?
    //selects random enemy in which to shoot a bullet from.

    //pick random number between 0 and length of swarm list.  
  }

}
