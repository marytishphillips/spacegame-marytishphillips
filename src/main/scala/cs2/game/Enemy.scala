package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Enemy(pic:Image, initPos:Vec2, private val bulletPic:Image) extends Sprite(pic, initPos) {

  def shoot():Bullet = {
    val b = new Bullet(bulletPic, pos, new Vec2(0,5))
    b
  }
  val enemyImgSize = 100
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(img,pos.x, pos.y, enemyImgSize, enemyImgSize)
  }

  def enemyPosition():Vec2 = {
    new Vec2(pos.x, pos.y)
  }

}
