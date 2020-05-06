package model;
import model.Hospital;

import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/HospitalAPI") 
public class HospitalAPI extends HttpServlet 
{  
	private static final long serialVersionUID = 1L;
	
	Hospital HosObj = new Hospital();
	
	public HospitalAPI()
	{
		super();
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String output = HosObj.insertHospital(request.getParameter("HospitalCode"),      
				request.getParameter("HospitalName"),     
				request.getParameter("HospitalAddress"),       
				request.getParameter("HospitalPhoNo")); 
	 
	 response.getWriter().write(output); 
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request); 
		 
		 String output = HosObj.updateHospital(paras.get("hidHospitalIDSave").toString(),     
				 paras.get("HospitalCode").toString(),     
				 paras.get("HospitalName").toString(),        
				 paras.get("HospitalAddress").toString(),        
				 paras.get("HospitalPhoNo").toString()); 
		 
		 response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Map paras = getParasMap(request); 
		 
		 String output = HosObj.deleteHospital(paras.get("HospitalID").toString()); 
		 
		 response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map 
		private static Map getParasMap(HttpServletRequest request) 
		{  
			Map<String, String> map = new HashMap<String, String>();  
			try  
			{   
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
				String queryString = scanner.hasNext() ?          
						scanner.useDelimiter("\\A").next() : "";  
						scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   
		  { 

	 
		   String[] p = param.split("=");    
		   map.put(p[0], p[1]);   
		   }  
		  }  
			catch (Exception e)  
			{  
				
			}  return map; 
			
		}

	
}
