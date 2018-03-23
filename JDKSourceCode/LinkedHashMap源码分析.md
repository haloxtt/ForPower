# LinkedHashMap源码分析

+ LinkedHashMap是使用HashMap+LinkedList来实现Map接口。和HashMap的区别在于它维持一个双端链表保存所插入访问的节点。链表定义了迭代顺序，默认情况下顺序就是键值插入到Map的顺序（插入顺序）。 
+ 构造方法为LinkedHashMap(int,float,boolean)的可用来创建一个按照访问顺序迭代的LinkedHashMap，按照最少访问到最多访问的顺序链接结点，这种LinkedHashMap可用来实现LRU缓存。 
+ LinkedHashMap继承自HashMap, LinkedHashMap的底层数据结构与HashMap类似，只不过在HashMap的基础上使用一个双端链表维持插入节点的顺序或者访问节点的顺序。

## 构造方法

### LinkedHashMap中的节点

+ LinkedHashMap中的节点类是Entry，继承自HashMap中的Node节点，在Node节点的基础上增加了before和after两个节点，定义如下：
```java
     static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
```
+ 其结构如下图： 
![LinkedHashMap结构](http://img.blog.csdn.net/20170629201744177?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMTk0MzEzMzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### LinkedHashMap中的重要字段
 
```java
    transient LinkedHashMap.Entry<K,V> head;


    transient LinkedHashMap.Entry<K,V> tail;

    /**
     * The iteration ordering method for this linked hash map: <tt>true</tt>
     * for access-order, <tt>false</tt> for insertion-order.
     *
     * @serial
     */
    final boolean accessOrder;
```
+ 由于LinkedHashMap使用双端链表维持所有节点，所以有head和tail两个字段，表示链表的头节点和尾节点。 accessOrder表示迭代顺序，true表示访问顺序，false表示插入顺序。

## 构造方法

+ LinkedHashMap的构造方法主要也是使用父类的构造方法并将accessOrder赋值，默认为false。accessOrder为final字段，值只能在构造方法中传入。构造方法如下：
```java
    public LinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        accessOrder = false;
    }

    public LinkedHashMap(int initialCapacity) {
        super(initialCapacity);
        accessOrder = false;
    }

    public LinkedHashMap() {
        super();
        accessOrder = false;
    }


    public LinkedHashMap(Map<? extends K, ? extends V> m) {
        super();
        accessOrder = false;
        putMapEntries(m, false);
    }

    public LinkedHashMap(int initialCapacity,
                         float loadFactor,
                         boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
    }
```
+ 可以看到LinkedHashMap也存在初始容量和加载因子两个影响LinkedHashMap的性能参数。

## 基本操作

### PUT操作

+ LinkedHashMap的put方法继承自HashMap，但是内部很多方法都自己实现了，下面我们以put方法开始说明。
```java
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        //如果桶为空，调用newNode新建一个节点
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            //如果找到了节点
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                //子类实现
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        //子类实现
        afterNodeInsertion(evict);
        return null;
    }
```

### newNode()方法

+ 从代码可以看到，如果插入K/V对时，桶中没有链表，那么使用newNode创建一个新节点，LinkedHashMap重写了该方法，其实现如下：

```java
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p =
            new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        linkNodeLast(p);
        return p;
    }
    private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
    }
```
+ 从上面代码可以看到，newNode()中方法中首先创建了一个以null为节点的Entry节点，然后调用linkNodeLast()方法将该结点添加到双端链表的尾节点。

### afterNodeInsertion(boolean evict)实现

+ afterNodeInsertion方法的evict参数如果为false，表示哈希表处于创建模式。只有在使用Map集合作为构造器创建LinkedHashMap或HashMap时才会为false，使用其他构造器创建的LinkedHashMap，之后再调用put方法，该参数均为true。LinkedHashMap的afterNodeInsertion()实现如下：
```java
    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null && removeEldestEntry(first)) {
            K key = first.key;
            removeNode(hash(key), key, null, false, true);
        }
    }
```
+ 从上面可以看到，如果要进入到if语句块中需要同时满足三个条件： 
1. evict为true。只要不是构造方法中的插入Map集合，evict就为true，否则为false 
2. first!=null。表明表不为空，按理来说，当调用该方法时，哈希表不会为空 
3. removeEldestEntry()方法返回true。该方法删除删除最老的节点

+ LinkedHashMap的removeEldestEntry()方法的默认实现返回false，如下：
```java
     protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return false;
    }
```
+ 所以上面就不会进入到if语句块中。removeElestEntry用于定义删除最老元素的规则。一旦需要删除最老节点，那么将会调用removeNode删除节点。 举个例子，如果一个链表只能维持100个元素，那么当插入了第101个元素时，以如下方式重写removeEldestEntry的话，那么将会删除最老的一个元素，如下：
```java
    public boolean removeEldestEntry(Map.Entry<K,V> eldest){
       return size()>100;
    }
```

### afterNodeAccess(Node e)实现

+ afterNodeAccess()在键值重复时，会调用该方法，其中参数该表示该节点。该方法用于再accessOrder为true时将节点移到最后，其实现如下：
```java
    void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        //如果accessOrder为true并且当前节点不是tail节点
        if (accessOrder && (last = tail) != e) {
            LinkedHashMap.Entry<K,V> p =
                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a != null)
                a.before = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.before = last;
                last.after = p;
            }
            tail = p;
            ++modCount;
        }
    }
```
+ 从上面代码可以看到，如果想进入到if语句块，那么必须同时满足两个条件： 
1. accessOrder为true。为true只能在三个参数的构造方法中指定accessOrder，表明按照访问顺序管理节点。那么当键相同时，就相当于一次访问，所以可能需要将访问的节点移到双端链表的尾端。 
2. 如果当前节点不是尾节点。如果已经是尾节点，那么无须就行移动。

+ 从上面可以看到，一旦满足LinkedHashMap使用访问顺序管理链表时且当前节点不是尾节点，那么需要将节点移到尾节点，if语句块中的代码就是将一个节点从双端链表中移至尾端。

### get()操作

+ LinkedHashMap的get方法用于根据键得到值，如果哈希表中不包含该键，那么返回null，其实现如下：
```java
     public V get(Object key) {
        Node<K,V> e;
        //如果哈希表中不存在该键，返回null
        if ((e = getNode(hash(key), key)) == null)
            return null;
        //如果accessOrder为true，即使用访问顺序
        if (accessOrder)
            afterNodeAccess(e);
        return e.value;
    }
```
+ 从上面代码可以看到，get()方法分为2步： 
1. 调用getNode()方法得到键对应的节点。如果节点为null，表明哈希表中不存在该键，那么返回null 
2. 如果哈希表中存在该键并且accessOrder为true，那么调用afterNodeAccess(e)将节点移到双端链表的尾部

### remove()操作

+ LinkedHashMap的remove()方法根据键删除节点，如果哈希表中不存在键值，那么返回null。LinkedHashMap的remove()方法继承自HashMap的rmeove()方法，在将节点从链表或红黑树中移除后，调用afterNodeRemoval(Node e)方法，LinkedHashMap实现了该方法，其实现如下：
```java
    // e表示待删除的节点
    void afterNodeRemoval(Node<K,V> e) { // unlink
        LinkedHashMap.Entry<K,V> p =
            (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
        p.before = p.after = null;
        if (b == null)
            head = a;
        else
            b.after = a;
        if (a == null)
            tail = b;
        else
            a.before = b;
    }
```
+ 从上面可以看到，afterNodeRemoval()方法主要就是将节点从双端链表中移除。

### keySet()操作

+ LinkedHashMap的keySet()方法用于返回该哈希表的键的一个Set集合。其实现如下：
```java
    public Set<K> keySet() {
        Set<K> ks;
        return (ks = keySet) == null ? (keySet = new LinkedKeySet()) : ks;
    }
```
+ 从上面可以看到，该方法返回了一个LinkedKeySet的对象，该对象是一个Set，继承自AbstractSet类，其实现如下：
```java
    final class LinkedKeySet extends AbstractSet<K> {
        public final int size()                 { return size; }
        public final void clear()               { LinkedHashMap.this.clear(); }
        public final Iterator<K> iterator() {
            return new LinkedKeyIterator();
        }
        public final boolean contains(Object o) { return containsKey(o); }
        public final boolean remove(Object key) {
            return removeNode(hash(key), key, null, false, true) != null;
        }
        public final Spliterator<K> spliterator()  {
            return Spliterators.spliterator(this, Spliterator.SIZED |
                                            Spliterator.ORDERED |
                                            Spliterator.DISTINCT);
        }
        public final void forEach(Consumer<? super K> action) {
            if (action == null)
                throw new NullPointerException();
            int mc = modCount;
            for (LinkedHashMap.Entry<K,V> e = head; e != null; e = e.after)
                action.accept(e.key);
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
```
+ 这里我们具体看一下iterator()方法，返回的是一个LinkedKeyIterator对象，该类的定义如下：
```java
    final class LinkedKeyIterator extends LinkedHashIterator
        implements Iterator<K> {
        public final K next() { return nextNode().getKey(); }
    }

 abstract class LinkedHashIterator {
        LinkedHashMap.Entry<K,V> next;
        LinkedHashMap.Entry<K,V> current;
        int expectedModCount;

        LinkedHashIterator() {
            next = head;
            expectedModCount = modCount;
            current = null;
        }

        public final boolean hasNext() {
            return next != null;
        }

        final LinkedHashMap.Entry<K,V> nextNode() {
            LinkedHashMap.Entry<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            current = e;
            next = e.after;
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }
```
+ 从上面可以看到LinkedKeyIterator继承自LinkedHashIterator，而LinkedHashIterator有三个子类，分别是LinkedKeyIterator、LinkedValueIterator、LinkedEntryIterator，从类名可以看出这三个类分别用于迭代键、值以及键值对。另外的两个雷LinkedValueIterator和LinkedEntryIterator的实现和LinkedKeyIterator的实现基本相同，只是类名不同，从下面的代码可以看出：
```java
    final class LinkedValueIterator extends LinkedHashIterator
        implements Iterator<V> {
        public final V next() { return nextNode().value; }
    }

    final class LinkedEntryIterator extends LinkedHashIterator
        implements Iterator<Map.Entry<K,V>> {
        public final Map.Entry<K,V> next() { return nextNode(); }
    }
```
+ 所以，LinkedHashIterator类是一个关键的类，其构造方法中保存了当前的modCount以及将next指针指向双端链表的头指针，其nextNode()方法就是按照双端链表从头往后遍历的方式操作的，而remeve()方法也是调用了removeNode()删除当前节点。

### values()方法

+ values()方法用于返回哈希表中的所有值，由于哈希表中的键是唯一的，所以keySet()的返回值是一个Set，而哈希表中的值是允许重复的，所以返回值是一个Collection，其实现如下：
```java
     public Collection<V> values() {
        Collection<V> vs;
        return (vs = values) == null ? (values = new LinkedValues()) : vs;
    }

    final class LinkedValues extends AbstractCollection<V> {
        public final int size()                 { return size; }
        public final void clear()               { LinkedHashMap.this.clear(); }
        public final Iterator<V> iterator() {
            return new LinkedValueIterator();
        }
        public final boolean contains(Object o) { return containsValue(o); }
        public final Spliterator<V> spliterator() {
            return Spliterators.spliterator(this, Spliterator.SIZED |
                                            Spliterator.ORDERED);
        }
        public final void forEach(Consumer<? super V> action) {
            if (action == null)
                throw new NullPointerException();
            int mc = modCount;
            for (LinkedHashMap.Entry<K,V> e = head; e != null; e = e.after)
                action.accept(e.value);
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
```
+ 可以看到，LinkedValues类继承自AbstractCollection，重写了几个关键方法。其中iterator()方法返回是LinkedValueIterator，上面已经提过了，该类将按照双端链表的中维持的节点顺序遍历节点，返回值。

### entrySet()方法

LinkedHashMap的entrySet()方法用于返回的是每一对Entry。由于该方法与keySet()和values()方法类似，这儿就不再介绍了。

## 总结

LinkedHashMap在HashMap的基础上使用一个双端链表维持有序的节点。这个有序并不是通常意义上的大小关系，默认情况下使用的插入顺序，意味着新插入的节点被添加到双端链表的尾部，而一旦使用了访问顺序，即accessOrder为true，那么在访问某一节点时，会将该节点移到双端链表的尾部。正因为此特性，可以在LinkedHashMap中使用三个参数的构造方法并制定accessOrder为true将LinkedHashMap实现为LRU缓存，这样经常访问的就会被移到链表的尾部，而越少访问的就在链表的头部。
+ 由于双端链表维持了所有的节点，所以keySet()、values()以及entrySet()得到的键、值、键值对都是按照双端链表中的节点顺序的。 
+ 另外尤其需要注意的是，在put、get、remove方法中涉及到的双端链表的操作，由于都是引用的更改，所以并没有影响到HashMap的底层结构：数组+链表+红黑树。




