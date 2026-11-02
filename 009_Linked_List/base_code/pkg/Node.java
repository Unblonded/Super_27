package pkg;
import java.util.Scanner;
import java.util.Random;


public class Node {
	Node prev;
	Node next;
	int data;

	public Node(int data) {
		next = null;
		prev = null;
		this.data = data;
	}
	public int getData(){
		return data;
	}	
	public void setNext(Node next){
		this.next = next;
	}
	public void setPrev(Node prev){
		this.prev = prev;
	}
	public Node getNext(){
		return next;
	}
	public Node getPrev(){
		return prev;
	}
}
