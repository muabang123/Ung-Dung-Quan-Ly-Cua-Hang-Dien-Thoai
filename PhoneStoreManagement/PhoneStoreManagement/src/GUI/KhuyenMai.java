/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ChuongTrinhKMBUS;
import BUS.ChiTietKM_HD_BUS;
import BUS.ChiTietKM_SP_BUS;
import DTO.ChuongTrinhKMDTO;
import DTO.ChiTietCTKM_HD_DTO;
import DTO.ChiTietCTKM_SP_DTO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
public class KhuyenMai extends javax.swing.JPanel {
    private boolean isAdding = false; // Khai báo ở đầu class
    /**
     * Creates new form KhuyenMai
     */
    public KhuyenMai() {
        initComponents();
        detailPanel.setVisible(false); // Ẩn panel chi tiết ban đầu
        addTableSelectionListener();   // xử lý click bảng
        loadDanhSachChuongTrinhKM();  // load dữ liệu khuyến mãi lên bảng
        // Xử lý sự kiện click ngoài table, textfield, button
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            Component clickedComponent = SwingUtilities.getDeepestComponentAt(jPanel1, evt.getX(), evt.getY());

            if (!(clickedComponent instanceof JTable) &&
                !(clickedComponent instanceof JTextField) &&
                !(clickedComponent instanceof JButton)) {

            // Bỏ chọn dòng đang chọn
                jTable1.clearSelection();

                // Reset các trường nhập liệu
               
                txtNgayBatDau.setText("");
                txtNgayKetThuc.setText("");
                txtMaSanPham.setText("");
                txtPhanTramSP.setText("");
                txtSoTienHoaDon.setText("");
                txtPhanTramHD.setText("");
                

                // Ẩn panel chi tiết sản phẩm
                detailPanel.setVisible(false);
            }
        }
    });

    }
    private void addTableSelectionListener() {
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        // Hiện panel chi tiết
                        detailPanel.setVisible(true);

                        int maCTKM = (int) jTable1.getValueAt(selectedRow, 0);
                        String ngayBD = jTable1.getValueAt(selectedRow, 1).toString();
                        String ngayKT = jTable1.getValueAt(selectedRow, 2).toString();

                        txtMaCTKM.setText(String.valueOf(maCTKM));
                        txtNgayBatDau.setText(ngayBD);
                        txtNgayKetThuc.setText(ngayKT);

                        loadChiTietKhuyenMai(maCTKM);
                    } else {
                        detailPanel.setVisible(false); // Ẩn khi không chọn
                    }
                }
            }
        });
    }
    private void loadDanhSachChuongTrinhKM() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

    ChuongTrinhKMBUS bus = new ChuongTrinhKMBUS();
    for (ChuongTrinhKMDTO ct : bus.getDanhSachChuongTrinhKM()) {
        model.addRow(new Object[]{
            ct.getMaChuongTrinhKM(),
            ct.getNgayBatDau(),
            ct.getNgayKetThuc()
        });
    }

    // Reset chi tiết khi load lại danh sách
    detailPanel.setVisible(false);
    txtMaCTKM.setText("");
    txtNgayBatDau.setText("");
    txtNgayKetThuc.setText("");
    txtMaSanPham.setText("");
    txtPhanTramSP.setText("");
    txtSoTienHoaDon.setText("");
    txtPhanTramHD.setText("");
}

   
    private void loadChiTietKhuyenMai(int maCTKM) {
        // Load chi tiết sản phẩm
        ChiTietKM_SP_BUS spBus = new ChiTietKM_SP_BUS();
        ArrayList<ChiTietCTKM_SP_DTO> dsSp = spBus.getDanhSach();
        txtMaSanPham.setText("");
        txtPhanTramSP.setText("");

        for (ChiTietCTKM_SP_DTO dto : dsSp) {
            if (dto.getMaChuongTrinhKM() == maCTKM) {
                txtMaSanPham.setText(String.valueOf(dto.getMaSanPham()));
                txtPhanTramSP.setText(String.valueOf(dto.getPhanTramGiamGia()));
                break; // chỉ hiển thị 1 bản ghi đầu tiên
            }
        }

        // Load chi tiết hóa đơn
        ChiTietKM_HD_BUS hdBus = new ChiTietKM_HD_BUS();
        ArrayList<ChiTietCTKM_HD_DTO> dsHd = hdBus.getDanhSach();
        txtSoTienHoaDon.setText("");
        txtPhanTramHD.setText("");

        for (ChiTietCTKM_HD_DTO dto : dsHd) {
            if (dto.getMaChuongTrinhKM() == maCTKM) {
                txtSoTienHoaDon.setText(String.valueOf(dto.getSoTienHoaDon()));
                txtPhanTramHD.setText(String.valueOf(dto.getPhanTramGiamGia()));
                break;
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        detailPanel = new javax.swing.JPanel();
        basicInfoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaCTKM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        detailSubPanel = new javax.swing.JPanel();
        hoaDonPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPhanTramHD = new javax.swing.JTextField();
        txtSoTienHoaDon = new javax.swing.JTextField();
        sanPhamPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPhanTramSP = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        jButtonADD = new javax.swing.JButton();
        jButtonUPDATE = new javax.swing.JButton();
        jButtonDELETE = new javax.swing.JButton();
        jButtonOUT = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setBackground(new java.awt.Color(51, 102, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Khuyến Mãi");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1)
                .addContainerGap(330, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã CTKM", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        detailPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết khuyến mãi"));

        jLabel2.setText("Mã CTKM:");

        txtMaCTKM.setEditable(false);
        txtMaCTKM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel5.setText("Ngày bắt đầu: ");

        txtNgayBatDau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel6.setText("Ngày kết thúc:");

        txtNgayKetThuc.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout basicInfoPanelLayout = new javax.swing.GroupLayout(basicInfoPanel);
        basicInfoPanel.setLayout(basicInfoPanelLayout);
        basicInfoPanelLayout.setHorizontalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        basicInfoPanelLayout.setVerticalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        hoaDonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn:"));

        jLabel7.setText("Số tiền hóa đơn:");

        jLabel8.setText("Phần trăm giảm giá:");
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout hoaDonPanelLayout = new javax.swing.GroupLayout(hoaDonPanel);
        hoaDonPanel.setLayout(hoaDonPanelLayout);
        hoaDonPanelLayout.setHorizontalGroup(
            hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hoaDonPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hoaDonPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(txtPhanTramHD, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        hoaDonPanelLayout.setVerticalGroup(
            hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(txtSoTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(hoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPhanTramHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sanPhamPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm:"));

        jLabel10.setText("Mã sản phẩm: ");

        jLabel11.setText("Phần trăm giảm giá:");
        jLabel11.setToolTipText("");

        javax.swing.GroupLayout sanPhamPanelLayout = new javax.swing.GroupLayout(sanPhamPanel);
        sanPhamPanel.setLayout(sanPhamPanelLayout);
        sanPhamPanelLayout.setHorizontalGroup(
            sanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanPhamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanPhamPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanPhamPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(txtPhanTramSP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sanPhamPanelLayout.setVerticalGroup(
            sanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanPhamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(sanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPhanTramSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout detailSubPanelLayout = new javax.swing.GroupLayout(detailSubPanel);
        detailSubPanel.setLayout(detailSubPanelLayout);
        detailSubPanelLayout.setHorizontalGroup(
            detailSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailSubPanelLayout.createSequentialGroup()
                .addGap(0, 264, Short.MAX_VALUE)
                .addComponent(hoaDonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(detailSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(detailSubPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sanPhamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(260, Short.MAX_VALUE)))
        );
        detailSubPanelLayout.setVerticalGroup(
            detailSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailSubPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(hoaDonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(detailSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(detailSubPanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(sanPhamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(basicInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addComponent(basicInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jButtonADD.setBackground(new java.awt.Color(51, 51, 255));
        jButtonADD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonADD.setForeground(new java.awt.Color(255, 255, 255));
        jButtonADD.setText("Thêm");
        jButtonADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADDActionPerformed(evt);
            }
        });

        jButtonUPDATE.setBackground(new java.awt.Color(102, 255, 102));
        jButtonUPDATE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUPDATE.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUPDATE.setText("Cập Nhật");
        jButtonUPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUPDATEActionPerformed(evt);
            }
        });

        jButtonDELETE.setBackground(new java.awt.Color(255, 51, 51));
        jButtonDELETE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDELETE.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDELETE.setText("Xóa");
        jButtonDELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDELETEActionPerformed(evt);
            }
        });

        jButtonOUT.setBackground(new java.awt.Color(0, 0, 0));
        jButtonOUT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonOUT.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOUT.setText("Thoát");
        jButtonOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOUTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButtonADD, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUPDATE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonOUT))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonADD)
                    .addComponent(jButtonUPDATE)
                    .addComponent(jButtonDELETE)
                    .addComponent(jButtonOUT))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADDActionPerformed
        if (!isAdding) {
            // Lần nhấn đầu tiên: hiện panel để nhập dữ liệu
            detailPanel.setVisible(true);
            txtMaCTKM.setText(""); // reset các trường
            txtNgayBatDau.setText("");
            txtNgayKetThuc.setText("");
            txtMaSanPham.setText("");
            txtPhanTramSP.setText("");
            txtSoTienHoaDon.setText("");
            txtPhanTramHD.setText("");

            isAdding = true; // chuyển sang chế độ thêm
            JOptionPane.showMessageDialog(this, "Nhập thông tin khuyến mãi rồi nhấn 'Thêm' lần nữa để xác nhận.");
        } else {
        detailPanel.setVisible(true);
        JOptionPane.showMessageDialog(this, "Nhập thông tin khuyến mãi rồi nhấn 'Thêm' lần nữa để xác nhận.");
            // Lần nhấn thứ 2: kiểm tra và thêm dữ liệu
        try {
            java.sql.Date ngayBatDau = java.sql.Date.valueOf(txtNgayBatDau.getText());
            java.sql.Date ngayKetThuc = java.sql.Date.valueOf(txtNgayKetThuc.getText());

            ChuongTrinhKMDTO ct = new ChuongTrinhKMDTO(0, ngayBatDau, ngayKetThuc);
            ChuongTrinhKMBUS bus = new ChuongTrinhKMBUS();

            if (bus.themChuongTrinhKM(ct)) {
                // Lấy mã CTKM mới vừa thêm (ví dụ dùng SELECT MAX hoặc trả về từ themChuongTrinhKM)
                int maCTKM = bus.getMaCTKMMax(); // Bạn cần cài đặt hàm này trong BUS

                // Thêm chi tiết hóa đơn
                ChiTietKM_HD_BUS hdBus = new ChiTietKM_HD_BUS();
                ChiTietCTKM_HD_DTO hd = new ChiTietCTKM_HD_DTO(
                    maCTKM,
                    Double.parseDouble(txtSoTienHoaDon.getText()),
                    Double.parseDouble(txtPhanTramHD.getText())
                );
                hdBus.themChiTietKM_HD(hd);

                // Thêm chi tiết sản phẩm
                ChiTietKM_SP_BUS spBus = new ChiTietKM_SP_BUS();
                ChiTietCTKM_SP_DTO sp = new ChiTietCTKM_SP_DTO(
                    maCTKM,
                    Integer.parseInt(txtMaSanPham.getText()),
                    Double.parseDouble(txtPhanTramSP.getText())
                );
                spBus.themChiTietKM_SP(sp);

                JOptionPane.showMessageDialog(this, "Thêm chương trình khuyến mãi thành công!");
                isAdding = false;
                loadDanhSachChuongTrinhKM();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng hoặc dữ liệu nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jButtonADDActionPerformed

    private void jButtonUPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUPDATEActionPerformed
         int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int maCTKM = Integer.parseInt(txtMaCTKM.getText());
            java.sql.Date ngayBatDau = java.sql.Date.valueOf(txtNgayBatDau.getText());
            java.sql.Date ngayKetThuc = java.sql.Date.valueOf(txtNgayKetThuc.getText());

            ChuongTrinhKMDTO ct = new ChuongTrinhKMDTO(maCTKM, ngayBatDau, ngayKetThuc);
            ChuongTrinhKMBUS bus = new ChuongTrinhKMBUS();

            if (bus.capNhatChuongTrinhKM(ct)) {
                // Cập nhật chi tiết hóa đơn
                ChiTietKM_HD_BUS hdBus = new ChiTietKM_HD_BUS();
                ChiTietCTKM_HD_DTO hd = new ChiTietCTKM_HD_DTO(
                    maCTKM,
                    Double.parseDouble(txtSoTienHoaDon.getText()),
                    Double.parseDouble(txtPhanTramHD.getText())
                );
                hdBus.suaChiTietKM_HD(hd);

                // Cập nhật chi tiết sản phẩm
                ChiTietKM_SP_BUS spBus = new ChiTietKM_SP_BUS();
                ChiTietCTKM_SP_DTO sp = new ChiTietCTKM_SP_DTO(
                    maCTKM,
                    Integer.parseInt(txtMaSanPham.getText()),
                    Double.parseDouble(txtPhanTramSP.getText())
                );
                spBus.suaChiTietKM_SP(sp);

                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadDanhSachChuongTrinhKM();
                loadChiTietKhuyenMai(maCTKM);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonUPDATEActionPerformed

    private void jButtonDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDELETEActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để xoá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int maCTKM = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ChuongTrinhKMBUS bus = new ChuongTrinhKMBUS();
            if (bus.xoaChuongTrinhKM(maCTKM)) {
                JOptionPane.showMessageDialog(this, "Xoá thành công!");
                loadDanhSachChuongTrinhKM();
            } else {
                JOptionPane.showMessageDialog(this, "Xoá thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_jButtonDELETEActionPerformed

    private void jButtonOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOUTActionPerformed
        JFrame frame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }//GEN-LAST:event_jButtonOUTActionPerformed
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Quản lý Khuyến Mãi");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new KhuyenMai());
                frame.pack();
                frame.setLocationRelativeTo(null); // căn giữa cửa sổ
                frame.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basicInfoPanel;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JPanel detailSubPanel;
    private javax.swing.JPanel hoaDonPanel;
    private javax.swing.JButton jButtonADD;
    private javax.swing.JButton jButtonDELETE;
    private javax.swing.JButton jButtonOUT;
    private javax.swing.JButton jButtonUPDATE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel sanPhamPanel;
    private javax.swing.JTextField txtMaCTKM;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtPhanTramHD;
    private javax.swing.JTextField txtPhanTramSP;
    private javax.swing.JTextField txtSoTienHoaDon;
    // End of variables declaration//GEN-END:variables
}
