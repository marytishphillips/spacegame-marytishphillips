package cs2.game
import cs2.util.Vec2
import scala.collection.mutable.Buffer

class GameState (var playerPos:Vec2, var enemyPos:Buffer[Enemy], var playerBull:Buffer[Bullet], var enemyBull:Buffer[Bullet], var score:Int, var lives:Int) {

}