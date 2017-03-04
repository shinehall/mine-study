package tech.algorithm.tree.binarytree.binarysearchtree;

import tech.algorithm.tree.binarytree.MyBinaryTree;
import tech.algorithm.tree.binarytree.MyBinaryTreeNode;
import tech.algorithm.tree.binarytree.interfaces.BinarySearchTreeADT;

public class MyBinarySearchTree<T> extends MyBinaryTree<T> implements BinarySearchTreeADT<T> {
  private MyBinaryTreeNode<T> root;
  private int count;

  public MyBinarySearchTree() {
    super();
  }

  public MyBinarySearchTree(T element) throws Exception {
    super();
    if (!(element instanceof Comparable)) {
      throw new Exception("element is not comparable!");
    }
    root = new MyBinaryTreeNode<T>(element, null, null);
    count = 1;
  }

  @Override
  public void addElement(T element) throws Exception {
    if (element == null) {
      throw new Exception("element should not be null!");
    }
    if (!(element instanceof Comparable)) {
      throw new Exception("element is not comparable!");
    }
    if (root == null) {
      root = new MyBinaryTreeNode<>();
      root.setElement(element);
      count++;
      return;
    }
    addElement(element, root);
    count++;
  }

  private void addElement(T element, MyBinaryTreeNode<T> root){
    Comparable<T> comparableElement = (Comparable<T>) element;
    if(comparableElement.compareTo(root.getElement()) < 0){
      if(root.getLeftChild() == null){
        root.setLeftChild(new MyBinaryTreeNode<T>(element, null, null));
      }else{
        addElement(element, root.getLeftChild());
      }
    }else{
      if(root.getRightChild() == null){
        root.setRightChild(new MyBinaryTreeNode<T>(element, null, null));
      }else{
        addElement(element, root.getRightChild());
      }
    }
  }
  
  @Override
  public T removeElement(T element) throws Exception {
    if(root == null){
      throw new Exception("root is null!");
    }
    if(element == null){
      return root.getElement();
    }
    if(!(element instanceof Comparable)){
      throw new Exception("element is not comparable!");
    }
    T resultElement = removeElement(element, root);
    return resultElement;
  }

  private T removeElement(T element, MyBinaryTreeNode<T> root){
    Comparable<T> comparableElement = (Comparable<T>) element;
    if(comparableElement.equals(root.getElement())){  //找到了要删的元素
      if(root.getRightChild() == null){
        return root.getLeftChild().getElement();
      }else{
        MyBinaryTreeNode<T> resultNode = replacement(root.getRightChild());
        count--;
        return resultNode.getElement();
      }
    }else{
      if(comparableElement.compareTo(root.getElement()) < 0){
        if(root.getLeftChild() == null){  //没有找到该元素
          return root.getElement();
        }else{
          return removeElement(element, root.getLeftChild());
        }
      }else{
        if(root.getRightChild() == null){
          return root.getElement();
        }else{
          return removeElement(element, root.getRightChild());
        }
      }
    }
  }
  
  //node为当前要删的元素
  private MyBinaryTreeNode<T> replacement(MyBinaryTreeNode<T> node){
    if(node.getLeftChild() == null){
      return node.getRightChild();
    }
    if(node.getRightChild() == null){
      return node.getLeftChild();
    }
    MyBinaryTreeNode<T> parent = node;
    MyBinaryTreeNode<T> current = node.getRightChild();
    //循环找到node右子树的左叶子结点
    while(current.getLeftChild() != null){
      parent = current;
      current = current.getLeftChild();
    }
    //将node的左子树连接到current的左子树上
    current.setLeftChild(node.getLeftChild());
    //以current为根结点将树平衡
    if(current != node.getRightChild()){
      parent.setLeftChild(current.getRightChild());
      current.setRightChild(node.getRightChild());
    }
    return current;
  }
  
  @Override
  public void removeAllOcurrences(T element) throws Exception {
    if(element == null){
      throw new Exception("element can not be null!");
    }
    if(!(element instanceof Comparable)){
      throw new Exception("element is not comparable!");
    }
    while(contains(element)){
      removeElement(element);
    }
  }

  @Override
  public T removeMin() throws Exception {
    T targetElement = findMin();
    removeElement(targetElement);
    return targetElement;
  }

  @Override
  public T removeMax() throws Exception {
    T targetElement = findMax();
    removeElement(targetElement);
    return targetElement;
  }

  @Override
  public T findMin() throws Exception {
    if(root == null){
      throw new Exception("binary search tree is null!");
    }
    MyBinaryTreeNode<T> rsNode = root;
    while(root.getLeftChild() != null){
      rsNode = root.getLeftChild();
    }
    return rsNode.getElement();
  }

  @Override
  public T findMax() throws Exception {
    if(root == null){
      throw new Exception("binary search tree is null!");
    }
    MyBinaryTreeNode<T> rsNode = root;
    while(root.getRightChild() != null){
      rsNode = root.getRightChild();
    }
    return rsNode.getElement();
  }

}
