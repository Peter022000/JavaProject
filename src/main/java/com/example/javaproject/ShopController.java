package com.example.javaproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ShopController {

    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, String> TableAction;

    @FXML
    private TableColumn<Item, String> tableDescription;

    @FXML
    private TableColumn<Item, String> tableName;

    @FXML
    private TableColumn<Item, String> tableValue;

    @FXML
    private TableColumn<Item, String> tableWeight;

    @FXML
    private TableColumn<Item, String> tableAmount;


    @FXML
    private ComboBox<String> shopMenu;

    @FXML
    private Label moneyLabel;

    private int sid;

    private UserData userData;

    @FXML
    private Label loginLabel;

    @FXML
    private TextArea descriptionArea;

    ObservableList<Item> items;

    private DatabaseConnection databaseConnection;

    public void setUserData(UserData userData, DatabaseConnection databaseConnection) throws SQLException {
        this.userData = userData;
        this.databaseConnection = databaseConnection;
        shopMenu.getItems().add("Blacksmith");
        shopMenu.getItems().add("Supplies and tools");
        shopMenu.getItems().add("Arcane shop");
        //Login
        loginLabel.setText(DatabaseConnection.getLogin(userData.getUid()));
        setShop(1);
    }

    public void setShop(int sid) throws SQLException {
        this.sid = sid;
        loadTable();
    }

    @FXML
    void shopMenuAction(ActionEvent event) throws IOException, SQLException {
        int selectedIndex = shopMenu.getSelectionModel().getSelectedIndex();
        descriptionArea.clear();
        if(selectedIndex == 0) {
            this.setShop(1);
        } else if(selectedIndex == 1) {
            this.setShop(2);
        } else if(selectedIndex == 2) {
            this.setShop(3);
        }
    }

    @FXML
    void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();

        MenuController menuController = loader.getController();
        menuController.setUserData(userData, databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void loadTable() throws SQLException {
        items = databaseConnection.getShopItems(sid);
        tableName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        tableValue.setCellValueFactory(new PropertyValueFactory<Item,String>("value"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<Item,String>("weight"));
        tableDescription.setCellFactory(cellFactory2);
        tableAmount.setCellValueFactory(new PropertyValueFactory<Item,String>("amount"));
        TableAction.setCellFactory(cellFactory);

        moneyLabel.setText(String.format("%.2f", userData.getMoney()));

        tableView.setItems(items);
    }

    Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory
            = //
            new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                @Override
                public TableCell call(final TableColumn<Item, String> param) {
                    final TableCell<Item, String> cell = new TableCell<Item, String>() {

                        final Button btn = new Button("Buy");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    int iid = getTableView().getItems().get(getIndex()).getIid();
                                    try {
                                        float money = databaseConnection.buyItemFromShop(sid,userData.getUid(),iid, userData.getMoney());
                                        userData.setMoney(money);
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

    Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory2
            = //
            new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                @Override
                public TableCell call(final TableColumn<Item, String> param) {
                    final TableCell<Item, String> cell = new TableCell<Item, String>() {

                        final Button btn = new Button("Show");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    descriptionArea.setText(getTableView().getItems().get(getIndex()).getDescription());
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
}
