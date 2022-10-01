import React  from 'react';
import { Link } from 'react-router-dom';
import mformcss from '../css/mform.module.css';

function CreateMemberForm() {
  document.body.style.backgroundImage = 'none';
  document.body.style.backgroundColor = 'white';
  return (
	<div className="CreateMemberForm">
		<div className={mformcss.container}>
			<header className={mformcss.common}>
				<div className={mformcss.subtitle}>
					<h2><Link to={'/'} style={{ color: '#FFF' }}>HOME</Link></h2>
				</div>
				<div className={mformcss.topnav}>
					<ul>
						<li style={{ color: '#FFF' }}>&#10217;</li>
						<li><Link to={'/mform'}>회원 가입</Link></li>
					</ul>
				</div>
			</header>
			<form action="/members/new" method="post">
				<div className={mformcss.formGroup}>
					<label for="name">도서 제목</label>
					<input type="text" id="name" name="name" placeholder="제목을 입력하세요"/>
					<label for="bookNum">도서 번호</label>
					<input type="text" id="bookNum" name="bookNum" placeholder="번호를 입력하세요"/>
					<label for="borrower">빌린 사람</label>
					<input type="text" id="borrower" name="borrower" placeholder="이름을 입력하세요"/>
				</div>
				<button type="submit">등록</button>
			</form>
		</div>
	</div>
  );
}

export default CreateMemberForm;
