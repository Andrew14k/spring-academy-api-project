import './App.css'
import Header from './components/Header'
import Footer from './components/Footer'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import DashboardComponent from './components/DashboardComponent'
import TraineeComponent from './components/TraineeComponent'
import AddTrainee from './components/AddTrainee'
import TrainerComponent from './components/TrainerComponent'
import AddTrainer from './components/AddTrainer'
import CourseComponent from './components/CourseComponent'
import AddCourse from './components/AddCourse'

function App() {

  return (
    <>
    <BrowserRouter>
        <Header/>
        <Routes>
          <Route path='/' element={<DashboardComponent />} />

          <Route path='create-trainee' element={<AddTrainee />} />
          <Route path='update-trainee/:id' element={<AddTrainee />} />

          <Route path='create-course' element={<AddCourse />} />
          <Route path='update-course/:id' element={<AddCourse />} />

          <Route path='create-trainer' element={<AddTrainer />} />
          <Route path='update-trainer/:id' element={<AddTrainer />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  )
}

export default App
