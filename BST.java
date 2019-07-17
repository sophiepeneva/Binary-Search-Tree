# Binary-Search-Tree

import java.util.*;
import java.util.LinkedList;

public class BST {
    private class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BST() {
        this.root = null;
    }

    public void insert(int data) {
        root = insert(data, root);
    }

    private Node insert(int data, Node curr) {
        if (curr == null) {
            return new Node(data);
        }

        if (curr.data > data) {
            return insert(data, curr.left);
        }
        if (curr.data < data) {
            return insert(data, curr.right);
        }
        return curr;
    }

    public void printInorder() {
        printInorder(root);
    }

    private void printInorder(Node curr) {
        if (curr == null) {
            return;
        }
        printInorder(curr.left);
        System.out.print(curr.data + " ");
        printInorder(curr.right);
    }

    public boolean find(int data) {
        return find(data,root);
    }

    private boolean find(int data, Node curr) {
        if (curr == null) {
            return false;
        }
        if (curr.data == data) {
            return true;
        }
        if (curr.data > data) {
            return find(data, curr.left);
        }
        return find(data, curr.right);
    }

    public int getMin() {
        if (isEmpty()) return -1;
        return getMin(root).data;
    }

    private Node getMin(Node curr) {
        if (curr.left == null) {
            return curr;
        }
        return getMin(curr.left);
    }

    public int getMax() {
        if (isEmpty()) return -1;
        return getMax(root).data;
    }

    private Node getMax(Node curr) {
        if (curr.right == null) {
            return curr;
        }
        return getMax(curr.right);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void delete(int data) {
        if(!isEmpty()){
            root = delete(data, root);
        }
    }

    private Node delete(int data, Node curr) {
        if (curr == null) {
            return curr;
        }
        if (curr.data > data) {
            curr.left = delete(data, curr.left);
        }
        if (curr.data < data) {
            curr.right = delete(data, curr.right);
        }
        if (curr.data == data) {
            if (curr.left == null) {
                return curr.right;
            }
            if (curr.right == null) {
                return curr.left;
            }
            Node temp = getMin(curr.right);
            curr.data = temp.data;
            return delete(temp.data, curr.right);
        }
        return curr;
    }

    public void levelOrderTraversal(){
        if(!isEmpty()){
            levelOrderTraversal(root);
        }
    }

    private void levelOrderTraversal(Node curr){
        Queue<Node> levels = new LinkedList<>();
        levels.add(curr);
        while(!levels.isEmpty()){
            Node at = levels.remove();
            System.out.print(at.data + " ");
            if(at.left != null){
                levels.add(at.left);
            }
            if(at.right != null){
                levels.add(at.right);
            }
        }
    }

    public void verticalOrderTraversal(){
        Map<Integer, Integer> valuesAndPositions = new HashMap<>();
        verticalOrderTraversal(root, 0, valuesAndPositions);
        for(int key : valuesAndPositions.keySet()){
            System.out.print(key + " ");
            System.out.println();
        }
    }

    private void verticalOrderTraversal(Node curr, int vd, Map<Integer, Integer> map){
        if(curr == null)return;
        map.put(vd, root.data);
        verticalOrderTraversal(curr.left, vd - 1, map);
        verticalOrderTraversal(curr.right, vd + 1, map);
    }

    public int findFirstCommonParent(int a, int b){
        Node parent = findFirstCommonParent(a, b, root);
        return ( parent == null ) ? -1 : parent.data;
    }

    private Node findFirstCommonParent(int a, int b, Node curr){
        if(curr == null){
            return null;
        }
        if(curr.data > a && curr.data < b){
            return curr;
        }
        if(curr.data > a && curr.data >b){
            return findFirstCommonParent(a, b, curr.left);
        }
        return findFirstCommonParent(a, b, curr.right);
    }
}
