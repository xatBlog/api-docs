import axios from 'axios';

class XatBlogAPI {
    private readonly BASE_URL = 'https://api.xatblog.net/';

    private async sendRequest(path: string): Promise<any> {
        try {
            const url = this.BASE_URL + path;
            const response = await axios.get(url, { httpsAgent: { rejectUnauthorized: false } });

            return response.data;
        } catch (error) {
            return { error: `Failed to send request: ${error.message}` };
        }
    }

    public async latest(): Promise<any> {
        return this.sendRequest('/latest');
    }

    public async prices(): Promise<any> {
        return this.sendRequest('/prices');
    }

    public async countdown(): Promise<any> {
        return this.sendRequest('/countdown');
    }

    public async promoted(): Promise<any> {
        return this.sendRequest('/promoted');
    }

    public async activePawns(): Promise<any> {
        return this.sendRequest('/activepawns');
    }

    public async regToId(username: string): Promise<any> {
        const endpoint = `/reg2id/${username}`;
        return this.sendRequest(endpoint);
    }

    public async idToReg(id: string): Promise<any> {
        const endpoint = `/id2reg/${id}`;
        return this.sendRequest(endpoint);
    }

    public async namePrice(username: string): Promise<any> {
        const endpoint = `/nameprice/${username}`;
        return this.sendRequest(endpoint);
    }

    public async chatPrice(chatname: string): Promise<any> {
        const endpoint = `/chatprice/${chatname}`;
        return this.sendRequest(endpoint);
    }

    public async chatInfo(chatname: string): Promise<any> {
        const endpoint = `/chatinfo/${chatname}`;
        return this.sendRequest(endpoint);
    }

    public async powerSearch(powername: string): Promise<any> {
        const endpoint = `/powersearch/${powername}`;
        return this.sendRequest(endpoint);
    }

    public async powerInfo(powername: string): Promise<any> {
        const endpoint = `/powerinfo/${powername}`;
        return this.sendRequest(endpoint);
    }

    public async powerLogs(powername: string): Promise<any> {
        const endpoint = `/powerlogs/${powername}`;
        return this.sendRequest(endpoint);
    }

    public async promoPrice(hours: number, language: string = 'en'): Promise<any> {
        const endpoint = `/promoprice/${hours}/${language}`;
        return this.sendRequest(endpoint);
    }

    public async daysToXats(amount: number): Promise<any> {
        const endpoint = `/dx/${amount}`;
        return this.sendRequest(endpoint);
    }

    public async xatsToDays(amount: number): Promise<any> {
        const endpoint = `/x2d/${amount}`;
        return this.sendRequest(endpoint);
    }

    public async verifyBanner(url: string): Promise<any> {
        const endpoint = `/verifybanner/${url}`;
        return this.sendRequest(endpoint);
    }

    public async userGifts(userOrId: string): Promise<any> {
        const endpoint = `/usergifts/${userOrId}`;
        return this.sendRequest(endpoint);
    }

    public async jinxList(powername?: string): Promise<any> {
        const endpoint = powername ? `/jinxlist/${powername}` : '/jinxlist';
        return this.sendRequest(endpoint);
    }
}