package com.example.pixhub.data.remote.model


import com.google.gson.annotations.SerializedName

data class UnsplashResponse(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class Result(
        @SerializedName("alt_description")
        val altDescription: String,
        @SerializedName("blur_hash")
        val blurHash: String,
        @SerializedName("color")
        val color: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("current_user_collections")
        val currentUserCollections: List<Any>,
        @SerializedName("description")
        val description: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("id")
        val id: String,
        @SerializedName("liked_by_user")
        val likedByUser: Boolean,
        @SerializedName("likes")
        val likes: Int,
        @SerializedName("links")
        val links: Links,
        @SerializedName("promoted_at")
        val promotedAt: String,
        @SerializedName("sponsorship")
        val sponsorship: Any,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("topic_submissions")
        val topicSubmissions: TopicSubmissions,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("urls")
        val urls: Urls,
        @SerializedName("user")
        val user: User,
        @SerializedName("width")
        val width: Int
    ) {
        data class Links(
            @SerializedName("download")
            val download: String,
            @SerializedName("download_location")
            val downloadLocation: String,
            @SerializedName("html")
            val html: String,
            @SerializedName("self")
            val self: String
        )

        data class Tag(
            @SerializedName("source")
            val source: Source,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String
        ) {
            data class Source(
                @SerializedName("ancestry")
                val ancestry: Ancestry,
                @SerializedName("cover_photo")
                val coverPhoto: CoverPhoto,
                @SerializedName("description")
                val description: String,
                @SerializedName("meta_description")
                val metaDescription: String,
                @SerializedName("meta_title")
                val metaTitle: String,
                @SerializedName("subtitle")
                val subtitle: String,
                @SerializedName("title")
                val title: String
            ) {
                data class Ancestry(
                    @SerializedName("category")
                    val category: Category,
                    @SerializedName("subcategory")
                    val subcategory: Subcategory,
                    @SerializedName("type")
                    val type: Type
                ) {
                    data class Category(
                        @SerializedName("pretty_slug")
                        val prettySlug: String,
                        @SerializedName("slug")
                        val slug: String
                    )

                    data class Subcategory(
                        @SerializedName("pretty_slug")
                        val prettySlug: String,
                        @SerializedName("slug")
                        val slug: String
                    )

                    data class Type(
                        @SerializedName("pretty_slug")
                        val prettySlug: String,
                        @SerializedName("slug")
                        val slug: String
                    )
                }

                data class CoverPhoto(
                    @SerializedName("alt_description")
                    val altDescription: String,
                    @SerializedName("blur_hash")
                    val blurHash: String,
                    @SerializedName("color")
                    val color: String,
                    @SerializedName("created_at")
                    val createdAt: String,
                    @SerializedName("current_user_collections")
                    val currentUserCollections: List<Any>,
                    @SerializedName("description")
                    val description: String,
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("liked_by_user")
                    val likedByUser: Boolean,
                    @SerializedName("likes")
                    val likes: Int,
                    @SerializedName("links")
                    val links: Links,
                    @SerializedName("premium")
                    val premium: Boolean,
                    @SerializedName("promoted_at")
                    val promotedAt: String,
                    @SerializedName("sponsorship")
                    val sponsorship: Any,
                    @SerializedName("topic_submissions")
                    val topicSubmissions: TopicSubmissions,
                    @SerializedName("updated_at")
                    val updatedAt: String,
                    @SerializedName("urls")
                    val urls: Urls,
                    @SerializedName("user")
                    val user: User,
                    @SerializedName("width")
                    val width: Int
                ) {
                    data class Links(
                        @SerializedName("download")
                        val download: String,
                        @SerializedName("download_location")
                        val downloadLocation: String,
                        @SerializedName("html")
                        val html: String,
                        @SerializedName("self")
                        val self: String
                    )

                    data class TopicSubmissions(
                        @SerializedName("nature")
                        val nature: Nature,
                        @SerializedName("textures-patterns")
                        val texturesPatterns: TexturesPatterns,
                        @SerializedName("wallpapers")
                        val wallpapers: Wallpapers
                    ) {
                        data class Nature(
                            @SerializedName("approved_on")
                            val approvedOn: String,
                            @SerializedName("status")
                            val status: String
                        )

                        data class TexturesPatterns(
                            @SerializedName("approved_on")
                            val approvedOn: String,
                            @SerializedName("status")
                            val status: String
                        )

                        data class Wallpapers(
                            @SerializedName("approved_on")
                            val approvedOn: String,
                            @SerializedName("status")
                            val status: String
                        )
                    }

                    data class Urls(
                        @SerializedName("full")
                        val full: String,
                        @SerializedName("raw")
                        val raw: String,
                        @SerializedName("regular")
                        val regular: String,
                        @SerializedName("small")
                        val small: String,
                        @SerializedName("small_s3")
                        val smallS3: String,
                        @SerializedName("thumb")
                        val thumb: String
                    )

                    data class User(
                        @SerializedName("accepted_tos")
                        val acceptedTos: Boolean,
                        @SerializedName("bio")
                        val bio: String,
                        @SerializedName("first_name")
                        val firstName: String,
                        @SerializedName("for_hire")
                        val forHire: Boolean,
                        @SerializedName("id")
                        val id: String,
                        @SerializedName("instagram_username")
                        val instagramUsername: String,
                        @SerializedName("last_name")
                        val lastName: String,
                        @SerializedName("links")
                        val links: Links,
                        @SerializedName("location")
                        val location: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("portfolio_url")
                        val portfolioUrl: String,
                        @SerializedName("profile_image")
                        val profileImage: ProfileImage,
                        @SerializedName("social")
                        val social: Social,
                        @SerializedName("total_collections")
                        val totalCollections: Int,
                        @SerializedName("total_likes")
                        val totalLikes: Int,
                        @SerializedName("total_photos")
                        val totalPhotos: Int,
                        @SerializedName("twitter_username")
                        val twitterUsername: String,
                        @SerializedName("updated_at")
                        val updatedAt: String,
                        @SerializedName("username")
                        val username: String
                    ) {
                        data class Links(
                            @SerializedName("followers")
                            val followers: String,
                            @SerializedName("following")
                            val following: String,
                            @SerializedName("html")
                            val html: String,
                            @SerializedName("likes")
                            val likes: String,
                            @SerializedName("photos")
                            val photos: String,
                            @SerializedName("portfolio")
                            val portfolio: String,
                            @SerializedName("self")
                            val self: String
                        )

                        data class ProfileImage(
                            @SerializedName("large")
                            val large: String,
                            @SerializedName("medium")
                            val medium: String,
                            @SerializedName("small")
                            val small: String
                        )

                        data class Social(
                            @SerializedName("instagram_username")
                            val instagramUsername: String,
                            @SerializedName("paypal_email")
                            val paypalEmail: Any,
                            @SerializedName("portfolio_url")
                            val portfolioUrl: String,
                            @SerializedName("twitter_username")
                            val twitterUsername: String
                        )
                    }
                }
            }
        }

        data class TopicSubmissions(
            @SerializedName("nature")
            val nature: Nature,
            @SerializedName("spirituality")
            val spirituality: Spirituality,
            @SerializedName("wallpapers")
            val wallpapers: Wallpapers
        ) {
            data class Nature(
                @SerializedName("approved_on")
                val approvedOn: String,
                @SerializedName("status")
                val status: String
            )

            data class Spirituality(
                @SerializedName("approved_on")
                val approvedOn: String,
                @SerializedName("status")
                val status: String
            )

            data class Wallpapers(
                @SerializedName("approved_on")
                val approvedOn: String,
                @SerializedName("status")
                val status: String
            )
        }

        data class Urls(
            @SerializedName("full")
            val full: String,
            @SerializedName("raw")
            val raw: String,
            @SerializedName("regular")
            val regular: String,
            @SerializedName("small")
            val small: String,
            @SerializedName("small_s3")
            val smallS3: String,
            @SerializedName("thumb")
            val thumb: String
        )

        data class User(
            @SerializedName("accepted_tos")
            val acceptedTos: Boolean,
            @SerializedName("bio")
            val bio: String,
            @SerializedName("first_name")
            val firstName: String,
            @SerializedName("for_hire")
            val forHire: Boolean,
            @SerializedName("id")
            val id: String,
            @SerializedName("instagram_username")
            val instagramUsername: String,
            @SerializedName("last_name")
            val lastName: String,
            @SerializedName("links")
            val links: Links,
            @SerializedName("location")
            val location: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("portfolio_url")
            val portfolioUrl: String,
            @SerializedName("profile_image")
            val profileImage: ProfileImage,
            @SerializedName("social")
            val social: Social,
            @SerializedName("total_collections")
            val totalCollections: Int,
            @SerializedName("total_likes")
            val totalLikes: Int,
            @SerializedName("total_photos")
            val totalPhotos: Int,
            @SerializedName("twitter_username")
            val twitterUsername: String,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("username")
            val username: String
        ) {
            data class Links(
                @SerializedName("followers")
                val followers: String,
                @SerializedName("following")
                val following: String,
                @SerializedName("html")
                val html: String,
                @SerializedName("likes")
                val likes: String,
                @SerializedName("photos")
                val photos: String,
                @SerializedName("portfolio")
                val portfolio: String,
                @SerializedName("self")
                val self: String
            )

            data class ProfileImage(
                @SerializedName("large")
                val large: String,
                @SerializedName("medium")
                val medium: String,
                @SerializedName("small")
                val small: String
            )

            data class Social(
                @SerializedName("instagram_username")
                val instagramUsername: String,
                @SerializedName("paypal_email")
                val paypalEmail: Any,
                @SerializedName("portfolio_url")
                val portfolioUrl: String,
                @SerializedName("twitter_username")
                val twitterUsername: String
            )
        }
    }
}