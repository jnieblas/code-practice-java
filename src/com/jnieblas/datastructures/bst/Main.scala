package src.com.jnieblas.datastructures.bst

object Main {
  def main(args: Array[String]): Unit = {
    val bst = new BinarySearchTree(List(3,4,1,5,6,2,0))
    // 0,2,3,4,5,6
    /*
          4
      3      5
    0   2       6



     */
    print("Bst: " + bst.toString);
  }
}
