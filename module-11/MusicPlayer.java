/*
 * Name:        Kristian Wareing
 * Course:      CSD-402 Java for Programmers
 * Assignment:  Module 11.2 Written Assignment - JavaFX HBox and VBox
 * Date:        October 2026
 *
 * Purpose:
 *   A 2010s-style music player screen built only from HBox and VBox
 *   layouts. It demonstrates the layout properties covered in the paper:
 *   spacing, padding, alignment, nesting, and grow priority (Hgrow).
 *
 * Layout structure:
 *   VBox root (stacks everything top to bottom)
 *     - Song title
 *     - Album art
 *     - Artist name
 *     - HBox controls:  [ Prev ] [ Play ] [ Next ]   (centered)
 *     - HBox share row: [ Share ] <spacer> [ Like ] [ Add ]
 *       The spacer has Priority.ALWAYS, so it absorbs extra width
 *       when the window is resized and pushes the buttons apart.
 *
 * How to run (Windows, PowerShell, with PATH_TO_FX set to the SDK lib folder):
 *   javac --module-path $env:PATH_TO_FX --add-modules javafx.controls MusicPlayer.java
 *   java  --module-path $env:PATH_TO_FX --add-modules javafx.controls MusicPlayer
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Builds and displays a music player window using nested HBox and VBox
 * containers. All styling lives in this file so the assignment stays a
 * single runnable source file.
 */
public class MusicPlayer extends Application {

    /** Pixel size of the square album art placeholder. */
    private static final double ART_SIZE = 220;

    /** Gap in pixels between items stacked in the root VBox. */
    private static final double ROOT_SPACING = 14;

    /** Gap in pixels between buttons inside each HBox row. */
    private static final double ROW_SPACING = 10;

    /**
     * Shared 2010s "glossy" button style: a light-to-dark gray gradient,
     * rounded corners, and a thin border, similar to iTunes 10 controls.
     */
    private static final String GLOSSY_BUTTON =
            "-fx-background-color: linear-gradient(to bottom, #fdfdfd 0%, #e2e2e2 50%, #cfcfcf 51%, #bdbdbd 100%);"
          + "-fx-background-radius: 16;"
          + "-fx-border-color: #8a8a8a;"
          + "-fx-border-radius: 16;"
          + "-fx-text-fill: #333333;"
          + "-fx-font-weight: bold;";

    /**
     * JavaFX entry point. Builds the scene graph and shows the window.
     *
     * @param stage the primary window supplied by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        // Text labels above and below the art
        Label title = new Label("Midnight Drive");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Label artist = new Label("The Neon Arcade");
        artist.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");

        // VBox root: stacks title, art, artist, and the two button rows.
        // spacing = gap between children, padding = gap from the window edge.
        VBox root = new VBox(ROOT_SPACING);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(
                title,
                buildAlbumArt(),
                artist,
                buildControlRow(),
                buildShareRow()
        );

        // Brushed-metal style background typical of 2010s media players
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f4f4f4, #c9c9c9);");

        stage.setTitle("Music Player - HBox and VBox Demo");
        stage.setScene(new Scene(root, 360, 480));
        stage.setMinWidth(320);
        stage.setMinHeight(460);
        stage.show();
    }

    /**
     * Creates a placeholder album cover. A gradient rectangle with a music
     * note is drawn in code so the program needs no external image file.
     *
     * @return a StackPane holding the cover graphic
     */
    private StackPane buildAlbumArt() {
        Rectangle cover = new Rectangle(ART_SIZE, ART_SIZE);
        cover.setArcWidth(12);
        cover.setArcHeight(12);
        cover.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ff5f9e")),
                new Stop(1, Color.web("#5b3cc4"))));

        Label note = new Label("♫");
        note.setStyle("-fx-font-size: 72px; -fx-text-fill: rgba(255,255,255,0.85);");

        // StackPane layers the note on top of the rectangle
        return new StackPane(cover, note);
    }

    /**
     * Builds the playback controls row. Shows HBox spacing and
     * center alignment.
     *
     * @return an HBox with Prev, Play, and Next buttons side by side
     */
    private HBox buildControlRow() {
        Button prev = styledButton("⏮");
        Button play = styledButton("▶");
        Button next = styledButton("⏭");

        // Make Play larger so it stands out, like a real player
        play.setStyle(GLOSSY_BUTTON + "-fx-font-size: 18px; -fx-padding: 8 22 8 22;");

        // Play toggles between play and pause symbols
        play.setOnAction(e -> play.setText(
                play.getText().equals("▶") ? "⏸" : "▶"));

        HBox controls = new HBox(ROW_SPACING, prev, play, next);
        controls.setAlignment(Pos.CENTER);
        return controls;
    }

    /**
     * Builds the share row. Demonstrates HBox grow priority: the empty
     * Region spacer is set to Priority.ALWAYS, so when the window gets
     * wider, the spacer takes all the extra width and keeps Share pinned
     * left while Like and Add stay pinned right.
     *
     * @return an HBox with share actions split across both edges
     */
    private HBox buildShareRow() {
        Button share = styledButton("Share");
        Button like = styledButton("♥ Like");
        Button add = styledButton("+ Add");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox shareRow = new HBox(ROW_SPACING, share, spacer, like, add);
        shareRow.setAlignment(Pos.CENTER_LEFT);
        shareRow.setPadding(new Insets(10, 0, 0, 0));
        return shareRow;
    }

    /**
     * Creates a button with the shared glossy style applied.
     *
     * @param text the button label
     * @return the styled button
     */
    private Button styledButton(String text) {
        Button button = new Button(text);
        button.setStyle(GLOSSY_BUTTON);
        return button;
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
