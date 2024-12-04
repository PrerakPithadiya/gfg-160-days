
/**
 * A solution to convert integers to their word representation in English.
 * This class provides functionality to convert numbers from 0 to billions into words.
 */
class Solution {

    private static final String[] LESS_THAN_TWENTY = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    private static final String[] TENS = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    private static final String[] THOUSANDS = {
        "", "Thousand", "Million", "Billion"
    };

    /**
     * Converts a given integer to its word representation in English.
     *
     * @param n The integer to convert (can handle numbers from 0 to
     * Integer.MAX_VALUE)
     * @return A string containing the word representation of the number
     */
    public String convertToWords(int n) {
        if (n == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();
        int thousandIndex = 0;

        while (n > 0) {
            if (n % 1000 != 0) {
                result.insert(0, helper(n % 1000) + THOUSANDS[thousandIndex] + " ");
            }
            n /= 1000;
            thousandIndex++;
        }

        return result.toString().trim();
    }

    /**
     * Helper method to convert numbers less than 1000 to words.
     *
     * @param num The number to convert (should be less than 1000)
     * @return A string containing the word representation of the number
     */
    private String helper(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return LESS_THAN_TWENTY[num] + " ";
        }
        if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        }
        return LESS_THAN_TWENTY[num / 100] + " Hundred " + helper(num % 100);
    }

    /**
     * Test cases to verify the functionality of the number to words converter.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.convertToWords(0));  // Expected: Zero
        System.out.println(solution.convertToWords(9));  // Expected: Nine
        System.out.println(solution.convertToWords(19));  // Expected: Nineteen
        System.out.println(solution.convertToWords(99));  // Expected: Ninety Nine
        System.out.println(solution.convertToWords(100));  // Expected: One Hundred
        System.out.println(solution.convertToWords(101));  // Expected: One Hundred One
        System.out.println(solution.convertToWords(999));  // Expected: Nine Hundred Ninety Nine
        System.out.println(solution.convertToWords(1000));  // Expected: One Thousand
        System.out.println(solution.convertToWords(1000000));  // Expected: One Million
        System.out.println(solution.convertToWords(1000000000));  // Expected: One Billion
    }
}
