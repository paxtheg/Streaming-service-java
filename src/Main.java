import app.BackendApplication;

public class Main {

    //starts the Backend application by adding the input and output files
    // and resets the information for every test
    public static void main(final String[] args) throws CloneNotSupportedException {
        BackendApplication.getApp().startBackend(args[0], args[1]);
        BackendApplication.getApp().reset();
    }
}
