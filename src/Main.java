import app.BackendApplication;

public class Main {
    public static void main(final String[] args) throws CloneNotSupportedException {
        BackendApplication.getApp().startBackend(args[0], args[1]);
        BackendApplication.getApp().reset();
    }
}
