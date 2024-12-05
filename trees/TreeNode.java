/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trees;

/**
 *
 * @author Admin
 */
class TreeNode {
    Student student;
    TreeNode left, right;

    public TreeNode(Student student) {
        this.student = student;
        left = right = null;
    }
}
