import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { getCourse, saveCourse, updateCourse } from "../services/CourseService"

const AddCourse = () => {
    const [title, setTitle] = useState('')
    const [traineeIds, setTraineeIds] = useState([])
    const navigate = useNavigate()
    const { id } = useParams()

    function saveUpdateCourse(e) {
        e.preventDefault()
        const trainees = traineeIds.map(id => ({ id }))
        const course = id ? { id: parseInt(id), title, trainees } : { title, trainees }
        console.log("Adding trainer:", course)

        if (id) {
            updateCourse(course).then((response) => {
                console.log("Update success", response)
                navigate('/')
            }).catch(error => {
                console.error(error)
            })
        } else {
            saveCourse(course).then((response) => {
                console.log("Create success", response)
                navigate('/')
            }).catch(error => {
                console.error(error)
            })
        }
    }

    function pageTitle() {
        if (id) {
            return <h2 className="text-center">Update Course</h2>
        } else {
            return <h2 className="text-center">Add Course</h2>
        }
    }

    useEffect(() => {
        if (id) {
            getCourse(id).then((response) => {
                setTitle(response.data.title)
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
                        <form onSubmit={saveUpdateCourse}>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Title:</label>
                                <input type='text' placeholder='Enter Course Title...' name='title' value={title}
                                    onChange={(e) => setTitle(e.target.value)}
                                    className='form-control' />

                                {/* <label className='form-label'>Trainees ID:</label>
                                <input type='text' placeholder='Enter Trainees ID...' name='trainee_id' value={traineeIds.join(',')}
                                    onChange={(e) => setTraineeIds(e.target.value.split(',').map(Number))}
                                    className='form-control' /> */}
                            </div>

                            <button type="submit" className='btn btn-success'>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddCourse