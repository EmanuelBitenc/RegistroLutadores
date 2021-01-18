package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.LutadorDAO;
import model.Lutador;
import view.ViewLutador;

public class ControllerLutador {
private Lutador lutador;
private LutadorDAO lutadorDAO;
public boolean excluir(int id) {
lutador = new Lutador(id);
try {
lutadorDAO = new LutadorDAO();
lutadorDAO.delete(lutador);
return true;
} catch (ClassNotFoundException | SQLException e) {
return false;
}
}
public boolean alterar( int id,String nome, double peso,
double altura) {
lutador = new Lutador(peso,altura,nome,id);
try {
lutadorDAO = new LutadorDAO();
lutadorDAO.update(lutador);
return true;
} catch (ClassNotFoundException | SQLException e) {
return false;
}
}
}