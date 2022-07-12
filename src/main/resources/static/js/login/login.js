const cards = document.querySelectorAll('.card');

/* View Controller
-----------------------------------------*/
const btns = document.querySelectorAll('.js-btn');
btns.forEach(btn => {
	btn.addEventListener('click', on_btn_click, true);
	btn.addEventListener('touch', on_btn_click, true);
});

function on_btn_click(e) {
	const nextID = e.currentTarget.getAttribute('data-target');
	const next = document.getElementById(nextID);
	if (!next) return;
	bg_change(nextID);
	view_change(next);
	return false;
}

/* Add class to the body */
function bg_change(next) {
	document.body.className = '';
	document.body.classList.add('is-' + next);
}

/* Add class to a card */
function view_change(next) {
	cards.forEach(card => { card.classList.remove('is-show'); });
	next.classList.add('is-show');
}


//////////////////////////////////////////////////////////////
const Mswitchers = [...document.querySelectorAll('.switcher-member')]

Mswitchers.forEach(item => {
	item.addEventListener('click', function() {
		Mswitchers.forEach(item => item.parentElement.classList.remove('is-active'))
		this.parentElement.classList.add('is-active')
	})
})

const Cswitchers = [...document.querySelectorAll('.switcher-company')]

Cswitchers.forEach(item => {
	item.addEventListener('click', function() {
		Cswitchers.forEach(item => item.parentElement.classList.remove('is-active'))
		this.parentElement.classList.add('is-active')
	})
})


/* 浮動提示 */
$(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

//一鍵輸入
$(function() {
	$('#memberA').click(function() {
		$('#username').val('A123456789')
		$('#password').val('12345')
	})
	$('#Admin').click(function() {
		$('#username').val('admin')
		$('#password').val('12345')
	})
	$('#compA').click(function() {
		$('#compid').val('87654321')
		$('#compwd').val('Aabc123zzz')
	})
	$('#compB').click(function() {
		$('#username').val('admin')
		$('#password').val('12345')
	})
	//memeber signup 一鍵
	$('#memberSignup').click(function() {
		$('#username').val('A123456789')
		$('#password').val('12345')
		
		
		
	})	
	//company signup 一鍵
		$('#companySignup').click(function() {
		$('#username').val('A123456789')
		$('#password').val('12345')
		
		
		
	})

});
