package cs2.adt

class TheDeque[A : Manifest] extends Deque[A] {
  private class Node(var data:A, var prev:Node, var next:Node)
  private var head:Node = null
  private var tail:Node = null
  //Define the required methods and create any neccessary fields here
  def prepend(elem:A) = {
    head = new Node(elem, null, head)
    if (head.next == null) tail = head
    if (head.next != null) head.next.prev = head 
  }//should add a single element to the logical "beginning" of the Deque
  def append(elem:A) = {
   if (tail != null) {
    tail.next= new Node(elem, tail, null)
    tail = tail.next
   }
   else { 
     tail = new Node(elem, tail, null)
     head = tail
   }
  }//should add a single element to the logical "end" of the Deque
  def front():A ={
    var ret:A = head.data
    if (head != null) {
      var ret = head.data
      val headd = head.next
      if(head.next != null) {
        val headdd = head.next.next
        head.next = headdd
      }
      head = headd
      if (head == null) tail = null
    }
    ret
  }//should return AND remove a single element from the logical "beginning" of the Deque
  def back():A = {
    val ret = tail.data
    val previ = tail.prev
    tail = tail.prev
    tail.prev = previ
    tail.next = null
    ret
  } //should return AND remove a single element from the logical "end" of the Deque
  def peekFront():A = {
    head.data
  }//should return BUT NOT remove a single element from the logical "beginning"
  def peekBack():A = {
    tail.data
  } //should return BUT NOT remove a single element from the logical "end"
  def isEmpty():Boolean = {
  if (head == null && tail == null) true
  else false //should return true if the Deque contains no elements, and false otherwise
}
}