/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author minhminh
 * @param <T>
 *
 */
public interface ChiTietInterface<T> {
    public int insert(ArrayList<T> t);
    public int delete(String t);
    public int update(ArrayList<T> t, String pk);
    public ArrayList<T> selectAll(String t);
}
