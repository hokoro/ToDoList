import React, { useState } from 'react';
import "../css/text.css";
import "../css/Signup.css";

// 서버의 데이터베이스 컬럼 이름에 맞춘 인터페이스 정의
interface FormData {
    user_id: string;  // 사용자 아이디
    password: string; // 사용자 비밀번호
}

const Signup: React.FC = () => {
    // formData 상태를 정의하고 초기값 설정
    const [formData, setFormData] = useState<FormData>({
        user_id:'',
        password:''
    });

    // 에러 메세지 설정
    const [error , setError] = useState<string|null>(null);

    // 입력 필드의 값이 변경될 때 호출되는 함수
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setFormData({...formData , [name]:value});
    };

    // 폼 제출 시 호출되는 함수
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // if(!/^[a-zA-Z0-9]{5,}$/.test(formData.user_id)){    // 아이디는 소문자, 대문자 알파벳과 0~9까지의 숫자만 사용하고 5자리 이상의 영문과 숫자가 포함되어 있는지를 조건화
        //     setError('아이디는 5자리 이상의 영문과 숫자가 포함되어야 합니다.');
        //     return;
        // }
        //
        // if(!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(formData.password)){    // 비밀번호 규정 위반
        //     setError('비밀번호는 8자리 이상이며, 대소문자, 숫자,특수문자를 포함해야 합니다.');
        //     return;
        // }

        setError(null); // 유효한 입력이 남아있지 않도록 메세지 초기화

        try{
            const response = await fetch('http://127.0.0.1:8045/api/user/create',{
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(formData),
            });

            if(response.ok){
                alert('회원가입이 완료되었습니다.');
            }else{
                const errorData = await response.json();
                setError(errorData.message || '회원가입에 실패하였습니다.');
            }

        }catch(error){  // server connect Error
            setError('서버와의 연결중 오류가 발생했습니다.')
        }


    };

    return (
        <div className="signup-container">
            <h2 className="jua-regular">Sign Up</h2>
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
                {error && <p className="error-message">{error}</p> }
                <button type="submit" className="btn btn-primary">회원가입</button>
            </form>
        </div>

    );
}

export default Signup;