package class06_GreedUnionFind;

import java.util.*;

public class Code05_UnionFind_LinkedList {//链表实现

    /**
     * 集合里面的元素是类型是V，随意指定。如果元素类型刚好是整数，从0开始编号的话，那么可以使用数组的实现
     * nodes表用来记录每一个结点
     * parents表用来记录每一个结点的代表结点,用于实现向上指
     * size表只有当一个结点是代表结点的时候才会有记录
     * 所有方法中的V v，默认都是在并查集里面的
     * @param <V>
     */
    public static class UnionFind<V> {//左程云的实现，正确

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
        private Node findFather(Node node) {
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

        public boolean isSameSet(V a, V b)  {//代表结点一样说明是同一个集合
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

        public int maxSize(){//返回元素个数最多的集合的元素个数
            int max=Integer.MIN_VALUE;
            for (Integer value : size.values()) {
                max = Math.max(max, value);
            }
            return max;
        }
    }

    /**
     * 我的实现，测试通过
     */
    static class UnionFind2<V> {

        public static class Node<V> {
            public V value;
            public Node parent;
            public Node(V value) {
                this.value = value;
            }

        }

        private HashMap<V, Node> nodes;//通过给定的V，快速找到对应的Node
        private HashMap<Node, Integer> size;//只有代表结点才有size值，一个代表结点代表一个集合

        public UnionFind2(List<V> values) {
            nodes = new HashMap<>();
            size = new HashMap<>();

            for (V v : values) {
                Node node = new Node<>(v);
                node.parent=node;//指向自己说明是代表结点
                nodes.put(v, node);
                size.put(node, 1);//初始状态默认一个结点一个集合
            }
        }

        /**
         *
         * @param node 如果node不在nodes表里面，那么会返回自己
         * @return 返回node所在集合的代表结点
         */
        private Node findFather(Node node) {
            if (node==node.parent) return node;
            else return node.parent=findFather(node.parent);//打扁平
        }

        public boolean isSameSet(V a, V b) {//代表结点一样说明是同一个集合
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
            if (size.get(head1)<size.get(head2)){//让head1指针永远指向大的
                Node tmp=head1;
                head1=head2;
                head2=tmp;
            }
            head2.parent=head1;//小挂大
            size.put(head1,size.get(head1)+size.get(head2) );//更新大集合的size
            size.remove(head2);//小集合的size移除
        }

        public int sets() {//返回集合的个数。集合的个数实际上就是sizeMap中键（代表结点）的个数
            return size.size();
        }

        public int maxSize(){//返回元素个数最多的集合的元素个数
            int max=Integer.MIN_VALUE;
            for (Integer value : size.values()) {
                max = Math.max(max, value);
            }
            return max;
        }

    }





    //test
    public static ArrayList<String> generateRandomStringList(int size){//生成一个规模为size的，装着随机String的List
        ArrayList<String> res=new ArrayList<>();
        HashSet<String> set=new HashSet<>();
        int len=20;//随机的字符串的长度在[0,20]
        int i=0;
        while (i < size) {//每次进while，生成一个长度随机的String
            int realLen=(int) (Math.random()*(len+1));//该随机字符串的长度
            StringBuilder builder= new StringBuilder();
            for (int j = 0; j < realLen; j++) {//用随机字符拼出一个随机字符串
                int ran=(int) (Math.random()*(25+1));//[0,25]随机，共26个字母
                char c=(char) (ran+'a');//[a,z]
//                int ran=(int) (Math.random()*(127+1));//[0,127]随机，共128个字符
//                char c=(char) ran;
                builder.append(c);
            }
            String str = builder.toString();
            if (!set.contains(str)){
                set.add(str);
                res.add(str);
                i++;//只有生成的字符串不一样的时候才i才++,才把str添加到res
            }
        }
        return res;
    }

    //for test
    public static boolean oneInTen(){//以1/10的概率产出一个true
        int random=0;//利用01发生器等概率随机产出一个[0000~1111]0~15的数
        do {
            random=0;
            for (int i = 0; i < 4; i++) {//对i位进行操作，最低位是第0位
                random+=oneOrZero()?1<<i:0;
            }
        }while (random>=10);//[0,9]刚好十个数字我才要
        if (random==0) return true;//1/10的概率产生true
        else return false;
    }

    //for rest
    public static boolean oneOrZero(){//01发生器
        return Math.random()>=0.5;
    }


    //for test
    public static void test1(){//测试证明两个UnionFind都是正确的
        int times=1000;
        int size=10000;
        boolean isok=true;
        for (int i = 0; i < times; i++) {//进行times次测试
            int realSize=(int) (Math.random()*(size+1));//[0,1000]随机数据规模
            ArrayList<String> list = generateRandomStringList(realSize);//list里面string的数量为realSize
            UnionFind<String> unionFind1=new UnionFind<>(list);
            UnionFind2<String> unionFind2=new UnionFind2<>(list);
            for (int j = 0; j < 1000; j++) {//每次测试随机进行times次的数据操作并且检查数据的正确性
                int index1=(int) (Math.random()*(realSize));//[0,size-1]
                int index2=(int) (Math.random()*(realSize));//[0,size-1]
                String s1 = list.get(index1);
                String s2 = list.get(index2);
                if (oneInTen()){//1的比例去union，9的比例去isSameSet
                    unionFind1.union(s1,s2);
                    unionFind2.union(s1,s2);
                    if (unionFind1.maxSize()!=unionFind2.maxSize()){
                        isok=false;
                        break;
                    }
                    if (unionFind1.sets()!=unionFind2.sets()){
                        isok=false;
                        break;
                    }
                }else {
                    if (unionFind1.isSameSet(s1,s2)!=unionFind2.isSameSet(s1,s2)){
                        isok=false;
                        break;
                    }
                }
            }
            if (!isok) break;
        }
        System.out.println(isok?"success":"fail");
    }

    //for test
    public static void test2(){//测试十分之一发生器
        int t=0;
        int f=0;
        for (int i = 0; i < 1000; i++) {
            if (oneInTen()) t++;
            else f++;
        }
        System.out.println("f = " + f);
        System.out.println("t = " + t);
    }

    //for test
    public static void test3(){//随机 String 的 List的生成器
        ArrayList<String> strings = generateRandomStringList(20);
        System.out.println("size:"+strings.size());
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) {
//        test2();
//        test1();
        test3();
    }


}
