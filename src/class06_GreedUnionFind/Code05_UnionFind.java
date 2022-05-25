package class06_GreedUnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code05_UnionFind {

    /**
     * nodes表用来记录每一个结点
     * parents表用来记录每一个结点的代表结点,用于实现向上指
     * size表只有当一个结点是代表结点的时候才会有记录
     *所以方法中的V v，默认都是在并查集里面的
     * @param <V>
     */
    public static class UnionFind<V> {

        public static class Node<V> {
            V value;

            public Node(V value) {
                this.value = value;
            }
        }

        public HashMap<V, Node> nodes;
        public HashMap<Node, Node> parents;
        public HashMap<Node, Integer> size;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            size = new HashMap<>();

            for (V v : values) {
                Node node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                size.put(node, 1);
            }
        }

        /**
         *
         * @param node:如果node不在nodes表里面，那么会返回自己
         * @return
         */
        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parents.get(node)) {
                stack.add(node);
                node = parents.get(node);
            }
            while (!stack.isEmpty()) {//扁平化
                parents.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(V a, V b) throws Exception {
                return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 小挂大，所以第一步是确认不是一个集合后找大小
         *
         * @param a
         * @param b
         */
        public void union(V a, V b) {
            Node head1 = findFather(nodes.get(a));
            Node head2 = findFather(nodes.get(b));
            if (head1 == head2) {
                return;
            }
            int size1 = size.get(head1);
            int size2 = size.get(head2);
            Node big = size1 >= size2 ? head1 : head2;
            Node small = big == head1 ? head2 : head1;
            parents.put(small, big);
            size.put(big, size1 + size2);
            size.remove(small);
        }

        public int sets() {
            return size.size();
        }

    }
}
