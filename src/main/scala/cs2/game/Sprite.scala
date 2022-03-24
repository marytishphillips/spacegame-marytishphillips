package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

abstract class Sprite (protected val img:Image, protected var pos:Vec2) {

  def move (direction:Vec2):Unit = { 
    pos.x += direction.x
    pos.y += direction.y
  }
  
  def moveTo (location:Vec2):Unit = {
    pos.x = location.x
    pos.y = location.y
  }
  
  def display (g:GraphicsContext):Unit = {
    g.drawImage(img,pos.x, pos.y)
  }
  
  def intersection (other:Sprite, imgSizeX:Int, imgSizeY:Int):Boolean = {
    val u1 = pos.y 
    val d1 = pos.y + imgSizeY
    val u2 = other.pos.y  
    val d2 = other.pos.y + imgSizeY
    val l1 = pos.x 
    val r1 = pos.x + imgSizeX
    val l2 = other.pos.x
    val r2 = other.pos.x + imgSizeX

    if (r2 >= l1 && r1 >= l2 && u2 <= d1 && u1 <= d2) true else false 
  }
}
