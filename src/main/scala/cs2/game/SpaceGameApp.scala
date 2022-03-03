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

object SpaceGameApp extends JFXApp {    
  stage = new JFXApp.PrimaryStage {
      title = "Space Game!"
      scene = new Scene (800,800) {
          val canvas = new Canvas(width.value, height.value)
          content = canvas
          val backImgPath = getClass().getResource("/images/Background.png")
          val backImg = new Image(backImgPath.toString)
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
          val bl = new Bullet(bullImg, new Vec2(350,500), new Vec2(0,10))
          var bulletList= List[Bullet]()
          var enemyBullList= List[Bullet]()

          val timer = AnimationTimer(t => {
              g.drawImage(backImg, 0,0, width.value,height.value)
              pl.display(g)
              es.display(g)
              for(x <- bulletList) {
                x.display(g)
                x.timeStep()
              }
              enemyBullList ::= es.shoot()
              for (y <- enemyBullList) {
                y.display(g)
                y.timeStep()
              }
        })
          timer.start()
         
          canvas.onKeyPressed = (e:KeyEvent) => {
              if(e.code == KeyCode.Space) {
                bulletList ::= new Bullet(bullImg, pl.playerPosition(), new Vec2(0,-5)) //position of bullet
              }
              else if (e.code == KeyCode.Left) {
                pl.moveLeft()
              }
              else if (e.code == KeyCode.Right) {
                pl.moveRight()
              } 
          }
           canvas.requestFocus()
      }
    }
  }