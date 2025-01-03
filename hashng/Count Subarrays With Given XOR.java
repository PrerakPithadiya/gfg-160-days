
import java.util.HashMap;

class Solution {

    public long subarrayXor(int arr[], int k) {
        HashMap<Integer, Integer> xorFreq = new HashMap<>();
        int currXor = 0;
        long count = 0;

        // Initialize map with 0 having frequency 1
        xorFreq.put(0, 1);

        for (int num : arr) {
            // Calculate running XOR
            currXor ^= num;

            // Find XOR needed from any previous prefix to get k
            int targetXor = currXor ^ k;

            // Add frequency of targetXor to count
            count += xorFreq.getOrDefault(targetXor, 0);

            // Update frequency of current XOR
            xorFreq.put(currXor, xorFreq.getOrDefault(currXor, 0) + 1);
        }

        return count;
    }
}
