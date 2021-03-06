/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhirpbo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author muhammad yusuf
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField txt_noresi;

    @FXML
    private TextField txt_nama;

    @FXML
    private TextField txt_nohp;

    @FXML
    private TextField txt_alamat;

    @FXML
    private TextField txt_jenis;
    
    @FXML
    private TableView<DataJne> tabel_jne;

    @FXML
    private TableColumn<DataJne, Integer> no_resi;

    @FXML
    private TableColumn<DataJne, String> nama_penerima;

    @FXML
    private TableColumn<DataJne, Integer> no_hp;

    @FXML
    private TableColumn<DataJne, String> alamat;

    @FXML
    private TableColumn<DataJne, String> jenis_barang;

    @FXML
    private Button hapus;

    @FXML
    private Button edit;

    @FXML
    private Button tambah;
 
    ObservableList<DataJne>listM;
    int index = -1;
    Connection konek = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void tambah(){
        
        konek = MySql.ConnectDb();
        String sql = "insert into datajne (noresi,namapenerima,nohp,alamat,jenisbarang)values(?,?,?,?,? )";
        try{
            
            pst = konek.prepareStatement(sql);
            pst.setString(1, txt_noresi.getText());
            pst.setString(2, txt_nama.getText());
            pst.setString(3, txt_nohp.getText());
            pst.setString(4, txt_alamat.getText());
            pst.setString(5, txt_jenis.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            updatedata();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }
    
    public void edit(){
        try{
            konek =MySql.ConnectDb();
            String value1 = txt_noresi.getText();
            String value2 = txt_nama.getText();
            String value3 = txt_nohp.getText();
            String value4 = txt_alamat.getText();
            String value5 = txt_jenis.getText();
            
            String sql = "update datajne set noresi='" +value1+"',namapenerima='" +value2+"',nohp='" +value3+"',alamat='" +value4+"',jenisbarang='" +value5+"' where noresi ='"+value1+"' ";
            pst = konek.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");
            updatedata();
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void hapus(){
    konek = MySql.ConnectDb();
    String sql = "delete from datajne where noresi = ?";
        try {
            pst = konek.prepareStatement(sql);
            pst.setString(1, txt_noresi.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Terhapus");
            updatedata();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    public void updatedata(){
    no_resi.setCellValueFactory(new PropertyValueFactory<DataJne, Integer>("noresi"));
    nama_penerima.setCellValueFactory(new PropertyValueFactory<DataJne, String>("namapenerima"));
    no_hp.setCellValueFactory(new PropertyValueFactory<DataJne, Integer>("nohp"));
    alamat.setCellValueFactory(new PropertyValueFactory<DataJne, String>("alamat"));
    jenis_barang.setCellValueFactory(new PropertyValueFactory<DataJne, String>("jenisbarang"));
    
    listM=MySql.getDataJne();
    tabel_jne.setItems(listM);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    updatedata();
    }

}
