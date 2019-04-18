package com.example.johan.garbarino


class Data {
	companion object {

		//  detalle: https://garbarino.invisionapp.com/share/W9PRX4JH2RP#/screens/253182891_Detalle
		//  listado: https://garbarino.invisionapp.com/share/WMPROKR32Y9#/screens/256466400_Resultados_-_Mosaico 
		fun getUrlProductList    ()         :String = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/"
		fun getUrlProductDetails (id:String):String = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/" + id + "/"
		fun getUrlProductReviews (id:String):String = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/" + id + "/reviews/"


		var listReady: Boolean = false
		var listData:String? = ""
      var listDataArray:Array<Product> = arrayOf()
		
		var detailsReady: Boolean = false
		var detailsData:String? = ""
		
		lateinit var productDetails:ProductDetailsResponse
		
		var reviewsReady: Boolean = false
		var reviewsData:String? = ""

		lateinit var productReviews:ProductReviewsResponse
	}

}
