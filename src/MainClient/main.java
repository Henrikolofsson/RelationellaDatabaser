package MainClient;

import Controllers.MainController;
import GUI.FestivalWindow;

public class main {

    public static void main(String[] args) {
        MainController controller = new MainController();
        FestivalWindow festivalWindow = new FestivalWindow(controller);
        controller.setFestivalWindow(festivalWindow);
    }
}
