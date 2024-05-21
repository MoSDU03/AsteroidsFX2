package dk.sdu.mmmi.cbse.main;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.List;

public class Game {
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private GraphicsContext gc;

    public Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServices, List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

    public void start(Stage window) {
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.setTitle("My Game");
        window.show();

        for (IGamePluginService plugin : gamePluginServices) {
            plugin.start();
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                render();
                update();
            }
        }.start();
    }

    public void render() {
        gc.clearRect(0, 0, 800, 600);

        // Add rendering code here using gc (GraphicsContext)
        gc.fillText("Game is running", 350, 300);
    }

    private void update() {
        // Call the entity processing services to update game state
        for (IEntityProcessingService service : entityProcessingServices) {
            service.process();
        }

        // Call post entity processing services after the entity processing
        for (IPostEntityProcessingService service : postEntityProcessingServices) {
            service.process();
        }
    }
}
