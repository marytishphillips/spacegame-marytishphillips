package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Background(backImage:Image, private var backImageSize:Vec2, initPos:Vec2, private var moveRate:Vec2) extends Sprite(backImage, initPos) {
  
  def timeStep():Unit = {
    // if off screen -- genereate new background
    pos.x += moveRate.x
  }

  override def display(g:GraphicsContext):Unit = {
    g.drawImage(backImage,pos.x, pos.y, backImageSize.x, backImageSize.y)
  }
}