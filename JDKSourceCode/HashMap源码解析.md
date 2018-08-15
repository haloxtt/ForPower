# HashMap源码解析

+ HashMap有两个影响性能的重要参数：初始容量和加载因子。容量是Hash表中桶的个数，当HashMap初始化时，容量就是初始容量。加载因子是衡量hash表多满的一个指标，用来判断是否需要增加容量。当HashMap需要增加容量时，将会导致rehash操作。 默认情况下，0.75的加载因子在时间和空间方面提供了很好的平衡。加载因子越大，增加了空间利用率但是也增加了查询的时间。

## 构造器

### JDK1.8之前的结构

+ 在JDK1.7之前，HashMap采用的是数组+链表的结构,数组的每一个元素都是一个单链表的头节点，链表是用来解决冲突的，如果不同的key映射到了数组的同一位置处，就将其放入单链表中。

### JDK1.8的结构

+ JDK1.8之前的HashMap都采用上图的结构，都是基于一个数组和多个单链表，hash值冲突的时候，就将对应节点以链表形式存储。如果在一个链表中查找一个节点时，将会花费O(n)的查找时间，会有很大的性能损失。到了JDK1.8，当同一个Hash值的节点数不小于8时，不再采用单链表形式存储，而是采用红黑树，如下图所示：
![HashMap结构](http://img.blog.csdn.net/20160127173307041)

### 重要的字段

```java
      //Hash表结构
    transient Node<K,V>[] table;

    //元素个数
    transient int size;

    //确保fail-fast机制
    transient int modCount;

    //下一次增容前的阈值
    int threshold;

    //加载因子
    final float loadFactor;

     //默认初始容量
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

   //最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;

    //加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //链表转红黑树的阈值
    static final int TREEIFY_THRESHOLD = 8;
```

## 构造方法

+ HashMap一共有4个构造方法，主要的工作就是完成容量和加载因子的赋值。Hash表都是采用的懒加载方式，当第一次插入数据时才会创建。

```java
     public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

     public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
```

## 基本操作

### 添加一个元素put(K k,V v)

+ HashMap允许K和V为null，添加一个键值对时使用put方法，如果之前已经存在K的键值，那么旧值将会被新值替换。实现如下：
```java
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //如果哈希表为空或长度为0，调用resize()方法创建哈希表
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        //如果哈希表中K对应的桶为空，那么该K，V对将成为该桶的头节点
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        //该桶处已有节点，即发生了哈希冲突
        else {
            Node<K,V> e; K k;
            //如果添加的值与头节点相同，将e指向p
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            //如果与头节点不同，并且该桶目前已经是红黑树状态，调用putTreeVal()方法
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            //桶中仍是链表阶段
            else {
                //遍历，要比较是否与已有节点相同
                for (int binCount = 0; ; ++binCount) {
                    //将e指向下一个节点，如果是null，说明链表中没有相同节点，添加到链表尾部即可
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        //如果此时链表个数达到了8，那么需要将该桶处链表转换成红黑树，treeifyBin()方法将hash处的桶转成红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    //如果与已有节点相同，跳出循环
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            //如果有重复节点，那么需要返回旧值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                //子类实现
                afterNodeAccess(e);
                return oldValue;
            }
        }
        //是一个全新节点，那么size需要+1
        ++modCount;
        //如果超过了阈值，那么需要resize()扩大容量
        if (++size > threshold)
            resize();
        //子类实现
        afterNodeInsertion(evict);
        return null;
    }
```
+ 从上面代码可以看到putVal()方法的流程： 
1. 判断哈希表是否为空，如果为空，调用resize()方法进行创建哈希表 
2. 根据hash值得到哈希表中桶的头节点，如果为null，说明是第一个节点，直接调用newNode()方法添加节点即可 
3. 如果发生了哈希冲突，那么首先会得到头节点，比较是否相同，如果相同，则进行节点值的替换返回 
4. 如果头节点不相同，但是头节点已经是TreeNode了，说明该桶处已经是红黑树了，那么调用putTreeVal()方法将该结点加入到红黑树中 
5. 如果头节点不是TreeNode，说明仍然是链表阶段，那么就需要从头开始遍历，一旦找到了相同的节点就跳出循环或者直到了链表尾部，那么将该节点插入到链表尾部 
6. 如果插入到链表尾部后，链表个数达到了阈值8，那么将会将该链表转换成红黑树，调用treeifyBin()方法 
7. 如果是新加一个数据，那么将size+1，此时如果size超过了阈值，那么需要调用resize()方法进行扩容

### resize()方法

+ 下面我们一个一个分析上面提到的方法。首先是resize()方法，resize()在哈希表为null时将会初始化，但是在已经初始化后就会进行容量扩展。下面是resize()的具体实现：

```java
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;//旧表容量
        int oldThr = threshold;//旧表与之
        int newCap, newThr = 0;
        //旧表存在
        if (oldCap > 0) {
            //旧表已经达到了最大容量，不能再大，直接返回旧表
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //否则，新容量为旧容量2倍，新阈值为旧阈值2倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        //如果就阈值>0，说明构造方法中指定了容量
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        //初始化时没有指定阈值和容量，使用默认的容量16和阈值16*0.75=12
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        //更新阈值
        threshold = newThr;
        //创建表,初始化或更新表
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        //如果属于容量扩展，rehash操作
        if (oldTab != null) {
            //遍历旧表
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                //如果该桶处存在数据
                if ((e = oldTab[j]) != null) {
                    //将旧表数据置为null，帮助gc
                    oldTab[j] = null;
                    //如果只有一个节点，直接在新表中赋值
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    //如果该节点已经为红黑树
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    //如果该桶处仍为链表
                    else { // preserve order
                    //下面这段暂时没有太明白，通过e.hash & oldCap将链表分为两队，参考知乎上的一段解释 
                        /** 
* 把链表上的键值对按hash值分成lo和hi两串，lo串的新索引位置与原先相同[原先位 
* j]，hi串的新索引位置为[原先位置j+oldCap]； 
* 链表的键值对加入lo还是hi串取决于 判断条件if ((e.hash & oldCap) == 0)，因为* capacity是2的幂，所以oldCap为10...0的二进制形式，若判断条件为真，意味着 
* oldCap为1的那位对应的hash位为0，对新索引的计算没有影响（新索引 
* =hash&(newCap-*1)，newCap=oldCap<<2）；若判断条件为假，则 oldCap为1的那位* 对应的hash位为1， 
* 即新索引=hash&( newCap-1 )= hash&( (oldCap<<2) - 1)，相当于多了10...0， 
* 即 oldCap 

* 例子： 
* 旧容量=16，二进制10000；新容量=32，二进制100000 
* 旧索引的计算： 
* hash = xxxx xxxx xxxy xxxx 
* 旧容量-1 1111 
* &运算 xxxx 
* 新索引的计算： 
* hash = xxxx xxxx xxxy xxxx 
* 新容量-1 1 1111 
* &运算 y xxxx 
* 新索引 = 旧索引 + y0000，若判断条件为真，则y=0(lo串索引不变)，否则y=1(hi串 
* 索引=旧索引+旧容量10000) 
   */  
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```

+ 从上面可以看到，resize()首先获取新容量以及新阈值，然后根据新容量创建新表。如果是扩容操作，则需要进行rehash操作，通过e.hash&oldCap将链表分为两列，更好地均匀分布在新表中。 当头节点是TreeNode时，将调用TreeNode的split方法将红黑树复制到新表中，代码实现如下：

```java
    final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
            TreeNode<K,V> b = this;//就是上面的头结点e
            // Relink into lo and hi lists, preserving order
            //与链表rehash时类似，将红黑树分为两部分
            TreeNode<K,V> loHead = null, loTail = null;
            TreeNode<K,V> hiHead = null, hiTail = null;
            int lc = 0, hc = 0;
            //遍历
            for (TreeNode<K,V> e = b, next; e != null; e = next) {
                next = (TreeNode<K,V>)e.next;
                e.next = null;
                //分散规则与rehash中相同
                if ((e.hash & bit) == 0) {
                    if ((e.prev = loTail) == null)
                        loHead = e;
                    else
                        loTail.next = e;
                    loTail = e;
                    ++lc;
                }
                else {
                    if ((e.prev = hiTail) == null)
                        hiHead = e;
                    else
                        hiTail.next = e;
                    hiTail = e;
                    ++hc;
                }
            }

            //如果存在低端
            if (loHead != null) {
                //如果分散后的红黑树节点小于等于6，将红黑树节点转换成链表节点
                if (lc <= UNTREEIFY_THRESHOLD)
                    tab[index] = loHead.untreeify(map);
                else {
                    tab[index] = loHead;
                    //将链表转换成红黑树
                    if (hiHead != null) // (else is already treeified)
                        loHead.treeify(tab);
                }
            }
            //如果存在高端
            if (hiHead != null) {
                //如果分散后的红黑树节点小于等于6，将红黑树节点转换成链表节点
                if (hc <= UNTREEIFY_THRESHOLD)
                    tab[index + bit] = hiHead.untreeify(map);
                else {
                    tab[index + bit] = hiHead;
                    //将链表转换成红黑树节点
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
        }
```

+ TreeNode的split方法首先将头节点从头开始遍历，区分出两条单链表，再根据如果节点数小于等于6，那么将单链表的每个TreeNode转换成Node节点；否则将单链表转换成红黑树结构。 至此，resize()方法结束。需要注意的是rehash时，由于容量扩大一倍，本来一条链表有可能会分成两条链表，而如果将红黑树结构复制到新表时，有可能需要完成红黑树到单链表的转换。

### treeifyBin()方法

+ reeifyBin()方法将表中某一个桶处的单链表结果转换成红黑树结构，其实现如下：

```java
    final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        //如果哈希表不存在，或者哈希表尺寸小于64，进行resize()扩容
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize();
        //如果桶处头节点不为null
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            TreeNode<K,V> hd = null, tl = null;
            //将单链表节点转换成TreeNode结构的单链表
            do {
                //将Node转换成TreeNode
                TreeNode<K,V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            //调用treeify将该TreeNode结构的单链表转换成红黑树
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }
```

### put操作总结

当调用put插入一个键值对时，在表为空时，会创建表。如果桶为空时，直接插入节点，如果桶不为空时，则需要对当前桶中包含的结构做判断，如果已是红黑树结构，那么需要使用红黑树的插入方法；如果不是红黑树结构，则需要遍历链表，如果添加到链表后端，如果该条链表达到了8，那么需要将该链表转换成红黑树，从treeifyBin方法可以看到，当容量小于64时，不会进行红黑树转换，只会扩容。当成功新加一个桶，那么需要将尺寸和阈值进行判断，是否需要进行resize()操作。

### get(K k)操作

+ get(K k)根据键得到值，如果值不存在，那么返回null。其实现如下：

```java
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

//根据键的hash值和键得到对应节点
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        //可以从桶中得到对应hash值的第一个节点
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            //检查首节点，如果首节点匹配，那么直接返回首节点
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            //如果首节点还有后续节点
            if ((e = first.next) != null) {
                //如果首节点是红黑树节点，调用getTreeNode()方法
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                //首节点是链表结构，从前往后遍历
                do {
                    //一旦匹配，返回节点
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

+ 从上面代码可以看到getNode()方法中有多种情况：
1. 表为空或表的长度为0或表中不存在key对应的hash值桶，那么返回null
2. 如果表中有key对应hash值的桶，得到首节点，如果首节点匹配，那么直接返回；
3. 如果首节点不匹配，并且没有后续节点，那么返回null
4. 如果首节点有后续节点并且首节点是TreeNode,调用getTreeNode方法寻找节点
5. 如果首节点有后续节点并且是链表结构，那么从前往后遍历，一旦找到则返回节点，否则返回null

### remove()操作

+ remove(K k)用于根据键值删除键值对，如果哈希表中存在该键，那么返回键对应的值，否则返回null。其实现如下：

```java
    public V remove(Object key) {
        Node<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
            null : e.value;
    }

//按照hash和key删除节点，如果不存在节点，则返回null
final Node<K,V> removeNode(int hash, Object key, Object value,
                               boolean matchValue, boolean movable) {
        Node<K,V>[] tab; Node<K,V> p; int n, index;
        //如果哈希表不为空并且存在桶与hash值匹配,p为桶中的头节点
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (p = tab[index = (n - 1) & hash]) != null) {
            Node<K,V> node = null, e; K k; V v;
            //case 1：如果头节点匹配
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
               //case2：如果头节点不匹配，且头节点是TreeNode，即桶中的结构为红黑树结构
                if (p instanceof TreeNode)
                    node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
                else {
                //case 3:如果头节点不匹配，且头节点是Node，即桶中的结构为链表结构，遍历链表
                    do {
                        //一旦匹配，跳出循环
                        if (e.hash == hash &&
                            ((k = e.key) == key ||
                             (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }

            //如果存在待删除节点节点
            if (node != null && (!matchValue || (v = node.value) == value ||
                                 (value != null && value.equals(v)))) {
                //如果节点是TreeNode，使用红黑树的方法
                if (node instanceof TreeNode)
                    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
                //如果待删除节点是头节点，更改桶中的头节点即可
                else if (node == p)
                    tab[index] = node.next;
                //在链表遍历过程中，p代表node节点的前驱节点
                else
                    p.next = node.next;
                ++modCount;
                --size;
                //子类实现
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }
```

+ 从上面的代码可以看出，removeNode()方法首先是找到待删除的节点，如果存在待删除节点，接下来再执行删除操作。查询时流程与getNode()方法的流程类似，只不过多了在遍历链表时还需要保存前驱节点，因为后面删除时要用到（单链表结构）。删除节点时就比较简单了，三种情况三种处理方式,分别是：
1. 如果待删除节点是TreeNode，那么调用removeTreeNode()方法
2. 如果待删除节点是Node，并且待删除节点就是头节点，那么将头节点更改为原有节点的下一个节点就可以了 
3. 如果待删除节点是Node且待删除节点不是头节点，那么将遍历过程中保存的前驱节点p的后继节点设为node的后继节点就可以了

## HashMap总结

+ 至此，我们分析完了HashMap的主要方法：构造器、put、get和remove。只需要明白JDK1.8的HashMap底层结构，那么就很好理解了。需要注意的是什么时候应该将链表结构转换成红黑树结构，什么时候又应该将红黑树结构重新转换成链表结构，本文没有具体解释有关红黑树的结构，但是这并不影响理解HashMap的基本原理。
