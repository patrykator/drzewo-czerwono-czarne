

public class Main {
   public static void main(String[] args) {
       RBTree rbTree = new RBTree();
       
       rbTree.insert(10, 100);
       rbTree.insert(20, 200);
       rbTree.insert(5, 50);
       rbTree.insert(15, 150);
       rbTree.insert(25, 250);
       
       System.out.println("Wartość dla klucza 20: " + rbTree.get(20));
       System.out.println("Wartość dla klucza 5: " + rbTree.get(5));
       System.out.println("Wartość dla klucza 25: " + rbTree.get(25));
       
       System.out.println("Usuwanie węzła o kluczu 20, usunięta wartość: " + rbTree.remove(20));
      
       System.out.println("Wysokość drzewa: " + rbTree.height());
   }
}
