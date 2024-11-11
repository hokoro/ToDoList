import React from 'react';
import {Outlet} from 'react-router-dom';
import '../../css/text.css';
import '../../css/Layout.css';
function Layout(){
    return(
      <div>
          <header>
              <h1 className={"jua-regular"}>ToDoList</h1>
          </header>
      <hr/>
      <main className={"long-page"}>
        <Outlet/>
      </main>
      <hr/>
      <footer>
            <h3 className={"jua-regular"}>ToDoList</h3>
          <div>
              <span>개인정보</span>

              <span> 고객센터</span>

              <span> 이용약관</span>

              <span> 문의사항</span>
          </div>
      </footer>


      </div>
    );
}

export default Layout;