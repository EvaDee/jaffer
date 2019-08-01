package summer.jaffer.datastructor.Sward2Offer;

import summer.jaffer.log.LogUtils;

import java.util.LinkedList;

/**
 * tree serialize
 *
 */
public class TreeSerialize {

    private static int index = -1;

    /**
     * 序列化树
     *
     * 前序遍历树组装字符串，遇到空孩子时字符串添加"$"代替
     * @param root
     * @return
     */
    String serialize(TreeNode root) {
        return preorderTraversal(root);
    }

    public TreeNode deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        return deserialize(str.split(","));
    }

    /**
     * 反序列化树
     *
     * 恢复树，根据index构建根节点，然后递归构建该节点的左子树和右子树，
     * 因为是序列化采用先序遍历，所以root节点左子树的根节点应为root节点索引+1，右子树根节点索引为左子树尾节点后第一个节点，
     * 故采用静态变量index在全局index++
     *
     * @param input
     * @return
     */
    public TreeNode deserialize(String[] input) {
        if (input == null || input.length == 0) return null;
        index++;
        TreeNode root = null;
        if (!input[index].equals("$")) {
            root = new TreeNode(new Integer(input[index]));
            root.left = deserialize(input);
            root.right = deserialize(input);
        }
        return root;
    }

    private String preorderTraversal(TreeNode root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp != null) {
                sb.append(temp.val + ",");
            } else {
                sb.append("$,");
                continue;
            }
            stack.push(temp.right);
            stack.push(temp.left);
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String inorderTraversal(TreeNode root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        while (!stack.isEmpty()) {
            temp = stack.pop();
            sb.append(temp.val + ",");
            temp = temp.right;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        String s = serialize(root);
        LogUtils.sout(s);
        TreeNode t = deserialize(s);
        System.out.println(t);
    }

}
