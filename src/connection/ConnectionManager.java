package connection;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection conn;
    public static Connection getConnection(){
    	if(conn==null){
    	try{
    		
    		
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		
    		conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/techquo?" +
    	                                           "user=root&password=tiger");
    	}
    	
    	catch (Exception ex) {
    			ex.printStackTrace();
    	}
    	}
    	return conn;
    }
}
