import TraineeComponent from "./TraineeComponent";
import TrainerComponent from "./TrainerComponent";
import CourseComponent from "./CourseComponent";

export default function DashboardComponent() {
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-md-4">
                    <TraineeComponent />
                </div>
                <div className="col-md-4">
                    <CourseComponent />
                </div>
                <div className="col-md-4">
                    <TrainerComponent />
                </div>
            </div>
        </div> 
    )
}