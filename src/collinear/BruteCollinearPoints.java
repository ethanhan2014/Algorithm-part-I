package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
	private int lineNum = 0 ;
	private final List<LineSegment> lines;
	/**
	 * finds all line segments containing 4 points
	 * @param points
	 */
	public BruteCollinearPoints(Point[] points) {
		
		lines = new ArrayList<LineSegment>();
		int N = points.length;
		Arrays.sort(points);
		for(int i = 0; i<N-3; i++){
			for(int j = i+1; j<N-2; j++){
				for(int k = j+1; k<N-1; k++){
					for(int l = k+1; l<N; l++){
						if(points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])){
							if(points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])){
								LineSegment newline = new LineSegment(points[i],points[l]);
								lines.add(newline);
								lineNum += 1;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * the number of line segments
	 * @return the number of line segments
	 */
	public int numberOfSegments(){
		return lineNum;
	}
	
	/**
	 * the line segments
	 * @return the array of line segments
	 */
	public LineSegment[] segments(){
		LineSegment[] segments = new LineSegment[lines.size()];
		segments = lines.toArray(segments);
		return segments;
	}
	
	

}
