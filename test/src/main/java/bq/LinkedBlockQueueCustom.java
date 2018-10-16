package bq;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockQueueCustom<E> {

    //容量
    public int capacity = 5;
    //数量计数器
    public AtomicInteger count = new AtomicInteger();

    //节点
    private class Node<E> {
        //下个节点信息
        Node next;
        //节点信息
        E item;
        Node(E x) {
            this.item = x;
        }
    }

    //队列头
    private Node<E> head = null;
    //队列尾
    private Node<E> last = null;

    //取锁
    private final ReentrantLock takeLock = new ReentrantLock();
    //取元素
    private final Condition notEmpty = takeLock.newCondition();

    //放锁
    private final ReentrantLock putLock = new ReentrantLock();
    //拿元素
    private final Condition notFull = putLock.newCondition();


    public void put(E e) throws Exception{
        if (e == null) {
            throw new NullPointerException();
        }

        ReentrantLock lock = this.putLock;
        lock.lock();
        int c = -1;
        try {
            c = count.get();
            if (c + 1 > capacity) {
                notFull.await();
            }
            addQueue(e);
            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }

        }catch (Exception ex) {

        }finally {
            lock.unlock();
        }

        if (c >= 0)
            signalNotEmpty();
    }

    public E take () {
        E result = null;
        int  c = -1;
        ReentrantLock lock = this.takeLock;
        lock.lock();
        try {
            if (count.get() ==0 ) {
                notEmpty.await();
            }
            result = takeQueue();
            c = count.getAndDecrement();
            if (c > 0)
                notEmpty.signal();
        } catch (Exception ex){

        }finally {
            lock.unlock();
        }

        if (c == capacity)
            signalNotFull();

        return result;
    }


    //把节点放到队列中去
    private void addQueue(E e) {
        Node node = new Node(e);
        if (head  == null) {
            head = last = node;
        }
        last = last.next = node;
    }

    //把节点偶从队列中取出来
    private E takeQueue() {
        if (head == null) {
            return null;
        }
        Node result = head;
        head = head.next;
        return head.item;
    }


    public void signalNotEmpty(){
        ReentrantLock lock = this.takeLock;
        lock.lock();
        try{
            notEmpty.signal();
        }catch (Exception ex) {

        }finally {
            lock.unlock();
        }
    }

    public void signalNotFull() {
        ReentrantLock lock = this.putLock;
        lock.lock();
        try {
            notFull.signal();
        }catch (Exception ex) {

        }finally {
            lock.unlock();
        }
    }
}
