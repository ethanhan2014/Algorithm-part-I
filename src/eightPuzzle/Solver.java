package eightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	//The constructor should throw a 
	//java.lang.NullPointerException if passed a null argument.
	
	private class SearchNode implements Comparable<SearchNode>{
		private int moves;
		private Board board;
		private SearchNode prev;
		private int priority;
		private int manhattan;
		
		public SearchNode(Board board, SearchNode prev){
			this.board = board;
			this.prev = prev;
			this.manhattan = board.manhattan();
			if(this.prev == null){
				moves = 0;
			}else{
				moves = prev.moves+1;
			}
			this.priority = this.manhattan + this.moves;
			assert((prev == null) || (this.priority >= prev.priority));
		}
		
		@Override
		public int compareTo(SearchNode that){
			if(this.priority > that.priority){
				return 1;
			}else if (this.priority < that.priority){
				return -1;
			}else{
				return 0;
			}
		}
	}
	
	private SearchNode node;
	private SearchNode twin;
	
	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * @param initial
	 */
    public Solver(Board initial){
    	
    	if(initial == null) throw new NullPointerException();
    	
    	if(initial.isGoal()){
    		node = new SearchNode(initial,null);
    	}
    	else{
    		MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
    		MinPQ<SearchNode> aux = new MinPQ<SearchNode>();
    		
    		queue.insert(new SearchNode(initial,null));
    		aux.insert(new SearchNode(initial.twin(),null));
    		
    		while(true){
    			twin = nextNode(aux);
    			if(twin.board.isGoal()){
    				clear(node);
    				clear(twin);
    				node = null;
    				return;
    			}
    			node = nextNode(queue);
    			if(node.board.isGoal()){
    				clear(twin);
    				return;
    			}
    		}
    	}
    }
    
    private SearchNode nextNode(MinPQ<SearchNode> pq){
    	SearchNode cur = pq.delMin();
    	addNeighbors(cur,pq);
    	return cur;
    }
    
    private void addNeighbors(SearchNode node, MinPQ<SearchNode> pq){
    	for(Board neighbor : node.board.neighbors()){
    		if(node.prev == null || (!neighbor.equals(node.prev.board))){
    			pq.insert(new SearchNode(neighbor,node));
    		}
    	}
    }
    
    private void clear(SearchNode node){
    	SearchNode cur = node.prev;
    	while(cur != null){
    		node = null;
    		node = cur;
    		cur = cur.prev;
    	}
    	node = null;
    }
    
    /**
     * 
     * @return true if initial board is solvable
     */
    public boolean isSolvable(){
    	if(node == null){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
     * 
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves(){
    	if(!isSolvable()) return -1;
    	else{
    		return node.moves;
    	}
    }
    
    /**
     * 
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution(){
    	if(!isSolvable()){
    		return null;
    	}
    	Stack<Board> stack = new Stack<Board>();
    	SearchNode cur = node;
    	while(cur != null){
    		stack.push(cur.board);
    		cur = cur.prev;
    	}
    	return stack;
    }

    //solve a slider puzzle (given below)
 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
    	
       Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
