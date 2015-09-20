import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int N;
	private WeightedQuickUnionUF uf, ufb;
	private boolean[] opensites;
	
	/**
	 * create N-by-N grid, with all sites blocked
	 * @param N
	 */
	public Percolation(int N){
		
		this.N = N;
		if(N <= 0) throw new IllegalArgumentException();
		
		opensites = new boolean[N*N+1];
		
		uf = new WeightedQuickUnionUF(N*N+2);
		ufb = new WeightedQuickUnionUF(N*N+1);
		
		for(int i=1; i<N+1; i++){
			uf.union(0, i);
			uf.union(N*N+1, N*N-N+i);
			ufb.union(0, i);
		}
	}
	
	/**
	 * open site (row i, column j) if it is not open already
	 * @param i
	 * @param j
	 */
	public void open(int i, int j){
		int index = xyTo1D(i,j);
		if(!isOpen(i,j)){
			opensites[index] = true;
			if(j > 1 && isOpen(i,j-1)){
				uf.union(index, index-1);
				ufb.union(index, index-1);
			}
			if(j < N && isOpen(i,j+1)){
				uf.union(index, index+1);
				ufb.union(index, index+1);
			}
			if(i >1 && isOpen(i-1,j)){
				uf.union(index, index-N);
				ufb.union(index, index-N);
			}
			if(i<N && isOpen(i+1,j)){
				uf.union(index, index+N);
				ufb.union(index, index+N);
			}
		}
	}
	
	/**
	 * is site (row i, column j) open?
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j){
		int index = xyTo1D(i, j);
		return this.opensites[index];
	}
	
	/**
	 * is site (row i, column j) full?
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j){
		int index = xyTo1D(i, j);
		if(isOpen(i,j) && ufb.connected(0, index)) return true;
		return false;
	}
	
	/**
	 * does the system percolate?
	 * @return
	 */
	public boolean percolates(){
		return uf.connected(0, N*N+1);
	}
	
	/**
	 * convert 2D location to 1D index
	 * @param i
	 * @param j
	 * @return
	 */
	private int xyTo1D(int i, int j){
		if (i <= 0 || i > N || j <= 0 || j > N) 
			throw new IndexOutOfBoundsException("row index i out of bounds");
		return N*(i-1)+j;
	}

}
