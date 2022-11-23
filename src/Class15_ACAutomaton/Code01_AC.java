package Class15_ACAutomaton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code01_AC {//

    // 前缀树的节点
    public static class Node {
        // 如果一个node，end为空，不是结尾
        // 如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        // 只有在上面的end变量不为空的时候，endUse才有意义
        // 表示，这个字符串之前有没有加入过答案
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node() {
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    Node next = new Node();
                    cur.nexts[index] = next;
                }
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        public void build() {//前缀树构建好了之后开始连fail指针。
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);//按照层序来遍历
            Node cur = null, cfail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();// 当前节点弹出，所有子加入到队列里去 并且 设置子的fail指针
                for (int i = 0; i < 26; i++) { // 所有的路都下去试
                    if (cur.nexts[i] != null) { // 找到有效的路 等价于 cur.nexts[i]这个孩子非空
                        cfail = cur.fail;//父亲的fail
                        while (cfail != null && cfail.nexts[i] == null) cfail = cfail.fail;
                        //出while就是 通往孩子的路与父亲的fail下面的路有匹配 或者 父亲的fail已经走到null了
                        cur.nexts[i].fail = cfail == null ? root : cfail.nexts[i];
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root, follow = null;
            int path = 0;
            List<String> ans = new ArrayList<>();
			for (int i = 0; i < str.length; i++) { // 依次遍历文章中的字符，i位置
				path = str[i] - 'a'; // 路
				while (cur.nexts[path] == null && cur != root) {
					cur = cur.fail;
				}
				cur = cur.nexts[path] != null ? cur.nexts[path] : root;
				follow = cur;
				while (follow != root) {
					if(follow.endUse) {
						break;
					}
					if (follow.end != null) {
						ans.add(follow.end);
						follow.endUse = true;
					}
					follow = follow.fail;
				}
			}
            return ans;
        }

        public List<String> containWords2(String content) {
			char[] str = content.toCharArray();
			Node cur = root, follow = null;
			int path = 0, i = 0;
			List<String> ans = new ArrayList<>();
			while (i != str.length) {
				path = str[i] - 'a';
				while (cur != null && cur.nexts[path] != null) {//匹配成功
					cur = cur.nexts[path];
					i++;
					path = str[i] - 'a';
					follow = cur;
					while (follow != null && !follow.endUse) {
						if (follow.end != null) {
							ans.add(follow.end);
							follow.endUse = true;
						}
						follow = follow.fail;
					}
					//出while说明 follow走到空 或者 follow位置已经被添加过答案了
				}
				//出while说明 cur为空 或者 目前的i位置下，在线段树中找不到通路了，通过fail指针继续失败
				if (cur == null) {
					cur = root;
					i++;
				} else {
					cur = cur.fail;
				}
			}
			return ans;
		}
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }

}
