package com.example.johan.garbarino

data class Product(val id:String = "",
                  val description:String = "",
                  val image_url:String = "",
                  val price:Int = 0,
                  val list_price:Int = 0,
                  val discount:Int = 0
            )
