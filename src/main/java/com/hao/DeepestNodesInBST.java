package com.hao;

import java.util.*;

/**
 * Your company needs you to implement a Binary Search Tree (BST), using the language of your choice. Your solution will be utilized by several different teams throughout your organization. Initially, this BST only needs to support integers.
 * Requirements
 * The BST must be built from an array of integers
 * Implement a method that returns the deepest nodes in the BST along with their depth
 * Implement any supporting methods needed for the solution to be useable by different teams throughout the organization
 * Example:
 * 	Input: 12,11,90,82,7,9
 * 	Output: deepest, 9; depth, 3
 * @param <E>
 */
public class DeepestNodesInBST<E extends Comparable<E>> {
    private List<E> uniqueElems = new ArrayList<>();
    private DeepestNodes deepestNodes = new DeepestNodes();

    protected static class TreeNode<E extends Comparable<E>> {
        public E element;
        public TreeNode left;
        public TreeNode right;
        public int depth;

        public TreeNode(E element, int depth) {
            this.element = element;
            this.depth = depth;
        }
    }

    protected static class DeepestNodes<E extends Comparable<E>> {
        private List<E> deepestElements = new ArrayList<>();
        private int maxDepth;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("max depth: ").append(maxDepth).append(" - ");
            sb.append(Arrays.toString(deepestElements.toArray()));
            return sb.toString();
        }

        public int getMaxDepth() {
            return maxDepth;
        }

        public List<E> getDeepestElements() {
            return deepestElements;
        }
    }

    /**
     * Since BST should be unique in general, and it is not necessary to consider the duplicate elements in the case.
     * constructor Sort/Deduplicate the input elements as the data source
     * @param elements
     */
    public DeepestNodesInBST(List<E> elements) {
        Collections.sort(elements);
        removeDuplicate(elements);
    }

    /**
     * Build a balanced binary search tree including depth info, and find and keep those nodes having the max depth
     */
    public void buildBSTWithDeepestInfo() {
        if (!uniqueElems.isEmpty()) {
            ListIterator<E> itr = uniqueElems.listIterator();
            startBuild(itr, 0, uniqueElems.size() - 1, 1);
        }
    }

    public DeepestNodes getDeepestNodes() {
        return this.deepestNodes;
    }

    /**
     * Recursively build the BST and find and keep those nodes having the max depth
     * @param itr
     * @param start
     * @param end
     * @param depth
     * @return
     */
    private TreeNode startBuild(ListIterator<E> itr, int start, int end, int depth) {
        if (start > end) {
            deepestNodes.maxDepth = Math.max(deepestNodes.maxDepth, depth - 1);
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode left = startBuild(itr, start, mid - 1, depth + 1);
        E currElem = itr.next();
        TreeNode parent = new TreeNode(currElem, depth);
        parent.left = left;
        TreeNode right = startBuild(itr, mid + 1, end, depth + 1);
        parent.right = right;
        if (left == null && right == null) {
            deepestNodes.deepestElements.add(currElem);
        }
        return parent;
    }

    /**
     * Remove duplicate elements from a sorted list
     * @param sortElements
     */
    private void removeDuplicate(List<E> sortElements) {
        E prev = null;
        for(E e : sortElements) {
            if (e.equals(prev)) {
                continue;
            }
            prev = e;
            uniqueElems.add(e);
        }
    }
}
