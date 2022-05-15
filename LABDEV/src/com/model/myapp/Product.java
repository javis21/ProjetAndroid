/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model.myapp;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import static com.codename1.ui.TextArea.DECIMAL;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.entities.myapp.ProductEntity;
import java.io.IOException;

/**
 *
 * @author elhou
 */
public class Product {
    
    private String name;
    
    private String description;
    
    private String image ;
    
    private int id ;
    
    private String price ;
    
    private String type ;
    
    static String pathImage;
    
    public Product(){
    
    
    }
    
    public Product(String name, String price, String image){
        
        this.name = name;
        
        this.price = price;
        
        this.image = image;
    
        
    }
    
    /*
    public Product( String name, String price, String description){
    
        this.name = name;
        
        this.price = price;
        
        this.description = description;
        
        
    }

*/
    
    
    public Product(String name, String description,String price,String image){
    
        this.name = name;
        
        this.price = price;
        
        this.image = image;
        
        this.description = description;
    }
    
    
    
    public String getName(){
    
    
        return this.name;
    }
    
    public void setName(String name){
    
        this.name = name;
    }
    
    public String getDescription(){
    
        return this.description;
    }
    
    public void setDescription(String description){
    
        this.description = description ;
    }
    
    public String getImage(){
    
        return this.image;
    }
    
    public void setImage(String image){
    
        this.image = image;
    }
    
    public int getId(){
    
        return this.id;
    } 
    
    public void setId(int id){
    
        this.id = id ;
    }

    public String getPrice(){
    
        return this.price;
    }    
    
    public void setPrice(String price){
    
        this.price = price;
    }
    
    public String getType(){
    
        return this.type;
    } 
    
    public void setType(String type){
    
        this.type = type;
    }
    
    
    public void formToCreateProduct(Form pr){
    
       Form addProduct = new Form("Create Product",new BoxLayout(BoxLayout.Y_AXIS));
        
       TextField productName = new TextField();
       
       productName.setHint("Nom du produit");
       
       addProduct.add(productName);
       
       TextArea productDescription = new TextArea("",100);
       
       productDescription.setHint("Décrire le Produit");
       
       addProduct.add(productDescription);
       
       TextField priceProduct = new TextField();
    
       priceProduct.setHint("Prix du Produit");
       
       priceProduct.setConstraint(DECIMAL);
       
       addProduct.add(priceProduct);
       
       Button uploadButton = new Button("Choisir L'image");
       
       addProduct.add(uploadButton);
       
       uploadButton.addActionListener((e)->{
       
           
         pathImage = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
       
          if( pathImage != null){
          
              try {
              
                  Image imageUploaded = Image.createImage(pathImage);
                  
              } catch (IOException ex ){
              
                  ex.printStackTrace();
                  
              }
              
          
          }
          
       });
       
       
       Button create = new Button("Créer le Produit");
       
       addProduct.add(create);
       
       create.addActionListener((e)->{
       
           String nom = productName.getText();
           
           String descrip = productDescription.getText();
           
           String prix =priceProduct.getText();
           
           if( nom=="" || descrip=="" || prix =="" || pathImage ==""){
               
               Dialog.show("Avertissement !","Veuillez remplir les champs vides","Ok",null);
           
           }
           
           else{
               
           Product ajoutProduit = new Product( nom, descrip, prix, pathImage );
           
           ProductEntity productEntity = new ProductEntity();
           
           productEntity.addProduct(ajoutProduit);
           
           productName.clear();
           
           productDescription.clearClientProperties();
           
           priceProduct.clear();
           
           }
       
       });
       
       
       
    Form formOfProduct= new Form("");
    
    formOfProduct.add(addProduct);
    
     formOfProduct.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,e->pr.showBack());
       
    formOfProduct.show();
    
       
       
    }
    
   
    
    
    
}
