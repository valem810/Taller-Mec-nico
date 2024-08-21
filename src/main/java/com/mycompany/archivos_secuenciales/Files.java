// Nada
package com.mycompany.archivos_secuenciales;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Files {
    private DataOutputStream write;
    private DataInputStream read;
    private String path="C:\\Proyecto\\archivo.txt";
    
     Object [][] datos= new Object[9][100];
     Object [][] eliminar= new Object[9][100];
     
     
    public int Id() throws FileNotFoundException{
        int Idmax=0;
        List<Integer> assignedIds = new ArrayList<>();
        try {
            DataInputStream read = new DataInputStream(new FileInputStream(path));
            while(true){
                int id = read.readInt();
                read.readUTF();
                read.readUTF();
                read.readUTF();
                read.readUTF();
                read.readUTF();
                read.readUTF();
                read.readUTF();
                read.readUTF();
                assignedIds.add(id);

                if (id > Idmax) {
                    Idmax = id;
                }
            }
        } catch (EOFException ex) {
            
        } catch (IOException ex) {
            
        }

        for (int i = 0; i < Idmax; i++) {
            if (!assignedIds.contains(i)) {
                return i;
            }
        }
        return Idmax + 1;
    }
            
            
    public void Guardar(contacto cto) throws FileNotFoundException{
        try {
            write=new DataOutputStream(new FileOutputStream(path,true));
            write.writeInt(cto.getId());
            write.writeUTF(cto.getNombre());
            write.writeUTF(cto.getTelefono());
            write.writeUTF(cto.getPaterno());
            write.writeUTF(cto.getMaterno());
            write.writeUTF(cto.getUsername());
            write.writeUTF(cto.getPerfil());
            write.writeUTF(cto.getDireccion());
            write.writeUTF(cto.getPassword());
            write.close();
            
        } catch (IOException ex) {
           
        }
        
    }
    public contacto BuscarContacto(contacto cto) throws FileNotFoundException{
        contacto aux=null;
        int id=0;
        String nombre="",telefono="",paterno="",materno="",username="",perfil="",direccion="",password="";
        try {
        read = new DataInputStream(new FileInputStream(path));
        while(true){
           
                id= read.readInt();
                nombre=read.readUTF();
                telefono=read.readUTF();
                paterno=read.readUTF();
                materno=read.readUTF();
                username=read.readUTF();
                perfil=read.readUTF();
                direccion=read.readUTF();
                password=read.readUTF();
                if(cto.getId()==id){
                    aux=new contacto();
                    aux.setId(id);
                    aux.setNombre(nombre);
                    aux.setTelefono(telefono);
                    aux.setPaterno(paterno);
                    aux.setMaterno(materno);
                    aux.setUsername(username);
                    aux.setPerfil(perfil);
                    aux.setDireccion(direccion);
                    aux.setPassword(password);
                }
            } 
        } catch (IOException ex) {
       }
        try {
            read.close();
        } catch (IOException ex) {
            
        }
        if(aux!=null){
           System.out.println(aux.getUsername()); 
        }
        
        return aux;
    }
     public contacto BuscarUsuario(contacto cto) throws FileNotFoundException{
        contacto aux=null;
        int id=0;
        String nombre="",telefono="",paterno="",materno="",username="",perfil="",direccion="",password="";
        try {
        read = new DataInputStream(new FileInputStream(path));
       
        while(true){
                id=read.readInt();
                nombre=read.readUTF();
                telefono=read.readUTF();
                paterno=read.readUTF();
                materno=read.readUTF();
                username=read.readUTF();
                perfil=read.readUTF();
                direccion=read.readUTF();
                password=read.readUTF();
                System.out.println(username); 
                if(cto.getUsername().equals(username)){
                    aux=new contacto();
                    
                    aux.setId(id);
                    aux.setNombre(nombre);
                    aux.setTelefono(telefono);
                    aux.setPaterno(paterno);
                    aux.setMaterno(materno);
                    aux.setUsername(username);
                    aux.setPerfil(perfil);
                    aux.setDireccion(direccion);
                    aux.setPassword(password);
                   
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
    
    
    public void Editar(contacto cto) throws IOException{
        int i=0;
        int z=0;
        try {
        read = new DataInputStream(new FileInputStream(path));
        
        while(true){
                datos[0][i]=read.readInt();
                datos[1][i]=read.readUTF();
                datos[2][i]=read.readUTF();
                datos[3][i]=read.readUTF();
                datos[4][i]=read.readUTF();
                datos[5][i]=read.readUTF();
                datos[6][i]=read.readUTF();
                datos[7][i]=read.readUTF();
                datos[8][i]=read.readUTF();
                i++;
            }
        } catch (IOException ex) {
       }
        try {
            read.close();
            
        } catch (IOException ex) {
            
        }
        
         while(z<i){
                if(cto.getId()==(int)datos[0][z]){
                System.out.println(datos[0][z]);
                System.out.println(datos[1][z]);
                System.out.println(datos[2][z]);
                System.out.println(datos[8][z]);
                
                datos[0][z]=cto.getId();
                datos[1][z]=cto.getNombre();
                datos[2][z]=cto.getTelefono();
                datos[3][z]=cto.getPaterno();
                datos[4][z]=cto.getMaterno();
                datos[5][z]=cto.getUsername();
                datos[6][z]=cto.getPerfil();
                datos[7][z]=cto.getDireccion();
                datos[8][z]=cto.getPassword();
              
                }
                  z++;
                
            }

        i=0;
        try {
            write= new DataOutputStream(new FileOutputStream(path));
            while(i<z){
                write.writeInt((int)datos[0][i]);
                write.writeUTF(datos[1][i].toString());
                write.writeUTF(datos[2][i].toString());
                write.writeUTF(datos[3][i].toString());
                write.writeUTF(datos[4][i].toString());
                write.writeUTF(datos[5][i].toString());
                write.writeUTF(datos[6][i].toString());
                write.writeUTF(datos[7][i].toString());
                write.writeUTF(datos[8][i].toString());
                i++;
         }
        } catch (FileNotFoundException ex) {
      
        }catch(IOException ex){
            
        }finally{
            if(write!=null){
                try{
                      write.close();
                }catch(IOException ex){
                    
                }
            }
        }
    }
    
    
     public void Eliminar(contacto cto) throws IOException{
        int i=0;
        int z=0;
        int id=0;
        int j;
        eliminar[0][0]="";
        eliminar[1][0]="";
        eliminar[2][0]="";   
        eliminar[3][0]="";
        eliminar[4][0]="";
        eliminar[5][0]="";   
        eliminar[6][0]="";   
        eliminar[7][0]="";   
        eliminar[8][0]=""; 
        
        try {
        read = new DataInputStream(new FileInputStream(path));
        
        while(true){
            
              
                 eliminar[0][i]=read.readInt();
                 eliminar[1][i]=read.readUTF();
                 eliminar[2][i]=read.readUTF();
                 eliminar[3][i]=read.readUTF();
                 eliminar[4][i]=read.readUTF();
                 eliminar[5][i]=read.readUTF();
                 eliminar[6][i]=read.readUTF();
                 eliminar[7][i]=read.readUTF();
                 eliminar[8][i]=read.readUTF();
                 
                 i++;
            }
        } catch (IOException ex) {
       }
        try {
            read.close();
            
        } catch (IOException ex) {
            
        }
        j=0;
        while(z<i){
            if((int)eliminar[0][z]!=cto.getId()){
                datos[0][j]=eliminar[0][z];
                datos[1][j]=eliminar[1][z];
                datos[2][j]=eliminar[2][z];
                datos[3][j]=eliminar[3][z];
                datos[4][j]=eliminar[4][z];
                datos[5][j]=eliminar[5][z];
                datos[6][j]=eliminar[6][z];
                datos[7][j]=eliminar[7][z];
                datos[8][j]=eliminar[8][z];
                
                j++;
            }
            z++;
        }
        i=0;

        try {
            write= new DataOutputStream(new FileOutputStream(path));
            while(i<j){
                write.writeInt((int)datos[0][i]);
                write.writeUTF(datos[1][i].toString());
                write.writeUTF(datos[2][i].toString());
                write.writeUTF(datos[3][i].toString());
                write.writeUTF(datos[4][i].toString());
                write.writeUTF(datos[5][i].toString());
                write.writeUTF(datos[6][i].toString());
                write.writeUTF(datos[7][i].toString());
                write.writeUTF(datos[8][i].toString());
                i++;
         }
        } catch (FileNotFoundException ex) {
      
        }catch(IOException ex){
            
        }finally{
            if(write!=null){
                try{
                      write.close();
                }catch(IOException ex){
                    
                }
            }
        }
    }
}