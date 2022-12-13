package data;

import java.util.Objects;

public final class Credentials implements Cloneable {
    private String name;
    private String password;
    private AccountType accountType;

    private String country;
    private String balance;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType type) {
        this.accountType = type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Credentials that = (Credentials) o;
        return name.equals(that.name) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Credentials credentials = new Credentials();
        credentials.name = name;
        credentials.password = password;
        credentials.accountType = accountType;
        credentials.country = country;
        credentials.balance = balance;
        return credentials;
    }
}



