import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post(
    @SerializedName("data")
    val `data`: MutableList<Data>,
    @SerializedName("msg")
    val msg: String? = null
) {
    data class Data(
        @SerializedName("avatar")
        val avatar: String? = null,
        @SerializedName("bottomText")
        val bottomText: String? = null,
        @SerializedName("idPost")
        val idPost: String? = null,
        @SerializedName("idUser")
        val idUser: String? = null,
        @SerializedName("isHidden")
        val isHidden: String? = null,
        @SerializedName("liked")
        var liked: String? = null,
        @SerializedName("topText")
        val topText: String? = null,
        @SerializedName("totalLike")
        var totalLike: String? = null,
        @SerializedName("url")
        val url: String? = null,
        @SerializedName("username")
        val username: String? = null,
        @SerializedName("totalComment")
        val totalComment: String? = null,
        @SerializedName("created_at")
        val createdAt: String? = null
    ): Serializable
}
