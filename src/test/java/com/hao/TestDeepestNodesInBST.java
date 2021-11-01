package com.hao;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class TestDeepestNodesInBST {
    private static class ValidCustomizedClass implements Comparable<ValidCustomizedClass> {
        int myInt;
        String myStr;

        public ValidCustomizedClass(int myInt, String myStr) {
            this.myInt = myInt;
            this.myStr = myStr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ValidCustomizedClass that = (ValidCustomizedClass) o;
            return myInt == that.myInt && Objects.equals(myStr, that.myStr);
        }

        @Override
        public int hashCode() {
            return Objects.hash(myInt, myStr);
        }

        @Override
        public int compareTo(ValidCustomizedClass o) {
            return (this.myInt + this.myStr).compareTo(o.myInt + o.myStr);
        }

        @Override
        public String toString() {
            return "{ myInt=" + myInt + ", myStr='" + myStr + "'}";
        }
    }

    private static class InvalidCustomizedClass {
        int myInt;
        String myStr;

        public InvalidCustomizedClass(int myInt, String myStr) {
            this.myInt = myInt;
            this.myStr = myStr;
        }
    }

    /*
     *          11
     *     7          82
     *       9    12     90
     */
    @Test
    public void testGetDeepestBSTNodesWithIntegers() {
        List<Integer> lstNodes = Arrays.asList(new Integer[] {12, 11, 90, 82, 7, 9});
        DeepestNodesInBST bst = new DeepestNodesInBST(lstNodes);
        bst.buildBSTWithDeepestInfo();
        assertEquals(bst.getDeepestNodes().getMaxDepth(), 3);
        assertArrayEquals(bst.getDeepestNodes().getDeepestElements().toArray(), new Integer[] {9, 12, 90});
        System.out.println(bst.getDeepestNodes());
    }

    @Test
    public void testGetDeepestBSTNodesWithDuplicateIntegers() {
        List<Integer> lstNodes = Arrays.asList(new Integer[] {12, 11, 7, 90, 82, 7, 9});
        DeepestNodesInBST bst = new DeepestNodesInBST(lstNodes);
        bst.buildBSTWithDeepestInfo();
        assertEquals(bst.getDeepestNodes().getMaxDepth(), 3);
        assertArrayEquals(bst.getDeepestNodes().getDeepestElements().toArray(), new Integer[] {9, 12, 90});
        System.out.println(bst.getDeepestNodes());
    }

    @Test
    public void testGetDeepestBSTNodesWithValidCustomizedType() {
        List<ValidCustomizedClass> lstNodes = Arrays.asList(new ValidCustomizedClass[] {
                new ValidCustomizedClass(0, "a"), new ValidCustomizedClass(3, "b"),
                new ValidCustomizedClass(1, "z"), new ValidCustomizedClass(3, "a"),
                new ValidCustomizedClass(2, "z"), new ValidCustomizedClass(1, "c"),
                new ValidCustomizedClass(1, "b")});
        //sort objects: {(0,a), (1,b), (1,c), (1,z), (2,z), (3,a), (3.b)}
        DeepestNodesInBST bst = new DeepestNodesInBST(lstNodes);
        bst.buildBSTWithDeepestInfo();
        assertEquals(bst.getDeepestNodes().getMaxDepth(), 3);
        assertArrayEquals(bst.getDeepestNodes().getDeepestElements().toArray(),
                new ValidCustomizedClass[] {new ValidCustomizedClass(0, "a"), new ValidCustomizedClass(1, "c"),
                        new ValidCustomizedClass(2, "z"), new ValidCustomizedClass(3, "b")});
        System.out.println(bst.getDeepestNodes());
    }

    @Test
    public void testGetDeepestBSTNodesWithInvalidCustomizedType() {
        List<InvalidCustomizedClass> lstNodes = Arrays.asList(new InvalidCustomizedClass[]{
                new InvalidCustomizedClass(0, "a"), new InvalidCustomizedClass(1, "b")});
        try {
            DeepestNodesInBST bst = new DeepestNodesInBST(lstNodes);
            fail("Expected exception to be thrown");
            bst.buildBSTWithDeepestInfo();
        } catch (ClassCastException e) {
        }
    }
}
