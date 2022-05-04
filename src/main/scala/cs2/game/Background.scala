package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Background(backImage:Image, initPos:Vec2, moveRate:Vec2) extends Sprite(backImage, initPos) {
  def timeStep():Unit = {
    // if off screen -- genereate new background
    pos.x += moveRate.x
  }
}