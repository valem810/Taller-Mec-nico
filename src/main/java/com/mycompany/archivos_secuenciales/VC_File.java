
package com.mycompany.archivos_secuenciales;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class VC_File {
    private DataOutputStream write;
    private DataInputStream read;
    private String path = "C:\\Proyecto\\vc.txt";
    
    
    
    public void guardar(vehiculo_cliente vc) {
        try {
            write = new DataOutputStream(new FileOutputStream(path, true));
            write.writeUTF(vc.getIdUsuario());
            write.writeUTF(vc.getIdCliente());
            write.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente");
        }
    }
    
     public void eliminar(vehiculo_cliente vc) {
        try {
            read = new DataInputStream(new FileInputStream(path));
            write = new DataOutputStream(new FileOutputStream("C:\\Proyecto\\temp.txt"));
            
            while (true) {
                String us=read.readUTF();
                String id = read.readUTF();
                
                if (!id.equals(vc.getIdCliente()) && !us.equals(vc.getIdUsuario())) {
                    write.writeUTF(us);
                    write.writeUTF(id);
                }
            }
        } catch (EOFException e) {
            // Fin del archivo, no se hace nada aquí
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
                
                if (write != null) {
                    write.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
        
        // Renombrar el archivo temporal al archivo original
        try {
            java.io.File temp = new java.io.File("C:\\Proyecto\\temp.txt");
            java.io.File original = new java.io.File(path);
            original.delete();
            temp.renameTo(original);
        } catch (Exception e) {
            System.out.println("Error al renombrar el archivo");
        }
    }
     
      public void editar(vehiculo_cliente vc) {
        try {
            read = new DataInputStream(new FileInputStream(path));
            write = new DataOutputStream(new FileOutputStream("C:\\Proyecto\\temp.txt"));
            
            while (true) {
                String us=read.readUTF();
                String id = read.readUTF();
                
                if (id.equals(vc.getIdCliente()) && us.equals(vc.getIdUsuario())) {
                    write.writeUTF(us);
                    write.writeUTF(id);
                } else {
                    write.writeUTF(us);
                    write.writeUTF(id);
                }
            }
        } catch (EOFException e) {
            // Fin del archivo, no se hace nada aquí
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
                
                if (write != null) {
                    write.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
        
        // Renombrar el archivo temporal al archivo original
        try {
            java.io.File temp = new java.io.File("C:\\Proyecto\\temp.txt");
            java.io.File original = new java.io.File(path);
            original.delete();
            temp.renameTo(original);
        } catch (Exception e) {
            System.out.println("Error al renombrar el archivo");
        }
    }
      
    
}
