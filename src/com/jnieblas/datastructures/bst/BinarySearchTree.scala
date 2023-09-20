package src.com.jnieblas.datastructures.bst

import scala.collection.mutable.ListBuffer

class BinarySearchTree() {
  private var root: Option[BSTNode] = None

  def this(numbers: List[Int]) = {
    this()
    this.root = Option(buildFromList(numbers.sorted))
    println("Root: " + root.get.toString)
  }

  def buildFromList(numbers: List[Int]): BSTNode = {
    val length = numbers.length

    if (length == 1) {
      return new BSTNode(numbers.head);
    }

    if (length <= 0) {
      return null
    }

    val median = length / 2

    // build left array
    val left: BSTNode = buildFromList(numbers.slice(0, median))

    val root: BSTNode = new BSTNode(numbers(median))
    root.left = left
    root.right = buildFromList(numbers.slice(median + 1, length))

    root
  }

  override def toString: String = {
    val nodesToPrint = ListBuffer.empty[String]
    printTree(nodesToPrint = nodesToPrint)
    nodesToPrint.mkString(", ")
  }

  private def printTree(head: BSTNode = this.root.get, nodesToPrint: ListBuffer[String]): Unit = {
    if (head == null) {
      return
    }

    printTree(head.left, nodesToPrint)
    nodesToPrint += head.i.toString
    printTree(head.right, nodesToPrint)
  }
}
