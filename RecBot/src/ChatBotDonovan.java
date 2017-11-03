import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Donovan Guo Period 2 
 * @version October-November 2017
 */
public class ChatBotDonovan
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello how may I help you?";
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
			response = "Enter a genre of games to start.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Then what is the purpose in calling for me?";
                	emotion--;
		}
		
		else if (findKeyword(statement,"Fps") >= 0 || findKeyword(statement,"fps") >= 0)
		{
			response = "Speaking of" + FPSList() + "is a good game";
		}
		else if (findKeyword(statement,"Platformer") >= 0) 
		{
			response = "I fond of" + PlatformList();
			emotion++;
		}
		else if (findKeyword(statement,"Fighter") >= 0)
		{
			response = FighterList() + "is a competitve game to get into";
			emotion++;
		}
		else if (findKeyword(statement,"Mobile") >= 0)
		{
			response = "I dont like mobile games but if I had to I would recommend" + MobileList();
		}
		else if (findKeyword(statement,"MOBA") >= 0)
		{
			response ="I'm not familiar with MOBAs but one of the big three is" + MOBAList();
		}
		else if (findKeyword(statement,"RPG") >= 0)
		{
			response = "A classic RPG is " + RPGList();
		}
	}
		
		//Rock,Paper,Scissors.
		if (findKeyword(statement,"Game") >= 0)
		{
			response = "";
			rpsgame();
		}
		
		
	
	
	/**
	 * Search for one word in phrase. MOk that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
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
	

	private void rpsgame()
	{System.out.println("Speaking of games let's play Rock, Paper, Scissors?");
		Scanner in = new Scanner (System.in);
		boolean isvalid = false;
		while (!isvalid) 
		{
			System.out.print("Please choose Rock,Paper,or Scissors");
			Random r = new Random ();
			String compchoice =(rpsResponses[r.nextInt(rpsResponses.length)]);
			String statement = in.nextLine();
			
			if (statement.equals("Rock") || statement.equals("rock"))
			{
				isvalid = true;
				System.out.println(compchoice);
				if (compchoice.equals("Rock"))
				{
					System.out.print("We Drawed");
				}
				else if (compchoice.equals("Scissors"))
				{
					System.out.print("You Win Congrats");
				}
				else if (compchoice.equals("Paper"))
				{
					System.out.print("I Win This Time!");
				}
			}
			else if (statement.equals("Scissors") || statement.equals("scissors")) 
			{
				isvalid = true;
				System.out.println(compchoice);
				if (compchoice.equals("Rock"))
				{
					System.out.print("I Win This Time");
				}
				else if (compchoice.equals("Scissors"))
				{
					System.out.print("We Drawed");
				}
				else if (compchoice.equals("Paper"))
				{
					System.out.print("You Win Congrats");
				}
			}
			else if (statement.equals("Paper") || statement.equals("paper")) 
			{
				isvalid = true;
				System.out.println(compchoice);
				if (compchoice.equals("Rock"))
				{
					System.out.print("You Win Congrats");
				}
				else if (compchoice.equals("Scissors"))
				{
					System.out.print("I Win This Time");
				}
				else if (compchoice.equals("Paper"))
				{
					System.out.print("We Drawed");
				}
			}
		}
	}
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
	private String FPSList()
	{
		Random r = new Random();
		return randomfps [r.nextInt(randomfps.length)];
	}
	
	private String RPGList()
	{
		Random r = new Random();
		return randomrpg [r.nextInt(randomrpg.length)];
	}
	
	private String MOBAList()
	{
		Random r = new Random();
		return randomMOBA [r.nextInt(randomMOBA.length)];
	}
	
	private String FighterList()
	{
		Random r = new Random();
		return randomfighter [r.nextInt(randomfighter.length)];
	}
	private String PlatformList()
	{
		Random r = new Random();
		return randomplatformer [r.nextInt(randomplatformer.length)];
	}
	
	private String MobileList()
	{
		Random r = new Random();
		return randommobile [r.nextInt(randommobile.length)];
	}
	
	private String [] randomNeutralResponses = {"I don't think I can work with that.",
			"Type (game) to play Rock,Paper,Scissors with me",
			"Out of Rock,Paper, and Scissor which do you like the most?",
			"How's it going",
			"I not sure that's Game related",
			"I don't think I know enough to help you with that.",
			"Sorry I was thinking about how to beat you. What did you say?"
	};
	private String [] randomAngryResponses = {"Bleh.", "I don't think this conversation will continue", "Have a good day bye.","I don't think any game could make you happy."};
	private String [] randomHappyResponses = {"I'm am very excited", "Today's a great day for gaming...like everyday", "Ready for another day?"};
	private String [] rpsResponses = {"Rock","Paper","Scissors"};
	private String [] randomfps = {"Overwatch","Call of Duty","Destiny 2","Battlefield 4"};
	private String [] randomMOBA = {"Smite","League of Legends","DOTA 2",};
	private String [] randomrpg = {"Final Fantasy 7","Fire Emblem","Dynasty Warriors"};
	private String [] randomfighter = {"Skullgirls","Marvel vs. Capcom","Injustice","Street Fighter","Tekken"};
	private String [] randomplatformer = {"Mario Odyssey","Sonic Mania","Banjo and Kazoey"};
	private String [] randommobile = {"Infinity Blade Series","Angry Birds","Final Fantasy Mobius"};
	
}
	

