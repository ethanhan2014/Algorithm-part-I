import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int size;
	private int total;
	private double[] prob;
	/**
	 * perform T independent experiments on an N-by-N grid
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T){
		if(N<=0 || T<=0) throw new IllegalArgumentException();
		size = N;
		total = T;
		prob = new double[total];
		int exp = 0;
		while(exp != T){
			Percolation perc = new Percolation(size);
			int site = 0;
			while(!perc.percolates()){
				int i = StdRandom.uniform(1, size+1);
				int j = StdRandom.uniform(1, size+1);
				if(!perc.isOpen(i, j)){
					perc.open(i, j);
					site += 1;
				}
			}
			prob[exp] = (double) site / (size*size);
			exp += 1;
		}
	}
	
	/**
	 * sample mean of percolation threshold
	 * @return
	 */
	public double mean(){
		return StdStats.mean(prob);
	}
	
	/**
	 * sample standard deviation of percolation threshold
	 * @return
	 */
	public double stddev() {
		return StdStats.stddev(prob);
	}
	
	/**
	 * low  endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceLo(){
		return mean() - 1.96*stddev()/Math.sqrt(total);
	}
	
	/**
	 * high endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceHi(){
		return mean() + 1.96*stddev()/Math.sqrt(total);
	}
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		PercolationStats perst = new PercolationStats(2, 10000);
		System.out.println(perst.mean());
		System.out.println(perst.stddev());
		System.out.println(perst.confidenceLo()+","+perst.confidenceHi());
	}

}
