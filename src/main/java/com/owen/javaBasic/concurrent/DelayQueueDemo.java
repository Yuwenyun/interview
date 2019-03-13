package com.owen.javaBasic.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue is using PriorityQueue as it's base structure, items are organized by priority, which is time.
 * 1. the take() method peek() an item(the first one) from priority queue and check it's delay time,
 *    if not reached, await, otherwise poll it
 */
public class DelayQueueDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        MyCache<Integer, String> cache = new MyCache<>();
        cache.put(1, "Owen", 3, TimeUnit.SECONDS);
        Thread.sleep(1000 * 2);
        System.out.println(cache.get(1));
        Thread.sleep(1000 * 2);
        System.out.println(cache.get(1));
    }
}

// domain object to store in cache
class Pair<K, V>
{
    private K key;
    private V value;
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

class DelayItem<T> implements Delayed
{
    private static final long NANO = System.nanoTime();
    private final long time;
    private final T item;

    public DelayItem(T item, long timeout){
        this.item = item;
        this.time = System.nanoTime() - NANO + timeout;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - (System.nanoTime() - NANO), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if(other == this){
            return 0;
        }
        if(other instanceof DelayItem){
            DelayItem otherItem = (DelayItem)other;
            long diff = this.time - otherItem.time;
            if(diff < 0) return -1;
            else if(diff > 0) return 1;
            else return 0;
        }
        long d = getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
        return d == 0 ? 0 : (d < 0 ?  -1 : 1);
    }

    public T getItem() {
        return item;
    }
}

class MyCache<K, V>
{
    // manage the delay item
    private DelayQueue<DelayItem<Pair<K, V>>> queue = new DelayQueue<>();

    // store the real key/value
    // when poll from delay queue, the item may still not reach the delay time
    // which means it shall still be available in map
    private Map<K, V> map = new ConcurrentHashMap<>();

    public MyCache(){
        Thread daemonTask = new Thread(new Runnable(){
            @Override
            public void run() {
                daemonCheck();
            }
        });
        daemonTask.setDaemon(true);
        daemonTask.start();
    }

    private void daemonCheck(){
        while(true)
        {
            try {
                DelayItem<Pair<K, V>> item = queue.take();
                if(item != null){
                    Pair<K, V> pair = item.getItem();
                    map.remove(pair.getKey(), pair.getValue());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void put(K key, V value, long time, TimeUnit unit){
        V oldValue = map.put(key, value);
        if(oldValue != null){
            queue.remove(key);
        }

        long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
        queue.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime));
    }

    public V get(K key){
        return map.get(key);
    }
}
