import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/text.css';
import '../css/Home.css';

const Home: React.FC = () => {
    const navigate = useNavigate();

    // 로그아웃 상태 체크 버튼
    const [isLoggedIn , setIsLoggedIn] = useState(false);

    // 로그인 상태 여부
    useEffect(() => {
        const authToken = localStorage.getItem('authToken');
        const user_id = localStorage.getItem('user_id');
        if(authToken && user_id){
            setIsLoggedIn(true);
        }


    },[]);

    // 에러 메시지 설정
    const [error , setError] = useState<string|null>(null);



    const handleLogout = async () =>{
        setIsLoggedIn(false);   // 로그아웃 버튼 클릭시
        console.log('로그아웃이 완료되었습니다');

        // 로컬스토리지에서 데이터 가져오기
        const sessionKey = localStorage.getItem('authToken');
        const userId = localStorage.getItem('user_id');

        if (!sessionKey || !userId) {
            setError('로그아웃 할 정보가 부족합니다.');
            return;
        }


        const formData = {  // api로 보낼 데이터
            sessionKey :sessionKey,
            user_id :userId,
        }

        try{
            const response = await fetch("https://127.0.0.1:8045/api/user/logout" , {
                method:'POST',
                headers:{'Content-Type':'application/json'},
                body:JSON.stringify(formData)
            })
            if(response.ok){
                const data = await response.json();
                localStorage.removeItem('authToken');
                localStorage.removeItem('user_id');
                alert(data.message);
            }else{
                const errorData = await response.json();
                setError(errorData.message||"로그아웃 할 정보가 존재하지 않습니다.");
            }

        }catch(error){
            setError("서버와의 연결중 오류가 발생하였습니다.");
        }


    };


        return (
            <div className="main">
                <p>안녕하십니까</p>
                <p>ToDoList 개발자입니다.</p>
                <p>오늘의 할일을 기록하는 사이트입니다.</p>
                <p>이용 중 불편하신 사항은</p>
                <p>문의사항을 통해 문의하시기 바랍니다.</p>

                <div>
                    {error && <div className="error-message">{error}</div>} {/* 에러 메시지 표시 */}
                </div>


                <div>
                    {isLoggedIn ? (
                        <>
                            <button className={"btn btn-primary my-half"} onClick={handleLogout}>로그아웃</button>
                            <button className={"btn btn-primary"}>ToDoList</button>
                        </>
                    ) : (
                        <>
                            <button className="btn btn-primary my-half"
                                    onClick={() => navigate('/login')}>로그인
                            </button>
                            <button className="btn btn-primary" onClick={() => navigate('/signup')}>회원가입</button>
                        </>
                    )}
                </div>
            </div>
        );
}

export default Home;