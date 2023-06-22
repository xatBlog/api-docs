import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson

class XatBlogAPI {
    companion object {
        const val BASE_URL = "https://api.xatblog.net/"
    }

    private fun sendRequest(path: String): Map<String, Any> {
        val url = URL("$BASE_URL$path")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode

        return if (responseCode == HttpURLConnection.HTTP_OK) {
            val responseString = connection.inputStream.bufferedReader().use { it.readText() }
            Gson().fromJson(responseString, Map::class.java) as Map<String, Any>
        } else {
            mapOf("error" to "Request failed with response code $responseCode")
        }
    }

    fun latest(): Map<String, Any> {
        return sendRequest("/latest")
    }

    fun prices(): Map<String, Any> {
        return sendRequest("/prices")
    }

    fun countdown(): Map<String, Any> {
        return sendRequest("/countdown")
    }

    fun promoted(): Map<String, Any> {
        return sendRequest("/promoted")
    }

    fun activePawns(): Map<String, Any> {
        return sendRequest("/activepawns")
    }

    fun regToId(username: String): Map<String, Any> {
        val endpoint = "/reg2id/$username"
        return sendRequest(endpoint)
    }

    fun idToReg(id: String): Map<String, Any> {
        val endpoint = "/id2reg/$id"
        return sendRequest(endpoint)
    }

    fun namePrice(username: String): Map<String, Any> {
        val endpoint = "/nameprice/$username"
        return sendRequest(endpoint)
    }

    fun chatPrice(chatname: String): Map<String, Any> {
        val endpoint = "/chatprice/$chatname"
        return sendRequest(endpoint)
    }

    fun chatInfo(chatname: String): Map<String, Any> {
        val endpoint = "/chatinfo/$chatname"
        return sendRequest(endpoint)
    }

    fun powerSearch(powername: String): Map<String, Any> {
        val endpoint = "/powersearch/$powername"
        return sendRequest(endpoint)
    }

    fun powerInfo(powername: String): Map<String, Any> {
        val endpoint = "/powerinfo/$powername"
        return sendRequest(endpoint)
    }

    fun powerLogs(powername: String): Map<String, Any> {
        val endpoint = "/powerlogs/$powername"
        return sendRequest(endpoint)
    }

    fun promoPrice(hours: Int, language: String = "en"): Map<String, Any> {
        val endpoint = "/promoprice/$hours/$language"
        return sendRequest(endpoint)
    }

    fun daysToXats(amount: Int): Map<String, Any> {
        val endpoint = "/dx/$amount"
        return sendRequest(endpoint)
    }

    fun xatsToDays(amount: Int): Map<String, Any> {
        val endpoint = "/x2d/$amount"
        return sendRequest(endpoint)
    }

    fun verifyBanner(url: String): Map<String, Any> {
        val endpoint = "/verifybanner/$url"
        return sendRequest(endpoint)
    }

    fun userGifts(userOrId: String): Map<String, Any> {
        val endpoint = "/usergifts/$userOrId"
        return sendRequest(endpoint)
    }

    fun jinxList(powername: String? = null): Map<String, Any> {
        val endpoint = if (powername != null) {
            "/jinxlist/$powername"
        } else {
            "/jinxlist"
        }
        return sendRequest(endpoint)
    }
}
