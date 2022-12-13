package actions;

import app.BackendApplication;
import data.User;

import java.util.ArrayList;

public final class BuyTokensAction extends OnPageAction {
    private final String count;

    public BuyTokensAction(final String type, final String page, final String feature, final String count) {
        super(type, page, feature);
        this.count = count;
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("upgrades")) {
            User currentUser = app.getCurrentUser();
            int noTokens = currentUser.getTokensCount();
            int balance = Integer.parseInt(currentUser.getCredentials().getBalance());
            int countInt = Integer.parseInt(count);
            if(countInt <= balance) {
                currentUser.setTokensCount(noTokens + countInt);
                currentUser.getCredentials().setBalance(String.valueOf(balance - countInt));
                return null;
            }
            return new app.Error("Error", new ArrayList<>(), null);
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
