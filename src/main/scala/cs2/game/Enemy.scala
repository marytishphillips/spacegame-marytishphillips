package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Enemy(pic:Image, initPos:Vec2, private val bulletPic:Image) extends Sprite(pic, initPos) {
 
  def shoot():Bullet = {
    val b = new Bullet(bulletPic, pos, new Vec2(0,5))
    b
  }

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

  override def clone():Enemy = {
    new Enemy(pic, new Vec2(pos.x,pos.y), bulletPic)
  }

  def enemyPosition():Vec2 = {
    new Vec2(pos.x, pos.y)
  }

  def enemyUpdate(q:Int, d:Int):Unit = {
    pos.x = q
    pos.y = d
  }

  //Boss Movement 
  def moveLeft():Unit = {
    if(pos.x > 50) {pos.x -= 10}
  }

  def moveRight():Unit = {
    if(pos.x < 650) { pos.x += 10 }
  }

  def moveDown():Unit = {
    if(pos.y < 550) { pos.y += 10}
  }

  def moveUp():Unit = {
    if(pos.y > 50) { pos.y -= 10}
  }
}
