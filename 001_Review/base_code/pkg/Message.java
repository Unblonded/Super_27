package pkg;
import java.util.*;
import java.io.*;

public class Message {
    ArrayList<Message> childList;

    String author;
    String subject;
    String body;
    int id;

	// Default Constructor
	public Message() {
        childList = new ArrayList<Message>();
        author = "";
        subject = "";
        body = "";
        id = 0;
	}
	
	// Parameterized Constructor
	public Message(String auth, String subj, String bod, int i) {
        childList = new ArrayList<Message>();
		author = auth;
        subject = subj;
        body = bod;
        id = i;
	}

	// This function is responsbile for printing the Message
	// (whether Topic or Reply), and all of the Message's "subtree" recursively:

	// After printing the Message with indentation n and appropriate format (see output details),
	// it will invoke itself recursively on all of the Replies inside its childList, 
	// incrementing the indentation value at each new level.

	// Note: Each indentation increment represents 2 spaces. e.g. if indentation ==  1, the reply should be indented 2 spaces, 
	// if it's 2, indent by 4 spaces, etc. 
	public void print(int indentation){
		if (this.author.isEmpty() && this.subject.isEmpty() && this.body.isEmpty()) {
			System.out.println("Nothing to Display");
			return;
		}

		StringBuilder indentBuilder = new StringBuilder();
		for (int i = 0; i < indentation * 2; i++) indentBuilder.append(' ');
		String indent = indentBuilder.toString();

		System.out.println("\"" + this.subject + "\"");
		System.out.println(indent + "From " + this.author + ": " + "\"" + this.body + "\"");

        for (Message child : childList) {
            System.out.println();
            System.out.print(indent + "  Message #" + child.getId() + ": ");
            child.print(indentation + 1);
        }
	}

	// Default function for inheritance
	public boolean isReply(){
		return false;
	}

	// Returns the subject String
	public String getSubject(){
		return subject;
	} 

	// Returns the ID
	public int getId(){
        return id;
	}

	// Adds a child pointer to the parent's childList.
	public void addChild(Message child){
		if (child != null) childList.add(child);
	}

}
