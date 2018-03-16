package ws.com.wstest.Entities;

// --> Creating model object Users

public class User {
    int id;
    String Email, Password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(int id, String Email, String Password) {
        super();
        this.id=id;
        this.Email = Email;
        this.Password = Password;

    }
}
