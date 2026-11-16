import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		/*
			Create an ArrayList of 100 Nodes
			Store random integers in each of them
			Print out all of the values
		*/

		ArrayList<Node> nodes = new ArrayList<Node>();
		Random rand = new Random();

		for (int i = 0; i < 100; i++) {
			int value = rand.nextInt(100);
			nodes.add(new Node(value));
		}

		for (int i = 0; i < nodes.size(); i++) {
			System.out.print(nodes.get(i).getData() + " ");
		}
	}
}
