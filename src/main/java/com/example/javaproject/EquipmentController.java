package com.example.javaproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EquipmentController {
    @FXML
    private BorderPane borderPane;

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

    public void initialize() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("navigation-view.fxml"));
        BorderPane topView = loader.load();
        borderPane.setTop(topView);

        loadTable();
    }

    private void loadTable() throws SQLException {
        ObservableList<Item> item = DatabaseConnectv2.getItems(3);
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