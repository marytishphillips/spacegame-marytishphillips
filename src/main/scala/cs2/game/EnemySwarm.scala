package cs2.game
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.VBox
import scalafx.scene.image.Image
import scala.util.Random
import scala.collection.mutable.Buffer

class EnemySwarm(val nRows:Int, val nCols:Int){
  val bullImgPath = getClass().getResource("/images/Bullet.png")
  val bullImg = new Image(bullImgPath.toString)
  val enemyImgPath = getClass().getResource("/images/Enemy.png")
  val enemyImg = new Image(enemyImgPath.toString)
  var swarm = Buffer[Enemy]()
  for(x <- 0 to nRows) {
      for(y <- 0 to nCols) {
        swarm += new Enemy(enemyImg,new Vec2((x*100)+70,(y*100)+30),bullImg)
      }
    }
  def display(g:GraphicsContext):Unit = {
    for (m <- swarm) {
      m.display(g)
    }
  }

  def shoot():Bullet = { 
    val r = scala.util.Random
    var rn = r.nextInt(swarm.length)
    val nb = new Bullet(bullImg, swarm(rn).enemyPosition(),new Vec2(0,5))
    nb
  }

}
