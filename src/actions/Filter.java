package actions;

import java.util.HashMap;
import java.util.List;

public final class Filter {
    private HashMap<String, SortType> sort;
    private HashMap<String, List<String>> contains;

    public HashMap<String, SortType> getSort() {
        return sort;
    }

    public void setSort(HashMap<String, SortType> sort) {
        this.sort = sort;
    }

    public HashMap<String, List<String>> getContains() {
        return contains;
    }

    public void setContains(HashMap<String, List<String>> contains) {
        this.contains = contains;
    }
}

enum SortType {
    increasing,
    decreasing
}
