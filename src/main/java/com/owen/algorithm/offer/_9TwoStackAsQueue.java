package com.owen.algorithm.offer;

import java.util.Stack;

public class _9TwoStackAsQueue
{
    public static void main(String[] args)
    {
        _9TwoStackAsQueue outer = new _9TwoStackAsQueue();
        StackQueue<Integer> queue = outer.new StackQueue<>();
        queue.inqueue(3);
        queue.inqueue(4);
        System.out.println(queue.dequeue());
        queue.inqueue(5);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    class StackQueue<T>{
        private Stack<T> stack1;
        private Stack<T> stack2;

        public StackQueue(){
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void inqueue(T value){
            stack2.push(value);
        }

        public T dequeue(){
            if(stack1.isEmpty()){
                if(stack2.isEmpty()){
                    return null;
                }
                else{
                    while(!stack2.isEmpty()){
                        stack1.push(stack2.pop());
                    }
                }
                return stack1.pop();
            }
            return stack1.pop();
        }
    }
}
