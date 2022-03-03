package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Bullet(pic:Image, initPos:Vec2, private var vel:Vec2) extends Sprite(pic, initPos) {
  def timeStep():Unit = {
    pos.x += vel.x
    pos.y += vel.y
  }
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(img,pos.x, pos.y, 50, 50)
  }
}
