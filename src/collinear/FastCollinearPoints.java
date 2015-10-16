package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
	private int num = 0;
	private final List<LineSegment> lines;

	public FastCollinearPoints(Point[] points) {
		
		lines = new ArrayList<LineSegment>();
		int N = points.length;
		//sort points by coordinates
		Arrays.sort(points);
		Point[] aux = new Point[N];

		for(int i = 0; i< N-3; i++){

			for (int j =0; j< N; j++){
				aux[j] = points[j];
			}

			//sort points before and after point[i] by slope
			Arrays.sort(aux,0,i,aux[i].slopeOrder());
			Arrays.sort(aux,i+1,N,aux[i].slopeOrder());

			int head = i+1;
			int tail = i+2;
			int pHead = 0;

			while(tail<N){
				//if slope equal, move tail forward
				double headSlope = aux[i].slopeTo(aux[head]);
				while(tail<N && headSlope == aux[i].slopeTo(aux[tail])) tail++;
				//slopes not equal, check if it is a segment
				if(tail - head >=3){
					double pSlope = Double.NEGATIVE_INFINITY;
					while(pHead<i){
						pSlope = aux[i].slopeTo(aux[pHead]);
						if(pSlope < headSlope) pHead++;
						else	break;
					}
					if(pSlope != headSlope){
						LineSegment newline = new LineSegment(aux[i],aux[tail-1]);
						lines.add(newline);
						num++;
					}
				}
				head = tail;
				tail++;
			}		
		}
	}

	public int numberOfSegments(){
		return num;
	}

	public LineSegment[] segments(){
		LineSegment[] segments = new LineSegment[lines.size()];
		segments = lines.toArray(segments);
		return segments;
	}
	

}
