package cs2.game
import cs2.util.Vec2
import cs2.adt.TheDeque

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
import scalafx.scene.control.Button
import scalafx.event.ActionEvent
import scalafx.scene.text.Font
import java.awt.Desktop.Action
import java.awt.RenderingHints.Key
import cs2.adt.LinkedStack
import scalafx.scene.chart.PieChart
import scalafx.collections.ObservableBuffer
import javafx.scene.layout.TilePane
import scalafx.scene.layout
import scalafx.geometry.Pos
import scalafx.scene.input.MouseButton

object SpaceGameApp extends JFXApp {    
  stage = new JFXApp.PrimaryStage {
      title = "Bullets In The Clouds"
      scene = new Scene (800,800) {
          val canvas = new Canvas(width.value, height.value)
          content = canvas
          val g = canvas.graphicsContext2D

          // Image Fields 
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
          val bossImgPath = getClass().getResource("/images/Boss.png")
          val bossImg = new Image(bossImgPath.toString)
          val titleImgPath = getClass().getResource("/images/title.png")
          val titleImg = new Image(titleImgPath.toString)
          val creatorImgPath = getClass().getResource("/images/CreatedBy.png")
          val creatorImg = new Image(creatorImgPath.toString)
          val keyImgPath = getClass().getResource("/images/KeyToBegin.png")
          val keyImg = new Image(keyImgPath.toString)
          val optionsImgPath = getClass().getResource("/images/Options.png")
          val optionsImg = new Image(optionsImgPath.toString)
          val finalScoreImgPath = getClass().getResource("/images/FinalScore.png")
          val finalScoreImg = new Image(finalScoreImgPath.toString)
          val gameOverImgPath = getClass().getResource("/images/gameOver.png")
          val gameOverImg = new Image(gameOverImgPath.toString)
          val pinkCloudImgPath = getClass().getResource("/images/PinkCloud.png")
          val pinkCloudImg = new Image(pinkCloudImgPath.toString)
          val orangeCloudImgPath = getClass().getResource("/images/OrangeCloud.png")
          val orangeCloudImg = new Image(orangeCloudImgPath.toString)
          val mountainImgPath = getClass().getResource("/images/Mountain.png")
          val mountainImg = new Image(mountainImgPath.toString)
          val bossBullImgPath = getClass().getResource("/images/BossBull.png")
          val bossBullImg = new Image(bossBullImgPath.toString)
         
          // Background Fields 
          var backgrndImage = new Background (backImg, new Vec2 (800,800), new Vec2 (0,0), new Vec2 (1,1))
          var pinkBackCloud1 = new Background (pinkCloudImg, new Vec2(750,500), new Vec2 (600, 50), new Vec2 (0.5,0))
          var pinkBackCloud2 = new Background (pinkCloudImg, new Vec2(750,500), new Vec2 (-50, 50), new Vec2 (0.5,0))
          var orangeBackCloud1 = new Background (orangeCloudImg, new Vec2(650,550), new Vec2 (-100, 10), new Vec2 (0.3,0))
          var orangeBackCloud2 = new Background (orangeCloudImg, new Vec2(650,550), new Vec2 (700, 10), new Vec2 (0.3,0))
          var mountain1 = new Background (mountainImg, new Vec2(800,600), new Vec2(0,200), new Vec2 (1, 0))
          var mountain2 = new Background (mountainImg, new Vec2(800,600), new Vec2(-800,200), new Vec2 (1, 0))

          // Game Fields
          var es = new EnemySwarm(6,3)
          val pl = new Player(playImg, new Vec2(350,500), playBullImg)
          val bl = new Bullet(bullImg, new Vec2(350,500), new Vec2(0,10))
          var bulletList= Buffer[Bullet]() 
          var enemyBullList= Buffer[Bullet]()
          var bossBulletToDisplay = Buffer[Bullet]()
          var bossMoves = new TheDeque[KeyCode]
          var keySet = scala.collection.mutable.Set[KeyCode]() 
          var count = 0 
          var bulletCount = 0 
          var starterPage = true
          var playerLives = 4
          var playerScore = 0 
          var isinRangeXX = true
          var isinRangeYY = true
          var rectFillValX = 0.0
          var rewindStack = new LinkedStack[GameState]
          var isReversing = false
          var isBoss = false
          var boss:Enemy = new Enemy(bossImg, new Vec2 (350,200), bossBullImg)
          var bossLives = 3
          var delay = 0
          var move = false
          var bullDelete:List[Bullet] = List()
          var enemyBullDelete:List[Bullet] = List()
          var enemyDelete:List[Enemy] = List()
          var bossBullDelete:List[Bullet] = List()
          var leftAlready = false
          var rightAlready = false
          var upAlready = false
          var downAlready = false
          var enemyyyPos:Vec2 = new Vec2(0,0)

          // Push To Stack Method
          def pushToStack ():Unit = {
            var cloneScore = playerScore
            var cloneLives = playerLives
            var clonePlayerBulletBuffer = bulletList.map(_.clone())
            var cloneEnemyBulletBuffer = enemyBullList.map(_.clone())
            var cloneEnemySwarm = es.swarm.map(_.clone)
            rewindStack.push(new GameState(pl.playerPosition().clone(), cloneEnemySwarm, clonePlayerBulletBuffer, cloneEnemyBulletBuffer, cloneScore, cloneLives))
          }
          // Boss Generation Method 
          def generateBoss():Boolean = {
            var b = scala.util.Random
            b.nextBoolean()
          }
          
          // Animation Timer
          val timer = AnimationTimer(t => {

            // Background
            backgrndImage.display(g)
            orangeBackCloud1.display(g)
            orangeBackCloud1.timeStep()
            orangeBackCloud2.display(g)
            orangeBackCloud2.timeStep()
            pinkBackCloud1.display(g)
            pinkBackCloud1.timeStep()
            pinkBackCloud2.display(g)
            pinkBackCloud2.timeStep()
            mountain1.display(g)
            mountain1.timeStep()
            mountain2.display(g)
            mountain2.timeStep()

            // Starter Page
            if (starterPage == true) { 
              playerScore = 0 
              rectFillValX = 0
              g.drawImage(titleImg, 2,100, 800, 200)
              g.drawImage(keyImg, 125, 300)
              g.drawImage(creatorImg, 500,700)
      
              for (s <- keySet) {
                if(s == KeyCode.Y) { 
                  starterPage = false 
                  playerLives = 4
                  delay = 0
                  move = false
                  es = new EnemySwarm(6,1)
                  isBoss = generateBoss()
                  if(isBoss == true) {
                    bossLives = 3
                    boss = new Enemy(bossImg, new Vec2 (350,200), bossBullImg)  
                  }
                  isinRangeXX = true
                  isinRangeYY = true 
                }
              }
            }

            // Game Over & Restart Game
            else if (playerLives == 0) {
              rewindStack = new LinkedStack[GameState]
              g.drawImage(gameOverImg, 140, 100, 500, 200)
              g.drawImage(finalScoreImg, 185, 300)
              g.setFill(Color.White)
              g.font = new Font("Impact", 150)
              g.fillText("" + playerScore, 275, 550)
              g.drawImage(optionsImg, 50, 600, 700, 200)

              for(s <- keySet) {
                if (s == KeyCode.X) starterPage = true 
                else if (s == KeyCode.P) {
                  playerLives = 4
                  playerScore = 0 
                  bulletList.clear()
                  rectFillValX = 0
                  delay = 0
                  move = false
                  es = new EnemySwarm(6,1)
                  isBoss = generateBoss()
                  if(isBoss == true) {
                    bossLives = 3
                    boss = new Enemy(bossImg, new Vec2 (350,200), bossBullImg)
                  }
                  isinRangeXX = true
                  isinRangeYY = true
                }
              }
            }
            
            // Play Game
            else {
              // Display Lives, Score, and Stack Space
              g.setFill(Color.White)
              g.font = new Font("Impact", 30)
              g.fillText("Player Lives Remaining: " + playerLives + " / 4" , 450, 40)
              g.fillText("Score: " + playerScore, 5, 40)
              g.fillText("Rewind Memory Available: ", 5, 770)
              g.font = new Font("Impact", 20)
              g.setFill(Color.Coral)
              g.fillText("Press 'R' To Rewind Game", 5, 790)
              g.setFill(Color.LightSeaGreen)
              g.fillRect(340, 740, rectFillValX, 40)
              pl.display(g)

              if (isBoss == true) {
                boss.display(g)
              }

              // Check If Need To Reset Game
              if(es.swarm.length == 0 && isBoss == false) {
                es = new EnemySwarm(6,1)
                delay = 0
                move = false
                isBoss = generateBoss()
                if(isBoss == true) {
                  bossLives = 3
                  boss = new Enemy(bossImg, new Vec2 (350,200), bossBullImg)
                } 
                isinRangeXX = true
                isinRangeYY = true
              } 
              es.display(g)

              // Check Keys Pressed
              leftAlready = false
              rightAlready = false
              upAlready = false
              downAlready = false

              for (e <- keySet) {
                if(e == KeyCode.Space) {
                  if (bulletCount % 25 == 0) {
                    move = true
                    bulletList += new Bullet(playBullImg, pl.playerPosition(), new Vec2(0,-5))
                    bulletCount += 1
                    if (isBoss == true) bossMoves.append(KeyCode.Space) 
                  }
                  else bulletCount += 1
                } 
                else if (e == KeyCode.Left || e == KeyCode.A && leftAlready == false) {
                  move = true
                  pl.moveLeft()
                  if (isBoss == true) bossMoves.append(KeyCode.Left)  
                  leftAlready = true 
                }
                else if (e == KeyCode.Right || e == KeyCode.D && rightAlready == false) {
                  move = true
                  pl.moveRight()
                  if (isBoss == true) bossMoves.append(KeyCode.Right) 
                  rightAlready = true
                } 
                else if (e == KeyCode.Up || e == KeyCode.W && upAlready == false) {
                  move = true
                  pl.moveUp()
                  if (isBoss == true) bossMoves.append(KeyCode.Down) 
                  upAlready = true
                }
                else if (e == KeyCode.Down || e == KeyCode.S && downAlready == false) {
                  move = true
                  pl.moveDown()
                  if (isBoss == true) bossMoves.append(KeyCode.Up) 
                  downAlready = true
                }
                else if(e ==KeyCode.R && !rewindStack.isEmpty()) {
                  isReversing = true
                  var reversePop:GameState = rewindStack.pop()
                  pl.playerUpdate(reversePop.playerPos.x.toInt, reversePop.playerPos.y.toInt)
                  es.swarm = reversePop.enemyPos
                  bulletList = reversePop.playerBull
                  enemyBullList = reversePop.enemyBull
                  playerLives = reversePop.lives
                  playerScore = reversePop.score
                  rectFillValX -= 0.1
                }
              }
              

              // Run Game As Normal
              if (isReversing == false) {

                // Enemy Swarm Movement
                for (m <- es.swarm) {
                m.enemyTimeStepX(isinRangeXX)
                isinRangeXX = m.enemyTimeStepX(isinRangeXX)
                m.enemyTimeStepY(isinRangeYY)
                isinRangeYY = m.enemyTimeStepY(isinRangeYY)
                }

                // Bullet Display
                for(x <- bulletList) { 
                  x.display(g)
                  x.timeStep()
                }
                
                // Boss Bullet & Movement Display
                if(isBoss == true && delay == 1) {
                  if(!bossMoves.isEmpty()) {
                    var bossKey = bossMoves.front()
                    if(bossKey == KeyCode.Space) bossBulletToDisplay += new Bullet (bossBullImg, boss.enemyPosition(), new Vec2 (0, 5))
                    else if (bossKey == KeyCode.Left) boss.moveLeft()
                    else if (bossKey == KeyCode.Right) boss.moveRight()
                    else if (bossKey == KeyCode.Down) boss.moveDown()
                    else boss.moveUp()
                  }
                  move = false
                  delay = 0
                }

                bossBullDelete = List()

                if (!bossBulletToDisplay.isEmpty) {
                  for (b <- bossBulletToDisplay) {
                    b.display(g)
                    b.timeStep()
                    if (pl.intersection(b, b.bulletImgSize, b.bulletImgSize)) { 
                      pl.playerUpdate(350, 500)
                      enemyyyPos = boss.enemyPosition()
                      boss.enemyUpdate(350, enemyyyPos.y.toInt)
                      bossMoves = new TheDeque[KeyCode]
                      if (playerLives > 0) playerLives -= 1
                      bossBullDelete ::= b
                    }
                  }
                }

                // Random Enemy Fire
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

                // Intersections
                bullDelete = List()
                enemyBullDelete = List()
                enemyDelete = List()
                

                for (b <- enemyBullList) { 
                  if (pl.intersection(b, b.bulletImgSize, b.bulletImgSize)) { 
                    pl.playerUpdate(350, 500)
                    enemyyyPos = boss.enemyPosition()
                    boss.enemyUpdate(350, enemyyyPos.y.toInt)
                    bossMoves = new TheDeque[KeyCode]
                    if (playerLives > 0) playerLives -= 1
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
                      playerScore += 100
                      bullDelete ::= u 
                    }
                  }
                }

                  for (b <- bulletList) {
                    if(boss.intersection(b, b.bulletImgSize, b.bulletImgSize)) {
                      bossLives -= 1
                      bullDelete ::= b
                      if (bossLives == 0) {
                        isBoss = false
                        playerScore += 500
                      }
                    }
                  }

                for (d <- bullDelete) {
                  bulletList -= d
                }

                for (e <- enemyDelete) {
                  es.swarm -= e
                }

                for (t <- enemyBullDelete) {
                  enemyBullList -= t 
                }

                for(b <- bossBullDelete) {
                  bossBulletToDisplay -= b
                }

                // Push To Stack & Move Stack Memory Bar
                pushToStack()
                rectFillValX += 0.1
              }

              else {
                // If isReversing == True, Still Display Bullets.
                for (y <- enemyBullList) { 
                  y.display(g)
                }
                for(x <- bulletList) { 
                  x.display(g)
                }
              }
              if (move == true) delay += 1
            }
            isReversing = false
          })
          timer.start()
          
          // Add & Remove Keys
          canvas.onKeyPressed = (w:KeyEvent) => {
            if(w.code == KeyCode.Space || w.code == KeyCode.S|| w.code == KeyCode.Left || w.code == KeyCode.Right || w.code == KeyCode.Up || w.code == KeyCode.Down || w.code == KeyCode.W || w.code == KeyCode.A || w.code == KeyCode.R || w.code == KeyCode.D|| w.code == KeyCode.X|| w.code == KeyCode.Y|| w.code == KeyCode.P) {
              keySet.add(w.code)
            }
          }
          canvas.onKeyReleased = (c:KeyEvent) => {
            if(c.code == KeyCode.Space || c.code == KeyCode.S || c.code == KeyCode.Left || c.code == KeyCode.Right || c.code  == KeyCode.Up || c.code == KeyCode.Down|| c.code == KeyCode.W || c.code == KeyCode.A || c.code == KeyCode.R || c.code == KeyCode.D|| c.code == KeyCode.X|| c.code == KeyCode.Y|| c.code == KeyCode.P) {
              keySet.remove(c.code)
            }
            if(c.code == KeyCode.Space) bulletCount = 0
          }
          canvas.requestFocus()
      }
  }
}