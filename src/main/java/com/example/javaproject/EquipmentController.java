package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EquipmentController {
    @FXML
    private BorderPane borderPane;

    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("navigation-view.fxml"));
        BorderPane topView = loader.load();

        borderPane.setTop(topView);
    }
<<<<<<< Updated upstream
=======

    @FXML
    private void goBackToMenu(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();

        MenuController menuController = loader.getController();

        menuController.setUserData(userData);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadTable() throws SQLException {
        ObservableList<Item> item = DatabaseConnectv2.getItems(userData.getUid());
        tableName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        tableValue.setCellValueFactory(new PropertyValueFactory<Item,String>("value"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<Item,String>("weight"));
        tableDescription.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<Item,String>("amount"));
        TableAction.setCellFactory(cellFactory);

        moneyLabel.setText(String.format("%.2f", userData.getMoney()));

        tableView.setItems(item);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory
            = //
            new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                @Override
                public TableCell call(final TableColumn<Item, String> param) {
                    final TableCell<Item, String> cell = new TableCell<Item, String>() {

                        final Button btn = new Button("Discard");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);//"-fx-background-color:" + colorString + "; -fx-fill:" + colorString+";"
                            } else {
                                btn.setOnAction(event -> {
                                    int iid = getTableView().getItems().get(getIndex()).getIid();
                                    try {
                                        DatabaseConnectv2.deleteItemFromEquipment(userData.getUid(),iid);
                                        loadTable();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });
                                setGraphic(btn);
                                setText(null);
                                btn.setStyle("-fx-background-color:\n" +
                                        "                        #750a0e,\n" +
                                        "                        linear-gradient(#ec2127, #bc1016);\n" +
                                        "    -fx-text-fill: white;\n" +
                                        "    -fx-background-radius: 10; -fx-font-size: 10;");
                            }
                        }
                    };
                    return cell;
                }
            };
>>>>>>> Stashed changes
}