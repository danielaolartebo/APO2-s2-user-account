package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
    private TextField optUsername;

    @FXML
    private PasswordField optPassword;

    @FXML
    private TextField optProfilePic;

    @FXML
    private TextField txtProfilePic;

    @FXML
    private RadioButton txtMale;
    
    @FXML
    private RadioButton txtFemale;
    
    @FXML
    private RadioButton txtOther;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private DatePicker txtBirthday;
    
    @FXML
    private Label selectUser;
    
    @FXML
    private ChoiceBox<String> txtBrowser;

    @FXML
    private CheckBox txtSoftware;

    @FXML
    private CheckBox txtTelematic;

    @FXML
    private CheckBox txtIndustrial;

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
    
    private RadioButton rbSelected;
    
    Image ima;
    
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
    public void signUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlLoader.setController(this);
    	Parent registerPane = fxmlLoader.load();
    	mainPanel.getChildren().setAll(registerPane);
    	
    	optBrowser(event);
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
    	String userName=optUsername.getText();
    	System.out.println(userName);
    	
    	String password=optPassword.getText();
    	System.out.println(password);
    
    	Image image = ima;
    	rbSelected= (RadioButton)genderGroup.getSelectedToggle();
    	
    	String gender=rbSelected.getText(); 
    	System.out.println(gender);
    	
    	String career="";
    	if(txtSoftware.isSelected()) {
    		career+="Software Engineering";
    	}if (txtTelematic.isSelected()) {
    		career+="Telematic Engineering";
    	}if(txtIndustrial.isSelected()){
    		career+="Industrial Engineering";
    	}
    	System.out.println(career);
    	String birthday=txtBirthday.getValue().toString();
    	System.out.println(birthday);
    	String browser=txtBrowser.getValue();
    	System.out.println(browser);
    	
    	classroom.addContact(userName, password, image, gender, career, birthday, browser);
    	if (userName.isEmpty() || password.isEmpty() || image==null || gender=="" || career=="" || birthday=="" || browser=="") {
        	validationErrorAlert();
        }else{
        	accountCreatedAlert();
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
        	fxmlLoader.setController(this);
        	Parent accountListPane = fxmlLoader.load();
    		mainPanel.getChildren().clear();
        	mainPanel.getChildren().setAll(accountListPane);
        	loadContactList();
        	initializeTableView();
        	
        	selectPicture.setImage(ima);
        	selectUser.setText(userName);
        }																																																								 
    } 
    
    @FXML
    public void logIn(ActionEvent event) throws IOException {
     	String userName=txtUsername.getText();
     	String password=txtPassword.getText();
     	
     	if(classroom.validateUser(userName, password)) {
     		loadContactList();
     		initializeTableView();
     		selectPicture.setImage(classroom.lastImage(userName, password));
     		selectUser.setText(classroom.lastUser(userName, password));
     		
     		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
         	fxmlLoader.setController(this);
         	Parent accountListPane = fxmlLoader.load();
         	loginPane.getChildren().setAll(accountListPane);
         
     	}if(!classroom.validateUser(userName, password)) {
     		loginErrorAlert();
     	}
     }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	Parent loginPane = fxmlLoader.load();
    	mainPanel.getChildren().setAll(loginPane);
    }
    
    @FXML
    public void optBirthday(ActionEvent event) {
    	LocalDate ld = txtBirthday.getValue();
    	tcBirthday.setText(ld.toString());
    }
    
    @FXML
    public void optCareer(ActionEvent event) {
    	String message="";
    	if(txtSoftware.isSelected()) {
    		message += txtSoftware.getText();
    	}
    	if(txtTelematic.isSelected()) {
    		message += txtTelematic.getText();
    	}
    	if(txtIndustrial.isSelected()) {
    		message += txtIndustrial.getText();
    	}
    	tcCareer.setText(message);
    }
    
    @FXML
    public void optBrowser(ActionEvent event) {
    	txtBrowser.getItems().add("Opera");
    	txtBrowser.getItems().add("Google Chrome");
    	txtBrowser.getItems().add("Firefox");
    }

    @FXML
    public void genderGroup(ActionEvent event) {
    	txtMale.setToggleGroup(genderGroup);
    	txtFemale.setToggleGroup(genderGroup);
    	txtOther.setToggleGroup(genderGroup);
    	
    	tcGender.setText(txtMale.toString());
    	tcGender.setText(txtFemale.toString());
    	tcGender.setText(txtOther.toString());
    }

    @FXML
    public void selectPicture(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("Select your picture");
    	fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    	Stage stage = (Stage)registerPane.getScene().getWindow();
    	File file = fc.showOpenDialog(stage);
    	System.out.println(file.getAbsolutePath());	
    	
    	ima= new Image(file.toURI().toString());
    }
    
    @FXML
    public void loadAddContact(ActionEvent even) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addAccountListPane = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setTop(addAccountListPane);
    }
    
    public void loadContactList() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
		
		fxmlLoader.setController(this);
		Parent accountListPane = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setCenter(accountListPane);
    	
    	for(int i=0; i<classroom.getContacts().size(); i++) {
    		System.out.println(classroom.getContacts().get(i));
    		System.out.println("Hola");
    	}
    	initializeTableView();
    }
    
    @FXML
    public void initializeTableView() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getContacts());
		tvAccountList.setItems(observableList);
	
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("UserName")); 
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Gender")); 
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Career")); 
		tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Birthday")); 
		tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Browser")); 

    }
    
    @FXML
    private void accountCreatedAlert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Account created");
	    alert.setHeaderText("");
	    alert.setContentText("New account has been created");
	    alert.showAndWait();
    }
    
    @FXML
    private void validationErrorAlert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Validation error");
	    alert.setHeaderText("");
	    alert.setContentText("You must fill each field in the form");
	    alert.showAndWait();
    }
    
    @FXML
    private void loginErrorAlert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Log in incorrect\"");
	    alert.setHeaderText("");
	    alert.setContentText("The username and password given are incorrect");
	    alert.showAndWait();
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
