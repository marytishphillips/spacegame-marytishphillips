package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Player(avatar:Image, initPos:Vec2, private val bulletPic:Image) extends Sprite(avatar, initPos) {
  def moveLeft():Unit = {
    if(pos.x > 50) {pos.x -= 10}
  }

  def moveRight():Unit = {
    if(pos.x < 650) { pos.x += 10 }
  }

  def shoot():Bullet = {
    val pb = new Bullet(bulletPic, pos, Vec2(3,4))
    pb
  }
  def playerPosition():Vec2 = {
    new Vec2(pos.x,pos.y)
  }
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(avatar,pos.x, pos.y, 150, 200)
  }

}
