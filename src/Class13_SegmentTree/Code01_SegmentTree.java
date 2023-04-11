package Class13_SegmentTree;

public class Code01_SegmentTree {
    /**
     * 每个线段树都自己定制需要维持的数据，这个经典的线段树维持的就是sum这个数据。
     * lazy、change、update都是为了sum维持的懒空间，为了提供更高的效率。
     * arr也不是每一个线段树都有的，其实是一个废数据，只在build的时候有用，不要也行，直接在构造函数初始化sum
     */
    public static class SegmentTree {
        private int[] arr;// arr[]为原序列的信息从0开始，但在arr里是从1开始的,
        private int[] sum;// sum[]模拟线段树维护区间和
        private int[] lazy;// lazy[]为懒增加标记
        private int[] change;// change[]为更新的值
        private boolean[] update;// update[]为懒更新标记

        public SegmentTree(int[] origin) {
            int N = origin.length, digit = 0, size;
            while (N != 0) {//出循环digit为log2(N)的向上取整
                digit++;
                N >>= 1;
            }
            size = 1 << (digit + 1);//2^(log2(N)上取整+1)
            arr = new int[origin.length + 1]; // arr[0] 不用  从1开始使用
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];//在线段树中从1下标开始记
            }
            sum = new int[size]; // 用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[size]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
            change = new int[size]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
            update = new boolean[size]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        }

        private void pushUp(int rt) {//通过左右孩子把自己的值加出来
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        // l~r这个范围结点之前的所有懒增加和懒更新发给左右两个子范围结点,rt代表这个范围的结点对应的下标
        // 懒更新的if语句一定放在懒增加的if语句之前！因为懒更新完了之后是没有懒增加的
        private void pushDown(int l, int r, int rt) {//结算在rt中缓着的懒东西
            int mid = l + r >> 1;//中点
            if (update[rt]) {//之前有懒更新--没有更新孩子的只更新了自己的叫做懒更新
                update[rt << 1] = true;//左孩子
                update[rt << 1 | 1] = true;//右孩子
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = (mid - l + 1) * change[rt];//[l,mid]的个数
                sum[rt << 1 | 1] = (r - mid) * change[rt];//[mid+1,r]的个数
                update[rt] = false;
            }
            if (lazy[rt] != 0) {//之前有懒增加
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * (mid - l + 1);//[l,mid]的个数
                sum[rt << 1 | 1] += lazy[rt] * (r - mid);//[mid+1,r]的个数
                lazy[rt] = 0;
            }
        }

        /*
         * (sum.length+1)/2 = (sum.length-1)/2+1,  代表的是最后一层的第一个结点的下标，从前往后填好最后一层
         * (sum.length-1)/2 代表倒二层的最后一个结点的下标  从后往前填好其它层
         * sum.length+1>>1 = (sum.length+1)/2  位运算的优先级最低
         */
        public void build() {//初始化sum数组
            int i, j;
            for (i = 1, j = sum.length + 1 >> 1; i < arr.length; i++, j++) {//把arr中的元素按照顺序填到sum最后一层中
                sum[j] = arr[i];
            }
            for (j = sum.length - 1 >> 1; j >= 1; j--) {//最后一层填好之后，迭代的填好其它层
                pushUp(j);
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {//任务把我包住了，说明我要为这个任务付出一切,并且不需要通知下级了
                update[rt] = true;
                change[rt] = C;
                lazy[rt] = 0;//更新任务来了，说明我之前攒的懒增加也不需要告诉下级了，因为他们也要更新了
                sum[rt] = (r - l + 1) * C;
            } else {//当前任务没有把我包住，说明我不用亲自出门，交给下面的小弟来做即可，交给小弟做之前得把之前自己攒的懒东西告诉小弟
                pushDown(l, r, rt);//把自己私藏的小弟的信息都告诉给小弟
                int mid = l + r >> 1;
                if (L <= mid) update(L, R, C, l, mid, rt << 1);//如果任务有部分落在左半边,把任务交给左孩子去做
                if (R >= mid + 1) update(L, R, C, mid + 1, r, rt << 1 | 1);//如果任务有部分落在右半边，把任务交给右孩子去做
                pushUp(rt);//让左右孩子更新完了sum之后别忘了把自己的sum也更新好。
            }
        }

        public void update(int L, int R, int C) {//对外提供的接口
            if (L < 1 || R > arr.length - 1) return;
            update(L, R, C, 1, arr.length - 1, 1);
        }

        // L..R -> 任务范围 ,所有的值累加上C
        // l,r -> 表达的范围
        // rt  去哪找l，r范围上的信息
        private void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {//自己是要更新的，但是小弟不给他更新，等需要用到小弟的时候再告诉他--pushDown
                lazy[rt] += C;
                sum[rt] += (r - l + 1) * C;
            } else {
                pushDown(l, r, rt);
                int mid = l + r >> 1;
                if (L <= mid) add(L, R, C, l, mid, rt << 1);//如果任务有部分落在左半边,把任务交给左孩子去做
                if (R >= mid + 1) add(L, R, C, mid + 1, r, rt << 1 | 1);//如果任务有部分落在右半边，把任务交给右孩子去做
                pushUp(rt);//让左右孩子更新完了sum之后别忘了把自己的sum也更新好。
            }
        }

        public void add(int L, int R, int C) {//对外提供的接口，用户也要提供从1开始的下标
            if (L < 1 || R > arr.length - 1) return;
            add(L, R, C, 1, arr.length - 1, 1);
        }

        //   1~6 累加和是多少？ 1~8   rt
        private long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            } else {
                pushDown(l, r, rt);
                long ans = 0;
                int mid = l + r >> 1;
                if (L <= mid) ans += query(L, R, l, mid, rt << 1);//如果任务有部分落在左半边,把任务交给左孩子去做
                if (R >= mid + 1) ans += query(L, R, mid + 1, r, rt << 1 | 1);//如果任务有部分落在右半边，把任务交给右孩子去做
                return ans;
            }
        }


        public long query(int L, int R) {
            if (L < 1 || R > arr.length - 1) throw new RuntimeException();
            return query(L, R, 1, arr.length - 1, 1);
        }
    }

    public static class SegmentTree2 {

        private int N;//代表着里面有多少数据,代替了arr,1~N的范围
        private int[] sum;//主要的
        private int[] lazyAdd;
        private int[] lazyUpdate;
        private boolean[] isLazyUpdate;
        public SegmentTree2(int[] arr) {
            N = arr.length;
            int digit = 0, len = arr.length;
            while (len != 0) {//1>>digit其实是满二叉树最后一行的元素个数，最后一行乘2减1其实就是满二叉树总的个数
                digit++;
                len >>= 1;
            }
            int size = (1 << (digit + 1)) - 1 + 1;//+1是因为我们下标从1开始，(1<<digit+1)-1是满二叉树元素个数
            sum = new int[size];
            lazyAdd = new int[size];
            lazyUpdate = new int[size];
            isLazyUpdate = new boolean[size];

            for (int i = (sum.length + 1) >> 1, j = 0; j < arr.length; i++, j++) {//用j来做控制，因为最后一行不一定能装满
                sum[i] = arr[j];
            }
            for (int i = (sum.length - 1) >> 1; i >= 1; i--) {
                pushUp(i);
            }
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int l, int r,int rt) {
            int mid = l + (r - l >> 1);
            if (isLazyUpdate[rt]) {
                isLazyUpdate[rt << 1] = true;
                isLazyUpdate[rt << 1 | 1] = true;
                lazyUpdate[rt << 1] = lazyUpdate[rt];
                lazyUpdate[rt << 1 | 1] = lazyUpdate[rt];
                lazyAdd[rt << 1] = 0;
                lazyAdd[rt << 1 | 1] = 0;

                sum[rt << 1] = lazyUpdate[rt] * (mid - l + 1);
                sum[rt << 1 | 1] = lazyUpdate[rt] * (r - mid);
                isLazyUpdate[rt] = false;
            }
            if (lazyAdd[rt] != 0) {
                lazyAdd[rt << 1] += lazyAdd[rt];
                lazyAdd[rt << 1 | 1] += lazyAdd[rt];
                sum[rt << 1] += lazyAdd[rt] * (mid - l + 1);
                sum[rt << 1 | 1] += lazyAdd[rt] * (r - mid);
                lazyAdd[rt] = 0;
            }
        }

        public void add(int L,int R,int C){
            if (L < 1 || R > N) return ;
            add(L,R,C,1,N,1);
        }

        private void add(int L,int R,int C,int l,int r,int rt){
            if (L<=l&&r<=R){
                lazyAdd[rt]+=C;
                sum[rt]+=(r-l+1)*C;
            }else {
                pushDown(l,r,rt);
                int mid=l+(r-l>>1);
                if (mid>=L) add(L,R,C,l,mid,rt<<1);
                if (mid<R) add(L,R,C,mid+1,r,rt<<1|1);
                pushUp(rt);
            }
        }

        private void update(int L, int R, int C, int l, int r,int rt) {
            if (L<=l&&r<=R){
                isLazyUpdate[rt]=true;
                lazyUpdate[rt]=C;
                lazyAdd[rt]=0;
                sum[rt]=C*(r-l+1);
            }else {
                pushDown(l,r,rt);
                int mid=l+(r-l>>1);
                if (mid>=L) update(L,R,C,l,mid,rt<<1);
                if (mid<R) update(L,R,C,mid+1,r,rt<<1|1);
                pushUp(rt);
            }
        }

        public void update(int L, int R, int C) {//对外提供的接口
            if (L < 1 || R > N) return ;

            update(L, R, C, 1, N,1);
        }
        private long query(int L, int R,int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            } else {
                pushDown(l, r, rt);
                long ans = 0;
                int mid = l + r >> 1;
                if (L <= mid) ans += query(L, R,l, mid, rt << 1);//如果任务有部分落在左半边,把任务交给左孩子去做
                if (R >= mid + 1) ans += query(L, R,mid + 1, r, rt << 1 | 1);//如果任务有部分落在右半边，把任务交给右孩子去做
                return ans;
            }
        }


        public long query(int L, int R) {
            if (L < 1 || R > N) return -1;

            return query(L, R, 1, N,1);
        }


    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 10;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build();
            SegmentTree2 rig = new SegmentTree2(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4};
        SegmentTree seg = new SegmentTree(origin);
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build();
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
