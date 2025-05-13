/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.ChiTietSanPhamBUS;
import BUS.HoaDonBUS;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import DTO.ThongKe.ThongKeKhachHangDTO;
//import BUS.PhieuNhapBUS;
//import DTO.PhieuNhapDTO;
//import BUS.SanPhamBUS;
//import DTO.HoaDonDTO;
//import DTO.SanPhamDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import DAO.*;
import BUS.*;
import DTO.*;
import DTO.ThongKe.*;
import java.io.FileOutputStream;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.List;
import javax.swing.JFrame;






/**
 *
 * @author MSI Gaming
 */
public class Main extends javax.swing.JFrame {
    private PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
    private SanPhamBUS sanphamBUS = new SanPhamBUS();
    private JDateChooser dateChooser1;
    private JDateChooser dateChooser2;
    private JDateChooser dateChooser3;
    private JDateChooser dateChooser4;
    private JDateChooser dateChooser5;
    private JDateChooser dateChooser6;
    private JDateChooser dateChooser7;
    private JDateChooser dateChooser8;
    private HoaDonBUS hoadonBUS = new HoaDonBUS();
    private SanPhamBUS spBUS = new SanPhamBUS();
   
    /**
     * Creates new form Main
     */
    public Main() {
        dateChooser1 = new JDateChooser();
        dateChooser2 = new JDateChooser();
        dateChooser1.setDateFormatString("yyyy/MM/dd");
        dateChooser2.setDateFormatString("yyyy/MM/dd");
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2025, Calendar.JANUARY, 1);
        dateChooser1.setDate(cal1.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2025, Calendar.DECEMBER, 31);
        dateChooser2.setDate(cal2.getTime());
        
        dateChooser3 = new JDateChooser();
        dateChooser4 = new JDateChooser();
        dateChooser3.setDateFormatString("yyyy/MM/dd");
        dateChooser4.setDateFormatString("yyyy/MM/dd");
        Calendar cal3 = Calendar.getInstance();
        cal1.set(2025, Calendar.JANUARY, 1);
        dateChooser3.setDate(cal3.getTime());
        Calendar cal4 = Calendar.getInstance();
        cal2.set(2025, Calendar.DECEMBER, 31);
        dateChooser4.setDate(cal4.getTime());
        
        dateChooser5 = new JDateChooser();
        dateChooser6 = new JDateChooser();
        dateChooser5.setDateFormatString("yyyy/MM/dd");
        dateChooser6.setDateFormatString("yyyy/MM/dd");
        Calendar cal5 = Calendar.getInstance();
        cal5.set(2025, Calendar.JANUARY, 1);
        dateChooser5.setDate(cal5.getTime());
        Calendar cal6 = Calendar.getInstance();
        cal6.set(2025, Calendar.DECEMBER, 31);
        dateChooser6.setDate(cal6.getTime());
        
        dateChooser7 = new JDateChooser();
        dateChooser8 = new JDateChooser();
        dateChooser7.setDateFormatString("yyyy/MM/dd");
        dateChooser8.setDateFormatString("yyyy/MM/dd");
        Calendar cal7 = Calendar.getInstance();
        cal7.set(2025, Calendar.JANUARY, 1);
        dateChooser7.setDate(cal7.getTime());
        Calendar cal8 = Calendar.getInstance();
        cal8.set(2025, Calendar.DECEMBER, 31);
        dateChooser8.setDate(cal8.getTime());
        
        initComponents(); 
        this.setSize(1400, 800); // Đặt kích thước cố định
        this.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        this.setResizable(true); // Ngăn người dùng thay đổi kích thước cửa sổ
        loadSanPhamToTable();
        loadNhanVienToTable();
        loadNhaCungCapToTable();
        loadKhachHangToTable();
        loadDuLieuToTable();
        
        
        
        
  txtTimKiem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        timKiemSanPham(); // Gọi khi nhấn Enter
    }

    private void timKiemSanPham() {
        String text = txtTimKiem.getText().trim().toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();

        for (SanPhamDTO sp : spBUS.getAll()) {
            String tenSP = sp.getTenSanPham().toLowerCase();

            if (tenSP.contains(text)) {
                result.add(sp); // Chỉ thêm nếu khớp TÊN
            }
        }

        // Cập nhật lại bảng
        DefaultTableModel model = (DefaultTableModel) JTableSanPham.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        for (SanPhamDTO sp : result) {  
            model.addRow(new Object[]{
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getSoLuong(),
                sp.getDonGia(),
                sp.getDonViTinh(),
                sp.getMaLoai()
            });
        }
    }
});


        
        panelContent.setLayout(new CardLayout()); 
        panelContent.add(pTrangChu, "TrangChu");
        panelContent.add(pSanPham, "SanPham");
        panelContent.add(pPhieuNhap, "PhieuNhap");
        panelContent.add(pPhieuXuat, "PhieuXuat");
        panelContent.add(pHoaDon, "HoaDon");
        panelContent.add(pKhachHang, "KhachHang");
        panelContent.add(pNhaCungCap, "NhaCungCap");
        panelContent.add(pNhanVien, "NhanVien");
        panelContent.add(pThongKe, "ThongKe");
        panelContent.add(pThuocTinh, "ThuocTinh");
        
      
        jPanel19.setLayout(new CardLayout()); 
        jPanel19.add(panelTongQuan, "TongQuan");
        jPanel19.add(panelDoanThu, "DoanhThu");
        jPanel19.add(panelKH, "TKKhachHang");
        
        jPanel21.setLayout(new CardLayout());
        jPanel21.add(panelTheoNam, "TheoNam");
        jPanel21.add(panelTungThang, "TungThang");
        jPanel21.add(panelTungNgay, "TungNgay");
        jPanel21.add(panelTuNgay, "TuNgay");
        
        JTableSanPham.getTableHeader().setForeground(Color.BLACK);      // Đổi màu chữ
        JTableSanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Đổi font chữ
        JTableSanPham.getColumnModel().getColumn(0).setPreferredWidth(40); // Cột đầu tiên rộng 150px
        JTableSanPham.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        JTablePhieuNhap.getTableHeader().setForeground(Color.BLACK);      // Đổi màu chữ
        JTablePhieuNhap.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Đổi font chữ
        JTablePhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(40); // Cột đầu tiên rộng 150px
        JTablePhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        jTableHoaDon.getTableHeader().setForeground(Color.BLACK);      // Đổi màu chữ
        jTableHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Đổi font chữ
        jTableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(40); // Cột đầu tiên rộng 150px
        jTableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(100);
      
        jPanel7.add(dateChooser1);
        jPanel8.add(dateChooser2);
        jPanel10.add(dateChooser3);
        jPanel17.add(dateChooser4);
        tungay.add(dateChooser5);
        denngay.add(dateChooser6);
        ngaybd.add(dateChooser7);
        ngaykt.add(dateChooser8);
        jPanel7.setLayout(new FlowLayout());  
        jPanel8.setLayout(new FlowLayout());
        jPanel10.setLayout(new FlowLayout());  
        jPanel17.setLayout(new FlowLayout());
        tungay.setLayout(new FlowLayout());  
        denngay.setLayout(new FlowLayout());
        ngaybd.setLayout(new FlowLayout());  
        ngaykt.setLayout(new FlowLayout());
        dateChooser1.setPreferredSize(new java.awt.Dimension(210, 30));  
        dateChooser2.setPreferredSize(new java.awt.Dimension(210, 30));
        dateChooser3.setPreferredSize(new java.awt.Dimension(210, 30));  
        dateChooser4.setPreferredSize(new java.awt.Dimension(210, 30));
        dateChooser5.setPreferredSize(new java.awt.Dimension(210, 30));  
        dateChooser6.setPreferredSize(new java.awt.Dimension(210, 30));
        dateChooser7.setPreferredSize(new java.awt.Dimension(210, 30));  
        dateChooser8.setPreferredSize(new java.awt.Dimension(210, 30));
        jTextFieldTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndUpdateTable();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdateTable();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdateTable();
                
            }
        });
        jTextFieldTimKiem2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndUpdateTable2();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdateTable2();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdateTable2();
                
            }
        });
       }
    
    
    public void exportSanPhamToExcel(JTable table) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Danh sách sản phẩm");

    TableModel model = table.getModel();

    // Ghi header
    Row header = sheet.createRow(0);
    for (int i = 0; i < model.getColumnCount(); i++) {
        header.createCell(i).setCellValue(model.getColumnName(i));
    }

    // Ghi dữ liệu từng dòng
    for (int i = 0; i < model.getRowCount(); i++) {
        Row row = sheet.createRow(i + 1);
        for (int j = 0; j < model.getColumnCount(); j++) {
            row.createCell(j).setCellValue(String.valueOf(model.getValueAt(i, j)));
        }
    }

    // Hiển thị hộp thoại lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        // Tự động thêm .xlsx nếu người dùng không nhập
        if (!file.getAbsolutePath().toLowerCase().endsWith(".xlsx")) {
            file = new File(file.getAbsolutePath() + ".xlsx");
        }

        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            workbook.close();
            JOptionPane.showMessageDialog(this, "Đã xuất file Excel thành công:\n" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi ghi file Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
   
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel18 = new javax.swing.JPanel();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTimKiemKH1 = new javax.swing.JTextField();
        panelName = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnPhieuNhap = new javax.swing.JButton();
        btnNhaCungCap = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnThuocTinh1 = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        pSanPham = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonThemSanPham = new javax.swing.JButton();
        jButtonSuaSanPham = new javax.swing.JButton();
        jButtonXoaSanPham = new javax.swing.JButton();
        jButtonChiTietSanPham = new javax.swing.JButton();
        jButtonXuatSanPham = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        Buttonrefesh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        JTableSanPham = new javax.swing.JTable();
        pThuocTinh = new javax.swing.JPanel();
        jButtonHang = new javax.swing.JButton();
        jButtonLoai = new javax.swing.JButton();
        jLabelHang = new javax.swing.JLabel();
        jLabelLoai = new javax.swing.JLabel();
        jButtonKhuyenMai = new javax.swing.JButton();
        jLabelLoai1 = new javax.swing.JLabel();
        pPhieuNhap = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton37 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTimKiem = new javax.swing.JTextField();
        Buttonrefesh6 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTablePhieuNhap = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        pPhieuXuat = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jButton55 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        pHoaDon = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton56 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTimKiem2 = new javax.swing.JTextField();
        Buttonrefesh7 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableHoaDon = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pKhachHang = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton68 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTimKiemKH = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        JTableKhachHang = new javax.swing.JTable();
        pNhaCungCap = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtTimKiemNCC = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        JTableNhaCungCap = new javax.swing.JTable();
        pNhanVien = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton77 = new javax.swing.JButton();
        jButton78 = new javax.swing.JButton();
        jButton79 = new javax.swing.JButton();
        jButton82 = new javax.swing.JButton();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtTimKiemNV = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        JTableNhanVien = new javax.swing.JTable();
        pThongKe = new javax.swing.JPanel();
        btnTongQuan = new javax.swing.JButton();
        btnDoanhThu = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        panelTongQuan = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableThongKe = new javax.swing.JTable();
        panelDoanThu = new javax.swing.JPanel();
        btnTheoNam = new javax.swing.JButton();
        btnTungThang = new javax.swing.JButton();
        btnTungNgay = new javax.swing.JButton();
        btnTuNgay = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        panelTheoNam = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTuNam = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtDenNam = new javax.swing.JTextField();
        btnTKTheoNam = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableTheoNam = new javax.swing.JTable();
        panelTungThang = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableTungThang = new javax.swing.JTable();
        txtTungThang = new javax.swing.JTextField();
        btnTKTungThang = new javax.swing.JButton();
        panelTungNgay = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        chonthang = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        chonnam = new javax.swing.JSpinner();
        btnTKTungNgay = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tableTungNgay = new javax.swing.JTable();
        panelTuNgay = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        ngaybd = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        ngaykt = new javax.swing.JPanel();
        btnTKTuNgay = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableTuNgay = new javax.swing.JTable();
        panelKH = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtTimKiemKH2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        tungay = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        denngay = new javax.swing.JPanel();
        btnLamMoi2 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        pTrangChu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jButton66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });

        jButton67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-50.png"))); // NOI18N
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });

        jButton69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });

        jButton73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(51, 51, 255));
        jLabel91.setText("SỬA");

        jLabel94.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(51, 51, 255));
        jLabel94.setText("XÓA");

        jLabel98.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(51, 51, 255));
        jLabel98.setText("XUẤT");

        jLabel99.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(51, 51, 255));
        jLabel99.setText("THÊM");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setText("Tìm Kiếm:");

        txtTimKiemKH1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTimKiemKH1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKH1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel98)
                        .addContainerGap(991, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton73)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtTimKiemKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel90)
                        .addComponent(jLabel94)
                        .addComponent(jLabel91)
                        .addComponent(jLabel98))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lí");
        setMinimumSize(new java.awt.Dimension(1200, 700));

        panelName.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-admin-36.png"))); // NOI18N
        jLabel3.setText("Admin");

        javax.swing.GroupLayout panelNameLayout = new javax.swing.GroupLayout(panelName);
        panelName.setLayout(panelNameLayout);
        panelNameLayout.setHorizontalGroup(
            panelNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNameLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNameLayout.setVerticalGroup(
            panelNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        btnHome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-home-26.png"))); // NOI18N
        btnHome.setText("Trang chủ");
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHome.setIconTextGap(10);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-order-26.png"))); // NOI18N
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHoaDon.setIconTextGap(10);
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnPhieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-create-order-26.png"))); // NOI18N
        btnPhieuNhap.setText("Phiếu nhập ");
        btnPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPhieuNhap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPhieuNhap.setIconTextGap(10);
        btnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapActionPerformed(evt);
            }
        });

        btnNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-supplier-26.png"))); // NOI18N
        btnNhaCungCap.setText("Nhà Cung cấp");
        btnNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhaCungCap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhaCungCap.setIconTextGap(10);
        btnNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCapActionPerformed(evt);
            }
        });

        btnSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-smartphones-26.png"))); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSanPham.setIconTextGap(10);
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-customer-26.png"))); // NOI18N
        btnKhachHang.setText("Khách hàng ");
        btnKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnKhachHang.setIconTextGap(10);
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-employee-26.png"))); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhanVien.setIconTextGap(10);
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-statistics-26.png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThongKe.setIconTextGap(10);
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-power-off-26.png"))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExit.setIconTextGap(10);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnThuocTinh1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThuocTinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-product-26.png"))); // NOI18N
        btnThuocTinh1.setText("Thuộc tính");
        btnThuocTinh1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThuocTinh1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThuocTinh1.setIconTextGap(10);
        btnThuocTinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuocTinh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThuocTinh1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThuocTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );

        panelContent.setBackground(new java.awt.Color(255, 153, 204));
        panelContent.setLayout(new java.awt.CardLayout());

        pSanPham.setBackground(new java.awt.Color(204, 255, 255));
        pSanPham.setName("SanPham"); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButtonThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButtonThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemSanPhamActionPerformed(evt);
            }
        });

        jButtonSuaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-50.png"))); // NOI18N
        jButtonSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaSanPhamActionPerformed(evt);
            }
        });

        jButtonXoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButtonXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaSanPhamActionPerformed(evt);
            }
        });

        jButtonChiTietSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-detail-50.png"))); // NOI18N
        jButtonChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChiTietSanPhamActionPerformed(evt);
            }
        });

        jButtonXuatSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButtonXuatSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXuatSanPhamActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("SỬA");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 255));
        jLabel18.setText("XÓA");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        jLabel19.setText("CHI TIẾT");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 255));
        jLabel21.setText("XUẤT ");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 255));
        jLabel22.setText("THÊM");

        Buttonrefesh.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Buttonrefesh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-30.png"))); // NOI18N
        Buttonrefesh.setText("  Làm mới");
        Buttonrefesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonrefeshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel17))
                            .addComponent(jButtonSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonXuatSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Buttonrefesh)
                        .addGap(100, 100, 100))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonSuaSanPham)
                        .addComponent(jButtonThemSanPham)
                        .addComponent(jButtonXoaSanPham)
                        .addComponent(jButtonChiTietSanPham)
                        .addComponent(jButtonXuatSanPham))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Buttonrefesh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        JTableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP ", "Tên sản phẩm", "Số Lượng", "Đơn Giá", "Đơn Vị Tính", "Mã Loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTableSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTableSanPham.setGridColor(new java.awt.Color(0, 0, 0));
        JTableSanPham.setRowHeight(30);
        JTableSanPham.setSelectionBackground(new java.awt.Color(51, 51, 255));
        JTableSanPham.setSelectionForeground(new java.awt.Color(255, 255, 255));
        JTableSanPham.setShowGrid(true);
        JTableSanPham.getTableHeader().setResizingAllowed(false);
        JTableSanPham.getTableHeader().setReorderingAllowed(false);
        JTableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(JTableSanPham);

        pThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        pThuocTinh.setName("ThuocTinh"); // NOI18N

        jButtonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chan-dung-may-tinh-bang-laptop-dien-thoai-up-xep-gon-va-dung-nhieu-goc-do_bengovn (1).jpg"))); // NOI18N
        jButtonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHangActionPerformed(evt);
            }
        });

        jButtonLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mau-logo-hang-dien-thoai(1) (1).jpg"))); // NOI18N
        jButtonLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoaiActionPerformed(evt);
            }
        });

        jLabelHang.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabelHang.setText("Hãng");

        jLabelLoai.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabelLoai.setText("CHƯƠNG TRÌNH KHUYẾN MÃI");

        jButtonKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/KHUYEN-MAI.png"))); // NOI18N
        jButtonKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKhuyenMaiActionPerformed(evt);
            }
        });

        jLabelLoai1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabelLoai1.setText(" Loại");

        javax.swing.GroupLayout pThuocTinhLayout = new javax.swing.GroupLayout(pThuocTinh);
        pThuocTinh.setLayout(pThuocTinhLayout);
        pThuocTinhLayout.setHorizontalGroup(
            pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThuocTinhLayout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabelHang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 974, Short.MAX_VALUE)
                .addComponent(jButtonKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(427, 427, 427))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThuocTinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThuocTinhLayout.createSequentialGroup()
                        .addComponent(jLabelLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(313, 313, 313))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThuocTinhLayout.createSequentialGroup()
                        .addComponent(jButtonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jButtonLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
            .addGroup(pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThuocTinhLayout.createSequentialGroup()
                    .addContainerGap(1737, Short.MAX_VALUE)
                    .addComponent(jLabelLoai1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(238, 238, 238)))
        );
        pThuocTinhLayout.setVerticalGroup(
            pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThuocTinhLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThuocTinhLayout.createSequentialGroup()
                        .addComponent(jLabelHang)
                        .addGap(174, 174, 174))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThuocTinhLayout.createSequentialGroup()
                        .addComponent(jButtonKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabelLoai)
                .addContainerGap(383, Short.MAX_VALUE))
            .addGroup(pThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pThuocTinhLayout.createSequentialGroup()
                    .addGap(489, 489, 489)
                    .addComponent(jLabelLoai1)
                    .addContainerGap(575, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pSanPhamLayout = new javax.swing.GroupLayout(pSanPham);
        pSanPham.setLayout(pSanPhamLayout);
        pSanPhamLayout.setHorizontalGroup(
            pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pSanPhamLayout.setVerticalGroup(
            pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContent.add(pSanPham, "card3");

        pPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        pPhieuNhap.setName("PhieuNhap"); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-detail-50.png"))); // NOI18N
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 255));
        jLabel60.setText("XÓA");

        jLabel61.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 255));
        jLabel61.setText("CHI TIẾT");

        jLabel63.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 255));
        jLabel63.setText("XUẤT");

        jLabel64.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 255));
        jLabel64.setText("THÊM");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        jTextFieldTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimKiemActionPerformed(evt);
            }
        });

        Buttonrefesh6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Buttonrefesh6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-30.png"))); // NOI18N
        Buttonrefesh6.setText("  Làm mới");
        Buttonrefesh6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonrefesh6ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo Mã Phiếu", "Theo Mã Nhân Viên", "Theo Mã Nhà Cung Cấp" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel63)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Buttonrefesh6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton42)
                        .addComponent(jButton40)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(Buttonrefesh6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64)
                        .addComponent(jLabel60)
                        .addComponent(jLabel61)
                        .addComponent(jLabel63))
                    .addComponent(jLabel58)))
        );

        JTablePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MaPhieuNhap", "MaNhanVien", "MaNhaCungCap", "NgayNhap", "TongTien"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTablePhieuNhap.setRowHeight(40);
        jScrollPane4.setViewportView(JTablePhieuNhap);
        JTablePhieuNhap.setDefaultEditor(Object.class, null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Từ Ngày:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Đến ngày:");

        dateChooser1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Kiểm tra nếu có sự thay đổi ngày
                if (evt.getPropertyName().equals("date")) {
                    Date startDate = dateChooser1.getDate();
                    Date endDate = dateChooser2.getDate();

                    if (startDate != null && endDate != null) {
                        // Cập nhật bảng với khoảng thời gian đã chọn
                        updateTableWithDateRange(startDate, endDate);
                    }
                }
            }
        });
        dateChooser2.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    Date startDate = dateChooser1.getDate();
                    Date endDate = dateChooser2.getDate();

                    if (startDate != null && endDate != null) {
                        // Cập nhật bảng với khoảng thời gian đã chọn
                        updateTableWithDateRange(startDate, endDate);
                    }
                }
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1037, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pPhieuNhapLayout = new javax.swing.GroupLayout(pPhieuNhap);
        pPhieuNhap.setLayout(pPhieuNhapLayout);
        pPhieuNhapLayout.setHorizontalGroup(
            pPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pPhieuNhapLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pPhieuNhapLayout.setVerticalGroup(
            pPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPhieuNhapLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pPhieuNhapLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(322, Short.MAX_VALUE))
        );

        panelContent.add(pPhieuNhap, "card4");

        pPhieuXuat.setBackground(new java.awt.Color(255, 255, 255));
        pPhieuXuat.setName("PhieuXuat"); // NOI18N

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-50.png"))); // NOI18N
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-detail-50.png"))); // NOI18N

        jButton54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 255));
        jLabel72.setText("SỬA");

        jLabel73.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 255));
        jLabel73.setText("XÓA");

        jLabel74.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 255));
        jLabel74.setText("CHI TIẾT");

        jLabel76.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 255));
        jLabel76.setText("XUẤT");

        jLabel77.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 255));
        jLabel77.setText("THÊM");

        jButton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-input-excel-50.png"))); // NOI18N
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 51, 255));
        jLabel78.setText("NHẬP");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel78))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1910, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton54)
                    .addComponent(jButton52)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel71)
                        .addComponent(jLabel73)
                        .addComponent(jLabel72)
                        .addComponent(jLabel74)
                        .addComponent(jLabel78)
                        .addComponent(jLabel76))))
        );

        javax.swing.GroupLayout pPhieuXuatLayout = new javax.swing.GroupLayout(pPhieuXuat);
        pPhieuXuat.setLayout(pPhieuXuatLayout);
        pPhieuXuatLayout.setHorizontalGroup(
            pPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPhieuXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pPhieuXuatLayout.setVerticalGroup(
            pPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPhieuXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1011, Short.MAX_VALUE))
        );

        panelContent.add(pPhieuXuat, "card5");

        pHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pHoaDon.setName("HoaDon"); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jButton56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jButton58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-detail-50.png"))); // NOI18N
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        jButton61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 255));
        jLabel81.setText("XÓA");

        jLabel82.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 255));
        jLabel82.setText("CHI TIẾT");

        jLabel84.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 255));
        jLabel84.setText("XUẤT");

        jLabel85.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 255));
        jLabel85.setText("THÊM");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Tìm Kiếm:");

        jTextFieldTimKiem2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        Buttonrefesh7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Buttonrefesh7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-30.png"))); // NOI18N
        Buttonrefesh7.setText("  Làm mới");
        Buttonrefesh7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonrefesh7ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Theo Mã Hóa Đơn", "Theo Mã Nhân Viên", "Theo Mã Khách Hàng" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel84)
                        .addContainerGap(2018, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Buttonrefesh7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Buttonrefesh7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton61)
                            .addComponent(jButton59)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel85)
                        .addComponent(jLabel81)
                        .addComponent(jLabel82)
                        .addComponent(jLabel84))
                    .addComponent(jLabel79)))
        );

        jTableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Ngày Lập HĐ", "Mã Nhân Viên", "Mã Khách Hang", "Tổng Tiền", "Phải Trả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableHoaDon.setRowHeight(40);
        jScrollPane7.setViewportView(jTableHoaDon);
        jTableHoaDon.setDefaultEditor(Object.class, null);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Từ Ngày:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Đến Ngày:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        dateChooser3.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Kiểm tra nếu có sự thay đổi ngày
                if (evt.getPropertyName().equals("date")) {
                    Date startDate = dateChooser3.getDate();
                    Date endDate = dateChooser4.getDate();

                    if (startDate != null && endDate != null) {
                        // Cập nhật bảng với khoảng thời gian đã chọn
                        updateTableWithDateRange2(startDate, endDate);
                    }
                }
            }
        });
        dateChooser4.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    Date startDate = dateChooser3.getDate();
                    Date endDate = dateChooser4.getDate();

                    if (startDate != null && endDate != null) {
                        // Cập nhật bảng với khoảng thời gian đã chọn
                        updateTableWithDateRange2(startDate, endDate);
                    }
                }
            }
        });

        javax.swing.GroupLayout pHoaDonLayout = new javax.swing.GroupLayout(pHoaDon);
        pHoaDon.setLayout(pHoaDonLayout);
        pHoaDonLayout.setHorizontalGroup(
            pHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pHoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7)))
                .addContainerGap())
        );
        pHoaDonLayout.setVerticalGroup(
            pHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(325, Short.MAX_VALUE))
        );

        panelContent.add(pHoaDon, "card6");

        pKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pKhachHang.setName("KhachHang"); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jButton63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });

        jButton64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-50.png"))); // NOI18N
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });

        jButton65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });

        jButton68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton68ActionPerformed(evt);
            }
        });

        jLabel88.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 255));
        jLabel88.setText("SỬA");

        jLabel89.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 255));
        jLabel89.setText("XÓA");

        jLabel92.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(51, 51, 255));
        jLabel92.setText("XUẤT");

        jLabel93.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(51, 51, 255));
        jLabel93.setText("THÊM");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setText("Tìm Kiếm:");

        txtTimKiemKH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKHActionPerformed(evt);
            }
        });
        txtTimKiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKHKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addContainerGap(2022, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton68)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel93)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel87)
                        .addComponent(jLabel89)
                        .addComponent(jLabel88)
                        .addComponent(jLabel92))))
        );

        JTableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã ", "Họ tên", "Địa chỉ", "SDT", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTableKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTableKhachHang.setGridColor(new java.awt.Color(0, 0, 0));
        JTableKhachHang.setRowHeight(30);
        JTableKhachHang.setSelectionBackground(new java.awt.Color(51, 51, 255));
        JTableKhachHang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        JTableKhachHang.setShowGrid(true);
        JTableKhachHang.getTableHeader().setResizingAllowed(false);
        JTableKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(JTableKhachHang);

        javax.swing.GroupLayout pKhachHangLayout = new javax.swing.GroupLayout(pKhachHang);
        pKhachHang.setLayout(pKhachHangLayout);
        pKhachHangLayout.setHorizontalGroup(
            pKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 2248, Short.MAX_VALUE))
                .addContainerGap())
        );
        pKhachHangLayout.setVerticalGroup(
            pKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
        );

        panelContent.add(pKhachHang, "card7");

        pNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        pNhaCungCap.setName("NhaCungCap"); // NOI18N

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jButton70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton70ActionPerformed(evt);
            }
        });

        jButton71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-50.png"))); // NOI18N
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });

        jButton72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });

        jButton75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton75ActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(51, 51, 255));
        jLabel96.setText("SỬA");

        jLabel97.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(51, 51, 255));
        jLabel97.setText("XÓA");

        jLabel100.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 51, 255));
        jLabel100.setText("XUẤT");

        jLabel101.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(51, 51, 255));
        jLabel101.setText("THÊM");

        txtTimKiemNCC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTimKiemNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemNCCKeyPressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setText("Tìm Kiếm:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addContainerGap(2028, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton75, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton75)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel101)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel95)
                        .addComponent(jLabel97)
                        .addComponent(jLabel96)
                        .addComponent(jLabel100))))
        );

        JTableNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên", "Địa chỉ", "SDT", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTableNhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTableNhaCungCap.setGridColor(new java.awt.Color(0, 0, 0));
        JTableNhaCungCap.setRowHeight(30);
        JTableNhaCungCap.setSelectionBackground(new java.awt.Color(51, 51, 255));
        JTableNhaCungCap.setSelectionForeground(new java.awt.Color(255, 255, 255));
        JTableNhaCungCap.setShowGrid(true);
        JTableNhaCungCap.getTableHeader().setResizingAllowed(false);
        JTableNhaCungCap.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(JTableNhaCungCap);

        javax.swing.GroupLayout pNhaCungCapLayout = new javax.swing.GroupLayout(pNhaCungCap);
        pNhaCungCap.setLayout(pNhaCungCapLayout);
        pNhaCungCapLayout.setHorizontalGroup(
            pNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 2248, Short.MAX_VALUE))
                .addContainerGap())
        );
        pNhaCungCapLayout.setVerticalGroup(
            pNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(455, Short.MAX_VALUE))
        );

        panelContent.add(pNhaCungCap, "card8");

        pNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pNhanVien.setName("NhanVien"); // NOI18N

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jButton77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-50.png"))); // NOI18N
        jButton77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton77ActionPerformed(evt);
            }
        });

        jButton78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-50.png"))); // NOI18N
        jButton78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton78ActionPerformed(evt);
            }
        });

        jButton79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bin-50.png"))); // NOI18N
        jButton79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton79ActionPerformed(evt);
            }
        });

        jButton82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-50.png"))); // NOI18N
        jButton82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton82ActionPerformed(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(51, 51, 255));
        jLabel104.setText("SỬA");

        jLabel105.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(51, 51, 255));
        jLabel105.setText("XÓA");

        jLabel108.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(51, 51, 255));
        jLabel108.setText("XUẤT");

        jLabel109.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(51, 51, 255));
        jLabel109.setText("THÊM");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel27.setText("Tìm Kiếm:");

        txtTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemNVActionPerformed(evt);
            }
        });
        txtTimKiemNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemNVKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton77, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton78, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton79, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel105)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel108)
                        .addContainerGap(2022, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton82, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton82)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel109)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel103)
                        .addComponent(jLabel105)
                        .addComponent(jLabel104)
                        .addComponent(jLabel108))))
        );

        JTableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã ", "Họ tên", "Chức vụ", "Ngày vào làm", "Lương"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTableNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTableNhanVien.setGridColor(new java.awt.Color(0, 0, 0));
        JTableNhanVien.setRowHeight(30);
        JTableNhanVien.setSelectionBackground(new java.awt.Color(51, 51, 255));
        JTableNhanVien.setSelectionForeground(new java.awt.Color(255, 255, 255));
        JTableNhanVien.setShowGrid(true);
        JTableNhanVien.getTableHeader().setResizingAllowed(false);
        JTableNhanVien.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(JTableNhanVien);

        javax.swing.GroupLayout pNhanVienLayout = new javax.swing.GroupLayout(pNhanVien);
        pNhanVien.setLayout(pNhanVienLayout);
        pNhanVienLayout.setHorizontalGroup(
            pNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 2260, Short.MAX_VALUE)
        );
        pNhanVienLayout.setVerticalGroup(
            pNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
        );

        panelContent.add(pNhanVien, "card9");

        pThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pThongKe.setName("ThongKe"); // NOI18N

        btnTongQuan.setText("Tổng quan");
        btnTongQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongQuanActionPerformed(evt);
            }
        });

        btnDoanhThu.setText("Doanh Thu");
        btnDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuActionPerformed(evt);
            }
        });

        jButton1.setText("Khách Hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel19.setLayout(new java.awt.CardLayout());

        tableThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày", "Vốn ", "Doanh Thu", "Lợi Nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Float.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tableThongKe);

        javax.swing.GroupLayout panelTongQuanLayout = new javax.swing.GroupLayout(panelTongQuan);
        panelTongQuan.setLayout(panelTongQuanLayout);
        panelTongQuanLayout.setHorizontalGroup(
            panelTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongQuanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(801, Short.MAX_VALUE))
        );
        panelTongQuanLayout.setVerticalGroup(
            panelTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongQuanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel19.add(panelTongQuan, "card2");

        btnTheoNam.setText("Thống kê theo năm");
        btnTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTheoNamActionPerformed(evt);
            }
        });

        btnTungThang.setText("Thống kê từng tháng trong năm");
        btnTungThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTungThangActionPerformed(evt);
            }
        });

        btnTungNgay.setText("Thống kê từng ngày trong tháng");
        btnTungNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTungNgayActionPerformed(evt);
            }
        });

        btnTuNgay.setText("Thống kê từ ngày đến ngày");
        btnTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTuNgayActionPerformed(evt);
            }
        });

        jPanel21.setLayout(new java.awt.CardLayout());

        jLabel8.setText("Từ năm ");

        txtTuNam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setText("Đến năm ");

        txtDenNam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTKTheoNam.setText("Thống kê");
        btnTKTheoNam.setActionCommand("");
        btnTKTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKTheoNamActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        tableTheoNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Năm", "Vốn", "Doanh thu", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tableTheoNam);

        javax.swing.GroupLayout panelTheoNamLayout = new javax.swing.GroupLayout(panelTheoNam);
        panelTheoNam.setLayout(panelTheoNamLayout);
        panelTheoNamLayout.setHorizontalGroup(
            panelTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTheoNamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addGroup(panelTheoNamLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTuNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDenNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTKTheoNam)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi)
                        .addGap(0, 331, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTheoNamLayout.setVerticalGroup(
            panelTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTheoNamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtDenNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTKTheoNam)
                    .addComponent(btnLamMoi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.add(panelTheoNam, "card2");

        jLabel26.setText("Nhập năm thống kê:");

        tableTungThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tháng", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tableTungThang);

        btnTKTungThang.setText("Thống kê");
        btnTKTungThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKTungThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTungThangLayout = new javax.swing.GroupLayout(panelTungThang);
        panelTungThang.setLayout(panelTungThangLayout);
        panelTungThangLayout.setHorizontalGroup(
            panelTungThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTungThangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(txtTungThang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnTKTungThang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelTungThangLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTungThangLayout.setVerticalGroup(
            panelTungThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTungThangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTungThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTungThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTKTungThang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
        );

        jPanel21.add(panelTungThang, "card3");

        jLabel28.setText("Chọn tháng");

        chonthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        chonthang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chonthangActionPerformed(evt);
            }
        });

        jLabel29.setText("Chọn năm");

        chonnam.setModel(new javax.swing.SpinnerNumberModel(2024, 2015, 2035, 1));
        chonnam.setToolTipText("");

        btnTKTungNgay.setText("Thống kê");
        btnTKTungNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKTungNgayActionPerformed(evt);
            }
        });

        tableTungNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane14.setViewportView(tableTungNgay);

        javax.swing.GroupLayout panelTungNgayLayout = new javax.swing.GroupLayout(panelTungNgay);
        panelTungNgay.setLayout(panelTungNgayLayout);
        panelTungNgayLayout.setHorizontalGroup(
            panelTungNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTungNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTungNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTungNgayLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(chonthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chonnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTKTungNgay)
                        .addContainerGap(384, Short.MAX_VALUE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        panelTungNgayLayout.setVerticalGroup(
            panelTungNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTungNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTungNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(chonthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(chonnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTKTungNgay))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.add(panelTungNgay, "card4");

        jLabel30.setText("Từ ngày");

        javax.swing.GroupLayout ngaybdLayout = new javax.swing.GroupLayout(ngaybd);
        ngaybd.setLayout(ngaybdLayout);
        ngaybdLayout.setHorizontalGroup(
            ngaybdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        ngaybdLayout.setVerticalGroup(
            ngaybdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel31.setText("Đến ngày");

        javax.swing.GroupLayout ngayktLayout = new javax.swing.GroupLayout(ngaykt);
        ngaykt.setLayout(ngayktLayout);
        ngayktLayout.setHorizontalGroup(
            ngayktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        ngayktLayout.setVerticalGroup(
            ngayktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnTKTuNgay.setText("Thống kê");
        btnTKTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKTuNgayActionPerformed(evt);
            }
        });

        tableTuNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane15.setViewportView(tableTuNgay);

        javax.swing.GroupLayout panelTuNgayLayout = new javax.swing.GroupLayout(panelTuNgay);
        panelTuNgay.setLayout(panelTuNgayLayout);
        panelTuNgayLayout.setHorizontalGroup(
            panelTuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTuNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(panelTuNgayLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTKTuNgay)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTuNgayLayout.setVerticalGroup(
            panelTuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTuNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTKTuNgay)
                    .addGroup(panelTuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngaybd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngaykt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.add(panelTuNgay, "card5");

        javax.swing.GroupLayout panelDoanThuLayout = new javax.swing.GroupLayout(panelDoanThu);
        panelDoanThu.setLayout(panelDoanThuLayout);
        panelDoanThuLayout.setHorizontalGroup(
            panelDoanThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTheoNam)
                .addGap(18, 18, 18)
                .addComponent(btnTungThang)
                .addGap(18, 18, 18)
                .addComponent(btnTungNgay)
                .addGap(18, 18, 18)
                .addComponent(btnTuNgay)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(panelDoanThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDoanThuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelDoanThuLayout.setVerticalGroup(
            panelDoanThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoanThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTheoNam)
                    .addComponent(btnTungThang)
                    .addComponent(btnTungNgay)
                    .addComponent(btnTuNgay))
                .addContainerGap(521, Short.MAX_VALUE))
            .addGroup(panelDoanThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDoanThuLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel19.add(panelDoanThu, "card3");

        jLabel32.setText("Tìm kiếm khách hàng");
        jLabel32.setToolTipText("");

        txtTimKiemKH2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKH2KeyPressed(evt);
            }
        });

        jLabel33.setText("Từ ngày");

        javax.swing.GroupLayout tungayLayout = new javax.swing.GroupLayout(tungay);
        tungay.setLayout(tungayLayout);
        tungayLayout.setHorizontalGroup(
            tungayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );
        tungayLayout.setVerticalGroup(
            tungayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jLabel34.setText("Đến ngày");

        javax.swing.GroupLayout denngayLayout = new javax.swing.GroupLayout(denngay);
        denngay.setLayout(denngayLayout);
        denngayLayout.setHorizontalGroup(
            denngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        denngayLayout.setVerticalGroup(
            denngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        btnLamMoi2.setText("Làm mới");
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });

        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khách hàng", "Tên khách hàng", "Số lượng hoá đơn", "Tổng số tiền "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane16.setViewportView(tableKH);

        javax.swing.GroupLayout panelKHLayout = new javax.swing.GroupLayout(panelKH);
        panelKH.setLayout(panelKHLayout);
        panelKHLayout.setHorizontalGroup(
            panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(btnLamMoi2)
                    .addGroup(panelKHLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemKH2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(denngay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelKHLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(tungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelKHLayout.setVerticalGroup(
            panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addGroup(panelKHLayout.createSequentialGroup()
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(22, 22, 22)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(denngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLamMoi2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel19.add(panelKH, "card4");

        javax.swing.GroupLayout pThongKeLayout = new javax.swing.GroupLayout(pThongKe);
        pThongKe.setLayout(pThongKeLayout);
        pThongKeLayout.setHorizontalGroup(
            pThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTongQuan)
                .addGap(18, 18, 18)
                .addComponent(btnDoanhThu)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(1949, Short.MAX_VALUE))
            .addGroup(pThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pThongKeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1442, Short.MAX_VALUE)))
        );
        pThongKeLayout.setVerticalGroup(
            pThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTongQuan)
                    .addComponent(btnDoanhThu)
                    .addComponent(jButton1))
                .addContainerGap(1074, Short.MAX_VALUE))
            .addGroup(pThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThongKeLayout.createSequentialGroup()
                    .addContainerGap(46, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(511, Short.MAX_VALUE)))
        );

        panelContent.add(pThongKe, "card10");

        pTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        pTrangChu.setName("TrangChu"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTextPane3.setBackground(new java.awt.Color(204, 204, 255));
        jTextPane3.setBorder(null);
        jTextPane3.setFont(new java.awt.Font("Arial", 3, 20)); // NOI18N
        jTextPane3.setForeground(new java.awt.Color(102, 102, 102));
        jTextPane3.setText("Liên hệ và hỗ trợ khách hàng Hiển thị thông tin liên hệ: ");
        jScrollPane3.setViewportView(jTextPane3);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-support-100.png"))); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-hotline-36.png"))); // NOI18N
        jLabel13.setText("09878887666");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-email-36.png"))); // NOI18N
        jLabel14.setText("mobileshop@gmail.com");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-email-36.png"))); // NOI18N
        jLabel15.setText("facebook.com/mobileshop");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setForeground(new java.awt.Color(255, 204, 204));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-like-100.png"))); // NOI18N
        jLabel10.setText("jLabel10");

        jTextPane5.setBackground(new java.awt.Color(255, 204, 204));
        jTextPane5.setBorder(null);
        jTextPane5.setFont(new java.awt.Font("Arial", 3, 20)); // NOI18N
        jTextPane5.setForeground(new java.awt.Color(102, 102, 102));
        jTextPane5.setText("\"Mobile Shop chuyên cung cấp các sản phẩm điện thoại, phụ kiện chất lượng cao với giá cả hợp lý. Chúng tôi cam kết mang đến dịch vụ tốt nhất cho khách hàng!\"");
        jTextPane5.setCaretColor(new java.awt.Color(255, 0, 102));
        jScrollPane5.setViewportView(jTextPane5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        jTextPane2.setBackground(new java.awt.Color(204, 255, 204));
        jTextPane2.setBorder(null);
        jTextPane2.setFont(new java.awt.Font("Arial", 3, 20)); // NOI18N
        jTextPane2.setForeground(new java.awt.Color(102, 102, 102));
        jTextPane2.setText("\"Mua điện thoại xịn Giá cực hời!\"");
        jTextPane2.setToolTipText("");
        jScrollPane2.setViewportView(jTextPane2);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-voucher-100.png"))); // NOI18N
        jLabel11.setText("jLabel11");

        jTextPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTextPane1.setBorder(null);
        jTextPane1.setFont(new java.awt.Font("Arial", 3, 20)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTextPane1.setText("Giảm ngay 200k cho đơn hàng 2tr và 500k cho đơn hàng 5tr");
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pTrangChuLayout = new javax.swing.GroupLayout(pTrangChu);
        pTrangChu.setLayout(pTrangChuLayout);
        pTrangChuLayout.setHorizontalGroup(
            pTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrangChuLayout.createSequentialGroup()
                .addGroup(pTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrangChuLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pTrangChuLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel9)))
                .addContainerGap(1304, Short.MAX_VALUE))
        );
        pTrangChuLayout.setVerticalGroup(
            pTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrangChuLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel9)
                .addGap(44, 44, 44)
                .addGroup(pTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(631, Short.MAX_VALUE))
        );

        panelContent.add(pTrangChu, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addComponent(panelContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
         CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "TrangChu");
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "HoaDon");
          loadHoaDonToTable();
    }//GEN-LAST:event_btnHoaDonActionPerformed
      private void searchAndUpdateTable() {
        // Get the text from the search field
    String searchText = jTextFieldTimKiem.getText().trim().toLowerCase();
    String selectedSearchOption = (String) jComboBox2.getSelectedItem();
    if (searchText.isEmpty()) {
        updateTable(phieuNhapBUS.getAllList());
        return;
    }
    ArrayList<PhieuNhapDTO> filteredList = new ArrayList<>();

    for (PhieuNhapDTO phieu : phieuNhapBUS.getAllList()) {
        boolean matches = false;
        switch (selectedSearchOption) {
            case "Theo Mã Phiếu":
                if (phieu.getMaPhieuNhap().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
            case "Theo Mã Nhân Viên":
                if (phieu.getMaNhanVien().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
            case "Theo Mã Nhà Cung Cấp":
                if (phieu.getMaNhaCungCap().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
            case "Tất cả":
                if (phieu.getMaPhieuNhap().toLowerCase().contains(searchText) || 
                    phieu.getMaNhanVien().toLowerCase().contains(searchText) ||
                    phieu.getMaNhaCungCap().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
        }
        if (matches) {
            filteredList.add(phieu);
        }
    }
    updateTable(filteredList);
    }
      private void searchAndUpdateTable2 () {
    String searchText = jTextFieldTimKiem2.getText().trim().toLowerCase();
    String selectedSearchOption = (String) jComboBox3.getSelectedItem();
    if (searchText.isEmpty()) {
        updateTable2(hoadonBUS.getAllList());
        return;
    }
    ArrayList<HoaDonDTO> filteredList = new ArrayList<>();

    for (HoaDonDTO hoaDon : hoadonBUS.getAllList()) {
        boolean matches = false;
        
        switch (selectedSearchOption) {
            case "Theo Mã Hóa Đơn":
                if (hoaDon.getMaHoaDon().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
            case "Theo Mã Nhân Viên":
                if (hoaDon.getMaNhanVien().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
            case "Theo Mã Khách Hàng":
                if (hoaDon.getMaKhachHang().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
            case "Tất Cả":
                if (hoaDon.getMaHoaDon().toLowerCase().contains(searchText) || 
                    hoaDon.getMaNhanVien().toLowerCase().contains(searchText) ||
                    hoaDon.getMaKhachHang().toLowerCase().contains(searchText)) {
                    matches = true;
                }
                break;
        }

        // If the search condition matches, add the item to the filtered list
        if (matches) {
            filteredList.add(hoaDon);
        }
    }

    // Update the JTable with the filtered list
    updateTable2(filteredList);
    }

    // Method to update the JTable with the filtered data
    private void updateTable(ArrayList<PhieuNhapDTO> list) {
        DefaultTableModel model = (DefaultTableModel) JTablePhieuNhap.getModel();
        model.setRowCount(0); // Clear existing rows

        // Add the filtered list to the table
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (PhieuNhapDTO phieu : list) {
            String ngayNhapFormatted = sdf.format(phieu.getNgayNhap());
            model.addRow(new Object[]{
                phieu.getMaPhieuNhap(),
                phieu.getMaNhanVien(),
                phieu.getMaNhaCungCap(),
                ngayNhapFormatted,
                phieu.getTongTien()
            });
        }
    }
    private void updateTable2(ArrayList<HoaDonDTO> list) {
        DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
        model.setRowCount(0); // Clear existing rows

        // Add the filtered list to the table
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (HoaDonDTO hd : list) {
            String ngayLapHoaDonFormatted = sdf.format(hd.getNgayLapHoaDon());
            model.addRow(new Object[]{
            hd.getMaHoaDon(),
            ngayLapHoaDonFormatted,
            hd.getMaNhanVien(),
            hd.getMaKhachHang(),
            hd.getTongTien(),
            hd.getTongTienPhaiTra()
            });
        }
    }
    public void loadKhachHangToTable() {
    KhachHangBUS bus = new KhachHangBUS();

    DefaultTableModel model = (DefaultTableModel) JTableKhachHang.getModel();
    model.setRowCount(0); // clear table

    ArrayList<KhachHangDTO> list = bus.getDanhSachKhachHang();
    
    
    for (KhachHangDTO kh : list) {
        model.addRow(new Object[]{
            kh.getMaKhachHang(),
            kh.getHoTenKhachHang(),
            kh.getDiaChi(),
            kh.getSoDienThoai(),
            kh.getEmail()
        });
    }
}

    public void loadNhaCungCapToTable() {
    NhaCungCapBUS bus = new NhaCungCapBUS();
    DefaultTableModel model = (DefaultTableModel) JTableNhaCungCap.getModel();
    model.setRowCount(0);
    ArrayList<NhaCungCapDTO> list = bus.getDanhSachNCC();
    
     
    for (NhaCungCapDTO ncc : list) {
        model.addRow(new Object[]{
            ncc.getMaNCC(),
            ncc.getTenNCC(),
            ncc.getDiaChi(),
            ncc.getSDT(),
            ncc.getEmail()
        });
    }
}
    public void loadDuLieuToTable() {
        ThongKeBUS1 bus = new ThongKeBUS1(); // gọi BUS

        ArrayList<ThongKeTungNgayTrongThangDTO> list = bus.getThongKe7NgayGanNhat(); // lấy dữ liệu

        DefaultTableModel model = (DefaultTableModel) tableThongKe.getModel();
        model.setRowCount(0); // clear dữ liệu cũ

        for (ThongKeTungNgayTrongThangDTO dto : list) {
            model.addRow(new Object[]{
                dto.getNgay(),
                dto.getChiphi(),
                dto.getDoanhthu(),
                dto.getLoinhuan()
            });
        }
    }


    public void loadPhieuNhapToTable() {
    DefaultTableModel model = (DefaultTableModel) JTablePhieuNhap.getModel();
    model.setRowCount(0);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    ArrayList<PhieuNhapDTO> list = phieuNhapBUS.getAll();
    for (PhieuNhapDTO pn : list) {
        String ngayNhapFormatted = sdf.format(pn.getNgayNhap());

        model.addRow(new Object[]{
            pn.getMaPhieuNhap(),
            pn.getMaNhanVien(),
            pn.getMaNhaCungCap(),
            ngayNhapFormatted,
            pn.getTongTien()
        });
    }
}
    public void loadHoaDonToTable() {
    DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
    model.setRowCount(0);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    ArrayList<HoaDonDTO> list = hoadonBUS.getAll();
    for (HoaDonDTO hd : list) {
        String ngayLapHoaDonFormatted = sdf.format(hd.getNgayLapHoaDon());
        model.addRow(new Object[]{
            hd.getMaHoaDon(),
            ngayLapHoaDonFormatted,
            hd.getMaNhanVien(),
            hd.getMaKhachHang(),
            hd.getTongTien(),
            hd.getTongTienPhaiTra()
        });
    }
}
    public void loadNhanVienToTable() {
        NhanVienBUS bus = new NhanVienBUS();

        DefaultTableModel model = (DefaultTableModel) JTableNhanVien.getModel();
        model.setRowCount(0);
        
        ArrayList<NhanVienDTO> ds = bus.layDanhSachNhanVien();
        for (NhanVienDTO nv : ds) {
            model.addRow(new Object[]{
                nv.getMaNhanVien(),
                nv.getHoTen(),
                nv.getChucVu(),
                nv.getNgayVaoLam(),
                nv.getLuong()
            });
        }
    }
    public void loadKhachHangThongKeToTable(List<ThongKeKhachHangDTO> result) {
    DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
    model.setRowCount(0); // clear table

    int k = 1;
    for (ThongKeKhachHangDTO i : result) {
        model.addRow(new Object[]{
            k,
            i.getMakh(),
            i.getTenkh(),
            i.getSohoadon(),
            i.getTongtien() // hoặc format nếu cần
        });
        k++;
    }
}

    
    private void btnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapActionPerformed
        CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "PhieuNhap");
          loadPhieuNhapToTable();
    }//GEN-LAST:event_btnPhieuNhapActionPerformed

    private void btnNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCapActionPerformed
       CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "NhaCungCap");
    }//GEN-LAST:event_btnNhaCungCapActionPerformed
    public void loadSanPhamToTable() {
    DefaultTableModel model = (DefaultTableModel) JTableSanPham.getModel();
    model.setRowCount(0); // Clear the table

    ArrayList<SanPhamDTO> list = new SanPhamBUS().getAll(); // Get the list of products from SanPhamBUS
    for (SanPhamDTO sp : list) {
        model.addRow(new Object[]{
            sp.getMaSanPham(),
            sp.getTenSanPham(),
            sp.getSoLuong(),
            sp.getDonGia(),
            sp.getDonViTinh(),
            sp.getMaLoai()
        });
    }
}
    

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
         CardLayout layout = (CardLayout) panelContent.getLayout();
         layout.show(panelContent, "SanPham");
         loadSanPhamToTable();
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
      CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "KhachHang");
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
       CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "NhanVien");
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
       CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "ThongKe");
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
        "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát", 
        javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        System.exit(0);
    }
    }//GEN-LAST:event_btnExitActionPerformed

    private void jButtonThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemSanPhamActionPerformed
      ThemSanPham themSP = new ThemSanPham(this); // Tạo đối tượng frame ThemSanPham
         themSP.setVisible(true); // Hiển thị frame
    }//GEN-LAST:event_jButtonThemSanPhamActionPerformed

    private void jButtonSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaSanPhamActionPerformed
     int selectedRow = JTableSanPham.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để cập nhật!");
    return;
}

int maSanPham = (int) JTableSanPham.getValueAt(selectedRow, 0);

SuaSanPham suaSP = new SuaSanPham(maSanPham, this); // `this` là form chính
suaSP.setVisible(true);
    }//GEN-LAST:event_jButtonSuaSanPhamActionPerformed

    private void jButtonXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaSanPhamActionPerformed
      int selectedRow = JTableSanPham.getSelectedRow();

    // Kiểm tra xem người dùng đã chọn sản phẩm chưa
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa!");
        return;
    }

    // Lấy mã sản phẩm từ bảng
    int maSanPham = (int) JTableSanPham.getValueAt(selectedRow, 0);  // Lấy mã sản phẩm từ cột 0

    // Xác nhận xóa
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        // Lấy sản phẩm từ maSanPham
        SanPhamBUS spBUS = new SanPhamBUS();
        SanPhamDTO sp = spBUS.getByMaSP(maSanPham);  // Lấy thông tin sản phẩm từ mã sản phẩm

        // Xóa sản phẩm và chi tiết sản phẩm
        boolean resultSP = spBUS.delete(sp);  // Gọi phương thức delete trong SanPhamBUS

        if (resultSP) {
            // Nếu xóa thành công, xóa chi tiết sản phẩm
            ChiTietSanPhamBUS ctspBUS = new ChiTietSanPhamBUS();
            boolean resultCTSP = ctspBUS.deleteByMaSanPham(maSanPham);  // Xóa chi tiết sản phẩm của mã sản phẩm
            loadSanPhamToTable();

            if (resultCTSP) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm và chi tiết thành công!");
                loadSanPhamToTable();  // Cập nhật lại bảng sản phẩm
            } else {
              
            }
        } else {
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButtonXoaSanPhamActionPerformed

    private void jButtonXuatSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXuatSanPhamActionPerformed
      exportSanPhamToExcel(JTableSanPham);
    }//GEN-LAST:event_jButtonXuatSanPhamActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here
        ThemPhieuNhap themPN = new ThemPhieuNhap(this);
        themPN.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        themPN.setVisible(true);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        int selectedRow = JTablePhieuNhap.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu nhập để xóa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phiếu nhập này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            String maPhieuNhap = JTablePhieuNhap.getValueAt(selectedRow, 0).toString();
            boolean isDeleted = phieuNhapBUS.delete(maPhieuNhap);
            if (isDeleted) {
                loadPhieuNhapToTable();
                JOptionPane.showMessageDialog(this, "Phiếu nhập đã được xóa thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa phiếu nhập.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    }//GEN-LAST:event_jButton39ActionPerformed
    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed

    int selectedRow = JTablePhieuNhap.getSelectedRow();
    if (selectedRow != -1) {
        String maPhieuNhap = JTablePhieuNhap.getValueAt(selectedRow, 0).toString();
        String maNhanVien = JTablePhieuNhap.getValueAt(selectedRow, 1).toString();
        String maNhaCungCap = JTablePhieuNhap.getValueAt(selectedRow, 2).toString();
        String ngayNhap = JTablePhieuNhap.getValueAt(selectedRow, 3).toString();
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        NhanVienDTO nhanVien = nhanVienBUS.getNhanVienById(Integer.parseInt(maNhanVien));
        String tenNhanVien = (nhanVien != null) ? nhanVien.getHoTen() : "Không rõ";

        NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        NhaCungCapDTO ncc = nhaCungCapBUS.getNhaCungCapById(Integer.parseInt(maNhaCungCap));
        String tenNhaCungCap = (ncc != null) ? ncc.getTenNCC() : "Không rõ";

        XemChiTietNhap chiTiet = new XemChiTietNhap();
        chiTiet.setPhieuNhapInfo(maPhieuNhap, tenNhanVien, tenNhaCungCap, ngayNhap);
        chiTiet.loadChiTietPhieuNhap(maPhieuNhap);
        chiTiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        chiTiet.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu nhập để xem chi tiết.");
    }
}//GEN-LAST:event_jButton40ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Phiếu Nhập");
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("MaPhieuNhap");
        headerRow.createCell(1).setCellValue("MaNhanVien");
        headerRow.createCell(2).setCellValue("MaNhaCungCap");
        headerRow.createCell(3).setCellValue("NgayNhap");
        headerRow.createCell(4).setCellValue("TongTien");
        DefaultTableModel model = (DefaultTableModel) JTablePhieuNhap.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        for (int i = 0; i < model.getRowCount(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(model.getValueAt(i, 0).toString());
            row.createCell(1).setCellValue(model.getValueAt(i, 1).toString());
            row.createCell(2).setCellValue(model.getValueAt(i, 2).toString());
            Object dateObject = model.getValueAt(i, 3);
            if (dateObject instanceof Date) {
                row.createCell(3).setCellValue(sdf.format((Date) dateObject)); 
            } else if (dateObject instanceof String) {
                try {
                    Date date = sdf.parse((String) dateObject); // Try parsing if it's a String
                    row.createCell(3).setCellValue(sdf.format(date));
                } catch (ParseException e) {
                    row.createCell(3).setCellValue(""); // If parsing fails, set it as an empty string
                }
            }

            // Handle other columns like DonGia
            row.createCell(4).setCellValue(Double.parseDouble(model.getValueAt(i, 4).toString())); // TongTien
        }

        // Save the file with a name like "Phieu_Nhap_YYYY-MM-DD.xlsx"
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "Phieu_Nhap_" + sdf2.format(new Date()) + ".xlsx";
        FileOutputStream fileOut = new FileOutputStream(fileName);
        workbook.write(fileOut);
        fileOut.close();

        JOptionPane.showMessageDialog(this, "Dữ liệu đã được xuất ra file Excel thành công: " + fileName);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton42ActionPerformed

    
    private void ButtonrefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonrefeshActionPerformed
        loadSanPhamToTable();
    }//GEN-LAST:event_ButtonrefeshActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
        ThemHoaDon themHD = new ThemHoaDon(this);
        themHD.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        themHD.setVisible(true);
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTableHoaDon.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xóa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hóa đơn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            String maPhieuNhap = jTableHoaDon.getValueAt(selectedRow, 0).toString();
            boolean isDeleted = hoadonBUS.delete(maPhieuNhap);
            if (isDeleted) {
                loadHoaDonToTable();
                JOptionPane.showMessageDialog(this, "Hóa đơn đã được xóa thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:
        try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Hóa Đơn");
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("MaHoaDon");
        headerRow.createCell(1).setCellValue("NgayLap");
        headerRow.createCell(2).setCellValue("MaNhanVien");
        headerRow.createCell(3).setCellValue("MaKhachHang");
        headerRow.createCell(4).setCellValue("TongTien");
        headerRow.createCell(5).setCellValue("TongTienPhaiTra");

        DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        for (int i = 0; i < model.getRowCount(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(model.getValueAt(i, 0).toString()); // MaHoaDon
            Object dateObject = model.getValueAt(i, 1);
            if (dateObject instanceof Date) {
                row.createCell(1).setCellValue(sdf.format((Date) dateObject)); // NgayLap
            } else if (dateObject instanceof String) {
                try {
                    Date date = sdf.parse((String) dateObject);
                    row.createCell(1).setCellValue(sdf.format(date)); // NgayLap
                } catch (ParseException e) {
                    row.createCell(1).setCellValue(""); // Nếu không thể chuyển đổi, để trống
                    System.out.println("Lỗi phân tích ngày: " + e.getMessage());
                }
            } else {
                row.createCell(1).setCellValue(""); // Nếu không phải Date hay String, để trống
            }

            // Các cột còn lại
            row.createCell(2).setCellValue(model.getValueAt(i, 2).toString()); // MaNhanVien
            row.createCell(3).setCellValue(model.getValueAt(i, 3).toString()); // MaKhachHang
            row.createCell(4).setCellValue(Double.parseDouble(model.getValueAt(i, 4).toString())); // TongTien
            row.createCell(5).setCellValue(Double.parseDouble(model.getValueAt(i, 5).toString())); // TongTienPhaiTra
        }

        // Lưu file Excel với tên "Hoa_Don_YYYY-MM-DD.xlsx"
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "Hoa_Don_" + sdf2.format(new Date()) + ".xlsx";
        FileOutputStream fileOut = new FileOutputStream(fileName);
        workbook.write(fileOut);
        fileOut.close();

        // Thông báo thành công
        JOptionPane.showMessageDialog(this, "Dữ liệu đã được xuất ra file Excel thành công: " + fileName);
    } catch (Exception e) {
        // Xử lý lỗi nếu có
        JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
              ThemKhachHang themSP = new ThemKhachHang(this); // Tạo đối tượng frame ThemSanPham
         themSP.setVisible(true); // Hiển thị frame
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        int selectedRow = JTableKhachHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một để cập nhật!");
            return;
        }

        int maSanPham = (int) JTableKhachHang.getValueAt(selectedRow, 0);

        SuaKhachHang suaSP = new SuaKhachHang(maSanPham, this); // `this` là form chính
        suaSP.setVisible(true);
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
             int selectedRow = JTableKhachHang.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        // Lấy mã khách hàng từ dòng đã chọn
        int maKH = (int) JTableKhachHang.getValueAt(selectedRow, 0);

        // Gọi BUS để xóa khách hàng
        KhachHangBUS khBUS = new KhachHangBUS();
        boolean isDeleted = khBUS.xoaKhachHang(maKH);

        if (isDeleted) {
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
            loadKhachHangToTable(); // Load lại bảng
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! Kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
   
    }//GEN-LAST:event_jButton68ActionPerformed

    private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
       ThemNhaCungCap themSP = new ThemNhaCungCap(this); // Tạo đối tượng frame ThemSanPham
         themSP.setVisible(true); // Hiển thị frame
    }//GEN-LAST:event_jButton70ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
                                               
        int selectedRow = JTableNhaCungCap.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một để cập nhật!");
            return;
        }

        int maSanPham = (int) JTableNhaCungCap.getValueAt(selectedRow, 0);

        SuaNhaCungCap suaSP = new SuaNhaCungCap(maSanPham, this); // `this` là form chính
        suaSP.setVisible(true);
    
    }//GEN-LAST:event_jButton71ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        int selectedRow = JTableNhaCungCap.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        int maNCC = (int) JTableNhaCungCap.getValueAt(selectedRow, 0);

        NhaCungCapBUS nccBUS = new NhaCungCapBUS();
        boolean isDeleted = nccBUS.xoaNhaCungCap(maNCC);

        if (isDeleted) {
            JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!");
            loadNhaCungCapToTable(); 
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! Có thể nhà cung cấp đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton75ActionPerformed

    private void jButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton77ActionPerformed
        ThemNhanVien themNV = new ThemNhanVien(this); // Tạo đối tượng frame ThemNhanVien
        themNV.setVisible(true); // Hiển thị frame        // TODO add your handling code here:
    }//GEN-LAST:event_jButton77ActionPerformed
    private void jButton78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton78ActionPerformed
        int selectedRow = JTableNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một để cập nhật!");
            return;
        }

        int maNV = (int) JTableNhanVien.getValueAt(selectedRow, 0);

        SuaNhanVien suaNV = new SuaNhanVien(maNV, this); // `this` là form chính
        suaNV.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton78ActionPerformed

    private void jButton79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton79ActionPerformed
        int selectedRow = JTableNhanVien.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Lấy mã khách hàng từ dòng đã chọn
            int maNV = (int) JTableNhanVien.getValueAt(selectedRow, 0);

            // Gọi BUS để xóa khách hàng
            NhanVienBUS nvBUS = new NhanVienBUS();
            boolean isDeleted = nvBUS.xoaNhanVien(maNV);

            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                loadNhanVienToTable(); // Load lại bảng
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại! Kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton79ActionPerformed

    private void jButton82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton82ActionPerformed

    private void jButtonChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChiTietSanPhamActionPerformed
       int selectedRow = JTableSanPham.getSelectedRow();
       if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xem chi tiết!");
        return;
    }

    int maSP = (int) JTableSanPham.getValueAt(selectedRow, 0);

    // Mở form xem chi tiết sản phẩm
    XemChiTietSanPham chiTietForm = new XemChiTietSanPham(maSP);
    chiTietForm.setVisible(true);
    }//GEN-LAST:event_jButtonChiTietSanPhamActionPerformed

    private void Buttonrefesh6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonrefesh6ActionPerformed
        // TODO add your handling code here:
        loadPhieuNhapToTable();
    }//GEN-LAST:event_Buttonrefesh6ActionPerformed

    private void Buttonrefesh7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonrefesh7ActionPerformed
        // TODO add your handling code here:
        loadHoaDonToTable();
    }//GEN-LAST:event_Buttonrefesh7ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // TODO add your handling code here:
    int selectedRow = jTableHoaDon.getSelectedRow();
    if (selectedRow != -1) {
        String maHoaDon = jTableHoaDon.getValueAt(selectedRow, 0).toString();
        String maNhanVien = jTableHoaDon.getValueAt(selectedRow, 2).toString();
        String maKhachHang = jTableHoaDon.getValueAt(selectedRow, 3).toString();
        String ngayNhap = jTableHoaDon.getValueAt(selectedRow, 1).toString();
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        NhanVienDTO nhanVien = nhanVienBUS.getNhanVienById(Integer.parseInt(maNhanVien));
        String tenNhanVien = (nhanVien != null) ? nhanVien.getHoTen() : "Không rõ";

        XemChiTietHoaDon chiTiet = new XemChiTietHoaDon();
        chiTiet.setHoaDonInfo(maHoaDon, tenNhanVien, maKhachHang, ngayNhap);
        chiTiet.loadChiTietHoaDon(maHoaDon);
        chiTiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        chiTiet.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu nhập để xem chi tiết.");
    }
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButtonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHangActionPerformed
        Hang hang = new Hang();
        hang.setVisible(true);
    }//GEN-LAST:event_jButtonHangActionPerformed

    private void jButtonLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoaiActionPerformed
        Loai loai = new Loai();
        loai.setVisible(true);
    }//GEN-LAST:event_jButtonLoaiActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextFieldTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimKiemActionPerformed

    private void JTableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JTableSanPhamMouseClicked

    private void txtTimKiemKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKHKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        String tuKhoa = txtTimKiemKH.getText().trim();
        KhachHangBUS bus = new KhachHangBUS();
        ArrayList<KhachHangDTO> list = bus.timKiemKhachHang(tuKhoa);
        
        DefaultTableModel model = (DefaultTableModel) JTableKhachHang.getModel();
        model.setRowCount(0); 

        for (KhachHangDTO kh : list) {
            model.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getHoTenKhachHang(),
                kh.getDiaChi(),
                kh.getSoDienThoai(),
                kh.getEmail()
            });
        }
    }
    }//GEN-LAST:event_txtTimKiemKHKeyPressed

    private void txtTimKiemNCCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNCCKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        String tuKhoa = txtTimKiemNCC.getText().trim();
        NhaCungCapBUS bus = new NhaCungCapBUS();
        ArrayList<NhaCungCapDTO> ketQua = bus.timKiemNCC(tuKhoa);

        DefaultTableModel model = (DefaultTableModel) JTableNhaCungCap.getModel();
        model.setRowCount(0); 

        for (NhaCungCapDTO ncc : ketQua) {
            model.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getDiaChi(),
                ncc.getSDT(),
                ncc.getEmail()
            });
        }
    }
    }//GEN-LAST:event_txtTimKiemNCCKeyPressed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton73ActionPerformed

    private void txtTimKiemKH1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKH1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKH1KeyPressed

    private void txtTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKHActionPerformed

    private void btnThuocTinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuocTinh1ActionPerformed
        CardLayout layout = (CardLayout) panelContent.getLayout();
          layout.show(panelContent, "ThuocTinh");        // TODO add your handling code here:
    }//GEN-LAST:event_btnThuocTinh1ActionPerformed

    private void jButtonKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKhuyenMaiActionPerformed
    JFrame khuyenMaiFrame = new JFrame("Quản lý Khuyến Mãi");
    KhuyenMai kmPanel = new KhuyenMai(); // Tạo instance của JPanel KhuyenMai

    khuyenMaiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame này thì chỉ frame này mất, không thoát cả chương trình
    khuyenMaiFrame.setContentPane(kmPanel); // Đặt panel KhuyenMai vào frame
    khuyenMaiFrame.pack(); // Tự động điều chỉnh kích thước frame cho vừa với nội dung
    khuyenMaiFrame.setLocationRelativeTo(this); // Hiển thị frame ở giữa so với cửa sổ hiện tại (hoặc null để giữa màn hình)
    khuyenMaiFrame.setVisible(true); // Hiển thị frame// TODO add your handling code here:
    }//GEN-LAST:event_jButtonKhuyenMaiActionPerformed

    private void txtTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemNVActionPerformed

    private void txtTimKiemNVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tuKhoa = txtTimKiemNV.getText().trim();
            NhanVienBUS bus = new NhanVienBUS();
            ArrayList<NhanVienDTO> list = bus.timKiemNhanVien(tuKhoa);
        
            DefaultTableModel model = (DefaultTableModel) JTableNhanVien.getModel();
            model.setRowCount(0); 

            for (NhanVienDTO nv : list) {
                model.addRow(new Object[]{
                    nv.getMaNhanVien(),
                    nv.getHoTen(),
                    nv.getChucVu(),
                    nv.getNgayVaoLam(),
                    nv.getLuong()
                });
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemNVKeyPressed

    private void btnTongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTongQuanActionPerformed
        CardLayout layout = (CardLayout) jPanel19.getLayout();
          layout.show(jPanel19, "TongQuan");    
        loadDuLieuToTable();        // TODO add your handling code here:
    }//GEN-LAST:event_btnTongQuanActionPerformed

    private void btnDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuActionPerformed
        CardLayout layout = (CardLayout) jPanel19.getLayout();
          layout.show(jPanel19, "DoanhThu");// TODO add your handling code here:
    }//GEN-LAST:event_btnDoanhThuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout layout = (CardLayout) jPanel19.getLayout();
          layout.show(jPanel19, "TKKhachHang");
          ThongKeBUS1 bus = new ThongKeBUS1();
            ArrayList<ThongKeKhachHangDTO> result = bus.getAllKhachHang(); // hoặc gọi filter nếu cần
        loadKhachHangThongKeToTable(result);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemKH2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKH2KeyPressed
                                                 
        /*if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tuKhoa = txtTimKiemKH2.getText().trim();

            KhachHangBUS bus = new KhachHangBUS();
            ArrayList<KhachHangDTO> list;

            if (tuKhoa.isEmpty()) {
                // Nếu không nhập gì, load toàn bộ danh sách khách hàng
                list = bus.getDanhSachKhachHang();
            } else {
                // Tìm kiếm theo tên hoặc mã
                list = bus.timKiemKhachHang(tuKhoa);
            }

            DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            for (KhachHangDTO kh : list) {
                model.addRow(new Object[]{
                    kh.getMaKhachHang(),
                    kh.getHoTenKhachHang(),
                    kh.getDiaChi(),
                    kh.getSoDienThoai(),
                    kh.getEmail()
                });
            }
    }  */ // TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER || txtTimKiemKH2.getText().length() > 1) {
        Fillter();
    }
    }//GEN-LAST:event_txtTimKiemKH2KeyPressed

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        ThongKeBUS1 bus = new ThongKeBUS1();
        ArrayList<ThongKeKhachHangDTO> result = bus.getAllKhachHang(); // hoặc gọi filter nếu cần
        loadKhachHangThongKeToTable(result);// TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi2ActionPerformed
    public void loadTKDoanhThuTungNam(ArrayList<ThongKeDoanhThuDTO> list) {
        //ThongKeBUS1 bus = new ThongKeBUS1(); // Khởi tạo BUS
        //ArrayList<ThongKeDoanhThuDTO> list = bus.getDoanhThuTungNam(); // Lấy dữ liệu doanh thu theo năm
        DefaultTableModel model = (DefaultTableModel) tableTheoNam.getModel();
        model.setRowCount(0); // clear table
         // Xóa toàn bộ hàng hiện tại
        for (ThongKeDoanhThuDTO i : list) {
            model.addRow(new Object[]{
                i.getThoigian(), // Thời gian (giả định là năm)
                 i.getThoigian(), // Thời gian (giả định là năm)
                i.getVon(), // Vốn (giá trị thô)
                i.getDoanhthu(), // Doanh thu (giá trị thô)
                i.getLoinhuan() // Lợi nhuận (giá trị thô)
            });
        }
    }
    private void btnTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTheoNamActionPerformed
        CardLayout layout = (CardLayout) jPanel21.getLayout();
          layout.show(jPanel21, "TheoNam");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTheoNamActionPerformed
    public static Boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        return input.equals("");
    }
    private void btnTKTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKTheoNamActionPerformed
        System.out.println(txtTuNam.getText());
        if (!isEmpty(txtTuNam.getText()) || !isEmpty(txtDenNam.getText())) {
        int nambd = Integer.parseInt(txtTuNam.getText());
        int namkt = Integer.parseInt(txtDenNam.getText());
        int current_year = LocalDate.now().getYear();
        if (nambd > current_year || namkt > current_year) {
            JOptionPane.showMessageDialog(this, "Năm không được lớn hơn năm hiện tại");
        } else {
            if (namkt < nambd || namkt <= 2015 || nambd <= 2015) {
                JOptionPane.showMessageDialog(this, "Năm kết thúc không được bé hơn năm bắt đầu và phải lớn hơn 2015");
            } else {
                ThongKeBUS1 thongkeBUS = new ThongKeBUS1();
                ArrayList<ThongKeDoanhThuDTO> dataset = thongkeBUS.getDoanhThuTheoTungNam(nambd, namkt);
                loadTKDoanhThuTungNam(dataset);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ !");
    }// TODO add your handling code here:
    }//GEN-LAST:event_btnTKTheoNamActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtTuNam.setText("");
        txtDenNam.setText("");
        int current_year = LocalDate.now().getYear();
        ThongKeBUS1 thongkeBUS = new ThongKeBUS1();
        ArrayList<ThongKeDoanhThuDTO> dataset = thongkeBUS.getDoanhThuTheoTungNam(current_year - 5, current_year);
        loadTKDoanhThuTungNam(dataset);// TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTungThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTungThangActionPerformed
        CardLayout layout = (CardLayout) jPanel21.getLayout();
          layout.show(jPanel21, "TungThang");// TODO add your handling code here:
    }//GEN-LAST:event_btnTungThangActionPerformed
    public void loadThongKeTheoThang(int nam) {
        ThongKeBUS1 thongkeBUS = new ThongKeBUS1();
        ArrayList<ThongKeTheoThangDTO> list = thongkeBUS.getThongKeTheoThang(nam);
        DefaultTableModel model = (DefaultTableModel) tableTungThang.getModel();
        model.setRowCount(0); // clear table
    
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
            "Tháng " + (i + 1), // Tên tháng
            list.get(i).getChiphi(), // Chi phí (giá trị thô)
            list.get(i).getDoanhthu(), // Doanh thu (giá trị thô)
            list.get(i).getLoinhuan() // Lợi nhuận (giá trị thô)
        });
    }
}
    private void btnTKTungThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKTungThangActionPerformed
         System.out.println(txtTungThang.getText());
        if (!isEmpty(txtTungThang.getText()) ) {
        int nam= Integer.parseInt(txtTungThang.getText());
        
        int current_year = LocalDate.now().getYear();
        if (nam > current_year ) {
            JOptionPane.showMessageDialog(this, "Năm không được lớn hơn năm hiện tại");
        } else {
            if ( nam <= 2015) {
                JOptionPane.showMessageDialog(this, "Nhập Năm phải lớn hơn 2015");
            } else {
                loadThongKeTheoThang(nam);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ !");
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnTKTungThangActionPerformed

    private void btnTungNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTungNgayActionPerformed
        CardLayout layout = (CardLayout) jPanel21.getLayout();
          layout.show(jPanel21, "TungNgay");        // TODO add your handling code here:
    }//GEN-LAST:event_btnTungNgayActionPerformed

    private void chonthangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chonthangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chonthangActionPerformed
    public void loadThongKeTheoNgayTrongThang(int thang, int nam) {
        ThongKeBUS1 thongkeBUS = new ThongKeBUS1();
        ArrayList<ThongKeTungNgayTrongThangDTO> list = thongkeBUS.getThongKeTungNgayTrongThang(thang, nam);
        DefaultTableModel model = (DefaultTableModel) tableTungNgay.getModel();
        model.setRowCount(0); // Xóa toàn bộ hàng hiện tại
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                list.get(i).getNgay(), // Ngày
                list.get(i).getChiphi(), // Chi phí (giá trị thô)
                list.get(i).getDoanhthu(), // Doanh thu (giá trị thô)
                list.get(i).getLoinhuan() // Lợi nhuận (giá trị thô)
            });
        }
    }
    private void btnTKTungNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKTungNgayActionPerformed
        // Lấy giá trị từ combobox tháng
        int thang = Integer.parseInt(chonthang.getSelectedItem().toString());
        // Lấy giá trị từ spinner năm
        int nam = (int) chonnam.getValue();
        int current_year = LocalDate.now().getYear();
        if (nam > current_year ) {
            JOptionPane.showMessageDialog(this, "Năm không được lớn hơn năm hiện tại");
        } else  loadThongKeTheoNgayTrongThang(thang,nam);       // TODO add your handling code here:
    }//GEN-LAST:event_btnTKTungNgayActionPerformed
    public boolean validateSelectDate1() throws ParseException {
        Date time_start = dateChooser7.getDate();
        Date time_end = dateChooser8.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateChooser7.setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateChooser8.setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateChooser8.setCalendar(null);
            return false;
        }
        return true;
    }
    public void loadThongKeTheoNgayTrongKhoang(String start, String end) {
        ThongKeBUS1 thongkeBUS = new ThongKeBUS1();
        ArrayList<ThongKeTungNgayTrongThangDTO> list = thongkeBUS.getThongKeTuNgayDenNgay(start, end);
        DefaultTableModel model = (DefaultTableModel) tableTuNgay.getModel();
        model.setRowCount(0); // Xóa toàn bộ hàng hiện tại
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                list.get(i).getNgay(), // Ngày
                list.get(i).getChiphi(), // Chi phí (giá trị thô)
                list.get(i).getDoanhthu(), // Doanh thu (giá trị thô)
                list.get(i).getLoinhuan() // Lợi nhuận (giá trị thô)
            });
        }
    }
    private void btnTKTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKTuNgayActionPerformed
        try {
            if (validateSelectDate1()) {
                if (dateChooser7.getDate() != null && dateChooser8.getDate() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String start = formatter.format(dateChooser7.getDate());
                    String end = formatter.format(dateChooser8.getDate());
                    loadThongKeTheoNgayTrongKhoang(start, end);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnTKTuNgayActionPerformed

    private void btnTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTuNgayActionPerformed
        CardLayout layout = (CardLayout) jPanel21.getLayout();
          layout.show(jPanel21, "TuNgay"); // TODO add your handling code here:
    }//GEN-LAST:event_btnTuNgayActionPerformed
    private void updateTableWithDateRange(Date startDate, Date endDate) {
    DefaultTableModel model = (DefaultTableModel) JTablePhieuNhap.getModel();
    model.setRowCount(0);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    ArrayList<PhieuNhapDTO> list = phieuNhapBUS.getAll();

    for (PhieuNhapDTO pn : list) {
        String dateString = sdf.format(pn.getNgayNhap());

        try {
            Date ngayNhap = sdf.parse(dateString);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(startDate);
            calStart.set(Calendar.HOUR_OF_DAY, 0);
            calStart.set(Calendar.MINUTE, 0);
            calStart.set(Calendar.SECOND, 0);
            calStart.set(Calendar.MILLISECOND, 0);
            startDate = calStart.getTime();

            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(endDate);
            calEnd.set(Calendar.HOUR_OF_DAY, 23);
            calEnd.set(Calendar.MINUTE, 59);
            calEnd.set(Calendar.SECOND, 59);
            calEnd.set(Calendar.MILLISECOND, 999);
            endDate = calEnd.getTime();
            if ((ngayNhap.equals(startDate) || ngayNhap.after(startDate)) &&
                (ngayNhap.equals(endDate) || ngayNhap.before(endDate))) {
                Object[] row = new Object[5];
                row[0] = pn.getMaPhieuNhap();
                row[1] = pn.getMaNhanVien();
                row[2] = pn.getMaNhaCungCap();
                row[3] = sdf.format(pn.getNgayNhap());
                row[4] = pn.getTongTien();
                model.addRow(row);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
    private void updateTableWithDateRange2(Date startDate, Date endDate) {
    DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
    model.setRowCount(0);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    ArrayList<HoaDonDTO> list = hoadonBUS.getAll();

    for (HoaDonDTO hd : list) {
        String dateString = sdf.format(hd.getNgayLapHoaDon());

        try {
            Date ngayNhap = sdf.parse(dateString);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(startDate);
            calStart.set(Calendar.HOUR_OF_DAY, 0);
            calStart.set(Calendar.MINUTE, 0);
            calStart.set(Calendar.SECOND, 0);
            calStart.set(Calendar.MILLISECOND, 0);
            startDate = calStart.getTime();

            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(endDate);
            calEnd.set(Calendar.HOUR_OF_DAY, 23);
            calEnd.set(Calendar.MINUTE, 59);
            calEnd.set(Calendar.SECOND, 59);
            calEnd.set(Calendar.MILLISECOND, 999);
            endDate = calEnd.getTime();
            if ((ngayNhap.equals(startDate) || ngayNhap.after(startDate)) &&
                (ngayNhap.equals(endDate) || ngayNhap.before(endDate))) {
                Object[] row = new Object[6];
                row[0] = hd.getMaHoaDon();
                row[1] = sdf.format(hd.getNgayLapHoaDon());
                row[2] = hd.getMaNhanVien();
                row[3] = hd.getMaKhachHang();
                row[4] = hd.getTongTien();
                row[5] = hd.getTongTienPhaiTra();
                model.addRow(row);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
    public boolean validateSelectDate() {
        java.util.Date time_start = dateChooser5.getDate();
        java.util.Date time_end = dateChooser6.getDate();
        java.util.Date current_date = new java.util.Date();

        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateChooser5.setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateChooser6.setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateChooser6.setCalendar(null);
            return false;
        }
        return true;
    }
    public void Fillter() {
    if (validateSelectDate()) {
        String input = txtTimKiemKH2.getText() != null ? txtTimKiemKH2.getText() : "";
        java.util.Date time_start = dateChooser5.getDate() != null ? dateChooser5.getDate() : new java.util.Date(0);
        java.util.Date time_end = dateChooser6.getDate() != null ? dateChooser6.getDate() : new java.util.Date(System.currentTimeMillis());
        ThongKeBUS1 thongkebus = new ThongKeBUS1(); // khởi tạo cục bộ
        List<ThongKeKhachHangDTO> list = thongkebus.filterKhachHang(input, time_start, time_end);
        loadKhachHangThongKeToTable(list);
    }
}
    public void resetForm() throws ParseException {
        txtTimKiemKH2.setText("");
        dateChooser5.setCalendar(null);
        dateChooser6.setCalendar(null);
        Fillter();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buttonrefesh;
    private javax.swing.JButton Buttonrefesh6;
    private javax.swing.JButton Buttonrefesh7;
    private javax.swing.JTable JTableKhachHang;
    private javax.swing.JTable JTableNhaCungCap;
    private javax.swing.JTable JTableNhanVien;
    private javax.swing.JTable JTablePhieuNhap;
    private javax.swing.JTable JTableSanPham;
    private javax.swing.JButton btnDoanhThu;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnNhaCungCap;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnPhieuNhap;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnTKTheoNam;
    private javax.swing.JButton btnTKTuNgay;
    private javax.swing.JButton btnTKTungNgay;
    private javax.swing.JButton btnTKTungThang;
    private javax.swing.JButton btnTheoNam;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnThuocTinh1;
    private javax.swing.JButton btnTongQuan;
    private javax.swing.JButton btnTuNgay;
    private javax.swing.JButton btnTungNgay;
    private javax.swing.JButton btnTungThang;
    private javax.swing.JSpinner chonnam;
    private javax.swing.JComboBox<String> chonthang;
    private javax.swing.JPanel denngay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton82;
    private javax.swing.JButton jButtonChiTietSanPham;
    private javax.swing.JButton jButtonHang;
    private javax.swing.JButton jButtonKhuyenMai;
    private javax.swing.JButton jButtonLoai;
    private javax.swing.JButton jButtonSuaSanPham;
    private javax.swing.JButton jButtonThemSanPham;
    private javax.swing.JButton jButtonXoaSanPham;
    private javax.swing.JButton jButtonXuatSanPham;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelHang;
    private javax.swing.JLabel jLabelLoai;
    private javax.swing.JLabel jLabelLoai1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableHoaDon;
    private javax.swing.JTextField jTextFieldTimKiem;
    private javax.swing.JTextField jTextFieldTimKiem2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JPanel ngaybd;
    private javax.swing.JPanel ngaykt;
    private javax.swing.JPanel pHoaDon;
    private javax.swing.JPanel pKhachHang;
    private javax.swing.JPanel pNhaCungCap;
    private javax.swing.JPanel pNhanVien;
    private javax.swing.JPanel pPhieuNhap;
    private javax.swing.JPanel pPhieuXuat;
    private javax.swing.JPanel pSanPham;
    private javax.swing.JPanel pThongKe;
    private javax.swing.JPanel pThuocTinh;
    private javax.swing.JPanel pTrangChu;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelDoanThu;
    private javax.swing.JPanel panelKH;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelName;
    private javax.swing.JPanel panelTheoNam;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelTuNgay;
    private javax.swing.JPanel panelTungNgay;
    private javax.swing.JPanel panelTungThang;
    private javax.swing.JTable tableKH;
    private javax.swing.JTable tableTheoNam;
    private javax.swing.JTable tableThongKe;
    private javax.swing.JTable tableTuNgay;
    private javax.swing.JTable tableTungNgay;
    private javax.swing.JTable tableTungThang;
    private javax.swing.JPanel tungay;
    private javax.swing.JTextField txtDenNam;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemKH1;
    private javax.swing.JTextField txtTimKiemKH2;
    private javax.swing.JTextField txtTimKiemNCC;
    private javax.swing.JTextField txtTimKiemNV;
    private javax.swing.JTextField txtTuNam;
    private javax.swing.JTextField txtTungThang;
    // End of variables declaration//GEN-END:variables
}
