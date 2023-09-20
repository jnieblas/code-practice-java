package src.com.jnieblas.datastructures.bst

class BSTNode(var i: Int, var left: BSTNode = null, var right: BSTNode = null) {

  override
  def toString: String = s"$i"
}
