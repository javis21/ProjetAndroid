/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import java.io.IOException;

/**
 *
 * @author elhou
 */
public class AdminDashboard extends Form {
    
    
    public void showDashboard(Form dash){
    
    
    Image user = null;
    
    Image user_button = null;
    
    Image favorites = null;
    
    Image archive = null;
    
    Image charging = null;
    
    Image log_out = null;
    
    Image delete = null;
    
    Image imageMac = null;
    
    try {

    user = Image.createImage("/user.jpg");
    
    user_button = Image.createImage("/user_button.jpg");
    
    favorites = Image.createImage("/favorites.jpg");
    
    archive = Image.createImage("/archive.jpg");
    
    charging = Image.createImage("/charging.png");

    log_out = Image.createImage("/log_out.png");
    
    delete = Image.createImage("/delete.jpg");
    
    imageMac = Image.createImage("/imageMac.jpg");
    
} catch (IOException ex){


    Dialog.show("Erreur de chargement",ex.getMessage(),"OK",null);

}
   
    Form dashboardForm = new Form(new BoxLayout(BoxLayout.Y_AXIS));
    
    Label userImage = new Label("Samanta Doe",user);
    
    Button editProfile =  new Button("Edit Profile");
    
    dashboardForm.add(userImage);
    
    dashboardForm.add(editProfile);
    
    Label ads = new Label("List Ads",user_button);
    
    dashboardForm.add(ads);
    
    Button favourite = new Button("Favourite Ads",favorites);
    
    dashboardForm.add(favourite);
    
    Button archeved = new Button("Archeved Ads",archive);
    
    dashboardForm.add(archeved);
    
    Button pending = new Button("Pending Approval",charging);
    
    dashboardForm.add(pending);
    
    Button logout = new Button("Logout",log_out);
    
    dashboardForm.add(logout);
    
    Button supprimer = new Button("Delete Account",delete);
    
    dashboardForm.add(supprimer);
    
    TableLayout tablelayout = new TableLayout(2, 3);
    
    Form tableau = new Form("List Ads",tablelayout);
    
    Label im = new Label("IMAGE");
    
    tableau.add(im);
    
    Label productTitle = new Label("PRODUCT TITLE");
    
    tableau.add(productTitle);
    
    Label categoryProduct = new Label("CATEGORY");
    
    tableau.add(categoryProduct);
    
    Label productImage = new Label(imageMac);
    
    tableau.add(productImage);
    
    SpanLabel descriptionProduct = new SpanLabel("Macbook Pro \n"+"15 inch \n"+"Ad \n"+"ID \n"+"ng3D5hAMHPajQrM \n"+"Posted on: \n"+"Jun 27 2017"
    
    +"Status: \n"+"Active \n"+"Location : \n"+"Dhaka.Bangladesh");
    
    tableau.add(descriptionProduct);
    
    dashboardForm.add(tableau);
    
   
    dashboardForm.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,e->dash.showBack()); 
    
    dashboardForm.show();
   
    
    
    }
    
    
}
