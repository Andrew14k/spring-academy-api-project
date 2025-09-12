import axios from 'axios';

const BASE_REST_API_URL = 'http://localhost:8091/courses';

export const getAllCourses = () => axios.get(BASE_REST_API_URL);

export const saveCourse = (course) => axios.post(BASE_REST_API_URL, course);

export const getCourse = (id) => axios.get(BASE_REST_API_URL + '/' + id);

export const updateCourse = (course) => axios.put(`${BASE_REST_API_URL}/${course.id}`, course);

export const deleteCourse = (id) => axios.delete(BASE_REST_API_URL + '/' + id);