
package com.mycompany.archivos_secuenciales;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;


import java.io.FileInputStream;

public class Cliente_File {
    private DataOutputStream write;
    private DataInputStream read;
    private String path = "C:\\Proyecto\\clientes.txt";

    public int getMaxId() {
        int maxId = -1; // Inicializar con un valor negativo para manejar el caso de archivo vacío
        
        try {
            read = new DataInputStream(new FileInputStream(path));
            
            while (true) {
                int us=read.readInt();
                int id = read.readInt();
                String nombre = read.readUTF();
                String apellidoPaterno = read.readUTF();
                String apellidoMaterno = read.readUTF();
                
                if (id > maxId) {
                    maxId = id;
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
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura");
            }
        }
        
        // Verificar si hay IDs sin asignar desde 0 al máximo encontrado
        for (int i = 0; i <= maxId; i++) {
            boolean idEncontrado = false;
            
            try {
                read = new DataInputStream(new FileInputStream(path));
                
                while (true) {
                    int us = read.readInt();
                    int id = read.readInt();
                    String nombre = read.readUTF();
                    String apellidoPaterno = read.readUTF();
                    String apellidoMaterno = read.readUTF();
                    
                    if (id == i) {
                        idEncontrado = true;
                        break;
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
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura");
                }
            }
            
            if (!idEncontrado) {
                return i; // Devolver el primer ID sin asignar encontrado
            }
        }
        
        return maxId + 1; // Todos los IDs están asignados, asignar el siguiente número
    }
    
    //Guarda los clientes al archivo clientes
    public void guardar(cliente cliente) {
        try {
            write = new DataOutputStream(new FileOutputStream(path, true));
            write.writeInt(cliente.getIdUsuario());
            write.writeInt(cliente.getId());
            write.writeUTF(cliente.getNombre());
            write.writeUTF(cliente.getApellidoPaterno());
            write.writeUTF(cliente.getApellidoMaterno());
            write.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente");
        }
    } 

    // Método para buscar un cliente recibiendo como parámetro el ID
    public cliente buscar(int id) {
        
        cliente cliente= null;
        try {
            read = new DataInputStream(new FileInputStream(path));
            
            while (true) {
                int us =read.readInt();
                int idCliente = read.readInt();//Se va a buscar el cliente con el id que le pasamos, comparando todos los atributos encontrados con los que queremos.
                String nombre = read.readUTF();
                String apellidoPaterno = read.readUTF();
                String apellidoMaterno = read.readUTF();
                
                System.out.println("ID Cliente");
                System.out.println(idCliente);
                
                System.out.println("ID PASADO");
                System.out.println(id);
                
                System.out.println("nombre");
                System.out.println(nombre);
                
                System.out.println("apellido paterno");
                System.out.println(apellidoPaterno);
                
                System.out.println("apellido materno");
                System.out.println(apellidoMaterno);
                
                

                // Verificar que el id del cliente leído si exista
                
                if (idCliente == id) {
                    cliente = new cliente();
                    
                    cliente.setIdUsuario(us);
                    cliente.setId(idCliente);
                    cliente.setNombre(nombre);
                    cliente.setApellidoPaterno(apellidoPaterno);
                    cliente.setApellidoMaterno(apellidoMaterno);
                    break;
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
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura");
            }
        }
        if(cliente!=null){
            System.out.println("CLIENTE");
            System.out.println(cliente.getId());
        }
        else{
            System.out.println("NULO");
        }
                   
        return cliente;
    }
    
    // Metodo para eliminar recibiendo el objeto cliente
    public void eliminar(cliente cliente) {
        try {
            read = new DataInputStream(new FileInputStream(path));
            write = new DataOutputStream(new FileOutputStream("C:\\Proyecto\\temp.txt"));
            
            while (true) {
                int us=read.readInt();
                int id = read.readInt();
                String nombre = read.readUTF();
                String apellidoPaterno = read.readUTF();
                String apellidoMaterno = read.readUTF();
                
                if (id != cliente.getId()) {
                    write.writeInt(us);
                    write.writeInt(id);
                    write.writeUTF(nombre);
                    write.writeUTF(apellidoPaterno);
                    write.writeUTF(apellidoMaterno);
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

    // Metodo para editar recibiendo el objeto cliente
    public void editar(cliente cliente) {
        try {
            read = new DataInputStream(new FileInputStream(path));
            write = new DataOutputStream(new FileOutputStream("C:\\Proyecto\\temp.txt"));
            
            while (true) {
                int us =read.readInt();
                int id = read.readInt();
                String nombre = read.readUTF();
                String apellidoPaterno = read.readUTF();
                String apellidoMaterno = read.readUTF();
                
                if (id == cliente.getId()) {
                    write.writeInt(cliente.getIdUsuario());
                    write.writeInt(cliente.getId());
                    write.writeUTF(cliente.getNombre());
                    write.writeUTF(cliente.getApellidoPaterno());
                    write.writeUTF(cliente.getApellidoMaterno());
                } else {
                    write.writeInt(us);
                    write.writeInt(id);
                    write.writeUTF(nombre);
                    write.writeUTF(apellidoPaterno);
                    write.writeUTF(apellidoMaterno);
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
