package com.mycompany.archivos_secuenciales;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author brand
 */

public class reparaciones_File {

    private DataOutputStream write;
    private DataInputStream read;
    private String path = "C:\\Proyecto\\reparaciones.txt";

    Object[][] datos = new Object[7][100];
    Object[][] eliminar = new Object[7][100];

    public void Guardar(reparaciones rep) throws FileNotFoundException {
        try {
            write = new DataOutputStream(new FileOutputStream(path, true));
            write.writeInt(rep.getId_ve());
            write.writeInt(rep.getId_pi());
            write.writeInt(rep.getId_re());
            write.writeUTF(rep.getFalla());
            write.writeInt(rep.getId_contrl());
            write.writeUTF(rep.getFecha_e());
            write.writeUTF(rep.getFecha_s());
            write.close();

        } catch (IOException ex) {

        }

    }

    public reparaciones BuscarReparacion(reparaciones rep) throws FileNotFoundException {
        reparaciones aux = null;
        int id_ve = 0, id_pi = 0, id_re = 0, id_contrl = 0;
        String falla = "", fecha_e = "", fecha_s = "";
        try {
            read = new DataInputStream(new FileInputStream(path));
            while (true) {
                id_ve = read.readInt();
                id_pi = read.readInt();
                id_re = read.readInt();
                falla = read.readUTF();
                id_contrl = read.readInt();
                fecha_e = read.readUTF();
                fecha_s = read.readUTF();

                if (rep.getId_re() == id_re) {
                    aux = new reparaciones();
                    aux.setId_ve(id_ve);
                    aux.setId_pi(id_pi);
                    aux.setId_re(id_re);
                    aux.setFalla(falla);
                    aux.setId_contrl(id_contrl);
                    aux.setFecha_e(fecha_e);
                    aux.setFecha_s(fecha_s);
                }
            }
        } catch (IOException ex) {
        }
        try {
            read.close();
        } catch (IOException ex) {

        }

        return aux;
    }

    public void Editar(reparaciones rep) throws IOException {
        int i = 0;
        int z = 0;
        try {
            read = new DataInputStream(new FileInputStream(path));

            while (true) {
                datos[0][i] = read.readInt();
                datos[1][i] = read.readInt();
                datos[2][i] = read.readInt();
                datos[3][i] = read.readUTF();
                datos[4][i] = read.readInt();
                datos[5][i] = read.readUTF();
                datos[6][i] = read.readUTF();
                i++;
            }
        } catch (IOException ex) {
        }
        try {
            read.close();

        } catch (IOException ex) {

        }

        while (z < i) {
            if (rep.getId_re() == (int) datos[2][z]) {
                System.out.println(datos[0][z]);
                System.out.println(datos[1][z]);
                System.out.println(datos[2][z]);
                System.out.println(datos[6][z]);

                datos[0][z] = rep.getId_ve();
                datos[1][z] = rep.getId_pi();
                datos[2][z] = rep.getId_re();
                datos[3][z] = rep.getFalla();
                datos[4][z] = rep.getId_contrl();
                datos[5][z] = rep.getFecha_e();
                datos[6][z] = rep.getFecha_s();
            }
            z++;
        }

        i = 0;

        try {
            write = new DataOutputStream(new FileOutputStream(path));
            while (i < z) {
                write.writeInt((int) datos[0][i]);
                write.writeInt((int) datos[1][i]);
                write.writeInt((int) datos[2][i]);
                write.writeUTF(datos[3][i].toString());
                write.writeInt((int) datos[4][i]);
                write.writeUTF(datos[5][i].toString());
                write.writeUTF(datos[6][i].toString());
                i++;
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            if (write != null) {
                try {
                    write.close();
                } catch (IOException ex) {

                }
            }
        }
    }

    public int getMax() {
        int maxId = -1; 
        int id = 0, idVe = 0, idPi = 0, con = 0;
        String falla = "", fechE = "", fechS = "";
        try {
            read = new DataInputStream(new FileInputStream(path));
            while (true) {
                idVe = read.readInt();
                idPi = read.readInt();
                id = read.readInt();
                falla = read.readUTF();
                con = read.readInt();
                fechE = read.readUTF();
                fechS = read.readUTF();

                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (EOFException e) {
            // Fin del archivo, no se hace nada aquí
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de reparaciones");
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de reparaciones");
            }
        }

        // Verificar si hay IDs sin asignar desde 0 al máximo encontrado
        for (int i = 0; i <= maxId; i++) {
            boolean idEncontrado = false;

            try {
                read = new DataInputStream(new FileInputStream(path));
                while (true) {
                    idVe = read.readInt();
                    idPi = read.readInt();
                    id = read.readInt();
                    falla = read.readUTF();
                    con = read.readInt();
                    fechE = read.readUTF();
                    fechS = read.readUTF();

                    if (id == i) {
                        idEncontrado = true;
                        break;
                    }
                }
            } catch (EOFException e) {
                // Fin del archivo, no se hace nada aquí
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de reparaciones");
            } finally {
                try {
                    if (read != null) {
                        read.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de reparaciones");
                }
            }
            if (!idEncontrado) {
                return i;
            }
        }
        return maxId + 1;
    }

    public void Eliminar_Reparacion(reparaciones rep) throws IOException {
        int i = 0;
        int z = 0;
        int j;
        eliminar[0][0] = "";
        eliminar[1][0] = "";
        eliminar[2][0] = "";
        eliminar[3][0] = "";
        eliminar[4][0] = "";
        eliminar[5][0] = "";
        eliminar[6][0] = "";

        try {
            read = new DataInputStream(new FileInputStream(path));

            while (true) {
                eliminar[0][i] = read.readInt();
                eliminar[1][i] = read.readInt();
                eliminar[2][i] = read.readInt();
                eliminar[3][i] = read.readUTF();
                eliminar[4][i] = read.readInt();
                eliminar[5][i] = read.readUTF();
                eliminar[6][i] = read.readUTF();

                System.out.println("ELIMINAR");
                System.out.println(eliminar[0][i]);
                System.out.println(eliminar[1][i]);
                System.out.println(eliminar[2][i]);
                System.out.println(eliminar[5][i]);

                i++;
            }
        } catch (IOException ex) {
        }
        try {
            read.close();

        } catch (IOException ex) {

        }
        j = 0;
        while (z < i) {
            if ((int) eliminar[2][z] != rep.getId_re()) {
                datos[0][j] = eliminar[0][z];
                datos[1][j] = eliminar[1][z];
                datos[2][j] = eliminar[2][z];
                datos[3][j] = eliminar[3][z];
                datos[4][j] = eliminar[4][z];
                datos[5][j] = eliminar[5][z];
                datos[6][j] = eliminar[6][z];
                j++;
            }
            z++;
        }
        i = 0;

        try {
            write = new DataOutputStream(new FileOutputStream(path));
            while (i < j) {
                write.writeInt((int) datos[0][i]);
                write.writeInt((int) datos[1][i]);
                write.writeInt((int) datos[2][i]);
                write.writeUTF(datos[3][i].toString());
                write.writeInt((int) datos[4][i]);
                write.writeUTF(datos[5][i].toString());
                write.writeUTF(datos[6][i].toString());
                i++;
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            if (write != null) {
                try {
                    write.close();
                } catch (IOException ex) {

                }
            }
        }
    }
}
