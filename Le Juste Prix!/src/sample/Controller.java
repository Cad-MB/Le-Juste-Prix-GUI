package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private Label prix1;

    @FXML
    private Label prix2;

    @FXML
    private Label prix3;

    @FXML
    private TextField prixPlayer;

    @FXML
    private Label comment;

    private String inputPlayer;

    private int reponse = 0;
    private int choixPlayer;
    private int choix1;
    private int choix2;
    private int choix3;
    private int i = 0;
    private int found = 0;
    private int chance = 4;

    private int aleatoire() {
        return (int) (Math.random() * 1000 + 100);

    }


    public void launchGame(ActionEvent event) throws IOException {

        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Stage primaryStage = new Stage();


        primaryStage.setTitle("Le Juste Prix");
        primaryStage.setScene(new Scene(root, 1360, 768));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("sample/images/icon.png"));
        primaryStage.setResizable(true);
        primaryStage.show();

        reponse = aleatoire();
        System.out.println("cheat ----> answer is : " + reponse);
    }

    public void round(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER) {
            if (prixPlayer.getText().matches("[1-9][0-9]*")) {
                inputPlayer = prixPlayer.getText();

                chance--;
                if (chance == 0) {
                    comment.setText("Game Over : no player won");
                    prixPlayer.setEditable(false);}
                else {
                choixPlayer = Integer.parseInt(inputPlayer);
                choix1 = aleatoire();
                prix1.setText("" + choix1);
                choix2 = aleatoire();
                prix2.setText("" + choix2);
                choix3 = aleatoire();
                prix3.setText("" + choix3);


                if (choix1 == reponse) found = 1;
                else if (choix2 == reponse) found = 2;
                else if (choix3 == reponse) found = 3;
                else if (choixPlayer == reponse) found = 4;
                else if (choix1 < reponse) comment.setText("Try again : it's less");
                else if (choix1 > reponse) comment.setText("Try again : it's more");

                    switch (found) {
                        case 1:
                            comment.setText("Game Over : player 1 won");
                            prixPlayer.setEditable(false);
                            break;
                        case 2:
                            comment.setText("Game Over : player 2 won");
                            prixPlayer.setEditable(false);
                            break;
                        case 3:
                            comment.setText("Game Over : player 3 won");
                            prixPlayer.setEditable(false);

                            break;
                        case 4:
                            comment.setText("Congratulation : YOU WON!!!");
                            prixPlayer.setEditable(false);

                            break;
                        default:
                            prixPlayer.setEditable(true);
                            break;
                    }

                }
            }

        }

    }
}
