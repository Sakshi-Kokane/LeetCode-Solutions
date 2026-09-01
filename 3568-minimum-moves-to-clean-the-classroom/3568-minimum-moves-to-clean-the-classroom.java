class Solution {

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Store litter positions and assign each one a bit
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = classroom[i].charAt(j);

                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                } 
                else if (cell == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int allCollectedMask = (1 << litterCount) - 1;

        // visited[row][col][mask][energyLeft]
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(startRow, startCol, energy, 0, 0));
        visited[startRow][startCol][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            State current = queue.poll();

            int row = current.row;
            int col = current.col;
            int energyLeft = current.energyLeft;
            int mask = current.mask;
            int moves = current.moves;

            // Try all 4 directions
            for (int d = 0; d < 4; d++) {
                int newRow = row + dr[d];
                int newCol = col + dc[d];

                // Boundary check
                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                // Cannot pass obstacle
                if (classroom[newRow].charAt(newCol) == 'X') {
                    continue;
                }

                // Need at least 1 energy to move
                if (energyLeft == 0) {
                    continue;
                }

                int newEnergy = energyLeft - 1;
                int newMask = mask;

                char nextCell = classroom[newRow].charAt(newCol);

                // Collect litter
                if (nextCell == 'L') {
                    int index = litterIndex[newRow][newCol];
                    newMask |= (1 << index);
                }

                // Reset energy
                if (nextCell == 'R') {
                    newEnergy = energy;
                }

                // All litter collected
                if (newMask == allCollectedMask) {
                    return moves + 1;
                }

                if (!visited[newRow][newCol][newMask][newEnergy]) {
                    visited[newRow][newCol][newMask][newEnergy] = true;

                    queue.offer(
                        new State(
                            newRow,
                            newCol,
                            newEnergy,
                            newMask,
                            moves + 1
                        )
                    );
                }
            }
        }

        return -1;
    }

    static class State {
        int row;
        int col;
        int energyLeft;
        int mask;
        int moves;

        State(int row, int col, int energyLeft, int mask, int moves) {
            this.row = row;
            this.col = col;
            this.energyLeft = energyLeft;
            this.mask = mask;
            this.moves = moves;
        }
    }
}