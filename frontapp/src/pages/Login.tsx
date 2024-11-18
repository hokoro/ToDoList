import React,{useState} from "react";
import "../css/text.css";
import "../css/Login.css";
import {useNavigate} from "react-router-dom";

interface FormData{
    user_id: string,
    password: string
}


const Login: React.FC = () => {
    const navigate = useNavigate();

    const [formData , setFormData] = useState<FormData>({
        user_id:'',
        password:''
    });

    // 에러 메시지 설정
    const [error , setError] = useState<string|null>(null);

    // 값이 변경될때 호출
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) =>{
        const {name , value} = e.target;
        setFormData({...formData , [name]:value});
    };

    // 폼 제출시 호출
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        setError(null);

        try{
            const response = await fetch("http://127.0.0.1:8045/api/user/login",{
                method:'POST',
                headers:{'Content-Type':'application/json'},
                body:JSON.stringify(formData),
            })
            if(response.ok){
                const data = await response.json();
                alert(`Message: ${data.message}\nToken: ${data.token}`);

                // token 사용 예제 (로컬 스토리지 저장)
                localStorage.setItem('authToken', data.token);
                navigate('/');
            }else{
                const errorData = await response.json();
                setError(errorData.message|| '아이디와 비밀번호를 다시 확인해주세요');
            }

        }catch (error){
            setError('서버와의 연결중 오류가 발생했습니다.');
        }
    }

    return (
        <div className="login-container">
            <h2 className="jua-regular">Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <input
                        type="text"
                        name="user_id"
                        value={formData.user_id}
                        placeholder={"아이디"}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        placeholder={"비밀번호"}
                        onChange={handleChange}
                        required
                    />
                </div>
                {error && <p className="error-message">{error}</p>}
                <button type="submit" className="btn btn-primary">로그인</button>
            </form>
        </div>
    )
}

export default Login;