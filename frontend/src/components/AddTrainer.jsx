import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { getTrainer, saveTrainer, updateTrainer } from "../services/TrainerService"

const AddTrainer = () => {
    const [fullName, setFullName] = useState('')
    const [course_id, setCourseId] = useState('')
    const navigate = useNavigate()
    const { id } = useParams()

    function saveUpdateTrainer(e) {
        e.preventDefault()
        const trainer = id 
            ? { id: parseInt(id), fullName, course: parseInt(course_id) } 
            : { fullName, course: parseInt(course_id) }
        console.log("Adding trainer:", trainer)

        if (id) {
            updateTrainer(trainer).then((response) => {
                console.log("Update success", response)
                navigate('/')
            }).catch(error => {
                console.error(error)
            })
        } else {
            saveTrainer(trainer).then((response) => {
                console.log("Create success", response)
                navigate('/')
            }).catch(error => {
                console.error(error)
            })
        }
    }

    function pageTitle() {
        if (id) {
            return <h2 className="text-center">Update Trainer</h2>
        } else {
            return <h2 className="text-center">Add Trainer</h2>
        }
    }

    useEffect(() => {
        if (id) {
            getTrainer(id).then((response) => {
                setFullName(response.data.fullName)
                setCourseId(response.data.course)
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
                        <form onSubmit={saveUpdateTrainer}>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Full Name:</label>
                                <input type='text' placeholder='Enter Trainer Full Name...' name='fullName' value={fullName}
                                    onChange={(e) => setFullName(e.target.value)}
                                    className='form-control' />

                                <label className='form-label'>Course ID:</label>
                                    <input type='text' placeholder='Enter Trainer Course ID...' name='course_id' value={course_id}
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

export default AddTrainer