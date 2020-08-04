package com.jinhe.common.util.StaticQueue;

public class StaticQueue<T> {
    private int queueCapacity = 1000;//默认队列为10个存储空间
    private QueueNode<T>[] nodeArray;
    private int queueSize;//队列的有效元素个数
    private int fontIndex;//队首位置
    private int endIndex;//队尾位置

    /**
     * 初始化栈
     */
    @SuppressWarnings("unchecked")
    public void init() {
        this.nodeArray = new QueueNode[this.queueCapacity];
        this.queueSize = 0;
        this.fontIndex = 0;
        this.endIndex = 0;
    }

    /**
     * 入队
     *
     * @param node
     * @return
     */
    public boolean add(QueueNode<T> node) {
//如果队列处于刚初始化状态
        if (this.fontIndex == this.endIndex) {
            this.queueSize++;
            this.nodeArray[this.fontIndex] = node;
            this.endIndex++;
        } else {
//判断当前列队尾节点是否是数组的最后一个节点
            if ((this.endIndex + 1) == this.queueCapacity) {
//如果当前列队尾节点处于数组的最后一个节点，列队首节点又处于数组的第一个节点位置没有出对 则表示列队满员
                if (this.fontIndex == 0) {
                    System.out.println("十分抱歉，队伍已经满员了无法插入");
                    return false;
//如果没有满员则继续添加
                } else {
                    this.nodeArray[endIndex] = node;
                    this.endIndex = 0;
                    this.queueSize++;
                }
            } else {//如果队列尾节点不处于数组最后一个节点

//如果队列尾节点的下一个节点是首节点则表示满员
                if (this.endIndex + 1 == this.fontIndex) {
                    System.out.println("十分抱歉，队伍已经满员了无法插入");
                    return false;
                } else {
                    this.nodeArray[this.endIndex] = node;
                    this.endIndex++;
                    this.queueSize++;
                }
            }
        }

        return true;
    }


    /**
     * 出队
     *
     * @return 这部分可以改成直接返回数据域 T
     */
    public QueueNode<T> peak() {
        if (this.endIndex == this.fontIndex) {
            System.out.println("队列无队员不能出队");
            return null;
        } else {
            QueueNode<T> re = this.nodeArray[this.fontIndex];
            this.fontIndex++;
            if (this.fontIndex == this.queueCapacity) {
                this.fontIndex = 0;
            }
            this.queueSize--;
            return re;
        }
    }

    /**
     * 返回当前队里的有效元素个数
     *
     * @return
     */
    public int size() {
        return this.queueSize;
    }

    /**
     *  
     *
     * @param capacity 初始化队里的存储空间大小
     */
    public StaticQueue(int capacity) {
        this.queueCapacity = capacity;
        init();
    }

    public StaticQueue() {
        init();
    }
}

