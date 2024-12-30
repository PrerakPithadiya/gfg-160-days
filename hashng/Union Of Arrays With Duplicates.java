
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    // Solution 1: Using HashSet
    public static int findUnion(int a[], int b[]) {
        Set<Integer> unionSet = new HashSet<>();

        // Add elements from first array
        for (int num : a) {
            unionSet.add(num);
        }

        // Add elements from second array
        for (int num : b) {
            unionSet.add(num);
        }

        return unionSet.size();
    }

    // Solution 2: Using TreeSet (if sorted order is needed)
    public static int findUnionSorted(int a[], int b[]) {
        TreeSet<Integer> unionSet = new TreeSet<>();

        for (int num : a) {
            unionSet.add(num);
        }
        for (int num : b) {
            unionSet.add(num);
        }

        return unionSet.size();
    }

    // Solution 3: Using Stream API (more concise but potentially less efficient)
    public static int findUnionStream(int a[], int b[]) {
        return (int) Arrays.stream(a)
                .distinct()
                .concat(Arrays.stream(b).distinct())
                .distinct()
                .count();
    }

    // Solution 4: Using sorting and two-pointer (space-efficient but modifies input)
    public static int findUnionTwoPointer(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0, j = 0;
        int count = 0;
        int lastIncluded = Integer.MIN_VALUE;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                if (a[i] != lastIncluded) {
                    count++;
                    lastIncluded = a[i];
                }
                i++;
            } else if (b[j] < a[i]) {
                if (b[j] != lastIncluded) {
                    count++;
                    lastIncluded = b[j];
                }
                j++;
            } else {
                if (a[i] != lastIncluded) {
                    count++;
                    lastIncluded = a[i];
                }
                i++;
                j++;
            }
        }

        // Process remaining elements in array a
        while (i < a.length) {
            if (a[i] != lastIncluded) {
                count++;
                lastIncluded = a[i];
            }
            i++;
        }

        // Process remaining elements in array b
        while (j < b.length) {
            if (b[j] != lastIncluded) {
                count++;
                lastIncluded = b[j];
            }
            j++;
        }

        return count;
    }
}
