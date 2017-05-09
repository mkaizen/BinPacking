package main;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;

public class IntegerSorter {
	public static void main(String[] args){
		MaxPQ<Integer> pq = new MaxPQ<Integer>(); //Create a new max priority queue

		//read integers from standard in and insert them into the maxPQ
		while(StdIn.hasNextLine()){
			try{
				pq.insert(StdIn.readInt());
			}
			catch(Exception e){ //if the next line is not an integer
				break;
			}
		}
		while(!pq.isEmpty()){ //print out contents of maxPQ from greatest to least
			System.out.println(pq.delMax());
		}
	}
}
