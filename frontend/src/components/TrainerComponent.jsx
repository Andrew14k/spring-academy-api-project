import { useEffect, useState } from "react"
import { getAllTrainers, deleteTrainer } from "../services/TrainerService"
import { useNavigate } from "react-router-dom"

export default function TrainerComponent() {

    const [trainers, setTrainers] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        listTrainers();
    }, [])

    function listTrainers(){
        getAllTrainers().then((response) => {
            console.log("Loaded trainers:", response.data)
            setTrainers(response.data)
        }).catch(error => {
            console.error(error)
        })
    }

    function addNewTrainer() {
        navigate('/create-trainer')
    }

    function updateTrainer(id) {
        navigate(`/update-trainer/${id}`)
    }

    function removeTrainer(id) {
        console.log("Deleting trainer id:", id)
        deleteTrainer(id).then((response) => {
            console.log("Delete success", response)
            listTrainers()
        }).catch(error => {
            console.error(error)
        })
    }

    return (
        <div className="container">
            <h2 className="text-center">Trainers Table</h2>
            <button className="btn btn-primary mb-2" onClick={addNewTrainer}>Add Trainer</button>
            <div>
                <table className="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Trainer ID</th>
                            <th>Trainer Name</th>
                            <th>Course ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            trainers.map(trainer => 
                                <tr key={trainer.id}>
                                    <td>{trainer.id}</td>
                                    <td>{trainer.fullName}</td>
                                    <td>{trainer.course}</td>
                                    <td>
                                        <button className="btn btn-info" onClick={() => updateTrainer(trainer.id)}>Update</button>
                                        <button className="btn btn-danger" onClick={() => removeTrainer(trainer.id)}>Delete</button>
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
