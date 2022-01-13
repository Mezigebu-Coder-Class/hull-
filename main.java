package yyy;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.text.Format;

public class main extends Application {
    public ObservableList<Object> data;
    private TableView table = new TableView();

    public static void main(String[] args) {
        launch(args);
    }
    Connection conn ;
    @Override
    public void start(Stage Stage) throws ClassNotFoundException {
        data = FXCollections.observableArrayList();
           try {
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccl", "root", "");
               if (conn != null) {
                   System.out.println("Conncted");
               }
           }
              catch (Exception e) {
                   System.out.println(e);

               }
               GridPane root = new GridPane();
               Button btn = new Button();
               Button btn2 = new Button("update");
               btn.setText("insert");
               btn.setLineSpacing(40);
              Button btn3  = new Button("specifc display");
               btn3.setLayoutX(20);
               Button btn4 =new Button("distnict ");
               btn4.setLayoutX(15);
               Button btn5 = new Button("display");
               btn5.setLayoutX(15);
        TextField txt = new TextField();
               TextField txt1 = new TextField();
               TextField txt2 = new TextField();
               TextField txt3 = new TextField();
               TextField txt4 = new TextField();
               TextField txt5 = new TextField();

               Label lb = new Label("SID :");
               Label slb = new Label("STUDID :");
               Label flb = new Label("FIRSTNAME :");
               Label llb = new Label("LASTNAME :");
               Label selb = new Label("SECTION :");
               Label delb = new Label("DEPARTMENT :");

               final Label label = new Label("Address Book");
               label.setFont(new Font("Arial", 10));
       table.setEditable(true);
               root.addRow(0, lb, txt);
               root.addRow(1, slb, txt1);
               root.addRow(2, flb, txt2);
               root.addRow(3, llb, txt3);
               root.addRow(4, selb, txt4);
               root.addRow(5, delb, txt5);
               root.addRow(6, btn);
               root.addRow(20,label,table);
               root.addRow(22, btn2);
              root.addRow(9,btn3);
              root.addRow(11,btn4);
              root.addRow(13,btn5);
               Scene scene = new Scene(root, 800, 700);
               Stage.setWidth(600);
               Stage.setHeight(800);
               Stage.setTitle("javafx!");
               Stage.setScene(scene);
               Stage.setScene(scene);
               btn.setOnAction(new EventHandler<ActionEvent>() {

@Override
                   public void handle(ActionEvent event) {
                       String tf1 = txt.getText();
                       String tf2 = txt1.getText();
                       String tf3 = txt2.getText();
                       String tf4 = txt3.getText();
                       String tf5 = txt4.getText();
                       String tf6 = txt5.getText();
                       int st = Integer.parseInt(tf1);
                       try {
                           Class.forName("com.mysql.jdbc.Driver");
                           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccl", "root", "");
                           Statement stm = conn.createStatement();                           
                           // String sql = "INSERT INTO dept_tb1(sid, studid, fristname, lastname, section, dept) VALUES   VALUES(" + tf1 + ",'" + tf2 + "','" + tf3 + "','" + tf4 + "','" + tf5 + "','" + tf6 + "',)";
                           String stat = ("insert into dept_tbl (SID,STUDID,FIRSTNAME,LASTNAME,SECTION,DEPARTMENT) values(" + tf1 + ",'" + tf2 + "','" + tf3 + "','" + tf4 + "','" + tf5 + "','" + tf6 + "')");
                           stm.executeUpdate(stat);
                           System.out.println("secuceful insert ");

                       } catch (Exception e) {
                           System.out.println(e);

                       }
                   }

               });
               
               btn2.setOnAction(new EventHandler<ActionEvent>() {

                   @Override
                   public void handle(ActionEvent event) {
                       try {
                           Class.forName("com.mysql.jdbc.Driver");
                           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccl", "root", "");
                           // Statement stm = conn.createStatement();
                           ResultSet rs;
                           data = FXCollections.observableArrayList();
                           table.setStyle("-fx-background-color:red; -fx-font-color:Green ");
                           String SQL = "SELECT * from dept_tbl";
                           rs = conn.createStatement().executeQuery(SQL);
                           for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                               final int j = i;
                               TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                               col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                               ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                               table.getColumns().addAll(col);
                               System.out.println("Column [" + i + "] ");

                           }

                           while (rs.next()) {
                               ObservableList<String> row = FXCollections.observableArrayList();
                               for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                   row.add(rs.getString(i));
                               }
                               System.out.println("Row[1]added " + row);
                               data.add(row);

                           }


                           table.setItems(data);
                       } catch (Exception e) {
                           e.printStackTrace();
                           System.out.println("Error ");
                       }
                   }
               });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
             private ObservableList<ObservableList> data;
            
             @Override
             public void handle(ActionEvent event)
             {

                 DbConnection obj1;
                 Connection c;
                 ResultSet rs;
                 table.setItems(data);
                 try {
                     data = FXCollections.observableArrayList();
                      obj1 = new DbConnection();
                     c = obj1.connMethod();
                     String SQL = "SELECT * from dept_tbl where FIRSTNAME = 'Elias'";
                     rs = c.createStatement().executeQuery(SQL);
                     for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                         final int j = i;
                         TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                         col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                                                          ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                         table.getColumns().addAll(col);
                        
                     }
          while (rs.next()) {
                         ObservableList<String> row = FXCollections.observableArrayList();
                         for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                             row.add(rs.getString(i));
                         }
                         //System.out.println("Row[1]added " + row);
                         data.add(row);

                     }
                     table.setItems(data);
                 } catch (Exception e) {
                     e.printStackTrace();
                     System.out.println("Error ");
                 }

             }
        }); 
     
btn4.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;

            //private TableView tbl;
            @Override
            public void handle(ActionEvent event) {

                DbConnection db = new DbConnection();
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    // table.setStyle("-fx-background-color:red; -fx-font-color:yellow ");
                    c = db.connMethod();
                    String SQL = "SELECT distinct SECTION from dept_tbl";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        col.setMinWidth(100);
                        table.getColumns().addAll(col);       
                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }

            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                DbConnection db = new DbConnection();
                Connection con = null;
                try {
                    con = db.connMethod();
                    String value = txt2.getText();
                    String value1 = "Aman";
                    String sql = "UPDATE dept_tbl SET FIRSTNAME='" + value + "' WHERE FIRSTNAME='" + value1 + "'";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();

                    a.setContentText("Updated successfuly");
                    a.showAndWait();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });


         btn5.setOnAction(new EventHandler<ActionEvent>() {
             private ObservableList<ObservableList> data;
            
             @Override
             public void handle(ActionEvent event)
             {

                 DbConnection obj1;
                 Connection c;
                 ResultSet rs;
                 table.setItems(data);
                 try {
                     data = FXCollections.observableArrayList();
                      obj1 = new DbConnection();
                     c = obj1.connMethod();
                     String SQL = "SELECT * from dept_tbl";
                     rs = c.createStatement().executeQuery(SQL);
                     for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                         final int j = i;
                         TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                         col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                         ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                         table.getColumns().addAll(col);
                        
                     }
          while (rs.next()) {
                         ObservableList<String> row = FXCollections.observableArrayList();
                         for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                             row.add(rs.getString(i));
                         }
                         data.add(row);

                     }
                     table.setItems(data);
                 } catch (Exception e) {
                     e.printStackTrace();
                     System.out.println("Error ");
                 }

             }
         });

   Stage.show();}
  }

