package org.rima.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.rima.model.Producto;


public class DbConnection {

	Connection connection = null;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (connection == null)
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rima?user=root&password=lpdppp");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public List<Producto> select() {
        List<Producto> productos = new LinkedList<Producto>();
         try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM rima.productos");
                Producto producto = null;
                while(resultSet.next()){
                    producto = new Producto();
                    producto.setCodigoProducto(Integer.parseInt(resultSet.getString("codigo_producto")));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    producto.setNombreProducto(resultSet.getString("nombre_producto"));
                    producto.setTipo(resultSet.getString("tipo"));
                    producto.setPrecio(Float.parseFloat(resultSet.getString("precio")));
                    productos.add(producto);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(productos);
            return productos;
    }
     
     
    public void closeConnection(){
        try {
              if (connection != null) {
                  connection.close();
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
    public void insert(Producto producto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rima.productos (codigo_producto ,nombre_producto, tipo, descripcion, precio) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1,  producto.getCodigoProducto());
            preparedStatement.setString(2,  producto.getNombreProducto());
            preparedStatement.setString(3,  producto.getTipo());
            preparedStatement.setString(4,  producto.getDescripcion());
            preparedStatement.setFloat(5,  producto.getPrecio());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
