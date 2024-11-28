
class Solution {

    public int getLastMoment(int n, int left[], int right[]) {
        int maxTime = 0;

        // Calculate the time for ants moving to the left
        for (int position : left) {
            maxTime = Math.max(maxTime, position); // Time to fall off the left edge
        }

        // Calculate the time for ants moving to the right
        for (int position : right) {
            maxTime = Math.max(maxTime, n - position); // Time to fall off the right edge
        }

        return maxTime; // The moment when the last ant falls off
    }
}
