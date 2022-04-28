package cs2.adt

import org.junit._
import org.junit.Assert._

class DoubleEndPriorityQueueTester {
  var d:DoubleEndPriorityQueue[Int] = null

  @Before def init():Unit = {
      d = DoubleEndPriorityQueue[Int]()
  }

  @Test def addTest():Unit = {
    assertTrue(d.isEmpty())
    d.add(5)
    d.add(3)
    d.add(12)
    assertTrue(d.peekMin == 3)
    assertTrue(d.peekMax == 12)
    assertTrue(!d.isEmpty())
  }

  @Test def minMax1Test():Unit = {
      assertTrue(d.isEmpty())
      d.add(3)
      d.add(9)
      d.add(2)
      assertTrue(!d.isEmpty())
      assertTrue(d.peekMax == 9)
      assertTrue(d.peekMin == 2)
      assertTrue(d.max == 9)
      assertTrue(!d.isEmpty())
      assertTrue(d.peekMax == 3)
      assertTrue(d.peekMin == 2)
      assertTrue(d.max == 3)
      assertTrue(d.min == 2)
      assertTrue(d.isEmpty())
  } 
  
@Test def minMax2Test():Unit = {
      assertTrue(d.isEmpty())
      d.add(20)
      assertTrue(d.peekMax == 20)
      assertTrue(d.peekMin == 20)
      assertTrue(d.max == 20)
      assertTrue(d.isEmpty())
      d.add(9)
      d.add(12)
      assertTrue(d.peekMax == 12)
      assertTrue(d.peekMin == 9)
      assertTrue(d.max == 12)
      assertTrue(!d.isEmpty())
      assertTrue(d.peekMax == 9)
      assertTrue(d.peekMin == 9)
      assertTrue(d.max == 9)
      assertTrue(d.isEmpty())
}
}
