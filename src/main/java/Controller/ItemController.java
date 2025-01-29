package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Model.Item;
import Model.ItemDetails;
import Model.ShowItemDetails;
import Service.ItemService;
import ServiceImpl.ItemServiceImpl;
import ServiceImpl.ItemUtilService;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	
	@Resource(name = "jdbc/web_item")
	private DataSource dataSource;
	
	
	
	private ItemUtilService itemUtilService;
   
   
    

    public void init() throws ServletException{
//    	itemservice = new ItemServiceImpl(dataSource);
    	itemUtilService = new ItemUtilService(dataSource);
    }
    
    
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String action = request.getParameter("action");
		
		if (action == null) {
			action = "SIGN";
		}
		
		switch(action) {
			case "ADD":
				addItem(request, response);
				break;
			case "LOAD_ITEMS":
				loadItems(request, response);
				break;
			case "LOAD_ITEM":
				loadItem(request, response);
				break;
			case "DELETE":
				deleteItem(request, response);
				break;
			case "UPDATE":
				updateItem(request, response);
				break;
			
				 case "ADD_ITEM_DETAILS":
							addItemDetails(request, response);
								break;
							case "LOAD_ITEM_DETAILS":
								loadItemDetails(request, response);
								break;
							case "Update_ITEM_DETAILS":
								UpdateITEMDETAILS(request, response);
								break;
							case "LOAD_ITEMS_DETAILS":
								lOADITEMSDETAILS(request, response);
								break;
							  case "SIGN_IN":
							        signIn(request, response);
							        break;
							    case "SIGN_UP":
							        signUp(request, response);
							        break;
							    case "SIGN":
							    	 sign(request, response);
								        break;
							    case "SIGN_OUT" :
							    signOut(request, response);
							    break;
								
			default:
				loadItems(request, response);
		
		}
		
		
		

	        
	     }
		
		
		
	
	

	private void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(false); // Don't create a new session if one doesn't exist

        if (session != null) {
            // Invalidate the session to log the user out
            session.invalidate();
        }

        // Redirect to the login page after signing out
        response.sendRedirect("login.jsp");
		
		
	}





	private void sign(HttpServletRequest request, HttpServletResponse response) {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	     
		 try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}



	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		
		try {
		String username = request.getParameter("usernameup");
	    String password = request.getParameter("passwordup");

	    itemUtilService.signUp(username, password);
	 
	   
	   HttpSession session = request.getSession();
       session.setAttribute("username", username);
       session.setAttribute("isAuthenticated", true);

       // Optionally, create a cookie to remember the user
       Cookie usernameCookie = new Cookie("usernamee", username);
       usernameCookie.setMaxAge(50); // 30 days
       usernameCookie.setPath(request.getContextPath());
       response.addCookie(usernameCookie);

       // Redirect the user to the items page or appropriate page after signup
       response.sendRedirect("ItemController?action=LOAD_ITEMS");
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}


	
	
	
	
	
	
	private boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("isAuthenticated") != null
		&& (Boolean) session.getAttribute("isAuthenticated");
		}
	
	
	
	


	private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String username = request.getParameter("usernamein");
	        String password = request.getParameter("passwordin");

	        boolean isAuthenticated = itemUtilService.signIn(username, password);

	        if (isAuthenticated) {
	            
	            HttpSession session = request.getSession(); // Create a new session
	            session.setAttribute("username", username);
	            session.setAttribute("isAuthenticated", true);

	            // Set a cookie for username
	            Cookie usernameCookie = new Cookie("username", username);
	            usernameCookie.setMaxAge(4 * 24 * 60 *60); // Set expiry to 4 days
	            usernameCookie.setPath("/"); // Make cookie accessible throughout the app
	            response.addCookie(usernameCookie);

	            // Redirect to load items
	            loadItems(request, response);
	        } else {
	            request.setAttribute("errorMessage", "Invalid username or password");
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "An error occurred during sign in");
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
	    }
	}


	
	
	
	private void lOADITEMSDETAILS(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
		
			ItemDetails item = itemUtilService.findItemDetailsById(id);
			
			request.setAttribute("existedItem", item);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Update-itemDetails.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void UpdateITEMDETAILS(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String model = request.getParameter("model");
			String description = request.getParameter("description");
			int itemdetailsid= Integer.parseInt(request.getParameter("itemdetailsid"));
			
			ItemDetails item = new ItemDetails(itemdetailsid, model,type, description, id);
			itemUtilService.updateItemDetails(item);
			
			
			loadItems(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	


	void addItem(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			int id = Integer.parseInt (request.getParameter("id"));
			String name = request.getParameter("nameItem");
			double price = Double.parseDouble(request.getParameter("price"));
			int totalNumber = Integer.parseInt(request.getParameter("TOTALNUMBER"));
			
			Item item = new Item(id,name, price, totalNumber);
			itemUtilService.saveItem(item);
			
			loadItems(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	
	void addItemDetails(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String type = request.getParameter("typeModel");
			String model = request.getParameter("model");
			String description = request.getParameter("Description");
			int item_id = Integer.parseInt(request.getParameter("id"));
			int itemdetailsid = Integer.parseInt(request.getParameter("itemdetailsid"));
			
			
			ItemDetails itemDetails = new ItemDetails(itemdetailsid, model,type, description,item_id );
			itemUtilService.saveItemDetails(itemDetails);
			
			loadItems(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	void loadItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 HttpSession session = request.getSession(false);
		    if (session == null || session.getAttribute("username") == null) {
		        response.sendRedirect(request.getContextPath() + "/");
		        return;
		    }
		try {	
			List<Item> items = itemUtilService.getAllItem();
			List<ShowItemDetails> it = itemUtilService.getAllItemDetails2();
			request.setAttribute("allItems", items);
			request.setAttribute("allIt", it);
			
			// Forward the wrapped request
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loadItems.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	void loadItem(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
		
			Item item = itemUtilService.findItemById(id);
			
			request.setAttribute("existedItem", item);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-item.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	void loadItemDetails(HttpServletRequest request, HttpServletResponse response) {
		
		try {	
			List<ShowItemDetails> items = itemUtilService.getAllItemDetails();
			
			request.setAttribute("allItemsDetails", items);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/show-items-details.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
		
			// TODO   delete child item details if exist
			itemUtilService.deleteItem(id);
			
			loadItems(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void updateItem(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("nameItem");
			double price = Double.parseDouble(request.getParameter("price"));
			int totalNumber = Integer.parseInt(request.getParameter("TOTALNUMBER"));
			
			Item item = new Item(id, name, price, totalNumber);
			itemUtilService.updateItem(item);
			
			
			loadItems(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
