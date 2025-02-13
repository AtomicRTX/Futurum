import axios from 'axios';

const API_URL = 'http://localhost:8080/keyword/';

class KeywordService {
    getAllKeywords() {
        return axios.get(API_URL + 'all')
            .then(response => {
                return response.data;
            });
    }

    getKeyword(id) {
        return axios.get(API_URL + `${id}`)
            .then(response => {
                return response.data;
            });
    }
}

export default new KeywordService;