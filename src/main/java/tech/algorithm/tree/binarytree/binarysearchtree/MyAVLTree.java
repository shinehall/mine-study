package tech.algorithm.tree.binarytree.binarysearchtree;

import tech.algorithm.tree.binarytree.MyBinaryTreeNode;

public class MyAVLTree<T> extends MyBinarySearchTree<T> {

  @Override
  public void addElement(T element) throws Exception {
    super.addElement(element);
    rebalance();
  }

  @Override
  public T removeElement(T element) throws Exception {
    T rs = super.removeElement(element);
    rebalance();
    return rs;
  }

  /*
   * 每次新增或删除元素时都做一次平衡
   * */
  private void rebalance() throws Exception{
    //左子树过长
    if((getHeight(root.getLeftChild()) - getHeight(root.getRightChild())) > 1){
      MyBinaryTreeNode<T> lNode = root.getLeftChild();
      //如果过长的是左子树的左侧，只需要一次右旋
      if(getHeight(lNode.getLeftChild()) > getHeight(lNode.getRightChild())){
        root = rotateRight(root);
      }else if(getHeight(lNode.getLeftChild()) < getHeight(lNode.getRightChild())){
        //如果过长的是左子树的右侧，则需要先左旋右侧，再右旋
        root = rotateLeftRight(root);
      }
    }else if((getHeight(root.getRightChild()) - getHeight(root.getLeftChild())) > 1){ //右子树过长
      MyBinaryTreeNode<T> rNode = root.getRightChild();
      //如果过长的是右子树的右侧，只需要一次左旋
      if(getHeight(rNode.getRightChild()) > getHeight(rNode.getLeftChild())){
        root = rotateLeft(root);
      }else if(getHeight(rNode.getRightChild()) > getHeight(rNode.getLeftChild())){
        //如果过长的是右子树的左侧，则需要先右旋左侧，再左旋
        root = rotateRightLeft(root);
      }
    }
  }
  
  private MyBinaryTreeNode<T> rotateLeft(MyBinaryTreeNode<T> node) throws Exception{
    if(node == null){
      throw new Exception("target node is null!");
    }
    if(node.getRightChild() == null){
      return node;
    }
    MyBinaryTreeNode<T> rightNode = node.getRightChild();
    node.setRightChild(rightNode.getLeftChild());
    rightNode.setLeftChild(node);
    return rightNode;
  }
  
  private MyBinaryTreeNode<T> rotateRight(MyBinaryTreeNode<T> node) throws Exception{
    if(node == null){
      throw new Exception("target node is null!");
    }
    if(node.getLeftChild() == null){
      return node;
    }
    MyBinaryTreeNode<T> leftNode = node.getLeftChild();
    node.setLeftChild(leftNode.getRightChild());
    leftNode.setRightChild(node);
    return leftNode;
  }
  
  /*
   * 左结点的右子树过长，先左旋再右旋
   * */
  private MyBinaryTreeNode<T> rotateLeftRight(MyBinaryTreeNode<T> node) throws Exception{
    if(node == null){
      throw new Exception("target node is null!");
    }
    if(node.getLeftChild() == null){
      return node;
    }
    node.setLeftChild(rotateLeft(node.getLeftChild()));
    return rotateRight(node);
  }
  
  /*
   * 右结点的左子树过长，先右旋再左旋
   * */
  private MyBinaryTreeNode<T> rotateRightLeft(MyBinaryTreeNode<T> node) throws Exception{
    if(node == null){
      throw new Exception("target node is null!");
    }
    if(node.getRightChild() == null){
      return node;
    }
    node.setRightChild(rotateRight(node.getRightChild()));
    return rotateLeft(node);
  }
}
