package main;

import java.util.ArrayList;

public class Disk implements Comparable<Disk>{
	private int spaceLeft; //how much space is left on the disk
	private ArrayList<Integer> file; //the files contained on the disk
	private int id; //the id of the disk

	//initialize the Disk
	public Disk(int id){
		this.id = id;
		spaceLeft = 1000000; //starts with 1 GB of space
		file = new ArrayList<Integer>();
	}

	//compares the space left on this disk and another disk
	@Override
	public int compareTo(Disk that){
		return Integer.compare(this.spaceLeft, that.spaceLeft);
	}


	//adds a file to the file array list
	public void add(int iFile){
		file.add(iFile);
		spaceLeft -= iFile;
	}
	 //returns the amount of space left on the disk
	public int getSpaceLeft(){
		return spaceLeft;
	}

	//returns the id of the disk
	public int getID(){
		return id;
	}

	//returns a string of the id, space left, and the size of the files on the disk
	@Override
	public String toString(){
		String out = "	";
		out = out.concat(Integer.toString(id) + "  ");
		out = out.concat(Integer.toString(spaceLeft) + ":  ");
		for(int i : file){
			 out = out.concat(Integer.toString(i) + " ");
		}
		return out;
	}
}
