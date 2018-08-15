import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class BinaryTree {
    //根节点
    private static TreeNode root = null;
    private class TreeNode{
        //左孩子
        private TreeNode leftChild;
        //右孩子
        private TreeNode rightChild;
        //父节点
        private TreeNode parent;
        //下标
        private int index;
        //节点的值
        private int data;
        /*
            构造函数
         */
        public TreeNode(int index,int data) {
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
        /*
            构造函数
         */
        public TreeNode(int data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }

    }
    /**
     *  查找二叉树删除操作(后继节点替换)
     */
    private void deleteNode(TreeNode node) {
        if (node.leftChild == null && node.rightChild==null) {//如果节点没有子节点
            if (node == root){
                root = null;//根节点就删除整个树
            }
            if (isLeft(node)) //如果是左节点就把左节点置为空
                node.parent.leftChild = null;
            if (isRight(node))
                node.parent.rightChild = null;

        } else if (node.leftChild !=null && node.rightChild == null) {//被删除的节点只有左节点
            if (node == root) {
                root = node.leftChild;
            } else if (isLeft(node)) {
                node.parent.leftChild = node.leftChild;
            } else {
                node.parent.rightChild = node.leftChild;
            }
        } else if (node.leftChild == null && node.rightChild!=null) {//被删除的节点只有右节点
            if (node == root) {
                root = node.rightChild;
            } else if (isLeft(node)) {
                node.parent.leftChild = node.rightChild;
            } else if (isRight(node)) {
                node.parent.rightChild = node.rightChild;
            }
        } else {//要删除的节点有两个节点
            TreeNode successor = findSuccessor(node);//查找某个节点的后继节点
            if (root == node) {
                root = successor;
            } else if (isLeft(node)) {
                node.parent.leftChild = successor;
            } else {
                node.parent.rightChild = successor;
            }
            successor.leftChild= node.leftChild;
        }
    }
    /**
     * 查找某个节点的后继节点
     * ①如果节点有右子树，则该节点的后继节点就是往右子树出发，然后转到右子树的左子树，一直到左子树的左子树为空
     * 如果节点没有右子树，则向上寻找父节点，直到父节点的左子树等于当前节点，则该父节点就是后继节点
     */
    private TreeNode findSuccessor(TreeNode node){
        if (node == null)
            return null;
        if (node.rightChild != null) {
            node = node.rightChild;
            while (node != null && node.leftChild != null) {
                node = node.leftChild;
            }
            return node;
        }
        while (node.parent != null) {
            if (node.parent.leftChild == node)
                return node.parent;
            node = node.parent;
        }
        return node;
    }
    /**
     * 判断一个节点是否是左节点
     */
    private boolean isLeft(TreeNode node) {
        if (node == null || node == root)
            return false;
        if (node.parent.leftChild == node)
            return true;
        return false;
    }
    /**
     * 判断一个节点是否是右节点
     */
    private boolean isRight(TreeNode node) {
        if (node == null || node == root)
            return false;
        if (node.parent.rightChild == node)
            return true;
        return false;
    }
    /**
     * 查找二叉树 插入操作
     */
    private void insertNode(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return ;
        }
        insertNode(data,root);
    }
    private void insertNode(int data,TreeNode node) {
        if (data == node.data)//等于节点值
            return;
        if (data>node.data) {//大于节点值
            if (node.rightChild == null) { //右孩子为空的时候插入
                node.rightChild = new TreeNode(data);
                node.rightChild.parent = node;
                return;
            } else {
                insertNode(data,node.rightChild);//继续对比右孩子
            }
        } else {//小于节点值
            if(node.leftChild == null) {
                node.leftChild = new TreeNode(data);
                node.leftChild.parent = node;
                return;
            } else {
                insertNode(data,node.leftChild);//继续对比左孩子
            }
        }
    }
    private TreeNode searchNode(int data,TreeNode node) {
        if (data == node.data)//等于节点值
            return node;
        if (data>node.data) {//大于节点值
            return searchNode(data,node.rightChild);//继续对比右孩子
        } else if(data<node.data) {//小于节点值
            return searchNode(data,node.leftChild);//继续对比左孩子
        } else {
            return null;
        }
    }
    /**
     * 通过前序遍历反向生成二叉树
     *       1
         2       3
     4    5   6    7
     *
     *  124##5##36##7##
     */
    private void createBinaryTreePre(int size, ArrayList<String> data) {
        createBinaryTreeBypre(data.size(),data);
    }
    private TreeNode  createBinaryTreeBypre(int size,ArrayList<String> data) {
        if (data.size() == 0) {
            return null;
        }
        String d = data.get(0);
        TreeNode node;
        int index = size - data.size();
        if (d.equals("#")) {
            node = null;
            data.remove(0);
            return node;
        }
        node = new TreeNode(index, Integer.valueOf(d));
        if (index == 0) {
            root = node;
        }
        data.remove(0);
        node.leftChild = createBinaryTreeBypre(size, data);
        node.rightChild = createBinaryTreeBypre(size, data);
        return node;
    }
    /*
        创建二叉树的方法
                  1
              2       3
           4    5   6    7
     */
    private void createBinaryTree() {
        TreeNode node1 = new TreeNode(0,1);
        root = node1;
        TreeNode node2 = new TreeNode(0,2);
        TreeNode node3 = new TreeNode(0,3);
        TreeNode node4 = new TreeNode(0,4);
        TreeNode node5 = new TreeNode(0,5);
        TreeNode node6 = new TreeNode(0,6);
        TreeNode node7 = new TreeNode(0,7);
        root.rightChild = node3;
        root.leftChild = node2;
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.rightChild = node7;
        node3.leftChild = node6;
    }
    /*
        先序遍历迭代
     */
    private static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.data);
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }

    }

    /**
     * 先序遍历非迭代
     * @param root
     */
    private static void nonRecPreOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.println(node.data);
            if (node.rightChild != null)
                stack.push(node.rightChild);
            if (node.leftChild != null)
                stack.push(node.leftChild);
        }
    }
    /*
        中序遍历迭代
     */
    private static void midOrder(TreeNode root) {
        if (root != null) {
            midOrder(root.leftChild);
            System.out.println(root.data);
            midOrder(root.rightChild);
        }
    }
    /**
     * 中序遍历非迭代
     * @param root
     */
    private static void nonRecMidOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (!stack.empty() || node!=null){
            while (node !=null) {//左孩子全部入栈
                stack.push(node);
                node = node.leftChild;
            }
            node = stack.pop();
            System.out.println(node.data);
            node = node.rightChild;
        }
    }
    /*
        后序遍历迭代
     */
    private static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            System.out.println(root.data);
        }
    }
    /*
     后序遍历非迭代
  */
    private static void nonRecPostOrder(TreeNode root) {
        TreeNode preNode = root;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node!=null || !stack.empty()) {
            while (node != null) { //先把左孩子全部入栈
                stack.push(node);
                node = node.leftChild;
            }
            node = stack.peek();//审查栈顶元素
            if (node.rightChild==null || node.rightChild==preNode) {// 该节点的右子树为空，或者其右子树已经被访问过
                node = stack.pop();
                System.out.println(node.data);
                preNode = node;
                node = null;
            } else {
                node = node.rightChild;
            }

        }
    }

    /**
     * 求树的结点数
     * @return
     */
    private static int getSize() {
        return getSize(root);
    }
    private static int getSize(TreeNode root) {
        if (root == null)
            return 0;
        return getSize(root.leftChild)+getSize(root.rightChild)+1;
    }
    /*
        求树的高度
     */
    private static int getHeight() {
       return getHeight(root);
    }
    private static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int i = getHeight(node.leftChild);
        int j = getHeight(node.rightChild);
        return (i < j) ? j+1 : i+1;
    }
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insertNode(56);
        binaryTree.insertNode(23);
        binaryTree.insertNode(98);
        binaryTree.insertNode(12);
        binaryTree.insertNode(54);
        binaryTree.insertNode(67);
        binaryTree.insertNode(99);
        System.out.println("前序遍历的顺序为：");
        preOrder(root);
        TreeNode node = binaryTree.searchNode(54,root);
        binaryTree.deleteNode(node);
        System.out.println("删除了 54");
        preOrder(root);
    }

}
