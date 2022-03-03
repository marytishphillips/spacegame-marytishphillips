package cs2.game
import cs2.util.Vec2

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color
import scalafx.scene.image.Image
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyCode

/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object SpaceGameApp extends JFXApp {    
  stage = new JFXApp.PrimaryStage {
      title = "Space Game!"
      scene = new Scene (800,800) {
          val canvas = new Canvas(width.value, height.value)
          content = canvas
          val playImgPath = getClass().getResource("/images/Player.png")
          val playImg = new Image(playImgPath.toString)
          val bullImgPath = getClass().getResource("/images/Bullet.png")
          val bullImg = new Image(bullImgPath.toString)
          val enemyImgPath = getClass().getResource("/images/Enemy.png")
          val enemyImg = new Image(enemyImgPath.toString)
          val enemyy = new Enemy(enemyImg, new Vec2(400,100), bullImg)
          val g = canvas.graphicsContext2D
          val es = new EnemySwarm(6,3)
          val pl = new Player(playImg, new Vec2(350,500), bullImg)

          val timer = AnimationTimer(t => {
              g.setFill(Color.LightBlue)
              g.fillRect(0,0,width.value,height.value)
              pl.display(g)
              es.display(g)
          })
          timer.start()
         
          canvas.onKeyPressed = (e:KeyEvent) => {
              if(e.code == KeyCode.Space) {
                  //player shoot bullet
              }
              else if (e.code == KeyCode.Left) {
                    pl.moveLeft()
              }
              else if (e.code == KeyCode.Right) {
                pl.moveRight()
              } 
          }
           canvas.requestFocus()
          //key event for left right arrow
          //key event for spacebar
      }
    }
  }