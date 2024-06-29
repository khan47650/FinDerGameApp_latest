package com.example.findergamesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findergamesapp.interfaces.OnPassColorLevelNumber;
import com.example.findergamesapp.utils.SharedPreferenceClass;

public class ColorLevelActivity extends AppCompatActivity implements OnPassColorLevelNumber {
    RecyclerView recyclerView;
    private int currentUnlockedLevel;
    LevelsAdapter adapter;
    ImageView ivrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_level_activtity);
        recyclerView = findViewById(R.id.recyclerView);

        SharedPreferenceClass sharedPreferenceClass=new SharedPreferenceClass(this);
        currentUnlockedLevel = sharedPreferenceClass.getCurrentLevel();


        recyclerView.setLayoutManager(new LinearLayoutManager(ColorLevelActivity.this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        adapter = new LevelsAdapter(this, this, currentUnlockedLevel);
        recyclerView.setAdapter(adapter);
        ivrow=findViewById(R.id.ivArrow);

        ivrow.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),ActivityHome.class);
            startActivity(intent);
            finish();
        });
    }
    @Override
    public void passColor(int levelNumber) {
        if(levelNumber==1){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.gradientStart)
                    .putExtra("color2",R.color.gradientEnd)
                    .putExtra("color3",R.color.grey)
                    .putExtra("color4",R.color.purple)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","GradientStart")
                    .putExtra("name2","GradientEnd")
                    .putExtra("name3","Grey")
                    .putExtra("name4","Purple")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","1")
            );
        }
        else if(levelNumber==2){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.skin)
                    .putExtra("color2",R.color.purpledark)
                    .putExtra("color3",R.color.lightgrey)
                    .putExtra("color4",R.color.purple2)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Skin")
                    .putExtra("name2","PurpleDark")
                    .putExtra("name3","LightGrey")
                    .putExtra("name4","Purple2")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","2")
            );
        }  else if(levelNumber==3){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.red)
                    .putExtra("color2",R.color.pink)
                    .putExtra("color3",R.color.darkred)
                    .putExtra("color4",R.color.lightred)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Red")
                    .putExtra("name2","Pink")
                    .putExtra("name3","DarkRed")
                    .putExtra("name4","LightRed")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","3")
            );
        }else if(levelNumber==4){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.green)
                    .putExtra("color2",R.color.lightgreen)
                    .putExtra("color3",R.color.blue)
                    .putExtra("color4",R.color.yellow)
                    .putExtra("color6",R.color.black)
                    .putExtra("white",R.color.white)
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","4")
            );
        }else if(levelNumber==5){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Hickory)
                    .putExtra("color2",R.color.Walnut)
                    .putExtra("color3",R.color.Russet)
                    .putExtra("color4",R.color.Espresso)
                    .putExtra("color6",R.color.black)
                    .putExtra("white",R.color.white)
                    .putExtra("name1","Hickory")
                    .putExtra("name2","Walnut")
                    .putExtra("name3","Russet")
                    .putExtra("name4","Espresso")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","5")
            );
        }else if(levelNumber==6){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.lightBlue)
                    .putExtra("color2",R.color.baby)
                    .putExtra("color3",R.color.Pecan)
                    .putExtra("color4",R.color.Navy)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","LightBlue")
                    .putExtra("name2","Baby")
                    .putExtra("name3","Pecan")
                    .putExtra("name4","Navy")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","6")
            );
        }
        else if (levelNumber==7){
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Sapphire)
                    .putExtra("color2",R.color.Maya)
                    .putExtra("color3",R.color.Space)
                    .putExtra("color4",R.color.Azure)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Sapphire")
                    .putExtra("name2","Maya")
                    .putExtra("name3","Space")
                    .putExtra("name4","Azure")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","7")
            );
        } else if (levelNumber==8) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Olympic)
                    .putExtra("color2",R.color.Corn)
                    .putExtra("color3",R.color.lemon)
                    .putExtra("color4",R.color.Flax)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Olympic")
                    .putExtra("name2","Corn")
                    .putExtra("name3","lemon")
                    .putExtra("name4","Flax")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","8")
            );

            
        } else if (levelNumber==9) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.sepia)
                    .putExtra("color2",R.color.Cream)
                    .putExtra("color3",R.color.Chili)
                    .putExtra("color4",R.color.FireBrick)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Sepia")
                    .putExtra("name2","Cream")
                    .putExtra("name3","Chili")
                    .putExtra("name4","FireBrick")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","9")
            );
            
        } else if (levelNumber==10) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.UsaFlag)
                    .putExtra("color2",R.color.Apple)
                    .putExtra("color3",R.color.Mahogany)
                    .putExtra("color4",R.color.Carmine)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","UsaFlag")
                    .putExtra("name2","Apple")
                    .putExtra("name3","Mahogany")
                    .putExtra("name4","Carmine")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","10")
            );
            
        } else if (levelNumber==11) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Persion)
                    .putExtra("color2",R.color.Scarlet)
                    .putExtra("color3",R.color.Ferrari)
                    .putExtra("color4",R.color.Jade)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Persion")
                    .putExtra("name2","Scarlet")
                    .putExtra("name3","Ferrari")
                    .putExtra("name4","Jade")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","11")
            );
            
        }
        else if (levelNumber == 12) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Artichoke)
                    .putExtra("color2",R.color.Tea)
                    .putExtra("color3",R.color.Moss)
                    .putExtra("color4",R.color.Sea)
                    .putExtra("color5",R.color.black)
                    .putExtra("white",R.color.white)
                    .putExtra("name1","Artichoke")
                    .putExtra("name2","Tea")
                    .putExtra("name3","Moss")
                    .putExtra("name4","Sea")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","12")
            );

        } else if (levelNumber == 13) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Army)
                    .putExtra("color2",R.color.Pine)
                    .putExtra("color3",R.color.Sage)
                    .putExtra("color4",R.color.Mint)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Army")
                    .putExtra("name2","Pine")
                    .putExtra("name3","Sage")
                    .putExtra("name4","Mint")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","13")
            );
        } else if (levelNumber == 14) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Thunder)
                    .putExtra("color2",R.color.Seal)
                    .putExtra("color3",R.color.Iron)
                    .putExtra("color4",R.color.Charcoal)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Thunder")
                    .putExtra("name2","Seal")
                    .putExtra("name3","Iron")
                    .putExtra("name4","Charcoal")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","14")
            );
        } else if (levelNumber == 15) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Lava)
                    .putExtra("color2",R.color.Trout)
                    .putExtra("color3",R.color.Mink)
                    .putExtra("color4",R.color.Coffee)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Lava")
                    .putExtra("name2","Trout")
                    .putExtra("name3","Mink")
                    .putExtra("name4","Coffee")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","15")
            );
        } else if (levelNumber == 16) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Umber)
                    .putExtra("color2",R.color.Tawny)
                    .putExtra("color3",R.color.Caramel)
                    .putExtra("color4",R.color.Bread)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Umber")
                    .putExtra("name2","Tawny")
                    .putExtra("name3","Caramel")
                    .putExtra("name4","Bread")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","16")
            );
        } else if (levelNumber == 17) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Peanut)
                    .putExtra("color2",R.color.Cedar)
                    .putExtra("color3",R.color.Brown)
                    .putExtra("color4",R.color.Steel)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Peanut")
                    .putExtra("name2","Cedar")
                    .putExtra("name3","Brown")
                    .putExtra("name4","Steel")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","17")
            );
        } else if (levelNumber == 18) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Sky)
                    .putExtra("color2",R.color.Independence)
                    .putExtra("color3",R.color.Prussian)
                    .putExtra("color4",R.color.AirForce)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Sky")
                    .putExtra("name2","Independence")
                    .putExtra("name3","Prussian")
                    .putExtra("name4","AirForce")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","18")
            );
        } else if (levelNumber == 19) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Denim)
                    .putExtra("color2",R.color.Turkish)
                    .putExtra("color3",R.color.Carolina)
                    .putExtra("color4",R.color.Egyption)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Denim")
                    .putExtra("name2","Turkish")
                    .putExtra("name3","Carolina")
                    .putExtra("name4","Egyption")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","19")
            );
        } else if (levelNumber == 20) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Yale)
                    .putExtra("color2",R.color.Flaxen)
                    .putExtra("color3",R.color.Peach)
                    .putExtra("color4",R.color.Laguna)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Yale")
                    .putExtra("name2","Flaxen")
                    .putExtra("name3","Peach")
                    .putExtra("name4","Laguna")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","20")
            );
        } else if (levelNumber == 21) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Mustard)
                    .putExtra("color2",R.color.Raspberry)
                    .putExtra("color3",R.color.Salmon)
                    .putExtra("color4",R.color.Tangerina)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Mustard")
                    .putExtra("name2","Raspberry")
                    .putExtra("name3","Salmon")
                    .putExtra("name4","Tangerina")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","21")
            );
        } else if (levelNumber == 22) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Fire)
                    .putExtra("color2",R.color.Amber)
                    .putExtra("color3",R.color.Ochre)
                    .putExtra("color4",R.color.Rust)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Fire")
                    .putExtra("name2","Amber")
                    .putExtra("name3","Ochre")
                    .putExtra("name4","Rust")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","22")
            );
        } else if (levelNumber == 23) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Honey)
                    .putExtra("color2",R.color.Fern)
                    .putExtra("color3",R.color.Olive)
                    .putExtra("color4",R.color.Lime)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Honey")
                    .putExtra("name2","Fern")
                    .putExtra("name3","Olive")
                    .putExtra("name4","Lime")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","23")
            );
        } else if (levelNumber == 24) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Sacramento)
                    .putExtra("color2",R.color.Hunter)
                    .putExtra("color3",R.color.Kelly)
                    .putExtra("color4",R.color.Jungle)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Sacramento")
                    .putExtra("name2","Hunter")
                    .putExtra("name3","Kelly")
                    .putExtra("name4","Jungle")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","24")
            );
        } else if (levelNumber == 25) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Fossil)
                    .putExtra("color2",R.color.Shadow)
                    .putExtra("color3",R.color.Fandango)
                    .putExtra("color4",R.color.Electric)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Fossil")
                    .putExtra("name2","Shadow")
                    .putExtra("name3","Fandango")
                    .putExtra("name4","Electric")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","25")
            );
        } else if (levelNumber == 26) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Royal)
                    .putExtra("color2",R.color.Grape)
                    .putExtra("color3",R.color.Lavender)
                    .putExtra("color4",R.color.Taffy)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Royal")
                    .putExtra("name2","Grape")
                    .putExtra("name3","Lavender")
                    .putExtra("name4","Taffy")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","26")
            );
        } else if (levelNumber == 27) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Carnation)
                    .putExtra("color2",R.color.Fuchsila)
                    .putExtra("color3",R.color.Magenta)
                    .putExtra("color4",R.color.Rose)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Carnation")
                    .putExtra("name2","Fuchsila")
                    .putExtra("name3","Magenta")
                    .putExtra("name4","Rose")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","27")
            );
        } else if (levelNumber == 28) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Cerise)
                    .putExtra("color2",R.color.French)
                    .putExtra("color3",R.color.Mulberry)
                    .putExtra("color4",R.color.Violet)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Cerise")
                    .putExtra("name2","French")
                    .putExtra("name3","Mulberry")
                    .putExtra("name4","Violet")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","28")
            );
        } else if (levelNumber == 29) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Carnation)
                    .putExtra("color2",R.color.Fuchsila)
                    .putExtra("color3",R.color.Mulberry)
                    .putExtra("color4",R.color.Violet)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Carnation")
                    .putExtra("name2","Fuchsila")
                    .putExtra("name3","Mulberry")
                    .putExtra("name4","Violet")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","29")
            );
        } else if (levelNumber == 30) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Royal)
                    .putExtra("color2",R.color.Grape)
                    .putExtra("color3",R.color.Fandango)
                    .putExtra("color4",R.color.Electric)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Royal")
                    .putExtra("name2","Grape")
                    .putExtra("name3","Fandango")
                    .putExtra("name4","Electric")
                    .putExtra("level","30")
            );
        } else if (levelNumber == 31) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Fossil)
                    .putExtra("color2",R.color.Shadow)
                    .putExtra("color3",R.color.Lavender)
                    .putExtra("color4",R.color.Taffy)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Fossil")
                    .putExtra("name2","Shadow")
                    .putExtra("name3","Lavender")
                    .putExtra("name4","Taffy")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","31")
            );
        } else if (levelNumber == 32) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Sacramento)
                    .putExtra("color2",R.color.Hunter)
                    .putExtra("color3",R.color.Olive)
                    .putExtra("color4",R.color.Lime)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Sacramento")
                    .putExtra("name2","Hunter")
                    .putExtra("name3","Olive")
                    .putExtra("name4","Lime")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","32")
            );
        } else if (levelNumber == 33) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Honey)
                    .putExtra("color2",R.color.Fern)
                    .putExtra("color3",R.color.Kelly)
                    .putExtra("color4",R.color.Jungle)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Honey")
                    .putExtra("name2","Fern")
                    .putExtra("name3","Kelly")
                    .putExtra("name4","Jungle")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","33")
            );
        } else if (levelNumber == 34) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Fire)
                    .putExtra("color2",R.color.Amber)
                    .putExtra("color3",R.color.Salmon)
                    .putExtra("color4",R.color.Tangerina)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Fire")
                    .putExtra("name2","Amber")
                    .putExtra("name3","Salmon")
                    .putExtra("name4","Tangerina")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","34")
            );
        } else if (levelNumber == 35) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Mustard)
                    .putExtra("color2",R.color.Raspberry)
                    .putExtra("color3",R.color.Peach)
                    .putExtra("color4",R.color.Laguna)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Mustard")
                    .putExtra("name2","Raspberry")
                    .putExtra("name3","Peach")
                    .putExtra("name4","Laguna")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","35")
            );
        } else if (levelNumber == 36) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Yale)
                    .putExtra("color2",R.color.Flaxen)
                    .putExtra("color3",R.color.Carolina)
                    .putExtra("color4",R.color.Egyption)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Yale")
                    .putExtra("name2","Flaxen")
                    .putExtra("name3","Carolina")
                    .putExtra("name4","Egyption")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","36")
            );
        } else if (levelNumber == 37) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Denim)
                    .putExtra("color2",R.color.Turkish)
                    .putExtra("color3",R.color.Prussian)
                    .putExtra("color4",R.color.AirForce)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Denim")
                    .putExtra("name2","Turkish")
                    .putExtra("name3","Prussian")
                    .putExtra("name4","AirForce")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","37")
            );
        } else if (levelNumber == 38) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Sky)
                    .putExtra("color2",R.color.Independence)
                    .putExtra("color3",R.color.Brown)
                    .putExtra("color4",R.color.Steel)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Sky")
                    .putExtra("name2","Independence")
                    .putExtra("name3","Brown")
                    .putExtra("name4","Steel")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","38")
            );
        } else if (levelNumber == 39) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Peanut)
                    .putExtra("color2",R.color.Cedar)
                    .putExtra("color3",R.color.Caramel)
                    .putExtra("color4",R.color.Bread)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Peanut")
                    .putExtra("name2","Cedar")
                    .putExtra("name3","Caramel")
                    .putExtra("name4","Bread")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","39")
            );
        } else if (levelNumber == 40) {
            startActivity(new Intent(getApplicationContext(),ActivityColors_1.class)
                    .putExtra("color1",R.color.Umber)
                    .putExtra("color2",R.color.Tawny)
                    .putExtra("color3",R.color.Mink)
                    .putExtra("color4",R.color.Coffee)
                    .putExtra("color5",R.color.black)
                    .putExtra("color6",R.color.white)
                    .putExtra("name1","Umber")
                    .putExtra("name2","Tawny")
                    .putExtra("name3","Mink")
                    .putExtra("name4","Coffee")
                    .putExtra("name5","black")
                    .putExtra("name6","white")
                    .putExtra("level","40")
            );
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityHome.class);
        startActivity(intent);
        finish();
    }
}