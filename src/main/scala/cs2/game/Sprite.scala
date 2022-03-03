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
    
}
