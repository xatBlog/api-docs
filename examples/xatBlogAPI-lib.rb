require 'net/http'
require 'json'
require 'openssl'

class XatBlogAPI
    BASE_URL = 'https://api.xatblog.net/'.freeze

    def send_request(path)
        uri = URI(BASE_URL + path)
        http = Net::HTTP.new(uri.host, uri.port)
        http.use_ssl = false
        response = http.get(uri)

        if response.is_a?(Net::HTTPSuccess)
            JSON.parse(response.body)
        else
            { 'error' => "Request failed with response code #{response.code}" }
        end
    rescue StandardError => e
        { 'error' => "Failed to send request: #{e.message}" }
    end

    def latest
        send_request('/latest')
    end

    def prices
        send_request('/prices')
    end

    def countdown
        send_request('/countdown')
    end

    def promoted
        send_request('/promoted')
    end

    def active_pawns
        send_request('/activepawns')
    end

    def reg_to_id(username)
        endpoint = '/reg2id/' + username
        send_request(endpoint)
    end

    def id_to_reg(id)
        endpoint = '/id2reg/' + id
        send_request(endpoint)
    end

    def name_price(username)
        endpoint = '/nameprice/' + username
        send_request(endpoint)
    end

    def chat_price(chatname)
        endpoint = '/chatprice/' + chatname
        send_request(endpoint)
    end

    def chat_info(chatname)
        endpoint = '/chatinfo/' + chatname
        send_request(endpoint)
    end

    def power_search(powername)
        endpoint = '/powersearch/' + powername
        send_request(endpoint)
    end

    def power_info(powername)
        endpoint = '/powerinfo/' + powername
        send_request(endpoint)
    end

    def power_logs(powername)
        endpoint = '/powerlogs/' + powername
        send_request(endpoint)
    end

    def promo_price(hours, language = 'en')
        endpoint = '/promoprice/' + hours.to_s + '/' + language
        send_request(endpoint)
    end

    def days_to_xats(amount)
        endpoint = '/dx/' + amount.to_s
        send_request(endpoint)
    end

    def xats_to_days(amount)
        endpoint = '/x2d/' + amount.to_s
        send_request(endpoint)
    end

    def verify_banner(url)
        endpoint = '/verifybanner/' + url
        send_request(endpoint)
    end

    def user_gifts(user_or_id)
        endpoint = '/usergifts/' + user_or_id
        send_request(endpoint)
    end

    def jinx_list(powername = nil)
        if powername
            endpoint = '/jinxlist/' + powername
        else
            endpoint = '/jinxlist'
        end
        send_request(endpoint)
    end
end