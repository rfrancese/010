package com.example.fantaproject;

import java.util.ArrayList;
import java.util.List;

public class Group {

  public String string;
  public String string2;
  public final List<String> children = new ArrayList<String>();
  public final List<String> children2 = new ArrayList<String>();


  
   
  public Group(String string) {
    this.string = string;
  }
  
  public void setSquadraCasa(String casa){
	  this.string = casa;
  }
  
  public void setSquadraFuori(String fuori){
	  this.string2 = fuori;
  }

} 
