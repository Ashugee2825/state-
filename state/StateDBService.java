 package state;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class StateDBService {
	Connection con;
	
	
	public StateDBService()
	{
		DBConnectionDTO conDTO = new DBConnectionDTO();
		con=conDTO.getConnection();
	}
	
	public void closeConnection()
	{
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
public int getTotalPvalues(int limit)
	{
		String query="select count(*) from dd_state";
	    int totalRecords=0;
	    int totalPvalues=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPvalues=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPvalues+=1;
		}
		return totalPvalues;
	}
	
	
//pagination
	public int getTotalPvalues(State state,int limit)
	{
		String query=getDynamicQuery2(state);
		int totalRecords=0;
	    int totalPvalues=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPvalues=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPvalues+=1;
		}
		return totalPvalues;
	}
	
	
	private String getDynamicQuery2(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getstateId(State state)
	{
		int id=0;
		String query="select id from dd_state";
String whereClause = " where "+ "code=? and value=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, state.getCode());
pstmt.setString(2, state.getValue());
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()) {
	       	id = rs.getInt("id");
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return id;
	}
	public void createState(State state)
	{
	// insert   	
String query="INSERT INTO dd_state(code,value) VALUES(?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, state.getCode());
pstmt.setString(2, state.getValue());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getstateId(state);
		state.setId(id);
	}
	// Update  State
	public void updateState(State state)
	{
		
String query="update dd_state set "+"code=?,value=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, state.getCode());
pstmt.setString(2, state.getValue());
pstmt.setInt(3, state.getId());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	    	System.out.println(e);
		}
		
	}
	public String getValue(String code,String table) {
		
		String value="";
		String query="select value from "+table+" where code='"+code+"'";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	    	value=rs.getString("value");
	    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	    return value;
	}
	
	public State getstate(int id)
	{
		State state =new State();
		String query="select * from dd_state where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
state.setId(rs.getInt("id")==0?0:rs.getInt("id"));
state.setCode(rs.getString("code")==null?"":rs.getString("code"));
state.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return state;
	}
	
	
	public ArrayList<State> getstateList()
	{
		ArrayList<State> stateList =new ArrayList<State>();
		String query="select * from dd_state";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	State state =new State();
state.setId(rs.getInt("id")==0?0:rs.getInt("id"));
state.setCode(rs.getString("code")==null?"":rs.getString("code"));
state.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	stateList.add(state);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return stateList;
	}
	
	public ArrayList<State> getstateList(int pvalueNo,int limit)
	{
		ArrayList<State> stateList =new ArrayList<State>();
String query="select * from dd_state limit "+limit +" offset "+limit*(pvalueNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	State state =new State();
state.setId(rs.getInt("id")==0?0:rs.getInt("id"));
state.setCode(rs.getString("code")==null?"":rs.getString("code"));
state.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	stateList.add(state);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return stateList;
	}
	
	public void deletestate(int id) {
		
			String query="delete from dd_state where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(State state)
{
String query="select * from dd_state ";
String whereClause="";
whereClause+=(state.getValue()==null?"":" ="+state.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery21(State state)
{
String query="select count(*) from dd_state ";
String whereClause="";
whereClause+=(state.getValue()==null?"":" value="+state.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<State> getstateList(State state)
{
ArrayList<State> stateList =new ArrayList<State>();
String query=getDynamicQuery(state);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	State state2 =new State();
state2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
state2.setCode(rs.getString("code")==null?"":rs.getString("code"));
state2.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	stateList.add(state2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return stateList;
}
	
public ArrayList<State> getstateList(State state,int pvalueNo,int limit)
{
ArrayList<State> stateList =new ArrayList<State>();
String query=getDynamicQuery(state);
query+= " limit "+limit +" offset "+limit*(pvalueNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	State state2 =new State();
state2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
state2.setCode(rs.getString("code")==null?"":rs.getString("code"));
state2.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	stateList.add(state2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return stateList;
}	
// test cases are test to test
public void testCreate() {
	
	StateDBService stateDBService =new StateDBService();
  
			State state = new State();
			state.setDefaultValues();
		    stateDBService.createState(state); // Test for create Record
			  
			//Test2 for read All records
			  ArrayList<State> stateList = stateDBService.getstateList();
			  StateService stateService =new StateService();
			  stateService.displayList(stateList);
			  stateDBService.closeConnection();
	
}

public void testReadAll() {
	StateDBService stateDBService =new StateDBService();
	  
	State state = new State();
		
	//Test2 for read All records
	  ArrayList<State> stateList = stateDBService.getstateList();
	  StateService stateService =new StateService();
	  stateService.displayList(stateList);
	  stateDBService.closeConnection();
}
   
public void testUpdate()
{
	 StateDBService stateDBService =new StateDBService();
	 
	State state = new State();
	state.setId(3);
	state.setCode("New Code");
	state.setValue("New Value");
	
	stateDBService.updateState(state);
	
	//Test3 for read all records
	ArrayList<State> stateList = stateDBService.getstateList();
	StateService stateService =new StateService();
	stateService.displayList(stateList);
	stateDBService.closeConnection();
	
}

public void testDelete() {
	 StateDBService stateDBService =new StateDBService();
     State state = new State();
     state.setDefaultValues();
	 
	//Test4 for Delete  Record
	 stateDBService.deletestate(4);
	 state.displayValues();
	 
	//Test4 for read all records
	// ArrayList<State> stateList = stateDBService.getStateList();
	// StateService stateService =new StateService();
	// stateService.displayList(stateList);
	// stateDBService.closeConnection();
}

public void testSearch() {
	StateDBService stateDBService =new StateDBService();
	 
	 State state = new State();
	 state=stateDBService.getstate(4);
     state.displayValues();
}



	public static void main(String[] args) {
	
		StateDBService stateDBService =new StateDBService();
		
		stateDBService.testCreate();
		stateDBService.testReadAll();
		stateDBService.testUpdate();
		stateDBService.testDelete();
		stateDBService.testSearch();
		
		
		
		
		 
		
		//Test for Update Record
		//stateDBService.updatestate(state);
	
		// test for Delete Record    
		//stateDBService.deletestate(4);
	
	
	//Test-1 : Create state
	  
			State state = new State(); 
			state.setDefaultValues();
			
				//test1 for Create Record
			//stateDBService.createState(state);
			  
				//Test2 for Read All Records 
			//ArrayList<State> stateList = stateDBService.getStateList();
			//StateService stateService =new StateService();
			//stateService.displayList(stateList);
			
			
		//Test3 for Update Record
		/*
		 * state.setId(5); state.setCode("Admin123"); state.setValue("admin12")
		 * stateDBService.updateState(state); 
		 * //state=stateDBService.getState(5);
		 * state.displayValues();
		 */
			//state=stateDBService.getState(3);
			//state.displayValues();
			
	//Test4 for Delete Record
			//stateDBService.deleteState(4);
			//ArrayList<State> stateList = stateDBService.getStateList();
			//StateService stateService =new StateService();
		    //stateService.displayList(stateList);
			
			
	//Test5 for Read one Record
			//state=stateDBService.getstate(4);
			//state.displayValues();
	
	}

	
	public ArrayList<State> getstateList11() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<State> getstateList1() {
		// TODO Auto-generated method stub
		return null;
	}
	public static int getTotalPages(int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalPages(State state, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}


