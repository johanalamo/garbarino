package com.example.johan.garbarino


class Data {
	companion object {
		fun getUrlProductList    ()         :String = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/"
		fun getUrlProductDetails (id:String):String = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/" + id + "/"
		fun getUrlProductReviews (id:String):String = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/" + id + "/reviews/"


		var productListLoaded: Boolean = false
		var listData:String? = ""
		var productList:Array<Product> = arrayOf()

		lateinit var productDetails:ProductDetailsResponse
		var productDetailsLoaded: Boolean = false

		lateinit var productReviews:ProductReviewsResponse
		var productReviewsLoaded: Boolean = false

		//  detalle: https://garbarino.invisionapp.com/share/W9PRX4JH2RP#/screens/253182891_Detalle
		//  listado: https://garbarino.invisionapp.com/share/WMPROKR32Y9#/screens/256466400_Resultados_-_Mosaico 


		fun getCompleteImageList():Array<Image>{
			if (Data.productDetailsLoaded){
				return Data.productDetails!!.resources!!.images!!
			}else
				return arrayOf()
		}


		fun getReviewList(cantidad:Int? = null):ArrayList<Review>{
			var res:ArrayList<Review> = ArrayList()
			var max:Int = 0;
			if (Data.productReviewsLoaded) {

				if (cantidad == null)
					max = Data.productReviews!!.items!![0]!!.reviews!!.size!! - 1
				else
					if (cantidad > Data.productReviews!!.items!![0]!!.reviews!!.size!!)
						max = Data.productReviews!!.items!![0]!!.reviews!!.size!! - 1
					else
						max = cantidad - 1
				for (i in 0..(max)) {
					try{
						res.add(Data.productReviews!!.items!![0]!!.reviews!![i])
					}catch(e:Exception){
						print("pos invalida: " + i.toString() + "   " )
					}
				}
			}
			return res
		}
	}
}
