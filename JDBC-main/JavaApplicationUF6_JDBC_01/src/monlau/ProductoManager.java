package monlau;

import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Producto;
import java.util.Scanner;

public class ProductoManager {
    private static ProductoDAO productoDAO = new ProductoDAOImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    insertarProducto();
                    break;
                case 2:
                    leerProducto();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n*** Gestión de Productos ***");
        System.out.println("1. Insertar nuevo producto");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void insertarProducto() {
        System.out.println("\n--- Insertar nuevo producto ---");
        System.out.print("Ingrese ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese precio del producto: ");
        double precio = scanner.nextDouble();
        
        Producto nuevoProducto = new Producto(id, nombre, precio);
        productoDAO.insert(nuevoProducto);
        System.out.println("Producto insertado con éxito: " + nuevoProducto);
    }

    private static void leerProducto() {
        System.out.println("\n--- Buscar producto ---");
        System.out.print("Ingrese ID del producto a buscar: ");
        int id = scanner.nextInt();
        
        Producto producto = productoDAO.read(id);
        if (producto != null) {
            System.out.println("Producto encontrado: " + producto);
        } else {
            System.out.println("No se encontró ningún producto con ID: " + id);
        }
    }

    private static void actualizarProducto() {
        System.out.println("\n--- Actualizar producto ---");
        System.out.print("Ingrese ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Producto producto = productoDAO.read(id);
        if (producto == null) {
            System.out.println("No se encontró ningún producto con ID: " + id);
            return;
        }
        
        System.out.println("Producto actual: " + producto);
        System.out.print("Ingrese nuevo nombre (dejar en blanco para no cambiar): ");
        String nuevoNombre = scanner.nextLine();
        
        System.out.print("Ingrese nuevo precio (0 para no cambiar): ");
        double nuevoPrecio = scanner.nextDouble();
        
        if (!nuevoNombre.isEmpty()) {
            producto.setNombre(nuevoNombre);
        }
        if (nuevoPrecio != 0) {
            producto.setPrecio(nuevoPrecio);
        }
        
        productoDAO.update(producto);
        System.out.println("Producto actualizado con éxito: " + productoDAO.read(id));
    }

    private static void eliminarProducto() {
        System.out.println("\n--- Eliminar producto ---");
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = scanner.nextInt();
        
        Producto producto = productoDAO.read(id);
        if (producto == null) {
            System.out.println("No se encontró ningún producto con ID: " + id);
            return;
        }
        
        productoDAO.delete(producto);
        System.out.println("Producto eliminado con éxito.");
        System.out.println("Verificando eliminación...");
        Producto productoEliminado = productoDAO.read(id);
        if (productoEliminado == null) {
            System.out.println("El producto ya no existe en la base de datos.");
        } else {
            System.out.println("Error: El producto no fue eliminado correctamente.");
        }
    }
}