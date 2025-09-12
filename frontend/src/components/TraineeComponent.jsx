import { useEffect, useState } from "react"
import { getAllTrainees, deleteTrainee } from "../services/TraineeService"
import { useNavigate } from "react-router-dom"

export default function TraineeComponent() {

    const [trainees, setTrainees] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        listTrainees();
    }, [])

    function listTrainees(){
        getAllTrainees().then((response) => {
            setTrainees(response.data)
        }).catch(error => {
            console.error(error)
        })
    }

    function addNewTrainee() {
        navigate('/create-trainee')
    }

    function updateTrainee(id) {
        navigate(`/update-trainee/${id}`)
    }

    function removeTrainee(id) {
        console.log("Deleting trainee id:", id)
        deleteTrainee(id).then((response) => {
            console.log("Delete success", response)
            listTrainees()
        }).catch(error => {
            console.error(error)
        })
    }

    return (
        <div className="container">
            <h2 className="text-center">Trainees Table</h2>
            <button className="btn btn-primary mb-2" onClick={addNewTrainee}>Add Trainee</button>
            <div>
                <table className="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Trainee ID</th>
                            <th>Trainee Name</th>
                            <th>Course ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            trainees.map(trainee => 
                                <tr key={trainee.id}>
                                    <td>{trainee.id}</td>
                                    <td>{trainee.fullName}</td>
                                    <td>{trainee.course_id}</td>
                                    <td>
                                        <button className="btn btn-info" onClick={() => updateTrainee(trainee.id)}>Update</button>
                                        <button className="btn btn-danger" onClick={() => removeTrainee(trainee.id)}>Delete</button>
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
