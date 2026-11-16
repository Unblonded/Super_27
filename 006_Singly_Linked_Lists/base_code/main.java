import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		MySinglyLinkedList list = new MySinglyLinkedList();
		Random rand = new Random();

		for (int i = 0; i < 20; i++) {
			list.insert(0, rand.nextInt(100));
		}

		for (int i = 0; i < 20; i++) {
			list.insert(rand.nextInt(20), -1);
		}

		list.printList();
		list.reverse();
		list.printList();
	}
}
