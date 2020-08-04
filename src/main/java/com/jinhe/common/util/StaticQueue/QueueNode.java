package com.jinhe.common.util.StaticQueue;

public class QueueNode<T>{
    private T t;
    private QueueNode<T> queueNode;//由于静态队列是基于数组的连续存储实现的，指针域部分可以去掉，保留数据域即可
    public T getT() {
        return t;
    }
    public void setT(T t) {
        this.t = t;
    }
    public QueueNode<T> getQueueNode() {
        return queueNode;
    }
    public void setQueueNode(QueueNode<T> queueNode) {
        this.queueNode = queueNode;
    }

}