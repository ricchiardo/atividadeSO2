import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        this.left = this.right = null;
    }
}

public class BinaryTreeOperations {
    private static TreeNode root;

    public static void main(String[] args) {
        BinaryTreeOperations treeOperations = new BinaryTreeOperations();

        for (int i = 0; i < 20; i++) {
            int randomNumber = new Random().nextInt(101);
            treeOperations.insert(randomNumber);
        }

        System.out.println("Árvore Original:");
        treeOperations.printPreOrder();
        treeOperations.printInOrder();
        treeOperations.printPostOrder();
        treeOperations.printLevelOrder();

        for (int i = 0; i < 5; i++) {
            int valueToRemove = treeOperations.getRandomValue();
            treeOperations.remove(valueToRemove);
            System.out.println("\nRemovendo elemento " + valueToRemove);
        }

        System.out.println("\nÁrvore Após Remoção:");
        treeOperations.printPreOrder();
        treeOperations.printInOrder();
        treeOperations.printPostOrder();
        treeOperations.printLevelOrder();
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public void remove(int value) {
        root = removeRec(root, value);
    }

    private TreeNode removeRec(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = removeRec(root.left, value);
        } else if (value > root.value) {
            root.right = removeRec(root.right, value);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);

            root.right = removeRec(root.right, root.value);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public void printPreOrder() {
        System.out.print("Pre-Ordem: ");
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public void printInOrder() {
        System.out.print("In-Ordem: ");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.value + " ");
            printInOrder(root.right);
        }
    }

    public void printPostOrder() {
        System.out.print("Pós-Ordem: ");
        printPostOrder(root);
        System.out.println();
    }

    private void printPostOrder(TreeNode root) {
        if (root != null) {
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

    public void printLevelOrder() {
        System.out.print("Nível-Ordem: ");
        printLevelOrder(root);
        System.out.println();
    }

    private void printLevelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.value + " ");

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    private int getRandomValue() {
        Random random = new Random();
        int randomIndex = random.nextInt(countNodes(root));
        return getNthValue(root, randomIndex);
    }

    private int getNthValue(TreeNode root, int n) {
        if (root == null) {
            return -1; // Valor sentinela para indicar que não há elementos suficientes
        }

        int leftCount = countNodes(root.left);

        if (n < leftCount) {
            return getNthValue(root.left, n);
        } else if (n == leftCount) {
            return root.value;
        } else {
            return getNthValue(root.right, n - leftCount - 1);
        }
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private void collectValues(TreeNode root, int[] values, int index) {
        if (root != null) {
            values[index] = root.value;
            collectValues(root.left, values, 2 * index + 1);
            collectValues(root.right, values, 2 * index + 2);
        }
    }
}