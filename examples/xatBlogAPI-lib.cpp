#include <iostream>
#include <curl/curl.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

class XatBlogAPI {
private:
    static constexpr const char* BASE_URL = "https://api.xatblog.net/";

    static size_t writeCallback(void* contents, size_t size, size_t nmemb, std::string* output) {
        size_t totalSize = size * nmemb;
        output->append(static_cast<char*>(contents), totalSize);
        return totalSize;
    }

    json sendRequest(const std::string& path) {
        std::string url = BASE_URL + path;
        std::string response;

        CURL* curl = curl_easy_init();
        if (curl) {
            curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
            curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeCallback);
            curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
            CURLcode res = curl_easy_perform(curl);
            if (res != CURLE_OK) {
                std::cerr << "Request failed: " << curl_easy_strerror(res) << std::endl;
            }
            curl_easy_cleanup(curl);
        }

        return json::parse(response);
    }

public:
    json latest() {
        return sendRequest("/latest");
    }

    json prices() {
        return sendRequest("/prices");
    }

    json countdown() {
        return sendRequest("/countdown");
    }

    json promoted() {
        return sendRequest("/promoted");
    }

    json activePawns() {
        return sendRequest("/activepawns");
    }

    json regToId(const std::string& username) {
        std::string endpoint = "/reg2id/" + username;
        return sendRequest(endpoint);
    }

    json idToReg(const std::string& id) {
        std::string endpoint = "/id2reg/" + id;
        return sendRequest(endpoint);
    }

    json namePrice(const std::string& username) {
        std::string endpoint = "/nameprice/" + username;
        return sendRequest(endpoint);
    }

    json chatPrice(const std::string& chatname) {
        std::string endpoint = "/chatprice/" + chatname;
        return sendRequest(endpoint);
    }

    json chatInfo(const std::string& chatname) {
        std::string endpoint = "/chatinfo/" + chatname;
        return sendRequest(endpoint);
    }

    json powerSearch(const std::string& powername) {
        std::string endpoint = "/powersearch/" + powername;
        return sendRequest(endpoint);
    }

    json powerInfo(const std::string& powername) {
        std::string endpoint = "/powerinfo/" + powername;
        return sendRequest(endpoint);
    }

    json powerLogs(const std::string& powername) {
        std::string endpoint = "/powerlogs/" + powername;
        return sendRequest(endpoint);
    }

    json promoPrice(int hours, const std::string& language = "en") {
        std::string endpoint = "/promoprice/" + std::to_string(hours) + "/" + language;
        return sendRequest(endpoint);
    }

    json daysToXats(int amount) {
        std::string endpoint = "/dx/" + std::to_string(amount);
        return sendRequest(endpoint);
    }

    json xatsToDays(int amount) {
        std::string endpoint = "/x2d/" + std::to_string(amount);
        return sendRequest(endpoint);
    }

    json verifyBanner(const std::string& url) {
        std::string endpoint = "/verifybanner/" + url;
        return sendRequest(endpoint);
    }

    json userGifts(const std::string& user_or_id) {
        std::string endpoint = "/usergifts/" + user_or_id;
        return sendRequest(endpoint);
    }

    json jinxList(const std::string& powername = "") {
        std::string endpoint = (powername.empty()) ? "/jinxlist" : "/jinxlist/" + powername;
        return sendRequest(endpoint);
    }
};
