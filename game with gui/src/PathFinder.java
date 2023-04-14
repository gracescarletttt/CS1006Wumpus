public class PathFinder {
    private int[][] test = new int[20][20];
    private boolean exists;
    public PathFinder(Cave[][] cave, int sx, int sy, int dx, int dy){
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if(cave[y][x].getCellState()){
                    test[y][x] = 0;
                }
                else{
                    test[y][x] = 3;
                }
                }
             }
             test[sx][sy] = 1;
             test[dx][dy] = 2;
        //     for (int y = 0; y < 20; y++) {
         //       System.out.println();
           //     for (int x = 0; x < 20; x++) {
             //       System.out.print(this.test[y][x]+ " ");
              //  }
        //    }
         isPath(test, 20);
}
public boolean getPath(){
    return this.exists;
}
 public void isPath(int matrix[][], int n)
    {
       
        // track of already visited indexes
        boolean visited[][] = new boolean[n][n];
 
        // Flag to indicate whether the
        // path exists or not
        boolean flag = false;
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // if matrix[i][j] is source
                // and it is not visited
                if (matrix[i][j] == 1 && !visited[i][j])
 
                    // Starting from i, j and
                    // then finding the path
                    if (isPath(matrix, i, j, visited)) {
                        // if path exists
                        flag = true;
                        break;
                    }
            }
        }
        if (flag)
            this.exists = true;
        else
            this.exists = false;
    }
 
    // Method for checking boundaries
    public static boolean isSafe(int i, int j,
                                 int matrix[][])
    {
 
        if (i >= 0 && i < matrix.length && j >= 0
            && j < matrix[0].length)
            return true;
        return false;
    }

    public static boolean isPath(int matrix[][], int i,
                                 int j, boolean visited[][])
    {
 
        // Checking the boundaries, walls and
        // whether the cell is unvisited
        if (isSafe(i, j, matrix) && matrix[i][j] != 0
            && !visited[i][j]) {
            // Make the cell visited
            visited[i][j] = true;
 
            // if the cell is the required
            // destination then return true
            if (matrix[i][j] == 2){
                return true;
            }

 
            boolean up = isPath(matrix, i - 1, j, visited);
            boolean left  = isPath(matrix, i, j - 1, visited);
            boolean down = isPath(matrix, i + 1, j, visited);
            boolean right = isPath(matrix, i, j + 1, visited);
 
           
            if (up){
                return true;
            }
           if (left){
                return true;
           }
           if (down){
                return true;
           }
 
      
            if (right){
                return true;
            }
        }
        // no path has been found
        return false;
    }

}
