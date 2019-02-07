package epi;

public class BSTInsertionDeletion {

    public static BstNode<Integer> insert(BstNode<Integer> root, int val) {

        BstNode<Integer> iter = root;
        BstNode<Integer> newNode = new BstNode<>(val);

        if(root == null) {
            return newNode;
        }

        while(iter != null) {

            if (iter.data == val) {
                break;
            }

            if(iter.data > val) {
                if(iter.left == null) {
                    iter.left = newNode;
                    break;
                } else {
                    iter = iter.left;
                }
            } else {
                if(iter.right == null) {
                    iter.right = newNode;
                    break;
                } else {
                    iter = iter.right;
                }
            }
        }

        return root;
    }
}
