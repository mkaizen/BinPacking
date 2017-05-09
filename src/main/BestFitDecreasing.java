package main;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;

public class BestFitDecreasing {
	public static void main(String[] args){
		//Create a new red black binary search tree to hold array lists of disks with keys
		//corresponding to the amount of space left on a disk
		RedBlackBST<Integer, ArrayList<Disk>> tree = new RedBlackBST<Integer, ArrayList<Disk>>();

		int diskNum = 0; 		//The number of disks used/name of current disk
		double totalSpace = 0;  //Total amount of space used on disks
		int totalFiles = 0;		//Number of files on disks

		while(StdIn.hasNextLine()){ //runs while there are more files
			int file;
			ArrayList<Disk> list;
			Disk disk;

			try{ //tries to read the next integer
				file = StdIn.readInt();
			}
			catch(NoSuchElementException e){ //if the next line is not an integer
				break;
			}

			try{  //If an existing disk can fit the file
				list = tree.get(tree.ceiling(file)); //get smallest list of disks that can fit file

				disk = list.remove(0); //delete disk from list

				if(list.isEmpty()) //delete list if empty
					tree.delete(disk.getSpaceLeft());
			}
			catch(Exception e){ //create a new disk if no disks can fit the file
				disk = new Disk(diskNum++);
			}

			disk.add(file); //add the file to the disk
			if(tree.contains(disk.getSpaceLeft())){ //if there is already an existing list for the disk
				list = tree.get(disk.getSpaceLeft());
				list.add(disk);  //add disk to the list
			}
			else{ //otherwise create a new list
				list = new ArrayList<Disk>();
				list.add(disk);  //add disk to new list
				tree.put(disk.getSpaceLeft(), list); //add new list to the tree
			}

			totalSpace += (double)file/1000000; //update totalSpace used
			totalFiles++; //increment how many files there are
		}

		System.out.println("Sum of all files = " + totalSpace + " GB"); //print the total space used
		System.out.println("Disks used       = " + (diskNum)); //print how many disks were used

		if(totalFiles <= 100) //print the disk information if there were 100 or less files
		while(!tree.isEmpty()){
				for(int i = 0; i < tree.get(tree.max()).size(); i++){
					System.out.println(tree.get(tree.max()).get(i).toString());
				}
				tree.deleteMax();
			}
	}
}
