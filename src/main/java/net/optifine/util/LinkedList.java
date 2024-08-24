package net.optifine.util;

import java.util.Iterator;

public class LinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public void addFirst(Node<T> cNode) {
        this.checkNoParent(cNode);
        if (this.isEmpty()) {
            this.first = cNode;
            this.last = cNode;
        } else {
            Node<T> node = this.first;
            cNode.setNext(node);
            node.setPrev(cNode);
            this.first = cNode;
        }
        cNode.setParent(this);
        ++this.size;
    }

    public void addLast(Node<T> cNode) {
        this.checkNoParent(cNode);
        if (this.isEmpty()) {
            this.first = cNode;
            this.last = cNode;
        } else {
            Node<T> node = this.last;
            cNode.setPrev(node);
            node.setNext(cNode);
            this.last = cNode;
        }
        cNode.setParent(this);
        ++this.size;
    }

    public void addAfter(Node<T> nodePrev, Node<T> cNode) {
        if (nodePrev == null) {
            this.addFirst(cNode);
        } else if (nodePrev == this.last) {
            this.addLast(cNode);
        } else {
            this.checkParent(nodePrev);
            this.checkNoParent(cNode);
            Node<T> nodeNext = nodePrev.getNext();
            nodePrev.setNext(cNode);
            cNode.setPrev(nodePrev);
            nodeNext.setPrev(cNode);
            cNode.setNext(nodeNext);
            cNode.setParent(this);
            ++this.size;
        }
    }

    public Node<T> remove(Node<T> cNode) {
        this.checkParent(cNode);
        Node<T> prev = cNode.getPrev();
        Node<T> next = cNode.getNext();
        if (prev != null) {
            prev.setNext(next);
        } else {
            this.first = next;
        }
        if (next != null) {
            next.setPrev(prev);
        } else {
            this.last = prev;
        }
        cNode.setPrev(null);
        cNode.setNext(null);
        cNode.setParent(null);
        --this.size;
        return cNode;
    }

    public void moveAfter(Node<T> nodePrev, Node<T> node) {
        this.remove(node);
        this.addAfter(nodePrev, node);
    }

    public boolean find(Node<T> nodeFind, Node<T> nodeFrom, Node<T> nodeTo) {
        Node<T> node;
        this.checkParent(nodeFrom);
        if (nodeTo != null) {
            this.checkParent(nodeTo);
        }
        for (node = nodeFrom; node != null && node != nodeTo; node = node.getNext()) {
            if (node != nodeFind) continue;
            return true;
        }
        if (node != nodeTo) {
            throw new IllegalArgumentException("Sublist is not linked, from: " + String.valueOf(nodeFrom) + ", to: " + String.valueOf(nodeTo));
        }
        return false;
    }

    private void checkParent(Node<T> node) {
        if (node.parent != this) {
            throw new IllegalArgumentException("Node has different parent, node: " + String.valueOf(node) + ", parent: " + String.valueOf(node.parent) + ", this: " + String.valueOf(this));
        }
    }

    private void checkNoParent(Node<T> node) {
        if (node.parent != null) {
            throw new IllegalArgumentException("Node has different parent, node: " + String.valueOf(node) + ", parent: " + String.valueOf(node.parent) + ", this: " + String.valueOf(this));
        }
    }

    public boolean contains(Node<T> node) {
        return node.parent == this;
    }

    public Iterator<Node<T>> iterator() {
        Iterator iterator = new Iterator<Node<T>>(){
            Node<T> node;
            {
                this.node = LinkedList.this.getFirst();
            }

            @Override
            public boolean hasNext() {
                return this.node != null;
            }

            @Override
            public Node<T> next() {
                Node node = this.node;
                if (this.node != null) {
                    this.node = this.node.next;
                }
                return node;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
        return iterator;
    }

    public Node<T> getFirst() {
        return this.first;
    }

    public Node<T> getLast() {
        return this.last;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    public String toString() {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator<Node<T>> it = this.iterator();
        while (it.hasNext()) {
            Node<T> node = it.next();
            if (stringbuffer.length() > 0) {
                stringbuffer.append(", ");
            }
            stringbuffer.append(node.getItem());
        }
        return this.size + " [" + stringbuffer.toString() + "]";
    }

    public static class Node<T> {
        private final T item;
        private Node<T> prev;
        private Node<T> next;
        private LinkedList<T> parent;

        public Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return this.item;
        }

        public Node<T> getPrev() {
            return this.prev;
        }

        public Node<T> getNext() {
            return this.next;
        }

        private void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        private void setNext(Node<T> next) {
            this.next = next;
        }

        private void setParent(LinkedList<T> parent) {
            this.parent = parent;
        }

        public String toString() {
            return String.valueOf(this.item);
        }
    }
}

