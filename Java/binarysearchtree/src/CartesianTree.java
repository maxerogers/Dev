import java.util.Random;

public class CartesianTree {
                private static class Node {
                        char value;
                        int priority;
                        int childrenCount;
                        Node left;
                        Node right;
 
                        public Node(char value, int priority) {
                                this.value = value;
                                this.priority = priority;
                                this.childrenCount = 1;
                        }
 
                        public String toString() {
                                StringBuilder result = new StringBuilder();
                                toString(this, result);
                                return result.toString();
                        }
 
                        private void toString(Node head, StringBuilder result) {
                                if (head != null) {
                                        toString(head.left, result);
                                        result.append(head.value);
                                        toString(head.right, result);
                                }
                        }
                }
                Node head = null;
                Random random = new Random(8282871029381L);
 
                public void add(char value) {
                        Node newNode = new Node(value, random.nextInt());
                        head = merge(head, newNode);
                }
 
                private Node merge(Node left, Node right) {
                        if (left == null) {
                                return right;
                        } else if (right == null) {
                                return left;
                        } else if (left.priority >= right.priority) {
                                left.right = merge(left.right, right);
                                update(left);
                                return left;
                        } else {
                                right.left = merge(left, right.left);
                                update(right);
                                return right;
                        }
                }
 
                private void update(Node head) {
                        if (head != null) {
                                head.childrenCount = 1 + count(head.left) + count(head.right);
                        }
                }
 
                private int count(Node head) {
                        if (head == null) {
                                return 0;
                        } else {
                                return head.childrenCount;
                        }
                }
 
                private Node[] split(Node head, int needTake) {
                        if (head == null) {
                                return new Node[2];
                        } else if (count(head.left) + 1 <= needTake) {
                                Node[] result = split(head.right, needTake - count(head.left) - 1);
                                head.right = result[0];
                                result[0] = head;
                                update(head);
                                return result;
                        } else {
                                Node[] result = split(head.left, needTake);
                                head.left = result[1];
                                result[1] = head;
                                update(head);
                                return result;
                        }
                }
 
                public void shift(int startIndex, int endIndex, int shift) {
                        Node[] pair = split(head, startIndex);
                        Node left = pair[0];
                        Node middle = pair[1];
                        pair = split(middle, endIndex - startIndex + 1);
                        middle = pair[0];
                        Node right = pair[1];
                        pair = split(middle, shift);
                        Node middleLeft = pair[0];
                        Node middleRight = pair[1];
                        head = merge(left, middleRight);
                        head = merge(head, middleLeft);
                        head = merge(head, right);
                }
 
                public String toString() {
                        if (head == null) {
                                return "null";
                        } else {
                                return head.toString();
                        }
                }
        }