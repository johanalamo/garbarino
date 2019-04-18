package com.example.johan.garbarino

import com.google.gson.annotations.SerializedName

class ProductReviewsResponse {

    @SerializedName("items")
    var items:Array<Item>? = arrayOf()

//    @SerializedName("id")
//    var id: String? = null

//    @SerializedName("description")
//    var description: String? = null

//    @SerializedName("main_image")
//    var mainImage: Image? = null

//    @SerializedName("resources")
//    var resources: Resources? = null


//    @SerializedName("coord")
//    var coord: Coord? = null
//    @SerializedName("sys")
//    var sys: Sys? = null
//    @SerializedName("weather")
//    var weather = ArrayList<Weather>()
//    @SerializedName("main")
//    var main: Main? = null
//    @SerializedName("wind")
//    var wind: Wind? = null
//    @SerializedName("rain")
//    var rain: Rain? = null
//    @SerializedName("clouds")
//    var clouds: Clouds? = null
//    @SerializedName("dt")
//    var dt: Float = 0.toFloat()
//    @SerializedName("id")
//    var id: Int = 0
//    @SerializedName("name")
//    var name: String? = null
//    @SerializedName("cod")
//    var cod: Float = 0.toFloat()

}
class Item {

    @SerializedName("id")
    var id:String? = null

    @SerializedName("review_statistics")
    var reviewStatistics: ReviewStatistics? = ReviewStatistics()

    @SerializedName("reviews")
    var reviews: Array<Review>? = arrayOf()
}

class ReviewStatistics {
    @SerializedName("average_overall_rating")
    var average:Float? = 0.toFloat()
}

class Review {

    @SerializedName("id")
    var id:String? = null

    @SerializedName("usernickname")
    var userNickname:String? = null

    @SerializedName("title")
    var title:String? = null

    @SerializedName("review_text")
    var reviewText:String? = null

    @SerializedName("rating")
    var rating:Float? = 0.toFloat()

    @SerializedName("submission_time")
    var submissionTime:String? = null

    @SerializedName("product_id")
    var productId:String? = null
}

/*
class Image {
    @SerializedName("max_width")
    var maxWidth: Int = 0
    @SerializedName("url")
    var url: String? = null
}

class Resources {
    @SerializedName("images")
    var images: Array<Image> = arrayOf()
}
*/
