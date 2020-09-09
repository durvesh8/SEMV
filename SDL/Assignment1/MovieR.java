import java.util.*;
public class MovieR {
	public static Genre horror = new Genre("Horror");
	public static Genre comedy = new Genre("Comedy");
	public static Genre crime = new Genre("Crime");
	public static Genre adventure = new Genre("Adventure");
	public static Genre drama = new Genre("Drama");
	public static void main(String[] args) {
		List<Genre> genres = new LinkedList<Genre>();
		genres.add(horror); genres.add(comedy); genres.add(crime); genres.add(adventure); genres.add(drama);
		dummyClass dc = new dummyClass();
		dc.addDummyData();
		Scanner input = new Scanner(System.in);
		while(true){
			int genre_no;
			System.out.println("\n-------------------------------------------------------\n\t\tWELCOME\n-------------------------------------------------------");
			System.out.println("Select Genre from the following:-\n1.Horror\t2.Comedy\t3.Crime\t\t4.Adventure\t5.Drama\t\t6.Exit the program");
			genre_no = input.nextInt();
			if(genre_no==6){
				input.close();
				break;
			}
			System.out.println("Genre Chosen: "+genres.get(genre_no-1).getName());
			Genre chosen_genre = genres.get(genre_no-1);
			int choice;
			System.out.println("1.Recommend\t2.List movies\t3.Add to list\t4.Remove from list");
			choice = input.nextInt();
			int listsize = chosen_genre.movielist.size();
			System.out.println("");
			switch (choice) {
				case 1:
				Random rand = new Random();
				int index = rand.nextInt(listsize);
				chosen_genre.recommendMovie(index);
				break;
				case 2:
				chosen_genre.printMovies();
				break;
				case 3:
				System.out.println("Enter Movie Name: ");
				Scanner s = new Scanner(System.in);
				String name = s.nextLine();
				System.out.print("Enter Rating: ");
				double rating = input.nextDouble();
				chosen_genre.addMovie(name, rating);
				System.out.println("Movie Added");
				break;
				case 4:
				System.out.println("The movies are as following:-");
				chosen_genre.printMovies();
				System.out.print("Enter the index of the movie to be removed: ");
				int movieindex = input.nextInt();
				if(movieindex>listsize || movieindex< 0){
					System.out.println("Enter valid index");
					// continue;
				}
				chosen_genre.removeMovie(movieindex-1);
				break;
				default:
				System.out.println("Enter valid Input");
				break;
			}
		}
		input.close();
	}
};


class Genre{
	String name;
	List<Movie> movielist = new Vector<Movie>();
	Genre(){}
	Genre(String Name){
		name = Name;
	}
	public String getName(){
		return name;
	}
	
	public String addMovie(String name, double rating){
		movielist.add(new Movie(name,rating));
		return movielist.get(movielist.size()-1).toString();
		
	}
	public void removeMovie(int index){
		System.out.println("The movie to be removed is: "+movielist.get(index).toString());
		movielist.remove((index));
		System.out.println("Movie removed\n");
	}
	public void recommendMovie(int index){
		System.out.println("Recommended Movie: \n"+ movielist.get(index).getMovieName()+"\n");
	}
	public void printMovies(){
		for(int i = 0; i < movielist.size(); i++){
			System.out.println((i+1)+". " +(movielist.get(i).toString()));
		}
		System.out.println("");
	}

};


class Movie{
	String name;
	double rating;
	Movie(String Name, double Rating){
		name = Name;
		rating = Rating;
	}
	public String getMovieName(){return name;}
	public double getRating(){return rating;}
	public String toString() {
		return (name + " : " + rating);
	}

};


class dummyClass{
	public void addDummyData(){
		MovieR.horror.addMovie("A quiet place",7.5);
		MovieR.horror.addMovie("Get out",7.7);
		MovieR.horror.addMovie("Alien",8.4);
		MovieR.horror.addMovie("World War Z",7);
		MovieR.horror.addMovie("The Thing",8.1);
		
		MovieR.comedy.addMovie("Step Brothers",6.9);
		MovieR.comedy.addMovie("White Chicks",5.6);
		MovieR.comedy.addMovie("The Hot chick",5.5);
		MovieR.comedy.addMovie("The Hangover",7.7);
		MovieR.comedy.addMovie("Horrible Bosses",6.9);

		MovieR.crime.addMovie("Project Power",6.0);
		MovieR.crime.addMovie("The Tax Collector",4.7);
		MovieR.crime.addMovie("The Devil All the time",6.9);
		MovieR.crime.addMovie("Knives Out",7.9);
		MovieR.crime.addMovie("Joker",8.5);

		MovieR.adventure.addMovie("Mulan",6.9);
		MovieR.adventure.addMovie("The Old Guard",6.7);
		MovieR.adventure.addMovie("Dune",2020);
		MovieR.adventure.addMovie("Jurassic Park",8.1);
		MovieR.adventure.addMovie("Wonder Woman 1984",6.9);

		MovieR.drama.addMovie("Titanic",7.8);
		MovieR.drama.addMovie("Tristan & Isolde",6.8);
		MovieR.drama.addMovie("Dangerous Beauty",7.2);
		MovieR.drama.addMovie("Head in the clouds",6.6);
		MovieR.drama.addMovie("Captain Corelli's Mandolin",5.9);


	}
}

