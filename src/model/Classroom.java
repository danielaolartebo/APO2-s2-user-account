package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
		private List<UserAccount> users;
		
		public Classroom() {
			users = new ArrayList<>();
		}
		
		public void addContact(String userName, String password, String image, String gender, String career, String birthday, String browser) {
			users.add(new UserAccount(userName, password, image, gender, career, birthday, browser));
		}
		
		public void addContact(String userName, String password) {
			users.add(new UserAccount(userName, password));
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

}
