public class Admin {
    private String login;
    private int pin;

    public Admin(String login, int pin) {
        this.login = login;
        this.pin = pin;
    }

    public String getLogin() {
        return login;
    }

    public int getPin() {
        return pin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
