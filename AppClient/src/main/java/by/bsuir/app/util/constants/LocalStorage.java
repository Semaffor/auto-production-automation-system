package by.bsuir.app.util.constants;

import by.bsuir.app.entity.Account;

public class LocalStorage {
    private static Account account;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account a) {
        account = a;
    }
}
