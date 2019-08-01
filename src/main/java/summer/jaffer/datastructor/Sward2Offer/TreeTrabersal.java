package summer.jaffer.datastructor.Sward2Offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Tree Traversal
 */
public class TreeTrabersal {

    /**
     * tree preorderTraversal
     * @param root
     * @return
     */
    private List<TreeNode> preorderTraversal(TreeNode root) {
        if (root == null) return null;
        List<TreeNode> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp != null) {
                list.add(temp);
            } else {
                continue;
            }
            stack.push(temp.right);
            stack.push(temp.left);
        }
        return list;
    }

    /**
     * tree inorderTraversal
     * @param root
     * @return
     */
    private List<TreeNode> inorderTraversal(TreeNode root) {
        if (root == null) return null;
        List<TreeNode> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        while (!stack.isEmpty()) {
            temp = stack.pop();
            list.add(temp);
            temp = temp.right;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
        }
        return list;
    }

}
