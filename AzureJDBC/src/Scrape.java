import java.util.Scanner;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Text;

public class Scrape {
	
	Scrape(){}
	
    protected void getPage() {

    	try {
	        // declaration and instantiation of objects/variables
	        WebDriver driver = new FirefoxDriver();
	        //Robot robot = new Robot();
	        String baseUrl = "https://pbanssb.ucmerced.edu/pls/PROD/xhwschedule.p_selectsubject";
	 
	        // launch Firefox and direct it to the Base URL
	        driver.get(baseUrl);
	 
	        // input value (semester) will be given by the user
	        driver.findElement(By.cssSelector("input[value='201630']")).click();
	        driver.findElement(By.name("validterm")).submit();
	        
	        String htmlsource = driver.getPageSource(); 
	        try(  PrintWriter out = new PrintWriter( "testsource3.html" )  ){
	            out.println( htmlsource );
	        }
	        
	        driver.close();
    	}
    	catch(Exception e) {
    		
    	}
	}
    
    protected void parsePage() {

    	try {
    		File input = new File("testsource3.html");
    		Document doc = Jsoup.parse(input, "UTF-8", "");
    		
    		String output = "";
    		int key = 0; //unique key restraint
    		for( Element table : doc.select("table") ) {
	    		for( Element row : table.select("tr[bgcolor=#DDDDDD], tr[bgcolor=#FFFFFF]") ) {
	    			Elements cols = row.select("small");

    				if (cols.get(0).text().equals("LECT") || cols.get(0).text().equals("EXAM")){// || cols.get(0).text().equals("LAB")) { // to check "&nbsp:" 
    					continue;
    				}
    				else {
		    			for(int i = 0; i < cols.size(); i++) {
		    				if (cols.get(0).text().equals("INI")) { // to check "&nbsp:" 
		  		    			output = output + "INI" + Integer.toString(key) + ";" + "BLAH;" + "BLAH;" + "BLAH;" +"BLAH;" +"BLAH;" + "BLAH;" + "BLAH;" + "BLAH;";
		    					if(i == cols.size() - 1)
		    						output = output + "\n";
		    					key = key + 1;
		    				}
		    				else
		    					if(i == cols.size() - 1)
		    						output = output + cols.get(i).text() + "\n";
		    					else
		    						output = output + cols.get(i).text() + ";";
		    			}
    				}
	    		}
    		}
    		
    		try( PrintWriter out = new PrintWriter( "output.txt" )  ) {
    			out.println( output );
    		}
    		
    	}
    	catch(Exception e) {
    		
    	}
    }
	
    
    
}
