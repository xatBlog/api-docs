const axios = require('axios');

class XatBlogAPI {
    constructor() {
        this.BASE_URL = 'https://api.xatblog.net/';
    }

    async sendRequest(path) {
        try {
            const response = await axios.get(this.BASE_URL + path);
            return response.data;
        } catch (error) {
            console.error('Request failed:', error.message);
            return { error: 'Request failed' };
        }
    }

    async latest() {
        return this.sendRequest('/latest');
    }

    async prices() {
        return this.sendRequest('/prices');
    }

    async countdown() {
        return this.sendRequest('/countdown');
    }

    async promoted() {
        return this.sendRequest('/promoted');
    }

    async activePawns() {
        return this.sendRequest('/activepawns');
    }

    async regToId(username) {
        const endpoint = '/reg2id/' + username;
        return this.sendRequest(endpoint);
    }

    async idToReg(id) {
        const endpoint = '/id2reg/' + id;
        return this.sendRequest(endpoint);
    }

    async namePrice(username) {
        const endpoint = '/nameprice/' + username;
        return this.sendRequest(endpoint);
    }

    async chatPrice(chatname) {
        const endpoint = '/chatprice/' + chatname;
        return this.sendRequest(endpoint);
    }

    async chatInfo(chatname) {
        const endpoint = '/chatinfo/' + chatname;
        return this.sendRequest(endpoint);
    }

    async powerSearch(powername) {
        const endpoint = '/powersearch/' + powername;
        return this.sendRequest(endpoint);
    }

    async powerInfo(powername) {
        const endpoint = '/powerinfo/' + powername;
        return this.sendRequest(endpoint);
    }

    async powerLogs(powername) {
        const endpoint = '/powerlogs/' + powername;
        return this.sendRequest(endpoint);
    }

    async promoPrice(hours, language = 'en') {
        const endpoint = '/promoprice/' + hours + '/' + language;
        return this.sendRequest(endpoint);
    }

    async daysToXats(amount) {
        const endpoint = '/dx/' + amount;
        return this.sendRequest(endpoint);
    }

    async xatsToDays(amount) {
        const endpoint = '/x2d/' + amount;
        return this.sendRequest(endpoint);
    }

    async verifyBanner(url) {
        const endpoint = '/verifybanner/' + url;
        return this.sendRequest(endpoint);
    }

    async userGifts(userOrId) {
        const endpoint = '/usergifts/' + userOrId;
        return this.sendRequest(endpoint);
    }

    async jinxList(powername = '') {
        const endpoint = powername ? '/jinxlist/' + powername : '/jinxlist';
        return this.sendRequest(endpoint);
    }
}