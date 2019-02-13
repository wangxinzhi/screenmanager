package com.system.pojo;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 自定义的优先级队列，继承于 Java 的 PriorityQueue.class
 * PriorityQueue.class 的排序是堆排，即只保证第一个元素是当前优先级队列中最小(或最大)的元素
 */
public class MyPriorityQueue extends PriorityBlockingQueue<ProgramItem> {

}
