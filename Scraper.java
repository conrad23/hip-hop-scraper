/**********************************************************************
Has the ability to retrieve artist and track title content from
the hiphopearly.com website.

@author Conner Toney
@version 1.1
 *********************************************************************/

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
	
	/** contains list of released artists/tracks */
	private static String released = "";
	
	/******************************************************************
	Retrieves the content from the website, and compares that content
	with the user-specified content in order to determine if any
	matches exist.
	 *****************************************************************/
	public static void getContent() throws IOException {
		
		//arraylists used to hold artist/track information
		ArrayList<String> artistList = new ArrayList<String>();
		ArrayList<String> trackList = new ArrayList<String>();
		ArrayList<String> faveArtistList = new ArrayList<String>();

		//connects to the website, and retrieves the section
		//containing relevant information
		String url = "http://www.hiphopearly.com/tracks";
		Document doc = Jsoup.connect(url).header("User-Agent", "curl").get();
		Elements tracks = doc.select("div.track-listing div.track");

		//iterates through the information, grabbing the info
		//containing artists + tracks
		for (Element track : tracks) {
			artistList.add(track.select("span.artist").text());
			trackList.add(track.select("span.title").text());
		}

		//reads in the user-given artists
		try {
			FileReader inFile = new FileReader("favoriteArtists.txt");
			BufferedReader bufReader = new BufferedReader(inFile);
			String listOfFaveArtists;
			while ((listOfFaveArtists = bufReader.readLine()) != null) {
				String[] tokens = listOfFaveArtists.split("/");
				for (int i = 0; i < tokens.length; i++) {
					faveArtistList.add(tokens[i]);
				}
			}
			bufReader.close();
		} catch (IOException e) {
			released = "Error reading user's file.";
		}
		
		//if the scraped artist matches the user-given artist, 
		//the artist is added to the 'released' string
		for (int i = 0; i < artistList.size(); i++) 
			for (int j = 0; j < faveArtistList.size(); j++) {
				if (artistList.get(i).toLowerCase().contains(faveArtistList.get(j))) {
					released += (artistList.get(i) + "- \n" + trackList.get(i) + "\n" + "\n");
				}
			}
	}
	
	/******************************************************************
	Displays the list of artist + tracks in String format.
	@return artist and track title content
	 *****************************************************************/
	public String toString() {
		return released;
	}
}
