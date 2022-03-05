public class TreeHeightMain {

    public static void main(String[] args) {

        BstNode root = new BstNode(10, new BstNode(5, new BstNode(2, null, null), null), new BstNode(15, null, new BstNode(25, new BstNode(20, null, null), null)));
        System.out.println("Inorder");
        inorder(root);
        System.out.println("\nPreorder");
        preorder(root);
        System.out.println("\nHeight");
        System.out.println(height(root, 0));
    }

    private static int height(BstNode node, int height) {
        if (node == null)
            return height;

        int currentHeight = height + 1;
        int lHeight = height(node.getLeft(), currentHeight);
        int rHeight = height(node.getRight(), currentHeight);

        return Integer.max(lHeight, rHeight);
    }

    private static void inorder(BstNode node) {
        if (node == null)
            return;
        inorder(node.getLeft());
        System.out.print(" " + node.getData());
        inorder(node.getRight());
    }

    private static void preorder(BstNode node) {
        if (node == null)
            return;
        System.out.print(" " + node.getData());
        inorder(node.getLeft());
        inorder(node.getRight());
    }
}