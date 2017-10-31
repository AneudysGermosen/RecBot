import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Mr. Levin
 * @version September 2017
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		//ChatBotSajarin chatbot1 = new ChatBotSajarin(); sorry just testing right now
		ChatBotSteven chatbot2 = new ChatBotSteven();
		
		System.out.println (chatbot2.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		


		while (!statement.equals("Bye"))
		{
			System.out.println (chatbot2.getResponse(statement));
			statement = in.nextLine();
		}
	}

}
