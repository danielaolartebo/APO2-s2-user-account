package ui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
	private Classroom classroom;

    @FXML
    private BorderPane mainPanel;
   
    @FXML
    private BorderPane loginPane;
    
    @FXML
    private BorderPane accountListPane;
    
    @FXML
    private BorderPane registerPane;
    
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button signUp;

    @FXML
    private Button logIn;

    @FXML
    private Label labMsg;

    @FXML
    private TextField txtProfilePic;

    @FXML
    private RadioButton txtGender;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private DatePicker txtBirthday;

    @FXML
    private MenuButton txtBrowser;

    @FXML
    private CheckBox txtCareer;

    @FXML
    private TableView<UserAccount> tvAccountList;

    @FXML
    private TableColumn<UserAccount, String> tcUsername;

    @FXML
    private TableColumn<UserAccount, String> tcGender;

    @FXML
    private TableColumn<UserAccount, String> tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, String> tcBrowser;


    @FXML
    private ImageView selectPicture;
    
    public ClassroomGUI(Classroom cl) {
    	classroom = cl;
	}

    @FXML
    public void optContinue(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	Parent loginPane = fxmlLoader.load();
    	mainPanel.getChildren().setAll(loginPane);
    }
    @FXML
    public void logIn(ActionEvent event) throws IOException {
    	String userName=txtUsername.getText();
    	String password=txtPassword.getText();
    	if(classroom.validateUser(userName, password)) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
        	fxmlLoader.setController(this);
        	Parent accountListPane = fxmlLoader.load();
        	loginPane.getChildren().setAll(accountListPane);
    	}if(!classroom.validateUser(userName, password)) {
    		loginError();
    	}
    }
    private void loginError() {
    	Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Log in incorrect\"");
	    alert.setHeaderText("");
	    alert.setContentText("The username and password given are incorrect");
	    alert.showAndWait();
	}

	@FXML
    public void signUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlLoader.setController(this);
    	Parent registerPane = fxmlLoader.load();
    	mainPanel.getChildren().setAll(registerPane);
    }
    
    @FXML
    public void optSignIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	Parent loginPane = fxmlLoader.load();
    	registerPane.getChildren().setAll(loginPane);
    }
    
     @FXML
    public void optCreateAccount(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
    	fxmlLoader.setController(this);
    	Parent accountListPane = fxmlLoader.load();
		mainPanel.getChildren().clear();
    	mainPanel.setCenter(accountListPane);
    	initializeTableView();
    } 
    
    @FXML
    public void loadAddContact(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent accountListPane = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setTop(accountListPane);
    }
    
    @FXML
    public void optBirthday(ActionEvent event) {

    }
    
    @FXML
    public void optBrowser(ActionEvent event) {

    }

    @FXML
    void optFemale(ActionEvent event) {

    }

    @FXML
    void optMale(ActionEvent event) {

    }

    @FXML
    void optOther(ActionEvent event) {

    }

    @FXML
    void optSoftware(ActionEvent event) {

    }

    @FXML
    void optTelematic(ActionEvent event) {

    }

    @FXML
    void selectPicture(ActionEvent event) {

    }
    
    @FXML
    public void initializeTableView() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getContacts());
    	
		tvAccountList.setItems(observableList);
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username")); 
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender")); 
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career")); 
		tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday")); 
		tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser")); 
    }
    
    @FXML
    public void loadContactList(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
		
		fxmlLoader.setController(this);
		Parent accountListPane = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setCenter(accountListPane);
    	initializeTableView();
    }
    
    @FXML
    public void showAbout(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Classroom");
	    alert.setHeaderText("Credits");
	    alert.setContentText("Daniela Olarte Borja\nAlgorithms II");
	    alert.showAndWait();
    }
	
}
