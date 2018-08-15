# 源码学习笔记---ArrayList

## ArrayList

+ ArrayList 是一个数组队列，相当于 动态数组。与Java中的数组相比，它的容量能动态增长。它继承于AbstractList，实现了List, RandomAccess, Cloneable, java.io.Serializable这些接口。
+ ArrayList 继承了AbstractList，实现了List。它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能。
+ ArrayList 实现了RandmoAccess接口，即提供了随机访问功能。RandmoAccess是java中用来被List实现，为List提供快速访问功能的。在ArrayList中，我们即可以通过元素的序号快速获取元素对象；这就是快速随机访问。稍后，我们会比较List的“快速随机访问”和“通过Iterator迭代器访问”的效率。
+ ArrayList 实现了Cloneable接口，即覆盖了函数clone()，能被克隆。
+ ArrayList 实现java.io.Serializable接口，这意味着ArrayList支持序列化，能通过序列化去传输。
+ 和Vector不同，ArrayList中的操作不是线程安全的！所以，建议在单线程中才使用ArrayList，而在多线程中可以选择Vector或者CopyOnWriteArrayList

## ArrayList的属性

```java
    public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {  
        // 序列化id  
        private static final long serialVersionUID = 8683452581122892189L;  
        // 默认初始的容量  
        private static final int DEFAULT_CAPACITY = 10;  
        // 一个空对象  
        private static final Object[] EMPTY_ELEMENTDATA = new Object[0];  
        // 一个空对象，如果使用默认构造函数创建，则默认对象内容默认是该值  
        private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];  
        // 当前数据对象存放地方，当前对象不参与序列化  
        transient Object[] elementData;  
        // 当前数组长度  
        private int size;  
        // 数组最大长度  
        private static final int MAX_ARRAY_SIZE = 2147483639; 
    }
```

### 构造函数

```java
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+initialCapacity);
        }
    }
    /**
    *     将数组的大小指定为传入的int参数。
    */


    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    /**
    *  无参构造函数将数组初始化为空。
    */

        public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
    /**
    *   传入一个集合，将集合转成数组，如果数组的长度为0，则数组初始化为空数组，否则如果转换后的数组也不是Object类型，则copy到一个Object数组里。
    */

```

### 修正数组大小

```java
        public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
              ? EMPTY_ELEMENTDATA
              : Arrays.copyOf(elementData, size);
        }
    }
    /**
    *   java.util.ArrayList作为一个动态数组，它有capacity和size两个概念：
    *   capacity：当前这个ArrayList实例总共可以装多少个元素
    *   size：当前这个ArrayList实例已经装了多少个元素。
    *   trimToSize()做的事情就是把capacity减少到跟当前的size一样大。
    *   例如假设现在ArrayList是{1,2,3,null,null,null}，则调用后为{1,2,3}。
    */
```

## 增加ArrayList实例的容量

+ 在使用add()方法增加新的元素时，如果要增加的数据量很大，应该使用ensureCapacity()方法，该方法的作用是预先设置Arraylist的大小，这样可以大大提高初始化速度。

```java
        public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // any size if not default element table
            ? 0
            // larger than default for default empty table. It's already
            // supposed to be at default size.
            : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }
```

## 新增add方法

```java
    /**
    *   入口
    */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

     private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     *   确保添加的元素有地方存储，当第一次添加元素的时候     *   this.size+1 的值是1，所以第一次添加的时候会将当   *    前 elementData 数组的长度变为10：
    */
   private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }
    /**
    *  将修改次数（modCount）自增1，判断是否需要扩充
    *  数组长度,判断条件就是用当前所需的数组最小长度与
    *  数组的长度对比，如果大于0，则增长数组长度。
    */

      private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
    *  如果当前的数组已使用空间（size）加1之后 大于数组
    *  长度，则增大数组容量，扩大为原来的1.5倍。
    */

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**这个方法其实和上面的add类似，该方法可以按照元素的位置，指定位置插入元素，具体的执行逻辑如下：
    *   1）确保数插入的位置小于等于当前数组长度，并且不小于0，否则抛出异常
    *   2）确保数组已使用长度（size）加1之后足够存下 下一个数据
    *3）修改次数（modCount）标识自增1，如果当前数组已使用长度（size）加1后的大于当前的数组长度，则调用grow方法，增长数组
    *  4）grow方法会将当前数组的长度变为原来容量的1.5倍。
    *  5）确保有足够的容量之后，使用System.arraycopy 将需要插入的位置（index）后面的元素统统往后移动一位。
    *  6）将新的数据内容存放到数组的指定位置（index）上
    */
      public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }
    
```

## GET，SET方法

```java
     public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    public E set(int index, E element) {
    rangeCheck(index);

    /**
     *  确保set的位置小于当前数组的长度（size）并
     *  且大于0，获取指定位置（index）元素，然后放
     *  到oldValue存放，将需要设置的元素放到指定的
     *  位置（index）上，然后将原来位置上的元素
     *  oldValue返回给用户。
    */
    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
    }

```

## contains方法

```java
      public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    /** 调用indexOf方法，遍历数组中的每一个元素作对比，如果找到
    *   对于的元素，则返回true，没有找到则返回false。
    */
```

## remove方法

### 根据索引remove

```java
    /** 1）判断索引有没有越界

    *   2）自增修改次数

    *   3）将指定位置（index）上的元素保存到oldValue

    *   4）将指定位置（index）上的元素都往前移动一位

    *   5）将最后面的一个元素置空，好让垃圾回收器回收

    *   6）将原来的值oldValue返回  
    */ 
        public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }
```

### 根据对象remove

```java
        public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

     private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

```

## clear方法

```java
        public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }
```

## sublist方法

+ 该方法返回的是父list的一个视图，从fromIndex（包含），到toIndex（不包含）。fromIndex=toIndex 表示子list为空
+ 父子list做的非结构性修改（non-structural changes）都会影响到彼此：所谓的“非结构性修改”，是指不涉及到list的大小改变的修改。相反，结构性修改，指改变了list大小的修改。
+ 对于结构性修改，子list的所有操作都会反映到父list上。但父list的修改将会导致返回的子list失效(即如果修改父list后再操作子list就会报错)。
+ tips：如何删除list中的某段数据：
```java
    list.subList(from, to).clear();

    //一个经典题目
        List<String> list = new ArrayList<String>();  
        list.add("a");  
  
        // 使用构造器创建一个包含list的列表list1  
        List<String> list1 = new ArrayList<String>(list);  
        // 使用subList生成与list相同的列表list2  
        List<String> list2 = list.subList(0, list.size());  
        list2.add("b");  
  
        System.out.println(list.equals(list1));  
        System.out.println(list.equals(list2));

    /**
    *   false
    *   true
    */
```

## iterator方法

+ ArrayList可以得到两种迭代器，iterator()方法和listIterator()方法。iterator()中返回的迭代器只能从开始往后遍历，而listIterator()中返回的迭代器不止能从后往前遍历，还能从指定位置开始遍历。在操作迭代器时，允许中途调用了ArrayList的其他改变结构的方法，那么将会抛出异常，这就是所谓的fail-fast机制。在前面的很多方法中，都出现了modCOunt变量，该变量就是用于处理fail-fast机制的。在初始化迭代器时保存当前的modCount，然后在每个操作时检查当前的modCount是否与初始化时的一致，如果不一致，说明进行了改变结构的操作，那么将会抛出异常。
```java
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements Iterator<E> {
        int cursor;       //下一个返回元素的索引
        int lastRet = -1; // 返回的最后一个元素的索引
        int expectedModCount = modCount;   //初始化时保存当前modCount

        //是否有下一个元素，只要下一个返回元素的索引不是size，就说明还有下一个元素
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            //检查modCount是否改变
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        //删除元素
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                //删除当前元素
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                //由于remove中改变了modCount，重新保存当前的modCount
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
        //检查modCount是否改变了，如果改变了，直接抛出异常
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
```
+ 初始时cursor指向0，一旦调用next()方法cursor和lastRet就会+1，就会右移，而一旦调用remove()方法就会删除lastRet所指向的元素。cursor指向下一个返回的元素，lastRet指向最后一个返回的元素，所以lastRet=cursor-1。

### listIterator()

+ listIterator()方法有几个重载方法，其返回值都是一个ListIterator对象，如下：
```java
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }
```
+ 无参的开始索引为0，有参的可以指定迭代器开始的位置。ListItr的实现如下：
```java
    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            //将下一个返回元素的位置设置为index
            cursor = index;
        }

        //是否有前一个值
        public boolean hasPrevious() {
            return cursor != 0;
        }

        //后一个值的索引
        public int nextIndex() {
            return cursor;
        }

        //前一个值的索引
        public int previousIndex() {
            return cursor - 1;
        }

        //得到前一个值，与next()相对
        @SuppressWarnings("unchecked")
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        //更改当前lastRet的值
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        //在cursor位置添加数据
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
```
+ ListItr继承自Itr并实现了ListIterator接口，既可以实现向前遍历，又可以实现向后遍历，并且除了删除操作外，还可以添加数据、更改数据。 cursor游标既可以左移也可以右移，当调用previous()时就将返回cursor的前一个数据。

## System.arraycopy方法
+ 两者的区别在于，Arrays.copyOf()不仅仅只是拷贝数组中的元素，在拷贝元素时，会创建一个新的数组对象。而System.arrayCopy只拷贝已经存在的数组元素。System.arraycopy方法：如果是数组比较大，那么使用System.arraycopy会比较有优势，因为其使用的是内存复制，省去了大量的数组寻址访问等时间

```java
    /**
     * @param      src      源数组
     * @param      srcPos   源数组中的起始位置
     * @param      dest     目标数组
     * @param      destPos  目标数组中的起始位置
     * @param      length   需要被复制的元素个数
     * @exception  IndexOutOfBoundsException  如果在复制的过程中发生索引溢界异常
     * @exception  ArrayStoreException  如果源数组中的元素因为类型不匹配不能被复制到目标数组中
     * @exception  NullPointerException 如果源数组为null或者目标数组为null
     *如果源数组(src)和目标数组(dest)为相同的数组对象，则复制过程为：
     *① 将源数组中需复制的元素src[srcPos, ..., srcPos+length-1]复制到一个临时数组中，长度为length；
     *② 然后将临时数组中的内容复制到目标数组dest[destPos, ..., destPos+length-1]中。
     */
    public static native void arraycopy(Object src,  int  srcPos,Object dest, int destPos,int length);   //由修饰符native可知，该方法调用的为JDK中的底层函数

```
## java.util.Arrays.copyOf()

```java
    /** Arrays.copyOf()内部是通过System.arraycopy()实现的。

     * 重载了很多copyOf()方法
     *@param <T> 　　　　　数组中元素的类型
     * @param original 　　被复制数组
     * @param newLength    返回的数组的长度
     * @return   返回源数组的一个“副本”，为了去达到指定的长度，必要情况下需截断或用null值填充
     * @throws NegativeArraySizeException  如果参数newLength为负值
     * @throws NullPointerException        如果参数original为null
     * @since 1.6
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }
    /**
    Arrays.copyOf()的实现方式是：
    ① 内部新建一个长度为指定长度参数newLength的数组copy[]；
    ② 调用System.arraycopy(original, 0, copy, 0,Math.min(original.length, newLength))完成对数组复制的功能
    if(original.length >= newLength)  截断original[]；original[0, ..., newLength-1] -> copy[0, ..., newLength-1]
    if(original.length < newLength)  用null值填充；original[0, ..., original.length-1] -> copy[0, ..., original.length-1] nulls -> copy[original.length, ..., newLength-original.length+1]
    ③ 返回一个长度为newLength的数组copy[]，元素为②中得到的对应值
    */
```

## 小结

+ ArrayList自己实现了序列化和反序列化的方法，因为它自己实现了 private void writeObject(java.io.ObjectOutputStream s)和 private void readObject(java.io.ObjectInputStream s) 方法
+ ArrayList基于数组方式实现，无容量的限制（会扩容）
添加元素时可能要扩容（所以最好预判一下），删除元素时不会减少容量（若希望减少容量，trimToSize()），删除元素时，将删除掉的位置元素置为null，下次gc就会回收这些元素所占的内存空间。
+ 线程不安全
+ add(int index, E element)：添加元素到数组中指定位置的时候，需要将该位置及其后边所有的元素都整块向后复制一位
+ get(int index)：获取指定位置上的元素时，可以通过索引直接获取（O(1)）
+ remove(Object o)需要遍历数组
+ remove(int index)不需要遍历数组，只需判断index是否符合条件即可，效率比remove(Object o)高
+ contains(E)需要遍历数组
+ 使用iterator遍历可能会引发多线程异常
+ 遍历list删除时可以倒叙遍历，然后通过传对象删除。或者：
```java
   for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
            String str = ite.next();
            System.out.println(str);
            if (str.contains("b")) {
                ite.remove();
            }
        }
```
+ 每用一次remove就要用一次next，原因看源码。



