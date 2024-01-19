package state;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DrownDownstateDBService {

Connection con;

	public DrownDownstateDBService()
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
	
	
	public ArrayList<StateDTO> getList(String tableName)
	{
		
		ArrayList<StateDTO> list = new ArrayList<StateDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				StateDTO StateDTO =new StateDTO();
				StateDTO.setId(rs.getInt("id"));
				StateDTO.setCode(rs.getString("code"));
				StateDTO.setValue(rs.getString("value"));
				list.add(StateDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}

