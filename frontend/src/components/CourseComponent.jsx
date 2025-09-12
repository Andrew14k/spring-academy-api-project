import { useEffect, useState } from "react"
import { getAllCourses, deleteCourse } from "../services/CourseService"
import { useNavigate } from "react-router-dom"

export default function CourseComponent() {

    const [courses, setCourses] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        listCourses();
    }, [])

    function listCourses(){
        getAllCourses().then((response) => {
            console.log("Loaded courses:", response.data)
            setCourses(response.data)
        }).catch(error => {
            console.error(error)
        })
    }

    function addNewCourse() {
        navigate('/create-course')
    }

    function updateCourse(id) {
        navigate(`/update-course/${id}`)
    }

    function removeCourse(id) {
        console.log("Deleting course id:", id)
        deleteCourse(id).then((response) => {
            console.log("Delete success", response)
            listCourses()
        }).catch(error => {
            console.error(error)
        })
    }

    return (
        <div className="container">
            <h2 className="text-center">Courses Table</h2>
            <button className="btn btn-primary mb-2" onClick={addNewCourse}>Add Course</button>
            <div>
                <table className="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Course ID</th>
                            <th>Course Title</th>
                            {/* <th>Trainees ID</th> */}
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            courses.map(course => 
                                <tr key={course.id}>
                                    <td>{course.id}</td>
                                    <td>{course.title}</td>
                                    {/* <td>{course.trainees?.map(trainee => trainee.id).join(', ')}</td> */}
                                    <td>
                                        <button className="btn btn-info" onClick={() => updateCourse(course.id)}>Update</button>
                                        <button className="btn btn-danger" onClick={() => removeCourse(course.id)}>Delete</button>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}
