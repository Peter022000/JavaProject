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

    public void setUserData(UserData userData) throws SQLException {
        this.userData = userData;
        shopMenu.getItems().add("Kowal");
        shopMenu.getItems().add("Zaopatrzenie");
        shopMenu.getItems().add("Krawiec");
        setShop(1);
    }

    public void setShop(int sid) throws SQLException {
        this.sid = sid;
        loadTable();
    }

    @FXML
    void shopMenuAction(ActionEvent event) throws IOException, SQLException {
        int selectedIndex = shopMenu.getSelectionModel().getSelectedIndex();
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

        menuController.setUserData(userData);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void loadTable() throws SQLException {
        ObservableList<Item> item = DatabaseConnectv2.getShopItems(sid);
        tableName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        tableValue.setCellValueFactory(new PropertyValueFactory<Item,String>("value"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<Item,String>("weight"));
        tableDescription.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<Item,String>("amount"));
        TableAction.setCellFactory(cellFactory);

        moneyLabel.setText(String.format("%.2f", userData.getMoney()));

        tableView.setItems(item);
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
                                        float money = DatabaseConnectv2.buyItemFromEquipment(sid,userData.getUid(),iid, userData.getMoney());
                                        userData.setMoney(money);
                                        loadTable();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };

}
