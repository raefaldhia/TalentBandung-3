package io.github.raefaldhia.talentbandung_3;

/**
 * Created by raefaldhia on 10/31/17.
 */

public class DashboardItem {
    String Username;
    String Email;

    public DashboardItem(String Username, String Email)
    {
        this.Username = Username;
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
