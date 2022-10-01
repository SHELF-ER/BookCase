import React  from 'react';
import { Link } from 'react-router-dom';
import ebookcss from '../css/index.module.css';

function Ebook() {
	document.body.style.backgroundImage = 'none';
  document.body.style.backgroundColor = 'white';
  return (
	<div className="Ebook">
		<section className={ebookcss.main}>
			<table className={ebookcss.table}>
				<tr>
					<td className={ebookcss.errorBook}>test</td>
					<td>test</td>
				</tr>
			</table>
		</section>
	</div>
  );
}

export default Ebook;
