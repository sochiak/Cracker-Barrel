package Checkers;


/**
 * File Name: checkersPiece.java
 * Author: Charlie Shahan
 * Date: Created: 4/22/2022
 * Purpose: Abstract class for a checkers piece
 */

abstract class CheckersPiece
{
    String color;

    CheckersPiece(String color)
    {
        this.color = color;
    }


   public String getColor()
   {
       return color;
   }
   abstract public void move();

   
}
