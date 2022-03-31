package com.example.javaproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

public class EquipmentController {
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

    private int uid;

    public void setUid(int uid) throws IOException, SQLException {
        this.uid = uid;
        System.out.println("Equipment: " + uid);
        loadTable();
    }

    @FXML
    private void goBackToMenu(ActionEvent event) throws IOException, SQLException {
        loadTable();
    }

    private void loadTable() throws SQLException {
        ObservableList<Item> item = DatabaseConnectv2.getItems(uid);
        tableName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        tableValue.setCellValueFactory(new PropertyValueFactory<Item,String>("value"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<Item,String>("weight"));
        tableDescription.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<Item,String>("amount"));
        TableAction.setCellFactory(cellFactory);

        tableView.setItems(item);
    }

    Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory
            = //
            new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                @Override
                public TableCell call(final TableColumn<Item, String> param) {
                    final TableCell<Item, String> cell = new TableCell<Item, String>() {

                        final Button btn = new Button("Delete");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    Item item1 = getTableView().getItems().get(getIndex());
                                    System.out.println(item1);
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