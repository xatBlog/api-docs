<?php

class XatBlogAPI
{
    private const BASE_URL = 'https://api.xatblog.net/';

    private function sendRequest($path)
    {
        $url = self::BASE_URL . $path;
        $options = array(
            CURLOPT_URL => $url,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_SSL_VERIFYPEER => false
        );

        $curl = curl_init();
        curl_setopt_array($curl, $options);
        $response = curl_exec($curl);
        curl_close($curl);

        if ($response !== false) {
            $data = json_decode($response, true);
            if ($data !== null) {
                return $data;
            } else {
                return array('error' => 'Failed to decode JSON response');
            }
        } else {
            return array('error' => 'Failed to send request');
        }
    }

    public function latest()
    {
        return $this->sendRequest('/latest');
    }

    public function prices()
    {
        return $this->sendRequest('/prices');
    }

    public function countdown()
    {
        return $this->sendRequest('/countdown');
    }

    public function promoted()
    {
        return $this->sendRequest('/promoted');
    }

    public function activePawns()
    {
        return $this->sendRequest('/activepawns');
    }

    public function regToId($username)
    {
        $endpoint = '/reg2id/' . $username;
        return $this->sendRequest($endpoint);
    }

    public function idToReg($id)
    {
        $endpoint = '/id2reg/' . $id;
        return $this->sendRequest($endpoint);
    }

    public function namePrice($username)
    {
        $endpoint = '/nameprice/' . $username;
        return $this->sendRequest($endpoint);
    }

    public function chatPrice($chatname)
    {
        $endpoint = '/chatprice/' . $chatname;
        return $this->sendRequest($endpoint);
    }

    public function chatInfo($chatname)
    {
        $endpoint = '/chatinfo/' . $chatname;
        return $this->sendRequest($endpoint);
    }

    public function powerSearch($powername)
    {
        $endpoint = '/powersearch/' . $powername;
        return $this->sendRequest($endpoint);
    }

    public function powerInfo($powername)
    {
        $endpoint = '/powerinfo/' . $powername;
        return $this->sendRequest($endpoint);
    }

    public function powerLogs($powername)
    {
        $endpoint = '/powerlogs/' . $powername;
        return $this->sendRequest($endpoint);
    }

    public function promoPrice($hours, $language = 'en')
    {
        $endpoint = '/promoprice/' . $hours . '/' . $language;
        return $this->sendRequest($endpoint);
    }

    public function daysToXats($amount)
    {
        $endpoint = '/dx/' . $amount;
        return $this->sendRequest($endpoint);
    }

    public function xatsToDays($amount)
    {
        $endpoint = '/x2d/' . $amount;
        return $this->sendRequest($endpoint);
    }

    public function verifyBanner($url)
    {
        $endpoint = '/verifybanner/' . $url;
        return $this->sendRequest($endpoint);
    }

    public function userGifts($userOrId)
    {
        $endpoint = '/usergifts/' . $userOrId;
        return $this->sendRequest($endpoint);
    }

    public function jinxList($powername = null)
    {
        if ($powername !== null) {
            $endpoint = '/jinxlist/' . $powername;
        } else {
            $endpoint = '/jinxlist';
        }
        return $this->sendRequest($endpoint);
    }
}