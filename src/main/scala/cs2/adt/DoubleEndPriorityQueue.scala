package cs2.adt

//Tester Code at /Users/marytishphillips/cs2hw-s22-marytishphillips/src/test/scala/cs2/hello/adt/DoubleEndPriorityQueueTester.scala

abstract class DoubleEndPriorityQueue[A <% Ordered[A]] {
  def isEmpty():Boolean //Return true if there are no elements in the DEPQ
  def add(elem: A):Unit //Add an element to the DEPQ
  def peekMax():A //Return the largest element in the DEPQ
  def max():A //Return AND Remove the largest element from the DEPQ
  def peekMin():A //Return the smallest element in the DEPQ
  def min():A //Return AND Remove the smallest element from the DEPQ
}

object DoubleEndPriorityQueue {
   def apply[A <% Ordered[A]]():DoubleEndPriorityQueue[A] = {
    new DEPQ[A]()
  }
}