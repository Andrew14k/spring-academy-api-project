import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { getTrainee, saveTrainee, updateTrainee } from "../services/TraineeService"

const AddTrainee = () => {
    const [fullName, setFullName] = useState('')
    const [course_id, setCourseId] = useState('')
    const navigate = useNavigate()
    const { id } = useParams()

    function saveUpdateTrainee(e) {
        e.preventDefault()
        const trainee = id 
            ? { id: parseInt(id), fullName, course_id: parseInt(course_id) } 
            : { fullName, course_id: parseInt(course_id) }
        console.log("Adding trainee:", trainee)

        if (id) {
            updateTrainee(trainee).then((response) => {
                console.log("Update success", response)
                navigate('/')
            }).catch(error => {
                console.error(error)
            })
        } else {
            saveTrainee(trainee).then((response) => {
                console.log("Create success", response)
                navigate('/')
            }).catch(error => {
                console.error(error)
            })
        }
    }

    function pageTitle() {
        if (id) {
            return <h2 className="text-center">Update Trainee</h2>
        } else {
            return <h2 className="text-center">Add Trainee</h2>
        }
    }

    useEffect(() => {
        if (id) {
            getTrainee(id).then((response) => {
                setFullName(response.data.fullName)
                setCourseId(response.data.course_id)
            }).catch(error => {
                console.error(error)
            })
        }
    }, [])

    return (
        <div className="container">
            <br /> <br />
            <div className="row">
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {pageTitle()}
                    <div className='card-body'>
                        <form onSubmit={saveUpdateTrainee}>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Full Name:</label>
                                <input type='text' placeholder='Enter Trainee Full Name...' name='fullName' value={fullName}
                                    onChange={(e) => setFullName(e.target.value)}
                                    className='form-control' />

                                    <label className='form-label'>Course ID:</label>
                                    <input type='text' placeholder='Enter Trainee Course ID...' name='course_id' value={course_id}
                                        onChange={(e) => setCourseId(e.target.value)}
                                        className='form-control' />
                            </div>

                            <button type="submit" className='btn btn-success'>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddTrainee