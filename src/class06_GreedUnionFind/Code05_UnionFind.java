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

        public HashMap<V, Node> nodes;//所有的结点都在这个map里
        public HashMap<Node, Node> parents;//<child,parent>,
        public HashMap<Node, Integer> size;//只有代表结点才有size值，一个代表结点代表一个集合

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            size = new HashMap<>();

            for (V v : values) {
                Node node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);//初始状态一个结点就是代表结点，代表结点的父节点就是自己
                size.put(node, 1);//初始状态默认一个结点一个集合
            }
        }

        /**
         *
         * @param node:如果node不在nodes表里面，那么会返回自己
         * @return 返回node所在集合的代表结点
         */
        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parents.get(node)) {//找父亲的同时把所有的其它结点都放到stack里面，方便后面扁平化
                stack.add(node);
                node = parents.get(node);
            }
            while (!stack.isEmpty()) {//扁平化
                parents.put(stack.pop(), node);//此时的node就是代表结点
            }
            return node;
        }

        public boolean isSameSet(V a, V b) throws Exception {//代表结点一样说明是同一个集合
                return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 小挂大，所以第一步先确认是不是一个集合。然后确定大集合和小集合。
         * @param a 某个结点的值
         * @param b 另一个结点的值
         */
        public void union(V a, V b) {
            Node head1 = findFather(nodes.get(a));
            Node head2 = findFather(nodes.get(b));
            if (head1 == head2) {//确认不是一个集合
                return;
            }
            int size1 = size.get(head1);
            int size2 = size.get(head2);
            Node big = size1 >= size2 ? head1 : head2;
            Node small = big == head1 ? head2 : head1;
            parents.put(small, big);//小挂大，小集合的代表结点的父亲是大集合的代表结点
            size.put(big, size1 + size2);//更新大集合的size
            size.remove(small);//小集合的size移除
        }

        public int sets() {//返回集合的个数。集合的个数实际上就是sizeMap中键（代表结点）的个数
            return size.size();
        }

    }
}
