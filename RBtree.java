public class RBTree {
   private static final boolean RED = true;
   private static final boolean BLACK = false;
   private class Node {
       int key;
       int value;
       Node left, right;
       boolean color;
       Node(int key, int value, boolean color) {
           this.key = key;
           this.value = value;
           this.color = color;
       }
   }
   private Node root;

   public void insert(int key, int value) {
       root = insert(root, key, value);
       root.color = BLACK; 
   }
   private Node insert(Node node, int key, int value) {
       if (node == null) return new Node(key, value, RED);
       if (key < node.key) {
           node.left = insert(node.left, key, value);
       } else if (key > node.key) {
           node.right = insert(node.right, key, value);
       } else {
           node.value = value; 
       }
       if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
       if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
       if (isRed(node.left) && isRed(node.right)) flipColors(node);
       return node;
   }

   public int get(int key) {
       Node node = root;
       while (node != null) {
           if (key < node.key) {
               node = node.left;
           } else if (key > node.key) {
               node = node.right;
           } else {
               return node.value;
           }
       }
       return -1; 
   }

   public int remove(int key) {
       int value = get(key); 
       root = remove(root, key);
       root.color = BLACK; 
       return value;
   }
   private Node remove(Node node, int key) {
       if (node == null) return null;
       if (key < node.key) {
           node.left = remove(node.left, key);
       } else if (key > node.key) {
           node.right = remove(node.right, key);
       } else {
           if (node.right == null) return node.left;
           if (node.left == null) return node.right;
           Node minNode = findMin(node.right);
           node.key = minNode.key;
           node.value = minNode.value;
           node.right = deleteMin(node.right);
       }
       return node;
   }
   private Node findMin(Node node) {
       while (node.left != null) {
           node = node.left;
       }
       return node;
   }
   private Node deleteMin(Node node) {
       if (node.left == null) return node.right;
       node.left = deleteMin(node.left);
       return node;
   }
  
   public int height() {
       return height(root);
   }
   private int height(Node node) {
       if (node == null) return -1;
       int leftHeight = height(node.left);
       int rightHeight = height(node.right);
       return 1 + Math.max(leftHeight, rightHeight);
   }

   private boolean isRed(Node node) {
       if (node == null) return false;
       return node.color == RED;
   }
   private Node rotateLeft(Node node) {
       Node rightChild = node.right;
       node.right = rightChild.left;
       rightChild.left = node;
       rightChild.color = node.color;
       node.color = RED;
       return rightChild;
   }
   private Node rotateRight(Node node) {
       Node leftChild = node.left;
       node.left = leftChild.right;
       leftChild.right = node;
       leftChild.color = node.color;
       node.color = RED;
       return leftChild;
   }
   private void flipColors(Node node) {
       node.color = RED;
       node.left.color = BLACK;
       node.right.color = BLACK;
   }
}
