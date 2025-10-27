/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.idraGroup.lavadero.view.cliente;

import com.idraGroup.lavadero.controller.ClienteController;
import com.idraGroup.lavadero.model.Cliente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LoloColombo
 */
public class PanelListarCliente extends javax.swing.JPanel {

    private final ClienteController clienteController;

    public PanelListarCliente(ClienteController clienteController) {
        this.clienteController = clienteController;
        initComponents();
        cargarClientes();
    }

     public void cargarClientes() {
       
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setRowCount(0); 

        try {
            List<Cliente> listaClientes = clienteController.listarTodos();

            for (Cliente cliente : listaClientes) {
                model.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getDni(),
                    cliente.getTelefono()
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los clientes desde la base de datos: " + e.getMessage(),
                    "Error de Persistencia",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void recargarDatos(){
        cargarClientes();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlListarCliente = new javax.swing.JPanel();
        clienteSiluetaImg = new javax.swing.JLabel();
        CiudadImg = new javax.swing.JLabel();
        LogoImg = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        pnlListarCliente.setBackground(new java.awt.Color(255, 255, 255));
        pnlListarCliente.setForeground(new java.awt.Color(255, 255, 255));
        pnlListarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clienteSiluetaImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clienteSilueta.png"))); // NOI18N
        pnlListarCliente.add(clienteSiluetaImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 280, 430));

        CiudadImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/city.png"))); // NOI18N
        pnlListarCliente.add(CiudadImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 280, 430));

        LogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoLavadero.png"))); // NOI18N
        pnlListarCliente.add(LogoImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        lblTitulo.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        lblTitulo.setText("LISTA DE USUARIO");
        pnlListarCliente.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 280, 30));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DNI", "TELEFONO"
            }
        ));
        jScrollPane1.setViewportView(tblClientes);

        pnlListarCliente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 470, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlListarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlListarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CiudadImg;
    private javax.swing.JLabel LogoImg;
    private javax.swing.JLabel clienteSiluetaImg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlListarCliente;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
