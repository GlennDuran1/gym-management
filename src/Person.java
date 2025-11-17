public abstract class Person implements Login {

    protected String name;
    protected String userName;
    protected String password;
    protected int id;

    public Person(String name, String userName, String password, int id) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    @Override
    public boolean checkCredentials(String u, String p) {
        return userName.equals(u) && password.equals(p);
    }

    public String getName() { return name; }
    public String getUserName() { return userName; }
    public int getId() { return id; }
    public void setName(String name) {
    this.name = name;
}

public void setUserName(String userName) {
    this.userName = userName;
}

public void setPassword(String password) {
    this.password = password;
}

}
