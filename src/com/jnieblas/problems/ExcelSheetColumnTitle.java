package src.com.jnieblas.problems;

/**
 * Idea is to resolve each base 26 & move to the next one
 * So you resolve the 26, then divide by 26 to get the next base value
 * Think like base 10, 16, etc. Same principles apply, but it's base 26 & nums are represented by A-Z
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int columnNumber) {
        if (columnNumber < 27) {
            return Character.toString((char) ('A' + (columnNumber - 1) % 26));
        }

        StringBuilder ans = new StringBuilder();
        while (columnNumber > 0) {
            if (columnNumber % 26 == 0) {
                ans.append((char) ('A' + 25));
                // Since we already resolved this 26, we need to subtract it & go to the next one
                columnNumber -= 26;
            } else {
                ans.append((char) ('A' + columnNumber % 26 - 1));
            }
            columnNumber /= 26;
        }
        return ans.reverse().toString();
    }

}
