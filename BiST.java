public class BiST {
    static class Node{
        int data;
        Node left, right;

        Node(int d){
            this.data = d;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insertN(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        
        if (root.data > val) {
            root.left = insertN(root.left, val);
        } else {
            root.right = insertN(root.right, val);
        }
        return root;
    }

    public static void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static Node deleteN(Node root, int val){
        if(root.data>val){
            root.left=deleteN(root.left, val);
        }else if(root.data<val){
            root.right=deleteN(root.right, val);
        }
        else{
            //no child
            if(root.left==null&&root.right==null){
                return null;
            }
            //one child
            if(root.left!=null){
                return root.right;
            }
            else if(root.right!=null){
                return root.left;
            }
            // both childrens
            Node is= inOrderSucc(root.right);
            root.data=is.data;
            root.right=deleteN(root.right, is.data);

        }
        return root;
    }

    public static Node inOrderSucc(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }

    
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String args[]) {
        Node root = null; 

        root = BiST.insertN(root, 20);
        root = BiST.insertN(root, 10);
        root = BiST.insertN(root, 30);
        root = BiST.insertN(root, 5);
        root = BiST.insertN(root, 15);
        root = BiST.insertN(root, 25);
        root = BiST.insertN(root, 35);

       

        System.out.print("In-order traversal: ");
        BiST.inOrder(root);
        System.out.println();  

        System.out.print("Pre-order traversal: ");
        BiST.preOrder(root);
        System.out.println(); 
        System.out.println("Deletion after 25");
        BiST.deleteN(root, 25);
        System.out.print("In-order traversal: ");
        BiST.inOrder(root);
        System.out.println();

        System.out.print("Post-order traversal: ");
        BiST.postOrder(root);
        System.out.println(); 

        System.out.println("Height of the tree: " + BiST.height(root));
    }
}
