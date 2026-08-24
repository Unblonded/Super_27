package pkg;
import java.util.*;
import java.io.*;

public class BBoard {		// This is your main file that connects all classes.
	// Think about what your global variables need to be.
    Scanner sc;
    String title;
    User user;
    ArrayList<Message> msgList;
    ArrayList<User> userList;

	// Default constructor that creates a board with a defaulttitle, empty user and message lists,
	// and no current user
	public BBoard() {
		sc = new Scanner(System.in);
		title = "Empty";
		user = null;
		msgList = new ArrayList<>();
		userList = new ArrayList<>();
	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {
		sc = new Scanner(System.in);
		title = ttl;
		user = null;
		msgList = new ArrayList<>();
		userList = new ArrayList<>();
	}

	// Gets a filename of a file that stores the user info in a given format (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		File users = new File(inputFile);
		Scanner scanner = new Scanner(users);

		while(scanner.hasNextLine()) {
			String scannedUser = scanner.nextLine();
			String username = scannedUser.substring(0, scannedUser.indexOf(" "));
			String password = scannedUser.substring(scannedUser.indexOf(" ") + 1);
			userList.add(new User(username, password));
		}

	}

	// Asks for and validates a user/password. 
	// This function asks for a username and a password, then checks the userList ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or 'Q' as username to quit
	// When the users chooses to quit, sayu "Bye!" and return from the login function
	public void login(){
		System.out.println(title);

		while(true) {
			System.out.print("Enter your username ('Q' or 'q' to quit): ");
			String input = sc.nextLine();
			if (input.equals("q") || input.equals("Q")) {
				System.out.println("Bye!");
				System.exit(0);
			}

			System.out.print("Enter your password: ");
			String pass = sc.nextLine();

			for(User i : userList) {
				if (i.check(input, pass)) {
					user = i;
					System.out.println();
					System.out.println("Welcome back " + user.getUsername() + "!");
					System.out.println();
					return;
				}
			}

			System.out.println("Invalid Username or Password\n");
		}
	}
	
	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately return without showing menu
	public void run(){
		this.login();
		if (user != null) {
			displayMenu();
			String action = sc.nextLine();
			System.out.println("");

			while (!action.equalsIgnoreCase("Q")) {
				switch (action.toUpperCase()) {
					case "D":
						this.display();
						break;
					case "N":
						this.addTopic();
						break;
					case "R":
						this.addReply();
						break;
					case "P":
						this.setPassword();
						break;
					default:
						System.out.println("Wrong Input - Please enter another.");
						break;
				}

				System.out.println("");
				displayMenu();
				action = this.sc.nextLine();
				System.out.println("");
			}

			System.out.println("Bye!");
		}
	}

	private void displayMenu() {
		System.out.println("Menu");
		System.out.println("  - Display Messages ('D' or 'd')");
		System.out.println("  - Add New Topic ('N' or 'n')");
		System.out.println("  - Add New Reply to a Topic ('R' or 'r')");
		System.out.println("  - Change Password ('P' or 'p')");
		System.out.println("  - Quit ('Q' or 'q')");
		System.out.print("Choose an action: ");
	}

	// Traverse the BBoard message list and print only Topic objects.
	// Each Topic is responsible for printing its nested replies recursively.
	// Replies are ignored here.
	public void display() {
		if (msgList.isEmpty()) {
			System.out.println("Nothing to Display");
			return;
		}

		for (Message message : msgList) {
			if (!message.isReply()) {
				System.out.println("--------------------------------------------");
				System.out.print("Message #" + message.getId() + ": ");
				message.print(0);
				System.out.println("--------------------------------------------");
			}
		}
	}

	// Create a new Topic (the first message in a new discussion thread).
	// Each Topic includes a subject and a body, each on a single line.
	/*
	Subject: "Thanks"
	Body: "I love this bulletin board that you made!"
	*/

	// Each Topic also stores the username of currentUser and a message ID,
	// where the ID is the message index + 1.
	// For example, the first message in msgList has index 0,
	// so its message ID is 1.
	private void addTopic() {
		System.out.print("Subject: ");
		String subject = this.sc.nextLine();
		System.out.print("Body: ");
		String body = this.sc.nextLine();
		Topic topic = new Topic(user.getUsername(), subject, body, msgList.size() + 1);
		msgList.add(topic);
	}

	// This function asks the user to enter a reply to a given Message (which may be either a Topic or a Reply, so we can handle nested replies).
	//		The addReply function first asks the user for the ID of the Message to which they are replying;
	//		if the number provided is greater than the size of messageList, it should output and error message and loop back,
	// 		continuing to ask for a valid Message ID number until the user enters it or -1.
	// 		(-1 returns to menu, any other negative number asks again for a valid ID number)
	
	// If the ID is valid, then the function asks for the body of the new message, 
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to that message:


	/*
			Enter Message ID (-1 for Menu): 9
			Body: It was a pleasure implementing this!
	*/

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the parent Message's list. 
	// The Reply's constructor should set the Reply's subject to "Re: " + its parent's subject.
	// Call the addChild function on the parent Message to push back the new Message (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList. 
	// Note: When the user chooses to return to the menu, do not call run() again - just return fro mthis addReply function. 
	public void addReply() {
		System.out.print("Enter Message ID (-1 for Menu): ");
		int messageId = sc.nextInt();
		sc.nextLine();

		while (messageId != -1 && (messageId <= 0 || messageId > this.msgList.size())) {
			System.out.println("Invalid Message ID!");
			System.out.print("Enter Message ID (-1 for Menu): ");
			messageId = this.sc.nextInt();
			this.sc.nextLine();
		}

		if (messageId == -1) return;

		System.out.print("Body: ");
		String body = this.sc.nextLine();

		Message parentMessage = this.msgList.get(messageId - 1);
		Reply reply = new Reply(user.getUsername(), ("Re: " + parentMessage.getSubject()), body, msgList.size() + 1);

		msgList.add(reply);
		parentMessage.addChild(reply);
	}

	// Change the current user's password.
	// The user must provide the old password first.
	// If it matches, they may enter a new password.
	// Enter 'c' or 'C' to cancel and return to the menu.
	public void setPassword() {
		while (true) {
			System.out.print("Old Password ('c' or 'C' for Menu): ");
			String oldPassword = sc.nextLine();
			if (oldPassword.equalsIgnoreCase("c")) return;

			if (user != null && user.check(user.getUsername(), oldPassword)) {
				System.out.print("Please enter your new password: ");
				String newPassword = sc.nextLine();

				while (newPassword.equalsIgnoreCase("c")) {
					System.out.println("Please choose a password other than 'c' or 'C'.");
					System.out.print("Please enter your new password: ");
					newPassword = sc.nextLine();
				}

				user.setPassword(oldPassword, newPassword);
				System.out.println("Password Accepted.");
				return;
			}

			System.out.println("Invalid Password, please re-enter.");
			System.out.println();
		}
	}
}
