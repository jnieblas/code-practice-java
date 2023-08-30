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
            int mod = columnNumber % 26;

            if (mod == 0) {
                mod = 26;
                columnNumber -= mod; // this inherently happens for EVERY number, since the remainder doesn't persist when dividing
                // However, it is faster to only manually subtract when mod == 0, since that is an additional operation that
                // automatically happens in the 25 other cases.
            }

            ans.append((char) ('A' + mod - 1));
            columnNumber /= 26; // automatically drops whatever the mod value is, allowing us to trim & find the next char
        }
        return ans.reverse().toString();
    }

}
