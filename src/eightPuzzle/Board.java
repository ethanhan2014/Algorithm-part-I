package eightPuzzle;

import edu.princeton.cs.algs4.Queue;

public class Board {
	
	private final int[] tiles;
	private final int N;
	
	
	/**
	 * construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j)
	 * @param blocks
	 */
	public Board(int[][] blocks) {
		N = blocks[0].length;
		tiles = new int[N*N];
		int k = 0;
		for(int i =0; i<N;i++){
			for(int j =0; j<N; j++){
				tiles[k] = blocks[i][j];
				k++;
			}
		}
	}
	
	/**
	 * 
	 * @return board dimension N
	 */
    public int dimension(){
    	return N;
    }
    
    /**
     * 
     * @return number of blocks out of place
     */
    public int hamming() {
    	int res = 0;
    	for(int i = 0; i<N*N; i++){
    		if(tiles[i] != 0 && tiles[i] != i+1){
    			res++;
    		}
    	}
    	return res;
    }
    
    /**
     * 
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
    	int res = 0;
    	for(int i = 0; i< N*N;i++){
    		if(tiles[i] != 0){
    			int val = Math.abs((tiles[i]-1)/N - i/N) + Math.abs((tiles[i]-1)%N-i%N);
    			res += val;
    		}
    	}
    	return res;
    }
    
    /**
     * 
     * @return is this board the goal board?
     */
    public boolean isGoal(){
    	return hamming() == 0;
    }
    
    /**
     * 
     * @return a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
    	int[][] blocks = this.getBlock();
    	Board twin = new Board(blocks);
    	for(int i =0; i<N*N-1;i++){
    		if(twin.tiles[i]!=0 && twin.tiles[i+1]!=0){
    			twin.swap(i,i+1);
    			break;
    		}
    	}
    	return twin;
    }

    private int[][] getBlock(){
    	int[][] res = new int[N][N];
    	int k = 0;
    	for(int i =0; i<N; i++){
    		for(int j = 0; j<N; j++){
    			res[i][j] = tiles[k];
    			k++;
    		}
    	}
    	return res;
    }
    
    //swap position i and position j
    private void swap(int i, int j){
    	int temp = tiles[i];
    	tiles[i] = tiles[j];
    	tiles[j] = temp;
    }
    
    /**
     * does this board equal y?
     */
    public boolean equals(Object y){
    	if(y == this) return true;
    	if(y == null) return false;
    	if(y.getClass() != this.getClass()) return false;
    	Board that = (Board) y;
    	if(that.dimension() != this.dimension()) return false;
    	for(int i = 0; i < N*N; i++){
    		if(that.tiles[i] != this.tiles[i]) return false;
    		}
    	return true;
    	}
    
    /**
     * 
     * @return  all neighboring boards
     */
    public Iterable<Board> neighbors(){
    	Queue<Board> neighbors = new Queue<Board>();
    	int loc = 0;
    	for(int i =0; i<N*N; i++){
    		if(tiles[i] == 0){
    			loc = i;
    			break;
    		}
    	}
    	//up
    	if(loc/N>0){	
    		Board n_up = new Board(this.getBlock());
    		n_up.swap(loc, loc-N);
    		neighbors.enqueue(n_up);
    	}
    	//down
    	if(loc/N<N-1){	
    		Board n_down = new Board(this.getBlock());
    		n_down.swap(loc, loc+N);
    		neighbors.enqueue(n_down);
    	}
    	//left
    	if(loc%N>0){	
    		Board n_left = new Board(this.getBlock());
    		n_left.swap(loc, loc-1);
    		neighbors.enqueue(n_left);
    	}
    	//right
    	if(loc%N<N-1){	
    		Board n_right = new Board(this.getBlock());
    		n_right.swap(loc, loc+1);
    		neighbors.enqueue(n_right);
    	}
    	
    	return neighbors;
    }
    
  
    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i*N+j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    //test
    public static void main(String[] args) {
    	int[][] tiles = {{0,1,3},{4,2,5},{7,8,6}};
    	Board game = new Board(tiles);
		System.out.println(game.toString());
		System.out.println(game.hamming());
		System.out.println(game.manhattan());
		
		System.out.println(game.twin().toString());
		System.out.println(game.twin().hamming());
		System.out.println(game.twin().manhattan());
		
		System.out.println(game.equals(game.twin()));
	}

}
