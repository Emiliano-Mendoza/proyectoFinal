/**
 * 
 */

function darFormatoFechaCompleta(d){
		 
	return	`${
			    d.getDate().toString().padStart(2, '0')}/${
			    (d.getMonth()+1).toString().padStart(2, '0')}/${
			    d.getFullYear().toString().padStart(4, '0')} ${
			    d.getHours().toString().padStart(2, '0')}:${
			    d.getMinutes().toString().padStart(2, '0')}`;
	
}

function darFormatoFechaSimple(d){
		 
	return	`${
			    d.getDate().toString().padStart(2, '0')}/${
			    (d.getMonth()+1).toString().padStart(2, '0')}/${
			    d.getFullYear().toString().padStart(4, '0')}`;
	
}

