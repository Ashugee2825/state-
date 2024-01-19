package state;

import jakarta.servlet.http.HttpServletRequest;


public class State {
 
int id;
String code;
String value;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getValue() {
	return value;
}

public void setValue(String i) {
	this.value = i;
}


public void setRequestParam(HttpServletRequest request) {

this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
this.setCode(null!=request.getParameter("code")?request.getParameter("code"):"");
this.setValue(null!=request.getParameter("Value")?request.getParameter("Value"):"");
//this.setValue(null!=request.getParameter("value")&&!request.getParameter("value").equals("")?Integer.parseInt((String)request.getParameter("value")):0);

}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("code = "+request.getParameter("code"));
System.out.println("value = "+request.getParameter("value"));

System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("code = "+this.getCode());
System.out.println("value = "+this.getValue());

}

public void setDefaultValues() {

this.setCode("admin111");
this.setValue("Admin2212");
this.setValue("Admin222");
   
}
}
