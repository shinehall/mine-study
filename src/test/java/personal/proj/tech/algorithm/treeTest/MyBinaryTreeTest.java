package personal.proj.tech.algorithm.treeTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import tech.algorithm.tree.binarytree.MyBinaryTree;
import tech.algorithm.tree.binarytree.MyBinaryTreeNode;

public class MyBinaryTreeTest {

  private static int[] arrays = { 1, 3, 17, 6, 9, 28, 30, 5, 11, 16 };
  
  @Test
  public void test() throws Exception {
    MyBinaryTree<Integer> tree = buildBinaryTree(arrays);
    System.out.println(tree.getHeight());
  }

  private MyBinaryTree<Integer> buildBinaryTree(int[] arrays) throws Exception {
    int middle = arrays.length / 2;
    MyBinaryTree<Integer> tree = new MyBinaryTree<Integer>(arrays[middle]);
    tree.getRootNode().setLeftChild(buildChild(arrays, 0, middle - 1));
    tree.getRootNode().setRightChild(buildChild(arrays, middle + 1, arrays.length - 1));
    return tree;
  }
  
  private MyBinaryTreeNode<Integer> buildChild(int[] arrays, int startIndex, int endIndex){
    if(endIndex < startIndex){
      return null;
    }
    if(endIndex == startIndex){
      return new MyBinaryTreeNode<Integer>(arrays[startIndex], null, null);
    }
    int middle = (startIndex + endIndex) / 2;
    MyBinaryTreeNode<Integer> node = new MyBinaryTreeNode<Integer>(arrays[middle], null, null);
    node.setLeftChild(buildChild(arrays, startIndex, middle - 1));
    node.setRightChild(buildChild(arrays, middle + 1, endIndex));
    return node;
  }

}
