/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {

            // Go as far left as possible
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // Visit the node
            root = stack.pop();
            k--;

            // kth smallest found
            if (k == 0) {
                return root.val;
            }

            // Move to right subtree
            root = root.right;
        }

        return -1;
    }
}