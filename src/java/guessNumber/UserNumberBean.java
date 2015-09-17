/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author justin
 */
@ManagedBean
@SessionScoped
public class UserNumberBean implements Serializable{
    
    Integer randomInt;
    Integer userNumber;
    String response;

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
    
    public void submitButton(){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.getResponse(),  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        this.setUserNumber(null);
    }
    
    public String getResponse(){
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)){
            
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            
            
            return userNumber + " is Correct!";
        }else {
            return "<p>Sorry, " + userNumber + " isn't it.</p>"
                    + "<p>Guess again...</p>";
        }    
        
    }

    /**
     * Creates a new instance of UserNumberBean
     */
    public UserNumberBean() {
        Random randomGR = new Random();
        randomInt = randomGR.nextInt(10);
        System.out.println("Duke's number: " + randomInt);
    }
    
}
