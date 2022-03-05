import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nawed2010 on 04/08/19.
 */
public class BstMain {

    /**
     *                            15
                          10             20
                       1      11     19      25
                          8       16
     * @param args
     */
    public static void main(String[] args) {

        BstNode root = null;
        root = insertNode(root, 15);
        root = insertNode(root, 10);
        root = insertNode(root, 20);
        root = insertNode(root, 25);
        root = insertNode(root, 19);
        root = insertNode(root, 11);
        root = insertNode(root, 1);
        root = insertNode(root, 8);
        root = insertNode(root, 16);

        inorderTraversal(root);

        search(root, 15);

        System.out.println("Smallest Element "+findMin(root));

        System.out.println("Largest Element "+findMax(root));

        System.out.println("Height " +findHeight(root));

        root = deleteNode(root, 15);

        inorderTraversal(root);
        System.out.println("\n");
        preorderTraversal(root);
        bfsTraversal(root);
        System.out.println("\n");

        //Check if a tree is a BST
        System.out.println("Is Tree a BST- "+isTreeBst(root)); //Recursion using property of tree as left will be small and right large
        System.out.println("Is Tree a BST- "+isTreeBstBoundMethod(root, Integer.MIN_VALUE, Integer.MAX_VALUE)); //Checking if each node is falling in the bound

        System.out.println("\n");

        BstNode root1 = new BstNode(10, null, null);
        root1.setLeft(new BstNode(11, null, null));
        root1.setLeft(new BstNode(20, null, null));
        System.out.println("Is Tree a BST- "+isTreeBstInorderMethod(root1));

        //TODO -- Implement below func
        //System.out.println(inorderSuccessor(root, 15));
        //System.out.println(inorderPredecessor(root, 15));
    }

    public static BstNode prvNode = null;

    public static boolean isTreeBstInorderMethod(BstNode root) {
        if(root == null) {
            return true;
        }

        boolean flag1 = isTreeBstInorderMethod(root.getLeft());

        boolean flag2 = false;

        if(prvNode == null || root.getData() >= prvNode.getData()){
            flag2 = true;
            prvNode = root;
        }

        boolean flag3 = isTreeBstInorderMethod(root.getRight());

        if(flag1 && flag2 && flag3){
            return true;
        } else {
            return false;
        }

        /*if(isTreeBstInorderMethod(root.getLeft(), root.getData())
                && (root.getData() > prv)
                && isTreeBstInorderMethod(root.getRight(), root.getData())) {
            return true;
        } else {
            return false;
        }*/
    }

    //Better than isTreeBst in terms of performance - O(n)
    public static boolean isTreeBstBoundMethod(BstNode root, int min, int max) {
        if(root == null) {
            return true;
        }

        if (root.getData() > min && root.getData() <= max       //As left sub tree can hold duplicate values
                && isTreeBstBoundMethod(root.getLeft(), min, root.getData())
                && isTreeBstBoundMethod(root.getRight(), root.getData(), max)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Recursively check for all nodes in the left subtree
     * @param root
     * @param data
     * @return
     */
    public static boolean isLeftTreeSmall(BstNode root, int data) {

        if(root == null) {
            return true;
        }

        if(root.getData() <= data && isLeftTreeSmall(root.getLeft(), data)
                && isLeftTreeSmall(root.getRight(), data)) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * Different approach as to isLeftTreeSmall --> data must be lesser than min of right sub tree -- vice versa for left sub tree
     * @param root
     * @param data
     * @return
     */
    public static boolean isRightTreeLarge(BstNode root, int data) {
        if(root == null) {
            return true;
        }

        int min = findMin(root);

        if(data < min) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isTreeBst(BstNode root) { //Performance O(n*n)
        if(root == null) {
            return true;
        }

        if(isLeftTreeSmall(root.getLeft(), root.getData()) && isRightTreeLarge(root.getRight(), root.getData())
                && isTreeBst(root.getLeft()) && isTreeBst(root.getRight())) {
            return true;
        } else {
            return false;
        }
    }

    public static void bfsTraversal(BstNode root) {
        if(root == null) {
            System.out.println("Tree is Empty");
            return;
        }
        Queue<BstNode> q = new LinkedList<>();
        q.add(root);

        System.out.print("BFS Traversal- ");

        while(!q.isEmpty()) {
            BstNode remove = q.remove();
            if(remove != null) {
                System.out.print(" "+remove.getData());
                q.add(remove.getLeft());
                q.add(remove.getRight());
            }
        }
    }

    public static BstNode deleteNode(BstNode root, int data) {
        if (root == null) {
            return root; //Tree is empty or data not found
        }

        if (root.getData() == data) {
            //Found Match

            //Case 1 if leaf node
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getLeft() != null && root.getRight() == null) {
                //Case 2 if right tree is null
                root = root.getLeft();
            } else if (root.getLeft() == null && root.getRight() != null) {
                //Case 3 if left tree is null
                root = root.getRight();
            } else {
                //Case 4 if both child are present
                int minInRightSubTree = findMin(root.getRight()); //Find Min in right tree
                //root.setRight(deleteNode(root.getRight(), minInRightSubTree)); //Delete the leaf node
                deleteNode(root, minInRightSubTree);
                root.setData(minInRightSubTree);
            }
            return root;

        } else if (data <= root.getData()) {
            //Traverse Left
            root.setLeft(deleteNode(root.getLeft(), data));
        } else {
            //Traverse right
            root.setRight(deleteNode(root.getRight(), data));
        }

        return root;
    }

    public static int findHeight(BstNode root) {
        if (root == null) {
            return - 1;
        }

        return Math.max(findHeight(root.getLeft()), findHeight(root.getRight())) + 1;
    }

    /**
     * Find Max without recursion --- right most child
     * @param root
     * @return
     */
    public static int findMax(BstNode root) {
        int max = Integer.MAX_VALUE;
        if(root == null) {
            System.out.println("Tree is Empty");
            return max;
        }

        while (root != null) {
            max = root.getData();
            root = root.getRight();
        }

        return max;
    }

    public static int findMin(BstNode root) {
        int minElement = Integer.MIN_VALUE;
       if ( root == null) {
           System.out.println("Tree is Empty");
           return minElement;
       } else if(root.getLeft() == null) {
           return root.getData();
       }

       return findMin(root.getLeft());
    }

    public static boolean search (BstNode root, int key ) {
        if (root == null) {
            System.out.println("\nNo Match Found "+key);
            return false;
        }

        if ( key == root.getData() ) {
            System.out.println("\nMatch Found "+key);
            return true;
        } else if (key <= root.getData()) {
            return search(root.getLeft(), key);
        } else {
            return search(root.getRight(), key);
        }
    }

    public static BstNode insertNode(BstNode root, int data) {
        //If root is null or tree is empty
        if (root == null) {
            root =  new BstNode(data, null, null);
        } else if ( data <= root.getData()) {
            //Traverse left sub tree
            root.setLeft(insertNode(root.getLeft(), data));
        } else {
            //Traverse right sub tree
            root.setRight(insertNode(root.getRight(), data));
        }

        return root;
    }

    public static void inorderTraversal(BstNode root) {
        if(root == null) {
            return;
        }

        inorderTraversal(root.getLeft());

        System.out.print(" "+root.getData());

        inorderTraversal(root.getRight());

    }

    public static void preorderTraversal(BstNode root) {
        if(root == null) {
            return;
        }

        System.out.print(" "+root.getData());
        preorderTraversal(root.getLeft());
        preorderTraversal(root.getRight());

    }

}
