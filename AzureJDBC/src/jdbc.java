import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class jdbc {
	
		static Connection conn = null;
		static Statement stat = null;
		static String sql = null;
		static ResultSet rs;	
		
		static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		static final String DB_URL = "jdbc:sqlserver://testserver1212.database.windows.net:1433;"
				+ "database=Test;"
				+ "user=dgist@testserver1212;"
				+ "password=samurai#2016;"
				+ "encrypt=true;"
				+ "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		static final String USERNAME = "root";
		static final String PASSWORD = "samurai#2016";
		
	public static void main(String[] args){
		
		Scrape scrape = new Scrape();
		
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("Database is connected");
			stat = conn.createStatement();
			if (!conn.isClosed()){
				System.out.println("connection open");
			}
			
			//scrape.getPage();
			//scrape.parsePage();

			//CreateTables();
			//InsertIntoCourses();
			//UpdateCourses();

			//sql = "DELETE FROM Courses WHERE crn = 33985";
			
			/*  sql = "SELECT id,number,crn FROM coursess;";
			  rs = stat.executeQuery(sql);
			  for(int i=0; i < 10; i++){
				  rs.next();
				  System.out.println(rs.getString(1)+" :: "+rs.getString(2)+" :: "+rs.getString(3));
			  }*/
			//sql = "UPDATE Courses SET seatsAvailable = 10 WHERE seatsAvailable = 5";
			//sql = "DROP TABLE courses;";
			//stat.executeUpdate(sql);
			Scanner in  = new Scanner(System.in);
			System.out.println("Input CRN in correct format:");
			String st = in.next();
			while(!st.equals("-1")){
				
			sql = "SELECT * FROM courses WHERE crn = '"+st+"';";
			rs = stat.executeQuery(sql);
				while(rs.next()){//						NO INDEX 0 in Result Set.
					System.out.println("Course number: " + rs.getString(1) + " " + rs.getString(2));
				}
				
			System.out.println("\nInput CRN in correct format.(-1 to Stop.)");	
			st = in.next();
			}
			
			conn.close();
			
			if (conn.isClosed()){
				System.out.println("connection closed");
			}
			
		}catch(SQLException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println("some other problem");
		}
			
	}
	
	public static void CreateTables(){
		
		try{
			stat = conn.createStatement();
			
			DatabaseMetaData dm = conn.getMetaData();//change beck to courses
			ResultSet rs = dm.getTables(null, null, "courses", null);
			if(!rs.next()){//"int", and no size
				System.out.println("Creating courses table...");
				sql = "CREATE TABLE courses ("
						+ "crn int PRIMARY KEY NOT NULL,"
						+ " number varchar (100) NOT NULL,"
						+ " title varchar (500) NOT NULL,"
						+ " units int NOT NULL,"
						+ " activity varchar (60) NOT NULL,"
						+ " days varchar (20) NOT NULL,"
						+ " time varchar (20) NOT NULL,"
						+ " room varchar (20) NOT NULL,"
						+ " length varchar (20) NOT NULL,"
						+ " instructor varchar (40) NOT NULL,"
						+ " maxEnrl int NOT NULL,"
						+ " seatsAvailable int NOT NULL,"
						+ " activeEnrl int NOT NULL,"
						+ " sem_id int  NOT NULL);";
				
				System.out.println("Executing query: " + sql);
				stat.executeUpdate(sql);
			}else{
				System.out.println("Courses table already exists");
			}

			dm = conn.getMetaData();//CANT CALL THE TABLE USER
			rs = dm.getTables(null, null, "users", null);
			if(!rs.next()){
				System.out.println("Creating user table...");
				sql = "CREATE TABLE users (username varchar(15) NOT NULL,"
						+ " email varchar(30) PRIMARY KEY,"
						+ " password varchar(200) NOT NULL,"
						+ " school varchar(20) NOT NULL);";
				System.out.println("Executing query: "+sql);
				stat.executeUpdate(sql);
			}else{
				System.out.println("Users table already exists");
			}			
			
			dm = conn.getMetaData();
			rs = dm.getTables(null, null, "userCourses", null);
			if(!rs.next()){
				System.out.println("Creating usercourses table...");
				sql = "CREATE TABLE userCourses (department varchar(10) NOT NULL,"
						+ " crn int PRIMARY KEY,"
						+ " username varchar(20) NOT NULL);";
				System.out.println("Executing query: "+sql);
				stat.executeUpdate(sql);
			}else{
				System.out.println("userCourses table already exists");
			}
			
			dm = conn.getMetaData();
			rs = dm.getTables(null, null, "semester", null);
			if(!rs.next()){
				System.out.println("Creating semester table...");
				sql = "CREATE TABLE semester (sem_id int NOT NULL,"
						+ " sem_name varchar(10) NOT NULL);";
				System.out.println("Executing query: "+sql);
				stat.executeUpdate(sql);
			}else{
				System.out.print("Semester table already exists\n");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void InsertIntoCourses(){
		int COMB_CRN = 0;
		String pad = String.format("%%0%dd", 2);
		String result = String.format(pad, COMB_CRN);//00 padding
		String CRN="",
				NUMBER="",
				TITLE="",
				UNITS="0",
				ACTIVITY="",
				DAYS="",
				TIME="",
				ROOM="",
				LENGTH="",
				INSTRUCTOR="",
				MAXENRL="",
				SEATAVL="",
				ACTENRL="",
				SEM_ID="";
		
		try{
			stat = conn.createStatement();
			File file = new File("output.txt");
			Scanner input = new Scanner(file);
			
			while(input.hasNextLine()){
				String line = input.nextLine();
				
				Scanner lines = new Scanner(line);
				
				if(lines.useDelimiter(";").hasNext()){//CRN
						if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
							COMB_CRN = 0;
							CRN = lines.next();
							COMB_CRN = Integer.parseInt(CRN + result);
						}else{
							//for more that 10 DISC's in a row.
							CRN = "" + (++COMB_CRN);
						}
						//System.out.println("crn = " + "00" + " and CRN = " + CRN);
				}else{
					CRN = "-99999";
					}
				
				if(lines.useDelimiter(";").hasNext()){//NUMBER
					if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
						NUMBER = lines.next();
					}
				}
				if(lines.useDelimiter(";").hasNext()){//TITLE
					if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
						TITLE = lines.next().replaceAll("'", "");
						if(TITLE.length() > 200)
							TITLE = TITLE.substring(0,200);
					}
				}
				if(lines.useDelimiter(";").hasNext()){//UNITS
					if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
						UNITS = lines.next();
					}
					//System.out.println("Units = " + UNITS );
				}
				if(lines.useDelimiter(";").hasNext())//ACTIVITY
					ACTIVITY = lines.next();
				if(lines.useDelimiter(";").hasNext())//DAYS
					DAYS = lines.next();
				if(lines.useDelimiter(";").hasNext())//TIME
					TIME = lines.next();
				if(lines.useDelimiter(";").hasNext())//ROOM
					ROOM = lines.next();
				if(lines.useDelimiter(";").hasNext())//LENGTH
					LENGTH = lines.next();
				if(lines.useDelimiter(";").hasNext())//INSTRUCTION
					INSTRUCTOR = lines.next();
				if(lines.useDelimiter(";").hasNext()){//MAX ENROLLMENT
					String maxenrl = lines.next();
					if(!(maxenrl.equals(""))){
						MAXENRL = maxenrl;
					}else{
						MAXENRL = "-1";
					}
					//System.out.println(maxenrl);
				}
				if(lines.useDelimiter(";").hasNext()){//SEATS AVAILABLE
					String seatavl = lines.next();
					if(!(seatavl.equals(""))){
						SEATAVL = seatavl;
					}else{
						SEATAVL = "-1";
					}
				}
				if(lines.useDelimiter(";").hasNext()){//ACTIVE ENROLLMENT
					String actenrl = lines.next();
					if(!(actenrl.equals(""))){
						ACTENRL = actenrl;
					}else{
						ACTENRL = "-1";
					}
				}
				SEM_ID = "1";
				System.out.println(line);
				
								//back to courses
				sql = "INSERT INTO courses VALUES('"+CRN+"',"
						+ "'"+NUMBER+"',"
						+ "'"+TITLE+"',"
						+ "'"+UNITS+"',"
						+ "'"+ACTIVITY+"',"
						+ "'"+DAYS+"',"
						+ "'"+TIME+"',"
						+ "'"+ROOM+"',"
						+ "'"+LENGTH+"',"
						+ "'"+INSTRUCTOR+"',"
						+ "'"+MAXENRL+"',"
					    + "'"+SEATAVL+"',"
						+ "'"+ACTENRL+"',"
						+ "'"+SEM_ID+"');";
				
				stat.executeUpdate(sql);
			}
			input.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void UpdateCourses(){
		int COMB_CRN = 0;
		String pad = String.format("%%0%dd", 2);
		String result = String.format(pad, COMB_CRN);//00 padding
		String CRN="",
				MAXENRL="",
				SEATAVL="",
				ACTENRL="",
				SEM_ID="",
				TEMP="";
		
		try{
			stat = conn.createStatement();
			File file = new File("output.txt");
			Scanner input = new Scanner(file);
			
			while(input.hasNextLine()){
				String line = input.nextLine();
				
				Scanner lines = new Scanner(line);
				
				if(lines.useDelimiter(";").hasNext()){//CRN
						if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
							COMB_CRN = 0;
							CRN = lines.next();
							COMB_CRN = Integer.parseInt(CRN + result);
						}else{
							//for more that 10 DISC's in a row.
							CRN = "" + (++COMB_CRN);
						}
						System.out.println("crn = " + "00" + " and CRN = " + CRN);
				}else{
					//Indicates end of classes. DELETE this row.
					CRN = "-99999";
					}
				
				if(lines.useDelimiter(";").hasNext()){//NUMBER
					if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
						TEMP = lines.next();
					}
				}
				if(lines.useDelimiter(";").hasNext()){//TITLE
					if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
						TEMP = lines.next();
					}
				}
				if(lines.useDelimiter(";").hasNext()){//UNITS
					if(!(lines.hasNext("DISC") || (lines.hasNext("LAB")))){
						TEMP = lines.next();
					}
				}
				if(lines.useDelimiter(";").hasNext())//ACTIVITY
					TEMP = lines.next();
				if(lines.useDelimiter(";").hasNext())//DAYS
					TEMP = lines.next();
				if(lines.useDelimiter(";").hasNext())//TIME
					TEMP = lines.next();
				if(lines.useDelimiter(";").hasNext())//ROOM
					TEMP = lines.next();
				if(lines.useDelimiter(";").hasNext())//LENGTH
					TEMP = lines.next();
				if(lines.useDelimiter(";").hasNext())//INSTRUCTION
					TEMP = lines.next();
				if(lines.useDelimiter(";").hasNext()){//MAX ENROLLMENT
					String maxenrl = lines.next();
					if(!(maxenrl.equals(""))){
						MAXENRL = maxenrl;
					}else{
						MAXENRL = "-1";
					}
					//System.out.println(maxenrl);
				}
				if(lines.useDelimiter(";").hasNext()){//SEATS AVAILABLE
					String seatavl = lines.next();
					if(!(seatavl.equals(""))){
						SEATAVL = seatavl;
					}else{
						SEATAVL = "-1";
					}
				}
				if(lines.useDelimiter(";").hasNext()){//ACTIVE ENROLLMENT
					String actenrl = lines.next();
					if(!(actenrl.equals(""))){
						ACTENRL = actenrl;
					}else{
						ACTENRL = "-1";
					}
				}
				System.out.println(line);
				
				
				sql = "UPDATE courses SET seatsAvailable = '"+SEATAVL+"' WHERE crn = '"+CRN+"';";
				stat.executeUpdate(sql);
				sql = "UPDATE courses SET ActiveEnrl = '"+ACTENRL+"' WHERE crn = '"+CRN+"';";
				stat.executeUpdate(sql);
			}
			input.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void SelectCoursesCSE(){
		
	}
}
