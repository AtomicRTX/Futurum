import React, { useEffect, useState } from 'react';
import CampaignService from '../services/campaign.service';
import KeywordService from '../services/keyword.service';
import { Link } from 'react-router';
import TownService from '../services/town.service';

const ListPage = () => {
    const [campaignList, setCampaignList] = useState([]);
    const [keywordList, setKeywordList] = useState([]);
    const [townList, setTownList] = useState([]);
    const [budget, setBudget] = useState(100000);

    useEffect(() => {
        CampaignService.getAllCampaigns()
            .then((data) => setCampaignList(data))
            .catch((error) => {
                console.error('Error fetching campaigns:', error);
            });
    }, []);

    useEffect(() => {
        KeywordService.getAllKeywords()
            .then((data) => setKeywordList(data))
            .catch((error) => {
                console.error('Error fetching keywords:', error);
            });
    }, []);

    const getKeywordById = (id) => {
        const keyword = keywordList.find(k => k.id === id);
        return keyword ? keyword.name : "Unknown keyword";
    };

    const getKeywordsByIds = (ids) => {
        return ids.map(id => getKeywordById(id)).join(", ");
    };

    useEffect(() => {
        TownService.getAllTowns()
            .then((data) => setTownList(data))
            .catch((error) => {
                console.error('Error fetching keywords:', error);
            });
    }, []);

    const getTownById = (id) => {
        const town = townList.find(t => t.id === id);
        return town ? town.name : "Unknown town";
    };

    useEffect(() => {
        setBudget(100000);
        let fund = 0;
        campaignList.forEach((campaign) => {
            if(campaign.status == true)fund += campaign.campaignFund;
        });
        setBudget(prevBudget => prevBudget - fund);
    }, [campaignList]);

    return (
        <div className='flex flex-col bg-cover bg-center h-screen w-full bg-gradient-to-r from-red-500 via-orange-500 to-yellow-500'>
            <main className='flex justify-center items-center flex-col w-5/6 h-full mt-5 mb-10 mx-auto bg-white rounded-xl'>
                <div className='h-1/6 w-1/2 flex items-center justify-between'>
                    <p>
                        Budget : {budget} PLN
                    </p>
                    <Link to="/create" className='text-center w-1/3 h-1/3 border bg-white shadow-lg rounded-lg justify-center p-3'>
                        <p className="text-sm opacity-80 h-1/2">
                            Add new
                        </p>
                    </Link>
                </div>
                <ul className='h-5/6 overflow-auto w-full items-center justify-center space-y-5'>
                    {campaignList.map((campaign) => (
                        <div key={campaign.campaignName} className='flex bg-gray-50 h-24 p-4 rounded-lg justify-between text-md mx-10'>
                            <div className='w-60 space-y-3'>
                                <p>Campaign: {campaign.campaignName}</p>
                                <p>Keyword: {getKeywordsByIds(campaign.keywordId)}</p>
                            </div>
                            <div className='w-60 space-y-3'>
                                <p>Bid: {campaign.bidAmount} PLN</p>
                                <p>Fund: {campaign.campaignFund} PLN</p>
                            </div>
                            <div className='w-60 space-y-3'>   
                                <p>Town: {getTownById(campaign.townId)}</p>
                                <p>Radius: {campaign.radius} km</p>
                            </div>
                            <div className='w-60 space-y-3'>
                                <p>Status: {campaign.status ? "on" : "off"}</p>  
                            </div>
                            <div className='w-60 flex flex-col space-y-3'>
                                <Link to={`/create/${campaign.id}`} className='text-red-500'>Edit</Link>
                                <Link onClick={() => {CampaignService.deleteCampaign(campaign.id); window.location.reload(true);}} className='text-red-500'>Delete</Link>
                            </div>
                        </div>
                    ))}
                </ul>
            </main>
        </div>
    );
};

export default ListPage;