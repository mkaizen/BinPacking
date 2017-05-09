package plant;

import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.MaxPQ;

public class WorstFit {
	public static void main(String[] args){
		//create max priority queue to store the disks
		//sorted by the amount of space remaining on the disk
		MaxPQ<Disk> pq = new MaxPQ<Disk>();
		int diskNum = 0;  //The number of disks used/name of current disk
		double totalSpace = 0; //The total amount of space used on the disks
		int totalFiles = 0; //the total number of files on the disks

		pq.insert(new Disk(diskNum++)); //insert first empty disk

		while(StdIn.hasNextLine()){
			int file;

			try{ //tries to read the next integer
				file = StdIn.readInt();
			}catch(NoSuchElementException e){ //if the next line is not an integer
				break;
			}

			if(pq.max().getSpaceLeft() - file < 0){ //if the max disk can not hold the file
				Disk disk = new Disk(diskNum++); //create a new disk
				disk.add(file); //add the file to the new disk
				pq.insert(disk); //add the disk to the priority queue
			}
			else{ //Otherwise add the file to the existing disk with the most space left
				Disk disk = pq.delMax(); //delete the disk and add it again so that it is resorted
				disk.add(file);
				pq.insert(disk);
			}
			totalSpace += (double)file/1000000; //update the total amount of space used
			totalFiles++; //update the total amount of files used
		}

		System.out.println("Sum of all files = " + totalSpace + " GB");  //print the total space used
		System.out.println("Disks used       = " + (diskNum)); //print the number of disks used

		if(totalFiles <= 100){ //print out the disk information if there were 100 or less files
			while(!pq.isEmpty()){
				System.out.println(pq.delMax().toString());
			}
		}
	}
}