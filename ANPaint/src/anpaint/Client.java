package anpaint;

public class Client {

    private AppWindow _window;

    public Client() {
        _window = null;
    }

    public void run() {
        _window = AppWindow.getInstance();
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.run();
    }
}