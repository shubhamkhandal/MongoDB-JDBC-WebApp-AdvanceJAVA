

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


/**
 * Servlet implementation class example
 */
@WebServlet("/example")
public class example extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public example() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in this services");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String fullName=request.getParameter("fullname");
		System.out.println(fullName);
		String Standard=request.getParameter("std");
		String contactno=request.getParameter("contactno");
		
		
		
		try{			
			//create a mongo client
		    MongoClient mongo=new MongoClient("localhost",27017);
		    //creating Credentials
		    MongoCredential credential=MongoCredential.createCredential("","example","".toCharArray());
		    System.out.println("Connected to the database succesfully");
		    //accessing the Database
		    MongoDatabase db = mongo.getDatabase("example");
		    System.out.println("credentials ::"+credential);
		    
		    //create a collection
		    //db.createCollection("studentInfo");
		    //System.out.println("collection created succesfully");
		    
		    //Retrieving a collection(for selecting collection from database)
		    MongoCollection <Document> collection = db.getCollection("studentinfo");
		    System.out.println("succesfully select a collection from database");
		    
		    Document document = new Document("FullName",fullName).append("Standard", Standard).append("Contact NO", contactno);
		    collection.insertOne(document);
		    System.out.println("Document insert successfully");
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
