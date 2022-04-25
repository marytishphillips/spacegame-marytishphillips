package cs2.adt

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
    
    /*var ret:A = head.data
    if (head != null) {
      var ret = head.data
      val headd = head.next
      head = headd
      if(head.next != null) {
        val headdd = head.next.next
        head.next = headdd
      }
      if (head == null) tail = null
    }
    ret*/
    /*var returnHead = head.data
    head = head.next
    returnHead*/
  def peekMin():A = tail.data
  def min():A = {
    var ret:A = tail.data
    if (tail.prev == head) {
      tail = tail.prev
      tail.prev = null
    }
    else if (head.data == tail.data) { //tail.prev == null &&
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
    /*val ret = tail.data
    val previ = tail.prev
    val prevvi = tail.prev.prev
    tail = tail.prev
    tail.prev = prevvi
    tail.next = null
    ret*/
    /*var returnTail = tail.data
   // if (tail.prev != null) {
      tail = tail.prev
   // }
    returnTail*/
}