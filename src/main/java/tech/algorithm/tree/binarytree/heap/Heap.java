package tech.algorithm.tree.binarytree.heap;

import tech.algorithm.tree.binarytree.MyBinaryTree;
import tech.algorithm.tree.binarytree.interfaces.HeapADT;

public class Heap<T> extends MyBinaryTree<T> implements HeapADT<T> {

  protected HeapNode<T> root;
  protected HeapNode<T> lastNode;

  public Heap() {
    super();
  }

  public Heap(T element) throws Exception {
    super(element);
    this.root = (HeapNode<T>) super.root;
    root.setParent(null);
  }

  /*
   * 1、先将元素加到堆的末尾
   * 2、将堆末的元素与父节点逐一比较，并排序
   * */
  @Override
  public void addElement(T element) throws Exception {
    if (element == null) {
      throw new Exception("element is null!");
    }
    if (!(element instanceof Comparable)) {
      throw new Exception("element is not comparable!");
    }
    addElementToEndPoint(element);
    resortAdd();
  }

  private void addElementToEndPoint(T element) {
    HeapNode<T> node = new HeapNode<T>(element);
    if (root == null) {
      root = node;
      lastNode = root;
      count++;
      return;
    }
    if (lastNode == root) {
      root.setLeftChild(node);
      lastNode = node;
      lastNode.setParent(root);
      count++;
      return;
    }
    //如果堆尾节点在父节点的左侧，那么父节点的右侧肯定为空了
    if (lastNode.getParent().getLeftChild() == lastNode) {
      lastNode.getParent().setRightChild(node);
      node.setParent(lastNode.getParent());
      count++;
      return;
    } else {
      //堆尾节点在父节点的右侧，则需要向上回溯，直到祖先节点在祖先父节点的左侧为止
      HeapNode<T> temp = lastNode;
      HeapNode<T> tempParent = temp.getParent();
      while (tempParent != root) {
        temp = tempParent;
        tempParent = tempParent.getParent();
      }
      //如果回溯到根节点还是在右侧，说明要加入的元素位置在最左侧的叶子节点上
      if (tempParent.getLeftChild() != temp) {
        while (tempParent.getLeftChild() != null) {
          tempParent = tempParent.getLeftChild();
        }
        tempParent.setLeftChild(node);
        node.setParent(tempParent);
        lastNode = node;
      } else {
        //找到了在左侧的祖先节点，则将元素加到祖先节点右侧的最左叶子节点上
        temp = tempParent.getRightChild();
        while (temp.getLeftChild() != null) {
          temp = temp.getLeftChild();
        }
        temp.setLeftChild(node);
        node.setParent(temp);
        lastNode = node;
      }
      count++;
    }
  }

  private void resortAdd() {
    if (lastNode == root) {
      return;
    }
    HeapNode<T> temp = lastNode;
    HeapNode<T> parent = temp.getParent();
    while (((Comparable<T>) (parent.getElement())).compareTo(temp.getElement()) > 0) {
      parent.setElement(temp.getElement());
      temp = parent;
      parent = parent.getParent();
    }
  }

  @Override
  public T removeMin() throws Exception {
    if(root == null){
      throw new Exception("heap is empty!");
    }
    T rs = root.getElement();
    if(lastNode == root){
      root = null;
      lastNode = null;
      count--;
      return rs;
    }else if(lastNode == root.getLeftChild()){
      root = lastNode;
      root.setLeftChild(null);
      count--;
      return rs;
    }
    
    return null;
  }

  @Override
  public T findMin() {
    // TODO Auto-generated method stub
    return null;
  }

}
