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
		ChatBotSajarin chatbot1 = new ChatBotSajarin();
		ChatBotSteven chatbot2 = new ChatBotSteven();
		ChatBotDonovan chatbot3 = new ChatBotDonovan();
		
		System.out.println (chatbot3.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		


		while (!statement.equals("Bye"))
		{
			System.out.println (chatbot3.getResponse(statement));
			statement = in.nextLine();
		}
	}

}
