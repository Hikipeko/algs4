# 第3章 查找



## 3.1 符号表

**定义**：储存键值对(Key, Value)的数据结构，支持：插入（put）,删除（delete） 查找（get）操作。

### 3.1.1 API

```java
// API for ST Symbol Table 
public class ST<Key, Value> {
    ST();
    void put(Key key, Value val);
    Value get(Key key);
    void delete(Key key)          {put(key, null);}
    boolean contains(Key key)     {return get(key) == null;}
    boolean isEmpty()             {return size == 0;}
    int size();
    Iterable<Key> keys(); //返回一个迭代器用以遍历所有的键，自定义的键需事先equals()
}
```

**规则** 

1. 每个键对应一个值
2. 若插入的键已存在，覆盖原有的值
3. 键和值均不能为null *此规定使得get()能够判断键是否存在，put(key, null)可以删除键*



### 3.1.2 有序符号表

**定义**：键是Comparable的对象

```java
// API for ordered ST Symbol Table 
public class ST<Key extends Comparable<key>, Value> {
    ST();
    void put(Key key, Value val);	//插入key
    Value get(Key key);				//获取key对应的value
    void delete(Key key);
    boolean contains(Key key);
    boolean isEmpty(); 
    int size();
    Key min();
    Key max();
    Key floor(Key key);				//小于等于key的最大键
    Key ceiling(Key key);			//大于等于key的最小键
    int rank(Key key);
    Key select(int k);
    void deleteMin()				{delete(min());}
    void deleteMax()				{delete(max());}
    int size(Key, lo, Key hi);		//[lo, hi]中键的数量
    Iterable<Key> keys(Key lo, Key, hi);  //[lo, hi]中的所有键，已排序
    Iterable<Key> keys(); //返回一个迭代器用以遍历所有的键，自定义的键需事先equals()
}
```

key的compareTo() == 0方法和equals()方法必须一致。

**用例1** 统计书中单词的出现频率 

```java
if (!st.contains(word)) st.put(word, 1);
else st.put(word, std.get(word) + 1);
```

特性

1. 混合查找，插入操作

2. 大量不同的键

3. 查找操作比插入操作多得多

   

### 3.1.4 无序链表中的顺序查找

**实现 **利用链表数据结构

**性能** get和put的复杂度为O(N)，其中get的平均比较次数为~N/2，非常低效。



### 3.1.5 有序数组的二分查找

**实现** 利用一堆平行数组，分别存储key和value

注意rank的实现终止细节（类似二分查找法）

```java
    //如果key存在，返回key的位置；若不存在，则返回表中小于它的键的数量（放入表后所在位置）
    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (hi + lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }
```

**Java迭代器**

迭代器 it 的两个基本操作是 next 、hasNext 和 remove。

调用 it.next() 会返回迭代器的下一个元素，并且更新迭代器的状态。

调用 it.hasNext() 用于检测集合中是否还有元素。

调用 it.remove() 将迭代器返回的元素删除。

**性能** get复杂度为O(logN)得到了有效提高，然而put的时间复杂度仍然为O(N)，这是由于移动数组元素时造成的大量开销。put的性能比链表实现提升了四倍左右。



## 3.2 二叉查找树

**定义** 二叉查找树（BST）每个节点的key都大于其左子树中任意节点的key，小于其右子树中任意节点的key。

### 3.2.1 基本实现

