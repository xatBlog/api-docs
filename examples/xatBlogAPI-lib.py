import requests


class XatBlogAPI:
    BASE_URL = 'https://api.xatblog.net/'

    def sendRequest(self, path):
        url = self.BASE_URL + path
        try:
            response = requests.get(url, verify=False)
            response.raise_for_status()
            return response.json()
        except requests.exceptions.RequestException as e:
            return {'error': 'Failed to send request: ' + str(e)}

    def latest(self):
        return self.sendRequest('/latest')

    def prices(self):
        return self.sendRequest('/prices')

    def countdown(self):
        return self.sendRequest('/countdown')

    def promoted(self):
        return self.sendRequest('/promoted')

    def activePawns(self):
        return self.sendRequest('/activepawns')

    def regToId(self, username):
        endpoint = '/reg2id/' + username
        return self.sendRequest(endpoint)

    def idToReg(self, id):
        endpoint = '/id2reg/' + id
        return self.sendRequest(endpoint)

    def namePrice(self, username):
        endpoint = '/nameprice/' + username
        return self.sendRequest(endpoint)

    def chatPrice(self, chatname):
        endpoint = '/chatprice/' + chatname
        return self.sendRequest(endpoint)

    def chatInfo(self, chatname):
        endpoint = '/chatinfo/' + chatname
        return self.sendRequest(endpoint)

    def powerSearch(self, powername):
        endpoint = '/powersearch/' + powername
        return self.sendRequest(endpoint)

    def powerInfo(self, powername):
        endpoint = '/powerinfo/' + powername
        return self.sendRequest(endpoint)

    def powerLogs(self, powername):
        endpoint = '/powerlogs/' + powername
        return self.sendRequest(endpoint)

    def promoPrice(self, hours, language='en'):
        endpoint = '/promoprice/' + str(hours) + '/' + language
        return self.sendRequest(endpoint)

    def daysToXats(self, amount):
        endpoint = '/dx/' + str(amount)
        return self.sendRequest(endpoint)

    def xatsToDays(self, amount):
        endpoint = '/x2d/' + str(amount)
        return self.sendRequest(endpoint)

    def verifyBanner(self, url):
        endpoint = '/verifybanner/' + url
        return self.sendRequest(endpoint)

    def userGifts(self, userOrId):
        endpoint = '/usergifts/' + userOrId
        return self.sendRequest(endpoint)

    def jinxList(self, powername=None):
        if powername:
            endpoint = '/jinxlist/' + powername
        else:
            endpoint = '/jinxlist'
        return self.sendRequest(endpoint)