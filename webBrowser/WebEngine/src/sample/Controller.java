package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView; //JavaFX WebView est un mini navigateur qui est appelé comme un navigateur intégré
                                // (embedded browser) dans l'application JavaFX
import java.net.URL;
import java.util.ResourceBundle;
//initialisation du Controller
public class Controller implements Initializable {


    @FXML
   private TextField addressBar;
    @FXML
    private WebView web;       //the webview is in charge of displaying the content of a web engine
    WebEngine engine;       //engine is a non-visual object capable of managing one Web page at a time.
                           // It loads Web pages, creates their document models, get history , and runs JavaScript on pages.
     private String homePage;
    private double webZoom;
    private WebHistory history;    //history of our webengine object is used for displaying what pages that we
                                  // visited at which time

    @Override //the subclass Controller takes the initialize method of its parent interface
             // and overrides it so that the output of that method will be different from its initial output

    public void initialize(URL location, ResourceBundle resources) {
        engine = web.getEngine();  // obtenir un objet  WebEngine de WebView en utilisant la méthode getEngine().
        homePage = "www.google.com";
        addressBar.setText(homePage);  //WE DON T HAVE TO TYPE IN HTTP BECAUSE  WE ARE ADDING THAT WHEN WE USE THE LOAD METHOD
                                      // OF OUR ENGINE

        webZoom = 1;     //1 FOR 100%
        loadPage();
    }

    public void loadPage() {

        engine.load("http://" +addressBar.getText());
    }

    public void refreshPage() {
        engine.reload();
    }
    public void zoomIn() {
        webZoom+=0.25;    // WE ZOOM IN with 0.25 INCREMENTATION DE WEBZOOM
        web.setZoom(webZoom);
    }

    public void zoomOut() {
        webZoom-=0.25;     // WE ZOOM out with 0.25 INCREMENTATION DE WEBZOOM
        web.setZoom(webZoom);
    }
    public void displayHistory() {
        history = engine.getHistory();
        //observable list allow to track changes
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        // The history is basically a list of entries. Each entry represents a visited page and it provides access to relevant
        // page info, such as URL, title, and the date the page was last visited
        for(WebHistory.Entry entry : entries) {

            //System.out.println(entry);
            System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
        }
    }
    public void back() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1); //to visit a recently visited page we type call our history objet.go and we pass in a negative 1
        //to get the current url of the webpage that we are on
        addressBar.setText(entries.get(history.getCurrentIndex()).getUrl());

    }

    public void forward() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);

        addressBar.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void executeJS() {
       //we pass in some javascript as a string
        //the javascript that we are typing here is for changing the windows location go to youtube
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }

}
