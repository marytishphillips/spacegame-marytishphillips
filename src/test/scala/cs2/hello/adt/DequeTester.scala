package cs2.adt

import org.junit._
import org.junit.Assert._

class DequeTester {
  //Include your thorough tester code here, including @Begin and @Test methods
  //and any fields needed for testing
  var d:Deque[Int] = null

  @Before def init():Unit = {
    d = Deque[Int]
  }

  @Test def prependFunctional():Unit = {
    assertTrue(d.isEmpty())
    d.prepend(5)
    d.prepend(4)
    d.prepend(6)
    assertFalse(d.isEmpty())
    d.front()
    d.back()
    d.front()
    assertTrue(d.isEmpty())
  }
  @Test def appendPeekFunctional():Unit = {
    assertTrue(d.isEmpty())
    d.append(7)
    assertFalse(d.isEmpty())
    assertTrue(d.peekBack() == 7)
    d.peekFront()
    d.peekBack()
    assertFalse(d.isEmpty())
  }
  @Test def isEmptyFunctional():Unit = {
    assertTrue(d.isEmpty())
    d.prepend(7)
    d.append(8)
    assertTrue(d.peekFront() == 7)
    d.front()
    assertTrue(d.peekBack() == 8)
    d.front()
    assertTrue(d.isEmpty())
  }
  
}
