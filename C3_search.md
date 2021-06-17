# 第3章 查找

## 3.1 符号表

**定义**：储存键值对(Key, Value)的数据结构，支持：插入（put）,删除（delete） 查找（get）操作。

### API

```java
// API for ST Symbol Table 
public class ST<Key, Value> {
    ST();
    void put(Key key, Value val);
    Value get(Key key);
    void delete(Key key)           {put(key, null);}
    boolean contains(Key key);     {reuturn get(key) == null;}
    boolean isEmpty();             {return size == 0;}
    int size();
    Iterable<Key> keys(); //返回一个迭代器用以遍历所有的键，自定义的键需事先equals()
}
```

**规则** 

1. 每个键对应一个值
2. 若插入的键已存在，覆盖原有的值
3. 键和值均不能为null *此规定使得get()能够判断键是否存在，put(key, null)可以删除键*



### 有序符号表

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







