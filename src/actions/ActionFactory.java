package actions;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    public static Action createAction(final ActionData data) {
        return switch (data.getType()) {
            case "change page" -> new ChangePageAction(data.getType(),
                    data.getPage(), data.getMovie());
            case "on page" -> switch (data.getFeature()) {
                case "login" ->
                        new LoginAction(data.getType(), data.getPage(),
                                data.getFeature(), data.getCredentials());
                case "register" ->
                        new RegisterAction(data.getType(), data.getPage(),
                                data.getFeature(), data.getCredentials());
                case "search" ->
                        new SearchAction(data.getType(), data.getPage(),
                                data.getFeature(), data.getStartsWith());
                case "filter" -> new FilterAction(data.getType(), data.getPage(),
                        data.getFeature(), data.getFilters());
                case "buy tokens" ->
                        new BuyTokensAction(data.getType(), data.getPage(),
                                data.getFeature(), data.getCount());
                case "buy premium account" ->
                        new BuyPremiumAccountAction(data.getType(), data.getPage(),
                                data.getFeature());
                case "purchase" -> new PurchaseAction(data.getType(), data.getPage(),
                        data.getFeature());
                case "like" -> new LikeAction(data.getType(), data.getPage(),
                        data.getFeature());
                case "watch" -> new WatchAction(data.getType(), data.getPage(),
                        data.getFeature());
                case "rate" -> new RateAction(data.getType(), data.getPage(),
                        data.getFeature(), data.getRate());
                default -> null;
            };
            default -> null;
        };

    }

    public static List<Action> createActions(final List<ActionData> data) {
        List<Action> actions = new ArrayList<>();

        for (ActionData datum : data) {
            actions.add(createAction(datum));
        }

        return actions;
    }
}
