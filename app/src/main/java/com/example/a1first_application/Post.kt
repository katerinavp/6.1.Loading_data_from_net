package com.example.a1first_application

data class Post(
        var type: PostTypes,
        val id: Int,
        val date: String?,
        val author: String?,
        val content: String?,
        val adress: String?,
        val location: Pair<Double?, Double?>?,
        val repost: String?,
        val video:String?,
        val adv:String?


        ) {

    override fun toString(): String {
        return "Post($type, $id, $date, $author, $content, $adress, $location, $repost, $video, $$adv)"
    }


}




