package com.owen.algorithm;

import java.util.HashMap;

/**
 * create a cache which is similar like LinkedHashMap
 */
public class LRUCache<K, V>
{
    private HashMap<K, Node<V>> map;
    private Node<V> head, tail;

    public LRUCache(){
        this.map = new HashMap<K, Node<V>>();
    }

    public void put(K key, V value){
        Node<V> storedValue = this.map.get(key);
        // value not exist before, insert directly
        if(storedValue == null){
            storedValue = new Node<V>(value);
            this.map.put(key, storedValue);
            if(this.tail != null){
                this.tail.next = storedValue;
            }
            storedValue.pre = this.tail;
            this.tail = storedValue;
        }
        else{
            // not the same value, we override it
            if(!storedValue.equals(value)){

            }
        }
    }

    private class Node<T>{
        private Node<T> pre;
        private Node<T> next;
        private T t;

        public Node(T t){
            this.t = t;
        }

        public Node<T> getPre()
        {
            return pre;
        }

        public void setPre(Node<T> pre)
        {
            this.pre = pre;
        }

        public Node<T> getNext()
        {
            return next;
        }

        public void setNext(Node<T> next)
        {
            this.next = next;
        }

        public T getT()
        {
            return t;
        }

        public void setT(T t)
        {
            this.t = t;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            Node<T> node = (Node<T>) o;

            return t.equals(node.t);
        }

        @Override
        public int hashCode()
        {
            return t.hashCode();
        }
    }
}
