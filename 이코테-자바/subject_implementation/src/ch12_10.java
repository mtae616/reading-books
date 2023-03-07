public class ch12_10 {
    static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] copyMap = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[j][n - 1 - i] = arr[i][j];
            }
        }
        return copyMap;
    }

    static void pullKey(int start_i, int start_j, int[][] newMap, int[][] key) {
        int m = key.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                newMap[start_i + i][start_j + j] -= key[i][j];
            }
        }
    }

    static void putKey(int start_i, int start_j, int[][] newMap, int[][] key) {
        int m = key.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                    newMap[start_i + i][start_j + j] += key[i][j];
            }
        }
    }

    static boolean matchKey(int n, int[][] newMap) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newMap[i + n][j + n] != 1) return false;
            }
        }
        return true;
    }

    public static boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        int[][] newMap = new int[n * 3][n * 3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i + n][j + n] = lock[i][j];
            }
        }

        int idx = 0;
        while (idx < 4) {
            key = rotate(key);
            for (int i = 0; i < n * 2; i++) {
                for (int j = 0; j < n * 2; j++) {
                    putKey(i, j, newMap, key);
                    if(matchKey(n, newMap)) return true;
                    pullKey(i, j, newMap, key);
                }
            }
            idx += 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        solution(key, lock);
    }
}
