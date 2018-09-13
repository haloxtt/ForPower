
# 复习大纲

## 算法和数据结构

### 线性表

#### 顺序结构

1. 数组

#### 链式结构

1. 单链表
2. 静态链表
3. 循环链表
4. 双向链表

#### 特殊结构

##### 栈

###### java如何实现栈

+ 通过数组来实现
+ 通过LinkedList来实现：

1. 实现压栈操作（调用LinkedList的addFirst()方法）
2. 实现出栈操作（调用LinkedList的removeFirst()方法）
3. 查看栈顶元素（调用LinkedList的getFirst()方法）
4. 查看栈顶元素并移除（调用LinkedList的poll()方法）
5. 判空（调用LinkedList的isEmpty()方法）
6. 打印元素（调用LinkedList的toString()方法）

###### 一些应用，主要用于递归和四则运算

1. 例如逆波兰表达式，二叉树遍历的非迭代方式等

##### 队列（一些应用，循环队列）

###### java如何实现队列

+ 通过LinkedList:

1. 实现入队操作（调用LinkedList的add()方法）
2. 查看队头（调用LinkedList的getFirst()方法）
3. 查看队头并移除（调用LinkedList的poll()方法）
4. 判空（调用LinkedList的isEmpty()方法）

### 二叉树

#### 求树的高度

```java
     if (node == null)
            return 0;
        int i = getHeight(node.leftChild);
        int j = getHeight(node.rightChild);
        return (i < j) ? j + 1 :i + 1;
```

#### 求树节点数

```java
     if (node == null)
            return 0;
        int i = getSize(node.leftChild);
        int j = getSize(node.rightChild);
        return i + j + 1;
```

#### 先序遍历迭代

```java
    if (node != null) {
            System.out.print(node.val + ",");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
```

#### 先序遍历非迭代

```java
    Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            System.out.print(n.val + ",");
            if ((n.rightChild != null))
                stack.push(n.rightChild);
            if (n.leftChild != null)
                stack.push(n.leftChild);
        }
```

#### 中序遍历迭代

```java
    if (node != null) {
            midOrder(node.leftChild);
            System.out.print(node.val + ",");
            midOrder(node.rightChild);
        }
```

#### 中序遍历非迭代

```java
    Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
            node = stack.pop();
            System.out.print(node.val + ",");
            node = node.rightChild;
        }
```

#### 后序遍历迭代

```java
    if (node != null) {
            nextOrder(node.leftChild);
            nextOrder(node.rightChild);
            System.out.print(node.val + ",");
        }
```

#### 完全二叉树

1. 满二叉树

#### 赫夫曼树（最优二叉树）

#### 红黑树

### 图

1. 邻接矩阵表示（耗费空间，稠密图用 邻接矩阵存储）。
2. 邻接表表示。

#### 算法

1. 普利姆算法

![图一](https://images2015.cnblogs.com/blog/1089549/201706/1089549-20170627090206414-709773911.png)

+ 图中有6个顶点v1-v6，每条边的边权值都在图上；在进行prim算法时，我先随意选择一个顶点作为起始点，当然我们一般选择v1作为起始点，好，现在我们设U集合为当前所找到最小生成树里面的顶点，TE集合为所找到的边，现在状态如下：U={v1}； TE={}；

![图二](https://images2015.cnblogs.com/blog/1089549/201706/1089549-20170627090304102-1558689798.png)

+ 现在查找一个顶点在U集合中，另一个顶点在V-U集合中的最小权值，如下图，在红线相交的线上找最小值。通过图中我们可以看到边v1-v3的权值最小为1，那么将v3加入到U集合，（v1，v3）加入到TE，状态如下：U={v1，v3}； TE={（v1，v3）}；

![图三](https://images2015.cnblogs.com/blog/1089549/201706/1089549-20170627090352539-707488440.png)

+ 继续寻找，现在状态为U={v1，v3}； TE={（v1，v3）}；在与红线相交的边上查找最小值。我们可以找到最小的权值为（v3，v6）=4，那么我们将v6加入到U集合，并将最小边加入到TE集合，那么加入后状态如下：U={v1，v3，v6}； TE={（v1，v3），（v3，v6）}； 如此循环一下直到找到所有顶点为止。
2. 克鲁斯卡尔算法
+ （1）将图中的所有边都去掉。（2）将边按权值从小到大的顺序添加到图中，保证添加的过程中不会形成环（3）重复上一步直到连接所有顶点，此时就生成了最小生成树。这是一种贪心策略。
3. 拓扑排序
+ 有向无环图的路径
1. 在有向图中选一个没有前驱的顶点并且输出（有几个就随机选择）
2. 从图中删除该顶点和所有以它为尾的弧（白话就是：删除所有和它有关的边）
3. 重复上述两步，直至所有顶点输出，或者当前图中不存在无前驱的顶点为止，后者代表我们的有向图是有环的，因此，也可以通过拓扑排序来判断一个图是否有环。

#### 应用

1. 铺设光缆的线路问题

#### 最短路径

1. 迪杰斯特拉算法
2. 弗洛伊德算法

### 查找

1. 二分法
2. B树
3. B+树
4. 散列函数

### 排序

#### 插入排序

1. 直接插入排序
2. 希尔排序
3. 二分插入排序

#### 选择排序

1. 简单选择排序
2. 堆排序

#### 交换排序

1. 冒泡排序
2. 快速排序

#### 归并排序

1. 归并排序

#### 桶排序

1. 基数排序


### 一些经典算法

1. 循环赛算法
2. 八皇后问题
3. 约瑟夫杀人法
4. 背包算法
5. 棋盘覆盖算法
6. 汉诺塔算法
7. 大数相乘
8. 卡拉楚巴算法
9. 泊松分酒

