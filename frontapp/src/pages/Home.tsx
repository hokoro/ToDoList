import React from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/text.css';
import '../css/Home.css';

const Home: React.FC = () => {
    const navigate = useNavigate();
    return (
        <div className="main">
            <p>안녕하십니까</p>
            <p>ToDoList 개발자입니다.</p>
            <p>오늘의 할일을 기록하는 사이트입니다.</p>
            <p>이용 중 불편하신 사항은</p>
            <p>문의사항을 통해 문의하시기 바랍니다.</p>
            <div>
                <button className="btn btn-primary my-half"
                        onClick={() => navigate('/login')}>로그인</button>
                <button
                    className="btn btn-primary"
                    onClick={() => navigate('/signup')}
                >
                    회원가입
                </button>
            </div>
        </div>
    );
}

export default Home;