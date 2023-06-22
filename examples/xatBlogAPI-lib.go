package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"strings"
)

type XatBlogAPI struct {
	BaseURL string
}

func XatBlogAPIData() *XatBlogAPI {
	return &XatBlogAPI{
		BaseURL: "https://api.xatblog.net/",
	}
}

func (x *XatBlogAPI) sendRequest(path string) (map[string]interface{}, error) {
	url := x.BaseURL + path

	// Desativa a verificação SSL
	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{Transport: tr}

	response, err := client.Get(url)
	if err != nil {
		return nil, fmt.Errorf("failed to send request: %s", err.Error())
	}
	defer response.Body.Close()

	body, err := ioutil.ReadAll(response.Body)
	if err != nil {
		return nil, fmt.Errorf("failed to read response body: %s", err.Error())
	}

	var data map[string]interface{}
	err = json.Unmarshal(body, &data)
	if err != nil {
		return nil, fmt.Errorf("failed to parse response JSON: %s", err.Error())
	}

	return data, nil
}

func (x *XatBlogAPI) latest() (map[string]interface{}, error) {
	return x.sendRequest("/latest")
}

func (x *XatBlogAPI) prices() (map[string]interface{}, error) {
	return x.sendRequest("/prices")
}

func (x *XatBlogAPI) countdown() (map[string]interface{}, error) {
	return x.sendRequest("/countdown")
}

func (x *XatBlogAPI) promoted() (map[string]interface{}, error) {
	return x.sendRequest("/promoted")
}

func (x *XatBlogAPI) activePawns() (map[string]interface{}, error) {
	return x.sendRequest("/activepawns")
}

func (x *XatBlogAPI) regToID(username string) (map[string]interface{}, error) {
	endpoint := "/reg2id/" + username
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) idToReg(id string) (map[string]interface{}, error) {
	endpoint := "/id2reg/" + id
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) namePrice(username string) (map[string]interface{}, error) {
	endpoint := "/nameprice/" + username
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) chatPrice(chatname string) (map[string]interface{}, error) {
	endpoint := "/chatprice/" + chatname
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) chatInfo(chatname string) (map[string]interface{}, error) {
	endpoint := "/chatinfo/" + chatname
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) powerSearch(powername string) (map[string]interface{}, error) {
	endpoint := "/powersearch/" + powername
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) powerInfo(powername string) (map[string]interface{}, error) {
	endpoint := "/powerinfo/" + powername
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) powerLogs(powername string) (map[string]interface{}, error) {
	endpoint := "/powerlogs/" + powername
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) promoPrice(hours int, language string) (map[string]interface{}, error) {
	endpoint := fmt.Sprintf("/promoprice/%d/%s", hours, language)
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) daysToXats(amount int) (map[string]interface{}, error) {
	endpoint := "/dx/" + strconv.Itoa(amount)
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) xatsToDays(amount int) (map[string]interface{}, error) {
	endpoint := "/x2d/" + strconv.Itoa(amount)
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) verifyBanner(url string) (map[string]interface{}, error) {
	endpoint := "/verifybanner/" + url
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) userGifts(userOrID string) (map[string]interface{}, error) {
	endpoint := "/usergifts/" + userOrID
	return x.sendRequest(endpoint)
}

func (x *XatBlogAPI) jinxList(powername string) (map[string]interface{}, error) {
	var endpoint string
	if powername != "" {
		endpoint = "/jinxlist/" + powername
	} else {
		endpoint = "/jinxlist"
	}
	return x.sendRequest(endpoint)
}

func main() {
	xatAPI := XatBlogAPIData()

	latestData, err := xatAPI.latest()
	if err != nil {
		fmt.Println("Failed to retrieve latest data:", err)
		os.Exit(1)
	}
	fmt.Println("Latest data:", latestData)

	pricesData, err := xatAPI.prices()
	if err != nil {
		fmt.Println("Failed to retrieve prices data:", err)
		os.Exit(1)
	}
	fmt.Println("Prices data:", pricesData)
}
