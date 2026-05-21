package edu.sdccd.cisc191;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Module 5 Lab: Recursion + Algorithms
 */
public class GameAlgorithms {

    // =========================
    // Binary Search (Recursive)
    // =========================

    public static int findMatchRecursive(int[] sortedMatchIds, int target) {
        return findMatchRecursiveHelper(sortedMatchIds, target, 0, sortedMatchIds.length - 1);
    }

    private static int findMatchRecursiveHelper(int[] arr, int target, int low, int high) {

        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2; // safer than (low + high) / 2

        if (arr[mid] == target) {
            return mid;
        }

        if (target < arr[mid]) {
            return findMatchRecursiveHelper(arr, target, low, mid - 1);
        } else {
            return findMatchRecursiveHelper(arr, target, mid + 1, high);
        }
    }

    // =========================
    // Binary Search (Iterative)
    // =========================

    public static int findMatchIterative(int[] sortedMatchIds, int target) {

        int low = 0;
        int high = sortedMatchIds.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (sortedMatchIds[mid] == target) {
                return mid;
            }

            if (target < sortedMatchIds[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    // =========================
    // Flood Fill (Recursive)
    // =========================

    public static int countConnectedTilesRecursive(char[][] map, int startRow, int startCol) {

        if (isOutOfBounds(map, startRow, startCol) || map[startRow][startCol] != '.') {
            return 0;
        }

        map[startRow][startCol] = '#';

        return 1
                + countConnectedTilesRecursive(map, startRow - 1, startCol)
                + countConnectedTilesRecursive(map, startRow + 1, startCol)
                + countConnectedTilesRecursive(map, startRow, startCol - 1)
                + countConnectedTilesRecursive(map, startRow, startCol + 1);
    }

    // =========================
    // Flood Fill (Iterative)
    // =========================

    public static int countConnectedTilesIterative(char[][] map, int startRow, int startCol) {

        if (isOutOfBounds(map, startRow, startCol) || map[startRow][startCol] != '.') {
            return 0;
        }

        Deque<CellPosition> stack = new ArrayDeque<>();
        stack.push(new CellPosition(startRow, startCol));

        int count = 0;

        while (!stack.isEmpty()) {

            CellPosition current = stack.pop();
            int row = current.row();
            int col = current.col();

            if (isOutOfBounds(map, row, col) || map[row][col] != '.') {
                continue;
            }

            map[row][col] = '#';
            count++;

            pushNeighbor(stack, row - 1, col);
            pushNeighbor(stack, row + 1, col);
            pushNeighbor(stack, row, col - 1);
            pushNeighbor(stack, row, col + 1);
        }

        return count;
    }

    // =========================
    // Tree Search (Recursive)
    // =========================

    public static boolean containsMatch(BracketNode root, String target) {
        return containsMatchHelper(root, target);
    }

    private static boolean containsMatchHelper(BracketNode node, String target) {

        if (node == null) {
            return false;
        }

        if (node.getMatchName().equals(target)) {
            return true;
        }

        return containsMatchHelper(node.getLeft(), target)
                || containsMatchHelper(node.getRight(), target);
    }

    // =========================
    // Utilities
    // =========================

    public static boolean isOutOfBounds(char[][] map, int row, int col) {
        return row < 0 || row >= map.length || col < 0 || col >= map[row].length;
    }

    public static void pushNeighbor(Deque<CellPosition> stack, int row, int col) {
        stack.push(new CellPosition(row, col));
    }
}