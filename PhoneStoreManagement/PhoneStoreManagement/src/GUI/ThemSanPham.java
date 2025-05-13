 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.ChiTietSanPhamBUS;
import BUS.LoaiSanPhamBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietSanPhamDTO;
import DTO.SanPhamDTO;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI Gaming
 */
public class ThemSanPham extends javax.swing.JFrame {

    /**
     * Creates new form SanPham
     */
    private Main mainForm; // Để giữ đối tượng của form chính
  
    public ThemSanPham(Main mainForm) {
        initComponents();
        // Đặt kích thước cố định
         setSize(1200, 1200); // Thay đổi kích thước nếu cần
         setResizable(false); // Không cho phép thay đổi kích thước

    // Hiển thị JFrame giữa màn hình
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.mainForm = mainForm;
      initComboBoxRam();
      initComboBoxRom();
      initComboBoxDungLuongPin();
      
    }
    
    private void openXemDanhSachHang() {
        XemDanhSachHang xemDanhSachHang = new XemDanhSachHang(this);
        xemDanhSachHang.setVisible(true);
    }
    
    private void clearFields() {
    txtMaSanPham.setText("");  // Xóa trường Mã sản phẩm
    txtTenSanPham.setText("");  // Xóa trường Tên sản phẩm
    txtMaHang.setText("");  // Xóa trường Mã hãng
    txtMaLoai.setText("");  // Xóa trường Mã loại
    txtDonGia.setText("");  // Xóa trường Đơn giá
    txtChip.setText("");  // Xóa trường Chip
    txtInch.setText("");  // Xóa trường Inch
    txtMauSac.setText("");  // Xóa trường Màu sắc
    // Nếu có thêm trường nhập liệu khác, bạn thêm vào đây
}

    private void initComboBoxRam() {
    jComboBoxRam.removeAllItems(); // Xóa các item cũ nếu có
    jComboBoxRam.addItem("2GB");
    jComboBoxRam.addItem("4GB");
    jComboBoxRam.addItem("8GB");
    jComboBoxRam.addItem("16GB");
    jComboBoxRam.addItem("32GB");
    jComboBoxRam.addItem("64GB");
}
    
    private void initComboBoxRom() {
    jComboBoxRom.removeAllItems(); // Xóa các item cũ nếu có
    jComboBoxRom.addItem("16GB");
    jComboBoxRom.addItem("32GB");
    jComboBoxRom.addItem("64GB");
    jComboBoxRom.addItem("128GB");
    jComboBoxRom.addItem("256GB");
    jComboBoxRom.addItem("512GB");
    jComboBoxRom.addItem("1TB");
}
    
    private void initComboBoxDungLuongPin() {
    jComboBoxDungLuongPin.removeAllItems(); // Xóa item cũ (nếu có)
    jComboBoxDungLuongPin.addItem("3000mAh");
    jComboBoxDungLuongPin.addItem("4000mAh");
    jComboBoxDungLuongPin.addItem("4500mAh");
    jComboBoxDungLuongPin.addItem("5000mAh");
    jComboBoxDungLuongPin.addItem("6000mAh");
}
    
    public void updateMaHang(String maHang) {
    txtMaHang.setText(maHang);  // Cập nhật giá trị cho txtMaHang
}
    
  private void themSanPham() {
    try {
        // Kiểm tra dữ liệu sản phẩm
        if (txtMaSanPham.getText().isEmpty() ||
            txtTenSanPham.getText().isEmpty() ||
            txtDonGia.getText().isEmpty() ||
            txtMaLoai.getText().isEmpty() ||
            HinhAnh == null || HinhAnh.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm và chọn ảnh!");
            return;
        }

        // Kiểm tra mã sản phẩm có tồn tại trong CSDL hay không
        int maSP = Integer.parseInt(txtMaSanPham.getText().trim());
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        if (sanPhamBUS.isMaSanPhamExist(maSP)) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại. Vui lòng chọn mã khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra mã loại có tồn tại trong CSDL hay không
        int maLoai = Integer.parseInt(txtMaLoai.getText().trim());
        LoaiSanPhamBUS loaiSanPhamBUS = new LoaiSanPhamBUS();
        if (!loaiSanPhamBUS.isMaLoaiExist(maLoai)) {
            JOptionPane.showMessageDialog(this, "Mã loại không tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // === Lấy dữ liệu sản phẩm ===
        String tenSP = txtTenSanPham.getText().trim();
        int soLuong = 0;
        BigDecimal donGia = new BigDecimal(txtDonGia.getText().trim());
        String donViTinh = "Chiếc";
        String linkAnh = HinhAnh;

        // === Thêm sản phẩm ===
        SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, soLuong, donGia, donViTinh, linkAnh, maLoai);
        boolean result = sanPhamBUS.add(sp);

        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            
            mainForm.loadSanPhamToTable(); // Tải lại bảng
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}




    
  private void themCauHinhSanPham() {
     try {
        // Kiểm tra
        if (txtMaSanPham.getText().isEmpty() ||
            txtMaHang.getText().isEmpty() ||
            txtChip.getText().isEmpty() ||
            txtInch.getText().isEmpty() ||
            txtMauSac.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin cấu hình!");
            return;
        }

        int maSP = Integer.parseInt(txtMaSanPham.getText().trim());
        int maHang = Integer.parseInt(txtMaHang.getText().trim());
        String chip = txtChip.getText().trim();
        String inch = txtInch.getText().trim();
        String mauSac = txtMauSac.getText().trim();
        String ram = jComboBoxRam.getSelectedItem().toString();
        String rom = jComboBoxRom.getSelectedItem().toString();
        String dungLuongPin = jComboBoxDungLuongPin.getSelectedItem().toString();

        ChiTietSanPhamDTO ctsp = new ChiTietSanPhamDTO(maSP, maHang, chip, ram, rom, inch, dungLuongPin, mauSac);
        ChiTietSanPhamBUS ctspBUS = new ChiTietSanPhamBUS();
        boolean result = ctspBUS.add(ctsp);

        if (result) {
            JOptionPane.showMessageDialog(this, "✅ Thêm cấu hình thành công!");
           
            loadChiTietSanPhamTheoMaTuTextField(); // ⬅️ GỌI Ở ĐÂY
        } else {
            JOptionPane.showMessageDialog(this, "❌ Thêm cấu hình thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
      
  private void loadChiTietSanPhamTheoMaTuTextField() {
    try {
        String maSPText = txtMaSanPham.getText().trim();
        if (maSPText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maSP = Integer.parseInt(maSPText);

        // Lấy model của bảng và reset
        DefaultTableModel model = (DefaultTableModel) jTableChiTietSanPham.getModel();
        model.setRowCount(0);  // Xóa dòng cũ

        // Lấy danh sách từ BUS
        ChiTietSanPhamBUS ctspBUS = new ChiTietSanPhamBUS();
        ArrayList<ChiTietSanPhamDTO> list = ctspBUS.getAllCTSPbyMasp(maSP);

        // Đổ dữ liệu vào bảng
        for (ChiTietSanPhamDTO ct : list) {
            model.addRow(new Object[]{
                ct.getInch(),
                ct.getChip(),
                ct.getDungLuongPin(),
                ct.getRam(),
                ct.getRom(),
                ct.getMauSac(),
                ct.getMaHang()
            });
        }

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sản phẩm chưa có cấu hình nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Mã sản phẩm phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải chi tiết sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        jPanelBody = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtChip = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMauSac = new javax.swing.JTextField();
        txtInch = new javax.swing.JTextField();
        jButtonThemSanPham = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableChiTietSanPham = new javax.swing.JTable();
        jButtonThemCH = new javax.swing.JButton();
        jButtonXoaCH = new javax.swing.JButton();
        jButtonLayDS = new javax.swing.JButton();
        jComboBoxRam = new javax.swing.JComboBox<>();
        jComboBoxRom = new javax.swing.JComboBox<>();
        jComboBoxDungLuongPin = new javax.swing.JComboBox<>();
        jButtonDanhSachHang = new javax.swing.JButton();
        jPanelSanPham = new javax.swing.JPanel();
        jLabelAnh = new javax.swing.JLabel();
        jButtonimage = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtMaLoai = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanelBody.setBackground(new java.awt.Color(255, 255, 255));

        jPanelHeader.setBackground(new java.awt.Color(51, 102, 255));
        jPanelHeader.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("THÊM SẢN PHẨM");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(505, 505, 505)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(103, 103, 103))
        );

        jLabel7.setText("Mã hãng");

        jLabel4.setText("CHIP");

        jLabel6.setText("RAM");

        jLabel9.setText("ROM");

        jLabel10.setText("Kích Thước màn hình");

        jLabel11.setText("Màu sắc");

        jLabel12.setText("Dung lượng pin");

        jButtonThemSanPham.setBackground(new java.awt.Color(51, 51, 255));
        jButtonThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThemSanPham.setText("Thêm sản phẩm");
        jButtonThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemSanPhamActionPerformed(evt);
            }
        });

        jButtonExit.setBackground(new java.awt.Color(255, 51, 51));
        jButtonExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonExit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExit.setText("Hủy bỏ");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 51, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CẤU HÌNH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(265, 265, 265))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CẤU HÌNH");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(jLabel18)
                .addContainerGap(575, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Màn hình", "CHIP", "PIN", "RAM", "ROM", "Màu", "Hãng"
            }
        ));
        jTableChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableChiTietSanPham);

        jButtonThemCH.setBackground(new java.awt.Color(51, 51, 255));
        jButtonThemCH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThemCH.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThemCH.setText("Thêm cấu hình");
        jButtonThemCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemCHActionPerformed(evt);
            }
        });

        jButtonXoaCH.setBackground(new java.awt.Color(255, 0, 0));
        jButtonXoaCH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonXoaCH.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXoaCH.setText(" Xóa cấu hình");
        jButtonXoaCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaCHActionPerformed(evt);
            }
        });

        jButtonLayDS.setBackground(new java.awt.Color(0, 153, 153));
        jButtonLayDS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonLayDS.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLayDS.setText("Lấy danh sách ");
        jButtonLayDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLayDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonThemCH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonLayDS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonXoaCH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButtonLayDS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonThemCH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonXoaCH)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBoxRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRamActionPerformed(evt);
            }
        });

        jComboBoxRom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxDungLuongPin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonDanhSachHang.setText("...");
        jButtonDanhSachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDanhSachHangActionPerformed(evt);
            }
        });

        jLabelAnh.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAnh.setForeground(new java.awt.Color(255, 255, 255));

        jButtonimage.setBackground(new java.awt.Color(0, 0, 0));
        jButtonimage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonimage.setForeground(new java.awt.Color(255, 255, 255));
        jButtonimage.setText("Thêm ảnh");
        jButtonimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonimageActionPerformed(evt);
            }
        });

        jLabel20.setText("Mã sản phẩm");

        jLabel23.setText("Tên sản phẩm");

        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        jLabel24.setText("Mã loại");

        txtMaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaLoaiActionPerformed(evt);
            }
        });

        jLabel25.setText("Đơn giá");

        javax.swing.GroupLayout jPanelSanPhamLayout = new javax.swing.GroupLayout(jPanelSanPham);
        jPanelSanPham.setLayout(jPanelSanPhamLayout);
        jPanelSanPhamLayout.setHorizontalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenSanPham))
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaLoai, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))))
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jButtonimage)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanelSanPhamLayout.setVerticalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonimage)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInch, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRam, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(116, 116, 116)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(61, 61, 61)
                                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(26, 26, 26))
                                            .addComponent(jComboBoxDungLuongPin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                                        .addComponent(jComboBoxRom, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDanhSachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(333, 333, 333))
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(jButtonThemSanPham)
                        .addGap(34, 34, 34)
                        .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtInch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxDungLuongPin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRom, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonDanhSachHang)
                                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 432, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
          this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemSanPhamActionPerformed
     themSanPham();
    }//GEN-LAST:event_jButtonThemSanPhamActionPerformed

    private void jButtonThemCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemCHActionPerformed
       themCauHinhSanPham();
    }//GEN-LAST:event_jButtonThemCHActionPerformed

    private void jComboBoxRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRamActionPerformed

    }//GEN-LAST:event_jComboBoxRamActionPerformed

    private void jButtonimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonimageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh sản phẩm");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(true);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            // Hiển thị ảnh lên jLabelAnh
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage().getScaledInstance(jLabelAnh.getWidth(), jLabelAnh.getHeight(), Image.SCALE_SMOOTH);
            jLabelAnh.setIcon(new ImageIcon(image));

            // Lưu lại đường dẫn để dùng khi thêm sản phẩm
            HinhAnh = path;
        }
    }//GEN-LAST:event_jButtonimageActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtMaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiActionPerformed

    private void jButtonLayDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLayDSActionPerformed
        loadChiTietSanPhamTheoMaTuTextField();
    }//GEN-LAST:event_jButtonLayDSActionPerformed

    private void jTableChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableChiTietSanPhamMouseClicked
    int selectedRow = jTableChiTietSanPham.getSelectedRow();

    if (selectedRow != -1) {
        // Đổ dữ liệu từ bảng vào các ô nhập liệu
        txtInch.setText(jTableChiTietSanPham.getValueAt(selectedRow, 0).toString());  // Màn hình
        txtChip.setText(jTableChiTietSanPham.getValueAt(selectedRow, 1).toString());  // Chip
       jComboBoxDungLuongPin.setSelectedItem(jTableChiTietSanPham.getValueAt(selectedRow, 2).toString());    // PIN
        jComboBoxRam.setSelectedItem(jTableChiTietSanPham.getValueAt(selectedRow, 3).toString()); // RAM
        jComboBoxRom.setSelectedItem(jTableChiTietSanPham.getValueAt(selectedRow, 4).toString()); // ROM
        txtMauSac.setText(jTableChiTietSanPham.getValueAt(selectedRow, 5).toString());  // Màu sắc
        txtMaHang.setText(jTableChiTietSanPham.getValueAt(selectedRow, 6).toString());  // Hãng
    }
    }//GEN-LAST:event_jTableChiTietSanPhamMouseClicked

    private void jButtonXoaCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaCHActionPerformed
  // Lấy giá trị maSanPham từ trường nhập liệu (txtMaSanPham)
    String maSanPhamText = txtMaSanPham.getText().trim();
    
    // Kiểm tra xem mã sản phẩm có rỗng hay không
    if (maSanPhamText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int maSanPham;
    try {
        maSanPham = Integer.parseInt(maSanPhamText);  // Chuyển đổi maSanPham từ String sang int
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Lấy các giá trị khác từ bảng jTableChiTietSanPham (Ram, Rom, MauSac)
    int selectedRow = jTableChiTietSanPham.getSelectedRow();
    
    // Nếu không có dòng nào được chọn, thông báo cho người dùng
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một cấu hình để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String ram = jTableChiTietSanPham.getValueAt(selectedRow, 3).toString();  // RAM
    String rom = jTableChiTietSanPham.getValueAt(selectedRow, 4).toString();  // ROM
    String mauSac = jTableChiTietSanPham.getValueAt(selectedRow, 5).toString();  // Màu sắc

    // Gọi phương thức xóa chi tiết sản phẩm từ BUS
    ChiTietSanPhamBUS ctspBUS = new ChiTietSanPhamBUS();
    boolean result = ctspBUS.deleteByCompositeKey(maSanPham, ram, rom, mauSac);  // Xóa theo các khóa chính (MaSanPham, Ram, Rom, MauSac)

    // Kiểm tra kết quả xóa
    if (result) {
        JOptionPane.showMessageDialog(this, "Xóa cấu hình thành công!");
        loadChiTietSanPhamTheoMaTuTextField();  // Tải lại dữ liệu cho bảng
    } else {
        JOptionPane.showMessageDialog(this, "Xóa cấu hình thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButtonXoaCHActionPerformed

    private void jButtonDanhSachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDanhSachHangActionPerformed
                                                      
      openXemDanhSachHang() ;
     
    }//GEN-LAST:event_jButtonDanhSachHangActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
            }
        });
    }

    
    
    
    private String HinhAnh;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDanhSachHang;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonLayDS;
    private javax.swing.JButton jButtonThemCH;
    private javax.swing.JButton jButtonThemSanPham;
    private javax.swing.JButton jButtonXoaCH;
    private javax.swing.JButton jButtonimage;
    private javax.swing.JComboBox<String> jComboBoxDungLuongPin;
    private javax.swing.JComboBox<String> jComboBoxRam;
    private javax.swing.JComboBox<String> jComboBoxRom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAnh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelSanPham;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTableChiTietSanPham;
    private javax.swing.JTextField txtChip;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtInch;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}

