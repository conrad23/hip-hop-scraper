/* 
 * HIP HOP SCRAPER
 * 
 * FEATURES THAT NEED TO BE ADDED:
 * add methods/expand from main method
 * implement GUI
 * auto-run daily, keeping track of releases
 * 
 */


package package1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scraper {

	public static void main(String[] args) throws IOException {

		//arraylists to hold artists and tracks
		ArrayList<String> artistList = new ArrayList<String>();
		ArrayList<String> trackList = new ArrayList<String>();

		//getting connection to website and loading source code
		String url = "http://www.hiphopearly.com/tracks";
		Document doc = Jsoup.connect(url).header("User-Agent", "curl").get();
		Elements tracks = doc.select("div.track-listing div.track");

		//loops through elements and adds artists/tracks to list
		for (Element track : tracks) {
			artistList.add(track.select("span.artist").text());
			trackList.add(track.select("span.title").text());
		}

		//FAVORITE ARTIST SECTION
		System.out.println("FAVORITE ARTISTS - NEW RELEASES");
		ArrayList<String> faveArtistList = new ArrayList<String>();
		try {
			FileReader inFile1 = new FileReader("favoriteArtists.txt");
			BufferedReader bufReader = new BufferedReader(inFile1);
			String listOfFaveArtists;
			while ((listOfFaveArtists = bufReader.readLine()) != null) {
				String[] tokens = listOfFaveArtists.split("/");
				for (int i = 0; i < tokens.length; i++) {
					faveArtistList.add(tokens[i]);
				}
			}
			bufReader.close();
		} catch (IOException e) {
			System.out.println("Error retrieving fave artist file.");
		}
		for (int i = 0; i < artistList.size(); i++) 
			for (int j = 0; j < faveArtistList.size(); j++) {
				if (artistList.get(i).toLowerCase().contains(faveArtistList.get(j))) {
					System.out.println(artistList.get(i));
					System.out.println(trackList.get(i));
					System.out.println();
				}
			}

		System.out.println("-----------------------------------------");

		//SIMILAR ARTIST SECTION
		System.out.println("SIMILAR ARTISTS - NEW RELEASES");
		ArrayList<String> simArtistList = new ArrayList<String>();
		try {
			FileReader inFile2 = new FileReader("similarArtists.txt");
			BufferedReader bufReader = new BufferedReader(inFile2);
			String listOfSimArtists;
			while ((listOfSimArtists = bufReader.readLine()) != null) {
				String[] tokens = listOfSimArtists.split("/");
				for (int i = 0; i < tokens.length; i++) {
					simArtistList.add(tokens[i]);
				}
			}
			bufReader.close();
		} catch(IOException e) {
			System.out.println("Error retrieving similar artist file.");
		}
		for (int i = 0; i < artistList.size(); i++) 
			for (int j= 0; j < simArtistList.size(); j++) {
				if (artistList.get(i).toLowerCase().contains(simArtistList.get(j))) {
					System.out.println(artistList.get(i));
					System.out.println(trackList.get(i));
					System.out.println();
				}
			}
	}
}
