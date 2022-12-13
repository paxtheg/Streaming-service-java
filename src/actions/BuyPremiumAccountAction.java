package actions;

import app.BackendApplication;
import app.Error;
import data.AccountType;
import data.User;

import java.util.ArrayList;

public final class BuyPremiumAccountAction extends OnPageAction {
    private final int price = 10;
    public BuyPremiumAccountAction(final String type, final String page, final String feature) {
        super(type, page, feature);
    }

    @Override
    public Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("upgrades")) {
            User currentUser = app.getCurrentUser();
            int noTokens = currentUser.getTokensCount();
            if (noTokens >= price) {
                currentUser.setTokensCount(noTokens - price);
                currentUser.getCredentials().setAccountType(AccountType.premium);
                return null;
            }
            return new app.Error("Error", new ArrayList<>(), null);
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
