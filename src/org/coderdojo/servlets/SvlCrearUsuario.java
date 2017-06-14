package org.coderdojo.servlets;
import java.io.IOException;

//Import DB Driver
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.Calendar;
import java.sql.Date;



//import org.apache.commons.validator.EmailValidator;//Not working from servlet?
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.DateUtils;
import org.coderdojo.utils.User;




public class SvlCrearUsuario extends HttpServlet { //Creo que el jboss no est� cargado correctamente si da error aqu�
	private static final long serialVersionUID = 1L;
	
    public SvlCrearUsuario() {
        // TODO Auto-generated constructor stub
    	
    }

    public boolean isStrongPassword(String inputPassword) {
        // Comprueba si una contrase�a es suficientemente fuerte
    	boolean check1 = false, check2 = false, check3 = false;
    	
    	//Texto suficientemente largo
    	if (inputPassword.length() >= 7)
    	{check1=true;}; 
    	
    	//Contiene alg�n numero
    	if (inputPassword.matches(".*\\d+.*"))
    	{check2=true;};
    	
    	//Comprueba si tiene alguna mayuscula
    	boolean hasUppercase = !inputPassword.equals(inputPassword.toLowerCase());
    	if (hasUppercase)
    	{check3 = true;};
    	
    	if(check1&&check2&&check3)
    	{return true;};
    	return false;
    	
    }
    
    public boolean userInDB(String inputUser){
    	FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn;
    	
    	boolean result = false;
    	
		try {
			conn = f.dameConexion();
			String queryCheck = "SELECT * from users WHERE nickname = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, inputUser);
	    	
	    	System.out.println("Nickname query prepared");
	    	
	    	ResultSet resultSet = ps.executeQuery();
	    	
	    	System.out.println("Nickname query executed");
	    	
	    	while(resultSet.next()){
	    		result=true;
	    		System.out.println("Nickname exists");
	    	}
	    	
	    	conn.close();
	    	
	    	/*
	    	while (true){
	    		//TODO Add a tester
	    		Object r = resultSet.next();
	    		System.out.println("userInDB loop ->" + r.toString());
	    		if((Boolean)r == false){
	    			break;
	    		}
	    	}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result;
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Recibe los datos enviados por el usuario
		String username = request.getParameter("nickname");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("mail");
		Date regDate = new Date(Calendar.getInstance().getTime().getTime());
		Date bornDate = DateUtils.fromString(request.getParameter("bornDateLong"));
		String skype = request.getParameter("skype");
		String desc = request.getParameter("desc");
		boolean hasDesc = (desc != null);
		User user = null;
    	
		System.out.println("Datos recibidos");
		System.out.println("email: "+email+" password: "+password);
		
    	boolean validUsername = false;
    	boolean validPassword = false;
    	boolean validEMAIL = false;
    	
    	//Comprueba si el usuario ya est� registrado
    	validUsername = ((username.length() >= 4) && ( !userInDB(username)));
    	
    	
    	validPassword = isStrongPassword(password);
    	
    	
    	//Check if the function is imported correctly
    	/*
    	EmailValidator validator = EmailValidator.getInstance(); A�adir if email not in database
    	if (validator.isValid(email))
    	{
    		validEMAIL = true;
    	};*/
    	
    	validEMAIL = true;
    	
    	if( validUsername && validPassword && validEMAIL )
    	{
    		//writeDatabase(); Writes Username, hash, etc..
    		//1 pedimos la fabrica
    		FabricaConexiones laFabria=FabricaConexiones.getFabrica();
    		//pedimios una conexi�n
    		try {
    			/**/
				Connection conexion=laFabria.dameConexion();
				//RequestStatement rs;
				String myQuery = "insert into users(nickname, password, name, surname, email, rep, registry_date, born_date, skype_user) values (?,?,?,?,?,0,?,?,?)";
				if(hasDesc){
					myQuery = "insert into users(nickname, password, name, surname, email, rep, registry_date, born_date, skype_user, description) values (?,?,?,?,?,0,?,?,?,?)";
				}
	    		PreparedStatement preStm = conexion.prepareStatement(myQuery);
	    		
	    		preStm.setString(1, username);
	    		preStm.setString(2, password);
	    		preStm.setString(3, name);
	    		preStm.setString(4, surname);
	    		preStm.setString(5, email);
	    		
	    		preStm.setDate(6, regDate);
	    		preStm.setDate(7, bornDate);
	    		preStm.setString(8, skype);
	    		if(hasDesc){
	    			preStm.setString(9, desc);
	    		}
	    		
	    		System.out.println("1st QUERY PREPARED");
	    		
	    		preStm.execute();
	    		preStm.close();
	    		//get id and log the user in
	    		String myOtherQuery = "select user_id from users where nickname = ?";
	    		PreparedStatement preStm2 = conexion.prepareStatement(myOtherQuery);
	    		
	    		preStm2.setString(1, username);
	    		
	    		System.out.println("2nd QUERY PREPARED");
	    		ResultSet rs = preStm2.executeQuery();
	    		
				long id = -1;
	    		
	    		while(rs.next()){
	    			id = rs.getLong("user_id");
	    		}
	    		preStm2.close();
	    		
	    		System.out.println("ID gotten " + id);
	    		
	    		user = new User(id, username,password,name,surname,email,regDate,bornDate,skype);
	    		if(hasDesc){
	    			user.setDescription(desc);
	    		}
	    		
	    		System.out.println("User created " + user.getDisplayName());
	    		
	    		conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		HttpSession sesion = request.getSession();
    		sesion.setAttribute("user", user);
    		
    		//Log the user in
    		
    		response.sendRedirect("welcomePage.jsp");
    	}
    	else
    	{
    		
    		response.sendRedirect("regError.html");
    	};
		
    	
	}

}
