import axios from 'axios';

const BASE_REST_API_URL = 'http://localhost:8091/trainees';

export const getAllTrainees = () => axios.get(BASE_REST_API_URL);

export const saveTrainee = (trainee) => axios.post(BASE_REST_API_URL, trainee);

export const getTrainee = (id) => axios.get(BASE_REST_API_URL + '/' + id);

export const updateTrainee = (trainee) => axios.put(BASE_REST_API_URL, trainee);

export const deleteTrainee = (id) => axios.delete(BASE_REST_API_URL + '/' + id);