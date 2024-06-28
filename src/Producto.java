import java.time.LocalDate;

public class Producto {
    private String nombre;
    private int cantidad;
    private LocalDate fechaVencimiento;

    public Producto(String nombre, int cantidad, LocalDate fechaVencimiento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}

