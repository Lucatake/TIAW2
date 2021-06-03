//pegar o nome no localstorage
function pegarNomeLS(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let nav = document.getElementById('nav-bar-type');
    if (usuario.tipo == 0) {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="./../html/initialScreen.html" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas Atividades</a>
                <a href="./../html/addVaga.html">Adicionar Aula</a>
                <a href="./../html/feedbackScreen.html">Sistema de feedback</a>
            </div>
        `;
    } else {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas Atividades</a>
                <a href="#">Cadastrar Oportunidade</a>
                <a href="./../html/perfilScreen.html">Perfil</a>
            </div>
        `;
    }
    document.getElementById('nomeUsuarioField').innerHTML = `<i class="far fa-user"></i> ${usuario.nome}`;
}
/*Navigation bar*/
/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}

/*Fim Navigation bar*/

let btnAdd = document.getElementById('btn-adicionar')



function ativarButton(){
    if(titulo.value == "" || salario.value == "" || descricao.value == ""){
        
        alert("Algum campo não foi preenchido!");
        e.preventDefault()
    }else {
        let sal = String(salario.value);
        sal = parseInt(sal);
        cadastrarVaga(titulo.value, sal, descricao.value);
    }
}

function cadastrarVaga(titulo1, sal1, descricao1){
    let usuario = JSON.parse(localStorage.getItem('user'));
    console.log(titulo1);console.log(sal1); console.log(descricao1);
    let params = `empregador_id=${usuario.id}&titulo=${titulo1}&descricao=${descricao1}&salario=${sal1}`
    let xhr = new XMLHttpRequest();
    xhr.open('POST', `http://localhost:6543/vaga/add?`+params);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Enviado com sucesso!");
            window.location.reload();
        }
    }
    xhr.send(null);
}