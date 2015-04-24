package classes.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.CharacterData;




import classes.dao.authorDAO;
import classes.dao.bookDAO;
import classes.users.*;

public class client {
	public static String GET_BOOK_BY_ID = "https://www.goodreads.com/book/show/BOOK_ID?format=xml&key=X1Gd1Zw1WpqsH8U5WBBdA";
    public static String GET_BOOK_BY_A_B = "https://www.goodreads.com/search/index.xml?key=X1Gd1Zw1WpqsH8U5WBBdA&q=STRING";
    public String GET_AUTH_DETAILS = "https://www.goodreads.com/author/show.xml?id=AUTH_ID&key=X1Gd1Zw1WpqsH8U5WBBdA";

    
    public static String GET_ALL_BOOKS_BY_AUTHOR = "https://www.goodreads.com/author/list/AUTH_GD_ID?format=xml&key=X1Gd1Zw1WpqsH8U5WBBdA";
        
        public List<book> getAllBooksByAuthor(int goodReads_id){
        	String url = GET_ALL_BOOKS_BY_AUTHOR.replace("AUTH_GD_ID", Integer.toString(goodReads_id));
    		String xml = getXMLFromURLString(url);
        	authorDAO ad = new authorDAO();
        	author a = ad.getAuthorByGoodreadsId(goodReads_id);
    		Document xmlDoc = getDocument(xml);
        	List<book> books = getBooks(xmlDoc, a);
        	a.getBooks().addAll(books);
        	ad.updateAuthor(a);   	
        	return books;
        }
        
        public List<book> getBooks(Document doc, author a){
        	NodeList nodes = doc.getElementsByTagName("author");
    		Element element = (Element) nodes.item(0);
    		NodeList books = element.getElementsByTagName("books");
    		Element element1 = (Element) books.item(0);
    		
    		//here we will get list of all books
    		NodeList list_books = element1.getElementsByTagName("book");
    		ArrayList<book> bks = new ArrayList<book>();
    		for(int i=0; i < list_books.getLength(); i++)
    		{
    			Element single_book = (Element) list_books.item(i);
    			String name = getTagFromElement(single_book, "title");
    			int book_isbn = Integer.parseInt(getTagFromElement(single_book, "id"));
    			String p_year = getTagFromElement(single_book, "publication_year");
    			int publish_year;
    			if(!p_year.equals(""))
    				publish_year = Integer.parseInt(p_year);
    			else
    				publish_year = 0;
    			String description = getTagFromElement(single_book,"description");
    			description = description.replace("<p>", "");
    			description = description.replace("</p>", "");
    			String poster = getTagFromElement(single_book,"image_url");
    			String publisher = getTagFromElement(single_book,"publisher");
    			book b = new book(0, 0 , publish_year, poster, description, publisher, book_isbn,0,0,0, name );
    					
    					
    			b.setAuth(a);
    			bks.add(b);		
    		}  	
    		return bks;
        }
        
        public String getTagFromElement(Element e, String tag){
        	NodeList value = e.getElementsByTagName(tag);
        	Element value1 = (Element) value.item(0);
        	return getCharacterDataFromElement(value1);	
        }
        
    
    
    
    
    
    
    
    
    
    
    
    
	public author getAuthorDetails(int id){
		String url = GET_AUTH_DETAILS.replace("AUTH_ID",Integer.toString(id));
		String xml = getXMLFromURLString(url);
		Document xmlDoc = getDocument(xml);
		
		String gender = getData(xmlDoc, "author", "gender");
		String aurl = getData(xmlDoc, "author", "image_url");
		String hometown = getData(xmlDoc, "author", "hometown");
		int work_count = Integer.parseInt(getData(xmlDoc, "author", "works_count"));
		int followers  = Integer.parseInt(getData(xmlDoc, "author", "author_followers_count"));
		author a = new author(0, "", hometown, gender, work_count, followers, aurl, id);
		return a;		
	}
    
    public List<author> findBookByAB(String name) throws UnsupportedEncodingException {

		String encodedUrl = URLEncoder.encode(name, "UTF-8");
		String url = GET_BOOK_BY_A_B.replace("STRING", encodedUrl);

		String xml = getXMLFromURLString(url);
		Document xmlDoc = getDocument(xml);
		int count = Integer.parseInt(getData(xmlDoc, "search", "results-end"));
		System.out.println(count);
		return getData(xmlDoc, count, "results");

	}
	
	public author findBookById(int id)
	{
		String book_url = GET_BOOK_BY_ID.replace("BOOK_ID", Integer.toString(id));
		String xml = getXMLFromURLString(book_url);
		Document xmlDoc = getDocument(xml);
		
		//Fetching book Data
		String name = getData(xmlDoc, "book", "title");
		int book_isbn = Integer.parseInt(getData(xmlDoc, "book", "id"));
		String p_year =getData(xmlDoc, "work", "original_publication_year");
		int publish_year;
		if(!p_year.equals(""))
			publish_year = Integer.parseInt(p_year);
		else
			publish_year = 0;
		String description = getData(xmlDoc, "book","description");
		description = description.replace("<p>", "");
		description = description.replace("</p>", "");
		String poster = getData(xmlDoc, "book","image_url");
		String publisher = getData(xmlDoc, "book","publisher");
		
		//Fetching author data
		int author_id = Integer.parseInt(getData(xmlDoc, "authors", "author", "id"));
		String auth_url = getData(xmlDoc, "authors", "author", "image_url");
		String auth_name = getData(xmlDoc, "authors", "author", "name");
				
		author a = new author(0, auth_name, "", "", 0, 0, auth_url, author_id);
		book b = new book(0, 0 , publish_year, poster, description, publisher, book_isbn,0,0,0, name );
		
		
		//author a3 = a;
//		b.setAuth(a);
		//a3.setBooks(new ArrayList<book>());
		//a3.getBooks().add(b);
			
		authorDAO ad = new authorDAO();
		bookDAO bd = new bookDAO();
		ad.createAuthor(a);
		author a2 = ad.getAuthorByGoodreadsId(author_id);
		bd.createBook(a2, b);
		return a2;
	}

	private String getXMLFromURLString(String book_url) {
		try {
			URL url = new URL(book_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream in = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader r = new BufferedReader(isr);
			String out;
			StringBuffer xml = new StringBuffer();
			while((out = r.readLine()) != null) {
				xml.append(out);
			}
			return xml.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Document getDocument(String xml) {
		DocumentBuilder db = null;

		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));

		Document doc = null;
		try {
			doc = db.parse(is);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}
	
	public List<author> getData(Document doc, int count, String tag){
		
		ArrayList<author> au = new ArrayList<author>();
		NodeList nodes = doc.getElementsByTagName(tag);
		Element element = (Element) nodes.item(0);
		for(int i =0; i <count; i++){
			NodeList work = element.getElementsByTagName("work");
			Element work_element = (Element) work.item(i);
			NodeList book = work_element.getElementsByTagName("best_book");
			Element book_element = (Element) book.item(0);
			NodeList id = book_element.getElementsByTagName("id");
			Element id_element = (Element) id.item(0);
			int book_id = Integer.parseInt(getCharacterDataFromElement(id_element));
			author a = findBookById(book_id);
			au.add(a);		
		}
		
		return au;
		
	}
	public String getData(Document doc, String initial, String tag){
		NodeList nodes = doc.getElementsByTagName(initial);
		Element element = (Element) nodes.item(0);
		NodeList name = element.getElementsByTagName(tag);
		Element line = (Element) name.item(0);
		return getCharacterDataFromElement(line);
		
	}
	
	public String getData(Document doc, String initial, String tag, String nested){
		NodeList nodes = doc.getElementsByTagName(initial);
		Element element = (Element) nodes.item(0);
		NodeList name = element.getElementsByTagName(tag);
		Element line = (Element) name.item(0);
		NodeList nest = line.getElementsByTagName(nested);
		Element nestd = (Element) nest.item(0);
		return getCharacterDataFromElement(nestd);		
	}

	public String getData(Document doc, String initial, String tag, String nested, String tag1){
		NodeList nodes = doc.getElementsByTagName(initial);
		Element element = (Element) nodes.item(0);
		NodeList name = element.getElementsByTagName(tag);
		Element line = (Element) name.item(0);
		NodeList nest = line.getElementsByTagName(nested);
		Element nestd = (Element) nest.item(0);
		NodeList nest1 = nestd.getElementsByTagName(tag1);
		Element nestd1 = (Element) nest1.item(0);
		return getCharacterDataFromElement(nestd1);		
	}
	
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
	  
	/*public static void main(String args[]) throws IOException {
		
		client api = new client();
		api.findBookByAB("qbc");
		//api.findBookByAB("Ender's Game");
		//api.getAuthorDetails(18541);
		
	}*/
}