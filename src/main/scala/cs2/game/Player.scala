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

  def moveDown():Unit = {
    if(pos.y < 550) { pos.y += 10 }
  }

  def moveUp():Unit = {
    if(pos.y > 50) { pos.y -= 10}
  }

  def shoot():Bullet = {
    val pb = new Bullet(bulletPic, pos, Vec2(3,4))
    pb
  }
  def playerPosition():Vec2 = {
    new Vec2(pos.x,pos.y)
  }
  def playerUpdate(q:Int, d:Int):Unit = {
    pos.x = q
    pos.y = d
  }
  override def clone():Player = {
    new Player(avatar, new Vec2(pos.x,pos.y), bulletPic)
  }
  val playerSizeX = 150
  val playerSizeY = 200
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(avatar,pos.x, pos.y, playerSizeX, playerSizeY)
  }
  

}
