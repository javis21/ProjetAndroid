/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities.myapp;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.model.myapp.Product;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author elhou
 */
public class ProductEntity extends Form{
    
    public static List productCart = new ArrayList();
    
    static Button buttonAdd;
    
    public ProductEntity(Form previous){
    
        BoxLayout layout = new BoxLayout(BoxLayout.Y_AXIS);
        
        setLayout(layout);
    };
    
    public ProductEntity(){
    
    }
    
    
    //Page et Formulaire d'ajout des produits dans la base de données.
    
    public void addProduct(Product product){
        
        
        Database db;
        
        try {
        
            db = Database.openOrCreate("labdev.db");
            
            
           
            //db.execute("create table product ( id_product int primary key, name varchar2 not null, description varchar2 not null, price number not null, image varchar2 not null )");
            
           
            db.execute("insert into product (name, description, price, image) values('"+product.getName()+"','"+product.getDescription()+"','"+product.getPrice()+"','"+product.getImage()+"')");
            
            Dialog.show(null,"Produit crée avec succès","OK",null);
            
            
        
        } catch(IOException ex){
        
           Dialog.show("Erreur de requete",ex.getMessage(),"OK",null);
        
        }
        
        
    }
    
    public void showAllProduct(Form voir){
    
        Database database;
        
        try {
            
            database = Database.openOrCreate("labdev.db");
            
            Cursor c = database.executeQuery("select name,price,image from product");
            
            //Page d'affichage de tous les produits qui existent dans la base de données.
            
            Form productExist = new Form(new BoxLayout(BoxLayout.Y_AXIS));
            
           
            //Boutton permettant de créer des produits et les ajouter dans la base de données.
            
            Button createPage = new Button("Create product");
        
            productExist.add(createPage);
        
            createPage.addActionListener((e)->{
            
            Product p = new Product();
        
            p.formToCreateProduct(productExist);
        
        });
            //Recupération des produits stockés dans la base de données.
            
            while(c.next()){
                
                Row ligne = c.getRow();
                
                String nomDuProduit = ligne.getString(0);
                
                String prixDuProduit = ligne.getString(1);
                
                String imageDuProduit = ligne.getString(2);
                
                Image extractImage = null;
                        
                        try {
                        
                            extractImage = Image.createImage(ligne.getString(2));
                            
                        } catch(IOException ex){
                        
                            Dialog.show("Errreur d'importation de l'image",ex.getMessage(),"OK",null);
                        }
                        
                        Label produit = new Label(nomDuProduit+" "+prixDuProduit+"",extractImage);
                       
                        buttonAdd = new Button("Ajouter au Panier");
                        
                        productExist.add(produit);
                        
                        productExist.add(buttonAdd);
                        
                        
                        buttonAdd.addActionListener(new ActionListener() {
                            
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Form panier = new Form();
                        
                        
                        /*
                        public void oncreateAccountActionEvent(){
                        
                        showCart.show();
                        };
                        };
                        
                        */
                        // String nomProduit = produit.getText();
                           
                        //Image imageProduit = produit.getIcon();
                        
                        Product products = new Product(nomDuProduit,prixDuProduit,imageDuProduit);    
                        
                        //productCart = new ArrayList();
                        
                        try {
                            
                            //database.execute("create table panier (nom varchar2 primary key, prix number, image varchar2)");
                            
                            database.execute("insert into panier values('"+nomDuProduit+"','"+prixDuProduit+"','"+imageDuProduit+"')");
                                    
                            
                        } catch(IOException ex){
                            
                            Dialog.show("Erreur",ex.getMessage(),"OK",null);
                        }
                        
                        productCart.add(products);
                          
                        Dialog.show(null,"Produit ajouté avec succès !!!","OK",null);
                        
                        // Label lie = new Label(nomProduit,imageProduit);
            
                        //panier.add(lie);
                        
                        //panier.show();
                    }
                });
                      
            
            }
            
             productExist.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,e->voir.showBack());
            
            productExist.show();
            
        
        } catch(IOException ex){
        
        Dialog.show("Erreur de requete",ex.getMessage(),"OK",null);
        
        }
        
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,null);
        
        
    }
    
   
    public void displayProductInCart(Form accessCart){
    
        Database base;
      
        
            Form panier = new Form();
            
            Button btn = new Button("Voir les produits commandés");
            
            panier.add(btn);
               
               try {
                   
                   base = Database.openOrCreate("labdev.db");
                   
                   Cursor cursor = base.executeQuery("select nom,prix,image from panier" );
                   
                   while(cursor.next()){
                   
                       Row lign = cursor.getRow();
                       
                       String NAME = lign.getString(0);
                       String PRIX = lign.getString(1);
                       
                       Image m1 = null;
                               
                       try{
                       
                           m1 = Image.createImage(lign.getString(2));
                           
                           
                       } catch(IOException ex){
                           
                           Dialog.show("Erreur de chargement de l'image",ex.getMessage(),"OK",null);
                       
                       }
                       
                       Label l1 = new Label(NAME+""+PRIX+"",m1);
                       
                       Button b1 = new Button("Commander le produit");
                       
                       Button b2 = new Button("Annuler la commande");
                       
                       Button b3 = new Button("Supprimer du Panier");
                       
                       panier.add(l1);
                       
                       panier.add(b1);
                       
                       panier.add(b2);
                       
                       panier.add(b3);
                       
                       b1.addActionListener((e)->{
                       
                           try {
                               
                              // base.execute("create table commande (nom varchar2 primary key, prix varchar2)");
                              
                               
                               base.execute("insert into commande values('"+NAME+"','"+PRIX+"')");
                               
                               Dialog.show(null,"Commande effectué avec succès","OK",null);
                               
                               Message m = new Message("Hello how are you ?? I'm from Codenameone");
                                
                               String textAttachmentUri="Hello this is a test";
                               
                               m.getAttachments().put(textAttachmentUri, "text/plain");
                               
                               String imageAttachmentUri="";
                               
                               m.getAttachments().put(imageAttachmentUri, "image/png");
                              
                              Display.getInstance().sendMessage(new String[] {"test@exemple.com"}, "test d'envoie d'email", m);
                               
                               
                           } catch (IOException ex) {
                               
                               Dialog.show("Erreur d'exécution de la requete",ex.getMessage(),"OK",null);
                           }
                           
                       });
                       
                      
                       
                       b2.addActionListener((e)->{
                       
                           try {
                           
                           
                               base.execute("delete from commande where(nom='"+NAME+"')");
                               
                               Dialog.show(null,"Commande annulé avec succès","OK",null);
                               
                           
                           } catch(IOException ex){
                           
                               Dialog.show("Erreur d'exécution de la requete",ex.getMessage(),"OK",null);
                           }
                       
                       
                       });
                       
                        b3.addActionListener((e)->{
                       
                           try {
                              
                           
                               base.execute("delete from panier where(nom='"+NAME+"')");
                               
                               panier.removeComponent(l1);
                               
                               panier.removeComponent(b1);
                               
                               panier.removeComponent(b2);
                               
                               panier.removeComponent(b3);
                               
                               Dialog.show(null,"Produit supprimé du panier avec succès","OK",null);
                               
                           
                           } catch(IOException ex){
                           
                               Dialog.show("Erreur d'exécution de la requete",ex.getMessage(),"OK",null);
                           }
                       
                       
                       });
                       
                       
                       
                       
                       
                   
                   } 
                   
              
                   panier.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,e->accessCart.showBack());
                   
                   panier.show();
            
                   
               } catch(IOException ex){
               
               
                   Dialog.show("Erreur de requete",ex.getMessage(),"OK",null);
               }
               
              
               
               
           }
           
            
            
            
            
        
   
        
    
}
