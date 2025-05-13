/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.ChiTietSanPhamBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietSanPhamDTO;
import DTO.SanPhamDTO;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author MSI Gaming
 */
public class SuaSanPham extends javax.swing.JFrame {

    /**
     * Creates new form SanPham
     */
    
    private int maSanPham;
    private Main mainForm; // Để giữ đối tượng của form chính
    

    public SuaSanPham(int maSanPham,Main mainForm) {
       initComponents();
    this.maSanPham = maSanPham;
      this.mainForm = mainForm;

    setSize(1200, 800);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    txtMaSanPham.setEnabled(false); 

    loadThongTinSanPham(); // Tự động load dữ liệu sản phẩm lên các field
    loadChiTietSanPhamTheoMaTuTextField();
    initComboBoxRam();
      initComboBoxRom();
      initComboBoxDungLuongPin();
    }
    
        private void openXemDanhSachHang() {
        XemDanhSachHang xemDanhSachHang = new XemDanhSachHang(this);
        xemDanhSachHang.setVisible(true);
    }
    
         public void updateMaHang(String maHang) {
    txtMaHang.setText(maHang);  // Cập nhật giá trị cho txtMaHang
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
    
 

    
  private void loadThongTinSanPham() {
    // === Load thông tin sản phẩm chính ===
    SanPhamBUS spBUS = new SanPhamBUS();
    SanPhamDTO sp = spBUS.getByMaSP(maSanPham);

    if (sp != null) {
        txtMaSanPham.setText(String.valueOf(sp.getMaSanPham()));  // Hiển thị mã sản phẩm
        txtTenSanPham.setText(sp.getTenSanPham());
        txtDonGia.setText(sp.getDonGia().toString());
        txtMaLoai.setText(String.valueOf(sp.getMaLoai()));

        // Hiển thị ảnh sản phẩm
        String pathAnh = sp.getHinhAnh();
        if (pathAnh != null && !pathAnh.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(pathAnh);
                Image img = icon.getImage().getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(), Image.SCALE_SMOOTH);
                jLabelImage.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                System.err.println("Không thể load ảnh: " + e.getMessage());
                jLabelImage.setIcon(null);
            }
        } else {
            jLabelImage.setIcon(null);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm!");
    }
}
   
    private String getCurrentImagePath(int maSP) {
    SanPhamBUS spBUS = new SanPhamBUS();
    SanPhamDTO sp = spBUS.getByMaSP(maSP);
    return sp != null ? sp.getHinhAnh() : null; // Nếu không có ảnh thì trả về null
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
        jLabel2 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtMaHang = new javax.swing.JTextField();
        txtMaLoai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtChip = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMauSac = new javax.swing.JTextField();
        txtInch = new javax.swing.JTextField();
        jButtonUpdateSP = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabelImage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableChiTietSanPham = new javax.swing.JTable();
        jButtonThemSanPham1 = new javax.swing.JButton();
        jButtonThemCH = new javax.swing.JButton();
        jButtonXoaCH = new javax.swing.JButton();
        jButtonLayDS = new javax.swing.JButton();
        jComboBoxRam = new javax.swing.JComboBox<>();
        jComboBoxRom = new javax.swing.JComboBox<>();
        jComboBoxDungLuongPin = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanelBody.setBackground(new java.awt.Color(255, 255, 255));

        jPanelHeader.setBackground(new java.awt.Color(51, 102, 255));
        jPanelHeader.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CẬP NHẬT THÔNG TIN");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(103, 103, 103))
        );

        jLabel2.setText("Đơn giá");

        jLabel5.setText("Tên sản phẩm");

        jLabel7.setText("Mã hãng");

        txtMaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaLoaiActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã loại");

        jLabel4.setText("CHIP");

        jLabel6.setText("RAM");

        jLabel8.setText("Mã sản phẩm");

        jLabel9.setText("ROM");

        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        jLabel10.setText("Kích Thước màn hình");

        jLabel11.setText("Màu sắc");

        jLabel12.setText("Dung lượng pin");

        jButtonUpdateSP.setBackground(new java.awt.Color(51, 51, 255));
        jButtonUpdateSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUpdateSP.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdateSP.setText("Lưu thông tin");
        jButtonUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateSPActionPerformed(evt);
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

        jLabelImage.setBackground(new java.awt.Color(51, 0, 51));
        jLabelImage.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 51, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CẤU HÌNH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(273, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(251, 251, 251))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jButtonThemSanPham1.setBackground(new java.awt.Color(51, 51, 255));
        jButtonThemSanPham1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThemSanPham1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThemSanPham1.setText("Thêm sản phẩm");
        jButtonThemSanPham1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemSanPham1ActionPerformed(evt);
            }
        });

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
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonThemCH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonXoaCH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonLayDS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41))))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(504, 504, 504)
                    .addComponent(jButtonThemSanPham1)
                    .addContainerGap(546, Short.MAX_VALUE)))
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
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(147, 147, 147)
                    .addComponent(jButtonThemSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(102, Short.MAX_VALUE)))
        );

        jComboBoxRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRamActionPerformed(evt);
            }
        });

        jComboBoxRom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxDungLuongPin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInch, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRam, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRom, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxDungLuongPin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                                        .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBodyLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonUpdateSP)
                .addGap(35, 35, 35)
                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(399, 399, 399))
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxDungLuongPin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelBodyLayout.createSequentialGroup()
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxRom, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addComponent(jButton1))))
                    .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 334, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
         this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void jButtonUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateSPActionPerformed
 try {
        // Lấy dữ liệu từ các ô nhập liệu
        int maSanPham = Integer.parseInt(txtMaSanPham.getText().trim());  // Mã sản phẩm từ ô txtMaSanPham
        String tenSanPham = txtTenSanPham.getText().trim();  // Tên sản phẩm từ ô txtTenSanPham
        int maLoai = Integer.parseInt(txtMaLoai.getText().trim());  // Mã loại từ ô txtMaLoai
        BigDecimal donGia = new BigDecimal(txtDonGia.getText().trim());  // Giá sản phẩm từ ô txtDonGia

        // Kiểm tra xem các trường có hợp lệ không (không để trống)
        if (tenSanPham.isEmpty() || donGia.compareTo(BigDecimal.ZERO) <= 0 || maLoai <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo đối tượng SanPhamDTO từ các trường nhập liệu
        SanPhamDTO sp = new SanPhamDTO(maSanPham, tenSanPham, 0, donGia, "Chiếc", null, maLoai);

        // Gọi phương thức cập nhật trong BUS
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        boolean result = sanPhamBUS.update(sp);

        if (result) {
            JOptionPane.showMessageDialog(this, "✅ Cập nhật sản phẩm thành công!");
             
        } else {
            JOptionPane.showMessageDialog(this, "❌ Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    } catch (NumberFormatException e) {
        // Xử lý khi nhập số không hợp lệ
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        // Xử lý các lỗi khác
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }      

  

      
    }//GEN-LAST:event_jButtonUpdateSPActionPerformed

    private void jButtonThemSanPham1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemSanPham1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonThemSanPham1ActionPerformed

    private void jButtonThemCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemCHActionPerformed
       themCauHinhSanPham();
    }//GEN-LAST:event_jButtonThemCHActionPerformed

    private void jButtonLayDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLayDSActionPerformed
        loadChiTietSanPhamTheoMaTuTextField();
    }//GEN-LAST:event_jButtonLayDSActionPerformed

    private void jComboBoxRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRamActionPerformed

    }//GEN-LAST:event_jComboBoxRamActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                                    
         openXemDanhSachHang();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonLayDS;
    private javax.swing.JButton jButtonThemCH;
    private javax.swing.JButton jButtonThemSanPham1;
    private javax.swing.JButton jButtonUpdateSP;
    private javax.swing.JButton jButtonXoaCH;
    private javax.swing.JComboBox<String> jComboBoxDungLuongPin;
    private javax.swing.JComboBox<String> jComboBoxRam;
    private javax.swing.JComboBox<String> jComboBoxRom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelHeader;
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
