import React,{useState} from "react";
import "../css/text.css";
import {Form, useNavigate} from "react-router-dom";
import "../css/ToDoList.css";

interface FormData{
    todo: string;
    user_id:string;
    sessionkey:string;
}


const ToDoList: React.FC = () =>{
    const navigate = useNavigate();

    const [formData , setFormData] = useState<FormData>({
        todo:'',
        user_id:'',
        sessionkey:'',
    })

    const handleChange = (e:React.ChangeEvent<HTMLInputElement>) =>{
        const {name , value} = e.target;
        setFormData({...formData , [name]:value});
    };
    const handleSubmit = async (e:React.FormEvent) =>{

    }

    const [error , setError] = useState<string|null>(null);


    return(
        <div className={"todo-container"}>
            <h2 className={"jua-regular"}>ToDoList</h2>
            <form onSubmit={handleSubmit}>
                <form className={"form-group"}>
                    <div className={"my-3"}>
                        <input
                            type={"text"}
                            name={"user_id"}
                            value={localStorage.getItem("user_id") || ""}
                            placeholder={"사용자 아이디"}
                            onChange={handleChange}
                            readOnly
                            required
                        />
                    </div>
                </form>
                <form className={"form-group"}>
                    <div className={"my-3"}>
                        <input
                            type={"text"}
                            name={"todo"}
                            value={formData.todo}
                            placeholder={"할 일"}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </form>

                {error && <p className="error-message">{error}</p>}
                <button type={"submit"} className={"btn btn-primary"}>등록</button>
            </form>
        </div>
    )


}


export default ToDoList;