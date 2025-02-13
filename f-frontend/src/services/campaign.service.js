import axios from 'axios';

const API_URL = 'http://localhost:8080/campaign/';

class CampaignService {
    getAllCampaigns() {
        return axios.get(API_URL + 'all')
            .then(response => {
                return response.data;
            });
    }

    getCampaign(id) {
        return axios.get(API_URL + `${id}`)
            .then(response => {
                return response.data;
            });
    }

    createCampaign(id, campaignName, keywordId, bidAmount, campaignFund, status, townId, radius) {
        return axios.post(API_URL + 'create', {id: id, campaignName: campaignName, keywordId: keywordId, bidAmount: bidAmount, campaignFund: campaignFund, status: status, townId: townId, radius: radius})
            .then(response => {
                return response.data;
            });
    }
    editCampaign(id, campaignName, keywordId, bidAmount, campaignFund, status, townId, radius){
        return axios.put(API_URL + 'edit', {id: id, campaignName: campaignName, keywordId: keywordId, bidAmount: bidAmount, campaignFund: campaignFund, status: status, townId: townId, radius: radius})
            .then(response => {
                return response.data;
            });
    }
    deleteCampaign(id){
        return axios.delete(API_URL + `delete/${id}`)
            .then(response => {
                return response.data;
            });
    }
}

export default new CampaignService;