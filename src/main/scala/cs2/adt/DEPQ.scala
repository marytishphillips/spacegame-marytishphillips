package cs2.adt

//Tester Code at /Users/marytishphillips/cs2hw-s22-marytishphillips/src/test/scala/cs2/hello/adt/DoubleEndPriorityQueueTester.scala

class DEPQ[A <% Ordered[A]] extends DoubleEndPriorityQueue[A] {
  private class Node(var data:A, var prev:Node, var next:Node)
  private var head:Node = null
  private var tail:Node = null

  def isEmpty():Boolean = {
    if (head == null && tail == null) true else false
  }

  def add(elem: A):Unit = {
    if (head == null) {
      head = new Node(elem, null, head)
      tail = head
    }
    else if (elem > head.data) {
      head = new Node(elem, null, head)
    }
    else {
      var rover:Node = head
      while (rover.next != null && elem <= rover.next.data) {
        rover = rover.next
      }
      if (rover.next == null && elem <= rover.data) {
        tail = new Node(elem, rover, null)
        rover.next = tail
      }
      else rover.next = new Node(elem, rover, rover.next)
    }
  }

  def peekMax():A = head.data

  def max():A = {
    var ret:A = head.data
    if (head.next == tail) {
      head = tail 
      head.next = null
    }
    else if (head.next == null) {
      head = null
      tail = null
    }
    else {
      var headd = head.next.next
      head = head.next
      head.next = headd
    }
    ret
  }
    
  def peekMin():A = tail.data

  def min():A = {
    var ret:A = tail.data
    if (tail.prev == head) {
      tail = tail.prev
      tail.prev = null
    }
    else if (head.data == tail.data) { 
      tail = null
      head = null
    }
    else {
      var taill = tail.prev.prev
      tail = tail.prev
      tail.prev = taill
    }
    ret
  }
}