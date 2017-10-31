import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version: 1.0?
 * Author: Steven Fong
 * Period 2
 * Last update: 10/28/17
 */
public class ChatBotSteven
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hi, how can I help you today?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Please say something, I can't help if you don't.";
		}
		
		else if (findKeyword(statement, "rated", 0) >= 0)
		{
			response = ageCatagorizing(statement);
			if (findKeyword(statement, "")
		}
		

		else if (findKeyword(statement, "hate") >= 0)
		{
			response = "Why so negative?";
                	emotion--;
		}
		
		else if (findKeyword(statement, "young", 0) >= 0 || findKeyword(statement, "below", 0) >= 0 && findKeyword(statement, "15", 0) >= 0)
		{
			response = ageCatagorizing(statement);
			emotion++;
		}

		else if (findKeyword(statement, "scary") >= 0 || findKeyword(statement, "gore") >= 0 || findKeyword(statement, "spooky") >= 0)
		{
			response = transformhorrorscaryspookysupernatural(statement);
		}
		
		return response;
	}
	
	private String ageCatagorizing(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "rated", 0);
		String restofstatement = statement.substring(psn + 5).trim();
		if (findKeyword(statement, "G") >= 0)
		{
			return "Some good rated" + restofstatement + "movies are" + radnomGratedmovies;
		}
		if (findKeyword(statement, "PG - 13") >= 0 || findKeyword(statement, "PG-13"))
		{
			return "Some good rated" + restofstatement + "movies are" + randomPG13ratedmovies;
		}
	}
	private String transformhorrorscaryspookysupernatural(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return "If you like scary stuff, you can try watching " + randomNewScaryMovies;
		
	}
	
	private String transformComedyHappy(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return "If you want something funny you can try watching " + randomNewComedyMovies;
	}
	
	private String transformKidsfamilyfriendly(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return "If you want something funny you can try watching " ;
	}
	
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.   
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	
	private int findNumber(String statement, int num)
	{
		String number = "" + num;
		statement = statement.trim().toLowerCase();
		int psn = statement.indexOf(number);
		if (psn >= 0)
		{
			return psn;
		}
		
		return -1;
	}
	
	/*private int age(int num)
	{
		int result;
		while (num >= 0)
		{
			result = num;
			num--;
		}
		return result;
	}*/
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"It's all boolean to me.",
			"So, would you like to go for a walk?",
			"Could you say that again?"
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	private String [] randomNewScaryMovies = {"Happy Death Day.", "Anabelle: Creations.", "IT.", "Jigsaw.", "Get Out.", "Alience Covenant."};
	private String [] randomNewComedyMovies = {"Baywatch", "Ghostbusters", "Bad Moms", "Deadpool", "The Big Sick"};
	private String [] randomKidMovies = {"Captain Underpants", };
	private String [] randomGratedmovies = {"poof"};
	private String [] randomPG13ratedmovies = {""};
}
