package com.owen.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V>
{
    private LinkedNode<K, V> head, tail;
    private Map<K, LinkedNode<K, V>> map;
    private int capacity;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.head = new LinkedNode(null, null);
        this.tail = new LinkedNode(null, null);
        this.head.setNextNode(this.tail);
        this.tail.setPreNode(this.head);
        this.map = new HashMap<K, LinkedNode<K, V>>();
    }

    public V get(K key){
        LinkedNode<K, V> valueNode = this.map.get(key);
        if(valueNode != null){
            valueNode.getPreNode().setNextNode(valueNode.getNextNode());
            valueNode.getNextNode().setPreNode(valueNode.getPreNode());
            appendToTail(valueNode);
            return valueNode.getValue();
        }
        return null;
    }

    public void set(K key, V value){
        LinkedNode<K, V> valueNode = this.map.get(key);
        if(valueNode != null){
            valueNode.getPreNode().setNextNode(valueNode.getNextNode());
            valueNode.getNextNode().setPreNode(valueNode.getPreNode());
            appendToTail(valueNode);
            valueNode.setValue(value);
            return;
        }
        LinkedNode<K, V> newNode = new LinkedNode(key, value);
        if(this.capacity == this.map.size() && this.capacity != 0){
            // remove head
            LinkedNode<K, V> removeNode = this.head.getNextNode();
            removeNode.getNextNode().setPreNode(this.head);
            this.head.setNextNode(removeNode.getNextNode());
            this.map.remove(removeNode.getKey());
        }
        if(this.capacity != 0){
            appendToTail(newNode);
            this.map.put(key, newNode);
        }
    }

    private void appendToTail(LinkedNode<K, V> newNode){
        if(newNode != null){
            this.tail.getPreNode().setNextNode(newNode);
            newNode.setPreNode(this.tail.getPreNode());
            this.tail.setPreNode(newNode);
            newNode.setNextNode(this.tail);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LRUCache: ");
        LinkedNode<K, V> node = this.head.getNextNode();
        while(node != null && node != this.tail){
            builder.append("[key=").append(node.getKey())
                    .append(",value=").append(node.getValue())
                    .append("]");
            node = node.getNextNode();
        }
        return builder.toString();
    }

    public static void main(String[] args){

        LRUCache<String, String> cache = new LRUCache<>(4);
        cache.set("1", "Owen");
        cache.set("2", "Jenney");
        cache.get("1");
        cache.set("3", "Vincent");
        System.out.println(cache.toString());

        cache.set("4", "Alice");
        cache.get("2");
        System.out.println(cache.toString());

        cache.set("5", "Ricy");
        System.out.println(cache.toString());
    }
}
