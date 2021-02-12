package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Classroom {
		private List<UserAccount> users;
		
		public Classroom() {
			users = new ArrayList<>();
		}
		
		public void addContact(String userName, String password, Image image, String gender, String career, String birthday, String browser) {
			users.add(new UserAccount(userName, password, image, gender, career, birthday, browser));
		}
		
		public List<UserAccount> getContacts(){
			return users; 
		}
		
		public boolean validateUser(String userName, String password) {
			boolean validate=false;
			for(int i=0; i<users.size() && !validate;i++) {
				UserAccount user = users.get(i);
				if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
					validate=true;
				}
			}
			return validate;
		}
		
		public int amountUsers() {
			return users.size();
		}
		
		public Image lastImage(String username, String password) {
			boolean aux = false;
			Image newImage = null;
			
			for(int i=0; i<users.size() && !aux; i++) {
				if(users.get(i).getUserName().equals(username) && users.get(i).getPassword().equals(password)){
					newImage=users.get(i).getImage();
					aux=true;
				}
			}
			return newImage;
		}

		public String lastUser(String userName, String password) {
			boolean aux = false;
			String newTxt = null;
			
			for(int i=0; i<users.size() && !aux;i++) {
				if(users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password)) {
					newTxt=users.get(i).getUserName();
					aux=true;
				}
			}
			return newTxt;
		}

}
