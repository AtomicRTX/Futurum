import axios from 'axios';

const API_URL = 'http://localhost:8080/town/';

class TownService {
    getAllTowns() {
        return axios.get(API_URL + 'all')
            .then(response => {
                return response.data;
            });
    }

    getTown(id) {
        return axios.get(API_URL + `${id}`)
            .then(response => {
                return response.data;
            });
    }
}

export default new TownService;