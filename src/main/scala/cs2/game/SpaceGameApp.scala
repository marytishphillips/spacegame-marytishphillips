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
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Buffer
import javafx.scene.input

object SpaceGameApp extends JFXApp {    
  stage = new JFXApp.PrimaryStage {
      title = "Bullets In The Clouds"
      scene = new Scene (800,800) {
          val canvas = new Canvas(width.value, height.value)
          content = canvas
          val g = canvas.graphicsContext2D

          val backImgPath = getClass().getResource("/images/Background.png")
          val backImg = new Image(backImgPath.toString)
          val playImgPath = getClass().getResource("/images/Player.png")
          val playImg = new Image(playImgPath.toString)
          val bullImgPath = getClass().getResource("/images/Bullet.png")
          val bullImg = new Image(bullImgPath.toString)
          val playBullImgPath = getClass().getResource("/images/PlayerBullet.png")
          val playBullImg = new Image(playBullImgPath.toString)
          val enemyImgPath = getClass().getResource("/images/Enemy.png")
          val enemyImg = new Image(enemyImgPath.toString)

          
          var es = new EnemySwarm(6,3)
          val pl = new Player(playImg, new Vec2(350,500), playBullImg)
          val bl = new Bullet(bullImg, new Vec2(350,500), new Vec2(0,10))
          var bulletList= Buffer[Bullet]() 
          var enemyBullList= Buffer[Bullet]() 
          val keySet = scala.collection.mutable.Set[KeyCode]() 
          var count = 0 
          var bulletCount = 0 

          val timer = AnimationTimer(t => {
              g.drawImage(backImg, 0,0, width.value,height.value)
              pl.display(g)

              if(es.swarm.length == 0) es = new EnemySwarm(6,3) 
              es.display(g)

              for(x <- bulletList) { 
                x.display(g)
                x.timeStep()
              }

              if (count % 45 == 0) { 
                enemyBullList += es.shoot()
                count += 1
              }
              else count += 1

              var k:Int = 0
              while (k < enemyBullList.length) { 
                if (enemyBullList(k).bulletPositionY() > 750) enemyBullList.remove(k)
                else k += 1
              }
              
              for (y <- enemyBullList) { 
                  y.display(g)
                  y.timeStep()
              }
              
              for (e <- keySet) {
                if(e == KeyCode.Space) {
                  if (bulletCount % 25 == 0) {
                    bulletList += new Bullet(playBullImg, pl.playerPosition(), new Vec2(0,-5))
                    bulletCount += 1
                  }
                  else bulletCount += 1
                } 
                else if (e == KeyCode.Left) {
                  pl.moveLeft() 
                }
                else if (e == KeyCode.Right) {
                  pl.moveRight()
                } 
                else if (e == KeyCode.Up) {
                  pl.moveUp()
                }
                else if (e == KeyCode.Down) {
                  pl.moveDown()
                }
              }
              var bullDelete:List[Bullet] = List()
              var enemyBullDelete:List[Bullet] = List()
              var enemyDelete:List[Enemy] = List()

              for (b <- enemyBullList) { 
                  if (pl.intersection(b, b.bulletImgSize, b.bulletImgSize)) { 
                    pl.playerUpdate(350, 500)
                    enemyBullDelete ::= b
                  }
              }
              
              for (h <- bulletList) {
                for (b <- enemyBullList) { 
                  if (h.intersection(b, b.bulletImgSize, b.bulletImgSize)) { 
                    bullDelete ::= h
                    enemyBullDelete ::= b
                  }
                }
              }

              for (j <- es.swarm) { 
                if (pl.intersection(j, j.enemyImgSize, j.enemyImgSize - 75)) pl.playerUpdate(350, 500) 
              }

              for (b <- es.swarm) { 
                for (u <- bulletList) {
                  if (b.intersection(u, u.bulletImgSize, u.bulletImgSize)) {
                    enemyDelete ::= b
                    bullDelete ::= u 
                  }
                }
              }
              for (d <- bullDelete) bulletList -= d
              for (e <- enemyDelete) es.swarm -= e
              
              for (t <- enemyBullDelete) enemyBullList -= t
        })
          timer.start()
          
          canvas.onKeyPressed = (w:KeyEvent) => {
              if(w.code == KeyCode.Space || w.code == KeyCode.Left || w.code == KeyCode.Right || w.code == KeyCode.Up || w.code == KeyCode.Down) {
                keySet.add(w.code)
              }
          }
          canvas.onKeyReleased = (c:KeyEvent) => {
            if(c.code == KeyCode.Space || c.code == KeyCode.Left || c.code == KeyCode.Right || c.code  == KeyCode.Up || c.code == KeyCode.Down) {
                keySet.remove(c.code)
              }
              if(c.code == KeyCode.Space) bulletCount = 0
          }
          
           canvas.requestFocus()
      }
    }
  }