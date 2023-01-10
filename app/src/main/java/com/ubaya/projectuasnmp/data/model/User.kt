import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("msg")
    val msg: String? = null
) {
    data class Data(
        @SerializedName("avatar")
        var avatar: String? = null,
        @SerializedName("created_At")
        val createdAt: String? = null,
        @SerializedName("firstname")
        val firstname: String? = null,
        @SerializedName("idUser")
        val idUser: String? = null,
        @SerializedName("isHidden")
        var isHidden: String? = null,
        @SerializedName("lastname")
        val lastname: String? = null,
        @SerializedName("password")
        val password: String? = null,
        @SerializedName("username")
        val username: String? = null
    )
}