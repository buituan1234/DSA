/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trees;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
class StudentTree {
    private TreeNode root;

    public void addStudent(int id, String name, double marks) {
        root = insert(root, new Student(id, name, marks));
    }

    private TreeNode insert(TreeNode node, Student student) {
        if (node == null) return new TreeNode(student);
        if (student.id < node.student.id) {
            node.left = insert(node.left, student);
        } else if (student.id > node.student.id) {
            node.right = insert(node.right, student);
        } else {
            System.out.println("Student with ID " + student.id + " already exists.");
        }
        return node;
    }

    public void deleteStudent(int id) {
        root = deleteNode(root, id);
    }

    private TreeNode deleteNode(TreeNode root, int id) {
        if (root == null) return null;

        if (id < root.student.id) root.left = deleteNode(root.left, id);
        else if (id > root.student.id) root.right = deleteNode(root.right, id);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.student = findMin(root.right).student;
            root.right = deleteNode(root.right, root.student.id);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public Student searchStudent(int id) {
        TreeNode result = search(root, id);
        return (result != null) ? result.student : null;
    }

    private TreeNode search(TreeNode root, int id) {
        if (root == null || root.student.id == id) return root;
        if (id < root.student.id) return search(root.left, id);
        return search(root.right, id);
    }

    public void editStudent(int id, String newName, double newMarks) {
        TreeNode node = search(root, id);
        if (node != null) {
            node.student.name = newName;
            node.student.marks = newMarks;
            node.student.rank = node.student.calculateRank(newMarks);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Method to collect all students in a list
    private void collectStudents(TreeNode node, List<Student> students) {
        if (node != null) {
            collectStudents(node.left, students);
            students.add(node.student);
            collectStudents(node.right, students);
        }
    }

    // Bubble Sort for students
    public void bubbleSort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).marks > students.get(j + 1).marks) {
                    // Swap students[j] and students[j + 1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Quick Sort Helper Methods
    private void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(students, low, high);
            quickSort(students, low, pivotIndex - 1);
            quickSort(students, pivotIndex + 1, high);
        }
    }

    private int partition(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).marks <= pivot.marks) {
                i++;
                // Swap students[i] and students[j]
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        // Swap students[i + 1] and students[high] (pivot)
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    // Method to display students sorted by marks using Bubble Sort or Quick Sort
    public void displayStudentsSortedByMarks(boolean useQuickSort) {
        List<Student> students = new ArrayList<>();
        collectStudents(root, students);

        if (useQuickSort) {
            // Quick Sort the students list
            quickSort(students, 0, students.size() - 1);
            System.out.println("Students sorted by Marks using Quick Sort:");
        } else {
            // Bubble Sort the students list
            bubbleSort(students);
            System.out.println("Students sorted by Marks using Bubble Sort:");
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void displayStudents() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.student);
            inOrderTraversal(node.right);
        }
    }
}