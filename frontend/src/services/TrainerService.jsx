import axios from 'axios';

const BASE_REST_API_URL = 'http://localhost:8091/trainers';

export const getAllTrainers = () => axios.get(BASE_REST_API_URL);

export const saveTrainer = (trainer) => axios.post(BASE_REST_API_URL, trainer);

export const getTrainer = (id) => axios.get(BASE_REST_API_URL + '/' + id);

export const updateTrainer = (trainer) => axios.put(`${BASE_REST_API_URL}/${trainer.id}`, trainer);

export const deleteTrainer = (id) => axios.delete(BASE_REST_API_URL + '/' + id);