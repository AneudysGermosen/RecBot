import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version: 1.0?
 * Author: Steven Fong
 * Period 2
 * Last update: 10/31/17
 */
public class ChatBotSteven
{
	//emotion and preference can alter the way our bot responds. Both can become more negative or positive over time.
	//*INCOMPLETE* Emotion part of the bot is not yet complete....
	int emotion = 0;
	int prefhor = 0;
	int prefcom = 0;
	int prefani = 0;
	int prefact = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		String greeting = "Hi, before we get into what you want to watch today I"
						+ " would like to know, in one word how do you feel today?";
		System.out.println(greeting);
		Scanner in = new Scanner(System.in);
		String mood = in.nextLine();
		String greeting2 = "So you are feeling " + mood + "?"
							+ " \nWell, hopefully I can be useful to you today!"
							+ "\nTo start, what movies do you like? Horror? Comedic? Animated? Action?" 
							+ "\n or perhaps you want to go by rating?"
							+ "If you are really unsure you can tell me how you feel.";
		if(mood.equals("great") || mood.equals("good") || mood.equals("energetic"))
		{
			emotion+=3;
			
		}
		else if (mood.equals("terrible") || mood.equals("depressed") || mood.equals("troubeled"))
		{
			emotion+=3;
		}
		else
		{
			emotion = 0;
		}
		return greeting2;
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
		
		if (findKeyword(statement, "hate") >= 0 || findKeyword(statement, "dislike") >= 0 || findKeyword(statement, "prefer not") >= 0)
		{
			if(findKeyword(statement, "want") < 0)
			{
				if (findKeyword(statement, "scary") >= 0 || findKeyword(statement, "gore") >= 0 || findKeyword(statement, "spooky") >= 0 || findKeyword(statement, "horror") >= 0)
				{
					response = "I will try to avoid recommending you anything too scary.";
					prefhor--;
					emotion--;
				}
				else if (findKeyword(statement, "funny") >= 0 || findKeyword(statement, "comedy") >= 0 || findKeyword(statement, "laugh") >= 0)
				{
					response = "I will try to keep my recommendations serious!!";
					prefcom--;
				}
				else if (findKeyword(statement, "animated") >= 0 || findKeyword(statement, "animation") >= 0 || findKeyword(statement, "motion picture") >= 0)
				{
					response = "Got it, I'll limit the animations recommendations just for you.";
					prefani--;
					emotion--;
				}
				else if(findKeyword(statement, "action") >= 0 || findKeyword(statement, "thriller") >= 0)
				{
					response = "I mean...I guess if you don't want me to I won't give you any more action movies...";
					prefact--;
				}
			}
		}
		if (findKeyword(statement, "feel") >= 0)
		{
			if (findKeyword(statement, "sad") >= 0 || findKeyword(statement, "depressed") >= 0 || findKeyword(statement, "bored") >= 0 || findKeyword(statement, "melancholy") >= 0)
			{
				emotion--;
			}
			else 
			{
				emotion++;
			}
			response = getRandomResponse();
		}	
		
		
		if (findKeyword(statement, "like") >= 0)
		{
			response = transformILikestatements(statement);
		}
		
		if (findKeyword(statement, "about") >= 0)
		{
			response = summary(statement) + transformaboutmoviesStatements(statement);
		}
		else if (findKeyword(statement, "rated", 0) >= 0)
		{
			response = ageCatagorizing(statement);
		}

		else if (findKeyword(statement, "scary") >= 0 || findKeyword(statement, "gore") >= 0 || findKeyword(statement, "spooky") >= 0 || findKeyword(statement, "horror") >= 0)
		{
			response = horrorMovies(statement);
			prefhor++;
			emotion++;
		}
		
		else if (findKeyword(statement, "funny") >= 0 || findKeyword(statement, "comedy") >= 0 || findKeyword(statement, "laugh") >= 0)
		{
			response = comedyMovies(statement);
			prefcom++;
		}
		
		else if (findKeyword(statement, "animated") >= 0 || findKeyword(statement, "animation") >= 0 || findKeyword(statement, "motion picture") >= 0)
		{
			response = animationMovies(statement);
			prefani++;
			emotion++;
		}
		
		else if(findKeyword(statement, "action") >= 0 || findKeyword(statement, "thriller") >= 0)
		{
			response = actionMovies(statement);
			prefact++;
		}
		else
		{
			response = getmoreRandomResponse();
		}
		
		return response;
	}
	// transform methods takes something the user inputs and uses a part of that  input in the response.
	// parameter statement is the input of the user.
	private String transformILikestatements(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "like");
		String restofStatement = statement.substring(psn + 5);
		return "If you like " + restofStatement + ", I may have the movie just for you, just tell me what genre of movie you want one more time to confirm your request!";
	}
	
	private String transformaboutmoviesStatements(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "about");
		String restofStatement = statement.substring(psn + 5);
		return "If you would like to know more about " + restofStatement + " check out the IMDB, Rotton Tomatoes, or wikipedia webpage for it, it is much more indepth.";	
	}
	
	private String summary(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		if (findKeyword(statement, "Happy Death Day") >= 0)
		{
			return "Happy Death Day is about a girl who dies and comes back to life just to relive her death again until she solves who kills her.";
		}
		if (findKeyword(statement, "Anabelle: Creations") >= 0)
		{
			return "The origin story of Anabelle, if you have not watched anabelle it is highly recommended to watch that one first, well.. if you like evil dolls..";
		}
		if (findKeyword(statement, "It") >= 0)
		{
			return "If you do not know about It, you don't deserve to watch it.";
		}
		if (findKeyword(statement, "Get Out") >= 0)
		{
			return "This is a horror movie about race, the protagonists gets a girlfriend, but the girlfriends family is a bit...odd...";
		}
		if (findKeyword(statement, "Bad Moms") >= 0)
		{
			return "It is about a mother who is tired of being the perfect mother, and it worked out for her.";
		}
		if (findKeyword(statement, "Deadpool") >= 0)
		{
			return "Deadpool is only the best anti-hero in the Marvel universe, and he possesses regenerative abilities and unmatched sword and gun skills.";
		}
		if (findKeyword(statement, "Totoro") >= 0)
		{
			return "Totoro is a very calm movie about a mystical beast that lives in a big tree and its interaction with a young girl who was curious about the beast.";
		}
		if (findKeyword(statement, "Spirited Away") >= 0)
		{
			return "Spirited Away is a fantasy movie set in a world of demons where the sins of gluttony and greed are explored in a very meaningful way.";
		}
		if (findKeyword(statement, "Beauty and the Beast") >= 0)
		{
			return "One of the best movies by Disney, Beauty and the Beast is a childish movie about love," + 
					" but it is still beautiful, there is an animated version and a real life version.";
		}
		if (findKeyword(statement, "Man of Steel") >= 0)
		{
			return "Superman the most Iconic superhero in the entire world, combined with its amazing computer effects,"
					+ " this is a story on the rivalry of him an Zod.";
		}
		if (findKeyword(statement, "Ratatouille") >= 0)
		{
			return "Ratatouille is about a rat that can cook... yea..";
		}
		if (findKeyword(statement, "The Lion King") >= 0)
		{
			return "A classic movie about being King and the vengeance for his father who passed away.";
		}
		if (findKeyword(statement, "Jurassic Park") >= 0)
		{
			return "Considered one of the best fiction movies that was ever produced, what would happen if we made a Zoo, with dinosaurs?";
		}
		if (findKeyword(statement, "The Dark Knight") >= 0)
		{
			return "If you know superman, You know Batman, The Dark Knight is story of Batman.";
		}
		else
		{
			return "If you to know my general summary," 
					+ "please ask for what the movie is about using the complete name of the movie you are asking for.";
		}
	}
	
	// The following methods below each correspond to a different list of movies based on the keywords found in the input.
	// its like transform methods but it does not use the users input(as in a substring of their statement) in the output.
	// the ageCatagorizing method contains the different movie rating lists *progress still incomplete here, missing R rated movies.
	// each list method used refers to a randomized string group *At the very bottom of the whole code.
	private String ageCatagorizing(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		String result = "";
		if (findKeyword(statement, "G") >= 0)
		{
			result = "If you want a G rated movie, " + GList() + "is a great choice for kids";
		}
		else if ((findKeyword(statement, "PG - 13") >= 0) || (findKeyword(statement, "PG-13") >= 0))
		{
			result = "A great pg-13 movie is " + pg13List();
		}
		return result;
	}
	private String horrorMovies(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return "If that is what you like, you should try watching " + horrorList() + ", really gets your heart racing!";
		
	}
	
	private String comedyMovies(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return comedyList() + " is a really funny one, don't eat anything while watching, you won't be able to breath!";
	}
	
	private String animationMovies(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return "If you want something animated one really deep and emotional one is " + animatedList();
	}
	
	private String actionMovies(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		return "Trust me, after watching " + actionList() + " you'd want to try out some crazy moves for yourself.";
	}
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
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
	//Shortened version for position starting at 0.
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomSadResponses [r.nextInt(randomSadResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String getmoreRandomResponse()
	{
		Random r = new Random();
		if (prefhor > prefcom && prefhor > prefani && prefhor > prefact)
		{
			return randomprefhorResponses [r.nextInt(randomprefhorResponses.length)];
		}
		else if (prefcom > prefhor && prefcom > prefani && prefcom > prefact)
		{
			return randomprefcomResponses [r.nextInt(randomprefcomResponses.length)];
		}
		else if (prefani > prefhor && prefani > prefcom && prefani > prefact)
		{
			return randomprefaniResponses [r.nextInt(randomprefaniResponses.length)];
		}
		else if (prefact > prefani && prefact > prefhor && prefact > prefcom)
		{
			return randomprefactResponses [r.nextInt(randomprefactResponses.length)];
		}
		return randomnoprefResponses [r.nextInt(randomnoprefResponses.length)];
	}
	// These are the list methods that refers to a group of strings with one chosen randomly.
	// *INCOMPLETE* NOT ALL OF THEM CONTAINS A SUMMARY AND DATE OF RELEASE ONLY A SELECT FEW.
	// *INCOMPLETE* SOME OF THEM HAVE THE WORD "NEW" IN THEM, THIS WAS ORIGINALLY SUPPOSED TO BE ORGANIZED BY DATES AS WELL, BUT ISN'T
	private String horrorList()
	{
		Random r = new Random();
		return randomNewScaryMovies [r.nextInt(randomNewScaryMovies.length)];
	}
	
	private String comedyList()
	{
		Random r = new Random();
		return randomNewComedyMovies [r.nextInt(randomNewComedyMovies.length)];
	}
	
	private String animatedList()
	{
		Random r = new Random();
		return randomAnimatedMovies [r.nextInt(randomAnimatedMovies.length)];
	}
	
	private String actionList()
	{
		Random r = new Random();
		return randomNewActionMovies [r.nextInt(randomNewActionMovies.length)];
	}
	private String GList()
	{
		Random r = new Random();
		return randomGratedmovies [r.nextInt(randomGratedmovies.length)];
	}
	
	private String pg13List()
	{
		Random r = new Random();
		return randomPG13ratedmovies [r.nextInt(randomPG13ratedmovies.length)];
	}
	
	// These are the groups of strings used in other methods to call them randomly
	private String [] randomNeutralResponses = 	
	{
		"mmmmmmmm, I'm not sure what to suggest you, if you don't give me preferences..",
		"you can always watch the old Star Wars movies again they never get old!",
		"If you really want to you can watch the Titanic, its really not a bad story, just old.",
		"Whatever you do, do not watch Twilight, just don't.."
	};
	private String [] randomSadResponses = 
	{
		"Never feel sad or tired, watch a comedy movie to cheer yourself up, if that's what it takes!", 
		"I don't recommend watching animations as they can get emotional real quick..", 
		"If you are angry watch some action movies and pretend you are the " 
		+ "\nprotagonist blowing things up... that may be somewhat therapeutic..I think"
	};
	private String [] randomHappyResponses = 
	{
		"If you are feeling so Happy, why not watch an action Movie to make you even happier or comedy?",
		"You can watch emotional movies as well if you want to get yourself really touchy..",
		"I heard Tucker and Dale versus Evil is a good mix between comedy and horror you should try it out!"
	};
	private String [] randomprefhorResponses =
	{
		"You like horror as much as I do!!",
		"One great classic is The Ring, its about a movie where if you watch it you de in seven days! There have been many remakes of it but I think I prefer the original one, You should try it out.",
		"You chose the correct genre!!",
		"Gore! Blood! Death! JUMPSCARES! Good to get your heart racing you know....",
		"Not part of the topic but you should read some books by Steven King if you like gothic literature."
	};
	private String [] randomprefcomResponses =
	{
		"I like funny stuff too but I like comedy TV shows more than movies, their kind of boring in my opinion.",
		"If I were you I wouldn't watch comedy movies.",
		"I'm not trying to judge you but you should really watch TV comedy shows instead of movies.",
		"You seem like a happy guy to enjoy comedy."
	};
	private String [] randomprefaniResponses =
	{
		"I love animated movies as well!!",
		"You should really check out Ghibli Studios animations, they are so deep and so touching!",
		"Disney has alrgiht animations once in awhile.",
		"Dreamworks or Disney? I say niether.",
		"It's good to feel like a child sometimes...",
		"If you love art you love animations period"
	};
	private String [] randomprefactResponses =
	{
		"wow, I sure wish I could do what those people are doing in the movies....",
		"If you really like action and fiction Marvel and DC is definitely the way to go!!!! (pst.. Marvel is better...)",
		"Jackie Chen is pretty good, he does all his stunts, that's some strong will.",
		"If you want to watch something old but amazing, The Terminator and the Matrix something you don't want to miss out on."
	};
	private String [] randomnoprefResponses =
	{
		"You seem like you like a wide variety of movies.",
		"It's good to enjoy everything..I think..",
		"I don't know about you but my favorite movies are animated movies and horror movies it's different.",
		"I don't know what to say.. can you choose a genre already?",
		"I may not have data of all the movies, but the ones recommended are guaranteed to be amazing!!"
	};
	private String [] randomNewScaryMovies = 
	{
		"Happy Death Day", 
		"Anabelle: Creations", 
		"IT", 
		"Jigsaw", 
		"Get Out", 
		"Alience Covenant"
	};
	private String [] randomNewComedyMovies = 
	{
		"Baywatch", 
		"Ghostbusters", 
		"Bad Moms", 
		"Deadpool", 
		"The Big Sick"
	};
	private String [] randomAnimatedMovies = 
	{
		"Snow White and the Seven Dwarfs", 
		"Up", 
		"Totoro", 
		"Spirited Away", 
		"The Secret World of Arriety",
		"When Marnie Was There", 
		"The Tale of Princess Kaguya", 
		"Bambi", 
		"Princess Mononoke",
		"Beauty and the Beast"
	};
	private String [] randomNewActionMovies =
	{
		"The Foreigner",
		"Iron Man",
		"Man of Steel",
		"White House Down",
		"Thor",
		"Spider-Man Homecoming",
		"The Terminator",
		"Mad Max: Fury Road"
	};
	private String [] randomGratedmovies = 
	{
		"Zootopia", "Inside Out", 
		"Finding Nemo", 
		"Toy Story", 
		"The Lion King", 
		"Aladdin", 
		"WALL-E", 
		"Mulan", 
		"Ratatouille", 
		"Cars", 
		"The Polar Express"
	};
	private String [] randomPG13ratedmovies = 
	{
		"The Dark Knight", 
		"The Avengers", 
		"Avatar", 
		"The Hunger Games", 
		"Jurassic Park", 
		"Iron Man"
	};
}
