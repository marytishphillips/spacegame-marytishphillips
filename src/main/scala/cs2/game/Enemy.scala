package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Enemy(pic:Image, initPos:Vec2, private val bulletPic:Image) extends Sprite(pic, initPos) {

  def shoot():Bullet = {
    val b = new Bullet(bulletPic, pos, new Vec2(0,5))
    b
  }
 /* def enemyTimeStep(inRange:Boolean):Boolean ={ 
    var r = scala.util.Random
    var returnval = true 
      if (inRange == true) {
        pos.x += r.nextInt(2)
        pos.y += r.nextInt(3)
      }
      else {
        pos.x -= r.nextInt(2)
        pos.y -= r.nextInt(3)
      }
      //counter += 1
      if (pos.y > 300) returnval = false
      if (pos.y < 10) returnval = true
      if (pos.x > 650) returnval = false
      if (pos.x < 10) returnval = true //pos.x = 10 + r.nextInt(600)
      returnval
  } */

  def enemyTimeStepX(inRangeX:Boolean):Boolean ={ 
    var r = scala.util.Random
    var returnvalX = true 
      if (inRangeX == true) {
        pos.x += r.nextInt(2)
      }
      else {
        pos.x -= r.nextInt(2)
        returnvalX = false
      }
      if (pos.x >= 720) returnvalX = false
      if (pos.x <= 10) returnvalX = true
      returnvalX
  }
  def enemyTimeStepY(inRangeY:Boolean):Boolean ={ 
    var r = scala.util.Random
    var returnvalY = true 
     if (inRangeY == true) {
        pos.y += r.nextInt(3)
      }
      else {
        pos.y -= r.nextInt(3)
        returnvalY = false
      }
      if (pos.y >= 300) returnvalY = false
      if (pos.y <= 10) returnvalY = true
      returnvalY
  }
  val enemyImgSize = 100
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(img,pos.x, pos.y, enemyImgSize, enemyImgSize)
  }

  def enemyPosition():Vec2 = {
    new Vec2(pos.x, pos.y)
  }

}
