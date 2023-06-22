package Org.LosRemedios.Recuperacion.Tema8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Persona persona = new Persona ("Pepe Manuel", 30);

        persistirPersona(persona);
    }

    public static void persistirPersona(Persona persona){

        String url = "jdbc:mysql://localhost:3306/Persistencia";
        String usuario="usuario";
        String contraseña="";
        String sql = "INSERT INTO tabla_personas (nombre, edad) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement statement = conn.prepareStatement(sql)) {


            statement.setString(1, persona.getNombre());
            statement.setInt(2, persona.getEdad());


            statement.executeUpdate();

            System.out.println("Persona guardada exitosamente en la base de datos.");

        } catch (SQLException e) {
            System.out.println("Error al guardar la persona en la base de datos: " + e.getMessage());
        }
    }
}

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}