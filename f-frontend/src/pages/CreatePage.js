import React, { useEffect, useState } from 'react';
import CampaignService from '../services/campaign.service';
import Select from 'react-select';
import { useNavigate, useParams } from 'react-router-dom';
import KeywordService from '../services/keyword.service';
import TownService from '../services/town.service';

const CreatePage = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [campaign, setCampaign] = useState({
        id: null,
        campaignName: '',
        keywordId: [],
        bidAmount: null,
        campaignFund: null,
        status: false,
        townId: null,
        radius: null
    });

    const [optionsTown, setOptionsTown] = useState([]);
    const [optionsKey, setOptionsKey] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        if (id) {
            CampaignService.getCampaign(id)
                .then(data => setCampaign(data))
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    }, [id]);

    useEffect(() => {
        KeywordService.getAllKeywords()
            .then(data => setOptionsKey(data.map(k => ({ value: k.id, label: k.name }))))
            .catch(error => {
                console.error('Error:', error);
            });
    }, []);

    useEffect(() => {
        TownService.getAllTowns()
            .then(data => setOptionsTown(data.map(t => ({ value: t.id, label: t.name }))))
            .catch(error => {
                console.error('Error:', error);
            });
    }, []);

    const handleInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setCampaign(prevState => ({
            ...prevState,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleSelectChange = (selectedOption, actionMeta) => {
        setCampaign(prevState => ({
            ...prevState,
            [actionMeta.name]: selectedOption ? selectedOption.value : null
        }));
    };

    const handleStatusChange = (e) => {
        setCampaign(prevState => ({
            ...prevState,
            status: e.target.checked
        }));
    };

    const handleMultiSelectChange = (selectedOptions) => {
        setCampaign(prevState => ({
            ...prevState,
            keywordId: selectedOptions ? selectedOptions.map(option => option.value) : []
        }));
    };

    const handleCreator = (e) => {
        e.preventDefault();
        if (campaign.bidAmount < 0 || campaign.campaignFund < 0 || campaign.radius < 0) {
            setErrorMessage('Values cannot be negative. Please enter valid numbers.');
            return;
        }
        if (campaign.campaignName == '' || campaign.keywordId == []) {
            setErrorMessage('Please enter values.');
            return;
        }
        setErrorMessage('');
        if(campaign.id == null){
            CampaignService.createCampaign(campaign.id, campaign.campaignName, campaign.keywordId, campaign.bidAmount, campaign.campaignFund, campaign.status, campaign.townId, campaign.radius);
        }
        else{
            CampaignService.editCampaign(campaign.id, campaign.campaignName, campaign.keywordId, campaign.bidAmount, campaign.campaignFund, campaign.status, campaign.townId, campaign.radius);
        }
        navigate("/");
        window.location.reload(true);
    };

    return (
        <div className='flex flex-col bg-cover bg-center h-screen w-full bg-gradient-to-r from-red-500 via-orange-500 to-yellow-500'>
            <main className='flex justify-center items-center flex-col w-5/6 h-full mt-5 mb-10 mx-auto bg-white rounded-xl'>
                <h1 className='text-2xl font-bold mb-4 text-gray-700'>Campaign</h1>
                <form onSubmit={handleCreator} className='w-1/2 flex flex-col gap-4'>
                    <div className='ml-10'>
                        <p className='relative'>Campaign name</p>
                        <input 
                            type='text' 
                            name='campaignName' 
                            value={campaign.campaignName} 
                            onChange={handleInputChange} 
                            placeholder='Campaign name (required)' 
                            className='w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    <div className='ml-10'>
                        <p>Bid amount</p>
                        <input 
                            type='number' 
                            name='bidAmount' 
                            value={campaign.bidAmount} 
                            onChange={handleInputChange} 
                            placeholder='Bid amount (required)' 
                            className='w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    <div className='ml-10'>
                        <p>Campaign fund</p>
                        <input 
                            type='number' 
                            name='campaignFund' 
                            value={campaign.campaignFund} 
                            onChange={handleInputChange} 
                            placeholder='Campaign fund (required)' 
                            className='w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    <div className='ml-10'>
                        <p>Town</p>
                        <Select 
                            name='townId' 
                            options={optionsTown} 
                            onChange={handleSelectChange} 
                            value={optionsTown.find(option => option.value === campaign.townId)}
                            placeholder='Town (required)' 
                            className='w-full rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    <div className='ml-10'>
                        <p>Keywords</p>
                        <Select 
                            name='keywordId' 
                            options={optionsKey} 
                            isMulti 
                            onChange={handleMultiSelectChange} 
                            placeholder='Keyword (required)' 
                            value={optionsKey.filter(option => campaign.keywordId.includes(option.value))}
                            className='w-full rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    <div className='ml-10'>
                        <p>Radius</p>
                        <input 
                            type='number' 
                            name='radius' 
                            value={campaign.radius} 
                            onChange={handleInputChange} 
                            placeholder='Radius (required)' 
                            className='w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    <div className='ml-10'>
                        <p>Status</p>
                        <input 
                            type="checkbox"
                            name="status"
                            checked={campaign.status}
                            onChange={handleStatusChange}
                            className='w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500'
                        />
                    </div>
                    {errorMessage && <p className='text-red-500 ml-10'>{errorMessage}</p>}
                    <button type='submit' className='ml-10 mt-5 bg-red-500 text-white py-2 px-4 rounded-lg hover:bg-red-600'>
                        Save Campaign
                    </button>
                </form>
            </main>
        </div>
    );
};

export default CreatePage;